package com.nebula.dingding.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import com.nebula.dingding.common.annotation.NotRepeatSubmit;
import com.nebula.dingding.common.token.TokenInfo;
import com.nebula.dingding.util.ApiUtil;
import com.nebula.dingding.util.MD5Util;
import com.nebula.dingding.util.RedisUtil;

/**
 * 
 * @author zheyan.yan
 *
 */
@Component
public class TokenInterceptor implements HandlerInterceptor{
	   /* @Autowired
	    private RedisTemplate redisTemplate;*/
	    @Resource
		private RedisTemplate<String, Object> redisTemplate;
	    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	    /**
	     *
	     * @param request
	     * @param response
	     * @param handler 访问的目标方法
	     * @return
	     * @throws Exception
	     */
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        String token = request.getHeader("token");
	        String timestamp = request.getHeader("timestamp");
	        // 随机字符串
	        String nonce = request.getHeader("nonce");
	        String sign = request.getHeader("sign");
	        //Assert.isTrue(!StringUtils.isEmpty(token) && !StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(sign), "参数错误");
	        if ( StringUtils.isEmpty(token) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(sign)) {
	        	throw new RuntimeException("参数错误");
			}

	        // 获取超时时间
	        NotRepeatSubmit notRepeatSubmit = ApiUtil.getNotRepeatSubmit(handler);
	        long expireTime = notRepeatSubmit == null ? 5 * 60 * 1000 : notRepeatSubmit.value();

	        // 2. 请求时间间隔
	        long reqeustInterval = System.currentTimeMillis() - Long.valueOf(timestamp);
	        logger.info("过期时间："+expireTime+"，请求间隔时间（获取token时间与当前时间差）："+reqeustInterval);
	        //Assert.isTrue(reqeustInterval < expireTime, "请求超时，请重新请求");
	        if (reqeustInterval > expireTime) {
	        	throw new RuntimeException("请求超时，请重新请求");
			}
	        

	        // 3. 校验Token是否存在
	        RedisUtil redisUtil = new RedisUtil(redisTemplate);
	        TokenInfo tokenInfo = (TokenInfo) redisUtil.get(token);
	       
	        if (null==tokenInfo) {
	        	throw new RuntimeException("token已过期");
			}

	        // 4. 校验签名(将所有的参数加进来，防止别人篡改参数) 所有参数看参数名升续排序拼接成url
	        // 请求参数 + key值 +token + timestamp + nonce
	        String signString = ApiUtil.concatSignString(request) + tokenInfo.getAppInfo().getKey() + token + timestamp + nonce;
	        logger.info("拼接后的签名："+signString);
	        String signature = MD5Util.encode(signString);
	        boolean flag = signature.equals(sign);
	        //Assert.isTrue(flag, "签名错误");
	        if (!flag) {
	        	throw new RuntimeException("签名错误");
			}

	        // 5. 拒绝重复调用(第一次访问时存储，过期时间和请求超时时间保持一致), 只有标注不允许重复提交注解的才会校验
	        if (notRepeatSubmit != null) {
	        	 boolean exists = redisUtil.hasKey(sign);
	        	 //Assert.isTrue(!exists, "请勿重复提交");
	        	 if (exists) {
	 	        	throw new RuntimeException("请勿重复提交");
	 			}
	        	 redisUtil.setMilliSeconds(sign, 0, expireTime);
	        }

	        return true;
	    }
}

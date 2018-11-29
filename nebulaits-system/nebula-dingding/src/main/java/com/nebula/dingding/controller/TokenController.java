package com.nebula.dingding.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nebula.dingding.common.annotation.NotRepeatSubmit;
import com.nebula.dingding.common.token.AccessToken;
import com.nebula.dingding.common.token.ApiResponse;
import com.nebula.dingding.common.token.ApiResult;
import com.nebula.dingding.common.token.AppInfo;
import com.nebula.dingding.common.token.TokenInfo;
import com.nebula.dingding.entity.AuthToken;
import com.nebula.dingding.entity.ErrorHandleLogVo;
import com.nebula.dingding.entity.UserInfo;
import com.nebula.dingding.service.AuthTokenService;
import com.nebula.dingding.service.HandleErrorLogService;
import com.nebula.dingding.util.HttpUtil;
import com.nebula.dingding.util.JsonResult;
import com.nebula.dingding.util.MD5Util;
import com.nebula.dingding.util.RedisUtil;
import com.nebula.dingding.util.ResultCode;
@RestController
@RequestMapping("/api/token")
public class TokenController {
	
	    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
	    private static final Integer tokenExpireTime=7200;
	    @Resource
		private RedisTemplate<String, Object> redisTemplate;
	    @Autowired
		private HandleErrorLogService handleErrorLogService;
	    @Autowired
	    private AuthTokenService authTokenService;

	    /**
	     * API Token
	     *
	     * @param sign
	     * @return
	     */
	    @PostMapping("/api_token")
	    public ApiResponse<AccessToken> apiToken(@RequestParam("appId")String appId, @RequestParam("timestamp") String timestamp, @RequestParam("sign") String sign) {
	    	ApiResponse<AccessToken>  apiResponse = new ApiResponse<>();
	    	try {
	    		logger.info("传来参数为："+appId+","+timestamp+","+sign);
		    	Assert.isTrue(!StringUtils.isEmpty(appId) && !StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(sign), "参数错误");
		        long reqeustInterval = System.currentTimeMillis() - Long.valueOf(timestamp);
		        Assert.isTrue(reqeustInterval < 5 * 60 * 1000, "请求过期，请重新请求");
		        // 1. 根据appId查询数据库获取appSecret(模拟数据)
		        AppInfo appInfo = new AppInfo();
		        List<AuthToken> authInfoByAppId = authTokenService.getAuthInfoByAppId(appId);
		        if (authInfoByAppId.size()>0) {
		        	AuthToken authToken = authInfoByAppId.get(0);
		        	appInfo.setAppId(appId);
		        	appInfo.setKey(authToken.getAppKey());
				}else {
					ApiResult apiResult =  new ApiResult("401", "参数错误，appid值不存在，请更换相应信息");
					apiResponse.setResult(apiResult);
					return apiResponse;
				}
		        

		        // 2. 校验签名（该sign签名的意义只是为了验证服务端进行签名校验的功能）
		        String signString = timestamp + appId + appInfo.getKey();
		        String signature = MD5Util.encode(signString);
		        Assert.isTrue(signature.equals(sign), "签名错误");
		        // 3. 如果正确生成一个token保存到redis中，如果错误返回错误信息
		        AccessToken accessToken = this.saveToken(0, appInfo, null);
	            logger.info(accessToken.toString());
	            apiResponse=ApiResponse.success(accessToken);
			} catch (Exception e) {
				ApiResult apiResult =  new ApiResult("400", "异常："+e.getMessage());
				apiResponse.setResult(apiResult);
			}
	        return apiResponse;
	    }


	    //此为登录接口
	    @NotRepeatSubmit(120000)
	    @PostMapping("user_token")
	    public ApiResponse<UserInfo> userToken(String username, String password) {
	    	ApiResponse<UserInfo>  apiResponse = new ApiResponse<>();
	    	try {
	    		logger.info("----------------------------成功进入登录页面----------------------------------");
		        // 根据用户名查询密码, 并比较密码(密码可以RSA加密一下)
		        UserInfo userInfo = new UserInfo(username, "81255cb0dca1a5f304328a70ac85dcbd", "111111");
		        String pwd = password + userInfo.getSalt();
		        String passwordMD5 = MD5Util.encode(pwd);
		        Assert.isTrue(passwordMD5.equals(userInfo.getPassword()), "密码错误");
		        // 2. 保存Token
		        AppInfo appInfo = new AppInfo("1", "12345678954556");
		        AccessToken accessToken = this.saveToken(1, appInfo, userInfo);
		        userInfo.setAccessToken(accessToken);
		        apiResponse=ApiResponse.success(userInfo);
			} catch (Exception e) {
				ApiResult apiResult =  new ApiResult("400", "异常："+e.getMessage());
				apiResponse.setResult(apiResult);
			}
	    	
	        return apiResponse;
	    }
	    
	    /**
		 * 微服务查询接口测试
		 * @return
		 */
	    @NotRepeatSubmit(120000)
		@RequestMapping("/getLogs")
		@ResponseBody
		public JsonResult getLogs(@RequestBody String logsName) {
			logger.info("*****************成功进入***********"+logsName);
			JsonResult jsonResult = new JsonResult();
			try {
				List<ErrorHandleLogVo> logInfos = handleErrorLogService.getLogInfos();
				jsonResult.setCode(ResultCode.SUCCESS);
				jsonResult.setData(logInfos);
				jsonResult.setMsg("操作成功,"+logsName);
				
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult.setCode(ResultCode.EXCEPTION);
				jsonResult.setMsg(e.getMessage()+","+logsName);
			}
			return jsonResult;
		}
	    
	   

	    private AccessToken saveToken(int tokenType, AppInfo appInfo,  UserInfo userInfo) {
	        String token = UUID.randomUUID().toString();

	        // token有效期为2小时
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.SECOND, tokenExpireTime);
	        Date expTime = calendar.getTime();
	        Long expireTime = expTime.getTime();
	        // 4. 保存token
	        RedisUtil redisUtil = new RedisUtil(redisTemplate);
	        TokenInfo tokenInfo = new TokenInfo();
	        tokenInfo.setTokenType(tokenType);
	        tokenInfo.setAppInfo(appInfo);
	        if (tokenType == 1) {
	            tokenInfo.setUserInfo(userInfo);
	        }
	        redisUtil.set(token, tokenInfo);
	        redisUtil.expire(token, tokenExpireTime);
	        AccessToken accessToken = new AccessToken(token, expireTime);

	        return accessToken;
	    }

	    public static void main(String[] args) {
	       /* long timestamp = System.currentTimeMillis();
	        logger.info("时间戳为："+timestamp);
	        // timestamp + appId + appInfo.getKey();
	        String signString = timestamp + "1" + "12345678954556";
	        String sign = MD5Util.encode(signString);
	        logger.info("签名为："+sign);
	        //请求参数 + key值 + token + timestamp + nonce

	        logger.info("password=123456&username=yanzhe&-------------------");
	        String token="bdbf2202-76b1-46ad-9c0b-2d3d906be6c3";
	        signString = "password=123456&username=yanzhe&12345678954556"+ token + timestamp + "A1scr6";
	        logger.info("拼接后的签名："+signString);
	        sign = MD5Util.encode(signString);
	        //password=123456&username=yanzhe&12345678954556bdbf2202-76b1-46ad-9c0b-2d3d906be6c31543388303205A1scr6
	        logger.info(sign);*/
	    	
	    	
	    	//调用接口得到token信息
	    	//首先生成第一次签名信息，该签名信息为校验token的合法性
	    	long timestamp = System.currentTimeMillis();
	        logger.info("时间戳为--："+timestamp);
	        // timestamp + appId + appInfo.getKey();
	        String signString = timestamp + "1" + "12345678954556";
	        String sign = MD5Util.encode(signString);
	        logger.info("签名为："+sign);
	        //此时得到签名信息，进行调用接口得到token信息
	        String url ="http://localhost:8765/api/token/api_token?appId="+1+"&timestamp="+timestamp+"&sign="+sign;
	        String parame ="";
	        String sendPost = HttpUtil.sendPost(url, parame);
	        logger.info(sendPost);
	        //http://localhost:8765/api/token/api_token?appId=1&timestamp1543393841672&signc238045e1b0bd20e02445e4ab030148c
	        Gson gson = new Gson();
	        ApiResponse apiResponse = gson.fromJson(sendPost, ApiResponse.class);
	        logger.info(apiResponse.getData()+"");
	        String json = gson.toJson(apiResponse.getData());
	        logger.info(json);
	        
	        AccessToken data = gson.fromJson(json, AccessToken.class);
	        String token = data.getToken();
	        logger.info(data.getExpireTime()+"");
	        //得到token信息，此时编辑签名信息
	        signString = "password=123456&username=yanzhe&12345678954556"+ token + timestamp + "A1scr6";
	        logger.info("拼接后的签名："+signString);
	        sign = MD5Util.encode(signString);
	        logger.info("时间戳："+timestamp+",签名："+sign+",token:"+token);
	    	
	    }
}

package com.nebula.api.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nebula.api.common.annotation.UserLoginToken;
import com.nebula.api.entity.Users;
import com.nebula.api.service.TokenService;
import com.nebula.api.service.UserService;
import com.nebula.api.util.JsonResult;
import com.nebula.api.util.MD5Util;
import com.nebula.api.util.RedisUtil;
import com.nebula.api.util.ResultCode;

/**
 * 
 * @author zheyan.yan
 *
 */
@RestController
@RequestMapping("api")
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Resource
	private RedisTemplate<String, Object> redisTemplate;
    //登录
    @PostMapping("/login")
    public JsonResult login(@RequestBody Users user){
    	
    	JsonResult jsonResult = new JsonResult();
    	try {
    		 RedisUtil redisUtil = new RedisUtil(redisTemplate);
    		 Users userForBase=userService.findByUsername(user);
    	        if(userForBase==null){
    	        	jsonResult.setCode(ResultCode.FAIL_CODE);
    	        	jsonResult.setMsg("登录失败,用户不存在");
    	        	return jsonResult;
    	            /*jsonObject.put("message","登录失败,用户不存在");
    	            return jsonObject;*/
    	            
    	        }else {
    	            if (!userForBase.getPassword().equals(user.getPassword())){
    	            	jsonResult.setCode(ResultCode.FAIL_CODE);
    	            	jsonResult.setMsg("登录失败,密码错误");
    	            	return jsonResult;
    	               /* jsonObject.put("message","登录失败,密码错误");
    	                return jsonObject;*/
    	            }else {
    	                String token = tokenService.getToken(userForBase);
    	                /*jsonObject.put("token", token);
    	                jsonObject.put("token", tokenKey);
    	                jsonObject.put("user", userForBase);
    	                return jsonObject;*/
    	                //将token存入redis中，并设置token过期时间
    	                String tokenKey = UUID.randomUUID().toString();
    	                //对uuid进行MD5加密
    	                tokenKey = MD5Util.encode(tokenKey);
    	                redisUtil.set(tokenKey, token, 60);
    	                jsonResult.setData(tokenKey);
    	                jsonResult.setCode(ResultCode.SUCCESS);
    	                jsonResult.setMsg("操作成功");
    	               
    	            }
    	        }
		} catch (Exception e) {
			jsonResult.setMsg(e.getMessage());
    		jsonResult.setCode(ResultCode.EXCEPTION);
		}
    	return jsonResult;
       
    }
    
    /**
     * 存在该注解则进行登录token验证
     * @return
     */
    @UserLoginToken
    @PostMapping("/getMessage")
    @ResponseBody
    public JsonResult getMessage(){
    	JsonResult jsonResult =  new JsonResult();
    	try {
    		List<Users> findUsersInfos = userService.findUsersInfos();
    		jsonResult.setData(findUsersInfos);
    		jsonResult.setMsg("你已通过验证");
    		jsonResult.setCode(ResultCode.SUCCESS);
    		
		} catch (Exception e) {
			jsonResult.setMsg(e.getMessage());
    		jsonResult.setCode(ResultCode.EXCEPTION);
		}
        return jsonResult;
    }
}

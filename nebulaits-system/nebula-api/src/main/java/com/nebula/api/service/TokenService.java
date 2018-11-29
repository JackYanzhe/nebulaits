package com.nebula.api.service;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nebula.api.entity.Users;


/**
 * 
 * @author zheyan.yan
 *
 */
@Service
public class TokenService {
	
	
    public String getToken(Users user) {
        String token="";
        token= JWT.create().withAudience(user.getId())// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}

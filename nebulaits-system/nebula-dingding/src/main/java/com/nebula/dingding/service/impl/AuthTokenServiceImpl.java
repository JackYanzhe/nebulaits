package com.nebula.dingding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.dingding.dao.AuthTokenDao;
import com.nebula.dingding.entity.AuthToken;
import com.nebula.dingding.service.AuthTokenService;

@Service
public class AuthTokenServiceImpl implements AuthTokenService{

	@Autowired
	private AuthTokenDao authTokenDao;
	@Override
	public List<AuthToken> getAuthInfoByAppId(String appId) {
		return authTokenDao.getAuthInfoByAppId(appId);
	}

}

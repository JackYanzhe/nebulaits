package com.nebula.dingding.service;
import java.util.List;
import com.nebula.dingding.entity.AuthToken;

public interface AuthTokenService {

	/**
	 * 根据appid查询appkey值
	 */
	public List<AuthToken> getAuthInfoByAppId(String appId);
	
}

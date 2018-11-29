package com.nebula.dingding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nebula.dingding.common.annotation.MysqlRepository;
import com.nebula.dingding.entity.AuthToken;
@MysqlRepository
public interface AuthTokenDao {

	/**
	 * 根据appid查询对应的appkey值
	 */
	public List<AuthToken> getAuthInfoByAppId(@Param("appId") String appId);
}

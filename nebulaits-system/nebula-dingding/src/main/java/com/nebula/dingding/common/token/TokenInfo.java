package com.nebula.dingding.common.token;

import java.io.Serializable;

import com.nebula.dingding.entity.UserInfo;

/**
 * 
 * @author zheyan.yan
 *
 */
public class TokenInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** token类型: api:0 、user:1 */
    private Integer tokenType;

    /** App 信息 */
    private AppInfo appInfo;

    /** 用户其他数据 */
    private UserInfo userInfo;

	public Integer getTokenType() {
		return tokenType;
	}

	public void setTokenType(Integer tokenType) {
		this.tokenType = tokenType;
	}

	public AppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo) {
		this.appInfo = appInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
    
    
}

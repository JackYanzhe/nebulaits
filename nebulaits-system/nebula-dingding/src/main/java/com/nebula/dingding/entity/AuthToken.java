package com.nebula.dingding.entity;

public class AuthToken {

	private Integer id;
	private String appId;
	private String appKey;
	private Integer userId;
	
	
	public AuthToken() {
		super();
	}
	@Override
	public String toString() {
		return "AuthToken [id=" + id + ", appId=" + appId + ", appKey=" + appKey + ", userId=" + userId + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}

package com.nebula.dingding.common.token;

import java.io.Serializable;
/**
 * 
 * @author zheyan.yan
 *
 */
public class AppInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** App id */
    private String appId;
    /** API 秘钥 */
    private String key;
    
    public AppInfo() {
		
	}
	public AppInfo(String appId, String key) {
		super();
		this.appId = appId;
		this.key = key;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
    
}

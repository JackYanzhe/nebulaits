package com.nebula.dingding.common.token;

import java.io.Serializable;

/**
 * 
 * @author zheyan.yan
 *
 */
public class AccessToken implements Serializable{
	private static final long serialVersionUID = 1L;
    /** token */
    private String token;

    /** 失效时间 */
    private Long expireTime;

    
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Long getExpireTime() {
		return expireTime;
	}


	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	public AccessToken(String token, Long expireTime) {
		this.token = token;
		this.expireTime = expireTime;
	}
	public AccessToken() {
		
	}

	/*public AccessToken(String token, Date expireTime) {
		super();
		this.token = token;
		this.expireTime = expireTime;
	}
	public AccessToken() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}*/
	@Override
	public String toString() {
		return "AccessToken [token=" + token + ", expireTime=" + expireTime + "]";
	}
    
}

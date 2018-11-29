package com.nebula.dingding.common.token;

import java.io.Serializable;

/**
 * 
 * @author zheyan.yan
 *
 */
public class ApiResult implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 代码 */
    private String code;

    /** 结果 */
    private String msg;
    
    public ApiResult() {
		
	}
    
	public ApiResult(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
}

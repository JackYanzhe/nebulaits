package com.nebula.dingding.service;

import com.alibaba.fastjson.JSONObject;

public interface DingLoginService {

	public JSONObject getDingLogin(String code);
}

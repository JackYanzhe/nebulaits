package com.nebula.api.service;

import java.util.List;

import com.nebula.api.entity.ErrorHandleLogVo;

public interface NebulaApiService {
	List<ErrorHandleLogVo> getLogInfos();
}

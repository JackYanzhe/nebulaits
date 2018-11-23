package com.nebula.dingding.service;

import java.util.List;

import com.nebula.dingding.entity.ErrorHandleLogVo;

public interface HandleErrorLogService {
	List<ErrorHandleLogVo> getLogInfos();
}

package com.nebula.feweb.service;

import java.util.List;

import com.nebula.feweb.entity.ErrorHandleLogVo;

public interface HandleErrorLogService {
	List<ErrorHandleLogVo> getLogInfos();
}

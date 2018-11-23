package com.nebula.dingding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.dingding.dao.HandErrorLogDao;
import com.nebula.dingding.entity.ErrorHandleLogVo;
import com.nebula.dingding.service.HandleErrorLogService;

@Service
public class HandleErrorLogServiceImpl implements HandleErrorLogService{

	@Autowired
	private HandErrorLogDao HandErrorLogDao;
	@Override
	public List<ErrorHandleLogVo> getLogInfos() {
		
		return HandErrorLogDao.getLogInfos();
	}

}

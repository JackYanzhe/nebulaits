package com.nebula.feweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.feweb.dao.HandErrorLogDao;
import com.nebula.feweb.entity.ErrorHandleLogVo;
import com.nebula.feweb.service.HandleErrorLogService;

@Service
public class HandleErrorLogServiceImpl implements HandleErrorLogService{

	@Autowired
	private HandErrorLogDao HandErrorLogDao;
	@Override
	public List<ErrorHandleLogVo> getLogInfos() {
		
		return HandErrorLogDao.getLogInfos();
	}

}

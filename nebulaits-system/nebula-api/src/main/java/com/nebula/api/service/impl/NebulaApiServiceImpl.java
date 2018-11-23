package com.nebula.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebula.api.dao.HandErrorLogDao;
import com.nebula.api.entity.ErrorHandleLogVo;
import com.nebula.api.service.NebulaApiService;
@Service
public class NebulaApiServiceImpl implements NebulaApiService{

	@Autowired
	private HandErrorLogDao HandErrorLogDao;
	@Override
	public List<ErrorHandleLogVo> getLogInfos() {
		
		return HandErrorLogDao.getLogInfos();
	}

}

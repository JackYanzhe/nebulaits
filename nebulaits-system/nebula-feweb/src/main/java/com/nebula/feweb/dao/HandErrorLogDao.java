package com.nebula.feweb.dao;

import java.util.List;

import com.nebula.feweb.common.annotation.MysqlRepository;
import com.nebula.feweb.entity.ErrorHandleLogVo;
@MysqlRepository
public interface HandErrorLogDao {

	List<ErrorHandleLogVo> getLogInfos();
}

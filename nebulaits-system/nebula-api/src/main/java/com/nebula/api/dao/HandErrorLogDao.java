package com.nebula.api.dao;

import java.util.List;

import com.nebula.api.common.annotation.MysqlRepository;
import com.nebula.api.entity.ErrorHandleLogVo;
@MysqlRepository
public interface HandErrorLogDao {

	List<ErrorHandleLogVo> getLogInfos();
}

package com.nebula.dingding.dao;

import java.util.List;

import com.nebula.dingding.common.annotation.MysqlRepository;
import com.nebula.dingding.entity.ErrorHandleLogVo;
@MysqlRepository
public interface HandErrorLogDao {

	List<ErrorHandleLogVo> getLogInfos();
}

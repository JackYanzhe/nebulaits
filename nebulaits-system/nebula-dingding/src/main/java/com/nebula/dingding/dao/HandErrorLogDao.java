package com.nebula.dingding.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nebula.dingding.common.annotation.MysqlRepository;
import com.nebula.dingding.entity.ErrorHandleLogVo;
@MysqlRepository
public interface HandErrorLogDao {
    //查询所有logs信息
	List<ErrorHandleLogVo> getLogInfos();
	
	//新增数据log操作
	Integer insertLogInfo(ErrorHandleLogVo logVo);
	
	//根据memo进行判断
	List<ErrorHandleLogVo> judgeErrorLogs(@Param("memo") String memo);
}

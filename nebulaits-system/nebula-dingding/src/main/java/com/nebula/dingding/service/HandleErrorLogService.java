package com.nebula.dingding.service;

import java.util.List;

import com.nebula.dingding.entity.ErrorHandleLogVo;

public interface HandleErrorLogService {
	List<ErrorHandleLogVo> getLogInfos();
	
	//新增数据log操作,验证事务--try-catch 捕获到异常并抛出运行时异常
    Integer insertLogInfo(ErrorHandleLogVo logVo) ;
    
    ////新增数据log操作,验证事务，向上抛出异常，但注解加回滚操作@Transactional(rollbackFor=Exception.class)
    Integer insertLogInfoRollback(ErrorHandleLogVo logVo) throws Exception ;
    
    //事务的范围和锁的范围问题,避免并发时出现脏数据问题,保证锁的范围比事务的范围大即可
    Integer insertLogInfoSynch(ErrorHandleLogVo logVo) ;
    
    //事务的范围和锁的范围问题,注意：此为错误测试，即当事务范围比锁的范围大的时候并发情况下会出现脏数据现象
    Integer insertLogInfoSynchErrorTest(ErrorHandleLogVo logVo) ;
    
}

package com.nebula.dingding.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Override
	@Transactional
	public Integer insertLogInfo(ErrorHandleLogVo logVo) {
		int num =-1;
		try {
			System.out.println("开始进入插入操作");
			num = HandErrorLogDao.insertLogInfo(logVo);
			List<ErrorHandleLogVo> logInfos = HandErrorLogDao.getLogInfos();
			for (ErrorHandleLogVo errorHandleLogVo : logInfos) {
				System.out.println(errorHandleLogVo.toString());
			}
			System.out.println(1/0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		
		
		return num;
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insertLogInfoRollback(ErrorHandleLogVo logVo) throws Exception {
		    int num =-1;
			System.out.println("开始进入插入操作");
			num = HandErrorLogDao.insertLogInfo(logVo);
			List<ErrorHandleLogVo> logInfos = HandErrorLogDao.getLogInfos();
			for (ErrorHandleLogVo errorHandleLogVo : logInfos) {
				System.out.println(errorHandleLogVo.toString());
				throw new SQLException("数据库异常");
			}
			
		
		return num;
	}
	
	/**
	 * 事务的范围和锁的范围问题,避免并发时出现脏数据问题
	 * 必须保证锁的范围比事务的范围大才可以
	 * 
	 * @param logVo
	 * @return
	 */
	@Override
	@Transactional
	public synchronized Integer insertLogInfoSynch(ErrorHandleLogVo logVo) {
		int num =-1;
		try {
			System.out.println("开始进入插入操作");
			//根据memo查询是否存在该条
			List<ErrorHandleLogVo> judgeErrorLogs = HandErrorLogDao.judgeErrorLogs(logVo.getMemo());
			if (judgeErrorLogs.size()>0) {
				//此时说明已存在该数据，不能重新插入操作
				System.out.println("此时存在该条数据，不能进行插入操作");
			}else {
				System.out.println("----------------------------开始等待--------------------------");
				//模拟并发情况
				Thread.sleep(30000);
				//此时同步锁的范围>=事务的范围，这个时候才保证同步，不会发生脏数据产生
				insertAvoidLogSynch(logVo);
				System.out.println("--------插入成功-----");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		return num;
	}

	/**
	 * 注意：“此为错误示范”：：事务的范围和锁的范围问题,避免并发时出现脏数据问题,保证锁的范围比事务的范围大即可
	 */
	@Override
	@Transactional
	public  Integer insertLogInfoSynchErrorTest(ErrorHandleLogVo logVo) {
		int num =-1;
		try {
			System.out.println("开始进入插入操作");
			//根据memo查询是否存在该条
			List<ErrorHandleLogVo> judgeErrorLogs = HandErrorLogDao.judgeErrorLogs(logVo.getMemo());
			if (judgeErrorLogs.size()>0) {
				//此时说明已存在该数据，不能重新插入操作
				System.out.println("此时存在该条数据，不能进行插入操作");
			}else {
				System.out.println("----------------------------开始等待--------------------------");
				
				//此时事务范围比同步锁范围大，导致脏数据产生，插入多条重复数据信息
				insertAvoidLogSynchErrorTest(logVo);
				System.out.println("--------插入成功-----");
				//模拟并发情况
				Thread.sleep(30000);
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		return num;
	}
	
	
   /**
    * 插入service方法
    */
	public synchronized Integer insertAvoidLogSynch(ErrorHandleLogVo logVo) {
		Integer num = HandErrorLogDao.insertLogInfo(logVo);
		return num;
		
	}
	
	 /**
	    * 插入service方法,此时为事务的范围比锁的范围大导致
	    */
	@Transactional
	public synchronized Integer insertAvoidLogSynchErrorTest(ErrorHandleLogVo logVo) {
		Integer num = HandErrorLogDao.insertLogInfo(logVo);
		return num;
			
	}

	
}

package com.nebula.dingding;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nebula.dingding.dao.AuthTokenDao;
import com.nebula.dingding.dao.HandErrorLogDao;
import com.nebula.dingding.entity.AuthToken;
import com.nebula.dingding.entity.ErrorHandleLogVo;
import com.nebula.dingding.service.HandleErrorLogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NebulaDingdingTest {

	@Autowired
    HandErrorLogDao handErrorLogDao;
	//autowired:以名字注入  resurce: 以类型注入
	@Autowired
	AuthTokenDao authTokenDao;
	@Autowired
	HandleErrorLogService handleErrorLogService;
	/**
	 * 测试微服务数据库连接测试（读取远程配置）
	 */
	@Test
	public void testGetErrorLogs() throws Exception {
		List<ErrorHandleLogVo> logInfos = handErrorLogDao.getLogInfos();
		System.out.println("查出条数为："+logInfos.size());
		for (ErrorHandleLogVo errorHandleLogVo : logInfos) {
			System.out.println(errorHandleLogVo.toString());
		}
		
	}
	
	/**
	 * 测试微服务数据库连接测试（读取远程配置）
	 */
	@Test
	public void testGetAppkey() throws Exception {
		List<AuthToken> authInfoByAppId = authTokenDao.getAuthInfoByAppId("1");
		
		for (AuthToken authToken : authInfoByAppId) {
			System.out.println(authToken.toString());
		}
		
	}
	
	/**
	 * 测试插入操作
	 */
	@Test
	public void testInsertLogs() throws Exception {
		ErrorHandleLogVo errorHandleLogVo = new ErrorHandleLogVo();
		errorHandleLogVo.setFieldName("testTransactional");
		errorHandleLogVo.setResult("result-Ttnal");
		errorHandleLogVo.setMemo("这是新的插入操作");
		errorHandleLogVo.setType("springboot");
		errorHandleLogVo.setTypeDetail("测试事务");
		errorHandleLogVo.setDate(new Date());
		Integer insertLogInfo = handErrorLogDao.insertLogInfo(errorHandleLogVo);
		System.out.println(insertLogInfo+"--------------------");
	}
	
	/**
	 * 测试事务回滚操作
	 */
	@Test
	public void testTranstnal()  {
		try {
			ErrorHandleLogVo errorHandleLogVo = new ErrorHandleLogVo();
			errorHandleLogVo.setFieldName("Transactional");
			errorHandleLogVo.setResult("result-Ttnal");
			errorHandleLogVo.setMemo("这是新的插入操作");
			errorHandleLogVo.setType("springboot");
			errorHandleLogVo.setTypeDetail("测试事务");
			errorHandleLogVo.setDate(new Date());
			handleErrorLogService.insertLogInfo(errorHandleLogVo);
		} catch (Exception e) {
			e.getMessage();
			
		}
	
	}
	
	/**
	 * 测试事务回滚操作
	 */
	@Test
	public void testTranstnalRollback()  {
		try {
			ErrorHandleLogVo errorHandleLogVo = new ErrorHandleLogVo();
			errorHandleLogVo.setFieldName("Transactional");
			errorHandleLogVo.setResult("result-Ttnal");
			errorHandleLogVo.setMemo("这是新的插入操作");
			errorHandleLogVo.setType("springboot");
			errorHandleLogVo.setTypeDetail("测试事务");
			errorHandleLogVo.setDate(new Date());
			handleErrorLogService.insertLogInfoRollback(errorHandleLogVo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
}

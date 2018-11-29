package com.nebula.dingding;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class NebulaDingdingTest {

	@Autowired
    HandErrorLogDao handErrorLogDao;
	//autowired:以名字注入  resurce: 以类型注入
	@Autowired
	AuthTokenDao authTokenDao;
	
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
	
}

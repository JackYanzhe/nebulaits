package com.nebula.dingding;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nebula.dingding.dao.HandErrorLogDao;
import com.nebula.dingding.entity.ErrorHandleLogVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NebulaDingdingTest {

	@Autowired
    HandErrorLogDao handErrorLogDao;
	
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
}

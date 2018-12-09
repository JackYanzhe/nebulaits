package com.nebula.dingding.controller;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nebula.dingding.entity.ErrorHandleLogVo;
import com.nebula.dingding.service.HandleErrorLogService;
import com.nebula.dingding.util.JsonResult;
import com.nebula.dingding.util.ResultCode;

@RequestMapping("/logs")
@Controller
public class ErrorHandleLogController {

	private static final Logger logger = LoggerFactory.getLogger(ErrorHandleLogController.class);
	@Autowired
	private HandleErrorLogService handleErrorLogService;
	
	/**
	 * 注意：“此为错误示范”： 插入信息测试事务并发问题处理（脏数据，原因事务范围比锁范围大导致）
	 * @return
	 */
	@RequestMapping("/getLogsSynchErrorTest")
	@ResponseBody
	public JsonResult getLogsSynchErrorTest(String memo) {
		System.out.println("*****************成功进入***********");
		JsonResult jsonResult = new JsonResult();
		try {
			ErrorHandleLogVo errorHandleLogVo = new ErrorHandleLogVo();
			errorHandleLogVo.setFieldName("Transactional");
			errorHandleLogVo.setResult("result-Ttnal");
			errorHandleLogVo.setMemo(memo);
			errorHandleLogVo.setType("springboot");
			errorHandleLogVo.setTypeDetail("测试事务");
			errorHandleLogVo.setDate(new Date());
			handleErrorLogService.insertLogInfoSynchErrorTest(errorHandleLogVo);
			jsonResult.setCode(ResultCode.SUCCESS);
			//jsonResult.setData(logInfos);
			//jsonResult.setMsg("操作成功,"+name);
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(ResultCode.EXCEPTION);
			//jsonResult.setMsg(e.getMessage()+","+name);
		}
		return jsonResult;
	}
	
	
	/**
	 * 此为正确的处理事务及同步问题接口
	 * @return
	 */
	@RequestMapping("/getLogsSynch")
	@ResponseBody
	public JsonResult getLogsSynch(String memo) {
		System.out.println("*****************成功进入***********");
		JsonResult jsonResult = new JsonResult();
		try {
			ErrorHandleLogVo errorHandleLogVo = new ErrorHandleLogVo();
			errorHandleLogVo.setFieldName("Transactional");
			errorHandleLogVo.setResult("result-Ttnal");
			errorHandleLogVo.setMemo(memo);
			errorHandleLogVo.setType("springboot");
			errorHandleLogVo.setTypeDetail("测试事务");
			errorHandleLogVo.setDate(new Date());
			handleErrorLogService.insertLogInfoSynch(errorHandleLogVo);
			jsonResult.setCode(ResultCode.SUCCESS);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(ResultCode.EXCEPTION);
			//jsonResult.setMsg(e.getMessage()+","+name);
		}
		return jsonResult;
	}
	
	

   
	
}

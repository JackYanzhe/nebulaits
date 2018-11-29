package com.nebula.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.api.common.annotation.UserLoginToken;
import com.nebula.api.entity.ErrorHandleLogVo;
import com.nebula.api.service.NebulaApiService;
import com.nebula.api.service.SchedualServiceHi;
import com.nebula.api.util.JsonResult;
import com.nebula.api.util.ResultCode;

/**
 * 
 * @author zheyan.yan
 *
 */
@RestController
public class HiController {


    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    SchedualServiceHi schedualServiceHi;
    @Autowired
    private NebulaApiService nebulaApiService;
   
    @GetMapping(value = "/getDdLogsInterface")
    public String getHomeInterface(@RequestParam String name) {
    	System.out.println("----开始接口调用--------");
        return schedualServiceHi.getHomeInterfaceTest(name);
    }
    
    /**
	 * 微服务查询接口测试
	 * @return
	 */
    @UserLoginToken
	@RequestMapping("/getApiLogs")
	@ResponseBody
	public JsonResult getLogs() {
		System.out.println("*****************成功进入***********");
		JsonResult jsonResult = new JsonResult();
		try {
			List<ErrorHandleLogVo> logInfos = nebulaApiService.getLogInfos();
			jsonResult.setCode(ResultCode.SUCCESS);
			jsonResult.setData(logInfos);
			jsonResult.setMsg("nebula-api接口调用成功,");
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(ResultCode.EXCEPTION);
			jsonResult.setMsg(e.getMessage());
		}
		return jsonResult;
	}
    
    
}
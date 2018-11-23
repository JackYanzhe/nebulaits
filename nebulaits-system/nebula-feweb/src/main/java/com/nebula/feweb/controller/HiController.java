package com.nebula.feweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.feweb.entity.ErrorHandleLogVo;
import com.nebula.feweb.service.HandleErrorLogService;
import com.nebula.feweb.service.SchedualServiceHi;

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
    private HandleErrorLogService handleErrorLogService;
   
    @GetMapping(value = "/getApiLogsInterface")
    public String getHomeInterface() {
    	System.out.println("----开始接口调用--------");
        return schedualServiceHi.getApiLogsInterface();
    }
    
    @GetMapping(value = "/getFewebLogsInterface")
    public List<ErrorHandleLogVo> getFeWebLogs() {
    	System.out.println("----开始接口调用--------");
    	List<ErrorHandleLogVo> logInfos = new ArrayList<>();
    	try {
    		logInfos = handleErrorLogService.getLogInfos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ErrorHandleLogVo errorHandleLogVo = new ErrorHandleLogVo();
			errorHandleLogVo.setResult(e.getMessage());
			logInfos.add(errorHandleLogVo);
		}
    	
    	
        return logInfos;
    }
    
}
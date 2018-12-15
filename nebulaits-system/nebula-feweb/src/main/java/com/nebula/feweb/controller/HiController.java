package com.nebula.feweb.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.feweb.entity.ErrorHandleLogVo;
import com.nebula.feweb.service.HandleErrorLogService;
import com.nebula.feweb.service.SchedualServiceHi;
import com.nebula.feweb.utils.JsonResult;
import com.nebula.feweb.utils.MySubTUtil;
import com.nebula.feweb.utils.Page;
import com.nebula.feweb.utils.ResultCode;

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
    
    /**
     * 分页查询
     */
    @GetMapping(value = "/getFeWebPageLogs")
    @ResponseBody
    public JsonResult getFeWebPageLogs(int pageNo,int pageSize) {
    	System.out.println("----开始接口调用--------");
    	JsonResult jsonResult = new JsonResult();
    	List<ErrorHandleLogVo> logInfos = new ArrayList<>();
    	try {
    		logInfos = handleErrorLogService.getLogInfos();
    		
    		Page<ErrorHandleLogVo> page = MySubTUtil.getPage(new Page<>(pageNo, pageSize), logInfos);
    		if (page!=null) {
				jsonResult.setCode(ResultCode.SUCCESS);
				jsonResult.setData(page);
				jsonResult.setMsg("成功");
			}else {
				jsonResult.setCode(ResultCode.FAIL_CODE);
				jsonResult.setMsg("查询失败");
				jsonResult.setData(null);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ErrorHandleLogVo errorHandleLogVo = new ErrorHandleLogVo();
			errorHandleLogVo.setResult(e.getMessage());
			logInfos.add(errorHandleLogVo);
		}
    	 return jsonResult;
    }
    
}
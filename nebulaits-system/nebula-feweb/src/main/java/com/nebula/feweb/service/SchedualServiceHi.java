package com.nebula.feweb.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nebula.feweb.service.fallback.SchedualServiceHiHystric;

/**
 * 
 * @author zheyan.yan
 *
 */
@FeignClient(value = "nebula-api",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
   
    @RequestMapping(value = "/getApiLogs",method = RequestMethod.GET)
    String getApiLogsInterface();
    
    
}


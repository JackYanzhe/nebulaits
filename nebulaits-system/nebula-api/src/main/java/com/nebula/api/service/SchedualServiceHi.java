package com.nebula.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nebula.api.service.fallback.SchedualServiceHiHystric;

/**
 * 
 * @author zheyan.yan
 *
 */
@FeignClient(value = "nebula-dingding",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
   
    @RequestMapping(value = "/dingUser/getLogs",method = RequestMethod.GET)
    String getHomeInterfaceTest(@RequestParam(value = "msg") String name);
    
    
}


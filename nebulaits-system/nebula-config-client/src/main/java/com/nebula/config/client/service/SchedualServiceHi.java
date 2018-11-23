package com.nebula.config.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nebula.config.client.service.fallback.SchedualServiceHiHystric;

/**
 * 
 * @author zheyan.yan
 *
 */
@FeignClient(value = "nebula-config-client2",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {
   
    @RequestMapping(value = "/homeInterfaceTest",method = RequestMethod.GET)
    String getHomeInterfaceTest(@RequestParam(value = "msg") String name);
    
    
}


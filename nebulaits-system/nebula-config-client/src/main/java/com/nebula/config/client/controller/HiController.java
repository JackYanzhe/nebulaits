package com.nebula.config.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nebula.config.client.service.SchedualServiceHi;

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
   
    @GetMapping(value = "/getHomeInterface")
    public String getHomeInterface(@RequestParam String name) {
    	System.out.println("----开始接口调用--------");
        return schedualServiceHi.getHomeInterfaceTest(name);
    }
}
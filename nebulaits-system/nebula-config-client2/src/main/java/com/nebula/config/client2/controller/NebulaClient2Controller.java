package com.nebula.config.client2.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class NebulaClient2Controller {

	 @RequestMapping("/homeInterfaceTest")
	    public String homeInterfaceTest(@RequestParam(value = "msg", defaultValue = "peizhi") String msg) {
	    	
	    	    String localIP = "";
	    	    InetAddress addr = null;
				try {
					addr = (InetAddress) InetAddress.getLocalHost();
					//获取本机IP
			    	localIP = addr.getHostAddress().toString();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}  
	    	    String message = "接口传来信息："+msg + "该接口ip及端口为："+localIP;
	    	    
	            return message;
	            
	    }
}

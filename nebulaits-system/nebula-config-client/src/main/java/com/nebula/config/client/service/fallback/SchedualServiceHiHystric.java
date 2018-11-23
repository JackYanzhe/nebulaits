package com.nebula.config.client.service.fallback;

import org.springframework.stereotype.Component;

import com.nebula.config.client.service.SchedualServiceHi;

/**
 * 断路器熔断机制
 * @author zheyan.yan
 *
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

	@Override
	public String getHomeInterfaceTest(String name) {
		 return "sorry, you are fail,"+name;
	}
}
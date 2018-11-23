package com.nebula.feweb.service.fallback;

import org.springframework.stereotype.Component;

import com.nebula.feweb.service.SchedualServiceHi;

/**
 * 断路器熔断机制
 * @author zheyan.yan
 *
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

	@Override
	public String getApiLogsInterface() {
		 return "sorry, you are fail";
	}
}
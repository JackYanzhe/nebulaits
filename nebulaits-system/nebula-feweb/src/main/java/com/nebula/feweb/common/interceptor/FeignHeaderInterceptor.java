package com.nebula.feweb.common.interceptor;

import org.springframework.http.HttpHeaders;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignHeaderInterceptor implements RequestInterceptor{

	@Override
	public void apply(RequestTemplate template) {
		template.header(HttpHeaders.AUTHORIZATION, "token");
		
		
	}
	public interface RequestInterceptor {
		  void apply(RequestTemplate template);
	}
}

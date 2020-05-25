package com.example.demo.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;


public class FeignClientReqInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		final String AUTHORIZATION_HEADER = "Authorization";
		System.out.println("feign req interceptor");
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (requestAttributes == null) {
			return;
		}
		HttpServletRequest request = requestAttributes.getRequest();
		if (request == null) {
			return;
		}
		String authToken = request.getHeader(AUTHORIZATION_HEADER);
		if (authToken == null) {
			return;
		}
		requestTemplate.header(AUTHORIZATION_HEADER, authToken);
	}

}

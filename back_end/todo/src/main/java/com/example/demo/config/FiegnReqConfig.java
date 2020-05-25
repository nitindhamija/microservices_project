package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import feign.Logger;
import feign.Request;

//@Configuration
public class FiegnReqConfig {
	
	   public static final int connectTimeOutMillis = 12000;
	    public static final int readTimeOutMillis = 12000;

	    @Bean
	    public Request.Options options() {
	        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
	    }
	  @Bean
	    public FeignClientReqInterceptor feignClientReqInterceptor() {
	        return new FeignClientReqInterceptor();
	    }
	  
	  @Bean
	   Logger.Level feignLoggerLevel() {
	      return Logger.Level.FULL;
	   }
	 
}

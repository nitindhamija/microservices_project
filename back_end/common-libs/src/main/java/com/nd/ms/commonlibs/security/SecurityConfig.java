package com.nd.ms.commonlibs.security;

import com.nd.ms.commonlibs.security.CommonAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
  @Bean
  public CommonAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
    return new CommonAuthenticationEntryPoint();
  }
}

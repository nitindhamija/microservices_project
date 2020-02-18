package com.nd.ms.authservice.config;

import com.nd.ms.authservice.config.JwtAuthenticationEntryPoint;
import com.nd.ms.authservice.config.JwtAuthenticationFilter;
import javax.annotation.Resource;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Resource(name = "userService")
  private UserDetailsService userDetailsService;
  
  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;
  
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  
  @Autowired
  public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.userDetailsService)
      .passwordEncoder((PasswordEncoder)encoder());
  }
  
  @Bean
  public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
    return new JwtAuthenticationFilter();
  }
  
  protected void configure(HttpSecurity http) throws Exception {
    http.headers().frameOptions().disable();
    ((HttpSecurity)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)http.cors().and()).csrf().disable())
      .authorizeRequests()
      .antMatchers(new String[] { "/token/*", "/signup", "/info", "/health", "/role/*", "/h2/**", "/favicon.ico" })).permitAll()
      .anyRequest()).authenticated()
      .and())
      .exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint)this.unauthorizedHandler).and())
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http
      .addFilterBefore((Filter)authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
  }
  
  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}

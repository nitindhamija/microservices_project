package com.nd.ms.commonlibs.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonAuthenticationTokenFilter extends OncePerRequestFilter {
  private Logger logger = LoggerFactory.getLogger(com.nd.ms.commonlibs.security.CommonAuthenticationTokenFilter.class);
  
  public static final String HEADER_STRING = "Authorization";
  
  @Autowired
  private RestTemplate restTemplate;
  
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authToken = request.getHeader("Authorization");
    if (!StringUtils.isEmpty(authToken) && SecurityContextHolder.getContext().getAuthentication() == null)
      try {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authToken);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        HttpEntity<String> entity = new HttpEntity("", (MultiValueMap)headers);
        ResponseEntity<String> responseEntity = 
          this.restTemplate.exchange(
            "http://AUTH-SERVICE/auth/current", 
            HttpMethod.POST, 
            entity, 
            String.class);
    	String jsonUserDetails = responseEntity.getBody();
        UserDetails userDetails = prepareUserDetails(jsonUserDetails);
       // UserDetails userDetails = (UserDetails)responseEntity.getBody();
        if (userDetails != null) {
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authentication.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication((Authentication)authentication);
        } 
      } catch (Exception e) {
        this.logger.error(e.getMessage());
      }  
    filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
  }
  
  private UserDetails prepareUserDetails(String jsonUserDetails) throws JsonProcessingException, IOException{
  	
  	ObjectMapper objectMapper = new ObjectMapper();
  	JsonNode root = objectMapper.readTree(jsonUserDetails);
  	System.out.println(jsonUserDetails);
  //	String userId = root.get("dbUser").get("id").asText();
  	String username = root.get("username").asText();
  	boolean isEnabled =  root.get("enabled").asBoolean();
  	
  	List<SimpleGrantedAuthority> authorities = new ArrayList<>();
  	
  	Iterator<JsonNode> authoritiesIterator = root.get("authorities").elements();
  	while(authoritiesIterator.hasNext()){
  		JsonNode authorityNode = authoritiesIterator.next();
  		authorities.add(new SimpleGrantedAuthority(authorityNode.get("authority").asText()));
  	}
  	
  	return new AuthUser( username, authorities, isEnabled);
  }
}

package com.nd.ms.authservice.controller;

import com.nd.ms.authservice.config.TokenProvider;
import com.nd.ms.authservice.model.AuthToken;
import com.nd.ms.authservice.model.User;
import com.nd.ms.authservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private TokenProvider jwtTokenUtil;
  
  @Autowired
  private IUserService userService;
  
  @RequestMapping(value = {"/token/generate-token"}, method = {RequestMethod.POST})
  public ResponseEntity<?> register(@RequestBody User loginUser) throws AuthenticationException {
    Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken(loginUser
          
          .getUserName(), loginUser
          .getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = this.jwtTokenUtil.generateToken(authentication);
    return ResponseEntity.ok(new AuthToken(token));
  }
  
  @GetMapping({"/info"})
  public ResponseEntity<String> getAppStatus() {
    System.out.println("auth-service is up and running");
    return new ResponseEntity("auth-service is up and running", HttpStatus.OK);
  }
  
  @GetMapping({"/health"})
  public ResponseEntity<String> getHealthInfo() {
    System.out.println("auth-service health is good");
    return new ResponseEntity("auth-service health is good", HttpStatus.OK);
  }
}

package com.nd.ms.authservice.controller;

import com.nd.ms.authservice.config.TokenProvider;
import com.nd.ms.authservice.model.AuthToken;
import com.nd.ms.authservice.model.User;
import com.nd.ms.authservice.service.IUserService;
import com.nd.ms.commonlibs.exception.UnAuthorizedAccessException;
import com.nd.ms.commonlibs.utils.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping("/auth")
public class AuthenticationController extends BaseResponse{
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
  
	@RequestMapping("/current")
	public ResponseEntity<UserDetails> getCurrent() throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String authenticatedUserName = authentication.getName();
		if(authenticatedUserName.equals("anonymousUser"))
			throw new UnAuthorizedAccessException(authenticatedUserName);
		else
			return makeResponse((UserDetails)authentication.getPrincipal());
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

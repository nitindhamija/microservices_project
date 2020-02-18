package com.nd.ms.authservice.config;


import com.nd.ms.authservice.config.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private TokenProvider jwtTokenUtil;
  
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader("Authorization");
    this.logger.info(req.getRequestURL());
    String username = null;
    String authToken = null;
    if (header != null && header.startsWith("Bearer ")) {
      authToken = header.replace("Bearer ", "");
      try {
        username = this.jwtTokenUtil.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException e) {
        this.logger.error("an error occured during getting username from token", e);
      } catch (ExpiredJwtException e) {
        this.logger.warn("the token is expired and not valid anymore", (Throwable)e);
      } catch (SignatureException e) {
        this.logger.error("Authentication Failed. Username or Password not valid.");
      } 
    } else {
      this.logger.warn("couldn't find bearer string, will ignore the header");
    } 
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (this.jwtTokenUtil.validateToken(authToken, userDetails).booleanValue()) {
        UsernamePasswordAuthenticationToken authentication = this.jwtTokenUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
        authentication.setDetails((new WebAuthenticationDetailsSource()).buildDetails(req));
        this.logger.info("authenticated user " + username + ", setting security context");
        SecurityContextHolder.getContext().setAuthentication((Authentication)authentication);
      } 
    } 
    chain.doFilter((ServletRequest)req, (ServletResponse)res);
  }
}

package com.nd.ms.authservice.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements Serializable {
  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }
  
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }
  
  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }
  
  private Claims getAllClaimsFromToken(String token) {
    return (Claims)Jwts.parser()
      .setSigningKey("nitindhamija123")
      .parseClaimsJws(token)
      .getBody();
  }
  
  private Boolean isTokenExpired(String token) {
    Date expiration = getExpirationDateFromToken(token);
    return Boolean.valueOf(expiration.before(new Date()));
  }
  
  public String generateToken(Authentication authentication) {
    String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    return Jwts.builder()
      .setSubject(authentication.getName())
      .claim("scopes", authorities)
      .signWith(SignatureAlgorithm.HS256, "nitindhamija123")
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + 18000000L))
      .compact();
  }
  
  public Boolean validateToken(String token, UserDetails userDetails) {
    String username = getUsernameFromToken(token);
    return Boolean.valueOf((username
        .equals(userDetails.getUsername()) && 
        !isTokenExpired(token).booleanValue()));
  }
  
  UsernamePasswordAuthenticationToken getAuthentication(String token, Authentication existingAuth, UserDetails userDetails) {
    JwtParser jwtParser = Jwts.parser().setSigningKey("nitindhamija123");
    Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
    Claims claims = (Claims)claimsJws.getBody();
    Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>)Arrays.<String>stream(claims.get("scopes").toString().split(",")).map(org.springframework.security.core.authority.SimpleGrantedAuthority::new).collect(Collectors.toList());
    return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
  }
}

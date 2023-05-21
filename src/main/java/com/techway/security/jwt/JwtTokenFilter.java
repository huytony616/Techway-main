package com.techway.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techway.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
    private JwtTokenUtil jwtUtil;
	
//	@Autowired
//	private UserDetailsService userDetailsService; //getting UserDetails object
 
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
 
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }
 
        String token = getAccessToken(request);
 
        if (!jwtUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
 
        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }
 
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
 
        return true;
    }
 
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }
    
    //This passes the user’s authorities to the security context, 
    //which lets Spring Security authorizes requests based on user’s authorities
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetailsFromToken(token); //include username and pw
        /**
    	 * This constructor should only be used by <code>AuthenticationManager</code> or
    	 * <code>AuthenticationProvider</code> implementations that are satisfied with
    	 * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
    	 * authentication token.
    	 * @param principal
    	 * @param credentials
    	 * @param authorities
    	 */
        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(
            		userDetails, null, userDetails.getAuthorities()
            		);
 
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
 
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
 
    private UserDetails getUserDetailsFromToken(String token) {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        Claims claims = jwtUtil.parseClaims(token);
        String subject = (String) claims.get(Claims.SUBJECT);
        String roles = (String) claims.get("roles");
         
        roles = roles.replace("[", "").replace("]", "");
        String[] roleNames = roles.split(",");
        userDetails.addRole(
                Arrays.stream(roleNames).map(roleName-> new SimpleGrantedAuthority(roleName)).collect(Collectors.toList())
        );

//        for (String aRoleName : roleNames) {
//            userDetails.addRole(new SimpleGrantedAuthority(aRoleName));
//        }
         
        String[] jwtSubject = subject.split(",");
     
        userDetails.setId(Long.parseLong(jwtSubject[0]));
        userDetails.setEmail(jwtSubject[1]);
     
        return userDetails;
    }

}

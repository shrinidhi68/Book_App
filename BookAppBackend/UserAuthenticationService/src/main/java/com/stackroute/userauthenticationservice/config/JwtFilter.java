package com.stackroute.userauthenticationservice.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stackroute.userauthenticationservice.security.JWTSecurityTokenGeneratorImpl;
import com.stackroute.userauthenticationservice.service.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JWTSecurityTokenGeneratorImpl jwt;

	@Autowired
	ApplicationContext context;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getServletPath().startsWith("/api/v1/login") ||request.getServletPath().startsWith("/api/v1/user") ) {
			filterChain.doFilter(request, response);
		}
		String token="";
		String userName="";
		String auth=request.getHeader("Authorization");
		if(auth!=null &&  auth.startsWith("Bearer")) {
			token=auth.substring(7);
			userName=jwt.extractUsername(token);
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails user=context.getBean(UserServiceImpl.class).loadUserByUsername(userName);
			if(jwt.validateToken(user, token)) {
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
		}
		
		
		filterChain.doFilter(request, response);
		 

	}

}

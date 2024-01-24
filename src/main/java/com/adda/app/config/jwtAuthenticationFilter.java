package com.adda.app.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.adda.app.ServiceImpl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter

{
	@Autowired
    private UserDetailsServiceImpl udsi;
	@Autowired
	private jwtUtil jwtutils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
	final String requestTokenHeader = request.getHeader("Authorization");
	System.out.println(requestTokenHeader);
	String username=null;
	String jwtToken = null;
	
	if(requestTokenHeader!=null&&requestTokenHeader.startsWith("Bearer ")) 
	{
	jwtToken=requestTokenHeader.substring(7);
	try {
	username=this.jwtutils.extractUsername(jwtToken);
	}
	catch(ExpiredJwtException e) 
	{
		e.printStackTrace();
		System.out.println("jwt token has expired");
	}
	catch(Exception e) 
	{
		e.printStackTrace();
		System.out.println("error");
	}
	}
	
	else 
	{
		System.out.println("Invalid token ,not start with bearer string");
	}
	
	//validated 
	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) 
	{
		final UserDetails userdetails = this.udsi.loadUserByUsername(username);
		if(this.jwtutils.validateToken(jwtToken, userdetails)) 
		{
			UsernamePasswordAuthenticationToken usernamepasswordauthenticationToken = new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
			usernamepasswordauthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamepasswordauthenticationToken);
		}
	}
	else 
	{
		System.out.println("TOken is not valid");
	}
	filterChain.doFilter(request, response);
	}

}

package com.myproject.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(urlPatterns="/",filterName="userLoginFilter")
public class UserLoginFilter implements Filter {
	
	private static String PRE_PROJECT = "myproject_";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.err.println("init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest){
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			Object sessionuserid = httpServletRequest.getSession().getAttribute(PRE_PROJECT+"userid");
			if(sessionuserid != null){
				chain.doFilter(request, response);
			}else{
				String requestURI = httpServletRequest.getRequestURI();
				if(requestURI.contains("login") 
						|| requestURI.contains("checklogin") 
						|| requestURI.contains("resource")){
					chain.doFilter(request, response);
				}else{
					request.getRequestDispatcher("/login").forward(request, response);
				}
			}
		}
	}

	@Override
	public void destroy() {
		System.err.println("destroy");
	}

}

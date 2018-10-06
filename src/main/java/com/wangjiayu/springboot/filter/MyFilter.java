package com.wangjiayu.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/filter")
public class MyFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("----destroy--------filter-----");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("---doFilter---filter-");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("---init-----filter-----");
	}

}

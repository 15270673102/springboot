package com.wangjiayu.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.err.println("销毁-------MyListener--------------");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.err.println("init------MyListener-------------");
	}

}

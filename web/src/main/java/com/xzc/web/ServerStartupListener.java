package com.xzc.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartupListener implements ServletContextListener {

    public ServerStartupListener() {
    	
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	//将web应用名称保持到application中
    	ServletContext  application = sce.getServletContext();
    	String path = application.getContextPath();
    	application.setAttribute("APP_PATH",path);
    }
	
}

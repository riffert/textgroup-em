package com.riffert.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.riffert.textgroup.service.DatabaseRequestService;

public class Init implements ServletContextListener
{
		
		public static ClassPathXmlApplicationContext context;
		
		public static DatabaseRequestService databaseRequestService;
		
		
	
		public void contextInitialized(ServletContextEvent sce)
		{
				context = new ClassPathXmlApplicationContext("applicationContext.xml");
				
				databaseRequestService = new DatabaseRequestService();
				
				System.out.println("Init::contextInitialized()");				
		}

		public void contextDestroyed(ServletContextEvent sce) {	}
}



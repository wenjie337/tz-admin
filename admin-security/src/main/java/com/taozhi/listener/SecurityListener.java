package com.taozhi.listener;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.filter.DelegatingFilterProxy;

@WebListener
public class SecurityListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
			
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
			
		FilterRegistration.Dynamic mdcFr = sc.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		mdcFr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, new String[] { "/*" });
		mdcFr.setAsyncSupported(true);
	}

}

	
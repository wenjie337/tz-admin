package com.taozhi.filter;

import javax.annotation.PostConstruct;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

@Component
public class MyExceptionTranslationFilter extends ExceptionTranslationFilter {
	
	public MyExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
		super(new MyloginAuthenticationEntryPoint());
			
	}

	@PostConstruct  
    public void init(){  
    }  



}

	
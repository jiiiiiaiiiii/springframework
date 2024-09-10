package com.mycompany.springframework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch17authenticationFailureHandler 
	extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) 
					throws IOException, ServletException {
		
		log.info("실행");
		
		// 로그인 실패시, 이동할 url
		setDefaultFailureUrl("/ch17/loginForm");
		
		super.onAuthenticationFailure(request, response, exception);
	}
}

package com.mycompany.springframework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request,
								ServletResponse reponse, 
								FilterChain chain)
			throws IOException, ServletException {

		// 전처리
		log.info("전처리 실행 ~~~(^v^)~~~");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) reponse;
		
		String requstURI = httpServletRequest.getRequestURI();
		String requstURL = httpServletRequest.getRequestURL().toString();
		
		if(requstURI.contains("/mypage")) {
			HttpSession session = httpServletRequest.getSession();
			if(session.getAttribute("login") == null) {
				// 로그인을 안했으니 로그인 폼으로 리다이렉팅
				// ★ Java에서의 절대경로 설정 ★
				String contextPath = request.getServletContext().getContextPath();
				httpServletResponse.sendRedirect(contextPath + "/ch02/loginForm");
				return;
			}
		}
		
		log.info("requstURI: " + requstURI);
		log.info("requstURL: " + requstURL);
		
		//---------------------------------------
		chain.doFilter(request, reponse);
		//---------------------------------------

		// 후처리
		log.info("후처리 실행 ~~~(^v^)~~~");
	}

}

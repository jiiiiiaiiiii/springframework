package com.mycompany.springframework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Ch17authenticationSuccessHandler  // 로그인 성공시 실행할 객체
// 로그인 성공시 사용자가 "요청한" 페이지로 이동
// 장바구니에서 로그인하면 -> 로그인 후에도 장바구니 페이지로 이동
	extends SavedRequestAwareAuthenticationSuccessHandler { 

// 로그인 성공시 "지정한" 페이지로 이동
// 어디에서 로그인하든 -> 로그인 후에 무조건 지정된 경로로만 이동(홈으로만 이동)
	//extends SimpleUrlAuthenticationSuccessHandler {
	
	//onAuthenticationSuccess 중 첫번째
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) // 인증정보를 갖고있는 객체(id, 권한 등..)
					throws ServletException, IOException {

		log.info("실행");
		
		// extends SimpleUrlAuthenticationSuccessHandler를 사용할 경우
		//setDefaultTargetUrl("/");	// 로그인 성공시, 이동할 페이지
		
		super.onAuthenticationSuccess(request, response, authentication);	// 지우면 ❌ => 부모객체로 사용자가 "요청"한 페이지에 대한 정보
	}
}

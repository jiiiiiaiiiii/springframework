package com.mycompany.springframework.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14AspectLoginCheck {
	@Around("@annotation(com.mycompany.springframework.aspect.LoginCheckAOP)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		// 세션객체 얻기
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		
		Object login = session.getAttribute("login");
		if(login == null) {
			// 로그인이 안된 경우
			return "redirect:/ch13/loginForm";
		} else {
			// 로그인 된 경우
			Object result = joinPoint.proceed();
			return result;
			// 별도 후처리 코드 없음
		}
	}
}

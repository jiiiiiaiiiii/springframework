package com.mycompany.springframework.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect6Around {
	@Around("execution(public * com.mycompany.springframework.controller.Ch14Controller.around*(..))")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {	// 필수 arg
		// [전처리]메소드 호출 전에 실행하는 공통 코드
		log.info("메소드 호출 전에 실행하는 공통 코드");
		
		// 컨트롤러의 메소드 호출 지점 => 이 코드를 기점으로 전처리 | 후처리 구분
		Object result = joinPoint.proceed();	// Throwable 예외 발생&처리
		
		// [후처리]메소드 호출 후에 실행하는 공통 코드
		log.info("메소드 호출 후에 실행하는 공통 코드");
		
		return result;
	}
}

/* === 예외 상속 관계 === */
// (상위) Throwable > Exception > [Runtime | ....xxxx]Exception (하위)

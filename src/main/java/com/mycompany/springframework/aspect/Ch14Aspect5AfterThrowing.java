package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component	// 관리객체로 만들기
@Aspect
@Slf4j
public class Ch14Aspect5AfterThrowing {
	@AfterThrowing(
		pointcut = "execution(public * com.mycompany.springframework.controller.Ch14Controller.afterThrowing*(..))",
		throwing = "e"	// Throwable 객체의 변수명(arg)
	) 
	public void method(Throwable e) {
		// 예외가 발생된 경우 공통 코드
		log.info("실행");
		log.info("예외 메시지: " + e.getMessage());
	}
}

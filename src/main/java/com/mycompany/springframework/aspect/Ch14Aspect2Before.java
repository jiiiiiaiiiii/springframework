package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component	// 관리객체로 만들기
@Aspect
@Slf4j
@Order(1)
public class Ch14Aspect2Before {
	@Before("execution(public * com.mycompany.springframework.controller.Ch14Controller.before*(..))") 
	public void method() {
		// 메소드 호출 '전'에 실행하는 공통 코드
		log.info("실행");
	}
}

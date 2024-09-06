package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component	// 관리객체로 만들기
@Aspect
@Slf4j
public class Ch14Aspect3After {
	@After("execution(public * com.mycompany.springframework.controller.Ch14Controller.after*(..))") 
	public void method() {
		// 메소드 호출 '후'에 실행하는 공통 코드
		log.info("실행");
	}
}

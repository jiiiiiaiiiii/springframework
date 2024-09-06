package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component	// 관리객체로 만들기
@Aspect
@Slf4j
@Order(2)
public class Ch14Aspect1Before {
	@Before("execution(public * com.mycompany.springframework.controller.Ch14Controller.before*(..))") 
	/* pointCut 지정
	-- excution: ~를 실행할 때
	-- public : 접근제어자가 public이고
	-- *: return 타입 상관없음
	-- com ~ Ch14Controller.before*: 안에 있는 before로 시작하는 메서드
	-- (..): 메서드의 매개변수는 상관없음 */
	public void method() {
		// 메소드 호출 '전'에 실행하는 공통 코드
		log.info("실행");
	}
}

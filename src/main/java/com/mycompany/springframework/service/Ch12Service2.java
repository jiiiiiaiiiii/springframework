package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service2 {
	// 정적 메소드
	// Ch12Service2.getInstance() => return 값으로 객체가 생성
	public static Ch12Service2 getInstance() {
		log.info("실행");
		return new Ch12Service2();
	}
	
	// 인스턴스 메소드
	// non-static: Ch12Service2객체를 생성한 후에 getCh12Service3를 호출할 수 있음
	public Ch12Service3 getCh12Service3() {
		return new Ch12Service3();
	}
}

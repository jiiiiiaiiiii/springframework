package com.mycompany.springframework.exception;

// extends: Exception(컨트롤러에서 try-catch 예외처리) || RuntimeException(try-catch없이 처리)
public class Ch10CustomException extends Exception {
	// 기본생성자
	public Ch10CustomException() {}
	
	// 예외메서지 생성자
	public Ch10CustomException(String message) {
		super(message);	// 부모에게 넘김
	}
}

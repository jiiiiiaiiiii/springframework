package com.mycompany.springframework.exception;

// Exception의 streotype --- ★
public class Ch15AccountNotExistException extends RuntimeException {
	public Ch15AccountNotExistException() {}
	
	public Ch15AccountNotExistException(String message) {
		super(message); // 부모쪽으로 전달
	}
}

package com.mycompany.springframework.exception;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Component
@ControllerAdvice
@Slf4j
public class Ch10ExceptionHandler {
	@ExceptionHandler(NullPointerException.class)
	public String HandleNullPointerException(Model model) {
		model.addAttribute("chNum","ch10");
		return "ch10/nullPointerException";
	}
	
	@ExceptionHandler(Ch10CustomException.class)
	public String HandleCh10CustomException(Model model) {
		model.addAttribute("chNum","ch10");
		return "ch10/customException";
	}
	
	@ExceptionHandler(Exception.class)
	public String HandleException(Model model) {
		model.addAttribute("chNum","ch10");
		return "ch10/exception";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String NoHandlerFoundException(Model model) {
		model.addAttribute("chNum","ch10");
		return "ch10/404";
	}
}
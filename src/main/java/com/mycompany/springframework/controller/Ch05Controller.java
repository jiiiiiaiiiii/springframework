package com.mycompany.springframework.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch05")
public class Ch05Controller {
	@GetMapping("/header")
	// 헤더값을 얻는 방법1: client 헤더의 User-Agent로 넘어오는 값을 userAgent 매개변수에 바로 담음
	public String header(@RequestHeader("User-Agent") String userAgent,
			HttpServletRequest request,
			Model model) {
		model.addAttribute("chNum", "ch05");
		log.info("userAgent: " + userAgent);
		
		// 헤더값을 얻는 방법2
		userAgent = request.getHeader("User-Agent");
		
		String browser = null;
		
		if(userAgent.contains("Edg")) {
			browser = "Edg";
		} else if(userAgent.contains("Chrome")) {
			browser = "Chrome";
		}
		
		model.addAttribute("browser", browser);
		
		String clientIp = request.getRemoteAddr();	// 클라이언트의 ip = 서버(웹앱) 입장에서는 원격에 있는 주소
		log.info("clientIp: " + clientIp);
		model.addAttribute("clientIp", clientIp);
		
		return "ch05/header";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(HttpServletResponse response, Model model) {
		model.addAttribute("chNum", "ch05");
		// 쿠키 생성
		Cookie cookie1 = new Cookie("mid", "user1");
		Cookie cookie2 = new Cookie("memail", "user1@mycompany.com");
		
		/* ------- Cookie의 메서드 ------- */
		//cookie1.setMaxAge(60);			   // 클라이언트 '파일 시스템'에 저장하는 기간을 설정(없으면 '브라우저 메모리'에 저장)
		//cookie1.setDomain("abc.com");	   // "*.abc.com" 이라면 쿠키 전송
		//cookie1.setSecure(true);            // https에서만 쿠키를 전송
		//cookie1.setHttpOnly(true); 		   // 브라우저의 자바스크립트로 쿠키에 접근을 허용하지 않음
		
		/* ------- Cookie의 저장 ------- */
		/* 1. 브라우저 메모리: 기본(브라우저 살아있는 동안 서버로 전달)
		   2. 파일 시스템: setMaxAge(초)를 설정하는 경우 => 지정 시간 동안 '파일 시스템'에 저장
		                     (브라우저 종료되어도 남아있다가 지정 시간 이후 쿠키 날아감) */
		

		// 쿠키를 응답 헤더에 추가해서 브라우저로 보냄
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		return "redirect:/";
	}
	
	@GetMapping("/readCookie")
	public String readCookie(
			@CookieValue("mid") String mid,	  // mid이름의 쿠키 값을 매개변수 mid에 넣어줌
			@CookieValue("memail") String memail,
			Model model){
		
		model.addAttribute("chNum", "ch05");
		
		log.info("mid: " + mid);
		log.info("memail: " + memail);
		
		model.addAttribute("mid", mid);
		model.addAttribute("memail", memail);
		return "ch05/readCookie";
	}
}

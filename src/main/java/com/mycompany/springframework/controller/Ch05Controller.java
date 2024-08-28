package com.mycompany.springframework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String createCookie() {
		return "redirect:/";
	}
	
	@GetMapping("/readCookie")
	public String readCookie() {
		return "ch05/readCookie";
	}
}

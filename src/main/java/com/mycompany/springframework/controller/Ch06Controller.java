package com.mycompany.springframework.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Cart;
import com.mycompany.springframework.dto.Ch06Item;
import com.mycompany.springframework.dto.Ch06Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	@GetMapping("/forward")
	public String forward(Model model, HttpServletRequest request) {
		Ch06Member member = new Ch06Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
		
		//방법1
		model.addAttribute("member1", member);
		
		//방법2
		request.setAttribute("member2", member);
		
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect(HttpSession session) {
		Ch06Member member = new Ch06Member();
		member.setMid("user1");
		member.setMname("사용자1");
		member.setMemail("user1@mycompany.com");
		
		// 세션에 저장
		session.setAttribute("member", member);
		
		return "redirect:/ch06/sessionData";
	}
	
	@GetMapping("/sessionData")
	public String sessionData(HttpSession session) {
		// 세션에서 찾아오기(읽기)
		Ch06Member member = (Ch06Member) session.getAttribute("member");
		
		log.info("mid: " + member.getMid());
		log.info("mname: " + member.getMname());
		log.info("memail: " + member.getMemail());
		return "ch06/sessionData";
	}
	
	@GetMapping("/cartview")
	public String cartview(HttpSession session) {
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		
		if(cart == null) {
			session.setAttribute("cart", new Ch06Cart());
			
			cart = (Ch06Cart) session.getAttribute("cart");
			
			Ch06Item item = new Ch06Item();
			item.setPno("p1");
			item.setPname("상품1");
			cart.addItem(item);
			
			item = new Ch06Item();
			item.setPno("p2");
			item.setPname("상품2");
			cart.addItem(item);
		}
		
		return "ch06/cartview";
	}
}

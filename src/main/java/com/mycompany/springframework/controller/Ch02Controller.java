package com.mycompany.springframework.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.Ch02LoginResult;
import com.mycompany.springframework.interceptor.LoginCheck;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ch02")
@Slf4j
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	@RequestMapping("/getMethod")
	public String getMethod(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/getMethod";
	}
	
	// RequestMapping의 default는 GET/POST 모두 허용
	// 어노테이션 작성 시, arg가 2개 일때 => arg별 '값'을 각각 정의해주어야 함
	// @RequestMapping(value="/getAtag", method=RequestMethod.GET)	// RequestMethod.GET:  GET방식만 허용
	@GetMapping("/getAtag")
	public String getAtag(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/getMethod";
	}
	@GetMapping("/getFormTag")
	public String getFormTag(String bno, String bkind) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		//return "ch02/getMethod";
		return "redirect:/";
	}
	
	@GetMapping("/getLocationHref")
	public String getLocationHref(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/getMethod";
	}
	
	@GetMapping("/getAjax1")
	public String getAjax1(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/ajaxFragmentHtml";
	}
	
	@GetMapping("/getAjax2")
	public String getAjax2(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/ajaxJSON";
	}
	
/*------------------------------------------------------------------------*/
	
// @PostMapping("/postMethod") // a태그 링크는 GET방식이므로 ERROR
	@GetMapping("/postMethod")
	public String postMethod(Model model) {
		logger.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/postMethod";
	}
	
	@PostMapping("/postFormTag")
	public String postFormTag(String bno, String bkind, Model model) {
		logger.info("실행");
		model.addAttribute("chNum", "ch02");
//		return "ch02/postMethod";
		return "redirect:/"; // 기존 요청 url (/postFormTag) 삭제되고 ->home(/)으로 돌아감 
	}
	
	@PostMapping("/postAjax1")
	public String postAjax1(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/ajaxFragmentHtml";
	}
	
	@PostMapping("/postAjax2")
	public String postAjax2(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/ajaxJSON";
	}
	
	/*------------------------------------------------------------------------*/
	
	@GetMapping("/returnModelAndView")
	// ModelAndView: data(Model)와 view에 대한 정보를 갖고 있는 객체
	public ModelAndView returnModelAndView() {
		log.info("실행");
		ModelAndView mav = new ModelAndView();
		// Controller에서 View로 넘기는 data(Model) => JSP에서 사용할 객체
		mav.addObject("bno", 15);
		mav.addObject("bkind", "notice");
		mav.addObject("mid", "user1");
		mav.addObject("memail", "user1@mycompany.com");
		mav.addObject("chNum", "ch02");
		mav.setViewName("ch02/returnModelAndView");
		return mav;
	}
	
	@GetMapping("/returnVoid")
	public void returnVoid(HttpServletResponse response) throws IOException {
		log.info("실행");
		
//		{ }: 객체 => JSONObject jsonObject = new JSONObject( );
//		[ ] : 배열 => JSONArray jsonArray = new JSONArray( );
		
//		JSONObject: 외부 라이브러리(pom.xml -> dependency에서 추가)
//		{ " result" : "success", "mid" : "user1" }
//		json을 직접 만들어서 응답에 실어 보냄
		JSONObject jsonObject = new JSONObject( );
		jsonObject.put("result", "success");
		jsonObject.put("mid", "user1");
		String json = jsonObject.toString();
		
//		응답 생성:  헤더(contentType) + 본문(data)를 넣는 것
//		응답 HTTP의 BODY 부분에 JSON을 포함
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();	// 버퍼 비우기
		pw.close();
	}
	
//	객체(Object)를 return해서 (내부에서) 자동으로 json을 만들어서 응답으로...
//	생성된 json(produces에서 지정)이 응답 body로 들어간다.(@ResponseBody)
//	별도 dto class(Ch02LoginResult) 선언 필요
	@GetMapping(value="/returnObject", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Ch02LoginResult returnObject() {
		log.info("실행");
		
		Ch02LoginResult obj = new Ch02LoginResult();
		obj.setResult("success");
		obj.setMid("user1");
		
		return obj;
	}
	
	/*------------------------------------------------------------------------*/
	@LoginCheck  // 로그인을 하고 요청해야 하는 페이지, 경로(custom-annotation)	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/mypage";
	}
	
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		log.info("실행");
		log.info("mid: " + mid);
		log.info("mpassword: " + mpassword);
		// 세션에 로그인 정보 저장
		session.setAttribute("login", mid);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("실행");
		// 세션에서 로그인 정보 삭제
		session.removeAttribute("login");
		return "redirect:/ch02/loginForm";
	}
	
	
	
	
	
	
	
	
}

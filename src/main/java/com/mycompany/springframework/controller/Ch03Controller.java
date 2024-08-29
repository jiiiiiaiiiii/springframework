package com.mycompany.springframework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.springframework.dto.Ch03Dto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch03")
public class Ch03Controller {
	@GetMapping("/receiveParamData")
	public String receiveParamData(
				String param1, 
				String param2, 
				String param3, 
				String param4, 
				String param5, 
				Model model) {
		
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		
		// JSP로 데이터 전달 --> JSP에서 ${param1}의 형태로 사용
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		model.addAttribute("chNum", "ch03");
		
		return "ch03/receiveParamData";
	}
	
	// Form 자체를 요청
	@GetMapping("/postMethodForm")
	public String postMethodForm(Model model) {
		model.addAttribute("chNum", "ch03");
		return "ch03/postMethodForm";
	}

	// Form에 입력된 값 처리
	@PostMapping("/receivePostMethodForm")
	public String receivePostMethodForm(
				String param1, 
				int param2, 
				double param3, 
				boolean param4, 
				@DateTimeFormat(pattern="yyyy-MM-dd") Date param5, 
				Model model) {
		
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		
		// JSP로 데이터 전달 --> JSP에서 ${param1}의 형태로 사용
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		model.addAttribute("chNum", "ch03");
		
		return "ch03/receiveParamData";
	}
	
	@GetMapping("/defaultValue")
	public String defaultValue(
			// param이 오지 않을 경우 -> String은 null로 자동 적용되나, 다른 데이터 타입의 경우 기본값이 없으면 err 발생
				String param1, 
				// @RequestParam(required=true) int param2, // 필수값이 넘어오지 않을 경우 -> error
				// (잦은 error 발생의 요인으로 잘 쓰이지 않음 -> defaultValue을 사용하는 것을 지향)
				@RequestParam(defaultValue="0", required=true) int param2, 
				@RequestParam(defaultValue="0.0") double param3, 
				@RequestParam(defaultValue="false") boolean param4, 
				String param5, 
				Model model) {
		
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		
		// JSP로 데이터 전달
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		model.addAttribute("chNum", "ch03");
		
		return "ch03/receiveParamData";
	}
	
	// 요청 파라미터명와 매개변수명이 다를 경우
	@GetMapping("/otherArgName")
	public String otherArgName(
				@RequestParam("param1") String arg1, 	// param1로 넘어온 data를 arg1이 받음
				@RequestParam(value="param2", defaultValue="0") int arg2, 
				@RequestParam(value="param3", defaultValue="0.0") double arg3, 
				@RequestParam(value="param4", defaultValue="false") boolean arg4, 
				String arg5, 
				Model model) {
		
		log.info("param1: " + arg1);
		log.info("param2: " + arg2);
		log.info("param3: " + arg3);
		log.info("param4: " + arg4);
		log.info("param5: " + arg5);
		
		// JSP로 데이터 전달
		model.addAttribute("param1", arg1);
		model.addAttribute("param2", arg2);
		model.addAttribute("param3", arg3);
		model.addAttribute("param4", arg4);
		model.addAttribute("param5", arg5);
		model.addAttribute("chNum", "ch03");
		
		return "ch03/receiveParamData";
	}
	
	@GetMapping("/commandObject")
	public String commandObject(Ch03Dto dto, Model model) {
		// params가 dto에 필드로 저장됨 -> getter 메서드로 읽음
		log.info("param1: " + dto.getParam1());
		log.info("param2: " + dto.getParam2());
		log.info("param3: " + dto.getParam3());
		log.info("param4: " + dto.isParam4());		// boolean의 getter는 'is'로 시작
		log.info("param5: " + dto.getParam5());
		
		// JSP로 데이터 전달 (CommandObject를 사용할 경우 -> 자동으로 전달)
		// class의 첫 글자를 소문자로 바꿔 키이름으로 전달
/*		model.addAttribute("ch03Dto", dto)*/
		model.addAttribute("chNum", "ch03");
		return "ch03/receiveCommandObject";
	}
	
	@GetMapping("/ajaxParam")
	public String ajaxParam(Model model) {
		model.addAttribute("chNum", "ch03");
		return "ch03/ajaxParam";
	}
	
	@PostMapping("/requestAjax")
	public void requestAjax(Ch03Dto dto, HttpServletResponse response) throws IOException {
		log.info(dto.toString());
		
		// {"result": "ok"}
		JSONObject jsonObject = new JSONObject( );
		jsonObject.put("result", "ok");
		String json = jsonObject.toString();
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
	}
}

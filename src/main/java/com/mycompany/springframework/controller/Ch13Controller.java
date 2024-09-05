package com.mycompany.springframework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.dto.Ch13WriteBoardForm;
import com.mycompany.springframework.service.Ch13BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch13")
public class Ch13Controller {
	@Autowired
	private Ch13BoardService boardService;
	
	@GetMapping("/writeBoardForm")
	public String writeBoardForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/writeBoardForm";
	}
	
	// 방법1. 파일시스템에 저장
	/*@PostMapping("/writeBoard")
	public String writeBoard(Ch13WriteBoardForm form) throws Exception {
		Ch13Board board = new Ch13Board();
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		board.setMid("user");
		MultipartFile battach = form.getBattach();
		if(!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			String fileName = new Date().getTime() + "-" + battach.getOriginalFilename();
			board.setBattachsname(fileName);
			String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
			File file = new File(saveDir, fileName);
			battach.transferTo(file);
		}
		
		boardService.writeBoard(board);	// 서비스로 dto 넘김
		
		return "redirect:/";
	}*/
	
	
	// 방법2. DB에 저장
	@PostMapping("/writeBoard")
	public String writeBoard(Ch13WriteBoardForm form) throws Exception {
		Ch13Board board = new Ch13Board();
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		board.setMid("user");
		MultipartFile battach = form.getBattach();
		if(!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			board.setBattachdata(battach.getBytes());		// getBytes(): 파일 자체의 데이터 얻기
		}
		boardService.writeBoard(board);	
		return "redirect:/";
	}	
	
	@GetMapping("/boardList")
	public String boardList(Model model,
			@RequestParam(defaultValue="1") int pageNo) {
		model.addAttribute("chNum", "ch13");
		
		int totalRows = boardService.getTotalRows();
		Ch13Pager pager = new Ch13Pager(10, 5, totalRows, pageNo);
		model.addAttribute("pager", pager);
		
		List<Ch13Board> list = boardService.getBoardList(pager);
		model.addAttribute("list", list);
		return "ch13/boardList";
	}
}

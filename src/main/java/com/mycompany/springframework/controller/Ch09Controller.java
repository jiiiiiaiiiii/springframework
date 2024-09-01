package com.mycompany.springframework.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springframework.dto.Ch09FileUploadForm;
import com.mycompany.springframework.dto.Ch09MultiFileUploadForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch09")
public class Ch09Controller {	
	@GetMapping("/fileUploadForm")
	public String fileUploadForm(Model model) {
		model.addAttribute("chNum", "ch09");
		return "ch09/fileUploadForm";
	}
	
	@PostMapping("singleFileUpload")
	public String singleFileUpload(Ch09FileUploadForm form) throws Exception {
		log.info("title: " + form.getTitle());
		log.info("desc: " + form.getDesc());
		MultipartFile attach = form.getAttach();
		// 파일이 포함되어 있는지 여부 조사
		if(!attach.isEmpty()) {
			// 파일의 정보 읽기
			log.info("originalFilename: " + attach.getOriginalFilename());	// client가 처음 올릴 때의 파일이름
			log.info("ContentType: " + attach.getContentType());
			log.info("size: " + attach.getSize());
			// 파일을 파일 스토리지에 저장
			String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
			String saveFileName = new Date().getTime() + "-" + attach.getOriginalFilename();
			File file = new File(saveDir, saveFileName);
			attach.transferTo(file);
		}
		return "redirect:/ch09/downloadFileList";
	}
	
	@PostMapping("multiFileUpload")
	public String multiFileUpload(Ch09MultiFileUploadForm form) throws Exception {
		log.info("title: " + form.getTitle());
		log.info("desc: " + form.getDesc());
		for(MultipartFile mf : form.getAttach()) {
			// 파일이 포함되어 있는지 여부 조사
			if(!mf.isEmpty()) {
				// 파일의 정보 읽기
				log.info("originalFilename: " + mf.getOriginalFilename());	// client가 처음 올릴 때의 파일이름
				log.info("ContentType: " + mf.getContentType());
				log.info("size: " + mf.getSize());
				log.info("---------------------------------------------------");
				// 파일을 파일 스토리지에 저장
				String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
				String saveFileName = new Date().getTime() + "-" + mf.getOriginalFilename();
				File file = new File(saveDir, saveFileName);
				mf.transferTo(file);
			}
		}
		return "redirect:/ch09/downloadFileList";
	}
	
	@GetMapping("/downloadFileList")
	public String downloadFileList(Model model) {
		model.addAttribute("chNum", "ch09");
		
		String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
		File file = new File(saveDir);
		String [] fileNames = file.list();
		model.addAttribute("fileNames", fileNames);
		
		return "ch09/downloadFileList";
	}
	
	@GetMapping("/downloadFile")
	// get방식의 헤더행에는 한글을 넣을 수 x
	public void downloadFile(
			String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 응답 헤더에 들어가는 Content-Type 내용 설정
		String contentType = request.getServletContext().getMimeType(fileName);
		response.setContentType(contentType);
		
		// 파일로 저장하기 위한 설정(미설정 시, 브라우저에서 바로 열림)
		String encodingfileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");	// 파일이름이 한글일 경우 대비하여 미리 인코딩
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingfileName +"\""); 	// 파일 강제 다운로드
		
		// 응답 본문에 파일 데이터를 출력
		String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
		Path path = Paths.get(saveDir + "/" + fileName);
		OutputStream out = response.getOutputStream(); 	// 응답 본문
		// 파일 데이터를 보냄 ->  getOutputStream
		// text를 보냄 -> getWriter
		Files.copy(path, out); // (읽을 파일의 위치, 출력스트림)
		out.flush();
		out.close();
	}
	
	@PostMapping("/uploadFileFromAjax")
	public void uploadFileFromAjax(
			Ch09FileUploadForm form, HttpServletResponse response) throws Exception {
		log.info("title: " + form.getTitle());
		log.info("desc: " + form.getDesc());
		MultipartFile attach = form.getAttach();
		// 파일이 포함되어 있는지 여부 조사
		if(!attach.isEmpty()) {
			// 파일의 정보 읽기
			log.info("originalFilename: " + attach.getOriginalFilename());	// client가 처음 올릴 때의 파일이름
			log.info("ContentType: " + attach.getContentType());
			log.info("size: " + attach.getSize());
			// 파일을 파일 스토리지에 저장
			String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
			String saveFileName = new Date().getTime() + "-" + attach.getOriginalFilename();
			File file = new File(saveDir, saveFileName);
			attach.transferTo(file);
		}
		
		// 응답 생성
		// {"result": "ok"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "ok");
		String json = jsonObject.toString();
	
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println(json);
		pw.flush();
		pw.close();
		
	}
}



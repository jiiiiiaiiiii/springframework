package com.mycompany.springframework.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Ch13Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String mid;
	private int bhitcount;
	private String battachoname;	// 실제(original) 파일이름
	private String battachsname;	// 파일 storage에 저장된 이름(DB에 파일을 저장할 경우 필요X)
	private String battachtype;		// 파일의 종류(MIME 타입)
	private byte[] battachdata;	// 파일(바이트배열)	
}

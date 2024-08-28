package com.mycompany.springframework.dto;

import lombok.Data;

@Data // 자동으로 getter, setter, toString 등의 메서드 포함됨
public class Ch02LoginResult {
	private String result;
	private String mid;
}

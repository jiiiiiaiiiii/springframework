package com.mycompany.springframework.service;

import com.mycompany.springframework.dao.Ch12Dao1;
import com.mycompany.springframework.dao.Ch12Dao2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor	// 기본생성자 만듦
//@AllArgsConstructor	// 필드를 모두 매개변수로 갖는 생성자를 만듦 -> 생성자 주입시 필요
public class Ch12Service6 {
	private Ch12Dao1 dao1;
	private Ch12Dao2 dao2;
	
	public Ch12Service6(Ch12Dao1 dao1, Ch12Dao2 dao2) {
		this.dao1 = dao1;
		this.dao2 = dao2;
		log.info("Ch12Dao1 dao1, Ch12Dao2 dao2: 실행");
	}
}

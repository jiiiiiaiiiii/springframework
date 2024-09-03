package com.mycompany.springframework.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springframework.dto.Ch13Board;

@Mapper
public interface Ch13BoardDao {
	// 추상메서드 정의
	// return 되는 int값 = 삽입된(반영된) 행의 갯수
	public int insert(Ch13Board board);
}



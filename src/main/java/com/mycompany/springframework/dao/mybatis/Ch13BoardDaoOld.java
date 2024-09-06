package com.mycompany.springframework.dao.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.springframework.dto.Ch13Board;

@Repository
// class로 작성 -> @Repository 관리 객체로 만들고 -> @Autowired sst injection 받고
// 메서드를 정의하면서 mapper.xml와 직접 연결시킴
public class Ch13BoardDaoOld {
	@Autowired
	private SqlSessionTemplate sst;
	
	public int insert(Ch13Board board) {
		// mapper.xml과 연결시켜 줘야 함
		// sst.메서드("namespace + id", parameterType 객체)
		int rows = sst.insert("com.mycompany.springframework.dao.mybatis.Ch13BoardDao.insert", board);
		return rows;
	}
}

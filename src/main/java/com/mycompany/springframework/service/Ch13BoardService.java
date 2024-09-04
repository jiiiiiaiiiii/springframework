package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13BoardDao;
import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13BoardService {
	@Autowired
	private Ch13BoardDao boardDao;	// mybatis가 생성한 Ch13BoardDao 인터페이스의 구현 객체(관리객체)
	
	//Ch13Board 하나의 객체 = 하나의 행 => List
	public List<Ch13Board> getBoardList(Ch13Pager pager) {
		List<Ch13Board>list = boardDao.selectList(pager);
		return list;
	}
	
	public Ch13Board getBoard(int bno) {
		return null;
	}
	
	// dto 형태로 매개변수를 받는 것이 좋음
	public void writeBoard(Ch13Board board) {
		log.info("실행");
		log.info("insert 전 bno: " + board.getBno());
		boardDao.insert(board);
		log.info("insert 후 bno: " + board.getBno());
		//bno를 이용해서 추가적인 비지니스 로직을 작성해야 할 경우
		int bno = board.getBno();
	}
	
	public void updateBoard(Ch13Board board) { 
		
	}
	
	public void deleteBoard(int bno) {
		
	}

	public int getTotalRows() {
		int totalRows = boardDao.countRows();
		return totalRows;
	}
}

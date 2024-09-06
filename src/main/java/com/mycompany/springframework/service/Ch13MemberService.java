package com.mycompany.springframework.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13MemberService {
	// enum: 제한된 값의 목록(제한된 return 값을 낼 때)
	public enum JoinResult {
		SUCCESS,
		FAIL_DUPLICATED_MID // 중복ID
	}
	
	public enum LoginResult {
		SUCCESS,
		FAIL_MID,
		FAIL_MPASSWORD,
		FAIL_ENABLED		// 비활성화(휴면) 계정, 탈퇴계정 등...
	}
	
	@Resource
	private Ch13MemberDao memberDao;
	
	// controller에서 form 유효성 검사 후 -> service에서 비지니스 로직 처리
	public JoinResult join(Ch13Member member) {
		boolean exist = isMid(member.getMid());
		if(exist) {
			return JoinResult.FAIL_DUPLICATED_MID;
		}
		memberDao.insert(member);
		return JoinResult.SUCCESS;
	}
	
	public boolean isMid(String mid) { // id가 db에 있는지 확인
		Ch13Member member = memberDao.selectByMid(mid);
		
		if(member == null) {	//mid가 없는 경우
			return false;
		} else {
			return true;
		}
	}
	
	public LoginResult login(Ch13Member member) {
		Ch13Member dbMember = memberDao.selectByMid(member.getMid());
		
		if(dbMember == null) {
			return LoginResult.FAIL_MID;
		} 
		if(!dbMember.isMenabled()) {
			return LoginResult.FAIL_ENABLED;
		}
		if(!dbMember.getMpassword().equals(member.getMpassword())) {
			return LoginResult.FAIL_MPASSWORD;
		}	
		return LoginResult.SUCCESS;
	}
}

package com.mycompany.springframework.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mycompany.springframework.dto.Ch13Member;

/* User 
 * UserDetails 인터페이스를 구현한 클래스
 * 기본생성자가 없음 */
public class Ch17UserDetails extends User {
	private Ch13Member member;		
	
	public Ch17UserDetails(
			Ch13Member member,
			List<GrantedAuthority> authorities) {
		super(	 // 부모객체 User 생성
				member.getMid(), 
				member.getMpassword(),
				member.isMenabled(),
				true, true, true,	// 만료여부, 자격증명, 자동잠금 => 모두 true로 줘서 사용 안함
				authorities 			    // role의 collection 객체(List가 collection 상속받음)
				);
		this.member = member;
	}
	
	public Ch13Member getMember() {
		return member;		// 현재 로그인한 유저에 대한 정보 getter -> 컨트롤러/서비스에서 사용
	}
	
}

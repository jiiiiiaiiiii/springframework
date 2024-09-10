package com.mycompany.springframework.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13MemberDao;
import com.mycompany.springframework.dto.Ch13Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
// 사용자 정보를 가져오는 서비스는 반드시 UserDetailsService 인터페이스를 구현하여
// loadUserByUsername 메서드 재정의 필요
public class Ch17UserDetailService implements UserDetailsService {
	@Autowired
	private Ch13MemberDao memberDao;
	
	@Override
	// spring-security에서는 username는 "사용자ID"를 의미(사용자 이름 ❌)
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		// db에서 id로 검색해서 사용자에 대한 정보를 가져와 UserDetails 타입으로 리턴
		
		Ch13Member member = memberDao.selectByMid(username);
		if(member == null) {
			throw new UsernameNotFoundException("Bad username");
		}
		
		// SimpleGrantedAuthority: GrantedAuthority 인터페이스를 구현한 클래스
		List<GrantedAuthority> authorities = new ArrayList<>();	// 권한이름을 가지고 있는 collection(한 유저가 여러 권한을 가질 수 있음)
		authorities.add(new SimpleGrantedAuthority(member.getMrole()));
		
		UserDetails userDetails = new Ch17UserDetails(member, authorities);
		return userDetails;
	}
	
}




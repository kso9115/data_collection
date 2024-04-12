package com.example.demo.securityLogin;

import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

// ** UserDetailsService
// => 스프링시큐리티는 인증처리를 위해 UserDetailsService (i) 의 구현체를 사용함.
// => 이 구현체 (아래 CustomUserDetailsService 클래스) 의
//	  loadUserByUsername() 메서드에서 User를 조회하고 인증과 권한을 처리함 
// => 시큐리티 설정
//	-> SecurityConfig.java 의 filterChain() 에 formLogin 설정 추가하면
//	-> 스프링시큐리티는 POST '/user/login' 요청시 username 과 password 라는
//     이름의 인자를 이용해서 로그인을 처리할수 있게됨.

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Member member = memberRepository.getWithRoles(username);
		log.info("** loadUserByUsername , userId => "+username);
		log.info("** loadUserByUsername , member => "+member);
		
		if(member == null){
			throw new UsernameNotFoundException("Not Found");
		}
		
		MemberDTO memberDTO = new MemberDTO(
			member.getId(),
            member.getPassword(),
            member.getName(),
            member.getAge(),
            member.getJno(),
            member.getInfo(),
            member.getPoint(),
            member.getBirthday(),
            member.getRid(),
            member.getUploadfile(),
            member.getRoleList()
                  .stream()
                  .map(memberRole -> memberRole.name()).collect(Collectors.toList()));
			// => enum 으로 정의된 memberRole 에서 name() 메서드로 
			//	  name들 (USER, MANAGER, ADMIN 등) 을 추출해서 List로 반환
		log.info(memberDTO);
		return memberDTO;
	} //loadUserByUsername

} //class

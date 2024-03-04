package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

// ** QueryDSL 적용
public interface MemberDSLRepository {
	// jno 사용하여 Entity를 return
	List<Member> findMemberJnoDSL(int jno);
	
	// join & DTO return
	List<MemberDTO> findMemberJoinDSL();

	List<MemberDTO> findMemberJoinDSL2();
	
}

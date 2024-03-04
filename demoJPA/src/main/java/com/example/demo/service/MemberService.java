package com.example.demo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {


	// DTO => Entity로 변경

	// selectList
	List<Member> selectList();

	// selectOne
	Member selectOne(String id);

	// insert, update
	Member save(Member entity);

	// pwUpdate
	Member pwUpdate(Member entity);

	// delete
	void deleteById(String id);

	// 1) JPARepository Method 규약
	// => jno 별 Member 출력
	List<Member> findByJno(int jno);

	// 2) @Query선언을 이용한 직접쿼리 선언
	// => password Update에 적용
	public void updatePassword(@Param("id") String id, @Param("password") String password);

	public void updatePassword2(@Param("id") String id, @Param("password") String password);

	// 3) Join 구문에 @Query 적용
	List<MemberDTO> findMemberJoin();
}
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//@Component
@Service
@Log4j2
@RequiredArgsConstructor // final로 정의된 생성자를 갖다쓰기위함
public class MemberServiceImpl implements MemberService {

	private final MemberRepository repository;

	// selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}

	// selectOne
	@Override
	public Member selectOne(String id) {

		Optional<Member> result = repository.findById(id);
		if (result.isPresent())
			return result.get();
		else
			return null;
	}

	// insert, update
	@Override
	public Member save(Member entity) {
//		Member entity = entityToDto(entity); // 서비스에 구현해야함
		return repository.save(entity);
	}

	// delete
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

	@Override
	public Member pwUpdate(Member entity) {
		return null;
	}

	// 1) JPARepository Method 규약
	// => jno 별 Member 출력
	@Override
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}

	// 2) @Query선언을 이용한 직접쿼리 선언
	// => password Update에 적용
	@Override
	public void updatePassword(String id, String password) {
		repository.updatePassword(id, password);
	}
	@Override
	public void updatePassword2(String id, String password) {
		repository.updatePassword2(id, password);
	}
	
	// 3) Join 구문에 @Query 적용
	@Override
	   public List<MemberDTO> findMemberJoin() {
	      return repository.findMemberJoin();
	   }

}

package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.repository.MemberRepository;

//@Component
@Service
public class MemberServiceImpl implements MemberService {
	
	
	private final MemberRepository repository;
	
	// selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}

	// 리스트 멤버 출력을 위한 서비스추가
	@Override
	public List<MemberDTO> selectJoList(int jno) {
		return mapper.selectJoList(jno);
	}

	// selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return mapper.selectOne(id);
	}

	// insert
	@Override
	public int insert(MemberDTO dto) {
		return mapper.insert(dto);
	}

	// update
	@Override
	public int update(MemberDTO dto) {
		return mapper.update(dto);
	}

	// delete
	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}

	@Override
	public int pwUpdate(MemberDTO dto) {
		return mapper.pwUpdate(dto);
	}
	
	
	

	

	
}

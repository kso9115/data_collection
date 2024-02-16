package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.MemberDTO;

import mapperInterface.MemberMapper;
import pageTest.Criteria;
import pageTest.SearchCriteria;

//@Component
@Service
public class MemberServiceImpl implements MemberService {
	// 전역변수 선언
	// IOC/DI 적용하여 자동주입 받는 것이다.> 어디선가 생성이 되어있어야 자동주입이가능
	// MemberDAO 클래스에서 component를 통해 bean을 컨테이너에 담아놓은 상태이다.
//	@Autowired(required = false)
//	MemberDAO dao;
//	MemberDAO dao = new MemberDAO();

	// **MyBatis 적용
	// => mapper 구현객체는 스프링이 실행 시 자동으로 만들어 주입해준다.
	// 그러므로 개발자는 interface와 xml만 구현하고 Serivce와 연동만 해주면 된다.
	// 즉, Java interface의 Mapper, Mapper의 namespace값이 모두 동일해야한다.
	// 그리고 해당 메서드는 
	@Autowired(required = false)
	MemberMapper mapper;
	
	
	// board Check_List
	@Override
	public List<BoardDTO> mCheckList(SearchCriteria cri) {
		return mapper.mCheckList(cri);
	}
	@Override
	public int mCheckRowsCount(SearchCriteria cri) {
		return mapper.mCheckRowsCount(cri);
	}

	// mPageList
//	@Override
//	public List<MemberDTO> mPageList(Criteria cri) {
//		return mapper.mPageList(cri);
//	}
//	@Override
//	public int mtotalRowsCount(Criteria cri) {
//		return mapper.mtotalRowsCount(cri);
//	}
	
	// mSearchList
	@Override
	public List<MemberDTO> mPageList(SearchCriteria cri) {
		return mapper.mSearchList(cri);
	}
	@Override
	public int mtotalRowsCount(SearchCriteria cri) {
		return mapper.mSearchRowsCount(cri);
	}

	// selectList
	@Override
	public List<MemberDTO> selectList() {
		return mapper.selectList();
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

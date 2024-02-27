package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberService {

	// ** board Check_List
	public List<BoardDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);
		
	// mPageList
	List<MemberDTO> mPageList(SearchCriteria cri);
	public int mtotalRowsCount(SearchCriteria cri);

	
	// mSearchList
	/*
	 * List<MemberDTO> mSearchList(SearchCriteria cri); public int
	 * mSearchRowsCount(SearchCriteria cri);
	 */
	
	// selectList
	List<MemberDTO> selectList();

	// 리스트 멤버 출력을 위한 서비스추가
	List<MemberDTO> selectJoList(int jno);

	// selectOne
	MemberDTO selectOne(String id);

	// insert
	int insert(MemberDTO dto);

	// update
	int update(MemberDTO dto);

	// pwUpdate
	int pwUpdate(MemberDTO dto);
	
	
	// delete
	int delete(String id);
	

}
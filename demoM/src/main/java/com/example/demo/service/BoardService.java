package com.example.demo.service;

import java.util.List;


import com.example.demo.domain.BoardDTO;

import pageTest.SearchCriteria;

public interface BoardService {
	
	// ** Ajax : ID별 boardList 출력하기
	public List<BoardDTO> idbList(String id);
	
	// ** board Check_List
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	public int bCheckRowsCount(SearchCriteria cri);
	
	// ** board paging ver01 : Criteria 사용
//	public List<BoardDTO> bPageList(Criteria cri);
//	public int totalRowsCount(Criteria cri);

	// ** board paging ver02 : SearchCriteria 사용
	public List<BoardDTO> bPageList(SearchCriteria cri);
	public int btotalRowsCount(SearchCriteria cri);
		
	// 1. selectList
	public List<BoardDTO> selectList();
	// 2. selectOne
	public BoardDTO selectOne(int seq);
	// 3. insert
	public int insert(BoardDTO dto);
	// 3-1. replyInsert
	public int rinsert(BoardDTO dto);
	// 4. update
	public int update(BoardDTO dto);
	// 5. delete
	public int delete(BoardDTO dto);
}

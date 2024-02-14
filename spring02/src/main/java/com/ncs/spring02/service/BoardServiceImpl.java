package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.model.BoardDAO;

import mapperInterface.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
//	@Autowired(required = false)
//	BoardDAO dao;
	
	@Autowired
	BoardMapper mapper;

	@Override
	public List<BoardDTO> selectList() {
		return mapper.selectList();
	}

	@Override
	public BoardDTO selectOne(int seq) {
		return mapper.selectOne(seq);
	}

	@Override
	public int insert(BoardDTO dto) {
		return mapper.insert(dto);
	}
	
	@Override
	public int update(BoardDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int delete(BoardDTO dto) {
		return mapper.delete(dto);
	}

	@Override
	public int rinsert(BoardDTO dto) {
		if(mapper.rinsert(dto)>0) {
			// stepUpdate
			System.out.println("** stepUpdate Count => "+mapper.stepUpdate(dto));
			return 1;
		} else return 0;
	}
	
	
}

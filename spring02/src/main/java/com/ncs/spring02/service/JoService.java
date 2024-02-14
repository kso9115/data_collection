package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoService {

	List<JoDTO> selectList(); 

	JoDTO selectOne(int jno);

	int insert(JoDTO jdto);

	int update(JoDTO jdto);

	int delete(int jno);

}
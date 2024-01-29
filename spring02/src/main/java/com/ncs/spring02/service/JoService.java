package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoService {

	List<JoDTO> selectList();

	JoDTO selectJoOne(int jno);

	int joInsert(JoDTO jdto);

	int joUpdate(JoDTO jdto);

	int delete(int jno);

}
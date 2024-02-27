package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.JoDTO;

public interface JoService {

	List<JoDTO> selectList(); 

	JoDTO selectOne(int jno);

	int insert(JoDTO jdto);

	int update(JoDTO jdto);

	int delete(int jno);

}
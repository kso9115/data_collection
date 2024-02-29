package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.JoDTO;
import com.example.demo.entity.Jo;

public interface JoService {

	// DTO => Entity로 변경
	
	List<Jo> selectList(); 

	Jo selectOne(int jno);

	Jo save(Jo entity);	// insert,update

//	int update(Jo entity);

	void deleteByJno(int jno);

}
package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.JoDAO;

@Service
public class JoService {
	
	@Autowired(required = false)
	JoDAO dao;
	
	public List<JoDTO> selectJoList(){
		return dao.selectList();
	}
	public JoDTO selectJoOne(int jno) {
		return dao.selectOne(jno);
	}
	public int joInsert(JoDTO jdto) {
		return dao.insert(jdto);
	}
	public int joUpdate(JoDTO jdto) {
		return dao.update(jdto);
	}
	public int delete(int jno) {
		return dao.delete(jno);
	}
}

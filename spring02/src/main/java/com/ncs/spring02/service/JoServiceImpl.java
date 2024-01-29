package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.JoDAO;
import com.ncs.spring02.model.MemberDAO;

@Service
public class JoServiceImpl implements JoService {
	
	@Autowired(required = false)
	JoDAO dao;
	
	@Override
	public List<JoDTO> selectList(){
		return dao.selectList();
	}
	@Override
	public JoDTO selectJoOne(int jno) {
		return dao.selectOne(jno);
	}
	@Override
	public int joInsert(JoDTO jdto) {
		return dao.insert(jdto);
	}
	@Override
	public int joUpdate(JoDTO jdto) {
		return dao.update(jdto);
	}
	@Override
	public int delete(int jno) {
		return dao.delete(jno);
	}
}

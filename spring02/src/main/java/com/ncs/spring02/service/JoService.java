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
	JoDAO jodao;
	
	public List<JoDTO> selectJoList(){
		return jodao.selectJoList();
	}
	
	public JoDTO selectJoOne(int jno) {
		return jodao.selectJoOne(jno);
	}
	
	public int joInsert(JoDTO jdto) {
		return jodao.joInsert(jdto);
	}
	public int joUpdate(JoDTO jdto) {
		return jodao.joupdate(jdto);
	}
}

package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import domain.MemberDTO;
import model.MemberDAO;

//@Component
@Service
public class MemberService {
	// 전역변수 선언
	// IOC/DI 적용하여 자동주입 받는 것이다.> 어디선가 생성이 되어있어야 자동주입이가능
	// MemberDAO 클래스에서 component를 통해 bean을 컨테이너에 담아놓은 상태이다.
	@Autowired(required = false)
	MemberDAO dao;
//	MemberDAO dao = new MemberDAO();
	
	// selectList
	public List<MemberDTO> selectList() {
		return dao.selectList();
	}
	
	// selectOne
	public MemberDTO selectOne(String id) {
		return dao.selectOne(id);
	}
	
	// insert
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	}
	
	// update
	public int update(MemberDTO dto) {
		return dao.update(dto);
	}
	
	// delete
	public int delete(String id) {
		return dao.delete(id);
	}
}

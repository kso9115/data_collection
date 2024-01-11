package jdbc02;

import java.util.List;

// ** Service
// Controller 요청에 해당하는 DAO의 메서드를 실행(중간다리역할)
// Controller와 DAO 사이의 중간에 위치하면서
// 이 둘의 의존성을 낮추어준다.

public class StudentService {
	
	// 3) wjd
	
	// 1) 전역변수 정의
	StudentDAO dao = new StudentDAO();
	
	// 2) selectList
	public List<StudentDTO> selectList(){
		return dao.selectList();
	}
	
	// ** selectOne
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}
	
	// ** selectOne2
	public void selectOne2(StudentDTO dto) {
		dao.selectOne2(dto);
	}
	
	
	// ** insert : StudentDTO 타입의 dto 주소를 받는 것
	public int insert(StudentDTO dto) {
		return dao.insert(dto);
	}

	// ** update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}
	
	// ** delete
	public int delete(int sno) {
		return dao.delete(sno);
	}
	
	// ** Transaction Test
	public void transactionTest() {
		dao.transactionTest();
	}
}

package javaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

//** DAO Test 시나리오
//=> Detail 정확성 
// -> Test Data
// -> 정확한 id 를 사용하면 not null : Green_Line
// -> 없는 id 를 사용하면 null : Red_Line

//=> Insert 정확성
// -> 입력 가능한 Data 적용 : 1 return : Green_Line
// -> 입력 불가능한 Data 적용 : 0 return : Red_Line

public class Ex03_DAOTest {

	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();
	
	// 1. Detail 정확성 Test
	@Test
	public void detailTest() {
		

		String id="kso1";	// kso1 : true banana : false
		id="banana";	// kso1 : true banana : false
		dto=dao.selectOne(id);
		System.out.println("** dto => "+dto);
		assertNotNull(dto);
	}
	
	// 2. insert 정확성 Test
	public void insertTest() {
		dto.setId("junit");
		dto.setPassword("1234!");
		dto.setName("유니트테스트");
		dto.setAge(20);
		dto.setJno(7);
		dto.setInfo("유티트insert테스트");
		dto.setPoint(300);
		dto.setBirthday("2000-02-16");
		dto.setRid("kso1");
		
		System.out.println(dto);
		// 성공 시 : 1, 실패 시 0 출력
		assertEquals(dao.insert(dto), 1);
	}
}

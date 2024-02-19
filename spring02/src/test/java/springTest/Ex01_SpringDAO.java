package springTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

//** DAOTest Spring Version
//=> 설정화일(~.xml) 을  사용
// -> 테스트코드 실행시에 설정파일을 이용해서 스프링이 로딩 되도록 해줌
// -> @RunWith(스프링 로딩) , @ContextConfiguration (설정파일 등록)

//=> IOC/DI Test
//=> 공통적으로 사용하는 MemberDAO dao 인스턴스를 전역으로 정의
//=> 자동 주입 받기 ( xml_root-context.xml , @ )

//** SpringJUnit4ClassRunner.class 자동 import 안되면 직접 복.붙 해본다.  

//** import 제대로 안되고 오류발생시 Alt+f5 눌러 Maven Update 한다.
//=> 메뉴 : Project 우클릭 - Maven - Update Project .. 
// ( 하기전 주의사항은 pom.xml 의  <plugin> <configuration> 의 
//          <source>1.8</source> 와 <target> Java 버전 확인 )

@RunWith(SpringJUnit4ClassRunner.class)	// 스프링이 로딩 되도록
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 어떤파일을 설정파일로 할 것인지, 절대적인 위치 삽입
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-root-context.xml")	// 두 가지 이상의 파일을 설정파일로
public class Ex01_SpringDAO {
	
	
	// 자동 주입
	// 생성은 root-context.xml에서 진행
	@Autowired
	MemberDAO dao;
	@Autowired
	MemberDTO dto;
	
	// 1. Detail 정확성 Test
	@Test
	public void detailTest() {
		// 자동주입 확인하기
		System.out.println("==============주입테스트~!");
		System.out.println("** DAO 주입 확인하기 => "+dao);
		System.out.println("** DTO 주입 확인하기 => "+dto);
		assertNotNull(dao);
		assertNotNull(dto);
		
		String id="kso1";	// kso1 : true banana : false
//		id="banana";	// kso1 : true banana : false
		dto=dao.selectOne(id);
		System.out.println("** dto => "+dto);
		assertNotNull(dto);
	}
		
	// 2. insert 정확성 Test
	// @Test
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

package springMybatis;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

import mapperInterface.MemberMapper;


@RunWith(SpringJUnit4ClassRunner.class)	// 스프링이 로딩 되도록
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_interfacemapper {
	
	// ** interface Mapper 설정
	// => Controller -> Service -> (DAO) -> interface Mapper : xml의 sql 구문을 이용해서 DB처리
	
	@Autowired
	MemberMapper mapper;
	// => 성공: MemberMapper mapper = new MemberMapper구현객체 ;
	//    -> 구현객체 생성 부터는 Spring과 Mybatis가 규칙에 의해 처리해줌 
	//    -> 규칙: 패키지 명과 클래스명을 interface , mapper xml, xml의 namespace 모두 동일하게 해줌.
	//           이를 위한 경로 설정 
	//           <mybatis-spring:scan base-package="mapperInterface"/>
	
	@Autowired
	MemberDTO dto;

	@Autowired
	MemberDAO dao;
	
	// ** mapper 동작 테스트
	// => getClass().getName() : 실제동작하는 클래스(MemberMapper의 구현객체) 의 이름 확인가능
	//    이를 통해 우리는 Mapper interface 만 작성했지만, 
	//    내부적으로는 동일한 타입의 클래스가 만들어졌음을 알 수 있다. 
//	@Test
	public void mapperTest() {
		assertNotNull(mapper);
		System.out.println("** MemberMapper interface 구현객체는 mapper가 가져다준다 =>"+mapper.getClass().getName());
		System.out.println("** dto 인스턴스의 실제 동작하는 클래스 이름 =>"+dto.getClass().getName());
	}
	
	// ** mapper의 method Test
	// => Mybatis 사용 시 주의사항
	// 참조형 매개변수 사용 시 주소값이 동일하지 않음(주소를 공유하지 않는다)
	// 형식 : selectDTO(MemberDTO dto)
	// -> 매개변수는 한 개만 사용이 가능하다
	// 그래서 주로 객체형태로 사용한다, 복수의 매개변수를 사용하려면 @Param을 이용하면 된다.
	// xml 대신 @으로 sql 구현이 가능하다.
	
	// 1) selectOne
//	@Test
	public void selectOne() {
		String id = "kso1";	// true
		id = "banana";		// false
		dto=mapper.selectOne(id);
		System.out.println(" ** selectOne DTO => "+dto);
		assertNotNull(dto);
	}
	
//	@Test
	// 2) selectOne
	// MemberDAO 와 Mybatis 비교
	// Mybatis는 참조형 매개변수 사용 시 주소값이 동일하지 않음(주소를 공유하지 않는다)
	public void selectDTO() {
		dto.setId("banana");
		
		// 2-1)  MemberDAO 적용 시
		MemberDTO dto1 = new MemberDTO();
		dto1.setId("kso1");
		dao.selectDTO(dto1);
		System.out.println("** MemberDAO selectDTO() => "+dto1);
		
		// 2-2) Mybatis 적용 시
		MemberDTO dto2 = new MemberDTO();
		dto2.setId("kso1");
//		mapper.selectDTO(dto2);
		dto2=mapper.selectDTO(dto2);
		System.out.println("** Mybatis selectDTO() => "+dto2);
	}
	
	// 3) 복수의 매개변수 사용 Test(selectParam 테스트)
	// => Mybatis에서 2개 이상의 매개변수 처리
	// => Mapper interface에서 @Param 적용가능
	@Test
	public void paramTest() {
		dto = mapper.selectParam("kso1", 4);
		System.out.println("** Mybatis paramTest() => "+dto);
		assertNotNull(dto);
	}
}

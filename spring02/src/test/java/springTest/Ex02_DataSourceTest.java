package springTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;

//*** DataSourceTest
//=> pom.xml 에 <dependency> spring-jdbc 추가
//=> 인터페이스 DataSource 구현객체 DriverManagerDataSource 를 bean 등록하고 (servlet~.xml 또는 root~.xml 에)
//=> DB Connection 생성 확인

@RunWith(SpringJUnit4ClassRunner.class)	// 스프링이 로딩 되도록
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 어떤파일을 설정파일로 할 것인지, 절대적인 위치 삽입
public class Ex02_DataSourceTest {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	MemberDTO dto;
	
	// 1) DBConnection
	@Test
	public void connectionTest() {
		try {
			assertNotNull(dataSource.getConnection()); // 인터페이스므로 메서드 제공
			System.out.println("*** DB connection 성공"+dataSource.getConnection());
		} catch (Exception e) {
			System.out.println("*** DB connection 실패"+e.toString());
		}
	}
	
	// 2) SQL 구문 실행 Test
	
	// 2-1) delete
	public int delete(String id) {
		String sql = "delete from member where id = ? ";
		
		try {
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			
			pst.setString(1, id);
			return pst.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("*** DataSource DB Delete Exception 실패"+e.toString());
			return 0;
		}
	}
	
//	@Test
	public void deleteTest() {
		String id = "junit";	// 존재하는 id, 없는 id
		System.out.println("삭제할 아이디 : "+id);
		assertEquals(delete(id), 1);
	}
	
	// 2-1) insert
	public int insert(MemberDTO dto) {

		try {
			
			String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?)";
			
			Connection cn = dataSource.getConnection();
			PreparedStatement pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());
			pst.setString(10, dto.getUploadfile());
			System.out.println(" ** DataSource DB insert 성공 ");
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** DataSource DB insert Exception => " + e.toString());
			return 0;
		}
		
	}
	
	@Before
	public void insertTest() {
		
		dto.setId("junit11"); // 있는 id, 없는 id 
	    dto.setPassword("12345!");
	    dto.setName("유니트");
	    dto.setAge(20);
	    dto.setJno(7);
	    dto.setInfo("JUnit Test");
	    dto.setPoint(200.456);
	    dto.setBirthday("2000-02-02");
	    dto.setRid("apple");
	    dto.setUploadfile("aaa.gif");
	      
	    assertEquals(insert(dto), 1);
	    System.out.println("** insert dto => "+dto);
	}
}

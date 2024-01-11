package jdbc02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc01.DBConnection;

//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
// Create(Insert), Read(selectList, selectOne), Update, Detete
// 코드를 수정해야하는 경우가 많으나, Controller와 연결해놓을 경우 의존성이 높아져
// 다른 클래스를 수정할 때 영향을 받는 정도가 높아진다.(Service 클래스를 통해서 의존성을 낮춰준다.)

//** 첫번째 예제 DBStart 와 ~~~DAO의 차이점
//	 결과를 직접 처리하지 않고 요청자에게 제공해야한다.
//	 즉 메서드 역할별로 처리 결과를 return 해줘야 한다
//	 그러므로 특히 select 의 결과를 잘 전달하기 위해 결과를 객체화해야한다.

public class StudentDAO {

	// 1) 전역변수 정의 
	private static Connection cn = DBConnection.getConnection(); // 기본적으로 checked이기때문에 try~catch 예외처리 필수
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql; // 모든 타입이 string이기때문에 sql 담당 변수 선언

	// ** Join Test(추가!!!!!!!!!!!!!!!!!!!)
	// => sno, name, agem jno, jname, project, 조장이름 출력하기
	// => JoDTO 작성, joinList() 메서드 작성(Controller, Service, DAO)
	// selectList
	public List<StudentDTO> joinList(){
		sql="select s1.sno, s1.name, s1.age, s1.jno, jname, project, captain," 
				+"(select name from student where sno=captain) cname"
				+" from student s1 Left Outer Join  jo j ON s1.jno=j.jno";
		
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();
			// => 결과의 존재여부
			// => 존재: list 에 담기
			// => 없음: return null
			if(rs.next()) {
				do {
					// setter를 사용
					StudentDTO dto = new StudentDTO();
					dto.setSno(rs.getInt(1));
					dto.setName(rs.getString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setJname(rs.getString(5));
					dto.setProject(rs.getString(6));
					dto.setCaptain(rs.getInt(7));
					dto.setCname(rs.getString(8));
					list.add(dto);
					
				} while(rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("** joinList Exception => "+e.toString());
			return null;
		}
	}
	
	// 2) selectList
	// DBStart에서는 메인에서 호출하여 바로 사용하기 위해 메서드를 static으로 선언하였으나
	// arraylist 타입을 사용하면되므로 조상인 List를 선언
	public List<StudentDTO> selectList() {
		sql = "select * from student";

		// 조상 인터페이스 = 자손
		// arrayList로 하지 않은 이유 : 코드의 유지보수를 위해서
		// 우측의 ArrayList를 LinkedList로 변경이 용이하다
		List<StudentDTO> list = new ArrayList<StudentDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// 결과 존재의 여부를 확인 : 존재 시 list에 담아주기 / 존재하지 않을 경우 return null
			// 데이터를 반복하며 확인해야하며, 지정된 데이터가 확실하지 않고 초기에 출력이 필요하므로 do~while
			if (rs.next()) {
				do {
					// 존재하는 데이터는 DTO 단위를 하나로 취급하여 담아줘야하며,
					// StudentDTO 타입의 변수가 있어야한다

					// 1. 객체 생성 후 데이터 기입해주는 방법
					// 객체 생성을 외부로 뺐을 때 기존 객체에 덮어씌워지기 때문에
					// 반복하며 동일한 데이터만 출력해버림
					StudentDTO dto = new StudentDTO();
					dto.setSno(rs.getInt(1));
					dto.setName(rs.getString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setInfo(rs.getNString(5));
					dto.setPoint(rs.getDouble(6));
//					
					// 2. 객체 생성과 동시에 생성자를 통해 데이터를 기입해주는 방법
//					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
//							rs.getNString(5), rs.getDouble(6));

					// 리스트에 dto를 넣어줘야함 > 값 초기화
					list.add(dto);

				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception ㅇㄴㅇ
			// 에러가 발생했을 때에도 값이 존재하지 않으니까 null 반환해줘야한다.
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		}
	}

	// 3) selectOne
	public StudentDTO selectOne(int sno) {
		sql = "select * from student where sno = " + sno;
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setSno(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setJno(rs.getInt(4));
				dto.setInfo(rs.getNString(5));
				dto.setPoint(rs.getDouble(6));

				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("** selectOne Exception => " + e.toString());
			return null;
		}
	}
	
	// 3)-1 selectOne2
	// 참조 자료형 매개변수 Test (Call By Reference) <=> (기본 자료형 매개변수 Call By Value)
	// 참조 자료형의 참조 자료 매개변수
	public void selectOne2(StudentDTO dto) {
		sql = "select * from student where sno = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSno());
			rs=pst.executeQuery();
			
			if (rs.next()) {
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setJno(rs.getInt(4));
				dto.setInfo(rs.getNString(5));
				dto.setPoint(rs.getDouble(6));
			} else {
				System.out.println("** Student 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** selectOne Exception =>" + e.toString());
			// TODO: handle exception
		}
		
	}

	// 4) insert : 업데이트한 데이터를 나타내는것이 아니라 업데이트 한 갯수를 알려주는거니까 타입은 int로
	public int insert(StudentDTO dto) {
		sql = "insert into student(name, age, jno, info) values(?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());

			// int를 반환할 때 사용하는 메서드
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** insertList Exception => " + e.toString());
			return 0;
		}
	}

	// 5) update
	public int update(StudentDTO dto) {
		sql = "update student set name = ?, jno = ? where sno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getJno());
			pst.setInt(3, dto.getSno());

			if(pst.executeUpdate() > 0) {
				return pst.executeUpdate();
			} else {
				System.out.println("** 데이터 업데이트 실패 **");
				return 0;
			}
		} catch (Exception e) {
			System.out.println(" ** updateList Exception => " + e.toString());
			return 0;
			// TODO: handle exception
		}
	}

	// 6) delete
	public int delete(int sno) {
		sql = "delete from student where sno = " + sno;
		try {
			pst = cn.prepareStatement(sql);
			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(" ** deleteList Exception => " + e.toString());
			return 0;
		}
	} // delete
	
	   // ** Transaction Test
	   // => Connection 객체가 관리
	   // => 기본값은 AutoCommit  true 임.
	   // => setAutoCommit(false) -> commit 또는 rollback 
	   // => Test 사항
	   //   - 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생 

	   // 1) Transaction 적용전
	   // => 동일자료를 2번 입력
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생 
	   //   - Rollback 불가능
	   //   - MySql Command 로 1번째 입력 확인 가능 
	      
	   // 2) Transaction 적용후 
	   // => 동일자료를 2번 입력 
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	   //   - Rollback 가능 -> 둘다 취소됨
	
	public void transactionTest() {
		sql = "insert into student values(19,'김길동',99,9,'Transaction 적용전', 123.45)";
		
		// 1) Transaction 적용전
		try {
			pst=cn.prepareStatement(sql);
			pst.executeUpdate();	// 1번째는 데이터 입력완료
			pst.executeUpdate();	// 2번째(테스트 용)는 p.key 중복 오류 발생 > catch블럭으로 이동	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** transactionTest 적용전 Exception => " + e.toString());
		}
		
		// 2) Transaction 적용후
		try {
			cn.setAutoCommit(false); // start Transaction
			pst=cn.prepareStatement(sql);
			pst.executeUpdate();	// 1번째는 데이터 입력 완료 buffer에 보관
			pst.executeUpdate();	// 2번째(테스트 용)는 p.key 중복 오류 발생 > 오류 발생 시점에서 catch블럭으로 이동 > rollback 처리 
			cn.commit(); // 롤백 시 커밋? 아니면 롤백 후 문제없으면 커밋? 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** transactionTest 적용후 Exception => " + e.toString());
			// Rollback : check 구문이기 때문에 exception 처리가 필요하다
			try {
				cn.rollback();
				System.out.println(" ** transactionTest 적용후 Rollback 성공 => ");
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(" ** transactionTest 적용후 Rollback Exception => " + e.toString());
			}
		}
	}
	
	
	
	
} // class

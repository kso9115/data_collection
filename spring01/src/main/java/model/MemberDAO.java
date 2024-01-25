package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import domain.MemberDTO;


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

//@Component
@Repository
public class MemberDAO {

	// 1) 전역변수 정의 
	private static Connection cn = DBConnection.getConnection(); // 기본적으로 checked이기때문에 try~catch 예외처리 필수
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql; // 모든 타입이 string이기때문에 sql 담당 변수 선언

	// 2) selectList
	// DBStart에서는 메인에서 호출하여 바로 사용하기 위해 메서드를 static으로 선언하였으나
	// arraylist 타입을 사용하면되므로 조상인 List를 선언
	public List<MemberDTO> selectList() {
		sql = "select * from member";

		// 조상 인터페이스 = 자손
		// arrayList로 하지 않은 이유 : 코드의 유지보수를 위해서
		// 우측의 ArrayList를 LinkedList로 변경이 용이하다
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// => 결과의 출력 여부
			if (rs.next()) {
				do {
					// setter
					MemberDTO dto = new MemberDTO();
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
					

					// 2. 객체 생성과 동시에 생성자를 통해 데이터를 기입해주기
//					MemberDTO dto = new MemberDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
//							rs.getInt(5), rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9));

					// 리스트에 dto를 넣어줘야함 > 값 초기화
					list.add(dto);

				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 에러가 발생했을 때에도 값이 존재하지 않으니까 null 반환해줘야한다.
			System.out.println("** selectList Exception => " + e.toString());
			return null;
		}
	}

	// 3) selectOne
	public MemberDTO selectOne(String id) {
		sql = "select * from member where id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setJno(rs.getInt(5));
				dto.setInfo(rs.getString(6));
				dto.setPoint(rs.getDouble(7));
				dto.setBirthday(rs.getString(8));
				dto.setRid(rs.getString(9));
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
	
	// 4) insert
	// => 모든 컬럼 입력
	public int insert(MemberDTO dto) {
		sql = "insert into member values(?,?,?,?,?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());

			System.out.println("성공");
			return pst.executeUpdate();
			// int를 반환할 때 사용하는 메서드
		} catch (Exception e) {
			System.out.println(" ** insertList Exception => " + e.toString());
			return 0;
		}
	}

	// 5) update : id 제외한 모든 컬럼을 수정
	// => id는 primary_key
	// => password의 경우 암호화되어있는 부분이기 때문에 이후에는 별도로 생성하여 변경해야한다.(실습에선 우선 진행)
	public int update(MemberDTO dto) {
		sql = "update member set password=?, name=?, age=?, jno=?, info=?, point=?, birthday=?, rid=? where id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getPassword());
			pst.setString(2, dto.getName());
			pst.setInt(3, dto.getAge());
			pst.setInt(4, dto.getJno());
			pst.setString(5, dto.getInfo());
			pst.setDouble(6, dto.getPoint());
			pst.setString(7, dto.getBirthday());
			pst.setString(8, dto.getRid());
			pst.setString(9, dto.getId());

			if(pst.executeUpdate() > 0) {
				return pst.executeUpdate();
			} else {
				System.out.println("** 데이터 업데이트 실패 **");
				return 0;
			}
		} catch (Exception e) {
			System.out.println(" ** updateList Exception => " + e.toString());
			return 0;
		}
	}

	// 6) delete
	public int delete(String id) {
		sql = "delete from member where id=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			
			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(" ** deleteList Exception => " + e.toString());
			return 0;
		}
	} // delete
} // class

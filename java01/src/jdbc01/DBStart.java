package jdbc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// ** 순서
// 1) JDBC API에 필요한 객체들을 전역 변수 선언 및 정의
// 2) CRUD 기능 처리할 메서드 생성
// 3) main 에서 사용

public class DBStart {
	
	// 해당 클래스 내부에서만 사용할 것으로 private으로 설정
	// DBConnection 클래스에서 생성한 메서드 호출하여 cn에 넣어주기
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;  // 모든 타입이 string이기때문에 sql 담당 변수 선언
	
	// ** 1-StudentList
	// 1. (MySQL에선) MySQL Command > Login > DB 선택 > sql 구문 실행 > 결과
	// 2. (자바에선)   JDBC > Connection 객체 생성 > sql 구문 실행 : Statement 또는 PreparedStatement > 결과(ResultSet rs에 전달됨) > 처리
	public static void selectList() {
		
		// student 테이블 가져올겨
		sql = "select * from student";
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			// ** 결과 출력
			// => 결과가 존재하는지 확인
			// => ResultSet 객체는 이를 위한 메서드 제공 
	        // => next() : 다음에 Data가 존재하면 true, 현재Data를 제공  

			System.out.println(" ** Student List ** ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("sno |  name  | age | jno |        info       | point");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			if(rs.next()) {
				// => selectList 결과가 존재할 때
				// 반복문 사용 : for / while / do~while
				// 초기 데이터가 있는지 확인하고 반복을 하기 때문에 do~while 사용
				do {
				System.out.print(rs.getInt(1)+" ");	
				System.out.print(rs.getString("name")+" ");	
				System.out.print(rs.getInt(3)+" ");	
				System.out.print(rs.getInt(4)+" ");	
				System.out.print(rs.getString(5)+" ");	
				System.out.print(rs.getDouble(6)+"\n");	
				} while(rs.next());
				
			} else {
				// selectList 결과가 1건도 없음을 의미한다.
				System.out.println("** selectList 결과가 1건도 없음 **");
				
			} // if_else
			
		} catch (Exception e) {
			System.out.println("** selectList Exception => "+ e.toString());
			
		} //try 
	} //selectList
	
	// ** 2-조별 List 출력 메서드
	// Statement 활용 : 매개변수를 활용한 조건문 추가
	public static void joList(int jno) {
		sql="select * from student where jno="+jno;
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			System.out.println("  ** Jo별 List **");
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");	
					System.out.print(rs.getString("name")+" ");	
					System.out.print(rs.getInt(3)+" ");	
					System.out.print(rs.getInt(4)+" ");	
					System.out.print(rs.getString(5)+" ");	
					System.out.print(rs.getDouble(6)+"\n");	
				} while(rs.next());
			} else {
				
			}
		} catch (Exception e) {
			System.out.println(" ** joList Exception => "+e.toString());
			// TODO: handle exception
		} // try
	} // joList

	// ** 3-조별 List2 출력 메서드
	// PreparedStatement 활용 : 매개변수를 활용한 조건문 추가
	public static void joListPS(int jno) {
		sql="select * from student where jno=?";
		try {
//			st=cn.createStatement();
//			rs=st.executeQuery(sql); // sql을 실행시에 전달하여 진행
			
			// sql 구문을 미리 전달해야 ? 에 대한 부분을 해결 후 실행(? 에 대한 처리가 필요)
			pst=cn.prepareStatement(sql);
			// (? 파라미터 인덱스, 해당 인덱스의 값)
			pst.setInt(1, jno);
			rs=pst.executeQuery(); // 미리 위에서 처리했기때문에 별도로 sql 전달할필요가 없다.
			
			System.out.println("  ** Jo별 List **");
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");	
					System.out.print(rs.getString("name")+" ");	
					System.out.print(rs.getInt(3)+" ");	
					System.out.print(rs.getInt(4)+" ");	
					System.out.print(rs.getString(5)+" ");	
					System.out.print(rs.getDouble(6)+"\n");	
				} while(rs.next());
			} else {
				
			}
		} catch (Exception e) {
			System.out.println(" ** joList Exception => "+e.toString());
			// TODO: handle exception
		} // try
	} // joList
	
	// ** insert
	// => 입력에 필요한 컬럼을 모두 매개변수로 전달 받아야한다ㅠ.ㅠ
	// 매개변수가 많을수록 굉장히 코드가 길어지고, 불편해짐 -> 해결을 위해 객체화를 진행
	// -> 엔티티(Table)를 Java Class 로 객체화
	// -> DTO, VO, Entity(JPA)
	
	// => 컬럼을 매개변수로 전달받을 경우 sql 구문을 완성하기위해 문자열 연산을 작성해야한다
	// insert into student(name, age, jno, info) point는 default values('홍길동', 22, 9,'관리자입니다.')
	// "insert into student(name, age, jno, info) point는 default values('"
	//  + name + "'," + age .........
	
	// 이 점을 보완하기 위해 제공된 객체 PreparedStatement
	// 변수의 위치에 ?(바인딩 변수)를 사용한다.
	// insert into student(name, age, jno, info) values(?,?,?,?)
	// ? 에 대응값는 JavaCode로 처리(PreparedStatement 제공 메서드)

	public static void insert(String name, int age, int jno, String info) {
		sql="insert into student(name, age, jno, info) values(?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, jno);
			pst.setString(4, info);
			
			// insert 는 DML로 DB의 인스턴스 변화가 생기게 된다 > excuteQuery가 아님
			// executeUpdate를 통해 int를 반환 >> 몇개가 업데이트 되었는지
			
			// cnt 변수 선언하는 경우
			int cnt = pst.executeUpdate();	// insert,update,delete : 실행된 갯수를 리턴
			if(cnt==1) System.out.println("** insert 성공 => "+cnt);
			else System.out.println("** insert 실패 => "); // 입력이 안됐을 경우 catch블럭에서 잡아줘야함..
			
			// 변수 선언하지 않고 쓰는 경우 : 직접 전달하여 바로 실행
			if(pst.executeUpdate() > 0) System.out.println("** insert 성공 => "+cnt);
			else System.out.println("** insert 실패 => ");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** insert Exception => "+e.toString());
		} // try_catch
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Connection 확인
		// cn.toString() 메서드에서 toString() 생략한 상태
		// 즉, 출력문에서 인스턴스명을 사용하면 toString 메서드를 호출한다
		System.out.println("** Connection 확인 => "+ cn);		
		
		// 1) Student List 
//		selectList();
		
		// 2) 조별 List 출력
//		joList(3);
		
		// 3) 조별 List 출력 2
		joListPS(2);
		
		// 4) 데이터 입력
		
		
	} // main

} // class

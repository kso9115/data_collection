package mvcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	// 1. 전체 클래스에서 접속 가능하게 public & static으로 설정
	// 2. java.sql import 진행 필요
	public static Connection getConnection() {
		
		// 3. 외부와의 I/O가 일어나기 때문에 try catch를 진행
		// checked 컴파일 exception 진행 필수 & unchecked exception 예외처리 필수 아님
		// I/O exception 미진행 시 무한 대기상태로 유지될 수 있기때문에 예외처리 필수
		try {
			// 5. 각 DB 별 드라이브를 찾는다.
			// 소문자 class : 예약어 / 대문자 Class : 클래스 이름을 가진 클래스() > 내장 객체라고 생각하면됨
			// 생성자(new)를 사용하지 않고 객체를 생성 + 인스턴스가 없음
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 7. url , id , password 로 각각 연결
			// => allowPublicKeyRetrieval=true : local DB open 하지 않아도 connection 허용
			// => localhost -> 동일값(ip주소) @127.0.0.1
			
			// ip address : 내 컴퓨터에서 db 주소를 찾는 것 @127.0.0.1
			// ip로 간 후 3306 포트를 찾고 db(mydb)를 찾아 들어가는것
			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"; // ?q부터 마지막까지는 설정값을 변경하는 파라미터를 전달하는 것 : 쿼리스트링 > 총 파라미터 4개
			
			// 6. 드라이브매니저를 통해 커낵션 메서드 사용
			// Connection cn = new Connection(); // 요래 생성하면 안된다..
			Connection cn = DriverManager.getConnection(url, "root", "mysql");
			System.out.println("** DB Connection 성공");
			return cn;
			
		} catch (Exception e) {
			// 4. 예외 발생 시 catch를 통해 예외에 대한 내용 서술
			System.out.println("** DB Connection Exception => " + e.toString());
			return null;
		} // try
		
	} // getConnection
	
} // class

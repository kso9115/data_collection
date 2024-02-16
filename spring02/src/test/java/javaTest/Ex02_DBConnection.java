package javaTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class Ex02_DBConnection {

	// 1) static, return 값이 존재하는 경우(@Test 불허)
	// => Test 메서드를 작성해서 Test
	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"; // ?q부터
																																					// 마지막까지는
			Connection cn = DriverManager.getConnection(url, "root", "mysql");
			System.out.println("** DB Connection 성공");
			return cn;

		} catch (Exception e) {
			System.out.println("** DB Connection Exception => " + e.toString());
			return null;
		} // try
	} // getConnection
	
	@Test
	public void connectionTest() {
		System.out.println("** DB_Connection => "+getConnection());
		assertNotNull(getConnection());
	}

}

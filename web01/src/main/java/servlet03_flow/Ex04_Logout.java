package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/logout")
public class Ex04_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex04_Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// uri = "" 리터럴 넣어서 로그아웃 값이면
		response.setContentType("text/html ;  charset = UTF-8 ");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		session.invalidate();
		out.print("<h3>** 로그아웃! </h3>");
		
		// 1. 요청 분석
		String uri = "home.jsp";
		
		// 2. 서비스 처리
		request.getSession().invalidate();
		System.out.println("로그아웃 완료");
		// 로그아웃 완료 후 기존 home.jsp로 이동
		response.sendRedirect(uri); 
	} // doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

} // class

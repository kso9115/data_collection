package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/radio")
public class Ex02_RadioText extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_RadioText() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청(request) 처리
		// 한글 변환 처리, 데이터를 전달받기
		request.setCharacterEncoding("UTF-8");
		
		String gender = request.getParameter("gender");
		String mailcheck = request.getParameter("mailcheck");
		String content = request.getParameter("content");
		
		// 2. Service 처리
		if(mailcheck.equals("Yes")) mailcheck = "수신동의";
		else mailcheck = "수신거절";
		
		// 3. 결과 (View) 처리
		// 한글 처리, 출력객체 생성하여 response 에 담아주기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<h3>당신의 성별 :  </h3>"+gender);
		out.print("\n<h3>메일 수신 동의 :  </h3>"+mailcheck);
		out.print("\n<h3>메일 수신 동의 :  </h3>"+content);
		out.print("</body></html>");
		
		// javascript:history.go(-1) : 이전 페이지로 다시 되돌아가기위하여 사용
		// history : 웹브라우저가 기억하고있는 순차페이지에서 그 전 페이지로 돌아간다.
		out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시 입력하기</a>");
		
		
		
		
	}

}

package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex01_Adder
 */
@WebServlet("/adder")
public class Ex01_Adder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex01_Adder() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1) 요청 분석
		// => request 처리 : 한글, Parameter 처리
		request.setCharacterEncoding("UTF-8");
		
		try {
			Integer num1 = Integer.valueOf(request.getParameter("num1"));
			Integer num2 = Integer.valueOf(request.getParameter("num2"));
			System.out.printf("두 값의 합은 : %d",num1 + num2);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.printf("<div>%d+%d = %d</div>", num1, num2, num1 + num2);
		} catch (Exception e) {
			System.out.println("값이 입력되지 않음");
		}
		
		// 쌤 코드 : 요청시 형변환을 통해 int 변수에 담아주기
		String number = request.getParameter("num1");
		int num1=Integer.parseInt(number);
		int num2=Integer.parseInt(request.getParameter("num2"));
		
		// 2) Service & 결과 처리
		// => response 결과물에 대한 처리 : 한글 ,출력객체 생성 & response에 담기
		
		// 쌤 코드
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.printf();
	}
}

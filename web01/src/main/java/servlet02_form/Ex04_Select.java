package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/select")
public class Ex04_Select extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Select() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1) Request 처리
		request.setCharacterEncoding("UTF-8");

		String job = request.getParameter("job");
		String[] interest = request.getParameterValues("interest");

		// 2) Service

		// 3) View 처리 : 연산결과 출력
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// interest 선택 job!=null : 내부서버 오류를 방지하기 위한 코드
		if (job != null && job.length() > 0) {
			out.print("<html><body>");
			out.print("<div> 직업은 : ");
			out.print(job);
			out.print("</div>");
//			out.print("<h3>** 당신의 직업은 => "+job+"</h3>");
		} else {
			out.print("<h3>** 직업? 당신은 아무것도 선택하지 않았습니다.</h3>");
		}
		
		// interest 선택
		if (interest != null && interest.length > 0) {
			for (String selectInterest : interest) {
				out.print("<h3>** 당신이 좋아하는 음료는 => " + selectInterest + "</h3>");
			}
		} else {
			out.print("<h3>** 음료? 당신은 아무것도 선택하지 않았습니다.</h3>");
		}
	}
}

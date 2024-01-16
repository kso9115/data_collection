package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class Ex03_Check extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex03_Check() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1) 요청 분석
		// => request 처리 : 한글, Parameter 처리
		request.setCharacterEncoding("UTF-8");
		String[] gift = request.getParameterValues("gift"); // 선택한 값만을 배열로 gift 변수에 담아주기

		// => CheckBox 처리
		// -> 하나의 Name 에 복수개의 Value 들이 있음
		// -> request.getParameterValues("gift") 를 이용해서 배열로 처리
		// 단일값일때 사용할 메서드 / 배열을 처리할 때 메서드
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 선택 여부를 확인하기 : 한 개도 선택하지 않았을 때를 위한 코드 작성
		if (gift != null && gift.length > 0) {
			out.print("<html><body>");
			out.print("<div>");
			for (String selectGift : gift) {
				out.println(selectGift);
				out.print("</div>");
				out.print("</body></html>");
//				out.printf("<h3>%s</h3>",selectGift);
			}
		} else {
			out.print("<h3>아무것도 선택되지 않음</h3>");
		}
	}

}

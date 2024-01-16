package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/flow01")
public class Ex01_Flow01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex01_Flow01() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Forward 방식
		// => flow01 -> hello (내용이 전달은 되나, 출력문이 출력되지 않음)

		// => 출력문이 출력되지 않는 부분을 확인하기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<h3>** Forward Test **</h3>");
		// console로 확인
		System.out.println("** Forward & Redirect Test => flow01");

		// 1-1) Forward 01 : servlet -> servlet으로의 이동
//		String uri = "hello";

		// 목적지를 getRequestDispatcher 인자로 전달, 변수에 담지 않고 선언하는 방법을 더 자주 사용
		// request에서 메서드를 사용함으로써 객체를 생성한 것이기 때문에 메서드를 연달아서 사용할 수 있다는 점 확인
//		RequestDispatcher dis = request.getRequestDispatcher(uri);
//		dis.forward(request, response);
//		request.getRequestDispatcher(uri).forward(request, response);

		// 1-2) Forward 02 : servlet -> Jsp, Html으로의 이동
		// http://localhost:8080/web01/flow01 라고 주소는 바뀌지 않는 것 확인 가능
		String uri="servletTestForm/form04_Select.jsp";
//		request.getRequestDispatcher(uri).forward(request, response);

		// 2. Redirect (재요청 처리)
		// 첫번째 요청 flow01 에서 hellos 라는 요청을 다시 보내줄것을 웹브라우져에게 response 함.
		// 재요청으로 hellos 가 서버로 전달되어 처리하는 방식
		// => 그러므로 웹브라우져에 표시된 요청명이 hellos 로 변경됨
		// 2.1) Servlet -> Servlet
		// 2.2) Servlet -> JSP
		
		response.sendRedirect(uri); // 서블릿>서블릿
	}

}

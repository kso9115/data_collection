package mvcTest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myinfo")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_MVC02Detail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 연동을 위한 service 객체 생성
		StudentService service = new StudentService();
		// Ex04_Login.java에서 로그인 시 입력한 데이터를 세션에 저장해놓은것을 가져와서 형변환(방법여러가지)
//		StudentDTO mySno = service.selectOne((Integer)(request.getSession().getAttribute("loginID")));
		int mySno = (Integer)request.getSession().getAttribute("loginID");
//		int mySno = (int)request.getSession().getAttribute("loginID");

		StudentDTO myinfo = service.selectOne(mySno);

		// 결과를 view가 인식 가능하도록 setAttribute
		request.setAttribute("myinfo", myinfo);

		String uri = "mvcTestJsp/ex03_MVC02Detail.jsp";
		request.getRequestDispatcher(uri).forward(request, response);
	}
}

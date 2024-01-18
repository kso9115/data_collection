package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/mlist")
public class C01_mList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C01_mList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 1. 요청 분석 : 
		// controller에서 out.print를 통해 직접 웹 브라우저를 출력할 경우 한글 변환이 필요하나,
		// 현재는 view로 보내 처리할 예정이므로 view에서만 변환해주면된다.
		MemberService service = new MemberService();

		// 2. Service 처리, 처리 결과 보관(attribute에 보관)
		service.selectList();
		request.setAttribute("banana", service.selectList());
		
		// 3. View 처리(Service 결과를 보내주기)
		request.getRequestDispatcher("member/memberList.jsp").forward(request, response);
	} // doGet
}

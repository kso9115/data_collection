package controllerM;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mdetail")
public class C03_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C03_mDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 1. 요청분석
	    // => request 의 Parameter 처리
		// => id, password 처리
		
		String id = (String)request.getSession().getAttribute("loginId");	// 세션에 selectOne을 통해 가져온 loginId객체를 호출하여 불러줘야함
		String uri = "member/memberDetail.jsp";
		System.out.println(id);
		
		// jCode = "U" 사용하여 view 이동하기
		// => Update 요청시에는 updateForm.jsp 로
		if("U".equals(request.getParameter("jCode"))) {
			uri="member/updateForm.jsp";
		}
		
	    // 2. 서비스 처리
	    // => Service, DTO 객체 생성
		// => 결과를 View가 출력 가능하도록 attribute에 저장
		MemberService service = new MemberService();
		request.setAttribute("apple", service.selectOne(id));
		
	    // 3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

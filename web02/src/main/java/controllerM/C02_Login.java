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

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C02_Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// 보안 유지를 위해 doPost 사용
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		// 1. 요청분석
		// => request 의 Parameter 처리
		// => id, password 처리
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
//		if(request.getParameter("password")!=null && request.getParameter("password").equals("password")) {
//			pw = request.getParameter("password");
//		}
		
		// 2. 서비스 처리
		// => Service, DTO 객체 생성
		// id 확인 : Service의 selectOne
		// id 확인 후 password 일치 여부
		// 성공 시 id, name을 session에 보관 후 home으로 이동
		// 성공 시 loginForm으로 이동 후 재로그인
		MemberService service = new MemberService();
		MemberDTO dto = service.selectOne(id);
		String uri = "home.jsp";
		
		if(dto!=null && pw.equals(dto.getPassword())) {
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("loginName", dto.getName());
			response.sendRedirect(uri);
			System.out.println("성공");
		} else {
			request.setAttribute("message", "** 로그인 실패했숨당~!");
			uri = "member/loginForm.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
			System.out.println("실패");
		}
		
		// 3. View (Response) : Forward
	}

}

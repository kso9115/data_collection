package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/loginT")
public class Ex04_Login2_T extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Login2_T() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청 분석
		// => 한굴,request의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		
		// 쌤 코드 요청 분석
		// sno 예외 방지 처리 : 전달받은 파라미터의 sno값이 빈값 혹은 null일때 예외를 걸어줄 
		int sno = 0;
		if(request.getParameter("sno").trim()!=null && request.getParameter("sno").trim().length()>0) {
			sno = Integer.parseInt(request.getParameter("sno"));
		}
		String name = request.getParameter("name").trim();	// 현재 이 유저가 입력한 값을 보관
		String uri = "home.jsp";

		// 2. Service 처리
		// => ~Service, ~DTO 인스턴스 선언해서 데이터 가져와야한다
		// => Service의 selectOne을 이용하여 sno 확인
		// 확인 결과 sno가 존재하면(성공이면) name 확인
		// => 전체 성공 후 index.html 화면으로 이동
		// => 실패 시 LoginForm.jsp 화면으로 이동
		StudentService service = new StudentService();
		// sno 형변환해서 담아주기
		StudentDTO dto = new StudentDTO();
		dto = service.selectOne(sno);
		
		// name 예외 방지 처리
		// 쌤 코드
		if(dto!=null && dto.getName().equals(name)) {
			request.getSession().setAttribute("loginName", dto.getName());
			request.getSession().setAttribute("loginID", dto.getSno());
			System.out.println("로그인 성공");
			System.out.println("로그인 student => "+dto);
			response.sendRedirect(uri);
		} else {
			System.out.println("로그인 실패");
			request.setAttribute("message", "로그인 실패");
			uri = "servletTestForm/flowEx04_LoginForm.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
			response.sendRedirect(uri);
		}

		// 3. View 처리(Response) : Forward 방식
		// => 성공했을 때 : index.html(첫 메인화면으로 이동)
		// => 실패했을 때 : LoginForm.jsp(다시 로그인을 할 수 있도록 로그인 화면으로 재이동)
//				request.getRequestDispatcher(uri).forward(request, response);;
		// forward 방식으로 진행 시
		// http://localhost:8080/web01/servletTestForm/flowEx04_LoginForm.jsp/index.html
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
} // class

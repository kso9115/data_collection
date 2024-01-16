package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex04_Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청 분석
		// => 한굴,request의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String uri = "home.jsp";
		
		// 쌤 코드 요청 분석
//		int sno = 0;
//		if(request.getParameter("sno")!=null && request.getParameter("sno").length()>0) {
//			sno = Integer.parseInt(request.getParameter("sno"));
//		}
//		String name = request.getParameter("name");	// 현재 이 유저가 입력한 값을 보관
//		String uri = "index.html";

		// 2. Service 처리
		// => ~Service, ~DTO 인스턴스 선언해서 데이터 가져와야한다
		// => Service의 selectOne을 이용하여 sno 확인
		// 확인 결과 sno가 존재하면(성공이면) name 확인
		// => 전체 성공 후 index.html 화면으로 이동
		// => 실패 시 LoginForm.jsp 화면으로 이동
		StudentService service = new StudentService();
		// sno 형변환해서 담아주기
		StudentDTO dto = service.selectOne(Integer.parseInt(sno));

//				System.out.println(service.selectList());
//				System.out.println(service.selectOne(Integer.parseInt(sno)));

		// 문자열 비교는 equal
//		if ((dto.getSno()) == Integer.parseInt(sno) && (dto.getName()).equals(name)) {
//			// index.html 로 이동 , redirect 사용
//			response.sendRedirect(uri);
//			System.out.println("로그인 성공");
//		} else {
//			// 재로그인
//			uri = "servletTestForm/flowEx04_LoginForm.jsp";
////			response.sendRedirect(uri);
//			request.getRequestDispatcher(uri).forward(request, response);
//			System.out.println("로그인 실패");
//		}
		
		// 쌤 코드
		if(dto!=null && dto.getName().equals(name)) {
			request.getSession().setAttribute("loginName", name);
			request.getSession().setAttribute("loginID", sno);
			System.out.println("로그인 성공");
			System.out.println("로그인 student => "+dto);
			response.sendRedirect(uri);
		} else {
			System.out.println("로그인 실패");
			request.setAttribute("message", "로그인 실패");
			uri = "servletTestForm/flowEx04_LoginForm.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
		}

		// 3. View 처리(Response) : Forward 방식
		// => 성공했을 때 : index.html(첫 메인화면으로 이동)
		// => 실패했을 때 : LoginForm.jsp(다시 로그인을 할 수 있도록 로그인 화면으로 재이동)
//				request.getRequestDispatcher(uri).forward(request, response);;
		// forward 방식으로 진행 시
		// http://localhost:8080/web01/servletTestForm/flowEx04_LoginForm.jsp/index.html
		// 4.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

} // class

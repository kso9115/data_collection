package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mjoin")
public class C04_mJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C04_mJoin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리 & post로 전달할 예정이기 떄문에 한글 변환처리 필수
		// => 성공 후 : 로그인 유도(loginForm.jsp 로 이동)
		// => 실패 시 : 재가입 유도(joinForm.jsp 로 이동)
		request.setCharacterEncoding("UTF-8");
		String uri = "member/loginForm.jsp";

		// 별도로 변수 담아주지 않기
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setJno(Integer.parseInt(request.getParameter("jno")));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint(Double.parseDouble(request.getParameter("point")));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));

		// 변수 담아서 작업
//		String id = request.getParameter("id").trim();
//		String pw = request.getParameter("password").trim();
//		String name = request.getParameter("name").trim();
//		String age = request.getParameter("age").trim();
//		String jno = request.getParameter("jno").trim();
//		String info = request.getParameter("info").trim();
//		String point = request.getParameter("point").trim();
//		String birthday = request.getParameter("birthday").trim();
//		String rid = request.getParameter("rid").trim();
		
		
		// 2. 서비스 처리
		// => Service, DTO 객체 생성
		// => 결과를 View가 출력 가능하도록 attribute에 저장
		MemberService service = new MemberService();
		if(service.insert(dto) > 0) {
			// 성공
			request.setAttribute("message", "회원가입 성공! 로그인 후 이용하세요");
			System.out.println("성공");
		} else {
			// 실패
			request.setAttribute("message", "회원가입 실패! 다시 진행해주세요");
			uri = "member/joinForm.jsp";
			System.out.println("실패");
			
		}

		// 3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// jsp에서 post 방식으로 전달을 했기 때문에
		// doGet 메서드 사용 시 doPost 내에서 호출을 진행해주어야한다.
		doGet(request, response);
	}
	
	

}

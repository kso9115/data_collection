package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mupdate")
public class C05_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C05_mUpdate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리 & post로 전달할 예정이기 떄문에 한글 변환처리 필수
		// => 성공 후 : 내 정보로 유도(memberDetail.jsp 로 이동)
		// => 실패 시 : 재수정 유도(updateForm.jsp 로 이동)
		// => 출력 객체(apple) 필요
		// 	  -> redirect 또는 apple에 저장
		// 	  -> 실습 apple로 진행
		
		String uri = "member/memberDetail.jsp";
		request.setCharacterEncoding("UTF-8");

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
		// 서비스 처리하러 가기전에 결과 출력을 위해 apple에 보관
		request.setAttribute("apple", dto);

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
		if(service.update(dto) > 0) {
			// 성공 : session에 보관한 Name 수정
			request.getSession().setAttribute("loginName", dto.getName());
			request.setAttribute("message", "회원정보수정 성공!");
			System.out.println("성공");
		} else {
			// 실패
			request.setAttribute("message", "회원정보수정 실패! 다시 진행해주세요");
			uri = "member/updateForm.jsp";
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

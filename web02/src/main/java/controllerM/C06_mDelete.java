package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C06_mDelete() {
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
		String uri = "home.jsp";
		

		// 서비스 처리
		// Service 객체 생성과 실행
		MemberService service = new MemberService();
		
		if(service.delete((String)request.getSession().getAttribute("id")) > 0) {
			System.out.println("지워짐");
			request.getSession().invalidate();
			
		} else {
			uri = "member/deleteForm";
			request.getRequestDispatcher(uri).forward(request, response);
			
		}

		response.sendRedirect(uri);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// jsp에서 post 방식으로 전달을 했기 때문에
		// doGet 메서드 사용 시 doPost 내에서 호출을 진행해주어야한다.
		doGet(request, response);
	}
	
	

}

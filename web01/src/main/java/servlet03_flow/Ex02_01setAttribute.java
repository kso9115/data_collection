package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/01set") // 매핑 네임 변경
public class Ex02_01setAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex02_01setAttribute() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. request 처리
		// => form 없이 쿼리스트링으로 처리하는 Test
 		// ~~/01set?id=banana&name=홍길동&age=22
		// => 한글 변환 처리(사실상 Post 요청 시 필수인 것이나, 연습용으로 진행해보기)
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		// 정수의 경우 타입 형변환 필요하다 : get 처리 오류로 우선은 주석처리
//		int age = Integer.parseInt(request.getParameter("age"));
		String age = request.getParameter("age");
		// 파라미터가 정상적으로 들어왔는지 확인하기
		System.out.println("** setAttribute Test "); 
		System.out.printf("** Parmeter id=%s, name=%s, age=%s\n",id,name,age);
		
		// 2. setAttribute 처리
		// => 보관 가능한 객체(Scope(처리시간이 짧은 순서) : Page < Request < Session < Application)
		// 2-1) request에 우선 보관
//		request.setAttribute(변수명, value);
		request.setAttribute("rid", id);
		request.setAttribute("rname", name);
		request.setAttribute("rage", age);
		
		// 2-2) session : HttpSession 객체 임포트하기
		HttpSession session = request.getSession();
		session.setAttribute("sid", id);
		session.setAttribute("sname", name);
		session.setAttribute("sage", age);

		// 3. 페이지 이동 후 getAttribute
		// => Forward / Redirect
		String uri = "02get"; //전달온 데이터를 저장해줄 변수 선언
		
		// 3-1) Forward
//		request.getRequestDispatcher(uri).forward(request, response);
		
		// 3-2) Redirect : 요청이 2번 진행됐기 때문에
		// request에 값이 담기더라도 소멸되어 attribute값 확인 불가(null)
		// 즉 기존에 값을 넣었던 상태(02get 위치)에서 재요청이 되는 것이므로 값이 모두 사라지게된다.
		// 다만 창이 종료되지 않았으므로 session 값은 유지
		response.sendRedirect(uri);
	}
}

package servlet03_flow;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessioni")
public class Ex03_SessionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex03_SessionInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Session 인스턴스 생성
		// => Session 객체는 클라이언트가 접속과 동시에 서버에서 자동 생성됨
		// 이 값을 코드내에서 사용하기위해 전달받음
		HttpSession session = request.getSession();

		// 2. Session Info 출력
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		response.setContentType("text/html ;  charset = UTF-8 ");
		PrintWriter out = response.getWriter();
		out.print("<h2>** Session Info **</h2>");
		// => 현재 시간 확인
		out.printf("<h3>* Session_ID : %s </h3>", session.getId());
		out.printf("<h3>* 현재시간 : %s </h3>", formatter.format(now));
		// => Session의 생성시간
		now.setTime(session.getCreationTime());
		out.printf("<h3>* CreationTime : %s </h3>", formatter.format(now));
		// => 마지막 접근시간
		now.setTime(session.getLastAccessedTime());
		out.printf("<h3>* LastAccessedTime : %s </h3>", formatter.format(now));
		// * Session_ID : FEF91D91C8C7101E8568619F6FA24CC3 > 세션 아이디는 동일하게 계속 유지된다
		// * CreationTime : 2024-01-12 15:32:52

		// * CreationTime의 경우 코드 변경(수정사항) 있을 때
		// restart 시 CreationTime 변경된다

		// 3. Session Time(제한시간) 설정
		// => 1-메서드를 이용하는 방법 : setMaxInactiveInterval(10), 단위가 초 즉 1시간(60*60)
		// => 2-설정파일(web.xml) 활용: 단위가 분
		// Tag session-config 의 subTag session-timeout

//		session.setMaxInactiveInterval(10);	// 단위 : 초, 10초가 지날 때 세션이 변경(세션 아이디가 변경됨)

		// 4. Session 무효화(종료) : session.invalidate()
		// => invalidate : 무효화
		// 세션객체 자체를 소멸시키는것이 아니라, 세션을 초기화하고 무효화시킴.
		// session 이 null 이 아니고 session = ""

		// => 퀴리스트링으로 테스트 ( ~~/sessioni?jCode=D )
		// => 주의: jCode 라는 Parameter 가 없는 경우 null 을 return
		// -> NullPointerException 예방 하도록 작성

//		if(request.getParameter("jCode").equals("D")) {
//			session.invalidate(); // 실행 시 마다 무효화-종료
//		} 
		// => request.getParameter("jCode") 파라미터 값을 먼저 불러오게되면
		// 어쨋든 파라미터 값은 무조건 불러와야하기때문에 jcode가 아닐때 NullPointerException 발생되니까
		// else 로 내려가서 처리를해줘야함;;
		
		if("D".equals(request.getParameter("jCode"))){
			session.invalidate(); // 실행 시 마다 무효화-종료
			System.out.println("** Session 무효화 ");
			out.print("<h3>** Session 종료! </h3>");
			return;
		}
		out.print("<h3>** Session Info 정상실행 후 종료 </h3>");
		// * Session_ID : 88631D6814590D5BC551D52D64993C2A
		
		// * Session_ID : 8CBE9126B2C42849BE0C6AC812BDD809
	}
}

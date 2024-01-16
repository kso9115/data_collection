package servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/hello")	// @ : 애노테이션 > 컴파일 시 영향
//@WebServlet(urlPatterns = {"/hello","/안녕","/123","/7seven","/seven7"}) // 복수선언	
public class Ex01_HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// 별도로 메인이 존재하지 않고, 클라이언트의 요청에 따라 자동반응하여 실행 : 이벤트 드리븐
    public Ex01_HelloServlet() {	// 생성자
        super();
    }

    // throw : 예외처리 위임 (doGet 메서드 호출 시 예외 발생)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ")
//							.append(request.getContextPath())
//							.append("여기는 doGet 메서드 입니다.");
		
		// <웹 브라우저에게 작업을 하라고 알려주는 거!!!!!>
	    // ** 출력문 (response 객체에 html 문서를 담아줌)
	    // => 출력객체 생성 -> html 문서작성
	    // => 한글처리 해야함 (출력객체 생성전에 해야함)
		response.setContentType("text/html; charset=UTF-8");
	    // => 웹브라우져에게 처리할 데이터의 MIME 타입을 알려줌
	    // => MIME : Multipurpose Internet Mail Extensions
	    // => 데이터 송.수신시 자료의 형식에 대한 정보 
	    // => Jsp 의 page 디렉티브의 contentType 속성값과 동일
		
		PrintWriter out = response.getWriter();	// 객체 생성
		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Hello Servlet **</h2>");
		out.print("<h3 => ContextPath : " + request.getContextPath()+"</h3>");
		out.print("<h3 => 여기는 doGet 메서드 입니다.</h3>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ")
//		.append(request.getContextPath())
//		.append("여기는 doPost 메서드 입니다.");
		
		PrintWriter out = response.getWriter();	// 객체 생성
		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Hello Servlet **</h2>");
		out.print("<h3 => ContextPath : " + request.getContextPath()+"</h3>");
		out.print("<h3 => 여기는 doPost 메서드 입니다.</h3>");
	}

}

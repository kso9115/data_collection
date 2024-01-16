package servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getpost") // @ : 애노테이션 > 컴파일 시 영향
public class Ex03_GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex03_GetPost() { // 생성자
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1)-1 request 의 Parameter 처리
		// => 한글 처리 : getParameter 전에 처리해야한다.
		// - Tomcat(WAS) 은 Get 방식요청에서는 "UTF-8" 을 default 로 적용함
		// ( html 문서에서 "UTF-8" 작성되었고 , Get 방식으로 전송되면 생략가능
		// 단, post 방식에서는 반드시 처리해야함 )

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		// => name이 id인 input Tag의 value 값을 return
		String name = request.getParameter("name");


		// 1)-2
		// => 해당하는 Parameter가 없는 경우 : null을 return
		// => Parameter는 존재하지만 값이 없는 경우 : null은 아니지만 값이 없는 상태(빈문자열x)
		//	( http://localhost:8080/web01/getpost?id=banana&name=바나나&password= )
		// 방어코드 : password.length()>0  -> 값이 없을 때 빈문자열을 값으로 인식하여 true로 판단하기 때문에 빈문자열 방어코드 생성 
		String password = request.getParameter("password");
		if (password != null && password.length()>0) {
			System.out.println("** password => " + password);
		} else {
			System.out.println("** password is null ");
		}

		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter(); // 객체 생성
		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Get/Post **</h2>");
		out.print("<h3 => 전달된 Parameter 확인</h3>");
		out.print("<h3 => id : " + id + "</h3>");
		out.print("<h3 => name : " + name + "</h3>");
		out.print("<h3 => 여기는 doGet 메서드 입니다.</h3>");
		out.print("</body></html>");

		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

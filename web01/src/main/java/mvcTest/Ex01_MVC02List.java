package mvcTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import mvcTest.StudentService;
import mvcTest.StudentDTO;

@WebServlet("/list2")
public class Ex01_MVC02List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex01_MVC02List() {
		super();
	}

	// ** MVC 패턴 1 StudentList 출력하기
	// => 요청을 Service에서 처리 후
	// => 결과를 출력(Java 스크립트)

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Service 연결 후 리스트 가져오기
		StudentService service = new StudentService();
		List<StudentDTO> list = service.selectList(); // 리스트를 변수에 담아줘도 되나 그냥 바로 메서드 호출해서 씀
//		service.selectList(); // List 형태로 데이터를 가져와도 된다.

//    	StudentDTO dto = new StudentDTO();

		// 2. 결과 출력 : Jsp, Java 스크립트 처리
		// => 화면에 출력해내는 건 jsp가 할 일이기 때문에
		// => Service 결과물인 List를 Jsp가 출력할 수 있도록 객체로 전달해야한다.
		// => 전달하기 위해 어트리뷰트에 보관하여 전달하면 됨. 어디에?
		// 서버 안에서 결과를 찾았고 그 내용을 전달하기 위함이므로 request에 어트리 뷰트를 생성하여 저장하면 된다.
		// request.setAttribute(...)
		request.setAttribute("myList", list);
		// => 담은 객체를 Forward 방식으로 전달 : request객체에 담았기 때문에 forward 사용
		
//		String uri="mvcTestJsp/ex01_MVC02List.jsp"; // version 01 자바코드 스크립트로 진행했을 때
		String uri="mvcTestJsp/ex02_MVC02List.jsp"; // version 02 jstl로 진행했을 때
		
		request.getRequestDispatcher(uri).forward(request, response);

	}

}

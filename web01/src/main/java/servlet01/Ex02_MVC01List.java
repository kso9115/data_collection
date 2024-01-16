package servlet01;

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

@WebServlet("/list")
public class Ex02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_MVC01List() {
		super();
	}

	// ** MVC 패턴 1 StudentList 출력하기
	// => 요청을 Service에서 처리 후
	// => 결과를 출력

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Service 연결 후 리스트 가져오기
		StudentService service = new StudentService();
		service.selectList(); // List 형태로 데이터를 가져옴
//    	List<StudentDTO> list = service.selectList(); // 리스트를 변수에 담아줘도 되나 그냥 바로 메서드 호출해서 씀

//    	StudentDTO dto = new StudentDTO();

		// 2. 결과 출력 : 출력내용을 Response 객체의 Body 영역에 Write
		// - 한글 처리
		// - 출력 객체 생성 및 출력
		response.setContentType("text/html; charset=UTF-8"); // 한국어 변환
		PrintWriter out = response.getWriter();

		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Servlet_MVC1 StudentList **</h2>");
		out.print("<table border=1><tr><th>Sno</th><th>Name</th><th>Age</th>");
		out.print("<th>Jno</th><th>Info</th><th>Point</th><tr>");

		if (service.selectList() != null) {	// 값이 존재할 때
			for (StudentDTO s : service.selectList()) {
				out.print("<tr><td>"+s.getSno()+"</td>");
				out.print("<td>"+s.getName()+"</td>");
				out.print("<td>"+s.getAge()+"</td>");
				out.print("<td>"+s.getJno()+"</td>");
				out.print("<td>"+s.getInfo()+"</td>");
				out.print("<td>"+s.getPoint()+"</td></tr>");
			}
		} else {
			out.print("<h2 style='color:blue;'>** 출력 Data가 없음 **</h2>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

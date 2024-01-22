package myDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//*** Spring MVC2_ver01
//=> MyDispatcherServlet (FrontController 역할)
//  HandlerMapping, ViewResolver 를 활용해서
//  요청분석, Service, View 를 처리

// Uril Mapping은 web.xml에서 처리
public class MyDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ** 전역벽수 정의
	// handler mapping
	private MyHandlerMapping hmappings;
	private MyViewResolver vresolver;
	
	// ** 멤버변수 초기화 : 생성자에서
	public MyDispatcher() {
		super();
		hmappings = MyHandlerMapping.getInstance();
		vresolver = new MyViewResolver();
		vresolver.setPrefix("/WEB-INF/views/");
		vresolver.setSuffix(".jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청 분석 : url 분석 후 요청명 확인 및 한글처리
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/"));
		
		// 2. service 실행
		// MyHandlerMapping에 요청 ,해당 서비스 컨트롤러이 인스턴스를 제공받음
		// => 요청에 해당하는 서비스를 실행
		MyController controller = hmappings.getController(uri);
		
		if(controller!=null) {
			// controller 가 작동한 처리를 uri 에 다시 입력하여 경로 설정해준다.
			uri=controller.handleRequest(request, response);
		} else {
			uri = "home";
			request.setAttribute("message", "없는 요청입니다.");
		}
		
		// 3. view 처리
		// 최종 view Name을 완성한 후 포워드 진행
		uri = vresolver.getViewName(uri);
		request.getRequestDispatcher(uri).forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

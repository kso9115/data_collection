package controllerF;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//** FrontController 패턴 2.
//=> Factory 패턴 적용
// - ServiceFactory
// - 개별컨트롤러(일반클래스) : 일관성을 위해 강제성 부여 ( interface 사용 )

@WebServlet(urlPatterns = { "*.fo" })
// web.xml에서 처리 가능하다.
public class Ex02_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ex02_FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청분석
		// 1-1 url 분석 후 요청명 확인(리스트인지, 디테일인지 ,로그인인지 등등)
		// 1-2 한글처리
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
//		URL => http://localhost:8080/web02/test.do
//		URI => /web02/test.do
		uri = uri.substring(uri.lastIndexOf("/"));
		
		// 2. service 처리
		// 2-1 ServiceFactory 에 요청
		// 2-2 uri 를 전달하면 해당 서비스컨트롤러 를 생성해서 인스턴스를 제공
		// 2-3 ServiceFactory 생성 : 허용되지 않는다(싱클턴 패턴 적용으로인해)

		// private으로 외부에서 직접 사용 불가능 : is not visible
		// Ex03_ServiceFactory sf = new Ex02_FrontController();
		
		// 클래스의 메서드를 호출하여 기존에 생성된 인스턴스를 가져올 수 있다 : 예시
		Ex03_ServiceFactory sf = Ex03_ServiceFactory.getInstance();
		// Ex03_ServiceFactory sf1 = Ex03_ServiceFactory.getInstance();
		// Ex03_ServiceFactory sf2 = Ex03_ServiceFactory.getInstance();
		System.out.printf("** 싱글턴 패턴 Test sf=%s, sf1=%s sf2=%s \n",sf);
		
		// 어떤 클래스이든 인터페이스를 상속받도록 해놓으면 자손들 클래스로 가서 각 스트링 뱉는다
		Ex04_Controller controller = sf.getController(uri);
		uri = controller.doUser(request, response);
		
		// 3. view 처리
		request.getRequestDispatcher(uri).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

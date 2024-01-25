package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

//스프링 제공 컨트롤러를 사용(임포트)

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=> @Component를 스프링에서는 역할별로 세분화
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//      -> DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌. 
//		-> interface Controller 의 구현의무가 사라진다.
//		-> 이로인해 메서드 handleRequest() 오버라이딩에 대한 의무도 사라진다
//		-> 즉, 메서드명, 리턴타입(ModelAndView or string or void), 전달받는 매개변수에 자유로워진다.
//		-> 그렇다면 매핑을 해주어야하는데,클래스와 메서드 단위로 매핑해주는 애노테이션 @RequestMapping이 등장한다
//		-> 결국 하나의 컨트롤러 클래스 안에 여러개의 매핑메서드 구현이 가능해진다.
//		-> 그래서 주로 테이블(엔티티)을 작성한다 (MemberConroller.java에 작성)
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//      -> DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

public class C01_mList implements Controller {

	// =
	@Autowired(required = false)
	MemberService service;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
									HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("mlist", service.selectList());
		mv.setViewName("member/memberList");
		return mv;
	}

}

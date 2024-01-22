package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;


// 스프링 제공 컨트롤러를 사용(임포트)
public class C02_mDetail implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		MemberService service = new MemberService();
		ModelAndView mv = new ModelAndView();
		mv.addObject("mdetail", service.selectOne("kso"));
		mv.setViewName("member/memberDetail");
		return mv;
	}

}

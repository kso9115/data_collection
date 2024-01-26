package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=> @Component를 스프링에서는 역할별로 세분화
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//    -> DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌. 
//		-> interface Controller 의 구현의무가 사라진다.
//		-> 이로인해 메서드 handleRequest() 오버라이딩에 대한 의무도 사라진다
//		-> 즉, 메서드명, 리턴타입(ModelAndView or string or void), 전달받는 매개변수에 따라 자유로워진다.
//		-> 그렇다면 매핑을 해주어야하는데,클래스와 메서드 단위로 매핑해주는 애노테이션 @RequestMapping이 등장한다
//		-> 결국 하나의 컨트롤러 클래스 안에 여러개의 매핑메서드 구현이 가능해진다.
//		-> 그래서 주로 테이블(엔티티)을 작성한다 (MemberConroller.java에 작성)
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//    -> DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

@Controller
@RequestMapping(value = "/member") // /member 에 대한 부분을 모두 매핑하므로, 
public class MemberController {

	@Autowired(required = false)
	MemberService service;

	// 리턴타입이 자유롭다고 해서, int를 넣을수는 없음 : 
	// ModelAndView or string or void 타입 사용 가능
	
	// 1. memberList
	@RequestMapping(value = {"/memberList"}, method = RequestMethod.GET)
	public String mList(Model model) {

		model.addAttribute("mlist", service.selectList());
		return "member/memberList";
	}
	
	// 2. memberDetail
	@RequestMapping(value = {"/mdetailsp", "/mdetail"}, method = RequestMethod.GET)
	public String mDetail(Model model) {
		
		model.addAttribute("mdetail", service.selectOne("kso"));
		return "member/memberDetail";
	}

//------------------------------------------------------------------------------------
	
	// 3. login
	// login form을 출력하기 위한 메서드 사용법 2가지
//	@RequestMapping(value = {"/loginForm"}, method = RequestMethod.GET)
//	 version 01 : return string
//	public String loginForm(Model model) {
//		
//		model.addAttribute("loginForm", service.selectOne("kso"));
//		return "member/loginForm";
//	}
//	 version 02 : return void
//	 => viewName 생략 : 요청명과 동일한 viewName을 찾음 
//	 => "/WEB-INF/views/member/loginForm.jsp"로 완성
//	@RequestMapping(value = {"member/loginForm"}, method = RequestMethod.GET)
//	public void loginForm() {
//	} >> 클래스 전체 매핑 전
	@RequestMapping(value = {"loginForm"}, method = RequestMethod.GET)
	public void loginForm() {
	}
	
	// 로그인 화면 처리 : 입력받은 값에 대한 처리
	@RequestMapping(value = "/login", method =RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto) {	// 메세지 처리를 위한 model 변수 사용
		// 포워드 방식으로 home.jsp 사용 시 homeController 에서 전달해주는 내용이 들어가지 않음(시간이라던가..)
		// 매핑 메서드의 인자객체와 동일한 컬럼명의 값은 자동으로 반환
		// 아래의 구문은 필요가 없다
		// String id = request.getParameter("id");
		// dto.setId(id);
		
		// 1. 요청분석
		// String password = request.getparameter("password"); // 비밀번호도 정해줘야하므로 dto에서 가져오기
		// => requst 로 전달되는 id, password 처리: 
	    //    매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
	    //   ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
	    // => 전달된 password 보관
	    String password = dto.getPassword();
	    String uri = "redirect:/home";	// 성공 시 첫 화면 : 홈으로~
	      
	    // 2. 서비스 처리 & 결과 처리
	    // => id 확인 
	    // => 존재하면 Password 확인
	    // => 성공: id, name은 session에 보관, home 으로
	    // => 실패: 재로그인 유도
		dto = service.selectOne(dto.getId());	// 비밀번호도 dto에서 가져오기때문에 미리 위에서 저장해놓아야한다
		if(dto!=null && dto.getPassword().equals(password)) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			//실패
			uri = "member/loginForm";
			model.addAttribute("message", "그런 아이디는 없어요 로그인을 다시하세욤;;");
		}
		return uri;
	}
	
	// 4. logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/";
	}
	
//------------------------------------------------------------------------------------

	// 5. member Detail
	// => 단일 parameter의 경우 @RequestParam("...") 활용
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("jCode") String jCode,HttpSession session, Model model) {
		// 1. 요청 분석
		// => session에 저장된 내 아이디를 확인
	    // => id: session 에서 get
	    // => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별
		//     단, 해당하는 Parameter 가 없으면 400 오류 발생
		//    그러므로 detail 요청에도 ?jCode=D 를 추가함. 
		String id = (String)session.getAttribute("loginID");
		String uri = "member/memberDetail";
		
		// => update 요청 확인 후 uri 수정
		if("U".equals(jCode))
			uri = "member/updateForm";
		// 2. 서비스 처리
		model.addAttribute("mdetail", service.selectOne(id));
		return uri;
	}
	
	// 6. joinForm
	@RequestMapping(value = {"/joinForm"}, method = RequestMethod.GET)
	public void joinForm() {
	}

	// 6. join
	@RequestMapping(value = {"/join"}, method = RequestMethod.POST)
	public String join(Model model, MemberDTO dto) {
		// 1. 요청 분석
		// => 이전 : 한글처리, request 값을 dto에 set하여 진행했으나,
		// => 현재 : 한글은 filter / request처리는 parameter(매개변수)로 자동 처리
		String uri = "member/loginForm";	// 성공 시
		
		
		// 2. 서비스 처리 & 결과
		if(service.insert(dto)>0) {
			//성공
			model.addAttribute("message", " 회원가입 성공, 로그인 하심 됨니당ㅋㅋ ");
		} else {
			//실패 : 재가입 유도
			uri = "member/joinForm";
			model.addAttribute("message", " 회원가입 실패 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}
	
	// 7. update
	@RequestMapping(value = {"/update"}, method = RequestMethod.POST)
	public String update(HttpSession session, Model model, MemberDTO dto) {
		// 1. 요청 분석
		// 성공 시 : 내 정보로
		// 실패 시 : updateForm
		// 즉 dto의 값을 저장해놔야함(detail을 뽑아줄 객체와 = 수정 후 객체가 동일해야한다.)
		// ${requestScope.mdetail} => 내정보에서도 쓰이고 회원정보 수정에서도 수정후 사용됨
		String uri = "member/memberDetail";	// 성공 시
		model.addAttribute("mdetail", dto);
		
		// 2. 서비스 처리 & 결과
		if(service.update(dto)>0) {
			//성공
			model.addAttribute("message", " 정보수정 성공 굳ㅋㅋ ");
			// => name 수정을 했을수도 있으므로 세션에 저장되는 loginName을 수정해줘야한다
			session.setAttribute("loginName", dto.getName());
		} else {
			//실패 : 재가입 유도
			uri = "member/updateForm";
			model.addAttribute("message", " 정보수정 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}

	// 8. delete
	@RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, MemberDTO dto, RedirectAttributes rttr) {
		// 1. 요청 분석
		// => id : session에서 get
		// => delete & session 처리
		String id = (String)session.getAttribute("loginID");
		String uri = "redirect:/home";
		
		// 2. 서비스 처리 & 결과
		if(service.delete(id)>0){
			// 성공 : requestScope 의 message를 redirect 시에도 유지하기 위해서는
			// 		 세션에 보관했다가 사용 후에는 무효화해주어야한다.
			//		 session에 보관 후 redirect 되어진 요청 처리시에 requestScope에 옮기고,
			//		 session의 message는 삭제
			//		 => 이를 처리해주는 API : RedirectAttribute
			rttr.addFlashAttribute("message", " 아이디 삭제 성공 안녕히 가세요");
			session.invalidate();
		} else {
			// 실패
			rttr.addFlashAttribute("message", " 아이디 삭제 실패 어딜 가세요");
		}
		
		return uri;
	}
}

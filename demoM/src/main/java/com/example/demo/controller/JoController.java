package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value = "/jo")
@AllArgsConstructor	// 모든 멤버변수를 초기화하는 생성자
public class JoController {

//	@Autowired(required = false)
	JoService joservice;
//	@Autowired(required = false)	// 조 선택 시 멤버 출력을 위해
	MemberService service;
	

	// 1. joList
//	@RequestMapping(value = { "/joList" }, method = RequestMethod.GET)
//	public String joList(Model model) {
//
//		model.addAttribute("joList", joservice.selectJoList());
//		return "jo/joList";
//	}

	// 1-2. joList : void 선언 시
	@RequestMapping(value = { "/joList" }, method = RequestMethod.GET)
	public void joList(Model model) {
		model.addAttribute("joList", joservice.selectList());
	}

	// 2. joDetail
	@RequestMapping(value = { "/joDetail" }, method = RequestMethod.GET)
	public String joDetail(HttpSession session,Model model, @RequestParam("jo") int jno) {
		
		String uri = "jo/joDetail";
		session.setAttribute("jno", jno);

		if(jno>20) {
			jno -= 20;
			uri = "jo/joDelete";
		} else if(jno>10){
			jno -= 10;
			uri = "jo/joupdateForm";
		} // 쿼리스트링으로 넘겨받아야함
		
		// 조별 멤버 리스트 출력을 위해 memberservice 호출하여 데이터를 jolist 객체에 담아 jsp에 전달
		model.addAttribute("jolist", service.selectJoList(jno));
		// 조별 상세정보 출력을 위해 객체에 담아준 후 jsp에 전달
		model.addAttribute("joDetail", joservice.selectOne(jno));
		return uri;
	}

	// 3-1. joInsert Form
	@RequestMapping(value = {"/joinsertForm"}, method = RequestMethod.GET)
	public void joInsertForm() {
	}
	
	// 3-2. joInsert 처리하기
	@RequestMapping(value = {"/join"}, method = RequestMethod.POST)
	public String joinsert(Model model, JoDTO jdto, RedirectAttributes rttr) {
		// 1. 요청 분석
		// => 이전 : 한글처리, request 값을 dto에 set하여 진행했으나,
		// => 현재 : 한글은 filter / request처리는 parameter(매개변수)로 자동 처리
		String uri = "redirect:joList";	// 성공 시
		
		
		// 2. 서비스 처리 & 결과
		if(joservice.insert(jdto)>0) {
			//성공
			rttr.addFlashAttribute("message", " 조 추가등록 성공ㅋㅋ ");
		} else {
			//실패 : 재가입 유도
			uri = "jo/joinForm";
			rttr.addFlashAttribute("message", " 조 추가 등록 실패;; ");
		}
		return uri;
	}
	
	
	// 4. joUpdate
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String joUpdate(HttpSession session, Model model, JoDTO dto) {
		// 1. 요청 분석
		// 성공 시 : 내 정보로
		// 실패 시 : updateForm
		// 즉 dto의 값을 저장해놔야함(detail을 뽑아줄 객체와 = 수정 후 객체가 동일해야한다.)
		// ${requestScope.mdetail} => 내정보에서도 쓰이고 회원정보 수정에서도 수정후 사용됨
		String uri = "jo/joDetail"; // 성공 시
		model.addAttribute("joDetail", dto);

		// 2. 서비스 처리 & 결과
		if (joservice.update(dto) > 0) {
			// 성공
			model.addAttribute("message", " 정보수정 성공 굳ㅋㅋ ");
			// => name 수정을 했을수도 있으므로 세션에 저장되는 loginName을 수정해줘야한다
			session.setAttribute("loginJName", dto.getJname());
		} else {
			// 실패 : 재가입 유도
			uri = "jo/joupdateForm";
			model.addAttribute("message", " 정보수정 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}
	
	// 5. joDelete
	@RequestMapping(value = {"/delete"}, method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, JoDTO dto, RedirectAttributes rttr) {
		// 1. 요청 분석
		// => id : session에서 get
		// => delete & session 처리
		String uri = "redirect:/home";
		int jno = (int)session.getAttribute("jno");
		
		System.out.println(jno);
		// 2. 서비스 처리 & 결과
		if(joservice.delete(jno)>0){
			// 성공 : requestScope 의 message를 redirect 시에도 유지하기 위해서는
			// 		 세션에 보관했다가 사용 후에는 무효화해주어야한다.
			//		 session에 보관 후 redirect 되어진 요청 처리시에 requestScope에 옮기고,
			//		 session의 message는 삭제
			//		 => 이를 처리해주는 API : RedirectAttribute
			session.getAttribute("joList");
			rttr.addFlashAttribute("message", " 조 삭제 성공 안녕히 가세요");
			session.invalidate();
		} else {
			// 실패
			rttr.addFlashAttribute("message", " 조 삭제 실패 어딜 가세요");
		}
		return uri;
	}//joDelete
	
	// 6. join해서 captain 이름까지 출력하기
}

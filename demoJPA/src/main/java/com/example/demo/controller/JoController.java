package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/jo")
@AllArgsConstructor // 모든 멤버변수를 초기화하는 생성자
public class JoController {

	JoService joservice;
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
	@GetMapping("/joDetail")
	public String joDetail(HttpSession session, Model model, Jo entity,
							@RequestParam("jo") int jno,
							@RequestParam("jCode") String jCode) {

		String uri = "jo/joDetail";
		session.setAttribute("jno", jno);

		if ("U".equals(jCode)) {
			uri = "jo/joupdateForm";
		} else {
			model.addAttribute("joList", service.findByJno(jno));	// 멤버서비스
		}
		// 조별 멤버 리스트 출력을 위해 memberservice 호출하여 데이터를 jolist 객체에 담아 jsp에 전달
		// model.addAttribute("jolist", service.selectList(jno));
		// 조별 상세정보 출력을 위해 객체에 담아준 후 jsp에 전달
		model.addAttribute("joDetail", joservice.selectOne(jno));
		return uri;
	}

	// 3-1. joInsert Form
	@GetMapping("/joinsertForm")
	public void joInsertForm() {
	}

	// 3-2. joInsert 처리하기
	@PostMapping("/join")
	public String joinsert(Model model, Jo entity, RedirectAttributes rttr) {
		// 1. 요청 분석
		// => 이전 : 한글처리, request 값을 dto에 set하여 진행했으나,
		// => 현재 : 한글은 filter / request처리는 parameter(매개변수)로 자동 처리
		String uri = "redirect:joList"; // 성공 시

		// 2. 서비스 처리 & 결과
		try {
			log.info("** Jo Insert 성공 => \n" + joservice.save(entity));
			rttr.addFlashAttribute("message", " 조 추가등록 성공ㅋㅋ ");

		} catch (Exception e) {
			// 실패 : 재가입 유도
			uri = "jo/joinForm";
			log.info("** Jo Insert 실패 => \n" + e.toString());
			rttr.addFlashAttribute("message", " 조 추가 등록 실패;; ");
		}

		return uri;
	}

	// 4. joUpdate
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String joUpdate(HttpSession session, Model model, Jo entity) {
		// 1. 요청 분석
		// 성공 시 : 내 정보로
		// 실패 시 : updateForm
		// 즉 dto의 값을 저장해놔야함(detail을 뽑아줄 객체와 = 수정 후 객체가 동일해야한다.)
		// ${requestScope.mdetail} => 내정보에서도 쓰이고 회원정보 수정에서도 수정후 사용됨
		String uri = "jo/joDetail"; // 성공 시
		model.addAttribute("joDetail", entity);

		// 2. 서비스 처리 & 결과

		try {
			log.info("** Jo Insert 성공 => \n" + joservice.save(entity));
			model.addAttribute("message", " 정보수정 성공 굳ㅋㅋ ");
			session.setAttribute("loginJName", entity.getJname());
		} catch (Exception e) {
			// 실패 : 재가입 유도
			uri = "jo/joupdateForm";
			log.info("** Jo Update 실패 => \n" + e.toString());
			model.addAttribute("message", " 정보수정 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}

	// 5. joDelete
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model, RedirectAttributes rttr, @RequestParam("jo") int jno) {
		// 1. 요청 분석
		// => id : session에서 get
		// => delete & session 처리
		String uri = "redirect:/home";

		log.info("~~~~~~~~~~~~~~~~~~~" + jno);
		// 2. 서비스 처리 & 결과
		try {
			joservice.deleteByJno(jno);
			log.info("** Jo Delete 성공 => \n");

			session.getAttribute("jolist");
			rttr.addFlashAttribute("message", " 조 삭제 성공 안녕히 가세요");
			session.invalidate();

		} catch (Exception e) {
			// 실패
			rttr.addFlashAttribute("message", " 조 삭제 실패 어딜 가세요");
		}
		return uri;
	}// joDelete

	// 6. join해서 captain 이름까지 출력하기
}

package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.JoService;

@Controller
@RequestMapping(value = "/jo")
public class JoController {

	@Autowired(required = false)
	JoService joservice;

	// 1. joList
	@RequestMapping(value = { "/joList" }, method = RequestMethod.GET)
	public String joList(Model model) {

		model.addAttribute("joList", joservice.selectJoList());
		return "jo/joList";
	}

	// 1-2. joList : void 선언 시
//	@RequestMapping(value = { "/joList" }, method = RequestMethod.GET)
//	public void joList(Model model) {
//		model.addAttribute("joList", joservice.selectJoList());
//	}

	// 2. joDetail
	@RequestMapping(value = { "/joDetail" }, method = RequestMethod.GET)
	public String joDetail(Model model, @RequestParam("jo") int jno) {
		
		String uri = "jo/joDetail";
		if(jno>10) {
			jno -= 10;
			uri = "jo/joupdateForm";
		}
		// 쿼리스트링으로 넘겨받아야함
		model.addAttribute("joDetail", joservice.selectJoOne(jno));
		return uri;
	}

	// 3-1. joInsert Form
	@RequestMapping(value = {"/joinsertForm"}, method = RequestMethod.GET)
	public void joInsertForm() {
	}
	
	// 3-2. joInsert 처리하기
	@RequestMapping(value = {"/join"}, method = RequestMethod.POST)
	public String joinsert(Model model, JoDTO jdto) {
		// 1. 요청 분석
		// => 이전 : 한글처리, request 값을 dto에 set하여 진행했으나,
		// => 현재 : 한글은 filter / request처리는 parameter(매개변수)로 자동 처리
		String uri = "redirect:joList";	// 성공 시
		
		
		// 2. 서비스 처리 & 결과
		if(joservice.joInsert(jdto)>0) {
			//성공
			model.addAttribute("message", " 조 추가등록 성공ㅋㅋ ");
		} else {
			//실패 : 재가입 유도
			uri = "jo/joinForm";
			model.addAttribute("message", " 조 추가 등록 실패;; ");
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
		if (joservice.joUpdate(dto) > 0) {
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
}

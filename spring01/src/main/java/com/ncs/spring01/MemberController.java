package com.ncs.spring01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.MemberService;

@Controller
public class MemberController {

	@Autowired(required = false)
	MemberService service;

	// 리턴타입이 자유롭다고 해서, int를 넣을수는 없음 : 
	// ModelAndView or string or void 타입 사용 가능
	
	// 1. memberList
	@RequestMapping(value = {"/mlistsp", "/mlist"}, method = RequestMethod.GET)
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
}

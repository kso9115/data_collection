package com.ncs.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor	// board service를 주입받기 위해서(autowired 대신 전체 필드 초기화하는 법)
@RequestMapping("/board") // 인자 하나만 전달하게되면 경로임 => value 안써도됨
public class BoardController {
	
	BoardService service;
	
	// 1. Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("blist", service.selectList());
	}//boardList
	
	// 2. Board Detail
	@GetMapping("/detail")
	public String boardDetail(Model model, 
			@RequestParam("jCode") String jcode,
			@RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		
		if("U".equals(jcode)) uri ="board/boardUpdate";
		model.addAttribute("seqContent", service.selectOne(seq));
		
		return uri;
	}//boardDetail
	
	// 3. Board Update
	@PostMapping("/update")
	public String boardUpdate(Model model,BoardDTO dto,@RequestParam("seq") int seq) {
		String uri = "board/boardDetail";	// 성공 시
		model.addAttribute("seqContent", dto);
		
		if(service.update(dto)>0) {
			model.addAttribute("message", "글 수정 성공하였슴다.");
		} else {
			uri = "board/boardUpdate";
			model.addAttribute("message", "글 수정 실패하였슴다.");
		}
		return uri;
	}
}

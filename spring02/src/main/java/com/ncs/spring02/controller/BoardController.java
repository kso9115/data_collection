package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
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
	
	// => 글요청 처리 중, 글을 읽기전
	// => 조회수 증가 처리
	//	  -> 세션에 저장된 아이디와 board의 아이디가 다른 경우(조건) 조회수 카운팅
	// 2. Board Detail
	@GetMapping("/detail")
	public String boardDetail(Model model, HttpSession session,
			@RequestParam("jCode") String jcode,
			@RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		
		
		if("X".equals(jcode)) uri="board/boardDelete";	// 파라미터를 X로 받을때는 delete로 이동
		
		// 사실은 seqContent 객체에 담기 전에 조회수를 증가시켜야하는지 아닌지에 대한 결과를 보관해야한다.
		// 조회수 증가 : selectOne의 결과를 보관해야한다(세션에 저장된 아이디와 board의 아이디가 다른 경우)
		BoardDTO dto = service.selectOne(seq);
		if("U".equals(jcode)) uri ="board/boardUpdate"; // 업데이트로의 이동 방어를 위해 else if문 사용
		else if(!dto.getId().equals((String)session.getAttribute("loginID"))) {	// 조회수 증가 조건 만족
			// 자바코드상에
			dto.setCnt(dto.getCnt()+1);
			service.update(dto);
			
		}
		model.addAttribute("seqContent", dto);
		return uri;
	}//boardDetail
	
	// 3-1. Board insert form으로 이동
	@GetMapping("/boardInsert")
	public void boardInsert() {
	}
	
	// 3-2. Board insert
	@PostMapping("/insert")
	public String boardinsert(RedirectAttributes rttr, Model model,BoardDTO dto) {
		String uri = "redirect:boardList";
		
		if(service.insert(dto)>0) {
			rttr.addFlashAttribute("message"," 글 추가 완료~!");
		} else {
			uri = "board/boardInsert";
			rttr.addFlashAttribute("message"," 글 추가 실패~!");
		}
		return uri;
	}
	
	// 4. Board Update
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
	
	// 5. Board delete
	@PostMapping("/delete")
	public String boardDelete(HttpSession session,BoardDTO dto,Model model, 
			@RequestParam("seq") int seq,
			RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		
		System.out.println(dto);
		if(service.delete(dto)>0) {
			rttr.addFlashAttribute("message"," 글 삭제 완료~!");
		} else {
			uri = "board/boardDetail";
			rttr.addFlashAttribute("message"," 글 삭제 실패ㅠ.ㅠ ");
		}
		return uri;
	}
	
	// 6. Reply Insert Form으로의 이동
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {	// boardDTO dto에 파라미터 저장
		// 답글 처리를 위해 부모글의 root, step, indent를 인자로 전달받게되면,
		// 이 인자에 담겨진 값은 requestScope과 동일하다
		// 즉, response 실행(전송) 전까지는 서버에서 사용이 가능하다는 것(jsp에서 사용~!)
		// 사용 시 객체명의 첫 글자를 소문자로 하여 사용할 수 있다. => ${boardDTO.xxxx}
	}
	
	// 7. Reply Insert Form 실행을 위한 코드
	// => 메서드명과 요청명이 위의 메서드와 동일하나,
	// => Post 요청으고, 인자가 다르기 때문에 허용된다.
	@PostMapping("/replyInsert")
	public String replyInsert(Model model,BoardDTO dto,RedirectAttributes rttr) {
		// 성공 시 : boardList
		// 실패 시 : replyInsert로 되돌아가여 재입력 유도
		String uri = "redirect:boardList";	// 변경된 데이터가 입력된 보드리스트로 가야하니까
		
		// => 전달된 데이터값 확인 : dto 값 확인
		// 	-> id,title,content : 사용 가능
		// 	-> 부모글의 root : 사용가능
		// 	-> 부모글의 step : 기존데이터+1(1씩증가)
		// 	-> 부모글의 indent : 기존데이터+1(1씩증가)
		// => SQL 처리 :
		//	-> replyInsert, step의 Update 
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		if(service.rinsert(dto)>0) {
			rttr.addFlashAttribute("message", "답글 등록 성공! c(   'o')っ");
		} else {
			uri = "board/replyInsert";
			rttr.addFlashAttribute("message", "답글 등록 실패 ( ✋˙࿁˙ )");
			
		}
		return uri;
	}//replyInsert
	
}

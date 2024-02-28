package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.service.GuestbookService;

import lombok.AllArgsConstructor;

//** spring boot 에서는 @Log4j2 사용

@Controller
@AllArgsConstructor
public class HomeController {
	
	GuestbookService service;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/home")
	// @GetMapping(value={"/", "/home"})
	// => void : 요청명.jsp 를 viewName 으로 처리함 (home.jsp)
	// 그러므로 "/" 요청은 .jsp 를 viewName 으로 찾게됨(주의)
	// => Boot 의 매핑메서드 에서 "/" 요청은 적용안됨(무시됨)
	// WebMvcConfig 의 addViewControllers 메서드로 해결
	public void home(Locale locale, Model model, @ModelAttribute("message") String message) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

	}
	
	@GetMapping("/axtestform")
	public String axTestForm() {
		return "axTest/axTestForm";
	}
	
	@GetMapping("/ginsert")
	public String ginsert() { // 홈화면이 계속해서 떠야하니 string 타입으로
		
		GuestbookDTO dto = GuestbookDTO.builder()
										.title("JPA Insert Test 입니다!")
										.content("서비스의 register 메서드를 호출하여 지금 이 dto를 넣어버림다")
										.writer("kso1").build();
		
		// 서비스의 register를 호출해주면 됨!! 데이터를 넣어야하니껜!!
		System.out.println("** guest Insert(ginsert) => "+service.register(dto));
		return "redirect:home";
	}//ginsert
	
	@GetMapping("/glist")
	public String glist() {
		
		List<Guestbook> list = service.selectList();
		for(Guestbook g:list) {
			// 부모의 regDate & modDate까지 출력하고 싶을때
			System.out.println(g+",regDate ="+g.getRegDate()+", modDate="+g.getModDate());
//			System.out.println("** guest Insert => "+g);
		}
		
		return "redirect:home";
	}//glist
	
	@GetMapping("/gupdate")	// 수정을 위한 dto
	public String gupdate() { 
		
		GuestbookDTO dto = GuestbookDTO.builder()
										.gno(4L)
										.title("JPA Update Test 입니다!")
										.content("과연 정상적으로 수정이 될지?")
										.writer("admin").build();
		
		// 서비스의 register를 호출해주면 됨!! 데이터를 넣어야하니껜!!
		System.out.println("** guest Update(gupdate) => "+service.register(dto));
		return "redirect:home";
	}//gupdate
	
	// 쿼리스트링으로 Test : /gdetail?gno=2
	@GetMapping("/gdetail")
	public String gdetail(Long gno) {
		System.out.println("** guest Detail 성공(gdetail)=> "+service.selectOne(gno));
		return "redirect:home";
	}
	
	
	// 쿼리스트링으로 Test : /gdelete?gno=2
	@GetMapping("/gdelete")
	public String gdelete(Long gno) {
		try {
			service.delete(gno);
			System.out.println("** guest Delete 성공(gdelete)=> "+gno);
			
		} catch (Exception e) {
			System.out.println("** guest Delete => "+e.toString());
		}
		
		return "redirect:home";
	}
	
	// JPA Paging & Sort
	@GetMapping("/gpage")
	public String gpage() {
		PageRequestDTO requestDTO = PageRequestDTO.builder()
												.page(1)
												.size(5).build();
							// 출력할 pageNo, Page 당 출력할 row 갯수 입력
		
		// 최종 결과물을 PageResultDTO이기때문에 해당 클래스로 보내서 받아와야함
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.pageList(requestDTO);
		
		System.out.println("** Page List => "+requestDTO.getPage());
		
		System.out.println("이것은 resultDTO의 주소값을 보기 위함이다@@!!"+resultDTO);
		// PageResultDTO에 있는 DTO List를 가져와야한다
		for(GuestbookDTO g:resultDTO.getDtoList()) {
			System.out.println(g);
		}
		return "redirect:home";
	}
	
}

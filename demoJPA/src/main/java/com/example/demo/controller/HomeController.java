package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//** spring boot 에서는 @Log4j2 사용

@Controller
public class HomeController {

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
}

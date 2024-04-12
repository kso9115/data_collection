package com.example.demo.securityLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.UserDTO;
import com.example.demo.jwtToken.TokenProvider;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private TokenProvider tokenProvider;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
							HttpServletResponse response,
							Authentication authentication) 
							throws IOException, ServletException {
		
		log.info("** LoginSuccessHandler => "+authentication);

	    MemberDTO memberDTO = (MemberDTO)authentication.getPrincipal();

	    // => 로그인 성공 : 토큰생성
		final String token = tokenProvider.create(memberDTO.getId());
		final UserDTO userDTO = UserDTO.builder()
				.token(token)
				.id(memberDTO.getId())
				.username(memberDTO.getName())
				.build();
		log.info("login 성공 token = "+token);

		//=> response 에 직접 성공 결과를 담아 프론트로 보냄
	    Gson gson = new Gson();
	    String jsonStr = gson.toJson(userDTO);

	    response.setContentType("application/json; charset=UTF-8");
	    PrintWriter printWriter = response.getWriter();
	    printWriter.println(jsonStr);
	    printWriter.close();

	  } //onAuthenticationSuccess
} //class

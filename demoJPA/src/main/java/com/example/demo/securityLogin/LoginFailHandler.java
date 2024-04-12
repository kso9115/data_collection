package com.example.demo.securityLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, 
			  				HttpServletResponse response,
			  				AuthenticationException exception) 
			  				throws IOException, ServletException {
		
		// => 로그인 실패시 아래 Exception 발생
		//	~~~security.authentication.BadCredentialsException: 자격 증명에 실패하였습니다.
		log.info("** Login fail..... => " + exception);
		
		//=> response 에 직접 결과(실패)를 담아 프론트로 보냄
	    Gson gson = new Gson();
	    String jsonStr = gson.toJson(Map.of("error", "Login failed."));

	    response.setContentType("application/json");
	    response.setStatus(502);  // HttpStatus.BAD_GATEWAY : 502
	    PrintWriter printWriter = response.getWriter();
	    printWriter.println(jsonStr);
	    printWriter.close();    
	  } //onAuthenticationFailure
} //class

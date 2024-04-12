package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.UserDTO;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.jwtToken.TokenProvider;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

// ** MemberController_Rest
// => SpringBoot JPA , React 사용, 계층적 uri 적용

//@CrossOrigin(origins = "http://localhost:3000")
// => Test용
@RestController
@RequestMapping("/user")
@Log4j2  //@Log4j -> Boot 에서는 2015년 이후 지원중단
@AllArgsConstructor // 모든 맴버변수 생성자 주입하므로 각각 @Autowired 할 필요없음
public class UserController {
	
	private MemberService service;
	private PasswordEncoder passwordEncoder;
	private TokenProvider tokenProvider;
	private BoardService bservice;
	
    // ** 로그인 확인 
    // => Session 체크해서 react state값 유지
	// => Session 객체는 각 User별로 관리됨 
    @GetMapping("/check-server")
    public ResponseEntity<?> checkLogin(HttpSession session) {
    	log.info("** React_SpringBoot Connection 확인 중 **");
        return ResponseEntity.ok()
        		.body(Map.of("checkLogin", "확인하지않음",
        					 "checkData", "** ** Server 연결 성공, Port:8080 **" 	
        				));
        // => Map.of()
        //	- java 9 버전 부터 추가, 간편하게 초기화 가능
        //	  map.put(1, "sangwoo kang"); map.put(2, "james kang"); put(3, "stef you");
        //	  -> Map.of(key_1, "Value_sangwoo kang",
        //        		2, "james kang",
        //        		3, "stef you" )
        //	- 그러나 10개 까지만 초기화 가능 (10개 이상은 ofEntries() 사용)
        //	- unmodifiable(수정불가능) map을 리턴하므로 초기화후 수정불가능 (Immutable 객체)
        //	- 초기화 이후에 조회만 하는경우 주로사용함.(Key 관리 등)
    }
	
    // ** 로그인
    // => Token 적용후, 스프링시큐리티 인증.인가 이전 
    @PostMapping(value="/login", consumes="application/json;"  
								, produces="application/json;")  
	public ResponseEntity<?> login(HttpSession session, @RequestBody Member entity) {	
		// => Login 성공 : status OK & 토큰생성 & userDTO return
		//          오류 : status Error & ResponseDTO 이용 Exception_Message  
		log.info("** login Data 전달 확인=> "+entity);
		
		// 1) 입력받은 Password 보관
		String password=entity.getPassword();
		
		// 2) Service 실행
		// => id 일치 &  Password 확인
		entity = service.getWithRoles(entity.getId());
		
    	if ( entity !=null && 
   			 passwordEncoder.matches(password, entity.getPassword()) ) {	
   			// => 로그인 성공
    		//	-> 로그인정보 session 보관 (추후 session Test)  
    		//	-> token 사용하면 요청정보에 userID 가 늘 전달되기 때문에 
    		//	   session에 반드시 보관할 필요는 없지만 Test 를 위해 보관함.   
   			session.setAttribute("loginID", entity.getId());
   			session.setAttribute("loginName", entity.getName());
   			log.info("*** login: session 보관 loginID = "+session.getAttribute("loginID"));
   			// => 현재 메서드에서는 session 값이 인식되지만 이후의 요청에서는 session 값은 유지되지 않을수 있음
   			//	  ( SecurityConfig 클래스의 sessionCreationPolicy_세션 정책 에 따름 )	
    	 
   			// => 로그인 성공 : 토큰생성
   			final String token = tokenProvider.createToken(entity.claimList());
			final UserDTO userDTO = UserDTO.builder()
					.token(token)
					.id(entity.getId())
					.username(entity.getName())
					.roleList(entity.getRoleList())
					.build();
			log.info("login 성공 token = "+token);
			return ResponseEntity.ok().body(userDTO);
		}else {
			// 로그인 실패 (id, pssword 오류 구분하지 않음)
			return ResponseEntity
					.status(HttpStatus.BAD_GATEWAY)  //502
					.body("Login failed.");
		}
	} //login
    
 /* => Token 적용전
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Member entity,  HttpSession session, Model model){

		// 1. 요청분석
    	String password = entity.getPassword();
		// 2. 서비스 처리
    	entity = service.selectOne(entity.getId());
    	log.info("login newEntity => "+entity);
		if ( entity !=null && 
			 passwordEncoder.matches(password, entity.getPassword()) ) {	
			// => 로그인 성공: 로그인 정보 session 보관 & Front로 전송  
			session.setAttribute("loginID", entity.getId());
			session.setAttribute("loginName", entity.getName());
			
			// => response 로 전송할 객체(UserDTO) 생성
			//    빌더 패턴적용, userDTO 의 값 변경 방지를 위해 final 사용.	
			final UserDTO userDTO = UserDTO.builder()
								.id(entity.getId())
								.username(entity.getName())
								.build();
			log.info("~~ 로그인 성공, HttpStatus.OK => "+HttpStatus.OK);
			return ResponseEntity.ok(userDTO);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("~~ id 또는 password 오류 ~~");
			// => HttpStatus.UNAUTHORIZED: 401 (메서드내부 오류에서는 500대 오류코드 사용바람직) 
		}
    } //login
	*/
    
    // ** BoardList
  	@GetMapping("/boardlist")
  	public ResponseEntity<?> boardlist() {
  		List<Board> list = bservice.selectList();
  		
     	if ( list !=null && list.size() > 0 ) {	
 			return ResponseEntity.ok().body(list);
 		}else {
 			log.info("** boardlist NotFound **");
 			return ResponseEntity
 					.status(HttpStatus.BAD_GATEWAY) 
 					.body("boardlist NotFound");
 		}
  	} //boardlist

} //class

package com.example.demo.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.JoDTO;
import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.UserDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

//** @RestController
//=> 스프링4 부터 추가됨,
//  	Controller의 모든 매핑메서드 리턴타입을 기존과 다르게 처리함을 명시
// 		viewPage가 아닌 Data를 다양한 Type으로 return 하며,
//  	이들을 JSON이나 XML로 자동으로 처리함.(클라이언트에서 인식 가능 하도록)
//=> @ResponseBody 애너테이션을 생략해도 
// xhr, ajax, fetch, axios(리액트) 등의 비동기 요청에 Data로 응답을 해줄수 있음.
// (@Controller 사용 시 Data를 return할 때 사용하는 애노테이션임 => @ResponseBody)
//=> Return 데이터 Type
// - String, Integer 등의 단일값
// - 사용자 정의 객체
// - Collection
// - ResponseEntity<> 타입 : 주로 이용됨

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//*** Method Mapping
//=> GET  : @GetMapping , SELECT
//=> POST : @PostMapping , INSERT
//=> PUT  : @PutMapping , UPDATE (Key 외에 전체변경시)
//=> PATCH: @PatchMapping , UPDATE (부분 변경시)
//=> DELETE: @DeleteMapping , DELETE

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//** status 
//=> https://ko.wikipedia.org/wiki/HTTP_상태코드
//1xx (메시지정보): 요청을 받았으며 프로세스를 계속한다
//2xx (요청성공): 요청을 성공적으로 받았으며 인식했고 수용하였다
//3xx (리다이렉션): 요청 완료를 위해 추가 작업 조치가 필요하다
//4xx (클라이언트 오류): 요청의 문법이 잘못되었거나 요청을 처리할 수 없다
//5xx (서버 오류): 서버가 명백히 유효한 요청에 대해 충족을 실패했다

//400: Bad request (사용자의 잘못된 요청을 처리할 수 없음)
//401: Unauthorized (허가_승인 되지 않음, 권한 없음) 
//403: Forbidden (금지된, 접근금지, 서버에 요청이 전달되었지만, 권한 때문에 거절되었음을 의미)
//( 401은 익명의 사용자, 403는 로그인은 하였으나 권한이 없는 사용자, 
// HTTP1.1 에서는 이 둘을 명확하게 구분하지 않고 Web API의 속성은 대부분 401을 내보낸다고 하지만,
//      401은 익명의 사용자, 403는 로그인은 하였으나 권한이 없는 사용자로 구분 가능하다.
// 즉 로그인전 접근시에는 401 , 로그인후 접근시는 403 ) 
//415: 지원되지 않는 미디어 유형 (요청이 요청한 페이지에서 지원하지않는 형식으로 되어있음.)

//404: Not found (요청한 페이지 없음)
//405: Method not allowed (허용되지 않는 http method 사용함)
//500: Internal server error (내부 서버 오류)
//501: Not implemented (웹 서버가 처리할 수 없음)
//503: Service unavailable (서비스 제공 불가)
//504: Gateway timeout (게이트웨이 시간초과)
//505: HTTP version not supported (해당 http 버전 지원되지 않음)
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//*** JSON 제이슨, (JavaScript Object Notation) **********
//=> 자바스크립트의 객체표기법으로, 브라우저와 서버사이에서 데이터를 전달할때 사용하는 표준형식.
//  속성(key) 과 값(value) 이 하나의 쌍을 이룸
//=> JSON의 파일 확장자는 .json
//=> 주요 메서드
// - JSON.stringify() : JavaScript 값이나 객체를 JSON 문자열(문자Type)로 변환.
// - JSON.parse() :  JSON 문자열을 구문분석하여 JavaScript 값이나 객체를 생성함.

//** JAVA의 Data 객체 -> JSON 변환하기
//** 참고용어 
//=> 마샬링(Marshalling)
// - 메모리상에 형상화된 객체 데이터를 다른 데이터 형태로 변환하는 과정을 말함.
// - JAVA 객체를 JSON 포맷으로 변환하는것
//=> 언마샬링(UnMarshalling)
// - 변환된 데이터를 다시 원래의 객체 모양으로 복원하는 작업
// - JSON 포맷을 JAVA 객체로 변환하는것
//=> 직렬화(Serialization)
// - 객체 데이터를 일련의 byte stream으로 변환하는 작업
// - 반대로 일련의 byte stream을 본래 객체 모양으로 복원하는 작업은 역직렬화(Deserialization) 
// - 직렬화와 마샬링은 거의 같은개념이지만, 직렬화 작업이 프로그래밍적으로 보다더 전문화 된것이 마샬링.
// ( 즉, 직렬화는 마샬링이 포함된 폭넓은 개념 )

//1) GSON
// : 자바 객체의 직렬화/역직렬화를 도와주는 라이브러리 (구글에서 만듦)
// 즉, JAVA객체 -> JSON 또는 JSON -> JAVA객체

//2) @ResponseBody (매핑 메서드에 적용)
// : 메서드의 리턴값이 View 를 통해 출력되지 않고 HTTP Response Body 에 직접 쓰여지게 됨.
// 이때 쓰여지기전, 리턴되는 데이터 타입에 따라 종류별 MessageConverter에서 변환이 이뤄진다.
// MappingJacksonHttpMessageConverter 를 사용하면 request, response 를 JSON 으로 변환
// view (~.jsp) 가 아닌 Data 자체를 전달하기위한 용도
// @JsonIgnore : VO 에 적용하면 변환에서 제외

//3) jsonView
//=> Spring 에서 MappingJackson2JsonView를 사용해서
//  ModelAndView를 json 형식으로 반환해 준다.
//=> 방법
// -> pom dependency추가
// -> 설정화일 xml 에 bean 등록 
// ( 안하면 /WEB-INF/views/jsonView.jsp 를 찾게되고  없으니 404 발생 )
// -> return할 ModelAndView 생성시 View_Name을 "jsonView"로 설정
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   

@RestController
@RequestMapping("/rest")
@Log4j2 // spring boot에서는 Log4j2 사용
@AllArgsConstructor
public class RESTController {

	MemberService service;
	JoService jservice;
	PasswordEncoder passwordEncoder; // 비밀번호 확인을 위한 객체 생성 호출 => DemoConfig 주입
	BoardService bservice;

	// hello 리턴을 위한 메서드 작성
	@GetMapping("/hello")
	// 메뉴없이 직접 요청 : http://localhost:8080/spring02/rest/hello
	// return 한 string 값이 response에 담겨져 전송되었으며, 출력되는 것
	public String hello() {
		log.info("** Rest API Test **");
		return "Hello Spring MVC Rest API";
	}

	// ** RESTController의 다양한 return Type
	// 1) Text Return
	// http://localhost:8080/spring02/rest/gettext

	// plain -> 기본문서임을 가르킨다
	// => produces 속성
	// - 해당 메서드 결과물의 MIME Type을 의미 ( UI Content-Type 에 표시됨 )
	// - 위처럼 문자열로 직접 지정 할수도 있고, 메서드내의 MediaType 클래스를 이용할 수도 있음
	// - 필수속성은 아님 ( 기본값은 text/html, 그러므로 적용하지 않은 경우 아래 <h1></h1> 적용됨 )
//	@GetMapping(value="/gettext", produces="text/plain; charset=UTF-8")
	@GetMapping("/gettext")
	public String getText() {
		log.info("** MIME Type MediaType 클래스 적용 => " + MediaType.TEXT_PLAIN_VALUE);
		return "<h2>안녕하세요? Spring MVC Rest API";
	}

	// ** 객체 주의사항
	// => Java 의 객체를 UI 가 인식가능한 형태의 객체로 변환후 전송
	// => xml 또는 JSON 포맷
	// => 즉, Java <-> JSON 변환을 지원하는 API 필요함
	// 여기부터는 pom 에 dependency 추가 해야함

	// 2) 사용자 정의 객체
	// 2-1) 객체 return1 : produces 속성을 지칭하는 경우
	// http://localhost:8080/spring02/rest/getDTO1
	@GetMapping(value = "/getDTO1", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public JoDTO getDTO1() {
		// => produces
		// - JSON 과 XML 방식의 데이터를 생성할 수 있도록 설정
		// - Response Data Type을 제한 함으로 오류를 줄임
		// - 입력값을 제한할때는 "consumes" 속성 사용
		// => 요청 url의 확장자에 따라 다른 타입으로 서비스
		// - Test1) 브라우져에서 /rest/getdto1 호출 -> 위 둘중 XML 전송(default)
		// - Test2) 브라우져에서 /rest/getdto1.json 호출 -> JSON 전송
		// 단, SpringBoot 에서는 요청Data가 없는 경우 Test 불가함.
		// produces 속성 지정하지 않은 getDTO2 만 정상적으로 실행됨.
		return new JoDTO(9, "Rest조", "restTest", "Rest API 테스트", "레스트 API 화이팅", "레스트");
	}

	// 2-2) 객체 return1 : produces 속성을 지칭하지 않는 경우 => xml
	// getDTO2.json => json 출력
	@GetMapping("/getDTO2")
	public JoDTO getDTO2() {
		return new JoDTO(99, "Rest2조", "restTest2", "Rest API 테스트2", "레스트 API 화이팅", "레스트");
	}

	// 3) Collection return
	// 3-1) Map
	// => XML로 Return하는 경우 Key값 주의 (변수명 규칙)
	// UI(브라우져) 에서 Tag명이 되므로 반드시 문자로 한다.
	// ( 첫글자 숫자, 특수문자 모두 안됨 주의, 단 json Type 은 무관함 )
	// -> 222, -Second, 2nd, ..... 등등, 그러나 한글은 허용
	// -> This page contains the following errors:
	// error on line 1 at column 109: StartTag: invalid element name...
	// => rest/getmap , rest/getmap.json 모두 Test
	// => map 은 출력 순서 무관
	@GetMapping("/getmap")
	public Map<String, JoDTO> getMap() {
		Map<String, JoDTO> map = new HashMap<String, JoDTO>();
		map.put("one", new JoDTO(1, "Rest1조", "CollectionTest", "Rest API 테스트", "레스트 API 화이팅", "레스트"));
		map.put("two", new JoDTO(2, "Rest2조", "CollectionTest", "Rest API 테스트", "레스트 API 화이팅", "레스트"));
		map.put("삼3", new JoDTO(3, "Rest3조", "CollectionTest", "Rest API 테스트", "레스트 API 화이팅", "레스트"));
		map.put("4사", new JoDTO(4, "Rest4조", "CollectionTest", "Rest API 테스트", "레스트 API 화이팅", "레스트"));
		// 키값이 태그가 된다 => 컬럼명(숫자로 시작하면 안된다.)
		return map;
	}

	// 3-2) List
	@GetMapping("/getlist")
	public List<JoDTO> getList() {
		return jservice.selectList();
	}

	// ResonseEntity

	// ** Parameter 를 쿼리스트링으로 전달하는 경우 서버에서 처리방법
	// 1) params 속성으로 처리
	// - URL Query_String Param Parsing, "key=value" 형식으로 전달된 파라미터 매핑

	// 2) @RequestParam 으로 처리
	// - @RequestParam("jno") int jno -> Spring02의 MemberController, /dnload 참고
	// => params 와 @RequestParam 비교 해보세요.
	// parameter 오류시 400
	// - params : Parameter conditions "jno, id" not met for actual request
	// parameters: jno2={11}, id={banana}
	// - @RequestParam : Required request parameter 'jno' for method parameter type
	// int is not present
	// ( Mapper interface의 @Param 과는 구별 )

	// 3) @PathVariable
	// 4) @RequestBody

//	 ** params 속성
//	 => 값에 상관없이 파라미터에 params 속성으로 정의한 "jno", "id" 이 반드시 있어야 호출됨
//	 만약 하나라도 전달받지 못하면 "400–잘못된 요청" 오류 발생
//	 => Parameter name 과 매개변수는 이름으로 매핑함. (즉, 같아야함)
//	 => Spring02 의 MemberController의 상단 주석 params 참고

	// 4) ResponseEntity Test
	// => 실습
	// 전달된 jno값의 조건에 의하여 502(BAD_GATEWAY) 또는 200(OK) 상태코드와 데이터를 함께 전송하므로
	// 요청 User가 이 응답결과(body값)의 정상/비정상 여부를 알수있도록 해준다
	// => 200 Test: http://localhost:8080/spring02/rest/incheck?jno=4&captain=kso1
	// http://localhost:8080/spring02/rest/incheck.json?jno=4&captain=kso1
	// => 502 Test: http://localhost:8080/spring02/rest/incheck?jno=5&captain=kso1

	// 4-1) incheck1 : params 테스트
	@GetMapping(value = "/incheck", params = { "jno", "captain" }) // 정확하게 파라미터가 존재하는 경우에만 받겠다(다중으로 받을경우 배열형태로 받음)
	public ResponseEntity<JoDTO> inCheck(int jno, String captain) {
		// 1) 준비
		ResponseEntity<JoDTO> result = null;
		JoDTO dto = new JoDTO();
		dto.setJno(jno);

		// 2) Service & return
		// => jno로 selectOne, 성공 시 captain의 값 비교해서 일치하면(데이터 찾으면) 성공, 아니면 오류라고 가정
		dto = jservice.selectOne(jno);
		if (dto != null && dto.getCaptain().equals(captain)) {
			// 성공
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info("** incheck Test HttpStatus.OK => " + HttpStatus.OK);
			log.info("** incheck Test dto => " + dto);
		} else {
			// 실패 : 전달되어진 데이터가 정상적이지 않을 때
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info("** incheck Test HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	}

	// 4-2) incheck2 : @RequestParam 테스트
	// => http://localhost:8080/spring02/rest/incheck2?jno=4&id=kso1
	@GetMapping(value = "/incheck2")
	// @RequestParam
	// parameter와 매개변수명이 동일한 경우 생력가능
	// 단, 생략된 경우 parameter가 없의면 null로 통과
	// 그러므로 매핑을 엄격하게 하기위해 @RequestParam, param 등을 사용함

	// @RequestParam 생력
	// => http://localhost:8080/spring02/rest/incheck2?jno=4&captain=kso1
//	public ResponseEntity<JoDTO> inCheck2(	@RequestParam("jno") int jno,
//											@RequestParam("id") String captain){
	public ResponseEntity<JoDTO> inCheck2(int jno, String captain) {
		// 1) 준비
		ResponseEntity<JoDTO> result = null;
		JoDTO dto = new JoDTO();
		dto.setJno(jno);

		// 2) Service & return
		// => jno로 selectOne, 성공 시 captain의 값 비교해서 일치하면(데이터 찾으면) 성공, 아니면 오류라고 가정
		dto = jservice.selectOne(jno);
		if (dto != null && dto.getCaptain().equals(captain)) {
			// 성공
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info("** incheck2 Test HttpStatus.OK => " + HttpStatus.OK);
			log.info("** incheck2 Test dto => " + dto);
		} else {
			// 실패 : 전달되어진 데이터가 정상적이지 않을 때
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info("** incheck2 Test HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	}

	// 4-3) @PathVariable 테스트
	// @PathVariable
	// => URL 경로의 일부를 파라미터로 사용할때 이용
	// http://localhost:8080/spring02/rest/order/outer/노랑
	// => 요청 URI 매핑에서 템플릿 변수를 설정하고 이를 매핑메서드 매개변수의 값으로 할당 시켜줌.
	// 이때 파라미터가 1개이면 @PathVariable 과 같이 name을 생략할수 있다
	@GetMapping("/order/{test1}/{test2}")
	// 매개변수를 연결하여 값을 각각의 매핑주소에 넣어주기
	public String[] order(@PathVariable("test1") String category, @PathVariable("test2") String color) {
		return new String[] { "category : " + category, "color : " + color };
	}

	// 6) @RequestBody
	// => JSON 형식으로 전달된 Data를 컨트롤러에서 사용자정의 객체(DTO) _Java객체 로 변환할때 사용
	// => 요청 url(부트) : http://localhost:8080/rest/convert
	// => Payload(JSON형식=> 바디에 담아보내는 데이터) :
	// {"jno":33, "jname":"삼삼오오", "captain":"victory", "project":"RequestBody Test
	// 중"}
	@PostMapping("/convert")
	public ResponseEntity<?> convert(@RequestBody JoDTO dto) {
		ResponseEntity<JoDTO> result = null;
		log.info("** convert JSON 변환 Test dto => " + dto);
		if (dto != null) {
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info("** convert Test HttpStatus.OK => " + HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(dto);
			log.info("** convert Test HttpStatus.BAD_GATEWAY => " + HttpStatus.BAD_GATEWAY);
		}
		return result;
	}

	// ** Ajax: 비동기 통신 fetch 요청 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// 1) Login1
	// => Request: JSON, Response: Text
	// => MediaType
	// Mapping 시 받는 데이터를 강제를 함으로 오류상황을 줄일 수 있다.
	// 이것을 위해 사용하는것중 하나가 MediaType 이며,
	// 받는 데이터를 제한할때 consumes (위에서는 Json 임을 강제함)
	// 나가는 데이터를 제한할때 produces (위에서는 String을 Return 함을 강제함)
	// => consumes를 설정하면 Request Header에 보내는 Data가 JSON 임을 명시해야함.
	// => @RequestBody : Json -> Java 객체로 파싱
	@PostMapping(value = "/rslogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> rslogin(HttpSession session, @RequestBody MemberDTO dto) {
		ResponseEntity<String> result = null;
		// 1) password 보관
		String password = dto.getPassword();

		// 2) Service 정의
		// => 성공: login 정보를 session에 보관후, status OK, body="성공 message"
		// 실패: status INTERNAL_BAD_GATEWAY 502, body="실패 message"
		dto = service.selectOne(dto.getId()); // dto 값에 찾은 id 값을 담아주기
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
			// 빌더 패턴으로 값을 채워나가기
			result = ResponseEntity.status(HttpStatus.OK).body("로그인(Login) 성공");
			log.info("rslogin HttpStatus.OK" + HttpStatus.OK);
		} else {
			// 실패
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("로그인(Login) 실패");
			log.info("rslogin HttpStatus.BAD_GATEWAY" + HttpStatus.BAD_GATEWAY);
		}

		return result;
	}// rslogin

	// 2) Login2
	// => UserDTO사용, login 정보를 담아서 전송
	@PostMapping(value = "/rsloginjj", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rslogin2(HttpSession session, @RequestBody MemberDTO dto) {
		ResponseEntity<UserDTO> result = null;
		// 1) password 보관
		String password = dto.getPassword();

		// 2) Service 정의
		dto = service.selectOne(dto.getId()); // dto 값에 찾은 id 값을 담아주기
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
			// response로 전송할 객체를 생성해야한 후 담아줘야한다.
			// UserDTO 사용, 빌터패턴으로 값 채워나가기
			// UserDTO의 값 변경을 예방하기 위해서 final 을 붙여준다.
			final UserDTO userDTO = UserDTO.builder().id(dto.getName()).username(dto.getName()).build();

			result = ResponseEntity.status(HttpStatus.OK).body(userDTO);
			log.info("rslogin HttpStatus.OK" + HttpStatus.OK);
		} else {
			// 실패
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
			log.info("rslogin HttpStatus.BAD_GATEWAY" + HttpStatus.BAD_GATEWAY);
		}
		return result;
	}// rslogin2

	// 3) Join 처리
	// => image 포함, "multipart/form-data" Type 으로 요청
	// => consumes, produces 설정
	@PostMapping(value = "/rsjoin", 
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, 
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> rsjoin(MemberDTO dto) throws Exception {
		ResponseEntity<String> result = null; // text 값을 받으니 string 사용

		// Join Service 처리
		// => MultipartFile, passwordEncoder, 처리

		// MultipartFile
		String realPath = "E:\\ksoo\\gitANDeclipse\\demoM\\src\\main\\webapp\\resources\\uploadImages\\";
		String file1 = "", file2 = "basicman1.jpg";

		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => image_File 을 선택함
			// 1.4.1) 물리적위치 저장 (file1)
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 1.4.2) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
		}
		dto.setUploadfile(file2);

		// passwordEncoder 적용
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		if (service.insert(dto) > 0) {
			// 성공
			result = ResponseEntity.status(HttpStatus.OK).body("회원가입 성공, 로그인 후 이용하세요");
			log.info("rsjoin HttpStatus.OK" + HttpStatus.OK);
		} else {
			// 실패 : 재가입 유도
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("회원가입 실패");
			log.info("rsjoin HttpStatus.BAD_GATEWAY" + HttpStatus.BAD_GATEWAY);
		}

		return result;
	}// rsjoin
	
	// ** Ajax, 반복문에 이벤트 적용하기
	// 4) idbList 처리(id별 boardList 처리)
	@GetMapping(value = "/idblist/{id}")
	public ResponseEntity<?> idblist(@PathVariable("id") String id) {
		
		ResponseEntity<?> result = null;
		List<BoardDTO> list = bservice.idbList(id);
		
		// 출력하는 Data의 유/무를 판별
		if(list!=null && list.size()>0) {
			result = ResponseEntity.status(HttpStatus.OK).body(list);
			log.info("** idblist HttpStatus.OK"+HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("idblist 출력할 자료가 업슴당~!");
			log.info("** idblist HttpStatus.BAD_GATEWAY"+HttpStatus.BAD_GATEWAY);
		}
		return result;
	}//idblist
	
	// ** Ajax delete 테스트
	// 5) axiDelete 처리(id 삭제 처리)
	@DeleteMapping("/axidelete/{deleteID}")
	public ResponseEntity<?> axidelete(@PathVariable("deleteID") String id) {
		
		if(service.delete(id)>0) {
			log.info("** axidelete HttpStatus.OK"+HttpStatus.OK);
			return new ResponseEntity<String>("** 삭제에 성공하였습니다.",HttpStatus.OK);
		} else {
			log.info("** axidelete HttpStatus.BAD_GATEWAY"+HttpStatus.BAD_GATEWAY);
			return new ResponseEntity<String>("** 삭제에 실패하였습니다.",HttpStatus.BAD_GATEWAY);
		}
	}
	
	// 6) showJoDetail 과 hideJoDetail 처리(joDetail 출력)
	@GetMapping("/jodetail/{jno}")
	public ResponseEntity<?> jodetail(@PathVariable("jno") int jno, JoDTO dto) {

		// => dto 확인 : parameter와 같은 이름의 멤버변수가 있으면 자동으로 set
		System.out.println("** jodetail dto =>"+dto);
		ResponseEntity<?> result = null;
		
		// service 처리
		dto = jservice.selectOne(jno);
		System.out.println("** jodetail dto22 =>"+dto);
		if(dto!=null) {
			result = ResponseEntity.status(HttpStatus.OK).body(dto);
			log.info("** jodetail HttpStatus.OK"+HttpStatus.OK);
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("jodetail의 출력할 자료가 업슴당~!");
			log.info("** jodetail HttpStatus.BAD_GATEWAY"+HttpStatus.BAD_GATEWAY);
		}
		
		return result;
	}
}

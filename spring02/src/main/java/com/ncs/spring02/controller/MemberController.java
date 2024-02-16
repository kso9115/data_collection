package com.ncs.spring02.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;

import pageTest.PageMaker;
import pageTest.SearchCriteria;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=> @Component를 스프링에서는 역할별로 세분화
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//    -> DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌. 
//		-> interface Controller 의 구현의무가 사라진다.
//		-> 이로인해 메서드 handleRequest() 오버라이딩에 대한 의무도 사라진다
//		-> 즉, 메서드명, 리턴타입(ModelAndView or string or void), 전달받는 매개변수에 따라 자유로워진다.
//		-> 그렇다면 매핑을 해주어야하는데,클래스와 메서드 단위로 매핑해주는 애노테이션 @RequestMapping이 등장한다
//		-> 결국 하나의 컨트롤러 클래스 안에 여러개의 매핑메서드 구현이 가능해진다.
//		-> 그래서 주로 테이블(엔티티)을 작성한다 (MemberConroller.java에 작성)
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//    -> DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

@Controller
@RequestMapping(value = "/member") // /member 에 대한 부분을 모두 매핑하므로,
public class MemberController {

	@Autowired(required = false)
	MemberService service;

	@Autowired
//	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	PasswordEncoder passwordEncoder;;
	// new BCryptPasswordEncoder() 클래스를 만드는 작업을 해주어햐한다.
	// root-context.xml에 bean 생성

	// ID 중복 확인
	@GetMapping("/idDupCheck")
	// 사용가능한 아이디인지 아닌지 확인해야하므로 model 객체 필요
	public void idDupCheck(@RequestParam("id") String id, Model model) {
		// 1) newID 존재여부 확인 & 결과처리
		if (service.selectOne(id) != null) { // 현재 아이디가 null이 아닐경우
			// 사용 불가능 : 모델객체에 파라미터를 담아 전달
			model.addAttribute("idUse", "F");
		} else {
			// 사용 가능
			model.addAttribute("idUse", "T");
		}

	} // idDupCheck()

	// 리턴타입이 자유롭다고 해서, int를 넣을수는 없음 :
	// ModelAndView or string or void 타입 사용 가능

	// 1-1. memberList
	@RequestMapping(value = { "/memberList" }, method = RequestMethod.GET)
	public String mList(Model model) {

		model.addAttribute("mlist", service.selectList());
		return "member/memberList";
	}

	// 1-2. mPageList
	@RequestMapping(value = { "/mPageList" }, method = RequestMethod.GET)
	public void mPageList(HttpServletRequest request, Model model,SearchCriteria cri, PageMaker pageMaker) {
		
		// 1-1. 요청명을 url에 포함하기 위한 작업
		String mappingName = 
				request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		System.out.println("1");
		
		// 1) Criteria 처리
		cri.setSnoEno();
		
		// 2) Service
		
		model.addAttribute("mlist", service.mPageList(cri));
		System.out.println("2");
		
		// 3) View 처리 : PageMaker 사용
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);	// mappingName을 객체에 담아주기
		pageMaker.setTotalRowsCount(service.mtotalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		System.out.println(pageMaker);
	}
	
	// 1-3. Member CheckList
	// Board CheckList
	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		
		// 1. 경로 설정
		String uri = "member/mPageList";
		// 1-1. 요청명을 url에 포함하기 위한 작업
		String mappingName = 
				request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		System.out.println("=>RequestURI : "+request.getRequestURI());
		// RequestURI : /spring02/board/bPageList
		System.out.println("=>mappingName : "+mappingName);
		
		// 1) Criteria 처리
		// 파라미터 값으로 전달했기 때문에 자동으로 데이터 들어감
		// ver01: currPage, rowsPerPage 값들은 Parameter 로 전달되어 자동으로 cri에 set
		// ver02: ver01 + searchType, keyword도 동일하게 cri set
		
		cri.setSnoEno();
		
		// 2) Service
	    // => check 의 값을 선택하지 않은경우 check 값을 null 로 확실하게 해줘야함.
		//    mapper 에서 명확하게 구분할수 있도록해야 정확한 저리가능 
		if(cri.getCheck() !=null && cri.getCheck().length<1) {
			cri.setCheck(null);
		}
		
		model.addAttribute("mlist", service.mCheckList(cri));
		
		// 3) View 처리 : PageMaker 사용
		// => cri, totalRowsCount가 필요하다(Read from DB)
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);	// mappingName을 객체에 담아주기
		// 전체 갯수를 세기때문에 현재로는 매개변수 필요없으나, 추후 검색 시는 필요할 수 있음
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri)); 
		model.addAttribute("pageMaker", pageMaker);
		
		return uri;
	}

	// 2. memberDetail
	@RequestMapping(value = { "/mdetailsp", "/mdetail" }, method = RequestMethod.GET)
	public String mDetail(Model model) {

		model.addAttribute("mdetail", service.selectOne("kso"));
		return "member/memberDetail";
	}

//------------------------------------------------------------------------------------

	// 3. login
	// login form을 출력하기 위한 메서드 사용법 2가지
//	@RequestMapping(value = {"/loginForm"}, method = RequestMethod.GET)
//	 version 01 : return string
//	public String loginForm(Model model) {
//		
//		model.addAttribute("loginForm", service.selectOne("kso"));
//		return "member/loginForm";
//	}
//	 version 02 : return void
//	 => viewName 생략 : 요청명과 동일한 viewName을 찾음 
//	 => "/WEB-INF/views/member/loginForm.jsp"로 완성
//	@RequestMapping(value = {"member/loginForm"}, method = RequestMethod.GET)
//	public void loginForm() {
//	} >> 클래스 전체 매핑 전
	@RequestMapping(value = { "loginForm" }, method = RequestMethod.GET)
	public void loginForm() {
	}

	// 로그인 화면 처리 : 입력받은 값에 대한 처리
	@PostMapping("/login")
	public String login(HttpSession session, Model model, MemberDTO dto) { // 메세지 처리를 위한 model 변수 사용
		// 포워드 방식으로 home.jsp 사용 시 homeController 에서 전달해주는 내용이 들어가지 않음(시간이라던가..)
		// 매핑 메서드의 인자객체와 동일한 컬럼명의 값은 자동으로 반환
		// 아래의 구문은 필요가 없다
		// String id = request.getParameter("id");
		// dto.setId(id);

		// 1. 요청분석
		// String password = request.getparameter("password"); // 비밀번호도 정해줘야하므로 dto에서
		// 가져오기
		// => requst 로 전달되는 id, password 처리:
		// 매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		// ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		// => 전달된 password 보관
		String password = dto.getPassword();
		String uri = "redirect:/home"; // 성공 시 첫 화면 : 홈으로~

		// 2. 서비스 처리 & 결과 처리
		// => id 확인
		// => 존재하면 Password 확인
		// => 성공: id, name은 session에 보관, home 으로
		// => 실패: 재로그인 유도
		dto = service.selectOne(dto.getId()); // 비밀번호도 dto에서 가져오기때문에 미리 위에서 저장해놓아야한다

		// if(dto!=null && dto.getPassword().equals(password)) { // 기존 패스워드 검증 코드

		// 패스워드 인코더 진행 (입력받은 값의 password, dto.getId() 하여 db에 들어가있는 비밀번호 즉 다이제스트를 비교)
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message", "그런 아이디는 없어요 로그인을 다시하세욤;;");
		}
		return uri;
	}

	// 4. logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/";
	}

//------------------------------------------------------------------------------------

	// 5. member Detail
	// => 단일 parameter의 경우 @RequestParam("...") 활용
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("jCode") String jCode, HttpSession session, Model model) {
		// 1. 요청 분석
		// => session에 저장된 내 아이디를 확인
		// => id: session 에서 get
		// => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별
		// 단, 해당하는 Parameter 가 없으면 400 오류 발생
		// 그러므로 detail 요청에도 ?jCode=D 를 추가함.
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail";

		// => update 요청 확인 후 uri 수정
		if ("U".equals(jCode))
			uri = "member/updateForm";
		// 2. 서비스 처리
		model.addAttribute("mdetail", service.selectOne(id));
		return uri;
	}

	// 6. joinForm
	@GetMapping("/joinForm")
	public void joinForm() {
	}

	// 6. join
	@RequestMapping(value = { "/join" }, method = RequestMethod.POST)
	public String join(Model model, MemberDTO dto, HttpServletRequest request) throws IOException {
		// 1. 요청 분석
		// => 이전 : 한글처리, request 값을 dto에 set하여 진행했으나,
		// => 현재 : 한글은 filter / request처리는 parameter(매개변수)로 자동 처리
		String uri = "member/loginForm"; // 성공 시

		// Upload File 처리하기
		// -> 전달받은 파일을 저장 : file1 (서버의 물리적 실제저장위치 필요함)
		// -> 전달된 파일의 정보를 테이블에 저장 : file2
		// => MultipartFile 클래스 활용 : 위의 과정을 지원해주는 전용 객체

		// 1) 물리적 실제저장 위치 확인
		// 1-1) 현재 웹어플리케이션의 실행위치
		// => 이클립스 개발환경 (배포 전) : ~~.eclipse.~~ 포함
		// => 톰캣 서버 (배포 후) : ~~.eclipse.~~ 포함되어있지 ㅇ낳음
		String realPath = request.getRealPath("/");
		System.out.println("** realPath => " + realPath);

		// 1.2) realPath를 이용해서 물리적 저장위치 (file1) 확인
		if (realPath.contains(".eclipse.")) // 개발중
			realPath = "E:\\ksoo\\gitANDeclipse\\spring02\\src\\main\\webapp\\resources\\uploadImages\\"; // 현재 파일의 경로
		else
			realPath = "E:\\ksoo\\IDESet\\apache-tomcat-9.0.85\\webapps\\spring02\\resources\\uploadImages\\"; // 배포 후
																												// 서버에
																												// 저장되는
																												// 경로 =>
																												// 모두

		// 1.3) 폴더 만들기 : 폴더 자체가 존재하지 않을수도 있다는 경우를 가정(uploadImages)
		// => File type 객체 생성 : new File("경로");
		// => file.exists()
		// -> 파일 또는 폴더가 존재하는지 리턴
		// -> 폴더가 아닌, 파일존재 확인하려면 file.isDirectory() 도 함께 체크해야함.
		// ( 참고: https://codechacha.com/ko/java-check-if-file-exists/ )
		// => file.isDirectory() : 폴더이면 true 그러므로 false 이면 file 이 존재 한다는 의미가 됨.
		// => file.isFile()
		// -> 파일이 존재하는 경우 true 리턴,
		// file의 Path 가 폴더인 경우는 false 리턴
		File file = new File(realPath); // realpath에 있는 값을 file 타입으로 변경하는 것
		if (!file.exists()) { // 만약 realpath가 uploadImages일때 폴더여도 파일로 인식해버릴수잇음
			// => 저장 폴더가 존재하지 않는경우 만들어준다
			file.mkdir();
		}

		// --------------------------------------------
		// ** File Copy 하기 (IO Stream)
		// => 기본이미지(cat04.gif) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
		// => IO 발생: Checked Exception 처리
		file = new File(realPath + "basicman4.png"); // uploadImages 폴더에 파일존재 확인을 위한 경로 설정 후 객체생성
		// file = new
		// File("E:\\ksoo\\IDESet\\apache-tomcat-9.0.85\\webapps\\spring02\\"+realPath+"basicman1.jpg");
		if (!file.isFile()) { // 존재하지않는 경우(파일존재의 여부를 확인) -> 디렉토리의 경로를 확인해주는 것
			String basicImagePath = "E:\\ksoo\\gitANDeclipse\\spring02\\src\\main\\webapp\\resources\\images\\basicman4.png";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트림 생성
			FileOutputStream fo = new FileOutputStream(file);
			// => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성
			FileCopyUtils.copy(fi, fo);
		}
		// --------------------------------------------

		// --------------------------------------------
		// ** MultipartFile
		// => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
		// -> String getOriginalFilename(),
		// -> void transferTo(File destFile),
		// -> boolean isEmpty()

		// 1.4) 저장경로 완성
		// => 기본 이미지 저장
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
		// --------------------------------------------

		dto.setUploadfile(file2);

		// 2. 서비스 처리 & 결과
		// => PasswordEncoder 적용 : 입력받은 비밀번호값을 인코딩한 후 db의 비밀번호에 저장해주기
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		if (service.insert(dto) > 0) {
			// 성공
			model.addAttribute("message", " 회원가입 성공, 로그인 하심 됨니당ㅋㅋ ");
		} else {
			// 실패 : 재가입 유도
			uri = "member/joinForm";
			model.addAttribute("message", " 회원가입 실패 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}

	// 7. update
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String update(HttpSession session, Model model, MemberDTO dto, HttpServletRequest request)
			throws IOException {
		// 1. 요청 분석
		// 성공 시 : 내 정보로
		// 실패 시 : updateForm
		// 즉 dto의 값을 저장해놔야함(detail을 뽑아줄 객체와 = 수정 후 객체가 동일해야한다.)
		// ${requestScope.mdetail} => 내정보에서도 쓰이고 회원정보 수정에서도 수정후 사용됨
		String uri = "member/memberDetail"; // 성공 시
		model.addAttribute("mdetail", dto);

		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {

			// => newImage를 선택함
			// 1) 물리적위치 저장 (file1)
			String realPath = request.getRealPath("/");
			String file1, file2 = dto.getUploadfile();

			// 2) realPath를 이용해서 물리적 저장위치 (file1) 확인
			if (realPath.contains(".eclipse.")) // 개발중
				realPath = "E:\\ksoo\\gitANDeclipse\\spring02\\src\\main\\webapp\\resources\\uploadImages\\"; // 현재 파일의
																												// 경로
			else
				realPath = "E:\\ksoo\\IDESet\\apache-tomcat-9.0.85\\webapps\\spring02\\resources\\uploadImages\\"; // 배포
																													// 후
																													// 서버에
																													// 저장되는
																													// 경로
																													// =>
																													// 모두

			// 3) oldFile 삭제
			// oldFile : dto.getUploadfile()
			// 삭제 경로 : realPath+dto.getUploadfile() => file 타입으로 만들어줘야한다(삭제를 위한 인스턴스가 필요함)
			File delFile = new File(realPath + dto.getUploadfile());
			if (delFile.isFile()) {
				delFile.delete();
			} // 파일 존재 시 삭제

			// 4) newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 5) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
			dto.setUploadfile(file2);

//	        dto.setUploadfile(uploadfilef.getOriginalFilename());

		}
		// --------------------------------------------

		// 2. 서비스 처리 & 결과
		if (service.update(dto) > 0) {
			// 성공
			model.addAttribute("message", " 정보수정 성공 굳ㅋㅋ ");
			// => name 수정을 했을수도 있으므로 세션에 저장되는 loginName을 수정해줘야한다
			session.setAttribute("loginName", dto.getName());
		} else {
			// 실패 : 재가입 유도
			uri = "member/updateForm";
			model.addAttribute("message", " 정보수정 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}

	// 7-1 pwUpdate : 비밀번호경로이동
	// password 수정 PasswordEncoder 추가 후 pwUpdate 경로로의 이동 진행
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		// View_name 생략
	}

	// password Update
	// 이동 후 처리 진행
	// Service, DAO에 pwUpdate(dto=> pw와 id도 확인해야댐)메서드 추가
	// 성공 시 재로그인 유도 : 로그인 창으로 & 세션 무효화 진행
	// 실패 시 재수정 유도 : pwUpdate 창으로
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, MemberDTO dto, Model model) {
		// 1. 요청분석 : 세션에 저장된 아이디값을 가져와 비교
		dto.setId((String) session.getAttribute("loginID"));
		dto.setPassword(passwordEncoder.encode(dto.getPassword())); // 비밀번호 인코더

		String uri = "member/loginForm"; // 성공 시 이동경로

		// 2. 서비스 처리
		if (service.pwUpdate(dto) > 0) {
			// 성공
			model.addAttribute("message", " 비밀번호 업데이트에 성공하였습니다 재로그인을 해주세요 ⎝⍢⎠");
			session.invalidate();

		} else {
			// 실패
			model.addAttribute("message", " 비밀번호 업데이트 실패!!!!!!!!!!! ⎝⍥⎠ ");
			uri = "member/pwUpdate";
		}
		return uri;
	}

	// 8. delete
	@RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, MemberDTO dto, RedirectAttributes rttr) {
		// 1. 요청 분석
		// => id : session에서 get
		// => delete & session 처리
		String id = (String) session.getAttribute("loginID");
		String uri = "redirect:/home";

		// 2. 서비스 처리 & 결과
		if (service.delete(id) > 0) {
			// 성공 : requestScope 의 message를 redirect 시에도 유지하기 위해서는
			// 세션에 보관했다가 사용 후에는 무효화해주어야한다.
			// session에 보관 후 redirect 되어진 요청 처리시에 requestScope에 옮기고,
			// session의 message는 삭제
			// => 이를 처리해주는 API : RedirectAttribute
			rttr.addFlashAttribute("message", " 아이디 삭제 성공 안녕히 가세요");
			session.invalidate();
		} else {
			// 실패
			rttr.addFlashAttribute("message", " 아이디 삭제 실패 어딜 가세요");
		}

		return uri;
	}
}

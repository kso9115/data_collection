package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Member;
import com.example.demo.service.JoService;
import com.example.demo.service.JoServiceImpl;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

@Log4j2
@Controller
@RequestMapping(value = "/member") // /member 에 대한 부분을 모두 매핑하므로,
@AllArgsConstructor // @Autowired 대신 사용! : 모든 멤버변수를 생성하면서 초기화한다.
public class MemberController {

	MemberService service;
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

	// 1. Login Form 출력
	@GetMapping("/loginForm")
	public void loginForm() {
	}

	// 2. Login 처리
	@PostMapping("/login")
	public String login(HttpSession session, Model model, Member entity) { // 메세지 처리를 위한 model 변수 사용

		String password = entity.getPassword(); // 비밀번호도 entity에서 가져오기때문에 미리 저장
		String uri = "redirect:/home"; // 성공 시 첫 화면 : home

		entity = service.selectOne(entity.getId());

		if (entity != null && passwordEncoder.matches(password, entity.getPassword())) {
			// 성공 : 아이디와 이름을 세션에 저장
			session.setAttribute("loginID", entity.getId());
			session.setAttribute("loginName", entity.getName());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message", "그런 아이디는 없어요 로그인을 다시하세욤;;");
		}
		return uri;
	}

	// 3. Logout
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		return "redirect:/";
	}

	// 4. memberDetail Form 으로 이동
	@GetMapping("/mdetail")
	public String mDetail(Model model) {

		model.addAttribute("mdetail", service.selectOne("kso"));
		return "member/memberDetail";
	}

	// 5. member Detail
	// => 단일 parameter의 경우 @RequestParam("...") 활용
	@GetMapping("/detail")
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

	// 6. memberList
	@GetMapping("/memberList")
	public String mList(Model model) {

		model.addAttribute("mlist", service.selectList());
		return "member/memberList";
	}

	// ****번외 Member_Jo Join List
	// => ver01) @Query("...") 에 JPQL, LEFT_JOIN 구문, MemberDTO return
	// => MemberDTO 는 JoDTO 상속
	@GetMapping("/mjoinList")
	public void mjoinList(Model model) {
		model.addAttribute("banana", service.findMemberJoin());
	}

//------------------------------------------------------------------------------------

	// 7. joinForm
	@GetMapping("/joinForm")
	public void joinForm() {
	}

	// 8. join
	@PostMapping("/join")
	public String join(Model model, Member entity, HttpServletRequest request) throws IOException {
		// 1. 요청 분석
		// => 이전 : 한글처리, request 값을 dto에 set하여 진행했으나,
		// => 현재 : 한글은 filter / request처리는 parameter(매개변수)로 자동 처리
		String uri = "member/loginForm"; // 성공 시

		String realPath = request.getRealPath("/");
		log.info("** realPath => " + realPath);

		// 1) 물리적 실제저장위치 확인
		// 1.1) 현재 웹어플리케이션의 실행위치 확인
		// => SpringBoot 의 realPath 값은
		// => 이클립스 개발환경 (배포전) : "E:\\ksoo\\IDESet\\apache-tomcat-9.0.85\\webapps"
		// => 톰캣 서버 배포후 : "E:\\ksoo\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoJPA"
		// => 그러므로 물리적저장위치 (file1) 값은 동일하게 "resources\\uploadImages\\" 만 연결하면됨.

		realPath += "resources\\uploadImages\\"; // 현재 파일의 경로
		// E:\ksoo\gitANDeclipse\demoJPA\src\main\webapp\

		// 1.2) 폴더 만들기 : 폴더 자체가 존재하지 않을수도 있다는 경우를 가정(uploadImages)
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
			String basicImagePath = "E:\\ksoo\\gitANDeclipse\\demoJPA\\src\\main\\webapp\\resources\\images\\basicman4.png";

			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트림 생성
			FileOutputStream fo = new FileOutputStream(file);
			// => 목적지 파일(realPath+"basicman4.png") 출력바이트스트림 생성
			FileCopyUtils.copy(fi, fo);
		}

		// --------------------------------------------

		// ** MultipartFile
		// => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
		// -> String getOriginalFilename(),
		// -> void transferTo(File destFile),
		// -> boolean isEmpty()

		// 1.3) 저장경로 완성
		// => 기본 이미지 저장
		String file1 = "", file2 = "basicman4.png";

		MultipartFile uploadfilef = entity.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => image_File 을 선택함
			// 1.3.1) 물리적위치 저장 (file1)
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 1.3.2) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
		}
		// --------------------------------------------

		entity.setUploadfile(file2);

		// 2. 서비스 처리 & 결과
		// => PasswordEncoder 적용 : 입력받은 비밀번호값을 인코딩한 후 db의 비밀번호에 저장해주기
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));

		// service.save(entity)는 member타입을 그대로 리턴하기 때문에 i/o exception 발생시 controller에서
		// 처리를해줘야한다.
		try {
			log.info("** Member Insert 성공 => \n" + service.save(entity));
			model.addAttribute("message", " 회원가입 성공, 로그인 하심 됨니당ㅋㅋ ");
		} catch (Exception e) {
			log.info("** Member Insert 실패 => \n" + e.toString());

			uri = "member/joinForm";
			model.addAttribute("message", " 회원가입 실패 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}

	// 9. pwUpdate Form: 비밀번호경로이동
	// password 수정 PasswordEncoder 추가 후 pwUpdate 경로로의 이동 진행
	@GetMapping("/pwUpdate")
	public void pwUpdate() {
		// View_name 생략
	}

	// 9. password Update
	// 이동 후 처리 진행
	// Service, DAO에 pwUpdate(dto=> pw와 id도 확인해야댐)메서드 추가
	// 성공 시 재로그인 유도 : 로그인 창으로 & 세션 무효화 진행
	// 실패 시 재수정 유도 : pwUpdate 창으로
	@PostMapping("/pwUpdate")
	public String pwUpdate(HttpSession session, Member entity, Model model) {
		// 1. 요청분석 : 세션에 저장된 아이디값을 가져와 비교
		entity.setId((String) session.getAttribute("loginID"));
		entity.setPassword(passwordEncoder.encode(entity.getPassword())); // 비밀번호 인코더

		String uri = "member/loginForm"; // 성공 시 이동경로

		// 2. 서비스 처리
		try {
			// 성공
			service.updatePassword(entity.getId(), entity.getPassword());
			log.info("** Member Password Update 완료 => \n" + entity.getId());

			model.addAttribute("message", " 비밀번호 수정 성공, 로그인 해보시죠ㅋㅋ ");
			session.setAttribute("loginName", entity.getName());
			session.invalidate();
		} catch (Exception e) {
			// 실패 : 재가입 유도
			uri = "member/updateForm";
			log.info("** Member Password Update 실패 => \n" + e.toString());
			model.addAttribute("message", "비밀번호 수정 실패 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}

	// 10. update
	@PostMapping("/update")
	public String update(HttpSession session, Model model, Member entity, HttpServletRequest request)
			throws IOException {
		// 1. 요청 분석
		// 성공 시 : 내 정보로
		// 실패 시 : updateForm
		// 즉 dto의 값을 저장해놔야함(detail을 뽑아줄 객체와 = 수정 후 객체가 동일해야한다.)
		// ${requestScope.mdetail} => 내정보에서도 쓰이고 회원정보 수정에서도 수정후 사용됨
		String uri = "member/memberDetail"; // 성공 시
		model.addAttribute("mdetail", entity);
		MultipartFile uploadfilef = entity.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {

			// => newImage를 선택함
			// 1) 물리적위치 저장 (file1)
			String realPath = request.getRealPath("/");
			String file1, file2 = entity.getUploadfile();

			// 2) realPath를 이용해서 물리적 저장위치 (file1) 확인
			realPath += "resources\\uploadImages\\";

			// 3) oldFile 삭제
			// oldFile : dto.getUploadfile()
			// 삭제 경로 : realPath+dto.getUploadfile() => file 타입으로 만들어줘야한다(삭제를 위한 인스턴스가 필요함)
			File delFile = new File(realPath + entity.getUploadfile());
			if (delFile.isFile()) {
				delFile.delete();
			} // 파일 존재 시 삭제

			// 4) newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 5) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
			entity.setUploadfile(file2);

			// dto.setUploadfile(uploadfilef.getOriginalFilename());

		}

		// 2. 서비스 처리 & 결과
		try { // 성공
			log.info("** Member Update 완료 => \n" + service.save(entity));
			model.addAttribute("message", " 회원가입 성공, 로그인 하심 됨니당ㅋㅋ ");
			session.setAttribute("loginName", entity.getName());
		} catch (Exception e) { // 실패 : 재가입 유도
			uri = "member/updateForm";
			log.info("** Member Update 실패 => \n" + e.toString());
			model.addAttribute("message", " 정보수정 흑흑 다시해주시겟어요? ");
		}
		return uri;
	}

	// 8. delete
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model, Member entity, RedirectAttributes rttr) {
		// 1. 요청 분석
		// => id : session에서 get
		// => delete & session 처리
		String id = (String) session.getAttribute("loginID");
		String uri = "redirect:/home";

		// 2. 서비스 처리 & 결과

		try {
			service.deleteById(id);
			log.info("** Member Delete 완료 => \n");

			rttr.addFlashAttribute("message", " 아이디 삭제 성공 안녕히 가세요");
			session.invalidate();
		} catch (Exception e) {
			log.info("** Member Delete 실패 => \n" + e.toString());
			rttr.addFlashAttribute("message", " 아이디 삭제 실패 어딜 가세요");
		}
		return uri;
	}
}

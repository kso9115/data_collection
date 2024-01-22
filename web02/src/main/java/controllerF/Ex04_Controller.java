package controllerF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

//** 서비스 컨트롤러들의 메서드명의 일관성(강제성)을 위해 작성
//=> 모든 서비스 컨트롤러들은 반드시 구현해야함.

public interface Ex04_Controller {

	// 이동할 jsp를 지정해줘야하기 때문에 return타입을 string으로 지정
	public String doUser(HttpServletRequest request, HttpServletResponse response);
}	// Controller : 서비스를 실행하기우한 강제성과 규칙성을 가진다

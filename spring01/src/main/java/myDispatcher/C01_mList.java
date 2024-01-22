package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C01_mList implements MyController{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		MemberService service = new MemberService();
		request.setAttribute("mlist", service.selectList());
		return "member/memberList";
	}

}

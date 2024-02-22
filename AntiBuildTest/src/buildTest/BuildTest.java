package buildTest;

import com.ncs.green.domain.UserDTO;
import com.ncs.green.service.DTOService;

public class BuildTest {

	public static void main(String[] args) {

		// 1) UserDTO 생성
		UserDTO dto = new UserDTO();
		dto.setId("kso1");
		dto.setName("김수옥");
		dto.setLoginTime("2023/02/22 am 10:04");

		// 2) 직접 출력
		System.out.println("** 직접 출력 => " + dto);

		// 3) DTOService로 출력
		DTOService service = new DTOService();
		service.setUserDTO(dto);
		System.out.println("** AntiBuild Test **");
		System.out.println("** DTOService 출력 => " + service.getUserDTO());

	}

}

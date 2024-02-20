package aop03;

import java.util.Random;

public class Girl implements Programmer {

	//공통 기능 제거, 핵심 기능만 유지
	@Override
	public void doStudying() throws Exception {

		System.out.println("GIRL 열심히 게시판을 만듭니다. => 핵심적 관심 사항");
		
		// ** Test 를 위해  늘 성공으로 처리
	    // => 항상 true 가 되도록
		//if (new Random().nextBoolean()) {
		if(true) {
			// 실패 : 발생시키기
			throw new Exception("~~ Error 500*100 => 예외 발생");
		} 
		System.out.println("=============================================");
	}
}

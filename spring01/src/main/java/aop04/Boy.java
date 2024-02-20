package aop04;

import java.util.Random;

//** Aop 구현
//1 단계 : 핵심적 관심사항 과  공통적 관심사항 분리
//=> 핵심적 관심사항만 구현
//=> 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Boy implements Programmer {

	// 공통 기능 제거, 핵심 기능만 유지
	@Override
	public String doStudying(int n) throws Exception { // main으로 Exception 넘기기

		System.out.printf("BOY 열심히 회원관리를 %d개 만듭니다. => 핵심적 관심 사항\n",n);
		
	    // ** Test 를 위해  늘 성공으로 처리
	    // => 항상 false 가 되도록
		//if (new Random().nextBoolean()) {
		if(1==2) {	// 항상 실패 발생시키기 위함
			// 실패 : 발생시키기
			throw new Exception("~~ Error 500*100 => 예외 발생");
		} 
		System.out.println("boy=============================================");
		return "취업성공 연봉 1억";
	}
}
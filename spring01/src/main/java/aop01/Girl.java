package aop01;

import java.util.Random;

public class Girl implements Programmer {

	@Override
	public void doStudying() {

		System.out.println("GIRL 프로젝트 과제를 합니다. => 핵심 기능을 하기위한 Before 단계");

		try {
			System.out.println("열심히 게시판을 만듭니다. => 핵심적 관심 사항");
			if (new Random().nextBoolean()) {
				// 실패 : 발생시키기
				throw new Exception("~~ Error 500*100 => 예외 발생");
			} else {
				// 성공
				System.out.println("글 등록이 잘됩니다. => 핵심적 관심 사항 정상 종료");
			}
		} catch (Exception e) {
			System.out.println("** Girl Exception => " + e.toString());
			System.out.println("밤새워 수정한다 ( ༎ຶŎ༎ຶ ) => 핵심적 관심 사항 예외발생");
		} finally {
			System.out.println("** finally : 무조건 제출합니다 => 무조건 종료(After)");
		}
		
		System.out.println("=============================================");
	}

}

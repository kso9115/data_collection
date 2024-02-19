package aop02;

import java.util.Random;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
	public void myAround(ProceedingJoinPoint joinpoint) {
		
		// 1. Before 단계
		System.out.println("프로젝트 과제를 합니다. => 핵심 기능을 하기위한 Before 단계");

		try {
			// 2. 핵심기능(pointcut) boy,girl 클래스에서 만들었기 때문에 전달받아야한다. => 매개변수를 통해서
	        // => Around 메서드의 매개변수(ProceedingJoinPoint Type) 를 통해 전달받아 수행
			joinpoint.proceed();
			// => Throwable 로 예외처리를 해야함
			//		    Throwable - Exception - RuntimeException (UnChecked), IOException (Checked)
			//	              - Error
			
			// 3. 핵심기능 정상 종료 : After_returning
			System.out.println(" 200 OK : 회원가입, 글 등록이 잘됩니다. => 핵심적 관심 사항 정상 종료");
		} catch (Throwable e) {
			// 4. 핵심기능 비정상 종료 : After_throwing

			System.out.println("밤새워 수정한다 ( ༎ຶŎ༎ຶ ) => 핵심적 관심 사항 예외발생");
	        // => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test)
	        // => main으로 전달하려면 예외발생시켜주면됨.
	        // throw new Exception(e) ;  // Exception 은 Checked -> try~catch 처리 해야함 
			
			// 에러 발생 후 메인에 전달 : unchecked이기 떄문에 2중으로 try catch할 필요가 없다
			throw new RuntimeException();	// unchecked
		} finally {
			System.out.println("** finally : 무조건 제출합니다");
		}
		
	}
}

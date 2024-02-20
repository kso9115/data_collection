package aop04;

import java.util.Random;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


//** 횡적(공통)관심사항 ( cross concerns => Aspect ) 구현
//=> Boy, Girl : 핵심 관심사항 (core concerns) 만 구현하면 됨.
//=> 횡적(공통)관심사항 과 핵심관심사항 의 연결 방법 xml, @ 방식

//** xml 방식의 공통적 관심 사항 구현 2.
//=> pointcut : 매개변수, return 값 없음  
//=> Around 메서드 1개로 구현 
// Before, After_returning, After_throwing, After 
// 을 1개의 메서드에서 try~catch~finally 를 이용해서 처리

//=> Around 메서드 특징
// -> Aspect 와 Pointcut의 모든 Joinpoint를 아우르는 연결고리 
// -> 반드시 ProceedingJoinPoint 타입을 인자로 사용하여 
//    pointcut을 처리 (그렇지 않으면 오류)
//    joinPoint.proceed(); 로 핵심적 관심사항을 처리함

//** ProceedingJoinPoint
//=> JoinPoint 를 상속 했으며 proceed() 메서드를 가짐
//=> JoinPoint (I) -> ProceedingJoinPoint (I) -> JoinPointImpl
//=> 예외상황 처리시에  Throwable 사용해야됨.
// 계층도 : Object -> Throwable -> Error, Exception 
//                -> RuntimeException(unChecked) / IOException(Checked)

//** JoinPoint 
//=> PointCut 을 지원해주는 클래스 (다양한 지원 메서드를 가지고 있음-> 매개변수 전달 등.. )
//=> 핵심적 관심사항으로 들어가는 모든 데이터 (before를 통해) 사항을
// 가지고 있으며 처리할 수 있도록 해줌

//** pom.xml 설정 
//1) AspectJ  
//2) AspectJ Weaver : AOP 실습시에 반드시 추가 해줄것 
//=> weaver가 AOP에서 advice를 핵심기능에 적용하는 설정 파일 

//AOP03 ==============================================================

//** xml 방식의 공통적 관심 사항 구현 2.
//=> pointcut : 매개변수, return 값 없음  
//=> 개별 advice 메서드를 구현 
//Before, After_returning, After_throwing, After 

//** 용어정리
//Target: 핵심사항(Core concerns) 가 구현된 객체 : Boy, Girl
//JoinPoint: 클라이언트가 호출하는 모든 비즈니스 메서드    
//    ( 공통관심사항이 적용될수있는 지점, ex:메소드 호출시, 객체생성시 등 )
//Pointcut: JoinPoint 중 실제 공통적 관심사항이 적용될 대상 : doStudying()  
//Advice : 공통관심사항(Cross-Cutting) 구현 코드 + 적용시점
//  : 시점별 메서드들 ( myBefore() .... )
//  : 적용시점 (핵심로직 실행 전, 후, 정상 종료 후, 비정상 종료 후 등 )
//Aspect : Advice + Pointcut

//AOP04 ==============================================================

//** JoinPoint  
//=> 핵심적 관심사항으로 들어가는 모든 데이터 (before를 통해) 사항을
//가지고 있으며 처리할 수 있도록 해줌

//1) 인자
//=> JoinPoint의 getArgs() 메서드
//핵심관심사항의 인자(매개변수) 의 목록을 배열의 형태로 제공함.
//Before  메서드 에서 사용가능함.
//2) return 값 처리
//=> myAfter_returning 메서드에 매개변수로 전달되어 사용 가능.

//1) 핵심적 관심사항의 매개변수 처리가능 
//=> 핵심적 관심사항을 실행할 필요가 없으므로 JoinPoint Type 을 사용함

public class MyAspect {
	
	// Before
	// 1) 핵심적 관심사항의 매개변수 처리가능 
	// => 핵심적 관심사항을 실행할 필요가 없으므로 JoinPoint Type 을 사용함  
	public void myBefore(JoinPoint joinpoint) {
		System.out.println("프로젝트 과제를 합니다. => 핵심 기능을 하기위한 Before 단계");
		
		// pointcut의 인자값 확인 가능
		Object[] args = joinpoint.getArgs();
		for(Object o:args) {
			System.out.println("** myBefore pointcut의 인자값 => "+o);
		}
	}//myBefore
	
	// After_returning : 핵심적 관심사항(기능)의 정상종료
	// => 핵심적 관심사항 정상 종료후 결과 return 
	// => 이 결과를 매개변수로 전달 받으며 이에 대한 처리가 가능
	// => 전달받을 매개변수 : xml에서 mapping -> returning="result"   
	   
	// Test 1. 전달된 Return 값 사용가능함 
	// Test 2. class 의 main 실행시에는 전달된 return 값 출력됨 확인
	public void myAfter_returning(Object result) {
		System.out.println(" 200 OK : 회원가입, 글 등록이 잘됩니다. => 핵심적 관심 사항 정상 종료");
		System.out.println("** myAfter_returning pointcut의 return값 => "+result);
		result +="return Value Change";
		// => result는 현 메서드의 지역변수이므로 pointcut의 return값에는 영향이 없다.
	}
	
	// After_throwing : 핵심적 관심사항(기능)의 비정상종료
	// => 핵심적 관심사항 실행도중 예외발생시 예외메시지 return 
	// => 매개변수로 예외 메시지 전달받으면 이에 대한 처리 가능
	// => 전달받을 매개변수 : xml에서 mapping -> throwing="e"
	public void myAfter_throwing(Exception e) {
		System.out.println("밤새워 수정한다 ( ༎ຶŎ༎ຶ ) => 핵심적 관심 사항 예외발생");
		System.out.println("** myAfter_throwing 전달받은 Exception Message 출력 => "+e.toString());
	}
	
	// After : 정상 비정상과 관계없이 무조건 시행
	public void myAfter() {
		System.out.println("** finally : 무조건 제출합니다");
	}
}

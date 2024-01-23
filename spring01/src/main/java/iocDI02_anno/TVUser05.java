package iocDI02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Annotation, @, 애너테이션
//=> 컴파일러에게 알려줌 (지시어)

//** @ Annotation 애너테이션 활용방법 *********************

//1. xml 에 context 네임스페이스 추가 [NameSpaces] 이용
//=> Component-scan 설정  : 
//<context:component-scan base-package="d0714.iocEx05" />
//  
//2. 소스코드 
//=> import 확인  : org.springframework.context.support.*;
//=> 생성을 원하는 TV 클래스 위에 @Component("tv") 설정

//=> @Component("tv") 
//<bean ....> </bean> -> xml
//new 생성자() -> Java Code

//3.Test : 오류 메시지 확인 하기
//=> @ Test1 생성 -> <bean ... />
//=> @ Test2 id명 미 지정(둘다 beanname 없이) 
//=> ...NoSuchBeanDefinitionException: 
//           No bean named 'tv' is defined ....
//=> @ Test3 id명 중복되는 경우 (둘다 beanname  ("tv") 지정 )  
//=> ...annotation.ConflictingBeanDefinitionException: ....
//  ...non-compatible bean definition of same name and class ...

//=> interface, 구현을 강제(메서드명 동일)
interface TV{
	void powerOn();
	void powerOff();
	void volumeUp();
	void volumeDown();
}

@Component("tv")
class SsTVi implements TV {
	
	public SsTVi() {System.out.println("이것은 SsTVi 기본 생성자");}
	@Override
	public void powerOn() {System.out.println("SsTv 티비켜짐");}
	@Override
	public void powerOff() {System.out.println("SsTv 티비꺼짐");}
	@Override
	public void volumeUp() {System.out.println("SsTv 소리키움");}
	@Override
	public void volumeDown() {System.out.println("SsTv 소리줄임");}
}

@Component
class LgTVi implements TV {
	
	public LgTVi() {System.out.println("이것은 LgTVi 기본 생성자");}
	@Override
	public void powerOn() {System.out.println("LgTv 티비켜짐");}
	@Override
	public void powerOff() {System.out.println("LgTv 티비꺼짐");}
	@Override
	public void volumeUp() {System.out.println("LgTv 소리키움");}
	@Override
	public void volumeDown() {System.out.println("LgTv 소리줄임");}
}

// 패키지 변경 후 애노테이션 테스트
public class TVUser05 {

	public static void main(String[] args) {
		
		// 1. BeanFactory 생성 : spring에서의 Container(컨테이너)생성
				AbstractApplicationContext sc = new 
						GenericXmlApplicationContext("iocDI02_anno/app05.xml");	
						// 구현체 -> 생성자의 매개변수에 xml을 넣어준다
				
				// => 설정화일(xml구문_요구사항 목록) 을 매개변수로 전달
				// => GenericXmlApplicationContext("app02.xml");
				//    xml 문을 "src/main/resources"  에 두면 패키지는 생략가능 
				
				// 2. 필요한 객체를 전달받아 서비스 실행
				// getBean > object 타입 반환하므로 TV타입으로 형변환
			    // => Spring 컨테이너는 getBean 메서드를 실행해서 해당객체를 제공
			    // => 실시간으로 소스코드 수정없이 전달받음 
				TV tv = (TV)sc.getBean("tv");
				if(tv!=null) {
					tv.powerOn();
					tv.volumeUp();
					tv.volumeDown();
					tv.powerOff();
				} else System.out.println("** TV를 선택하지 않았습니다. **");
				
				System.out.println("** 정상 종료. **");
		
	}//main
}//class

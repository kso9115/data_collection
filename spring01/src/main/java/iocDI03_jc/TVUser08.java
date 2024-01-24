package iocDI03_jc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Java Bean Configuration class를 이용한 DI
//=> Bean 컨테이너 : AnnotationConfigApplicationContext 사용
//             매개변수로 Java_config_class 를 사용 (JavaConfig01.class)           
//=> Test01 : 스피커 없는 TV

interface TV{
	void powerOn();
	void powerOff();
	void volumeUp();
	void volumeDown();
}

//@Component("tv")
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

//@Component
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
public class TVUser08 {

	public static void main(String[] args) {
		
		// 1. BeanFactory 생성 : spring에서의 Container(컨테이너)생성
	    // => AnnotationConfigApplicationContext 사용
	    //    매개변수로 Java_Config_class 를 사용 (JavaConfig01.class) 
	    //    -> 주의사항 : "" 없이 화일명 적용, 같은 package에 있으므로  package명 생략
	    //     -> 참고 : 스프링 프로젝트의 .class 화일 위치 C:\MTest\myWork\Spring01\target\classes\iocDI03_jc   
				AbstractApplicationContext sc = new 
						AnnotationConfigApplicationContext(JavaConfig01.class);	
						// 구현체 -> 생성자의 매개변수에 xml을 넣어준다
				
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

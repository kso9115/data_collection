package iocDi01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** TV의 의존성 처리
//=> TV 는 Speaker 를 사용
//=> 생성자 주입, setter 주입

//** 의존성 해결 Test
//1) 고전적 방법 (직접 new)
//2) IOC/DI -> 생성자 주입 
//3) IOC/DI -> setter 주입
//=> 이런경우에 setter 보다는 생성자주입을 권장함 (최초 1회 초기화 적용후 변경 불가능 하기때문)

//** IOC: 제어의 역전 (외부에서 Control)
//** DI : 주입 받음으로 해결

class Speaker {
	   public Speaker() { System.out.println("~~ Speaker Default 생성자 ~~"); }
	   public void volumeUp() { System.out.println("~~ Speaker volumeUp ~~"); } 
	   public void volumeDown() { System.out.println("~~ Speaker volumeDown ~~"); }
	} //Speaker

	//1) 고전적 방법 (직접 new : 소스 재컴파일)
	//=> Speaker 생성 : 직접코드에서
	class SsTVs implements TV {
	   
	   private Speaker speaker = new Speaker();
	   // 객체를 생성하고 주입을 하는 것이나, 실행시에 주입하는 방법을 2번에서 사용
	   
	   public SsTVs() { System.out.println("~~ SsTVs Default 생성자 ~~"); }
	   
	   @Override
	   public void powerOn() { System.out.println("~~ SsTVs powerOn ~~"); }
	   @Override
	   public void powerOff() { System.out.println("~~ SsTVs powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //SsTVs

	//2) IOC/DI -> 생성자 주입 
	//=> Speaker 생성 : ~xml 에서 설정 
	class LgTVs implements TV {
	   
	   private Speaker speaker;
	   private String color;
	   private int price;
	   
	   public LgTVs() { System.out.println("~~ LgTVs Default 생성자 ~~"); }
	   
	   // 생성자를 통해서 값을 주입 : 전달받은 값으로 각각 할당
	   // 해당 생성자를 호출할때는 동일한 매개변수를 전달(아래처럼)
	   // new LgTVs(Speaker speaker, String color, int price)
	   public LgTVs(Speaker speaker, String color, int price) { 
	      this.speaker=speaker;
	      this.color=color;
	      this.price=price;
	      System.out.printf("~~ LgTVs 초기화 생성자 : color=%s, price=%d \n",color,price); 
	   }
	   
	   @Override
	   public void powerOn() { System.out.println("~~ LgTVs powerOn ~~"); }
	   @Override
	   public void powerOff() { System.out.println("~~ LgTVs powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //LgTVs

	//3) IOC/DI -> setter 주입
	//=> Speaker 생성 : ~xml 에서 설정, setter 주입
	class AiTVs implements TV {
	   
	   private Speaker speaker;
	   private String color;
	   private int price;
	   
	   public AiTVs() { System.out.println("~~ AiTVs Default 생성자 ~~"); }
	   
	   // xml에 생성을 요청해야하는데, 매개변수가 존재하는 생성자를 전달할 때 어떻게해야하지는 알기위해 하는거임
	   public void setSpeaker(Speaker speaker) { this.speaker=speaker; }
	   public void setColor(String color) { this.color=color; }
	   public void setPrice(int price) { this.price=price; }
	   
	   @Override
	   public void powerOn() { System.out.printf("~~ AiTVs powerOn : color=%s, price=%d \n",color,price); }
	   @Override
	   public void powerOff() { System.out.println("~~ AiTVs powerOff ~~"); }
	   @Override
	   public void volumeDown() { speaker.volumeDown(); }
	   @Override
	   public void volumeUp() { speaker.volumeUp(); }
	} //AiTVs

// speaker에 의존하는 클래스 생성
public class TVUser03_Speaker {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너(BeanFactory)생성 : spring에서의 Container(컨테이너)생성
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDi01_xml/app03.xml");	
		
		// 2. 필요한 객체를 전달받아 서비스 실행
		System.out.println("** 1) 고전적 방법 (직접 new : 소스 재컴파일) **");
		TV sstv = (TV)sc.getBean("sstv");	// xml 파일의 id로 호출
		sstv.powerOn();
		sstv.volumeUp();
		sstv.volumeDown();
		sstv.powerOff();
		
		System.out.println("** 2) IOC/DI -> 생성자 주입  **");
		TV lgtv = (TV)sc.getBean("lgtv");
		lgtv.powerOn();
		lgtv.volumeUp();
		lgtv.volumeDown();
		lgtv.powerOff();
		
		System.out.println("** 3) IOC/DI -> setter 주입 **");
		TV aitv = (TV)sc.getBean("aitv");
		aitv.powerOn();
		aitv.volumeUp();
		aitv.volumeDown();
		aitv.powerOff();
		
		sc.close();
			
	}
}

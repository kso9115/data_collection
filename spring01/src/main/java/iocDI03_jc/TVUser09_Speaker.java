package iocDI03_jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

//** Java Bean Configuration class를 이용한 DI
//=> Bean 컨테이너 : AnnotationConfigApplicationContext 사용
//=> Test02 : 스피커 1개 사용 TV

class Speaker {
	   public Speaker() { System.out.println("~~ Speaker Default 생성자 ~~"); }
	   public void volumeUp() { System.out.println("~~ Speaker volumeUp ~~"); } 
	   public void volumeDown() { System.out.println("~~ Speaker volumeDown ~~"); }
} //Speaker

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


class LgTVs implements TV {
	   
	private Speaker speaker;
	private String color;
	private int price;
	 
	public LgTVs() { System.out.println("~~ LgTVs Default 생성자 ~~"); }
	   
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
//@Component("aitv")
class AiTVs implements TV {
		
	@Autowired	// configuaration에서 bean을 받지 않을 경우 컨테이너 내부에서 인식할 수 없음
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
public class TVUser09_Speaker {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너(BeanFactory)생성 : spring에서의 Container(컨테이너)생성
		AbstractApplicationContext sc = new 
				AnnotationConfigApplicationContext(JavaConfig02.class);
		
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

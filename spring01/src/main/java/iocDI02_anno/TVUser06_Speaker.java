package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

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
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//** @Autowired
//=> 맴버변수에 생성된(Type에 해당하는) 주소값을 찾아 있으면 전달
// 그러므로 Speaker 는 반드시 이미 생성되어 있어야 함.
//=> 자동주입: 있으면 주입 , 없으면 Exception 발생

//=> 적용순위
//1) 주로 멤버변수 위에 선언하며,
//  스프링 컨테이너는 해당변수의 타입을 체크하고,
// 그 타입의 객체가 메모리에 존재하는지 확인하여
// 해당 변수에 대입 해준다.
//2) 동일타입의 객체가 둘이상이면  @Qualifier 에 명시된 객체 주입
//3) 동일타입의 객체가 둘이상이고  @Qualifier 없으면 둘중 선택할수 없으므로 오류

//** Test : Speaker 생성 안되어있는 경우 오류메시지 확인
//=> ...Injection of autowired dependencies failed;.....

//** required 속성
//=> true : 해당 타입의 등록된 빈이 없을때 주입시 익셉션이 발생
//=> false: 해당 타입의 등록된 빈이 없을때 익셉션이 발생하지 않음 
//   ( 있으면 주입 , 없으면 null )    
//
//1) @Autowired(required=true) : default 
//=> BeanCreationException <- NoSuchBeanDefinitionException: .... as autowire...  
//2) @Autowired(required=false) 
//=> 실행문에서 접근시 NullPointerException 발생

//@Component
class Speaker {
	   public Speaker() { System.out.println("~~ Speaker Default 생성자 ~~"); }
	   public void volumeUp() { System.out.println("~~ Speaker volumeUp ~~"); } 
	   public void volumeDown() { System.out.println("~~ Speaker volumeDown ~~"); }
} //Speaker

//1) 고전적 방법 (직접 new : 소스 재컴파일)
//=> Speaker 생성 : 직접코드에서
//@Component("sstv") // getBean할 때 가져가는 아이디
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
//=> @Autowired
//- 자동주입, 맴버변수에 적용,  
//- Only 전달만 : 생성문 "~ = new Speaker()" 에서 "=" 역할만
//	그러므로 해당 객체는 반드시 생성 되어있어야 함.   

//오토와이드의 경우는 멤버별로 지정해야한다.
//	@Autowired(required=false)
//	private String color;
//	@Autowired
//	private int price;

//=> Autowired 의 실행규칙 
//-> 메모리에서 타입이 일치하는 객체를 찾아 있으면 제공
//-> 없으면 Autowired 구문에서 오류 : UnsatisfiedDependencyException: Error creating bean wit....
//-> required = false ( default 값은 true -> 없으면 Exception 발생 )
//	못찾으면 Exception 을 발생하지않고 null return 
//	그러므로 인스턴스 호출구문( volumeUp, volumeDown) 에서 NullPointExceptiopn 발생

//@Component("lgtv")
class LgTVs implements TV {
	   
	// 메모리값이 일치하는 타입이 있으면 호출
	// 위에서 speaker 객체가 이미 만들어져있어야한다는것 : speaker 객체 만들어져 있음
	@Autowired
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
//@Component("aitv")
class AiTVs implements TV {
		
	@Autowired
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
public class TVUser06_Speaker {
	public static void main(String[] args) {
		// 1. 스프링 컨테이너(BeanFactory)생성 : spring에서의 Container(컨테이너)생성
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDI02_anno/app05.xml");	
		
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

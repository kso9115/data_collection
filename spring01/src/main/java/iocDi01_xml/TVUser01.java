package iocDi01_xml;

// ** Test1. 절차지향
class SsTv {
	public void turnOn() {System.out.println("SsTv 티비켜짐");}
	public void turnOff() {System.out.println("SsTv 티비꺼짐");}
	public void soundUp() {System.out.println("SsTv 소리키움");}
	public void soundDown() {System.out.println("SsTv 소리줄임");}
}// SsTv

class LgTv {
	public void powerOn() {System.out.println("LgTv 티비켜짐");}
	public void powerOff() {System.out.println("LgTv 티비꺼짐");}
	public void volumeUp() {System.out.println("LgTv 소리키움");}
	public void volumeDown() {System.out.println("LgTv 소리줄임");}
}// LgTv

// ** Test1. 절차지향
// => interface, 구현을 강제(메서드명 동일)
interface TV{
	void powerOn();
	void powerOff();
	void volumeUp();
	void volumeDown();
}

// Test3. Factory 패턴(IOC/DI 입문) : 인터페이스를 제공
// => getBean 메서드의 매개변수로 요청을 전달한다.
class BeanFactory {
	public TV getBean(String tv) {
		if(tv!=null && tv.equals("ss")) return new SsTVi(); 
		else if(tv!=null && tv.equals("lg")) return new LgTVi();
		else return null;
	}//getBean
}//BeanFactory

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

public class TVUser01 {
	public static void main(String[] args) {	
		// ** Test1. 절차지향 프로세스
		// => 작업 중 TV 교체가 필요할 때 : 기존 Sstv 코드 재활용 불가, 아예 새로운 객체를 생성
		System.out.println("** 절차지향을 테스트하는 중입니땨. **");
//		SsTv tv = new SsTv();
//		tv.turnOn();
//		tv.soundDown();
//		tv.soundUp();
//		tv.turnOff();

		LgTv tv = new LgTv();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

		// ** Test2. 객체지향
		// => 기본특징 : 캡슐화, 상속, 추상, 다형성(*)
		// => 다형성 적용
		// -> 관련성이 없는 두객체를 하나의 인터페이스로 묶어줌, 규칙성 부여
		// -> 인터페이스에 정의된 메서드만 사용했다는 의미 (그러므로 교체가능)
		// -> TV 교체 필요 : 우측의 클래스만 교체 (단, 소스코드수정-재컴파일 이 필요함)
		
		System.out.println("** 객체지향을 테스트하는 중입니땨 **");
		TV tvi = new LgTVi(); // SsTVi() , LgTVi()
		tvi.powerOn();
		tvi.volumeUp();
		tvi.volumeDown();
		tvi.powerOff();
		
	    // ** Test3. 소스코드 수정없이 실시간 객체 교체
	    // => 객체를 생성해서 교체해줄 역할자가 필요 : Factory 패턴 (IOC/DI 입문)
	    // => user 클래스의 요구사항(필요한 클래스, 의존성_Dependency) 을 Factory 에게 전달하는방법
	    // => 3가지 : xml, @, JavaConfig (JavaCode)
		System.out.println("** Factory 패턴(IOC/DI 입문)을 테스트하는 중입니땨 **");
		BeanFactory bf = new BeanFactory();
		TV tvf = bf.getBean(args[0]);	// String[] args 메인메서드의 매개변수를 이용하기
		// => 소스코드 수정없이 실시간으로 요청을 전달 받음 : main의 매개변수 활용
		if(tvf!=null) {
			tvf.powerOn();
			tvf.volumeUp();
			tvf.volumeDown();
			tvf.powerOff();
		} else System.out.println("** TV를 선택하지 않았습니다. **");
		
		
		
	}// main
}// class

package controllerF;

import java.util.HashMap;
import java.util.Map;

//** ServiceFactory
//=> FrontController 가 요청한 서비스 컨트롤러 클래스를 제공

//** Map 적용
//=> HashMap 객체 정의 : 전역변수 (맴버)
//=> 자료 put : 생성자에 정의

//** 싱글톤 패턴 : 인스턴스를 단 1개만 허용
//=> 일반적인 경우 : 복수의 인스턴스 가능 
// Student s1 = new Student();
// Student s2 = new Student();
//=> 클래스 외부에서 인스턴스를 생성할 수 없도록 제한  

//** 방법
//=> 생성자를 private 으로 내부에서만 사용가능하도록하고
//=> 내부에서 getInstance() 메서드로 생성 제공해줌 
//=> 외부에서는 getInstance() 메서드를 통해서만 사용

// 요청이 들어오는대로 처리를 해준 후 내보내기
// 인스턴스를 단일로 허용하는 객체 : 외부에서 호출하지 못하도록 생성자 만들어야한다.
public class Ex03_ServiceFactory {
	
	// 1. Map 정의
	private Map<String,Ex04_Controller> mappings;
	
	// 2. 생성자(싱글톤 패턴) : 내부에서 생성해 공급하기 위해 private으로 정의
	private Ex03_ServiceFactory() {
		mappings = new HashMap<String,Ex04_Controller>();
		mappings.put("/mlist.fo", new Ex05_mList());
		mappings.put("/mdetail.fo", new Ex05_mDetail());
		
	}
	
	// 내부에서 인스턴스 생성 : 1단계와 2단계인 map의 경우에 모두 선언하여 사용해야하는 것.
	// 생성자를 변수에 담아주기
	// 인스턴스 제공을 위해선 public으로 정의한 getter를 통해 무한 공급
	// 참조형 변수를 주소값을 가져오는 것임
	private static Ex03_ServiceFactory instance = new Ex03_ServiceFactory();
	public static Ex03_ServiceFactory getInstance() { return instance; }

	// 싱글톤 패턴이 적용되지 ㅇ낳는다 : 메서드를 호출할 때마다 new를 통해 생성한다
	// public static Ex03_ServiceFactory getInstance() { return new Ex03_ServiceFactory(); }
	
	// 실행때마다 각각 다를 서비스를 실행하고, 뱉어내야하므로 인터페이스 타입이 들어와야한다.
	public Ex04_Controller getController(String mappingName) {
//		// 1단계
//		if("mlist.fo".equals(mappingName)) {
//			return new Ex05_mList();
//		} else if ("mdetail.fo".equals(mappingName)) {
//			return new Ex05_mDetail();
//		} else return null;
		
		// 2단계 : Map 적용
		return mappings.get(mappingName);
		
	}
	
	
}

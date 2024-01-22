package myDispatcher;

import java.util.HashMap;
import java.util.Map;

public class MyHandlerMapping {

	// 1) Map 정의
	private Map<String,MyController> mappings;
	
	// 2) 생성자 정의(싱글톤 패턴) : 내부에서 생성해 공급하기 위해 private으로 정의
	// => map 초기화 : put 메서드를 활용해 데이터 넣기
	private MyHandlerMapping() {
		mappings = new HashMap<String,MyController>();
		mappings.put("/mlist", new C01_mList());
		mappings.put("/mdetail", new C02_mDetail());
	}
	
	// 3) 내부에서 인스턴스 생성하여 제공(싱글톤타입)
	// getter 는 외부에서 호출하여 사용하기 위함으로 public으로 선언
	private static MyHandlerMapping instance = new MyHandlerMapping();
	public static MyHandlerMapping getInstance() { return instance; }
	
	// 4) Dispatcher_Servlet (FrontController)의 요청에 대한 컨트롤러 클래스의 인스턴스를 제공한다.
	// 컨트롤러 클래스의 인스턴스를 제공
	public MyController getController(String mappingName) {
		return mappings.get(mappingName);
	}

}

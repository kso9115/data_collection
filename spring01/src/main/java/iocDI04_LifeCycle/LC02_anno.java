package iocDI04_LifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

//** Bean(객체) 의 LifeCycle 
//=> 객체생성 -> 사용 -> 소멸
//=> 해당되는 특정시점에 자동실행 : xml, @, interface

//** Test2. Annotation, @
//=> @PostConstruct , @PreDestroy
//=> 실행 순서
// 생성자 -> @PostConstruct (자동호출) -> 사용...  
//    -> 컨테이너 Close -> @PreDestroy (자동호출)
@Component("lca")
class LifeCycleTestA {
	   public LifeCycleTestA() { System.out.println("~~ANNO TEST : LifeCycleTestA 생성자 ~~"); }
	   
	   @PostConstruct // 생성 직후에 호출
	   public void begin() { System.out.println("~~ANNO TEST : LifeCycleTestA PostConstruct ~~"); }
	   @PreDestroy	// 생성 직전에 호출 ??음?
	   public void end() { System.out.println("~~ANNO TEST : LifeCycleTestA PreDestroy ~~"); }
	   
	   public void login() { System.out.println("~~ANNO TEST : LifeCycleTestA login() ~~"); }
	   public void list() { System.out.println("~~ANNO TEST : LifeCycleTestA list() ~~"); }
	} //LifeCycleTest

public class LC02_anno {
	public static void main(String[] args) {
		
		AbstractApplicationContext sc = new 
				GenericXmlApplicationContext("iocDI04_LifeCycle/lc01.xml");
		LifeCycleTestA lc = (LifeCycleTestA)sc.getBean("lca");
		lc.login();
		lc.list();
		sc.close();
	}
}

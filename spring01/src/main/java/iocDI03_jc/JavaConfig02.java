package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java Bean Configuration class를 이용한 DI
//=> Test02 : 스피커 1개 사용 TV 
// -> 생성자를 이용한 주입,
// -> JC에서 xml 병행사용

//** JC 에서 xml 병행 사용하기 
//=> @ImportResource("iocDI03_jc/app09.xml")
//=> AiTVs 생성은 xml 로 

@ImportResource("iocDI03_jc/app09.xml")
@Configuration
public class JavaConfig02 {
	
	//1) 고전적 방법 (직접 new : 소스 재컴파일)
	@Bean	// 스프링 컨테이너에 의해 해당 메서드가 시행되고 인스턴스가 컨테이너에 값을 리턴한다.
	public TV sstv() {return new SsTVs();}
	
	//2) IOC/DI: 생성자 주입
	// lg 티비의 경우 스피커 객체를 생성해서 넣어줘야댐
	@Bean
	public TV lgtv() {return new LgTVs(new Speaker(),"Blue",9988000);}  
//	public TV lgtv() {return new LgTVs(sp(),"Blue",9988000);}  

//	@Bean	// bean 사용시 컨테이너에 전달되어져있기 때문에 aitv에서도 갖다쓸 수 있음
	public Speaker sp() { return new Speaker(); }

	//3) IOC/DI: JC 에서 xml 병행 사용
	//	=> jc에서 생성 후 Speaker는 @Autowired
	@Bean
	public TV aitv() { return new AiTVs(); }
}

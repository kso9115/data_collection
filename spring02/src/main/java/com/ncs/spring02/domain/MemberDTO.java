package com.ncs.spring02.domain;
//** DTO
//=> private 맴버변수
//=> getter/setter
//=> toString

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//** Lombok
//setter, getter, toString, 생성자 등을 자동으로 만들어줌

//컴파일 단계에서 애너테이션에 따라 여러가지 메소드나 코드를 자동적으로 추가해줌.
//=> 모든 필드의  public setter 와  getter 를 사용하는 일반적인 경우 유용하며, 
//=> 보안을 위해 setter 와  getter 의 접근 범위를 지정해야 하는 경우 등
//=> 대규모의 프로젝트에서 다양한 setter 와 getter code를 작성하는 경우에는 충분히 고려해야함. 

//=> @Data 즉, 다음 애너테이션을 모두 한번에 처리 한다.
//=> @Getter(모든 필드) : getter 생성하도록 지원
//=> @Setter(모든 필드-final로 선언되지 않은) : setter를 생성하도록 지원
//=> @ToString :  모든 필드를 출력하는 toString() 메소드 생성 

//@Data
//=> 정의된 모든 필드에 대한 
//Getter, Setter, ToString 과 같은 모든 요소를 한번에 만들어주는 애너테이션.
@AllArgsConstructor	// 모든 필드 값을 파라미터로 받아 초기화하는 생성자를 생성 : 디폴트 생성자는 없기때문에 만들어줘야한다.
@NoArgsConstructor	// 파라미터가 없는 디폴트 생성자 자동 생성
@Data
public class MemberDTO {
	// 1. private 멤버변수
	private String id;			// primary_key
	private String password;	// not null
	private String name;		
	private int age;		
	private int jno;
	private String info;
	private double point;
	private String birthday;
	private String rid;			// 추천인

	
	// lombok 애노테이션 활용하기
	// 2. getter/setter
	// 3. toString 오버라이딩
}
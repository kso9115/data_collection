package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 상속을 받아 확장

@AllArgsConstructor	// 모든 값을 초기화하는 생성자 : 디폴트 생성자는 없기때문에 만들어줘야한다.
@NoArgsConstructor	// 디폴트 생성자 자동 생성
@Data
public class JoDTO {
	// 1) 멤버 변수 정의 : 오버라이딩?
	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;
	
	private String name; // 조인을 위한 필드값 추가(롬복으로인해 게터세터필요없음)

	// 1) 생성자 정의 : 모든값을 초기화하는 생성자
	// 2) getter & setter
	// 3) toString
	// toString 문자열 자동완성 시 inherited method 선택 시 자동으로 상속된 요소까지 가져옴'
	
//	@AllArgsConstructor
//	public JoDTO(int jno, String jname, String captain, String project, String slogan) {
//		this.jno = jno;
//		this.jname = jname;
//		this.captain = captain;
//		this.project = project;
//		this.slogan = slogan;
//	}
//	
//	@NoArgsConstructor
//	public JoDTO() {
//	}
}



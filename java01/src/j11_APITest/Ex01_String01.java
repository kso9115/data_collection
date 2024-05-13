package j11_APITest;

/* ** 숙제: String, StringBuffer, StringBuiler 차이점 두줄 요약
 * string : 불변성 문자열 클래스로, 한번 값이 할당되면 바꿀 수 없음.
 * 문자열 연산 시, 새로운 String 인스턴스가 할당되는 방식.
 * 
 * StringBuffer : 가변성 문자열 클래스로, 한번 값이 할당 되었어도 다른 값으로 수정할 수 있고, 
 * 연산 등으로 공간이 부족하게 되더라도, 기존 버퍼의 크기를 늘려 유연하게 동작함.
 * 
 * StringBuilder : StringBuffer와 마찬가지로 가변성 문자열 클래스이고, 제공하는 메서드도 유사함.
 * 차이점은 동기화 여부로, StringBuffer는 동기화를 지원하나, StringBuilder는 동기화를 지원하지 않음.
 */

//** String 1
//1) 선언(정의) 과 인스턴스
//=> 묵시적, 명시적
//=> 참조형변수 : 주소값을 가지고 있음
//=> 불변(immutable) 속성을 가짐.

public class Ex01_String01 {

	public static void main(String[] args) {
		// 1) 묵시적선언
		String s1 = "그린";
		String s2 = "그린";
		
		// 2) 명시적선언
		String s3 = new String("그린") ;
		String s4 = new String("그린") ;
		
		// 3) 인스턴스(주소값) 비교
		// 3.1) 위에 정의한 default 값으로 Test
		
		// 3.2) s1, s3 값 변경후 Test
		//s1="파랑"; s3="파랑";
		//s4=s3;
		// => 묵시와 묵시 : T
		if (s1==s2) System.out.println("** s1==s2 : true");
		else System.out.println("** s1==s2 : false");
		// => 묵시와 명시 : F
		if (s1==s3) System.out.println("** s1==s3 : true");
		else System.out.println("** s1==s3 : false");
		// => 명시와 명시 : F
		if (s3==s4) System.out.println("** s3==s4 : true");
		else System.out.println("** s3==s4 : false");
		
		// 4) 값(리터럴) 의 비교
		if (s1.equals(s2)) System.out.println("** s1.equals(s2) => true");
		else System.out.println("** s1.equals(s2) => false");
		if (s1.equals(s3)) System.out.println("** s1.equals(s3) => true");
		else System.out.println("** s1.equals(s3) => false");
		if (s3.equals(s4)) System.out.println("** s3.equals(s4) => true");
		else System.out.println("** s3.equals(s4) => false");

		// 5) length(글자수), 문자열연산(+ 만가능) 
		s2="안녕하세요 ~~~";
		s3="My name is Green";
		System.out.println("** 한글 글자수 => "+s2.length());
		System.out.println("** 영문 글자수 => "+s3.length());
		int i=100, j=123;
		System.out.println("** 문자열 연산 => "+s1+s2+s3+s4+i+j);
		System.out.println("** 문자열 연산(우선순위) => "+s1+s2+s3+s4+(i+j));

	} //main

} //class

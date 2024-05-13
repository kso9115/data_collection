package j11_APITest;

public class Ex08_Math {

	public static void main(String[] args) {
		 
		int a=100;
		int b=1000;		
		System.out.println("** -10 의 절대값 => "+Math.abs(-10));
		System.out.println("** -12.345f 의 절대값 => "+Math.abs(-12.345f));
		System.out.println("** max => "+Math.max(a,b));
		System.out.println("** min => "+Math.min(a,b));
		System.out.println("** random => "+Math.random());
		// a:100 ~ b:1000 사이의 정수중 선택하도록 
		System.out.println("** random int => "+ (int)(Math.random()*b+a)); //????
		// ceil : 크거나 같은 가장 작은 정수
		System.out.println("** ceil 올림값0 => "+Math.ceil(20.0));   //20.0
		System.out.println("** ceil 올림값1 => "+Math.ceil(20.123)); //21.0 
		System.out.println("** ceil 올림값2 => "+Math.ceil(20.678)); //21.0
		// floor : 작거나 같은 가장 큰 정수
		System.out.println("** floor 내림값0 => "+Math.floor(20.0));   // 20.0
		System.out.println("** floor 내림값1 => "+Math.floor(20.123)); // 20.0 
		System.out.println("** floor 내림값2 => "+Math.floor(20.678)); // 20.0
		// rint : 가장 가까운 정수를 double 로 반환
		System.out.println("** rint0 => "+Math.rint(20.0));   // 20.0 
		System.out.println("** rint1 => "+Math.rint(20.123)); // 20.0  
		System.out.println("** rint2 => "+Math.rint(20.678)); // 21.0 

		// 오토 박싱(auto-boxing) Test
		// => Wrapper 객체와 기본자료형 사이의 변환을 자동으로 수행
		Integer wrapI = a;  // Integer <- int  자동 형변환됨
		Double  wrapD = 123.456; // 자동 형변환됨
		System.out.printf("** I = %d , D = %f \n", wrapI, wrapD);

	} //main

} //class

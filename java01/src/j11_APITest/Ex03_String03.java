package j11_APITest;

import java.util.Arrays;

//** String 3
//=> String 의 메서드
//=> 문자열 변경
//	-> toLowerCase, toUpperCase, replace, split
//	-> Type 변경: int to String, String to int 

// static : 메서드와 필드에만
// final class는 후손을 만들 수 있음(상속이 불가능하다)
// 대표적 종단 클래스 : string (final string)

public class Ex03_String03 {

	public static void main(String[] args) {
		// 1) 문자열 변경
		String name="홍길동" ;
		String city="~~~~~SeoulKorea~~eee";
		String country="     !I!Love!Korea!     ";
		
		System.out.printf("** toLowerCase => %s, %s \n", city.toLowerCase(), name.toLowerCase());
		System.out.printf("** toUpperCase => %s, %s \n", country.toUpperCase(), name.toUpperCase());
		System.out.printf("** value 확인 => %s, %s, %s \n", country, city, name);
		// => 복습 : city 에서 Korea 만 모두 대문자로 출력하기
		System.out.printf("** Korea만 대문자로 => %s \n", city.substring(city.indexOf("K"), city.indexOf("a")+1).toUpperCase());
		
		System.out.printf("** replace => %s, %s, %s \n", country.replace("Korea","대한민국"),
						 city.replace("e", "Apple"), name.replace("이","김"));  // 없으면 실행 안할뿐 오류없음
		// 2) split
		// => 인자로 전달된 기호를 기준으로 잘라 배열에 담아줌 
		String[] ss = country.split("!"); 
		System.out.println("** split 1 => "+Arrays.toString(ss));
		// => 양옆의 스페이스를 제거하고 처리하기
		ss = country.trim().split("!");
		System.out.println("** split 2 => "+Arrays.toString(ss));
		
		// 3) 형변환
		// => String <-> int
		// => String <-> double , float
		// 3.1) String -> int, double, float (Wrapper Class 이용)
		String num1 = "12345";
		String num2 = "100";
		System.out.println("** Test1 문자열연산 => "+num1+num2);
		System.out.println("** Test2 산술연산(int) => "+(Integer.parseInt(num1)+Integer.parseInt(num2)));
		num2 = "100a";  // -> 런타임오류 : java.lang.NumberFormatException
		//System.out.println("** Test2 산술연산 => "+(Integer.parseInt(num1)+Integer.parseInt(num2)));
		
		num1="123.456";
		num2="200.300f"; // "200.300" : parseFloat 에 적용됨 
		num2="200.300t"; // -> 런타임오류 : java.lang.NumberFormatException
		System.out.printf("** Test2 산술연산 : double= %f , float= %f \n",Double.parseDouble(num1), Float.parseFloat(num2));
		
		// 3.2) int, double..(숫자) -> String
		int i=10, j=20;
		// num1 = i+j ; //-> Type mismatch Error
		num1 = i+j+"" ; // 30  -> 수식에 문자가 포함되면 문자열연산
		num2 = ""+i+j ; // 1020
		System.out.println("** Test3 num1 => "+num1);
		System.out.println("** Test3 num2 => "+num2);
		// => 기본 자료형의 값을 문자열로 바꾸기 : valueOf()
		//    모든 기본자료형 적용 가능 
		//    static String valueOf(boolean b)
		//    boolean, char, float, int, long ..
		double d = 1234.567;
		// num1=d; // Type mismatch Error
		num1 = String.valueOf(d);
		num2="true" ; // => "true" boolean Type 과는 무관한 String
		num2 = String.valueOf(true);   
		num2 = String.valueOf((i>j)); 
		// 비교 : num2=true -> Type mismatch  
		
		// 4) 비교 : compareTo 
		// => 사전 순서로 비교 
		//    같으면 0, 이전이면 음수, 이후면 양수 return
		// => 문자열의 순서가 일치하면 : 다른 글자 갯수를 표시함 (abc:ab , abc:abcabcd)
		// => 완전다른 문자는 알파벳 순서의 차이를 return
		//   ( comparTo는 같은 위치의 문자만 비교하기 때문에, 
		//     첫번째 문자부터 순서대로 비교해서 다를 경우 바로 아스키값을 기준으로 비교처리를 한다. )
		//    abc : japan ->-9  abc:korea -> -10  abc:love -> -11 
		// => https://mine-it-record.tistory.com/133
		name="abc";
		System.out.println("** 비교1 => "+name.compareTo("abc")); // 0
		System.out.println("** 비교2 => "+name.compareTo("ab"));  // +1
		System.out.println("** 비교3 => "+name.compareTo("a"));   // +2
		System.out.println("** 비교4 => "+name.compareTo("bc"));  // -1
		System.out.println("** 비교5 => "+name.compareTo("c"));   // -2
		System.out.println("** 비교6 => "+name.compareTo("abcabc")); // -3
		System.out.println("** 비교7 => "+name.compareTo("abcabcd"));// -4
		System.out.println("** 비교8 => "+name.compareTo("love"));   
		// => if 에 적용시 주의 
		//    if ( name.compareTo(num2)==0) { }
		
		// 5) 포함확인
		System.out.println("** 포함확인1 => "+country.contains("Korea")); // true
		System.out.println("** 포함확인2 => "+country.contains("korea")); // flase
	} //main

} //class

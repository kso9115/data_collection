package j11_APITest;

//** String 2
//=> String 의 메서드
//=> 문자열 추출
//   charAt, length, indexOf, lastIndexOf, substring, trim
//=> 메서드 적용시 변수의 값이 변경되는것이 아니고, 메서드가 적용결과를 return 할뿐. 

public class Ex02_String02 {

	public static void main(String[] args) {
		String name="홍길동";
		String city="  ~~~SeoulKorea~~~  ";
		String country="     I Love Korea     ";
		
		System.out.printf("** charAt => %s, %s \n", name.charAt(1), city.charAt(10));
		System.out.printf("** indexOf => %d, %d, %d \n", name.indexOf("홍"), city.indexOf("e"), country.indexOf("o"));
		System.out.printf("** indexOf => %d, %d, \n", name.indexOf("김"), city.indexOf("e",10));
		// 없으면 -1(음수) , indexOf("e",fromIndex) 특정index 이후부터적용가능, 여러개면 첫번째것 return
		System.out.printf("** lastIndexOf => %d, %d, %d \n", name.lastIndexOf("홍"), city.lastIndexOf("e"), country.lastIndexOf("o"));
		System.out.printf("** substring => %s, %s \n", country.substring(0,7), city.substring(12)); //"01234I " , "rea~~~~~" 
							// substring(0,7) : 인덱스 0 부터 7 이전까지 (0 부터 7개)
		// trim : 앞 뒤의 스페이스 제거 => 변수의 값이 변경되는것이 아님
		System.out.printf("** trim => %s, %s \n", country.trim(), city.trim()); 
		// 변경된 값을 해당 변수에 적용하려면 -> country = country.trim();
		// ** 실습
		// 1) 아래 url 에서 화일명만 추출해서 출력하세요~~ 
		// 2) 아래 url 에서 화일의 확장자만 추출해서 출력하세요~~ 
		// 3) 위의 city, country 에서 "Korea" 만 추출해서 출력 해보세요
		String url="C:\\MTest\\myWork\\Java01\\src\\j10_APITest\\Ex02_String02.java";
		System.out.printf("** 1) 화일명만 => %s \n", url.substring(url.lastIndexOf("\\")+1, url.lastIndexOf("."))); 
		System.out.printf("** 2) 확장자만 => %s \n", url.substring(url.lastIndexOf(".")+1)); 
		System.out.printf("** 3) Korea만 => %s, %s \n", city.substring(city.indexOf("K"), city.indexOf("a")+1)
													  , country.substring(country.indexOf("K"), country.indexOf("a")+1)); 
		
		//System.out.printf("** 1) 화일명만 => %s \n", url.substring(url.indexOf("E"), url.lastIndexOf("."))); // 70점
		
	} //main

} //class

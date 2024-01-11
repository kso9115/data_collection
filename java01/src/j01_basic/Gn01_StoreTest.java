package j01_basic;

import jdbc02.JoDTO;

// test용 Class : 모든 물건을 판매하는 클래스
class Store {

	// 모든 타입을 받을 수 있는 데이터,get,set
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
} // store

// test 용 제네릭 Class : <T> 확정되지않은 타입
class StoreG<T> {
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
} // storeG

// test 용 제네릭 Class : 배열을 만들어주는 클래스
class GenArray<T> {
	private T[] arr;

	public T[] getData() {
		return arr;
	}

	public void setData(T[] arr) {
		this.arr = arr;
	}

	public void arrayPrint() {
		for (T a : arr) {
			System.out.println(a);
		} // for
	} // arrayPrint
} // GenArray

// test 용 제넥릭 클래스 : Number 클래스의 상속을 받은 클래스
class Box<T extends Number>{
	private T data;
	public void setData(T data) { this.data = data; }
	public T getData() { return this.data; }
}

public class Gn01_StoreTest {

	public static void main(String[] args) {

		// 1. Object를 이용한 기존의 방식
		Store s1 = new Store();
		s1.setData("짜장면"); // 기본 자료형은 자동 형변환 발생(auto boxing)
		s1.setData(123); // int -> Integer 취급(기본 자료형은 래퍼 클래스를 가진다)
		s1.setData(123.456); // double -> Double
		s1.setData(123.456f);
		s1.setData(123456789L);
		

		// jdbc02 의 JoDTO를 임포트 한 후 조 데이터 삽입하기
//		s1.setData(new JoDTO(7,"Banana",77,"화이팅","Generic Test"));
//		System.out.println("** Test 1 => "+s1.getData());

		// 단점 Test -> 보완하기 위해 15행 제네릭 타입의 클래스 지정
		// 컴파일 시에서는 문제가 없으나, runtime 시 오류가 발생한다. : java.lang.ClassCastException
		// long 타입이 string이 될 수 없음
//		String s = (String) s1.getData();
//		System.out.println("** 단점 Test => " + s);

		// 2. Generic StoreG
		StoreG g1 = new StoreG(); // 사용은 가능하나, Type 생략 시 Object와 동일하다

		StoreG<String> g2 = new StoreG<String>();
		g2.setData("string type만 가능!");
//		g2.setData(12345); // 컴파일 오류 발생

		StoreG<Integer> g3 = new StoreG<Integer>();
		g3.setData(12345);
//		g3.setData(123.456); // 컴파일 오류 발생

		// Number의 상속을 받은 제네릭 클래스 : string 타입은 컴파일 에러 발생
		Box<Integer> b1 = new Box();
		b1.setData(123456);
//		Box<String> b2 = new Box(); // 에러
		
		
		// 3. Generic GenArray : 배열
		// 1) String
		String[] ss = { "가", "나", "Do", "Re", "Mi" };
		GenArray<String> ga1 = new GenArray<String>();
		ga1.setData(ss);
		ga1.arrayPrint();

		// 2) Integer
		Integer[] ii = { 1, 2, 3, 4, 5 };
		GenArray<Integer> ga2 = new GenArray<Integer>();
		ga2.setData(ii);
		ga2.arrayPrint();

		// 3) Character
		Character[] cc = { 'A', 'a', 'B', '다', '여' };
		GenArray<Character> ga3 = new GenArray<Character>();
		ga3.setData(cc);
		ga3.arrayPrint();

		// 4) 객체
		JoDTO[] jj = { new JoDTO(), new JoDTO(), new JoDTO() };
		GenArray<JoDTO> ga4 = new GenArray<JoDTO>();
		ga4.setData(jj);
		ga4.arrayPrint();
	} // main
} // class

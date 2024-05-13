package j11_APITest;

//** StringBuilder
//=> StringBuffer 와 동일하나 동기화 키워드를 지원하지 않으므로 
//   "thread-safe" 하지않으나 단일쓰레드에서의 성능은 StringBuffer 보다 뛰어남.

public class Ex06_StringBuilder {

	public static void main(String[] args) {
		 
		StringBuilder sb = new StringBuilder("가나다");
		sb.append(123).append("ABCD").append(false);
		System.out.println("** Before sb => "+sb);
		System.out.println("** sb.toString => "+sb.toString());
		
		sb.insert(1, true);
		sb.insert(sb.length(),"END");
		System.out.println("** After sb => "+sb);
	} //main

} //class

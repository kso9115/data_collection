package j16_thread;

//** 병렬처리 실습 I.
//=> 1~100 까지의 합을 구하는것을 멀티스레드로 처리하기 
//=> Sum interface , Adder 는 Thread 상속

interface Sum {
	void addNum(int i);
	int getNum();
} //interface

// ** Adder
// => 1~100 까지의 합을 멀티스레드 (2개) 로 처리하는 클래스 
// => 조건 : interface Sum 구현 활용
//          연산구간은 생성자로 전달받아 초기화 한다.
//          연산 결과를 담을 변수는 num 으로 정의한다. 
class Adder extends Thread implements Sum {
	int start, end, num ;
	public Adder(int start, int end) {
		this.start=start;
		this.end=end;
		num=0;
	}
	@Override
	public void run() {
		System.out.printf("* Adder Start : %d ~ %d **\n",start,end);
		for(int i=start; i<=end; i++) {
			addNum(i); //num+=i;
			System.out.println("** start 값 => "+start);
	      } //for
	} //run
	@Override
	public void addNum(int i) { num+=i; }
	@Override
	public int getNum() { return num; }
} // Adder

public class Ex03_joinThread {

	public static void main(String[] args) {
		// 생성
		Adder ad0 = new Adder(1,100); // 결과 확인용
		Adder ad1 = new Adder(1,50);
		Adder ad2 = new Adder(51,100);
		// 실행
		ad0.run();  // Thread 와 무관하게 실행
		ad1.start();  // 1 ~ 50
		ad2.start();  // 51 ~ 100 
		/* join 적용하기  */
		try {
			ad1.join();
			ad2.join();
			// join():해당 쓰레드가 종료될때 까지 상위 쓰레드 실행 멈춤.
			// => ad1, ad2 가 종료 되어야 다음 문장 실행됨
		} catch (Exception e) {
			System.out.println("** main join_Exception => "+e.toString());
		} 
		// join : 상위 스레드를 멈추게할 수 있음
		// 메인이 실행시킨 스레드에 조인 메서드를 적용했을 때 상위(즉 메인)은 멈춰있음
		// 즉 join메서드가 적용된 후 메인이 실행된다
		// join 메서드는 checked Exception을 발생시킬 수 있으므로 try~catch 필요
		
		// 결과 확인
		System.out.println("** ad0 결과 => "+ad0.getNum());
		System.out.println("** ad1+ad2 결과 => "+(ad1.getNum()+ad2.getNum()));
		
		System.out.println("** Program_main() Stop **");
	} //main

} //class

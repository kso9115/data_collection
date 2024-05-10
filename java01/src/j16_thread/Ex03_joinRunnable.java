package j16_thread;

//** 병렬처리 실습 II.
//=> 1~100 까지의 합을 구하는것을 멀티스레드로 처리하기 
//=> class Sum2 , Adder2 클래스는 Runnable을 구현

//** Sum2
//=> 일반 클래스
//=> num, void addNum(int i), int getNum() 구현

//** Adder2
//=> 1~100 까지의 합을 멀티스레드 (2개) 로 처리하는 클래스 
//=> 조건 : class Sum2 을 상속받아 활용
//       연산구간은 생성자로 전달받아 초기화 한다.
//       연산 결과를 담을 변수는 num 으로 정의한다. 
class Sum2 {
	int num;
	
	Sum2() { num=0; }
	void addNum(int i) { num+=i; }
	int getNum() { return num; } 
} //Sum2 

class Adder2 extends Sum2 implements Runnable {
	int start, end;
	
	public Adder2(int start, int end) {
		this.start=start;
		this.end=end;
	}
	@Override
	public void run() {
		System.out.printf("* Adder2 Start : %d ~ %d **\n",start,end);
		for(int i=start; i<=end; i++) {
			addNum(i); // 조상에서 호출됨
			System.out.println("** start 값 => "+start);
	      } //for
	} //run
	
} //Adder2

public class Ex03_joinRunnable {

	public static void main(String[] args) {
		// ** 생성
		Adder2 ar0 = new Adder2(1,100);
		
		// Thread 적용을 위해서 Thread 로 재생성 
		Adder2 ar1 = new Adder2(1,50);
		Adder2 ar2 = new Adder2(51,100);
		Thread ad1 = new Thread(ar1);  
		// new Thread(new Adder2(1,50)) -> 조상메서드 접근불가
		Thread ad2 = new Thread(ar2);  
		
		// ** 실행
		ar0.run();  // Thread 와 무관하게 실행
		ad1.start();  // 1 ~ 50
		ad2.start();  // 51 ~ 100 
		try {
			ad1.join();
			ad2.join();
		} catch (Exception e) {
			System.out.println("** main join_Exception => "+e.toString());
		}
		// 결과 확인
		System.out.println("** ad0 결과 => "+ar0.getNum());
		System.out.println("** ad1+ad2 결과 => "+(ar1.getNum()+ar2.getNum()));
		
		System.out.println("** Program_main() Stop **");
	} //main
} //class

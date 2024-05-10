package j16_thread;

//** 과제 : 동기화 필요성 Test 
// => Increment 클래스 만들기
//    int num , getNum(), increment() { num++; }
// => InThread 클래스 만들기
//    위 Increment 클래스의 increment 메서드를 이용하여 10000번 처리하기
// => 이 클래스를 3개의 Thread 를 나누어 실행하여 30000번 처리했을때의 결과 출력하기 

//** 동기화 (Thread Synchronization) 
//=> 1. 메서드에 synchronized 선언
//=> 2. 메서드에 synchronized block(영역) 을 지정
//=> 3. wait() , notify() , notifyAll()
//      -> 동기화된 블럭내에 적용하여 한번에 하나의 쓰레드만 호출할 수 있도록 해줌

class Increment {
	int num=0;
	
	//public void increment() { num++; }
	public synchronized void increment() { num++; }
	// 실행중인 스레드가 완전히 작업을 마칠때 까지 다른 스레드가 접근하지 못하도록 함.
	
	public int getNUm() { return num; } 
} //Increment 

class InThread extends Thread {
	Increment inc;
	// 생성자로 초기화
	InThread(Increment inc) { this.inc=inc; }
	
	@Override
	public void run() {
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				inc.increment();
			} // for_j
		} // for_i
	} //run
} //InThread

public class Ex05_sync01Method {

	public static void main(String[] args) {
		// 생성
		Increment inc = new Increment(); 
		InThread in1 = new InThread(inc);
		InThread in2 = new InThread(inc);
		InThread in3 = new InThread(inc);
		
		// ** 동기화 되어 있지 않은 경우
		// => increment() 메서드의 num++ 이 완료되지 않은 상태에서
		//    다른 스레드가 값을 사용하게되어 부정확한 결과를 만들게 됨
		// 1) start 만 한 경우 
		// => main 이 먼저 종료됨 ,  값 부정확
		in1.start();
		in2.start();
		in3.start();
		// 2) join 추가 
		// => 실행 순서는 순차적으로 진행했으나 여전히 결과는 부정확
		//    동기화 필요함
		try {
			in1.join();
			in2.join();
			in3.join();
		} catch (Exception e) {
			System.out.println("** main Exception => "+e.toString());
		}
		
		System.out.println("main  결과 => "+inc.getNUm());  // 30000 => 예측불가
		System.out.println("** Main Stop **");
	} //main
} //class

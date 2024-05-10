package j16_thread;

//** Thread 클래스 구현
//=> Thread 를 적용할 수 있는 클래스를 구현
//1) Thread 클래스 상속
//2) Runnable interface 구현

//** 계층구조 : Runnable interface -> Thread 클래스
//=> Thread 클래스에 Thread 를 지원하는 다양한 메서드 들이 정의 되어있음 
//=> Thread 클래스 가 Runnable 을 구현함. 
//   public class Thread implements Runnable { ....   }

//=> Thread 클래스의 생성자 중 Runnable을 매개변수로 하는 생성자가 있음
//	 -> Runnable 을 구현한 MyThread02 생성시 이용됨
//      public Thread(Runnable target) {
//	         this(null, target, "Thread-" + nextThreadNum(), 0);
//		}

//** run() 메서드
//=> thread 실행의 주체 메서드
//   생성된 thread 의 main 메서드
//=> thread 를 통해 실행하려는 기능 (객체가 해야되는 일) 을 여기에 작성함.
//=> start() 메서드 호출에 의해 실행됨.
//   자신의 일을 다 마치면 (run 메서드의 종료) 자동으로 소멸됨   
//** start() 메서드
//=> Thread 를 시작함 (Runnable 상태로)

//** 실습1. Thread 클래스 상속
class MyThread01 extends Thread {
	@Override
	public void run() {
		//super.run();
		for (int i=0; i<50; i++) {
			System.out.printf("** Thread01 i=%d, Thread_Name=%s \n", i, getName());
			// => 조상인 Thread 에 정의된 getName() 호출
		} //for
	} //run()
} //MyThread01

//** 실습2. Runnable interface 구현
class MyThread02 implements Runnable {
	
	@Override
	public void run() {
		for (int i=0; i<50; i++) {
			System.out.printf("** Thread02 i=%d, Thread_Name=%s \n", i, Thread.currentThread().getName());
			// => 조상인 Thread 에 정의된 getName() 호출
		} //for
	} //run()
	
} //MyThread02

public class Ex01_threadBasic {

	public static void main(String[] args) {
		// Test1) Thread 
		// => 1.1) 생성
		MyThread01 t01 = new MyThread01();
		Thread t02 = new MyThread01(); // 다형성적용가능
		
		// => 1.2) 실행
		// => start() 호출 : thread Start -> run() 메서드 실행
		//t01.start();
		//t02.start();
		
		// => 비교 : run() 호출
		// => multi thread 는 실행되지 않고 main 이 일반 메서드 호출 & 실행.  
		//    thread 적용없이 순서대로 실행 
		//t01.run();
		//t02.run();
		
		// Test2) Runnable
		// => 생성 1단계 : 일반클래스 (필요한 Thread 메서드 접근불가)
		MyThread02 r01 = new MyThread02();
		//Runnable r02 = new MyThread02();
		// => 생성 2단계 : Thread 클래스 화
		// => Thread 클래스의 생성자 중 Runnable을 매개변수로 하는 생성자가 있음
		//    생성자 Thread(Runnable target)
		Thread tr01 = new Thread(r01);
		Thread tr02 = new Thread(new MyThread02());
		tr01.start();
		tr02.start();
		
		System.out.println("** Program 정상종료 **");

	} //main

}//class

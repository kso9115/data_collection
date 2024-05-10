package j16_thread;

//** 스레드 종료
//=> run()이 종료하면 스레드 종료
//=> 타 스레드에 의한 강제종료 Test (ppt 35p)

//***  InterruptedException
//=> 말 그대로 Thread가 중단되었을 때 발생하는 예외.
//=> interrupt()메소드를 사용해 Thread를 중단시킬 때도  발생함.
//=> wait()메소드나 sleep() 메소드를 사용해 Thread가 대기 상태로 들어갔다가
//   깨어나지 못 할때 발생하는 예외 또한,

//** InterruptedException 참고 사이트
// http://happinessoncode.com/2017/10/09/java-thread-interrupt/

class PrintThread extends Thread {
	@Override
	public void run() {
		// Test1) Exception 없으면 무한 실행
		// => 해결 1
		//    - Exception 발생시킴 -> catch 블럭으로 분기 유도 
		//      타 스레드(main)에 의해 InterruptedException 발생시킴 -> interrupt()메소드를 사용
/*		try {
			while(true) {
				System.out.println("** Test 1 **");
				sleep(1);
			} //while
		} catch (InterruptedException e) {
			System.out.println("** run InterruptedException => "+e.toString());
			System.out.println("** run interrupted() 확인 => "+interrupted());
			// 무조건 메서드 종료 
			// return; -> 아래 자원정리구문 필요없음
		} //catch
*/		
		// Test2) interrupted() 를 이용
		// => Interrupt 확인 가능 메서드
		//    interrupted() -> static 메서드 , isInterrupted() -> 인스턴스메서드
		// => 그러나 Exception 처리시 또는 isInterrupted(), interrupted() 호출시
		//    바로 clear 되어 false 로 출력됨.
		while(true) {
			System.out.println("** Test 2 **");
			if ( Thread.interrupted() ) {
				System.out.println(" ** run Test2 interrupted() => "+interrupted());
				break;
			}
		} //while
		System.out.println(" ** 자원 정리 **");
		System.out.println(" ** run 종료 **");
	} //run
	
} //PrintThread

public class Ex04_stopInterrupt {

	public static void main(String[] args) {

		PrintThread pt = new PrintThread() ;
		pt.start();
		// Test1) Exception 없으면 무한 실행
		// => 해결 1
		// => main Thread 지연, pt.interrupt() 실행
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("** main InterruptedException => "+e.toString());
		}
		System.out.println("** main_interrup() 호출 **");
		pt.interrupt();
		// => 해당 스레드에는 InterruptedException 발생함.
		// => 해당 스레드의 isInterrupted(), interrupted() 를 true 로 해줌.
		//    그러나 Exception 처리시 또는 isInterrupted(), interrupted() 호출시
		//    바로 clear 되어 아래는 false 로 출력됨. 
		System.out.println("** main pt.isInterrupted() => "+pt.isInterrupted());
		
		System.out.println("** Program_main() Stop **");
	} //main

} //class

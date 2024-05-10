package j16_thread;

import java.util.Random;

//** Sleep & Priority Test
//=> Thread 실행 순서 제어

//** 우선순위 Test
//=> 1 ~ 10 
//=> 우선순위 default 값 확인 : 5 
//   단, 이전 스레드의 값을 전달 받음
// ( 이전 스레드가 10 이었으면 동일한 10 이 기본 우선순위값이 됨 )
//=> 확인: getPriority() , 변경: setPriority()
//=> 우선순위의 지정(변경)은 반드시 start 전에 해야 함.

//** sleepTime Test 
//=> sleepTime 있는 경우, 없는 경우(Ex01_...) 비교
//------------------------------------------------

//** Rabbit01 Test
//=> 우선순위는 동일하고 sleepTime 은 서로 다름
//sleepTime 을 Random 으로 생성
class Rabbit01 extends Thread {
	private String name;
	private int sleepTime;
	
	public Rabbit01(String name) {
		this.name=name ;
		sleepTime = new Random().nextInt(1000);
	}	
	@Override
	public void run() {
		System.out.println("** Rabbit01_Thread 출발 **");
		System.out.printf("* Thread_Name=%s, %s, sleepTime=%d\n",getName(),this.name,sleepTime);
		for (int i=50; i>0; i--) {
			System.out.printf(" 나는 토끼01 %s, i=%d, \n",name,i);
			// ** sleep 적용
			// => Thread 에 정의, Checked Exception
			//    매개변수로 sleepTime 전달 (단위 는 1/1000초 )
			try{
				sleep(sleepTime); // 1/1000초
			}catch(InterruptedException e) {
				System.out.println("InterruptedException => "+e.toString());
			} //catch
		} //for
	} //run
} //Rabbit01

//** Rabbit02 Test
//=> sleep Time은 동일하고 우선순위 서로 다름 
//=> 우선순위 변경(set)을 생성자에 추가 
//=> 우선순위의 지정(변경)은 반드시 start 전에 해야 함.
class Rabbit02 implements Runnable {
	String name;
	int op ; // 기본 우선순위 보관 위한 변수
	
	Rabbit02(String name, int p) {
		this.name=name;
		op=Thread.currentThread().getPriority(); // 기본 우선순위 보관
		Thread.currentThread().setPriority(p);	 // 우선순위 변경
	} 
	@Override
	public void run() {
		System.out.println("** Rabbit02_Runnable 출발 **");
		System.out.printf("* Thread_Name=%s, %s \n",Thread.currentThread().getName(),this.name);
		for (int i=50; i>0; i--) {
			System.out.printf(" 나는 토끼02 %s, op=%d, p=%d\n",name,op,Thread.currentThread().getPriority());
			// ** sleep 동일하게 적용
//			try{
//				Thread.sleep(10); // 10/1000초
//			}catch(InterruptedException e) {
//				System.out.println("InterruptedException => "+e.toString());
//			} //catch
		} //for
	} //run
} //Rabbit02

public class Ex02_sleepRun {

	public static void main(String[] args) {
		/* ** 
		 * Test1) Rabbit01 : 토끼_Thread 출발 준비
		// => 우선순위는 동일하고 sleepTime 은 서로 다름
 		Rabbit01 red = new Rabbit01("Red") ;
 		Rabbit01 blue = new Rabbit01("Blue") ;
		Rabbit01 green = new Rabbit01("Green") ;
		*/
		// ** Rabbit02 : 토끼_Runnale 출발 준비
		// Test2.1) sleepTime은 동일하고 우선순위는 서로 다름
		//  => sleepTime 이 동일하기 때문에 결과에 우선순위의 영향이 거의 없음
		
		/* Test2.2) sleepTime없이 우선순위는 서로 다른경우
		   => MIN -> NORMAL -> MAX 
		*/   
		Thread red = new Thread(new Rabbit02("Red",Thread.MAX_PRIORITY));    // 10
		Thread blue = new Thread(new Rabbit02("Blue",Thread.NORM_PRIORITY)); // 5 
		Thread green = new Thread(new Rabbit02("Green",Thread.MIN_PRIORITY)); // 1 
		
		// ** 실행
		red.start();
		blue.start();
		green.start();
		
		// ** 거북이(main) 준비
		System.out.println("** 거북이 출발 **");
		for (int i=50; i>0; i--) {
			System.out.println("* 거북이 i = "+i);
//					try{
//						Thread.sleep(10); // 단위: 1/1000 초 
//					}catch(InterruptedException e) {
//						System.out.println("* main_InterruptedException => "+e.toString());
//					} //catch
		} //for

		System.out.println("** main Thread info & Priority **");
		/* main 메서드도 main 쓰레드 => 다른 쓰레드와 마찬가지로 자신만의 실행 흐름을 이어감
		// main 메서드가 종료되어도 남은 쓰레드는 실행됨
		// => 모든 쓰레드가 종료되어야 프로그램은 종료됨.   */
		System.out.println("** getState => "+ Thread.currentThread().getState());
		System.out.println("** getName => "+ Thread.currentThread().getName());
		System.out.println("** getPriority => "+ Thread.currentThread().getPriority());
		System.out.println("** MAX_Priority => "+ Thread.MAX_PRIORITY);
		System.out.println("** NORM_Priority => "+ Thread.NORM_PRIORITY);
		System.out.println("** MIN_Priority => "+ Thread.MIN_PRIORITY);
		
		System.out.println("** Program_main() Stop **");
	} //main

} //class

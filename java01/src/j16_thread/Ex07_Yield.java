package j16_thread;

// *** Thread.yield()
// => Thread  양보
//    running -> runnable

class ThreadY extends Thread {
	boolean stop = false ;
	boolean work = true;
	
	@Override
	public void run() {
		while (!stop) {
			if (work) System.out.println(" ** ThreadY 열심히 일합니다 **");
			else {
				System.out.println(" ** ThreadY yield() -> 양보 **");
				Thread.yield(); // running -> runnable
			} //else
		} //while
		System.out.println(" ** ThreadY 종료 **");
	}//run
} //ThreadY

class ThreadZ extends Thread {
	boolean stop = false ;
	boolean work = true;
	
	@Override
	public void run() {
		while (!stop) {
			if (work) System.out.println(" ** ThreadZ 열심히 일합니다 **");
			else {
				System.out.println(" ** ThreadZ yield() -> 양보 **");
				Thread.yield(); // running -> runnable
			} //else
		} //while
		System.out.println(" ** ThreadZ 종료 **");
	}//run
} //ThreadZ

public class Ex07_Yield {

	public static void main(String[] args) {
		ThreadY ty = new ThreadY() ;
		ThreadZ tz = new ThreadZ() ;
		ty.start();
		tz.start();
		// Test 
		// => 처음에는 y, z 번갈가면서 실행 하다가
		// => ty.work=false -> ty는 계속 양보 , tz 는 무한 ..
		// => 양보했지만 runnable 상태에 있다가 차례가 되면 자동 running
		// => ty 의 차례가 되어 running 되어도 work=false 때문에 계속 양보함. 
		try { 
			Thread.sleep(10); 
		} catch (InterruptedException e) { e.printStackTrace(); }
		ty.work=false;
		
		try { 
			Thread.sleep(10); 
		} catch (InterruptedException e) { e.printStackTrace(); }
		ty.stop=true;
		tz.stop=true;
		System.out.println("** Main Stop **");
		
	} //main
} //class

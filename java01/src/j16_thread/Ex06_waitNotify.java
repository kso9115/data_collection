package j16_thread;

// *** wait() , notify() , notifyAll()
// => Object 클래스에 정의되어 있음
// => 동기화된 블럭 내에서만 적용 가능
//    한번에 하나의 쓰레드만 호출할 수 있도록 해줌 
// => notifyAll()
//    wait() 로 대기중인 모든 스레드를 깨우고 모두 RUNNABLE 상태로 만든다.

// *** 동기화 블럭 내에서 wait() , notify() 적용전/후 Test
// => 적용 : A , B 가 정확하게 교대로 1회씩 호출됨.
// => 적용하지 않음 : 동기화는 되어있으나 A, B 호출 횟수 불규칙

class Worker {
	public synchronized void methodA() {
		System.out.println(" ** methodA() 작업중 **");
		// ** wait() , notify() 적용효과
		// => 동기화된 블럭내에서 호출 규칙강화 -> 한번에 하나의 쓰레드만 호출 
		notify(); // wait() 로 Block 되어있는 현재 Thread 를 깨움
		try {
			wait(); // 자신을 wait 로 -> 깨울때 까지 wait
		} catch (InterruptedException e) {
			System.out.println(" ** methodA() Exception => "+e.toString());
		} //catch
	} //methodA
	
	public synchronized void methodB() {
		System.out.println(" ** methodB() 작업중 **");
		notify(); // wait() 로 Block 되어있는 현재 Thread 를 깨움
		try {
			wait(); // 자신을 wait 로 -> 깨울때 까지 wait
		} catch (InterruptedException e) {
			System.out.println(" ** methodB() Exception => "+e.toString());
		} //catch
	} //methodA
} //Worker

// Thread class만들기
class ThreadA  extends Thread {
	Worker worker;
	ThreadA(Worker worker) { this.worker=worker; }
	@Override
	public void run() {
		for (int i=0; i<10; i++) {
			System.out.println(" ThreadA i => "+i);
			worker.methodA();
		}
	} //run
} //ThreadA

class ThreadB  extends Thread {
	Worker worker;
	ThreadB(Worker worker) { this.worker=worker; }
	@Override
	public void run() {
		for (int i=0; i<10; i++) {
			System.out.println(" ThreadB i => "+i);
			worker.methodB();
		}
	} //run
} //ThreadA

public class Ex06_waitNotify {

	public static void main(String[] args) {
		Worker wk = new Worker() ;
		
		ThreadA ta = new ThreadA(wk) ;
		ThreadB tb = new ThreadB(wk) ;
		
		ta.start();
		tb.start();
		
		System.out.println("** Main Stop **");
	} //main
} //class

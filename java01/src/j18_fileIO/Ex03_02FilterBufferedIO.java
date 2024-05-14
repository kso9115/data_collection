package j18_fileIO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//** Filter I/O Stream 2.
//=> Buffered I/O Stream
//=> 버퍼링 기능을 제공하는 필터 IO Stream 
//	 기본 I/O 스트림에 연결되어 I/O 효율을 높여줌
//=> 파일 처리에서 성능향상의 핵심은 파일 접근 횟수인데, 버퍼를 사용함으로 이를 줄여줌.
//   객체 내부에 버퍼를 가지고 있어서 일단 버퍼에 채운후 버퍼를 단위로 I/O 처리 

//=> BufferedOutputStream 클래스의 데이터 flush
//   주기억 장치의 Buffer를 이용하면 기본용량은 16Byte   
//   버퍼를 다 채웠을때, flush() 호출시, close() 호출시 스트림의 버퍼는 flush 됨.
//=> ppt 22p

public class Ex03_02FilterBufferedIO {

	public static void main(String[] args) throws IOException {
		// ** 존재하는 파일로 Test (생성 순서 무관)
		// 1) BufferedInputStream 생성
		// => 입력스트림을 생성하고, 필터스트림 BufferedInputStream 과 연결
		BufferedInputStream in = 
				new BufferedInputStream(new FileInputStream("E:\\ksoo\\gitANDeclipse\\sql1209.sql"));
		
		// 2) BufferedOutputStream 생성
		// => 출력스트림을 생성하고, 필터스트림 BufferedOutputStream 과 연결
		BufferedOutputStream out = 
				new BufferedOutputStream(new FileOutputStream("bufferedTest.txt"));
		// => 인자로 전달된 출력스트림과 연결된 필터스트림 출력 객체 생성
		try {
			int data;
			while(true) {
				data = in.read();
				// ** BufferedInputStream
				// => FileInputStream 으로 부터 파일의 자료를 가져다 버퍼에 채운후
				//    read() 메서드에서 이 버퍼의 자료를 반환함		
				if (data == -1) break;
				out.write(data);
				// ** BufferedOutputStream
				// => FileOutputStream 으로 부터 자료를 가져다 버퍼에 채운후
				//    write() 메서드에서 이 버퍼의 자료를 파일에 write 함.
				// => 주의사항 (출력스트림에만 해당)
				//	  버퍼스트림의 데이터가 파일에 저장 되지않은 상태에서 오류로 종료된다면 	
				//    데이터가 손상될 수 있다.
				//    이런경우를 대비해 명시적으로 write (버퍼를 비워라) 하라는 메서드가 flush() 임.
				//    그러므로 일반적으로 write() 가 끝나는곳에서 ~.flush() 함.
			} //while
		} catch (Exception e) {
			System.out.println("Exception => "+e.toString());
		} finally {
			// ** 스트림소멸
			if (in != null) in.close();
			if (out != null) out.close();
		}
		System.out.println("** Buffered I/O Stream Test 종료 **");
	} //main
} //class

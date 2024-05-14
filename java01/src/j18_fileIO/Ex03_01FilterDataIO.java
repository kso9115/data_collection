package j18_fileIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//** Filter I/O Stream 1.
//=> Data I/O Stream
//=> 기본 자료형 단위의 데이터 입출력을  가능하게 하는 필터 스트림
//=> ppt 20-21p

public class Ex03_01FilterDataIO {

	public static void main(String[] args) throws IOException {
		// ** 스트림 생성
		// => 순서에 주의 (없는 문서를 만들 예정 이므로) 
		
		// 1) 기본자료형 단위의 출력을 위한 필터스트림 DataOutputStream 을 생성
		// => 출력스트림을 생성하고, 필터스트림 DataOutputStream 과 연결한다.
//		FileOutputStream fout = new FileOutputStream("dataType.txt");
//		DataOutputStream out = new DataOutputStream(fout);
		
		DataOutputStream out = new DataOutputStream(new FileOutputStream("dataType.txt"));
		// => 인자로 전달된 출력스트림과 연결된 필터스트림 DataOutputStream 객체 생성
		
		// 2) 기본자료형 단위의 입력을 위한 필터스트림 DataInputStream 을 생성
		// => 입력스트림을 생성하고, 필터스트림 DataInputStream 과 연결한다.
		// => 단, read 스트림 이므로 "dataType.txt" 파일은 반드시 존재해야 생성됨. 
		//    java.io.FileNotFoundException: dataType.txt (지정된 파일을 찾을 수 없습니다)
		DataInputStream in = new DataInputStream(new FileInputStream("dataType.txt"));
		try {
			// ** write
			out.writeDouble(3.14159);
			out.writeInt(123);
			out.writeUTF("자신의 생각을 바꾸지 못하는 사람은 결코 현실을 바꿀 수 없다.");
			
			// ** read
			// => 저장 순서에 따라 처리함.
			//    그러므로 저장 순서대로 타입을 지켜야함.
			double d = in.readDouble();
			int i = in.readInt();
			String s = in.readUTF();
			System.out.println("** double d = "+d);
			System.out.println("** int i = "+i);
			System.out.println("** String s = "+s);
		} catch (Exception e) {
			System.out.println("Exception =>"+e.toString());
		} finally {
			// ** 스트림소멸
			if (in != null) in.close();
			if (out != null) out.close();
		} //finally
	} //main
} //class

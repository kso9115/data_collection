package j18_fileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

//** ObjectIOStream 1
//=> 객체직렬화 Object Serialization  
//   객체가 가진 데이터들을 순차적인 데이터로 변환하는 것
//=> ppt 23p
public class Ex04_01ObjectIOStream {

	public static void main(String[] args) throws IOException {
		// ** 스트림 생성 
		// => 없는 화일로 Test , 생성 순서 중요
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("object.txt"));
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("object.txt"));
		try {
			out.writeObject(new Date());    // 객체를 직렬화해서 사용
			Date d = (Date)in.readObject(); // 화일의 자료를 역직렬화 해서 객체로 제공
			System.out.println("** d => "+d);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// ** 스트림 소멸
			if (in!=null) in.close();
			if (out!=null) out.close();
		} //finally
		System.out.println("** Object I/O Stream Test 종료 **");
	} //main
} //class

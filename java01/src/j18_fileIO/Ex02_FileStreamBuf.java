package j18_fileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

//** InputStream, OutputStream 2
//=> Byte Stream 이지만 byte 배열을 이용해서 처리속도 높이기
//=> ppt 18-19p
public class Ex02_FileStreamBuf {
	
	public static void main(String[] args) throws IOException {
		// ** MTest 에 존재하는 ~.txt 문서 이용
		// => 그러므로 InputStream 부터 생성해도 됨.
		// => MTest 에 존재하는 ~.txt 문서 를 현재 프로젝트내에 동일이름으로 복사본 만들기
		InputStream in = new FileInputStream("E:\\ksoo\\gitANDeclipse\\서버_kill.txt"); 
		OutputStream out = new FileOutputStream("서버_kill.txt");
		try {
			// ** read -> write 복사본 만들기
			// => byte Type 의 배열을 만들어 1024 Byte 씩 처리하도록 함
			byte[] buff = new byte[1024];
			int len ;
			
			while(true) {
				// ** read(byte[] buff)
            	// => buff크기 만큼 읽어 buff에 저장하고 읽은 byte수를 반환
            	// => 다 읽으면 -1 반환
				len = in.read(buff);
				System.out.println("buff=>"+Arrays.toString(buff));
				if (len == -1) break;
				out.write(buff);
			} //while
		} catch (Exception e) {
			System.out.println("Exception => "+e.toString());
		} finally {
			// 스트림 소멸
			if (in != null) in.close();
			if (out != null) out.close();
		} //finally
		System.out.println("** Program Stop **");
	} //main
} //class

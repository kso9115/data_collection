package j18_fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// ** 필터 문자 스트림
// => BufferedWriter , BufferedReader
//    생성시에 FileWriter , FileReader 와 연결
public class Ex06_BufferedRW {

	public static void main(String[] args) throws IOException {
		// ** 스트림 생성
		// => 없는 화일로 Test : 생성순서 중요
		BufferedWriter out = new BufferedWriter(new FileWriter("string.txt"));
		BufferedReader in = new BufferedReader(new FileReader("string.txt"));
		String ks = "공부에 있어서 돈이 꼭 필요한 것은 아니다.";
        String es = "Life is long if you know how to use it.";
        String str;
		try {
			// ** write
			out.write(ks, 0, ks.length());
			// 문자열 ks를, 인덱스 0 부터, length 개 까지 저장 
			out.newLine(); // OS 에 정의된 줄바꿈 문자를 추가함
			out.write(es, 0, es.length());
			out.flush();
			// ** read
			while(true) {
				str=in.readLine(); 
				// => 한문장 읽어들이기
				// => 더이상 반환할 문자열이 없으면 null 을 return 
				if (str==null) break;
				System.out.println(str);
			} //while
		 
		} finally {
			// ** 스트림 소멸
			if (in!=null) in.close();
			if (out!=null) out.close();
		}
		System.out.println("** BufferedReader / BufferedWriter Test 종료 **");
	} //main
} //class

package j18_fileIO;
import java.io.*;

// ** Byte Stream 적용 1
// => image 복사하기

public class Ex07_01BinaryCopy {
	public static void main(String[] args) throws IOException {
		
		File src = new File("C:\\MTest\\images\\aaa.gif"); // 원본 파일 경로명
		File dest = new File("yellow.gif"); // 현재 프로젝트로 복사 
		
		FileInputStream fi = new FileInputStream(src); // 파일 입력 바이트 스트림 생성
		FileOutputStream fo = new FileOutputStream(dest); // 파일 출력 바이트 스트림 생성

		int c;
		try {
			while((c = fi.read()) != -1) {
				//fo.write((byte)c); 
				// byte 스트림이므로 int 에서 1byte 만 전달되므로 타입변환을 했음(안해도 무관) 
				fo.write(c);
			}
			System.out.println(src.getPath()+ "를 " + dest.getPath()+ "로 복사하였습니다.");
		} finally {
			fi.close();
			fo.close();
		} // finally
	} //main
} // class
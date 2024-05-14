package j17_fileClass;

import java.io.File;

// 실행 환경및 실행위치에 따른 문제를 최소화 하기위해 
// 현재 디렉토리를 가져와서 이를기준으로 작업하는것이 좋음.
public class Ex05_RelativePath {

	public static void main(String[] args) {
		File curDir=new File("AAA");
		System.out.println(curDir.getAbsolutePath());
		
		File upperDir=new File("AAA"+File.separator+"BBB");
		System.out.println(upperDir.getAbsolutePath());
	} //main
} //class

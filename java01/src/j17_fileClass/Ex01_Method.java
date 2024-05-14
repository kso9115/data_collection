package j17_fileClass;

import java.io.File;
import java.io.IOException;

public class Ex01_Method {
	
	public static void main(String[] args) throws IOException {
		
		File f = new File("D:\\MTest\\MyWork\\Java02\\src\\j16_fileClass\\Ex01_Method.java");
		String fileName = f.getName();
		int pos = fileName.lastIndexOf(".");
		
		System.out.println("** 화일명(경로제외) => "+fileName);
		System.out.println("** 화일명(확장자제외) => "+fileName.substring(0,pos));
		System.out.println("** 확장자명 => "+fileName.substring(pos+1));
		
		System.out.println("** 화일명(경로포함) => "+f.getPath());
		System.out.println("** 절대 경로 => "+f.getAbsolutePath());
		System.out.println("** 정규 경로 => "+f.getCanonicalPath()); // Checked Exception
		// Canonical : (성경이)정본에 속하는 , (문학작품이)고전적인
		// => 일반적 사용시에는 둘다 파일의 절대경로를 반환해 주지만,
		//    경로에 .(현재 디렉토리)과 ..(상위 디렉토리)를 사용할 경우 결과값이 달라짐
		//    ppt 4p 참고
		System.out.println("--- File ------------------------------");
		System.out.println("File.pathSeparator => "+File.pathSeparator);
		System.out.println("File.pathSeparatorChar => "+File.pathSeparatorChar);
		// separator는 OS 에 따른 구분자를 기준으로 표시됨
		System.out.println("File.separator => "+File.separator);
		System.out.println("File.separatorChar => "+File.separatorChar);
		
		System.out.println("--- System ------------------------------");
		System.out.println("user.dir => "+System.getProperty("user.dir"));
		// user.dir : 현재 프로그램이 실행중인 디렉토리를 알려주는 속성값
		System.out.println("user.home => "+System.getProperty("user.home"));
		System.out.println("user.name => "+System.getProperty("user.name"));
		System.out.println("sun.boot.class.path=" + System.getProperty("sun.boot.class.path"));
		// sun.boot.class.path : 기본적인 경로들을 설정해서 가지고 있는 속성값

	} //main

} //class

package j17_fileClass;

import java.io.*;

// ** 지정한 폴더에 포함된 파일과 디렉토리 목록 표시하기.
// => FileEx1 의 user.dir 의 결과를 이용하여 Run Configurations 로 Test
//	  -> D:\MTest\MyWork\Java03, D:\MTest\MyWork
public class Ex02_DirList {

	public static void main(String[] args) {

//		if(args.length != 1) {
//			System.out.println("** 경로 또는 화일명을 입력하지 않았습니다. **");
// 			System.exit(0);  
		// ** 자바 프로그램 종료
		// => main 메서드 종료
		// => return
		// => System.exit(0) : 프로그램 종료 
		//    매개변수 0 -> 정상종료
		//    매개변수 0 아니면 -> 비정상종료 를 의미. 
//		}

 		File f = new File("D:\\MTest\\MyWork\\Java02");
//		File f = new File(args[0]);
		
		// exists() : 파일의 존재여부
		// isDirectory() : 디렉토리 인지 확인
		// isFile() : 파일인지 확인
		// 정석 918~919 p 표 참고
		if(!f.exists() || !f.isDirectory()) {
			System.out.println("** 유효하지 않은 디렉토리입니다. **");
			//System.exit(0); // 종료, return 과 동일
			return;
		} 
		// listFiles() : 폴더의 파일목록을 File 배열로 반환 
		File[] files = f.listFiles();

		for(int i=0; i < files.length; i++) {
			String fileName = files[i].getName();
			if(files[i].isDirectory()) {
				System.out.println("["+fileName+"] \t\t DIR");
			}else {
				System.out.println(fileName+" \t\t FILE");
			}
		} //for
		
	} //main

} //class

package j17_fileClass;

import java.io.File;
import java.util.ArrayList;

//** 지정한 폴더에 포함된 파일과 폴더 목록, 서브폴더의 파일과 폴더 모두  표시하기.
// => FileEx1 의 user.dir 의 결과를 이용하여 Run Configurations 로 Test
//	  -> D:\MTest\MyWork\Java02\src, D:\MTest\MyWork

public class Ex03_subDirList {
	
	static int totalFiles = 0;
	static int totalDirs = 0; 

	public static void main(String[] args) {
		// File 인스턴스 생성
		File f = new File("C:\\MTest\\MyWork\\Java01\\src");
		
		// 출력가능한 Directory 인지 확인
		if(!f.exists() || !f.isDirectory()) {
			System.out.println("** 유효하지 않은 디렉토리입니다. **");
			//System.exit(0); // 종료, return 과 동일
			return;
		} 
		// Directory 출력
		printDir(f) ;
		
		System.out.println("---------------------------");
		System.out.println("총 "+totalFiles+"개의 화일" );
		System.out.println("총 "+totalDirs+"개의 디렉토리" );
	} //main
	
	public static void printDir(File f) {
		System.out.println("** 디렉토리 : "+f.getAbsolutePath()+" **");
		File[] files = f.listFiles();
		
		// ** sub Dir 갯수 확인
		ArrayList<Integer> subDir = new ArrayList<>();
		
		for (int i=0; i<files.length; i++) {
			String fileName = files[i].getName();
			
			if (files[i].isDirectory()) {
				fileName="["+fileName+"]" ;
				subDir.add(i);
			} //if
			System.out.println(fileName);
		} //for
		
		int dirNum = subDir.size(); // 서브디렉토리 갯수
		int fileNum = files.length - dirNum; // 파일갯수
		totalDirs += dirNum;
		totalFiles += fileNum;
		
		System.out.println(fileNum+"개의 파일,  "+dirNum+"개의 디렉토리\n");
		
		for(int i=0; i<subDir.size(); i++) {
			int index = subDir.get(i);
			printDir(files[index]);
		}
	} //printDir
} //class

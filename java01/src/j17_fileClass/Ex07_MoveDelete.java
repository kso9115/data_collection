package j17_fileClass;

import java.io.File;

// ** Move & Delete

public class Ex07_MoveDelete {

	public static void main(String[] args) {
		// ** File 준비
		File myFile = new File("C:\\MTest\\MyWork\\Java01\\src\\j16_fileClass\\Ex01_Method2.java");
		if (!myFile.exists()) {
			System.out.println("~~ 원본 화일이 준비되어있지 않습니다 ~~");
			return;
		}
//		// ** Dir 준비
		File newDir = new File("C:\\MTest\\MyWork\\Java01\\src\\testDir");
		if (!newDir.exists()) newDir.mkdir(); // dir 이 없으면 생성
//		
//		// ** Move
//		// => renameTo : 변경사항에 디렉토리가 포함되므로 위치가 변경됨
		File newFile = new File(newDir,"Ex01_Method2.java");
		myFile.renameTo(newFile) ;
		if (newFile.exists()) System.out.println("~~ 파일 이동 성공 ~~");
		else System.out.println("~~ 파일 이동 실패 ~~");
		
		// ** Delete
		// => 이동시킨 newFile 삭제
		//    존재 확인후 삭제
/*		if (newFile.exists()) newFile.delete() ;
		// => 삭제결과 확인
		if (newFile.exists()) System.out.println("~~ 파일 삭제 실패 ~~");
		else System.out.println("~~ 파일 삭제 성공 ~~");
		
		// => testDir 삭제
		if (newDir.exists()) newDir.delete();
*/		
	} //main
} //class

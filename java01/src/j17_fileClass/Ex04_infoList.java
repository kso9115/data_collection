package j17_fileClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

// ** 현재 폴더에 속한 파일과 폴더의 이름과 크기 등 정보 출력. 
public class Ex04_infoList {

	public static void main(String[] args) {
		String currDir = System.getProperty("user.dir") ;
		System.out.println("*** "+currDir+" Information **");
		
		File f = new File(currDir);
		File[] files = f.listFiles();
		
		for (int i=0; i<files.length; i++) {
			String name = files[i].getName();
			// 날짜 데이터는 타입마다 포맷이 다름을 주의하기
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mma");
			String attribute = "";
			String size = "";
			
			if (files[i].isDirectory()) {
				attribute = "Dir" ;
			}else {
				size = files[i].length()+""; 
				attribute = (files[i].canRead() ? "R" : " ");
				attribute += (files[i].canWrite() ? "W" : " ");
				attribute += (files[i].isHidden() ? "H" : " ");
			}
			System.out.printf("%s %3s %6s %s %n",
					df.format(new Date(files[i].lastModified())), attribute, size, name);
		} //for
	} //main
} //class

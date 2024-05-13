package j12_dateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

//** Date 
//=> 날짜 처리 클래스  
//=> 컴퓨터의 현재 날짜.시간를 읽어 Date 객체를 생성
//=> 비권장( 더이상 지원하지 않음 )  
//   Deprecated :중요도가 떨어져 더 이상 사용되지 않고 앞으로는 사라지게 될 (컴퓨터 시스템 기능 등)

//** SimpleDateFormat
//=> Date Type 의 날짜 시간의 포맷을 잡아주는 클래스
//=> Format ->  DateFormat -> SimpleDateFormat

public class Ex01_Date {

	public static void main(String[] args) {
		
		Date now = new Date() ;
		System.out.println("** now => "+now);
		System.out.println("** Year => "+(now.getYear()+1900));  // +1900
		System.out.println("** Month => "+(now.getMonth()+1));   // +1
		System.out.println("** Date => "+now.getDate()+","+now.getDay());
		System.out.printf("** Time => %d:%d:%d \n",now.getHours(),now.getMinutes(),now.getSeconds());
		
		// ** SimpleDateFormat
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd") ;
		System.out.println(" sf1 now => "+sf.format(now));
		
		sf = new SimpleDateFormat("yyyy년 MM월 dd일") ;
		System.out.println(" sf2 now => "+sf.format(now));
		
		sf = new SimpleDateFormat("yyyy/MM/dd a HH:mm:ss");
		System.out.println(" sf3 now => "+sf.format(now));
		
		sf = new SimpleDateFormat("오늘은 E요일 입니다 ~~");
		System.out.println(" sf4 now => "+sf.format(now));
		
		sf = new SimpleDateFormat("오늘은 올해의 D번째 날 입니다 ~~");
		System.out.println(" sf5 now => "+sf.format(now));
		
		sf = new SimpleDateFormat("오늘은 이달의 d번째 날 입니다 ~~");
		System.out.println(" sf6 now => "+sf.format(now));
		
	} //main
} //class

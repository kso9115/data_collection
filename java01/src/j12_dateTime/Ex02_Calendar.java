package j12_dateTime;

import java.util.Calendar;
import java.util.Formatter;

//** Calendar : 추상 클래스 이므로 new 인스턴스 생성 불가
//=> 날짜, 시간 계산법이 지역과 문화에 따라 다르기 때문에 꼭 필요한 메서드만 정의,
//   특정한 역법을 따르는 경우엔 하위 클래스에서 구현 하도록	

//** Formatter 활용
//=> Calendar Type 의 출력 Format 지원 

public class Ex02_Calendar {

	public static void main(String[] args) {
		
		Calendar now = Calendar.getInstance();
		// getInstance() 메서드로 Calendar 객체 얻기
		// getInstance() 
		// => Calendar 클래스의 정적(static) 메서드로써 OS에 설정된 시간정보 제공
		
		int yy = now.get(Calendar.YEAR);
		System.out.println("Calendar.YEAR => "+Calendar.YEAR);
		System.out.println("yy => "+yy);
		int mm = now.get(Calendar.MONTH);
		int dd = now.get(Calendar.DATE);
		
		int hh = now.get(Calendar.HOUR);
		int min= now.get(Calendar.MINUTE);
		int sec= now.get(Calendar.SECOND);
		int ap = now.get(Calendar.AM_PM); // AM_PM : 0 이면 AM , 아니면 PM
		String ampm = "PM" ;
		if (ap==0) ampm="AM" ;
		System.out.printf("now => %d년 %d월 %d일 %s %d시 %d분 %d초 \n",
							yy,mm,dd,ampm,hh,min,sec);
		int yy_w = now.get(Calendar.WEEK_OF_YEAR);
		System.out.printf(" 오늘은 올해의 %d번째 주 입니다 ~~\n", yy_w);
		
		// ** Formatter 활용
		// => printf("..." , ..) 와 동일하게 다양한 형태의 출력포맷을 제공
		//    format("....") 으로 출력포맷을 지정하고 toString() 으로 출력한다.
		Formatter f = new Formatter();
		f.format("tR => %tR%n",now);  // 시간을 R Type 으로
		f.format("tT => %tT%n",now);
		f.format("tr => %tr%n",now);
		f.format("tD => %tD%n",now);
		f.format("tF => %tF%n",now);
		// f 라는 Formatter 인스턴스에 format 메서드로 다양한 포맷을 지정하고, 
		// 이렇게 지정된 형식을 f.toString() 메서드를 이용하여 문자열로 반환받아 출력등에 사용함.
		
		f.format("** tc => %tc%n",now);
		// => 목 5월 27 14:46:02 KST 2021
		//    ISO 8601 은 국제 표준화 기구(ISO)에서 제정한 날짜와 시간의 표기에 관한 국제표준규격
		//    KST : Korea Standard Time의 약자로 한국 표준시간 
		
		System.out.println("** f.toString() => \n"+f.toString());
		
		// printf 메서드를 이용하면 위의 기호로 포맷 지정해서 출력가능
		System.out.printf("** printf => %tF \n",now);
		
		f.close();
		
		// *** 다양한 Formatting 문자
		// http://docs.oracle.com/javase/8/docs/api/Formatter 에서 찾아보기

		// % : 출력형식을 의미하는 문자열은 반드시 %로 시작,
		// %n : 개행
		// %B,%b : Boolean Type
		// %c,%C : 문자
		// %e : 10진수 지수표현
		// %f : 실수
		// %x : 16진수
		// %o : 8진수
		// %d : 10진수
		
		// %t : 날짜 시간을 , T 시간 출력 형식 
		// %tA : 요일
		// %tY : 년도
		// %tB : 월의 이름(Jan,Feb ..)
		// %tm : 1~12월
		// %te : 1~31일
		// %tk : 0~23시
		// %tM : 0~59분
		// %tS : 0~59초
		// %tZ : 타임존 출력		
	} //main
} //class

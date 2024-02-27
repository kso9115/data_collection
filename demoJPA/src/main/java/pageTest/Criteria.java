package pageTest;

import lombok.Getter;
import lombok.ToString;

//** Criteria : (판단이나 결정을 위한) 기준
//=> 출력할 Row 를 select 하기 위한 클래스
//=> 이것을 위한 기준 값들을 관리

//** PageMaker : UI에 필요한 정보 완성

//** Paging 을 하려면 ... **
//=> 1Page에 출력할 Row 갯수 :  5개
//=> 현재 출력(요청) Page
//=> 출력할 List (Rows) 
// -> start Row 순서번호 : 계산필요
//=> Criteria Class 에 정의 

//=> 1Page 출력 PageNo 갯수 : 10개
// -> PageBlock 의 First Page No 
// -> PageBlock 의 Last Page No
// -> 전진, 후진 표시여부
// -> go 전체의 First Page 표시여부
// -> go 전체의 Last Page 표시여부
//=> PageMaker Class 로 처리 


@Getter
@ToString
// 값을 보관하는 용도(DTO) : 페이지에서 활용되는 값들
public class Criteria {
	
	private int rowPerPage;	// 1page에 출력할 row의 갯수
	private int currPage; 	// 요청받은(현재 출력할) page
	private int sno;		// start Row 순서번호 : 계산을 통해서 얻어지는 값
	private int eno;		// end Row 순서번호 : 계산을 통해서 얻어지는 값으로 Oracle만 필요 => MySql의 경우 limit이 있음
	
	// 1. 기본 생성자 : 값 초기화를 위해
	public Criteria () {
		this.rowPerPage = 5;
		this.currPage = 1;
	}
	
	// 2. 요청 시 값 갱신
	// 2-1) currPage
	public void setCurrPage(int currPage) {
		if(currPage > 1) { this.currPage=currPage; }
		else this.currPage=1;
	}
	
	// 2-2) rowsPerPage 
	// => 1페이지당 보여줄 Row(Record,튜플) 갯수 확인
	// => 제한조건 점검 ( 50개 까지만 허용)
	// => 당장은 사용하지 않지만 사용가능하도록 작성   
	public void setrowPerPage(int rowPerPage) {
		if(rowPerPage > 5 && rowPerPage < 51) this.rowPerPage=rowPerPage;
		else this.rowPerPage=5;
	}
	
	// 2-3) setSnoEno : sno, eno 계산
	// 페이지 블록에서의 sno를 구하기 위한 것
	public void setSnoEno() {
		if(this.sno<1) this.sno=1;
		this.sno=(this.currPage-1)*this.rowPerPage;
		// => MySql : limit 5,5 (6번째 5개를 지나고부터 5개 노출)
		// => Oracle : between 6 and 10
		// this.sno=(this.currPage-1)*this.rowPerPage+1;
		// this.eno=(this.sno+this.rowPerPage)-1
	}
	
}

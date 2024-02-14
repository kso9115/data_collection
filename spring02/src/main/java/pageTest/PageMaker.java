package pageTest;

import lombok.Getter;
import lombok.ToString;

//** PageMaker : View 에 필요한 값을 완성
//=> 전체Row 갯수 (전체 Page수 계산위해 필요)
//=> 1Page당 표시할 pageNo갯수
//=> view 에 표시할 첫 PageNo
//=> view 에 표시할 끝 PageNo

//=> 출력 가능한 마지막 PageNo (totalRowsCount, rowsPerPage 로 계산)
//=> 이전 PageBlock 으로
//=> 다음 PageBlock 으로

//=> Criteria 를 이용해서..
 
@Getter
@ToString
public class PageMaker {
	
	private int totalRowsCount;		// 출력 대상 전체 Row 갯수 : from DB에서 가져올 것
	private int displayPageNo=3;	// 1Page 당 표시할 페이지 번호 갯수
	private int spageNo;			// view에 표시할 첫 PageNo(계산을 통해 얻는 값)
	private int epageNo;
	// sPageNo -> setSPageNo or setsPageNo 세터 게터 생성 시 혼돈이 올 수 있음
	
	private int lastPageNo;			// 출력 가능한 마지막 PageNo(계산을 통해 얻는 값)
	
	private boolean prev;			// 이전 PageBlock으로
	private boolean next;			// 다음 PageBlock으로
	
	Criteria cri;
	
	// ** 필요값 계산
	// 1) Criteria
	public void setCri(Criteria cri) {
		this.cri=cri;
	}
	
	// 2) totalRowCount
	// => 전체 Rows 갯수 : Read from DB
	// => 해당 값을 이용하여 나머지 필요한 값 계산
	public void setTotalRowsCount(int totalRowsCount) {
		this.totalRowsCount=totalRowsCount;
		calcData();
	}
	
	// 3-1) 나머지 필요한 값 계싼하기
	public void calcData() {
		// 3-1) spageNo, epageNo
		// => curPage가 속한 pageBlock을 찾아서 spageNo, epageNo 구간을 계산
		
		// => pageNo를 n개씩 출력한다고 가정하면 epageNo은 항상 n의 배수만큼 출력이 된다
		// displayPageNo = 3 이라면 epageNo는 3, 6, 9 ....이렇게 나아감
		
		// => ex) 17 page 요청 , displayPageNo=3, 일때 17이 몇번째 그룹 인지 계산하려면,
	    //        17/3 -> 5 나머지 2 결론은 정수 나누기 후 나머지가 있으면 +1 이 필요함
	    //    -> Math.ceil(17/3) -> Math.ceil(5.73..) -> 6.0 -> 6번쨰 그룹 16,17,18
	    // 즉, 17이 몇번째 그룹 인지 계산하고, 여기에 * displayPageNo 를 하면 됨.   
	      
	    // ** Math.ceil(c) : 매개변수 c 보다 크면서 같은 가장 작은 정수를 double 형태로 반환 
	    //                   ceil -> 천장, 예) 11/3=3.666..  -> 4
	    // => Math.ceil(12.345) => 13.0      
		this.epageNo=(int)Math.ceil(cri.getCurrPage()/(double)displayPageNo)*displayPageNo;
		this.spageNo=(this.epageNo - displayPageNo)+1;
		// => 요청받은 currPageNo가 11인 경우
		// 마지막 페이지 = (int)Math.ceil(11/3)*3 = 12
		// 첫 페이지 = 12 - 3 + 1 = 10페이지 => 11페이지는 10,11,12페이지의 그룹에 속한다는 걸 알 수 있음

		// 3-2) lastPageNo 계산 & epageNo의 적합성
		// 전체 상품에서 총 페이지 번호 나눈 값이 최종 페이지 번호
		this.lastPageNo=(int)Math.ceil(this.totalRowsCount/(double)cri.getRowPerPage());
		// 만약 마지막 페이지가 최총 페이지 번호보다 클 경우는 최총 페이지 번호를 넣어주면 된다.
		if(this.epageNo > this.lastPageNo) this.epageNo = this.lastPageNo;
		
		// 3-3) prev, next
		prev = this.spageNo==1 ? false : true;	// 참일 때 더이상 앞으로 갈 수 없음
		next = this.epageNo==this.lastPageNo ? false : true; // 참일 떄 더이상 뒤로 갈 수 없음
	}
	
	
	
	
}

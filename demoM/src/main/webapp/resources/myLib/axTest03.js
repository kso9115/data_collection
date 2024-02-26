/* axTest03.js */

// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response
//   - idbList(id별 boardList) : RTestController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// ** Ajax Member_PageList *********************
// => axiMList 에 Paging + 검색기능 추가
// => 검색조건 & Paging , Ajax 구현
//    -> 입력된 값들을 서버로 전송요청: axios
//   -> url 완성후 axios 호출

// => 1) 검색조건 입력 후 버튼클릭
//   -> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨
//    ${pageMaker.makeQuery(1)} -> ?currPage=1&rowsPerPage=5 

"use strict"

// 1. List 출력 
// 1.1) Page response
// => response 를 resultArea1에 출력하기
// => 요청명 : /member/aximlist
// => response : axMemberList.jsp
function axiMList() {
	let url = "/member/aximlist";
	axios.get(url
	).then(response => {
		console.log('** response : axMemberList 요청 담기 성공');
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		alert(`** response : joinForm 실패 => ${err.message}`);
	})
}

// ** Ajax Member_PageList : 페이징 기능 추가, 서치 기능 추가 **
// => axiMList 에 Paging + 검색기능 추가
// => 검색조건 & Paging , Ajax 구현
//    -> 입력된 값들을 서버로 전송요청: axios
//   -> url 완성후 axios 호출

// 1) 검색조건 입력 후 버튼클릭
//   -> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨
//    ${pageMaker.makeQuery(1)} -> ?currPage=1&rowsPerPage=5 

function searchDB() {
	let url = 'axmcri'
		+ '?currPage=1&rowsPerPage=5'
		+ '&searchType='
		+ document.getElementById('searchType').value
		+ '&keyword='
		+ document.getElementById('keyword').value;

	axiMListCri(url);	//axios호출
}

// 2) searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear() {
	if (document.getElementById('searchType').value == 'all')
		document.getElementById('keyword').value = '';
}

// 3) axios Code
function axiMListCri(url) {
	url = "/member/" + url;
	alert(`axiMListCri url=${url}`);

	axios.get(url
	).then(response => {
		console.log("** response 성공 **");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		document.getElementById('resultArea1').innerHTML = "** axiMListCri 실패 => " + err.message;
	})
}

function checkClear() {
	// 태그 리스트이기때문에, 즉 배열 리스트이기 때문에 아래의 코드는 불가함
	// => nodeList를 반환하기 때문에 적용되지 않는다.
	// document.querySelectorAll('.clear').checked=false;

	let ck = document.querySelectorAll('.clear');
	for (let i = 0; i < ck.length; i++) {
		ck[i].checked = false;
	}
	return false; // reset의 기본 이벤트 제거
}//checkClear

function axiMListCheck() {

	let url = 'axmcheck'
		+ '?currPage=1&rowsPerPage=5'
		+ '&searchType='
		+ document.getElementById('searchType').value
		+ '&keyword='
		+ document.getElementById('keyword').value;

	let classAll = document.querySelectorAll('.check');
	for (let i = 0; i < classAll.length; i++) {
		if (classAll[i].checked == true) {
			url+= '&check=' + classAll[i].value;
		}
	}
	axiMListCri(url);	//axios호출
}

// 1.2) idbList(id 별 boardList 출력하기)
// => RESTController , PathVariable처리(/mlist) , List_Data response
// => Server : service, Sql 구문 작성이 필요하다.
// => request : id를 쿼리스트링으로 보낼 것인지, 경로에 붙여서 전달할 것인지 선택
//                => id를 path로 전송하기 "/rest/idblist/mlist"
// => Response
// 	-> 성공 시 : 반복문을 사용하여 Table로 List 출력문을 완성하여 resultArea2에 출력한다
//	-> 출력 자료의 유/무 : 자료가 없을 경우 Server에서 502로 처리하기

// 	-> 실패 시 : resultArea2는 clear(비우기)하고, alert으로 에러메시지 출력하기

function idbList(id) {
   let url = "/rest/idblist/"+id;

   axios.get(url
   ).then(response => {
	alert("** 성공 => resultArea2 에 List 작성하기 **");
	console.log("** result List Data =>"+response.data);	// 데이터 담겼는지 확인 후 문장 붙여주기
	let listData = response.data;
	let resultHtml = 
	`<table style="width: 100%">
		<tr>
			<th>Seq</th>
			<th>Title</th>
			<th>I D</th>
			<th>RegDate</th>
			<th>조회수</th>
		</tr>`
		
	// 넣어놓은 데이터 꺼내주기 => 반복문 사용
	for(let b of listData) {
		resultHtml +=
		`<tr>
			<td>${b.seq}</td>
			<td>${b.title}</td>
			<td>${b.id}</td>
			<td>${b.regdate}</td>
			<td>${b.cnt}</td>
		</tr>`
	}// for
	resultHtml += `<\table>`
	document.getElementById('resultArea2').innerHTML = resultHtml;
	
   }).catch(err => {
	// => response의 status의 값이 502면 출력 자료가 없음
	if(err.response.status=='502'){
		document.getElementById('resultArea2').innerHTML = err.response.data;
	} else{
		document.getElementById('resultArea2').innerHTML = '';
		alert("** 시스템 오류 잠시후 다시 하세요 => "+err.message);
	}

   })

}

// 2-2) axiDelete('${m.id}')
// => 요청 : "/rest/axidelete/" PathVariable 적용
// => Response 결과 => 성공 혹은 실패 여부만 전달하면 된다 : RESTController 사용
// => 성공했을 시 deleted 로 변경, onclick 이벤트 해제(다시 선택할 수 없게끔)
function axiDelete(id){
	let url = "/rest/axidelete/"+id;

	axios.delete(url
	).then(response => {
		alert(response.data);
		// 삭제 성공
		// Delete -> Deleted, Gray_color, Bold로
		// onclick 이벤트 해제
		// style 제거
		document.getElementById(id).innerHTML="Deleted";
		document.getElementById(id).style.color="Gray";
		document.getElementById(id).style.fontWeight="bold";
		document.getElementById(id).classList.remove('textlink');
		document.getElementById(id).removeAttribute('onclick');


	}).catch(err => {
		if(err.response.status=='502'){
			alert(err.response.data);
		} else alert("** 시스템 오류, 잠시 후 다시 하세요 => "+err.message);
	})

}


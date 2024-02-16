<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Member Page List **</title>
</head>
<!-- <link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css"> -->

<script type="text/javascript">
	"use strict"

	// 1. 검색 조건 입력 후 버튼을 클릭했을 때
	// => 입력된 값들을 서버로 전송요청 처리해야한다 : location

	// ** self.location   
	// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
	// 2) location 객체의 메서드
	// => href, replace('...'), reload() 
	function searchDB() {
		/* url 추가 */
		self.location = 'mPageList'
		/* +'${pageMaker.makeQuery(1)}'  
		   => 하나의 jsp 문서로 다양한 요청을 처리하기위해 쿼리스트링에 url 을 포함했기 때문에
		      첫 요청에서는  makeQuery 메서드 사용할수 없음 */
		+ '?currPage=1&rowsPerPage=5' 
		+ '&searchType='
		+ document.getElementById('searchType').value
		+ '&keyword='
		+ document.getElementById('keyword').value;
	}//searchDB

	//2. searchType 을 '전체' 로 변경하면 keyword는 clear 
	function keywordClear() {
		if (document.getElementById('searchType').value == 'all') {
			document.getElementById('keyword').value = '';
		}
	}//keywordClear

	// class로 주었기 때문에 getElementById 대신 querySelectorAll 사용

	// ** querySelector
	// => css 선택자를 이용하여 첫번째 만난 요소 1개만 선택

	// ** querySelectorAll 
	// => css 선택자를 이용하여 해당하는 nodeList 를 반환
	// =>  ","를 사용하면 여러 요소를 한번에 가져올 수 있음
	//     querySelectorAll("#id,.class");
	// => 그러므로 반복문과 이용됨.
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
</script>

<style>
table {
	border-collapse: collapse;
	width: 500px;
	margin: 1rem auto;
	border: 1px solid #ddd;
	background-color: white;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #ddd;
	/* text-align: center; */
}

th {
	background-color: #1b1b1b;
	color: #ddd;
}

tbody tr:hover {
	background-color: #d3d3d3;
	opacity: 0.9;
	cursor: pointer;
}

a {
	color: inherit;
	text-decoration: none;
}

a:hover {
	color: red;
}
</style>
<body>
	<h3>** Spring MVC2 Board List **</h3>

	<c:if test="${not empty requestScope.message}">
		=> ${requestScope.message}<br>
	</c:if>
	<hr>

	<!-- 검색기능 -->
	<div id="searchBar">
		<select name="searchType" id="searchType" onchange="keywordClear()">
			<option value="all"	${pageMaker.cri.searchType=='all' ? 'selected' : ''}>전체</option>
			<option value="id" ${pageMaker.cri.searchType=='id' ? 'selected' : ''}>ID</option>
			<option value="name" ${pageMaker.cri.searchType=='name' ? 'selected' : ''}>Name</option>
			<option value="age" ${pageMaker.cri.searchType=='age' ? 'selected' : ''}>나이</option>
			<option value="birthday" ${pageMaker.cri.searchType=='birthday' ? 'selected' : ''}>Birthday</option>
			<option value="info" ${pageMaker.cri.searchType=='info' ? 'selected' : ''}>INFO</option>
			<option value="rid"	${pageMaker.cri.searchType=='rid' ? 'selected' : ''}>추천인</option>
		</select> <input type="text" name="keyword" id="keyword"
			value="${pageMaker.cri.keyword}">
		<button id="searchBtn" onclick="searchDB()">Search</button>

		<!-- CheckBox Test -->
		<form action="mCheckList" method="get">
			<b>조이름 : </b>
			<!-- check의 선택한 값 유지를 위한 코드 -->
			<c:set var="ck1" value="false" />
			<c:set var="ck2" value="false" />
			<c:set var="ck3" value="false" />
			<c:set var="ck4" value="false" />
			<c:forEach var="id" items="${pageMaker.cri.check}">
				<c:if test="${jno=='1'}">
					<c:set var="ck1" value="true" />
				</c:if>
				<c:if test="${jno=='2'}">
					<c:set var="ck2" value="true" />
				</c:if>
				<c:if test="${jno=='3'}">
					<c:set var="ck3" value="true" />
				</c:if>
				<c:if test="${jno=='4'}">
					<c:set var="ck4" value="true" />
				</c:if>
			</c:forEach>
			<!--  -->

			<input type="checkbox" name="check" class="clear" value="1"
				${ck1 ? 'checked' : '' }>Business&nbsp; 
				<input type="checkbox" name="check" class="clear" value="2"
				${ck2 ? 'checked' : '' }>static&nbsp; 
				<input type="checkbox" name="check" class="clear" value="3" 
				${ck3 ? 'checked' : '' }>칭찬해조&nbsp;
				<input type="checkbox" name="check" class="clear" value="4"
				${ck4 ? 'checked' : '' }>카톡으로&nbsp; 
				
				<input type="submit" value="Search">&nbsp; 
				<input type="reset" value="Clear"
				onclick="return checkClear()">&nbsp;
		</form>
	</div>
	<hr>


	<table style="width: 100%">
		<tr bgcolor="DarkSlateBlue" style="color: white">
			<th>ID</th>
			<th>Name</th>
			<th>Age</th>
			<th>Jno</th>
			<th>Info</th>
			<th>Point</th>
			<th>Birthday</th>
			<th>추천인</th>
			<th>프로필사진</th>
		</tr>
		<c:if test="${!empty requestScope.mlist}">
			<c:forEach var="m" items="${requestScope.mlist}">
				<tr bgcolor="Lavender">
					<td>${m.id}</td>
					<td>${m.name}</td>
					<td>${m.age}</td>
					<td>${m.jno}</td>
					<td>${m.info}</td>
					<td>${m.point}</td>
					<td>${m.birthday}</td>
					<td>${m.rid}</td>
					<td><img alt="myImage" width="70" height="70"
						src="/spring02/resources/uploadImages/${m.uploadfile}"></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty requestScope.mlist}">
			<tr>
				<td colspan="9">출력할 데이터가 없슴당</td>
			</tr>
		</c:if>
	</table>

	<hr>
	<div align="center">


   <!-- 1) FirstPage, Prev -->
		<c:choose>
			<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
				<!-- ver02 : makeQuery를 searchQuery로 + 메서드 내 url 추가 -->
				<a href="${pageMaker.searchQuery(1)}">FP</a>&nbsp;
   		<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;
   	</c:when>

			<c:otherwise>
				<font color="Gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
			</c:otherwise>
		</c:choose>

		<!-- 2) Display PageNo -->
		<c:forEach var="i" begin="${pageMaker.spageNo}"
			end="${pageMaker.epageNo}">
			<!-- 현재 페이지 -->
			<c:if test="${i==pageMaker.cri.currPage}">
				<font color="Orange" size="5"><b>${i}</b></font>
			</c:if>
			<!-- 현재 페이지 제외 모든 페이지 : 페이지 클릭 시 해당 페이지의 값을 가지고 넘어갈 수 있도록-->
			<c:if test="${i!=pageMaker.cri.currPage}">
				<%-- <a href="bPageList?currPage=${i}&rowsPerPage=5">${i}</a>&nbsp; --%>
				<%-- <a href="${pageMaker.makeQuery(i)}">${i}</a>&nbsp; --%>
				<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
		</c:if>
		</c:forEach>

		<!-- 3) Next, LastPage -->

		<c:choose>
			<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
				<!-- ver02 메서드 내 url 추가 -->
      			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
      			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">LP</a>

			</c:when>

			<c:otherwise>
				<font color="Gray">&nbsp;&GT;&nbsp;LP</font>
			</c:otherwise>
		</c:choose>
	</div>
	<hr>


	&nbsp;
	<a href="/spring02/home">Home</a>&nbsp; &nbsp;
	<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

	<c:if test="${!empty sessionScope.loginID}">
&nbsp;<a href="boardInsert">글 작성하기</a>&nbsp;
</c:if>
</body>
</html>
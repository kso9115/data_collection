<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Board List **</title>
</head>
<!-- <link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css"> -->
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

	<table style="width: 100%">

		<tr>
			<th>Seq</th>
			<th>Title</th>
			<th>I D</th>
			<th>RegDate</th>
			<th>조회수</th>
		</tr>
<c:if test="${!empty requestScope.blist}">
	<c:forEach var="b" items="${requestScope.blist}">
		<tr>
			<td>${b.seq}</td>
			<td>
			<!-- 답글 등록한 후 Title 출력전에 들여쓰기를 추가하기
				1부터 시작하여, indent(level)까지  -->
			<c:if test="${b.indent>0}">
				<c:forEach begin="1" end="${b.indent}">
					<span>&nbsp;&nbsp;</span>
				</c:forEach>
				<span style="color:red">ㄴre</span>
			</c:if>
			
			<c:if test="${!empty loginID}">
				<a href="detail?jCode=D&seq=${b.seq}">${b.title}</a>			
			</c:if>
			<c:if test="${empty loginID}">
				${b.title}			
			</c:if>
			
			</td>
			<td>${b.id}</td>
			<td>${b.regdate}</td>
			<td>${b.cnt}</td>
		</tr>
	</c:forEach>
</c:if>

<c:if test="${empty requestScope.blist}">
		<tr>
			<td colspan="5">출력할 데이터가 없슴당</td>
		</tr>
</c:if>
	</table>
	
<hr>
<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성

   1) FirstPage, Prev  -->
   <c:choose>
   	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
   		<a href="bPageList?currPage=1&rowsPerPage=5">FP</a>&nbsp;
   		<a href="bPageList?currPage=${pageMaker.spageNo-1}&rowsPerPage=5">&LT;</a>&nbsp;&nbsp;
   	</c:when>
   	
   	<c:otherwise>
   		<font color="Gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
   	</c:otherwise>     
   </c:choose>
     
<!-- 2) Display PageNo
		=> currPage 제외한 PageNo만 a Tag 적용 ()
 -->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<!-- 현재 페이지 -->
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>
		</c:if>
		<!-- 현재 페이지 제외 모든 페이지 : 페이지 클릭 시 해당 페이지의 값을 가지고 넘어갈 수 있도록-->
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="bPageList?currPage=${i}&rowsPerPage=5">${i}</a>&nbsp;
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage 
      => ver01: makeQuery() 메서드 사용
      => ver02: searchQuery() 메서드 사용 -->
      
      <c:choose>
      	<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
      		<!-- 마지막 페이지에서 NEXT 클릭 시 다음 블럭의 첫 번째 페이지로 이동 -->
      		&nbsp;<a href="bPageList?currPage=${pageMaker.epageNo+1}&rowsPerPage=5">&GT;</a>
      		&nbsp;<a href="bPageList?currPage=${pageMaker.lastPageNo}&rowsPerPage=5">LP</a>
      	</c:when>
  	
  	  	<c:otherwise>
			<font color="Gray">&nbsp;&GT;&nbsp;LP</font>
	   	</c:otherwise>    
      </c:choose>

</div>
<hr>


&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

<c:if test="${!empty sessionScope.loginID}">
&nbsp;<a href="boardInsert">글 작성하기</a>&nbsp;
</c:if>
</body>
</html>
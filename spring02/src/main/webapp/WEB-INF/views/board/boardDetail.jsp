<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Board Detail **</title>


</head>

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
  text-align: center;
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
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">

<body>
<c:set var="d" value="${requestScope.seqContent}"></c:set>


<form action="detail" method="post">
	
	<c:if test="${empty d}">
		<tr><td colspan="2"><h3> 출력할 차료가 없습니다. </h3></td></tr>
	</c:if>
	
	<hr>
	
<table border="1" style="width: 100%">
		<tr style="color:white">
			<th>Seq</th>
			<th>Title</th>
			<th>Content</th>
			<th>I D</th>
			<th>RegDate</th>
			<th>조회수</th>
		</tr>
		
	<c:if test="${!empty d}">
		<tr>
			<td>${d.seq}</td>
			<td>${d.title}</td>
			<td>${d.content}</td>
			<td>${d.id}</td>
			<td>${d.regdate}</td>
			<td>${d.cnt}</td>
		</tr>
	</c:if>
</table>
</form>

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
<c:if test="${d.id==sessionScope.loginID}">
&nbsp;<a href="detail?jCode=U&seq=${d.seq}">수정하기</a>&nbsp;
&nbsp;<a href="detail?jCode=X&seq=${d.seq}">삭제하기</a>&nbsp;
</c:if>
<!-- 로그인 시 답글등록 -->
<c:if test="${!empty sessionScope.loginID}">
<!-- &nbsp;<a href="boardInsert">새글달기</a>&nbsp; -->
<!-- 댓글등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
    서버로 보내주어야함 (퀴리스트링으로 작성)    -->
&nbsp;<a href="replyInsert?root=${seqContent.root}&step=${seqContent.step}
				&indent=${seqContent.indent}">답글달기</a>&nbsp;
</c:if>
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
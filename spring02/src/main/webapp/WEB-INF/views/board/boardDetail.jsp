<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Board Detail **</title>


</head>

<!-- <style>
	tbody:first-child{
	display : flex;
	width : 
	}
	
	tr:first-child{
	display : flex;
	flex-direction: column;
	}
</style> -->
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">


<body>
<c:set var="d" value="${requestScope.seqContent}"></c:set>
<form action="detail" method="post">
	
	<c:if test="${empty d}">
		<tr><td colspan="2"><h3> 출력할 차료가 없습니다. </h3></td></tr>
	</c:if>
	
	<hr>
	
<table border="1" style="width: 100%">
		<tr bgcolor="DarkCyan" style="color:white">
			<th>Seq</th>
			<th>Title</th>
			<th>I D</th>
			<th>RegDate</th>
			<th>조회수</th>
		</tr>
		
	<c:if test="${!empty d}">
			<tr bgcolor="LightCyan">
				<td>${d.seq}</td>
				<td>${d.title}</td>
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
</c:if>
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
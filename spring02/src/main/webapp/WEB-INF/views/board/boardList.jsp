<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Board List **</title>
</head>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css">

<body>
	<h3>** Spring MVC2 Board List **</h3>

	<c:if test="${not empty requestScope.message}">
		=> ${requestScope.message}<br>
	</c:if>
	<hr>

	<table border="1" style="width: 100%">

		<tr bgcolor="lavender">
			<th>Seq</th>
			<th>Title</th>
			<th>I D</th>
			<th>RegDate</th>
			<th>조회수</th>
		</tr>
<c:if test="${!empty requestScope.blist}">
	<c:forEach var="b" items="${requestScope.blist}">
		<tr bgcolor="Lavender">
			<td>${b.seq}</td>
			<td>
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

	&nbsp;
	<a href="/spring02/home">Home</a>&nbsp; &nbsp;
	<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;
</body>
</html>
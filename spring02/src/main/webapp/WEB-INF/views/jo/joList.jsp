<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring02_MVC02 JoList **</title>
</head>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">
<body>

<h2>** Spring02_MVC02 JoList **</h2>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
	</c:if>
	
<table border="1" style="width:100%">
		<tr bgcolor="lightblue">
			<th>조번호</th>
			<th>조이름</th>
			<th>조장</th>
			<th>Project</th>
			<th>Slogan</th>
		</tr>
<c:if test="${!empty requestScope.joList}">
	<c:forEach var="j" items="${requestScope.joList}">
		<tr >
			<td><a href="joDetail?jo=${j.jno}">${j.jno}</a></td>
			<td>${j.jname}</td>
			<td>${j.captain}</td>
			<td>${j.project}</td>
			<td>${j.slogan}</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.joList}">
	<tr>
	<td colspan="5">출력할 데이터가 없슴당</td>
	</tr>
</c:if>

</table>
<hr>

&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;
&nbsp;<a href="joinsertForm">새로운 조 등록하기</a>&nbsp;
</body>
</html>
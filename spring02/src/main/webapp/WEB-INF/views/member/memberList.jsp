<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList **</title>
</head>
<body>

<h2>** Web02_MVC02 MemberList **</h2>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
	</c:if>
	
<table border="1" style="width:100%">
		<tr bgcolor="DarkSlateBlue" style="color:white">
			<th>ID</th>
			<th>Password</th>
			<th>Name</th>
			<th>Age</th>
			<th>Jno</th>
			<th>Info</th>
			<th>Point</th>
			<th>Birthday</th>
			<th>추천인</th>
		</tr>
<c:if test="${!empty requestScope.mlist}">
	<c:forEach var="m" items="${requestScope.mlist}">
		<tr bgcolor="Lavender">
			<td>${m.id}</td>
			<td>${m.password}</td>
			<td>${m.name}</td>
			<td>${m.age}</td>
			<td>${m.jno}</td>
			<td>${m.info}</td>
			<td>${m.point}</td>
			<td>${m.birthday}</td>
			<td>${m.rid}</td>
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

&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="">이전으로</a>&nbsp;
</body>
</html>
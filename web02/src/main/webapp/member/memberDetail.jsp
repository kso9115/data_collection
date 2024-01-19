<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web MVC2 My Page **</title>


</head>

<style>
	tbody{
	display : flex;
	width : 
	}
	
	tr{
	display : flex;
	flex-direction: column;
	}
</style>

<body>
<c:set var="m" value="${requestScope.apple}"></c:set>
<form action="/web02/mDetail" method="post">
	
	<table border="1" style="width: 100%">
		<tr bgcolor="DarkCyan" style="color:white">
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
		
	<c:if test="${!empty m}">
		<tr bgcolor="LightCyan">
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
	</c:if>
	
	<c:if test="${empty m}">
		<tr><td colspan="2"><h3> 출력할 차료가 없습니다. </h3></td></tr>
	</c:if>
</table>
</form>

&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
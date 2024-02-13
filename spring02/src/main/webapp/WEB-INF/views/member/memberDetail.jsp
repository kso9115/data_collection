<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring02 MVC2 My Page **</title>


</head>

<style>
/* 	tbody{
	display : flex;
	width : 
	}
	
	tr{
	display : flex;
	flex-direction: column;
	} */
</style>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle4.css">


<body>
<c:set var="m" value="${requestScope.mdetail}"></c:set>
<form action="detail" method="post">
	
	<table border="1" style="width: 60%">
	
	<c:if test="${!empty m}">
		<tr>
			<th>프로필사진</th>
			<td><img alt="myImage" width="250" height="300"
					src="/spring02/resources/uploadImages/${m.uploadfile}"></td>
		</tr>
		<tr>
			<th>ID</th>
			<td>${m.id}</td>
		</tr>
		<tr>
			<th>Name</th>
			<td>${m.name}</td>
		</tr>
		<tr>
			<th>Age</th>
			<td>${m.age}</td>
		</tr>
		<tr>
			<th>Jno</th>
			<td>${m.jno}</td>
		</tr>
		<tr>
			<th>Info</th>
			<td>${m.info}</td>
		</tr>
		<tr>
			<th>Point</th>
			<td>${m.point}</td>
		</tr>
		<tr>
			<th>Birthday</th>
			<td>${m.birthday}</td>
		</tr>
		<tr>
			<th>추천인</th>
			<td>${m.rid}</td>
		</tr>
		
	</c:if>
		
	<%-- 	<tr bgcolor="DarkCyan" style="color:white">
			<th>프로필사진</th>
			
			<th>ID</th>
			<!-- <th>Password</th> -->
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
			<td><img alt="myImage" width="400" height="400"
					src="/spring02/resources/uploadImages/${m.uploadfile}"></td>
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
	</c:if> --%>
	
	<c:if test="${empty m}">
		<tr><td colspan="2"><h3> 출력할 차료가 없습니다. </h3></td></tr>
	</c:if>
</table>
</form>

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
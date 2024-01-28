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
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">


<body>
<c:set var="d" value="${requestScope.joDetail}"></c:set>
<form action="detail" method="post">
	
	<table border="1" style="width: 100%">
		<tr bgcolor="lightblue">
			<th>조번호</th>
			<th>조이름</th>
			<th>조장</th>
			<th>Project</th>
			<th>Slogan</th>
		</tr>
		
	<c:if test="${!empty d}">
		<tr bgcolor="LightCyan">
			<td>${d.jno}</td>
			<td>${d.jname}</td>
			<td>${d.captain}</td>
			<td>${d.project}</td>
			<td>${d.slogan}</td>
		</tr>
	</c:if>
	
	<c:if test="${empty d}">
		<tr><td colspan="2"><h3> 출력할 차료가 없습니다. </h3></td></tr>
	</c:if>
</table>
</form>

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;
&nbsp;<a href="joDetail?jo=1${d.jno}">수정하기</a>&nbsp;
&nbsp;<a href="joDetail?jo=2${d.jno}">탈퇴하기</a>&nbsp;

</body>
</html>
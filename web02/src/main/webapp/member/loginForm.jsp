<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Login Form **</title>
</head>

<style>
/* 	tbody{
	display : flex;
	}
	
	tr{
	display : flex;
	flex-direction : column;
	} */
</style>

<body>
<h2>** Web MVC2 Login Page **</h2>
<hr>
<img alt="" src="/web02/images/basicman3.jpg" width="200px" height="200px" border="1">
<form action="/web02/login" method="post">
	<table>
		<tr height="40">
			<td><label for="id">ID</label></td>
			<td><input type="text" id="id" name="id" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="password">PW</label></td>
			<td><input type="password" id="password" name="password" size="18"></td>
		</tr>
		<tr>
		<td></td>
		<td>
		<input type="submit" value="로그인" size="5">&nbsp;&nbsp;
		<input type="reset" value="취소" size="5">
		</td>
		</tr>
		
	</table>
</form>
<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
</c:if>

<hr>
&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
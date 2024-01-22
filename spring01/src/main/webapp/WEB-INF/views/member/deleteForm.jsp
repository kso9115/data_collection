<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Delete Form **</title>
</head>

<body>
<h2>** Web MVC2 Delete Page **</h2>
<hr>
<img alt="" src="/web02/images/basicman3.jpg" width="200px" height="200px" border="1">
<form action="/web02/mdelete" method="post">
	<table>
		<tr height="40">
			<td>삭제하시겠나이까?</td>
			<td><input type="submit" value="삭제"></td>
			<td><input type="reset" value="취소" onClick="location.href='/web02/home.jsp'"></td>
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
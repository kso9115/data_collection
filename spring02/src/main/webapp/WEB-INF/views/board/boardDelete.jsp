<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board delete Form **</title>
</head>

<body>
	<h2>** Spring02 MVC2 Board delete Page **</h2>
	<hr>
	
<form action="delete?seq=${requestScope.seqContent.seq}
				  &root=${requestScope.seqContent.root}" method="post">
<h2>${requestScope.seqContent.id}님아</h2>
<h3>글을 삭제하시겠습니까?</h3>
<input type="submit" value="삭제완료">
<input type="reset" value="취소" onClick="location.href='/spring02/home'">
</form>

	
	<hr>
	&nbsp;
	<a href="/spring02/home">Home</a>&nbsp; &nbsp;
	<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
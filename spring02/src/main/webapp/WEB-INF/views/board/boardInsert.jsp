<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board Insert Form **</title>
</head>
<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle.css">
<style>
textarea {
	padding: 10px;
	max-width: 100%;
	line-height: 1.5;
	border-radius: 5px;
	border: 1px solid #ccc;
	box-shadow: 1px 1px 1px #999;
}

input {
	width: 150px;
	height: 25px;
	font-size: 12px;
	border: 0;
	border-radius: 15px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
}

textarea {
	width: 400px;
	height: 200px;
	font-size: 15px;
	border: 0;
	border-radius: 15px;
	outline: none;
	padding-left: 10px;
	background-color: rgb(233, 233, 233);
}
</style>
<body>
	<h2>** Spring02 MVC2 Board Insert Page **</h2>
	<hr>
	${requestScope.loginID}
	<form action="insert" method="post">
		<table>
			<tr height="40">
				<td><label for="title">Title</label></td>
				<td><input type="text" id="title" name="title" size="18">
			</tr>
			<tr height="40">
				<td><label for="content">글내용</label></td>
				<td><textarea rows="10" cols="50" placeholder="작성하세요"></textarea>
				<!-- 아이디값이 있으면..텍스트 창 자체가 안뜨고..아이디값이 없으면..
					데이터가 다 null로 넘어가고 난리 ㅠㅠ -->
				</td>
			</tr>
			<tr height="40">
				<td><label for="id">작성자 ID</label></td>
				<td><input type="text" id="id" name="id"
					value="${sessionScope.loginID}" size="18" readonly></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="완료" size="5">&nbsp;&nbsp;
					<input type="reset" value="취소" size="5"></td>
			</tr>

		</table>
	</form>
	&nbsp;
	<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
	</c:if>

	<hr>
	&nbsp;
	<a href="/spring02/home">Home</a>&nbsp; &nbsp;
	<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board Update Form **</title>
</head>

<body>

	<h2>** Spring02 MVC2 Board Update Page **</h2>
	<hr>

	<form action="update" method="post">
		<table>

			<tr height="40">
				<td><label for="seq">글번호</label></td>
				<td><input type="text" id="seq" name="seq" size="18"
					value="${requestScope.seqContent.seq}" readonly></td>
			</tr>
			<tr height="40">
				<td><label for="title">Title</label></td>
				<td><input type="text" id="title" name="title" size="18"
					value="${requestScope.seqContent.title}"></td>
			</tr>
			<tr height="40">
				<td><label for="content">글내용</label></td>
				<td>
					<textarea id="content" name="content" rows="6" cols="33">
					${requestScope.seqContent.content}</textarea>
				</td>
			</tr>
			<tr height="40">
				<td><label for="id">ID</label></td>
				<td><input type="text" id="id" name="id" size="18" readonly
					value="${requestScope.seqContent.id}"></td>
			</tr>
			<tr height="40">
				<td><label for="regdate">작성시간(수정불가)</label></td>
				<td><input type="datetime" id="regdate" name="regdate" size="18"
					value="${requestScope.seqContent.regdate}" readonly></td>
			</tr>
			<tr height="40">
				<td><label for="cnt">조회수</label></td>
				<td><input type="text" id="cnt" name="cnt" size="18"
					value="${requestScope.seqContent.cnt}"></td>
			</tr>
			<tr>

				<td></td>
				<td><input type="submit" value="완료" size="5">&nbsp;&nbsp;
					<input type="reset" value="취소" size="5"></td>
			</tr>

		</table>
	</form>
	<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
	</c:if>

	<hr>
	&nbsp;
	<a href="/spring02/home">Home</a>&nbsp; &nbsp;
	<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
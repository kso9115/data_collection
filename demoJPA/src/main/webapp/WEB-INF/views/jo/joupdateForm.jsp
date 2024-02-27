<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update Form **</title>
</head>

<body>

	<h2>** Spring02 MVC2 Update Page **</h2>
	<hr>

	<form action="update" method="post">
		<table>

			<tr height="40">
				<td><label for="jno">조번호</label></td>
				<td><input type="text" id="jno" name="jno" size="18"
					value="${requestScope.joDetail.jno}" readonly
					placeholder="숫자만 입력하세요"></td>
				<!-- <td><input type="submit" value="중복검사" id="id" name="id"></td> -->
			</tr>
			<tr height="40">
				<td><label for="jname">조이름</label></td>
				<td><input type="text" id="jname" name="jname" size="18"
					value="${requestScope.joDetail.jname}"></td>
			</tr>
			<tr height="40">
				<td><label for="captain">조장</label></td>
				<td><input type="text" id="captain" name="captain" size="18"
					value="${requestScope.joDetail.captain}"></td>
			</tr>
			<tr height="40">
				<td><label for="project">Project</label></td>
				<td><input type="text" id="project" name="project" size="18"
					value="${requestScope.joDetail.project}"></td>
			</tr>
			<tr height="40">
				<td><label for="slogan">Slogan</label></td>
				<td><input type="text" id="slogan" name="slogan" size="18"
					value="${requestScope.joDetail.slogan}"></td>
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
	<a href="/home">Home</a>&nbsp; &nbsp;
	<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;
	<c:if test="${!empty sessionScope.loginID}">
	</c:if>

</body>
</html>
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

<h2>** Web MVC2 Update Page **</h2>
<hr>

<form action="update" method="post">
	<table>
		<tr height="40">
			<td><label for="id">ID</label></td>
			<td><input type="text" id="id" name="id" size="18" value="${requestScope.mdetail.id}" readonly placeholder="영문과 숫자 4~10글자"></td>
		</tr>
		<tr height="40">
			<td><label for="password">Password</label></td>
			<td><input type="password" id="password" name="password" value="${requestScope.mdetail.password}" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="name">Name</label></td>
			<td><input type="text" id="name" name="name" value="${requestScope.mdetail.name}" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="age">Age</label></td>
			<td><input type="text" id="age" name="age" value="${requestScope.mdetail.age}" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="jno">Jno</label></td>
			<td><select name="jno" id="jno" >
				<option value="1" ${mdetail.jno==1 ? "selected":""}>1조 : Buisness</option>
				<option value="2" ${mdetail.jno==2 ? "selected":""}>2조 : static</option>
				<option value="3" ${mdetail.jno==3 ? "selected":""}>3조 : 칭찬해조</option>
				<option value="4" ${mdetail.jno==4 ? "selected":""}>4조 : 카톡으로얘기하조</option>
				<option value="7" ${mdetail.jno==7 ? "selected":""}>7조 : 칠면조</option>
			</select></td>
		</tr>
		<tr height="40">
			<td><label for="info">Info</label></td>
			<td><input type="text" id="info" name="info" value="${requestScope.mdetail.info}" size="18" placeholder="자기소개 & 희망사항"></td>
		</tr>
		<tr height="40">
			<td ><label for="point">Point</label></td>
			<td><input type="text" id="point" name="point" value="${requestScope.mdetail.point}" size="18" placeholder="실수 입력 가능"></td>
		</tr>
		<tr height="40">
			<td><label for="birthday">Birthday</label></td>
			<td><input type="date" id="birthday" name="birthday" value="${requestScope.mdetail.birthday}" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="rid">추천인</label></td>
			<td><input type="text" id="rid" name="rid" value="${requestScope.mdetail.rid}" size="18"></td>
		</tr>
		<tr>
		
		<td></td>
		<td>
		<input type="submit" value="수정완료" size="5">&nbsp;&nbsp;
		<input type="reset"  value="취소" size="5">
		</td>
		</tr>
		
	</table>
</form>
<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
</c:if>

<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;


</body>
</html>
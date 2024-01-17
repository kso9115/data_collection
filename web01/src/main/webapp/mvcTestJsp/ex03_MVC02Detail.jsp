<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Java MVC_Detail JSTL **</title>
</head>
<body>
<h3>하이</h3>
<%-- ${requestScope} --%>
<c:set var="s" value="${requestScope.myinfo}"/>

<table border="1" style="width: 100%">
		<tr bgcolor="MediumAquaMarine">
			<th>Sno</th>
			<th>Name</th>
			<th>Age</th>
			<th>Jno</th>
			<th>Info</th>
			<th>Point</th>
		</tr>
	<c:if test="${not empty s}">
		<tr>
			<th>${s.sno}</th>
			<th>${s.name}</th>
			<th>${s.age}</th>
			<th>${s.jno}</th>
			<th>${s.info}</th>
			<th>${s.point}</th>
		</tr>
	</c:if>

	<c:if test="${empty s}">
		<tr><td colspan="2"><h3> 출력할 차료가 없습니다. </h3></td></tr>
	</c:if>
	</table>

</body>
</html>
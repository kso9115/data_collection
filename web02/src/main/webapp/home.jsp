<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Home **</title>
</head>
<body>
<h2>** Web02_MVC02 **</h2>
<h2>MVC2 패턴을 공부합쉬다</h2>

<c:set value="${sessionScope.id}" var="id"></c:set>

<c:if test="${id!=null}">
<h3>${id}님 안녕하세요</h3>
</c:if>

<c:if test="${id==null}">
<span>로그인 후 이용하세요</span>
</c:if>

<hr>
	<img alt="" src="/web02/images/party_parrot.gif">
	<hr>
	<!-- 로그인 / 로그아웃 연동 -->
	
<c:if test="${id!=null}">
	&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
	<!-- ?jCode=U view만 변경 시 -->
	&nbsp;<a href="/web02/logout">Logout</a>&nbsp;
	&nbsp;<a href="/web02/member/deleteForm.jsp">탈퇴</a>&nbsp;
</c:if>
<c:if test="${id==null}">
	&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="/web02/member/joinForm.jsp">Join</a>&nbsp;
</c:if>
	
	&nbsp;<a href="/web02/mlist">MList</a>&nbsp;

	
</body>
</html>
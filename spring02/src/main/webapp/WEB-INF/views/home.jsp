<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css">

<body>
<h1>Hello Spring !</h1>
<hr>
<h3>당신에게 제일 소중한 금, 바로 지금..^^</h3>
<P> ${serverTime}. </P>
<hr>
<c:if test="${!empty sessionScope.loginName}">
<img alt="mainImage" src="/spring02/resources/images/JHBae.png" >
	${sessionScope.loginName}님 안녕하세요<br>
</c:if>
<c:if test="${empty sessionScope.loginID}">
<img alt="mainImage" src="/spring02/resources/images/green01.png" width=300px height=300px><br>
	<h3>로그인 후 이용해주갓어?</h3>
</c:if>

<c:if test="${!empty requestScope.message}">
	<hr>${requestScope.message}<br>
</c:if>

<hr>

<!-- Login 전 -->
<c:if test="${empty sessionScope.loginID}">
&nbsp;<a href="member/loginForm">Login</a>&nbsp;
&nbsp;<a href="member/joinForm">Join</a>&nbsp;
</c:if>
<!-- Login 후 -->
<c:if test="${!empty sessionScope.loginID}">
&nbsp;<a href="member/logout">Logout</a>&nbsp;
&nbsp;<a href="member/detail?jCode=D">내정보보기</a>&nbsp;
&nbsp;<a href="member/detail?jCode=U">회원정보수정</a>&nbsp;
&nbsp;<a href="member/delete">탈퇴하기</a>&nbsp;
</c:if>

<!-- dispatcher로 -->
&nbsp;<a href="member/memberList">MList</a>&nbsp;
</body>
</html>

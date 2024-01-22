<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h2>Hello Spring !</h2>
<P>  The time on the server is ${serverTime}. </P>
<hr>
<c:if test="${loginId!=null}">
<h3>${loginName}님 안녕하세요</h3>
</c:if>

<c:if test="${loginId==null}">
<span>로그인 후 이용하세요</span>
</c:if>

<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
</c:if>

<hr>
<img alt="" src="resources/images/green01.png">
<hr>

<c:if test="${loginId!=null}">
	&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
	<!-- ?jCode=U view만 변경 시 -->
	&nbsp;<a href="/web02/logout">Logout</a>&nbsp;
	&nbsp;<a href="/web02/member/deleteForm.jsp">탈퇴</a>&nbsp;
</c:if>
<c:if test="${loginId==null}">
	&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="/web02/member/joinForm.jsp">Join</a>&nbsp;
</c:if>

<!-- 내가맹글은 dispatcher로 -->
&nbsp;<a href="mlist">MList</a>&nbsp;
&nbsp;<a href="mdetail">MDetail</a>&nbsp;
<!-- 스프링의 dispatcher로 -->
&nbsp;<a href="mlistsp">MListSP</a>&nbsp;
&nbsp;<a href="mdetailsp">MDetailSP</a>&nbsp;
</body>
</html>

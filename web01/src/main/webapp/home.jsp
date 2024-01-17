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
<h2>** Dynamic Web Project **</h2>

<c:set value="${sessionScope.loginName}" var="loginName"></c:set>

<c:if test="${loginName!=null}">
<h3>${sessionScope.loginName}님 안녕하세요</h3>
</c:if>

<c:if test="${loginName==null}">
<span>로그인 후 이용하세요</span>
</c:if>

<%-- <%
	if(session.getAttribute("loginName")!=null){ %>
		<h3><%=session.getAttribute("loginName")%>님 안녕하세요</h3>
<% 	} else{ %>
		로그인 후 이용하세요 <br>
<% }
%> --%>
 

<hr>
	<form action="life" method="post">
		<input type ="text" id = "id" name ="id" value = "banana"> &nbsp;
		<input type ="text" id = "name" name ="name" value = "바나나">
		<input type ="text" id = "password" name ="password">
		<input type ="submit" value ="Test">
	</form>
	<hr>
	<!-- ** 경로표기
	 => 절대경로 : / 로 시작, 프로젝트명부터 전체경로 표기
	 			 /web01/images/letsgo.png
	 			  webapp 폴더는 생략된다.
	 	상대경로 : ./ (현재 위치)에서 시작 ../ 현재에서 1단계 상위 위치
	 			 "./images/letsgo.png", "images/letsgo.png" 동일한 경로이다
	 -->
	<img alt="" src="images/party_parrot.gif">
	<hr>
	<!-- 로그인 / 로그아웃 연동 -->
	
	<c:if test="${loginName!=null}">
	&nbsp;<a href="/web01/myinfo">MyInfo</a><br>
	&nbsp;<a href="/web01/logout">Logout</a>&nbsp;
	</c:if>
	<c:if test="${loginName==null}">
	&nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">LoginForm</a>&nbsp;
	</c:if>
	

	<!-- 초기 화면 연동 -->
	&nbsp;<a href="/web01/hello">Hello</a>&nbsp;
	&nbsp;<a href="/web01/list">MVC01List</a>&nbsp;
	&nbsp;<a href="/web01/life">LifeCycle</a><br>
	<!-- sevletTestForm 연동(예제문제) -->
	&nbsp;<a href="/web01/servletTestForm/form01_Adder.html">Adder</a>
	&nbsp;<a href="/web01/servletTestForm/form02_Radio.jsp">Radio</a>
	&nbsp;<a href="/web01/servletTestForm/form03_Check.jsp">Check</a>
	&nbsp;<a href="/web01/servletTestForm/form04_Select.jsp">Select</a><br>
	&nbsp;<a href="/web01/flow01">Flow01</a>&nbsp;
	&nbsp;<a href="/web01/sessioni">SessionI</a>&nbsp;
	&nbsp;<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>&nbsp;
	&nbsp;<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ</a>&nbsp;
	&nbsp;<a href="/web01/list2">M02ListJ</a>&nbsp;

	
</body>
</html>
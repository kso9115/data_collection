<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>** View Cookies **</h2>
	<pre>
=> 웹 브라우져는 request의 header에 쿠키의 값을 담아보냄
=> request 객체에 담겨진 쿠키목록 확인
=> request.getCookies() : 배열타입이며 없으면 null
<hr>
		<b>
=> Cookie List
1. 쿠키 배열로 담아준 후, 데이터가 전달되었는지 확인(쿠키의 이름과 값을 확인)
<%
		Cookie[] ck = request.getCookies();
		if (ck != null && ck.length > 0) {
			for (Cookie c : ck) {
				out.print("<br>* Name : " + c.getName());
				out.print("<br>* Value : " + c.getValue());
			}
		} else {
			out.print("<br>** Cookie NotFound **");
		}
%>
</b><hr>
=> <a href="ex02_makeCookies.jsp">MakeCookies</a>
=> <a href="ex03_upDelCookies.jsp">UpDelCookies</a>
</pre>


</body>
</html>
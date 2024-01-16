<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Hello Jsp **</title>
</head>
<body>
	<h2>** Hello Jsp **</h2>
	<h3>~~ 안녕 하세요 ~~</h3>
	<h3>** Jsp의 장점 : View가 간편하다</h3>
	<h3>** Jsp의 단점 : Java Code는 불편하다</h3>
	<hr>
	<h3>=> Java Code</h3>
	<pre>
1) Scriptlet : 자바코드
2) Expression : 표현식 (출력문)
3) Declaration : 선언부 (메서드 등)
</pre>
	
	
	1. Declaration : 선언부
	<%!
	// Declaration : 선언부
	public int multiply(int a, int b) {
		return a * b;
	}

	String name = "홍길동";
	int i = 100;
	int j = 200;
	%>
	2. Expression : 표현식 (출력문) <br>
	multiply(4,5)의 경과는
	<%= // 자체로 출력을 해주기때문에 세미콜론 사용하지 않음
	multiply(4,5)%><br>
	변수 출력 : i=<%=i%>,j=<%=j%>,name=<%=name%><br>
	연산 적용 : i+j=<%=i+j%><br>
	
	3. Scriptlet : 자바코드<br>
<%
		// multiply(4,5) 의 결과를 짝/홀 구분해서 출력하기
		if(multiply(i, j) % 2 == 0 ){ %>
			multiply(i,j)의 결과는 짝수 <%=multiply(i,j)%> 입니다.<br>
<%		} else{ %>
			multiply(i,j)의 결과는 짝수 <%=multiply(i,j)%> 입니다.<br>
<%		}
%>

	
</body>
</html>
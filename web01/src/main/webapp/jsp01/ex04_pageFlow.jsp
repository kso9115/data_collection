<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jsp Page Flow **</title>
</head>
<body>
<h2>** Jsp Page Flow **</h2>
<h3>1. Forward</h3>
=> JSP 표준 Action Tag 를 이용한 이동
<script type="text/javascript">
	alert("~~ Hello 로 이동 합니다 ~~ >> 사실상 이동은 아니고 include 한 것임~");
	// => Forward Test 시에는
	// => 스크립스는 브라우져에서 실행되기 때문에 실행 되지않음
	// => 서버에서 forward 된 화면이 response 로 출력 되기때문 
	// => 즉 13~19행이 실행되기 전에 20행이 실행되어 alert이 뜨지 않는다.
</script>
<%-- <jsp:forward page="ex01_HelloJsp.jsp" /> --%>

<h3>2. Include</h3>
<hr>
-> 2.1) JSP Action Tag <br>
본래는 서블릿이 컴파일 되고 컴파일되어 화면이 만들어지는 것이나 액션 태그의 경우
Jsp 문서의 완성된 웹페이지가 포함됨, 변수공유 불가능 (코드호환이 안됨)<br>
 <jsp:include page="ex01_HelloJsp.jsp" /> 
 => 컴파일 오류
 <%-- => 변수 공유 확인(ex01_HelloJsp.jsp 선언한 변수) : name=<%=name%> --%> 

-> 2.2) Directive include Test <br>
Jsp 문서의 소스코드가 포함됨, 변수공유 가능 (코드호환이 됨)<br>
   
<%@ include file="ex01_HelloJsp.jsp" %>
 => 변수 공유 확인(ex01_HelloJsp.jsp 선언한 변수) : name=<%=name%> 
<hr>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL forEach begin, end, step Test **</title>
</head>
<body>
<h2>** JSTL forEach begin, end, step Test **</h2>
<pre>
=> 구간반복: StartIndex(begin), LastIndex(end), 증감값(step) 적용하기
=> step 의 default 값은 1
=> 실습 1)
   1 ~ 10 까지를 다음처럼 출력하세요 ~~
   -> 1, 2, 3, .....10         
   -> java의 예 : for (int i=1; i<11; i++) { ......  }   
=> 결과
</pre>
<b>
<c:forEach var="i" begin="1" end="10" step="1" varStatus="vs">
	${i}
	${vs.last ? "" : ", "}
</c:forEach>

=> step=2(조건: 2부터 2씩 증)
<table border="1" style="text-align: center; width: 90%;">
			<tr>
				<th>step 2씩 증가할 때 짝수</th>
				<th>index</th>
				<th>count</th>
			</tr>

			<c:forEach var="i" begin="2" end="10" step="2" varStatus="vs">
					<tr>
						<th>${i}</th>
						<th>${vs.index}</th>
						<th>${vs.count}</th>
					</tr>
			</c:forEach>
		</table> 

=> step=1(짝수인 값을 찾아야하니까 i의 값을 확인해야한다.)
<table border="1" style="text-align: center; width: 90%;">
			<tr>
				<th>step 1씩 증가할 때 짝수</th>
				<th>index</th>
				<th>count</th>
			</tr>

			<c:forEach var="i" begin="1" end="10" step="1" varStatus="vs">
					<tr>
						<c:if test="${i%2==0}">
							<th>${i}</th>
							<th>${vs.index}</th>
							<th>${vs.count}</th>
						</c:if>
					</tr>
			</c:forEach>
		</table> 
</b>

</body>
</html>
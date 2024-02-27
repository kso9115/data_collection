<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring boot Axios MemberList **</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle4.css">

</head>
<body>

<h2>** Spring boot Axios MemberList **</h2>
<hr>
<c:if test="${not empty requestScope.message}">
=> ${requestScope.message}<br><hr>
	</c:if>
	
<table border="1" style="width:100%">

		<tr bgcolor="DarkSlateBlue" style="color:white">
			<th>ID</th>
			<!-- <th>Password</th> -->
			<th>Name</th>
			<th>Age</th>
			<th>Jno</th>
			<th>Info</th>
			<th>Point</th>
			<th>Birthday</th>
			<th>추천인</th>
			<th>프로필사진</th>
			<th>삭제하기</th>
		</tr>
<c:if test="${!empty requestScope.mlist}">
	<c:forEach var="m" items="${requestScope.mlist}">
		<tr bgcolor="Lavender">
			<!-- ** idList : id 별 boardList 확인 
				=> 선택된 id를 function에 전달(el 태그로 매개변수를 전달하여 활용)
					idbList('mlist')
				=> 이벤트 적용
					-> a Tag에 이벤트 적용 시 책갈피 기능 활용 가능
					-> href를 적용하지 않았을 때 : 이동하지 않음
					-> href = # 일 때 : 최상위로 이동
					-> href = "#id" 일 때 : id의 위치로 이동
					-> href = "javascript:;" 일 때 : javascript를 아무것도 입력하지 않았기 때문에 이동하지 않음 
			-->
			
			<td>
			<%-- <span class="textlink" onclick="idbList('${m.id}')">${m.id}</span> --%>
			<a href="#" onclick="idbList('${m.id}')">${m.id}</a>
			</td>
			<%-- <td>${m.password}</td> --%>
			<td>${m.name}</td>
			<td>${m.age}</td>
			<td align="center">
				<span class="textlink" onmouseover="showJoDetail(event,${m.jno})"
								onmouseout="hideJoDetail()">${m.jno}
				</span>
			</td>
			<td>${m.info}</td>
			<td>${m.point}</td>
			<td>${m.birthday}</td>
			<td>${m.rid}</td>
			<td><img alt="myImage" width="70" height="70"
					src="/resources/uploadImages/${m.uploadfile}"></td>
			<!-- ** axiDelete : 멤버 삭제 기능 추가
				=> 선택된 id를 function에 전달(el 태그로 매개변수를 전달하여 활용)
				=> 결과는 성공 혹은 실패 여부만 전달하면 된다 : RESTController 사용
				=> 성공했을 시 Deleted 로 변경, onclick 이벤트 해제(다시 선택할 수 없게끔)
				
				 ** function 에 이벤트객체 전달
            	=> 이벤트핸들러의 첫번째 매개변수에 event 라는 이름으로 전달함.
             	=> a Tag 와 span 사용시 e.target 값 비교
                	-> a Tag : "javascript:;" 
                	-> span  : [object HTMLSpanElement]         
			 -->
			<td><span class="textlink" onclick="axiDelete(event,'${m.id}')" id="${m.id}">Delete</span></td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.mlist}">
	<tr>
	<td colspan="10">출력할 데이터가 없슴당</td>
	</tr>
</c:if>

</table>

<div id="content"></div>
<hr>

&nbsp;<a href="/home">HOME</a>&nbsp;
&nbsp;<a href="">이전으로</a>&nbsp;
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Login Form **</title>
</head>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle4.css">
<!-- <link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css"> -->
<script src="/resources/myLib/inCheck.js"></script>
<script>
	"use strict"
	let iCheck = false;
	let pCheck = false;
	onload=function(){
	
		
		document.getElementById('id').focus(); /* 내장 함수 */
		document.getElementById('id').addEventListener('keydown',
				// 발생된 이벤트 인자로 전달받음 : e
				(e) => {
					if(e.which==13){
						e.preventDefault(); 
						// 기존에 가지고 있는 이벤트의 값
						// => form 에서는
	               		// => enter 누르면 자동 submit 발생되므로 이를 제거함
						document.getElementById('password').focus();
					}//if
				});
		// -> 무결성 점검
		// ** document.getElementById('id').addEventListener('focusout',function(){});
		document.getElementById('id').addEventListener('focusout', () => { iCheck=idCheck(); });
		
		// => Password
		document.getElementById('password').addEventListener('keydown',
	    		 (e)=>{
	        		if (e.which==13) {
	           			e.preventDefault();
	           			document.getElementById('loginTag').focus();
	                    // => password 에서 입력후 Enter_Key 누르면 바로 submit 진행 되도록~~
	                    //     type="submit" 을 사용하는경우 정확하게 적용하기 어려워 적용하지 않음    
	                    //if (!iCheck) iCheck=idCheck();
	                    //else if (!pCheck) pCheck=pwCheck();
	                    //else document.getElementById('myForm').submit();
	        		}//if
	     		});
		// -> 무결성 점검 
		document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });
		
	}//onload
	
function inCheck() {
		
		if(!iCheck) {document.getElementById('iMessage').innerHTML=' 필수 입력, ID를 확인하세요 ';}
		if(!pCheck) {document.getElementById('pMessage').innerHTML=' 필수 입력, password를 확인하세요 ';}
		
		if (iCheck && pCheck ) {
				return true;
			} else {
				return false;
			}
	}// inCheck 메서드
	
</script>

<body>
<h2>** Web MVC2 Login Page **</h2>
<hr>
<form action="login" method="post">
	<table>
		<tr height="40">
			<td><label for="id">ID</label></td>
			<td><input type="text" id="id" name="id" size="18">
			<span id="iMessage" class="eMessage"></span></td>
		</tr>
		<tr height="40">
			<td><label for="password">PW</label></td>
			<td><input type="password" id="password" name="password" size="18">
			<span id="pMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr>
		<td></td>
		<td>
		<input type="submit" id="loginTag" value="로그인" onclick="return inCheck()" size="5">&nbsp;&nbsp;
		<input type="reset" value="취소" size="5">
		</td>
		</tr>
		
	</table>
</form>
<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
</c:if>

<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;

</body>
</html>
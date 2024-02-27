<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Password Update **</title>
</head>

<style>
	h2{
	text-align: center;
	}
	
	input {
  	text-align: center;
  	background-color: #eee;
  	border: none;
  	padding: 12px 15px;
  	margin: 8px 0;
  	width: 180px;
	}

</style>
<!-- <link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle3.css"> -->
<script src="/resources/myLib/inCheck.js"></script>
<script>
	"use strict"
	let pCheck = false;
	let p2Check = false;
onload=function(){
	
		// password
		document.getElementById('password').focus(); /* 내장 함수 */
		document.getElementById('password').addEventListener('keydown',
				// 발생된 이벤트 인자로 전달받음 : e
				(e) => {
					if(e.which==13){
						e.preventDefault(); 
						// 기존에 가지고 있는 이벤트의 값
						// => form 에서는
	               		// => enter 누르면 자동 submit 발생되므로 이를 제거함
	               		// => password 에서 입력후 Enter_Key 누르면 바로 submit 진행 되도록~~
	                    //     type="submit" 을 사용하는경우 정확하게 적용하기 어려워 적용하지 않음    
	                    //if (!iCheck) iCheck=idCheck();
	                    //else if (!pCheck) pCheck=pwCheck();
	                    //else document.getElementById('myForm').submit();
						document.getElementById('password2').focus();
					}//if
				});
		// -> 무결성 점검
		// ** document.getElementById('id').addEventListener('focusout',function(){});
		document.getElementById('password').addEventListener('focusout', () => { pCheck=pwCheck(); });
		
		// => Password 재확인
		document.getElementById('password2').addEventListener('keydown',
	    		 (e)=>{
	        		if (e.which==13) {
	           			e.preventDefault();
	           			document.getElementById('updateTag').focus();
	                    
	        		}//if
	     		});
		// -> 무결성 점검 
		document.getElementById('password2').addEventListener('focusout', ()=>{ p2Check=pw2Check(); });
		
	}//onload
	
function inCheck() {
		
		if(!pCheck) {document.getElementById('pMessage').innerHTML=' 필수 입력, password를 확인하세요 ';}
		if(!p2Check) {document.getElementById('p2Message').innerHTML=' 필수 입력, password를 재확인하세요 ';}
		
		if (pCheck && p2Check ) {
			if(confirm("정말 password를 수정하시겠습니다? YES:확인 NO:취소"))
				return true;
			} else {
				alert("비밀번호 수정 실패!!!!!!!!!! ദ്ദി   -᷄ ᴗ -᷅   ) ")
				return false;
			}
}// inCheck 메서드
	
</script>
<body>

<h2>** Spring MVC2 Password Update **</h2>
<div align="center">

<br><b>새로운 Password를 입력해주세요</b><br>
<form action="pwUpdate" method="post">
	<table>
		<tr height="40">
			<td><label>Password</label></td>
			<td><input type="password" id="password" name="password">
				<br><span id="pMessage" class="eMessage"></span>
			</td>
		</tr>

		<tr height="40">
			<td><label>Password 재확인</label></td>
			<td><input type="password" id="password2" placeholder="반드시 입력하세요">
				<br><span id="p2Message" class="eMessage"></span>
			</td>
		</tr>
		
		<tr height="40">
			<td><label></label></td>
			<td><input type="submit" value="수정" id="updateTag" onclick="return inCheck()">&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
		
	</table>
</form>

</div>

<div align="center">
	<c:if test="${!empty requestScope.message}">
	${requestScope.message}<br>
	</c:if>
</div>

</body>
</html>
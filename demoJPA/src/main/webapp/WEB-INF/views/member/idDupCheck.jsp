<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>** ID 중복 확인 **</title>
	<script src="/resources/myLib/inCheck.js"></script>
	<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle.css">
	<script>
	//** idOK : 사용자가 입력한 id를 사용가능하도록 해주고, 현재(this)창은 close
	//1) this 창의 id 를 부모창의 id 로
	//2) 부모창의 ID중복확인 버튼은 disabled & submit 은 enable
	//3) 부모창의 id 는 수정불가 (readonly) , password 에 focus
	//4) 현재(this)창은 close
	
function idOK(){
		console.log("머야");
		// 1) 현재창에 있는 id를 부모창으로 가져오면 됨 : 아이디가 사용가능하니까
		// opener.document.getElementById('id').value = document.getElementById('id').value;
		// 톰캣 서버는 자스 스크립트 코드에 있는 el 태그를 읽음
		opener.document.getElementById('id').value = "${param.id}";
		// EL 태그 활용 : Jsp 문서에서는 script 구문의 문자열 내부에 있는 EL은 처리해준다.
		// 그러나 백틱의 경우는 제이쿼리의 문법으로 인식하는 점을 주의하자
		
		// 2) 부모창의 중복확인 버튼 선택 불가능하도록 만들것, submitTag는 기존에 막아놨으니까 풀어주기
		opener.document.getElementById('idDup').disabled='disabled';
		opener.document.getElementById('submitTag').disabled='';
		
		// 3) password로 focus를 변경, id는 읽기 전용으로 변경
		opener.document.getElementById('id').readOnly='readOnly';
		opener.document.getElementById('password').focus();
		
		// 4) 현재 창 종료
		close();
		// window.close(); self.close(); 모두 동일하게 사용가능하다.
	}
	
	</script>
<style>
   body {
      background-color: LightYellow;
      font-family: 맑은고딕;
   }
   #wrap {
      margin-left: 0;
      text-align: center;
   }
   h3 { color: navy; }   
</style>
</head>
<body>

<div id="wrap">
   <h3>** ID 중복확인 *</h3>
   <h4>Parameter id값 확인</h4>
   => Parameter_ID : ${param.id}<br>
   <hr>
   <form action="idDupCheck" method="get">
      User_ID : 
      <input type="text" name="id" id="id" value="${param.id}">   
      <input type="submit" value="ID중복확인" onclick="return idCheck()"><br>
      <!-- inCheck.js 의  idCheck() 의 결과에 따라 submit 결정 -->
      <span id="iMessage" class="eMessage"></span>
   </form>
   <br><br>
   <!-- 서버의 처리결과 : idUse 의 값 'T'/'F' 에 따른 화면 -->
   <div id="msgBlock">
   <c:if test="${idUse=='T'}">
   	${param.id}는 사용 가능합니다. &nbsp;&nbsp;
   	<button onclick="idOK()">ID 사용하기</button>
   </c:if>
   <c:if test="${idUse=='F'}">
   	${param.id}는 이미 사용중인 아이디 입니다.
   	<br>다시입력하세요<br>
   	<!-- 부모창(joinForm, opener)에 남아있는 사용자가 입력한 id는 지워주고,  
          현재(this)창 에서는 id 에 focus 를 주고 재입력 유도 -> script 필요
    -->
    <script>
    	document.getElementById('id').focus();			// 자손(팝업창)에 있는 id
    	opener.document.getElementById('id').value='';	// 부모창에 있는 id
    </script>
   </c:if>
   </div>
</div>

</body>
</html>
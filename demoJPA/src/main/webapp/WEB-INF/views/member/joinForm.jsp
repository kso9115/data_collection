<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle3.css">
	

<head>
<meta charset="UTF-8">
<title>** Join Form **</title>


<!-- 외부 스크립트 가져올 때 : src 속성 사용 -->
<script src="/resources/myLib/inCheck.js"></script>
<script>

//** ID 중복확인
//=> UI 개선사항
//=> 중복확인 버튼 추가
// 처음 : 중복확인버튼_enable / submit_disable
//=> 중복확인 완료후 submit 이 가능하도록
// 중복확인버튼_disable / submit_enable
//=> 중복확인 기능 : function idDupCheck()
// id입력값의 무결성점검 -> id 확인요청 -> 서버로 전송 -> id , selectOne 결과 -> response: 사용가능/불가능 
//=> 서버측 : 컨트롤러에 idDupCheck 요청을 처리하는 매핑메서드, view_Page(팝업창) 작성  
function idDupCheck(){

	// 1) id 입력값의 무결성 점검
	if(!iCheck) { iCheck=idCheck(); 
	} else {
	// 2) 서비로 id 확인 요청 -> 결과는 view_Page(팝업창)으로 띄워줄 것
	let url = "idDupCheck?id="+document.getElementById('id').value;
	window.open(url,'_blank','width=400,height=300,resizable=yes,scrollbars=yes,toolbar=no,menubar=yes')
	}
}

//** 화살표 함수
//=> 익명함수를 간단하게 표기
// function(){....}  
// () => {....}  

//** 입력값의 무결성 점검
//=> ID 중복확인, 무결성 점검

//1) 모든항목  focusout 이벤트핸들러
// => 개별항목 점검확인하는 boolean Type 변수 (스위치변수) 
// => 개별항목 점검 function() 작성
//2) submit 진행전에 점검확인
// => 모든항목의 점검이 완료된 경우에만  submit 진행
// => function inCheck() 로 확인
// => submit 버튼의 onclick 리스너에 등록
//    ( submit 의 default 이벤트 고려 )
//----------------------------------------------------
//** 실습


/* 엄격모드 실행 */
"use strict"


	
// ** 입력값의 무결성을 확인해야한다.
// ** => ID 중복확인, 입력값 확인(규칙에 맞게 작성되어있는지)

// ** => 입력값의 무결성 확인
// 1) 전역변수 정의
//	=> 무결성 확인 결과를 위한 switch변수 생성(true/false 판별) 
//	=> id,password,name,age,info,point,birthday
//	=> password의 경우 두 번 확인(재확인)을 진행하므로 form에 추가
	let iCheck = false;
	let pCheck = false;
	let p2Check = false;
	let nCheck = false; // 이름 : 문자열 확인
	let aCheck = false; // 나이 : 정수확인
	let oCheck = false; // 포인트 : 실수확인
	let bCheck = false;

	// 2) 개별적인 확인 코드
	// => 이벤트 : focusout, keydown_EnterKey 적용
	// => 오류가 없다면 : switch 변수값을 true, 메시지 삭제
	// => 오류가 있다면 : switch 변수값을 false, 메시지 출력
	// => 순서 : Tag인식 -> Tag의 value를 가져오기 -> 무결성 확인
	
	// => ID
	// -> keydown_EnterKey 에 포커스이동 적용
   	// -> 제어문자의 ascii 코드 값(참고)
   	//     esc=27, EnterKey=13, Space_Bar=32
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
           			document.getElementById('password2').focus();
        		}//if
     		});
	// -> 무결성 점검 
	document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });

	// => Password2
	document.getElementById('password2').addEventListener('keydown',
    		 (e)=>{
        		if (e.which==13) {
           			e.preventDefault();
           			document.getElementById('name').focus();
        		}//if
     		});
	// -> 무결성 점검 
	document.getElementById('password2').addEventListener('focusout', ()=>{ p2Check=pw2Check(); });

	// => Name
	document.getElementById('name').addEventListener('keydown',
    		 (e)=>{
        		if (e.which==13) {
           			e.preventDefault();
           			document.getElementById('age').focus();
        		}//if
     		});
	// -> 무결성 점검 
	document.getElementById('name').addEventListener('focusout', ()=>{ nCheck=nmCheck(); });

	// => Age
	document.getElementById('age').addEventListener('keydown',
    		 (e)=>{
        		if (e.which==13) {
           			e.preventDefault();
           			document.getElementById('jno').focus();
        		}//if
     		});
	// -> 무결성 점검 
	document.getElementById('age').addEventListener('focusout', ()=>{ aCheck=agCheck(); });

	// => Jno : Focus 이동만
	document.getElementById('jno').addEventListener('keydown',
    		 (e)=>{
        		if (e.which==13) {
           			e.preventDefault();
           			document.getElementById('info').focus();
        		}//if
     		});

	// => Info : Focus 이동만
	document.getElementById('info').addEventListener('keydown',
  			(e)=>{
     			if (e.which==13) {
        			e.preventDefault();
        			document.getElementById('point').focus();
 			    }//if
  			});

	// => Point
	document.getElementById('point').addEventListener('keydown',
     		(e)=>{
        		if (e.which==13) {
           			e.preventDefault();
           			document.getElementById('birthday').focus();
        		}//if
     		});
	// -> 무결성 점검 
	document.getElementById('point').addEventListener('focusout', ()=>{ oCheck=poCheck(); });
	
	// => birthday
	document.getElementById('birthday').addEventListener('keydown',
     		(e)=>{
        		if (e.which==13) {
           			e.preventDefault();
           			document.getElementById('rid').focus();
        		}//if
     		});
	// -> 무결성 점검 
	document.getElementById('birthday').addEventListener('focusout', ()=>{ bCheck=bdCheck(); });
	
	// => Rid : Focus 이동하여 submitTag로 
	document.getElementById('rid').addEventListener('keydown',
  			(e)=>{
     			if (e.which==13) {
        			e.preventDefault();
        			document.getElementById('submitTag').focus();
 			    }//if
  			});

}//onload

	// 3) submit 실행 여부 판단 & 실행
	// => 모든항목의 무결성을 확인
	// => 오류가 없으면 : return true
	// => 오류가 1항목이라도 있으면 : return false

	// function 사용 시점 : submit 눌렀을 때
	function inCheck() {
		
		if(!iCheck) {document.getElementById('iMessage').innerHTML=' 필수 입력, ID를 확인하세요 ';}
		if(!pCheck) {document.getElementById('pMessage').innerHTML=' 필수 입력, password를 확인하세요 ';}
		if(!p2Check) {document.getElementById('p2Message').innerHTML=' 필수 입력, password를 재확인하세요 ';}
		if(!nCheck) {document.getElementById('nMessage').innerHTML=' 필수 입력, 이름을 확인하세요 ';}
		if(!aCheck) {document.getElementById('aMessage').innerHTML=' 필수 입력, 나이를 확인하세요 ';}
		if(!oCheck) {document.getElementById('oMessage').innerHTML=' 필수 입력, 포인트를 확인하세요 ';}
		if(!bCheck) {document.getElementById('bMessage').innerHTML=' 필수 입력, 생일을 확인하세요 ';}
		
		if (iCheck && pCheck && p2Check && nCheck && aCheck && oCheck && bCheck) {
			// 유효성 검사 완료 후 submit 확인		
			if (confirm("정말 가입하시겠습니다??? (YES : 확인 / NO : 취소)")) {
				// 유효성 검사 완료 후 submit 진행
				return true;
			} else {
				// 유효성 검사 실패
				alert(" 가입이 취소되었습니다 하하하하하!!! ");
				return false;
					} 
			} else{
			return false;
			} // Check 조건
	}// inCheck 메서드
	
	
</script>

</head>

<body>

	<h2>** Spring02 Web MVC2 Join Page **</h2>
	<h3>회원가입을 환영합니다</h3>
	<hr>

<!--  ** FileUpLoad Form **
=> form 과 table Tag 사용시 주의사항 : form 내부에 table 사용해야함
   -> form 단위작업시 인식안됨
   -> JQ 의 serialize, FormData 의 append all 등 

=> method="Post" : 255 byte 이상 대용량 전송 가능 하므로

=> <form enctype="속성값">
   <form> 태그의 데이터 (input 의 value)가 서버로 제출될때 해당 데이터가 인코딩되는 방법을 명시함.  
 
=> enctype="multipart/form-data" : 화일 upload 를 가능하게 해줌 
   ** multipart/form-data는 파일업로드가 있는 입력양식요소에 사용되는 enctype 속성의 값중 하나이고, 
       multipart는 폼데이터가 여러 부분으로 나뉘어 서버로 전송되는 것을 의미
       이 폼이 제출될 때 이 형식을 서버에 알려주며, 
       multipart/form-data로 지정이 되어 있어야 서버에서 정상적으로 데이터를 처리할 수 있다.     
-->


	<form action="join" method="post" id="myform" enctype="multipart/form-data">
		<table>
			<tr height="40">
				<td><label for="id">ID</label></td>
				<td><input type="text" id="id" name="id" size="18"
					placeholder="영문과 숫자 4~10글자">
					<button type="button" id="idDup" onclick="idDupCheck()">ID중복확인</button>
					<br> <span id="iMessage"
					class="eMessage"></span></td>

				<!-- <td><input type="submit" value="중복검사" id="id" name="id"></td> -->
			</tr>
			<tr height="40">
				<td><label for="password">Password</label></td>
				<td><input type="password" id="password" name="password"
					size="18"> <br> <span id="pMessage" class="eMessage"></span></td>
			</tr>
			<tr height="40">
				<td><label for="password2">PW 재확인</label></td>
				<td><input type="password" id="password2" size="18"> <br>
					<span id="p2Message" class="eMessage"></span></td>
			</tr>
			<tr height="40">
				<td><label for="name">Name</label></td>
				<td><input type="text" id="name" name="name" size="18">
					<br> <span id="nMessage" class="eMessage"></span></td>
			</tr>
			<tr height="40">
				<td><label for="age">Age</label></td>
				<td><input type="text" id="age" name="age" size="18"> <br>
					<span id="aMessage" class="eMessage"></span></td>
			</tr>
			<tr height="40">
				<td><label for="jno">Jno</label></td>
				<td><select name="jno" id="jno">
						<option value="1">1조 : Buisness</option>
						<option value="2">2조 : static</option>
						<option value="3">3조 : 칭찬해조</option>
						<option value="4">4조 : 카톡으로얘기하조</option>
						<option value="7">7조 : 칠면조</option>
				</select> <!-- select의 경우 선택해서 진행하는 부분임으로 유효성 검사 ㄴㄴ --></td>
			</tr>
			<tr height="40">
				<td><label for="info">Info</label></td>
				<td><input type="text" id="info" name="info" size="18"
					placeholder="자기소개 & 희망사항"></td>
			</tr>
			<tr height="40">
				<td><label for="point">Point</label></td>
				<td><input type="text" id="point" name="point" size="18"
					placeholder="실수 입력 가능"> <br> <span id="oMessage"
					class="eMessage"></span> <!-- 실수이므로 처리하는 과정 확인을 위해 진행 --></td>
			</tr>
			<tr height="40">
				<td><label for="birthday">Birthday</label></td>
				<td><input type="date" id="birthday" name="birthday" size="18">
					<br> <span id="bMessage" class="eMessage"></span></td>
			</tr>
			<tr height="40">
				<td><label for="rid">추천인</label></td>
				<td><input type="text" id="rid" name="rid" size="18"></td>
			</tr>
			<!-- File Upload 기능 추가 -->
			<tr height="40">
				<td><label for="uploadfilef">Image</label></td>
				<td><img alt="myImage" width="250" height="300" class="select_img"><br>
				<input type="file" id="uploadfilef" name="uploadfilef" size="18"></td>
				
			<script>
        		document.getElementById('uploadfilef').onchange=function(e){
         			if(this.files && this.files[0]) {
            			let reader = new FileReader;
            			reader.readAsDataURL(this.files[0]);
             				reader.onload = function(e) {
                			// => jQuery를 사용하지 않는경우 
               				document.getElementsByClassName('select_img')[0].src=e.target.result;
                
               				//$(".select_img").attr("src", e.target.result)
		               		//            .width(70).height(90); 
               				} // onload_function
          			} // if   
        		}; //change  
			</script>

			</tr>
			<tr id="submitTr">

				<td></td>
				<td><!-- <input type="submit" value="회원가입" size="5" id="submitTag"
					onclick="return inCheck()" disabled> --> 
					
					<!-- => Tag 의 onclick 이벤트를 작성하고, onclick 이벤트핸들러가 가지고있던
                 			기본동작인 submit 을 선택적으로 진행되도록 해준다. 
                			 - submit 진행 : default (또는 return true)
               				 - submit 정지 : submit 이벤트를 무효화 해야함 (return false 또는 이벤트.preventDefault())  -->

					<!-- <input type="reset" value="취소" size="5"> --></td>
					 <!-- Button Test
            			=> default : form 내부에서는  submit 와  동일하게 작동됨 
                       				 inCheck() 의 return 값에 따라 (true 면) submit 진행됨 
            			=> 단, type 속성을 선택하면 (button, reset, submit 등) 속성에 맞게 실행됨
               			예) button 을 선택하면 submit 은 실행되지않음   -->
					<!-- <button type="button" onclick="inCheck()">ButtonTest
						type:button</button> <span>input type:submit 과 동일하게 작동 / 리턴 사용하지 않아도
						됨</span><br>
					<button type="submit" onclick="return inCheck()">ButtonTest2
						type:submit</button> <span>input type:submit 과 동일하게 작동 / 리턴 사용해줘야함</span><br>
					<button type="submit" onclick="inCheck()">ButtonTest3
						type:submit</button> <span>input type:submit 과 동일하게 작동 / 리턴 사용하지 않아도 됨</span> -->
			</tr>

		</table>
		<div>
		<input type="submit" value="회원가입" size="5" id="submitTag"
					onclick="return inCheck()" disabled>
		<input type="reset" value="취소" size="5">
		<!-- axios -->
		<span class="textlink" onclick="axiJoin()">axiJoin</span>
		</div>
	</form>
	&nbsp;
	<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
	</c:if>

	<hr>
	&nbsp;
	<a href="/home">Home</a>&nbsp; &nbsp;
	<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;


</body>
</html>
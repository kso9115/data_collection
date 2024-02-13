<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update Form **</title>
</head>

<link rel="stylesheet" type="text/css"
	href="/spring02/resources/myLib/myStyle3.css">
<style>
  a {
  	  text-decoration: none;
  }
  
  a:link, a:visited {
     background-color: #FFA500;
     color: maroon;
     padding: 15px 25px;
     text-align: center;
     text-decoration: none;
     display: inline-block;
     
}
 a:hover, a:active {
     background-color: #FF4500;
}
</style>

<body>

<h2>** Web MVC2 Update Page **</h2>
<hr>

<form action="update" method="post" enctype="multipart/form-data">
	<table>
		<tr height="40">
			<td><label for="id">ID</label></td>
			<td><input type="text" id="id" name="id" size="18" value="${requestScope.mdetail.id}" readonly placeholder="영문과 숫자 4~10글자"></td>
		</tr>
		<%-- <tr height="40">
			<td><label for="password">Password</label></td>
			<td><input type="password" id="password" name="password" value="${requestScope.mdetail.password}" size="18"></td>
		</tr> --%>
		<!-- passwordEncoder 적용 후 분리 -->
		<tr height="40">
			<td><label for="password">Password</label></td>
			<td>
				<a href="pwUpdate">비밀번호 변경</a>
			</td>
		</tr>
		
		<tr height="40">
			<td><label for="name">Name</label></td>
			<td><input type="text" id="name" name="name" value="${requestScope.mdetail.name}" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="age">Age</label></td>
			<td><input type="text" id="age" name="age" value="${requestScope.mdetail.age}" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="jno">Jno</label></td>
			<td><select name="jno" id="jno" >
				<option value="1" ${mdetail.jno==1 ? "selected":""}>1조 : Buisness</option>
				<option value="2" ${mdetail.jno==2 ? "selected":""}>2조 : static</option>
				<option value="3" ${mdetail.jno==3 ? "selected":""}>3조 : 칭찬해조</option>
				<option value="4" ${mdetail.jno==4 ? "selected":""}>4조 : 카톡으로얘기하조</option>
				<option value="7" ${mdetail.jno==7 ? "selected":""}>7조 : 칠면조</option>
			</select></td>
		</tr>
		<tr height="40">
			<td><label for="info">Info</label></td>
			<td><input type="text" id="info" name="info" value="${requestScope.mdetail.info}" size="18" placeholder="자기소개 & 희망사항"></td>
		</tr>
		<tr height="40">
			<td ><label for="point">Point</label></td>
			<td><input type="text" id="point" name="point" value="${requestScope.mdetail.point}" size="18" placeholder="실수 입력 가능"></td>
		</tr>
		<tr height="40">
			<td><label for="birthday">Birthday</label></td>
			<td><input type="date" id="birthday" name="birthday" value="${requestScope.mdetail.birthday}" size="18"></td>
		</tr>
		<tr height="40">
			<td><label for="rid">추천인</label></td>
			<td><input type="text" id="rid" name="rid" value="${requestScope.mdetail.rid}" size="18"></td>
		</tr>
		
		<!-- Image Update 추가 
         => form Tag : method, enctype 확인
         => new Image 를 선택하는 경우 -> uploadfilef 사용
         => new Image 를 선택하지않는 경우 
            -> 본래 Image 를 사용 -> uploadfile 값이 필요함
  		 -->   
		<tr height="40">
			<td><label for="uploadfilef">Image</label></td>
			<td><img alt="myImage" width="250" height="300" class="select_img"
				src="/spring02/resources/uploadImages/${requestScope.mdetail.uploadfile}">
				<input type="hidden" id="uploadfile" name="uploadfile" value="${requestScope.mdetail.uploadfile}">
				<br>`
				<input type="file" name="uploadfilef" id="uploadfilef" size="20">
			</td>
			
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
		
	</table>
	
	<div align="center">
		<input type="submit" value="수정완료" size="5">&nbsp;&nbsp;
		<input type="reset"  value="취소" size="5">
	</div>
</form>
<div align="center">
	<c:if test="${!empty requestScope.message}">
	${requestScope.message}<br>
	</c:if>
</div>

<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;


</body>
</html>
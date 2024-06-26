/*	axTest02.js : axiJoin */


/* ** Ajax_REST API Join Test **
 => axios
 => file_UpLoad 가 포함된 formData 처리
 => joinForm 요청: MemberController -> 만들어진 웹 Page return
 => join 요청: RTestController -> 결과 Text return  */

/* => Axios 메서드형식 적용 (00_AJAX(공유).pptx 16p)
   - GET   : axios.get( url[, config] )
   - POST  : axios.post( url, data[, config] )
   - PUT   : axios.put( url, data[, config] )
   - PATCH : axios.patch( url[, data[, config]] )
   - DELETE: axios.delete( url[, config] )     */

"use strict"

// 1) joinForm 요청
// => response : 웹 Page를 리턴
function rsJoinf(){
   let url="/member/joinForm";
   console.log(url);
   axios.get(url
      ).then(response => {
         console.log('** response : joinForm 요청 담기 성공');
         document.getElementById('resultArea1').innerHTML=response.data;
      }).catch(err => {
         alert(`** response : joinForm 실패 => ${err.message}`);
      })
}//rsJoinf

// ** form Tag의 input Data 처리방법
// => 직접 입력 : multipart 타입은 처리할 수 없음
//      data: { id:document.getElementById('id').value,
//              password:document.getElementById('password').value
//           } 
// => Form 의 serialize() : jQuery 만 적용됨
//       -> input Tag 의 name 과 id 가 같아야 함.   
//       -> multipart 타입은 전송안됨. 
//         처리하지 못하는 값(예-> file Type) 은 스스로 제외시킴 
// => 객체화 : multipart 타입은 처리할 수 없음
//       let myData = {
//            id:document.getElementById('id').value,
//            password:document.getElementById('password').value
//         }

// => FormData 객체 활용 : JS의 내장객체
//      -> append 메서드 : multipart 타입 처리 불편
//      -> 생성자 매개변수 이용 : multipart 타입 포함 간편한 처리가능 

// 2) Join 처리
// => multipart 타입 포함된 경우 대한 처리를 해줘야한다. : JS 의 내장객체 FormData 에 담아서 전송
// => Data 전송 : JS의 FormData 사용, 요청_headers "Content-Type" 변경
// => JS의 내장객체 FormData 사용
//      -> 요청_headers "Content-Type" 변경

function axiJoin(){
   // 2-1) Data 전송 준비
   let formData = new FormData(document.getElementById('myform'));
   
   // 2-2) Axios 요청처리
   // 이미지가 존재하기 떄문에 Content-Type을 알려줘야함
   let url = "/rest/rsjoin";
   axios.post(url, formData, 
         {headers:{'Content-Type':''}
   }).then(response => {
      alert(`** Join 성공 => ${response.data}`)
      // location.reload(); // 화면 새로고침
      console.log(rsLoginf());
      rsLoginf();
   }).catch(err => {
      if(err.response.status=='502') alert("** 회원가입 입력 오류 : 다시 진행해주세요")
      else alert(`** 시스템 오류 : 잠시 후 다시 진행하세요 => ${err.message}`)
   })
   document.getElementById('resultArea2').innerHTML='';
}
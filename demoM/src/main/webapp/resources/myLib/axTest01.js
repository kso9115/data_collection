/* axTest01.js : rsLogin , rsLoginjj */

/* ** Ajax_REST API Login Test **
 1. fetch
 => then 1단계  
         * response 속성값 
         response.status – HTTP 상태코드(예: 200)
         response.ok - Boolean, HTTP 상태 코드가 200-299 사이이면 True
         response.headers – HTTP 헤더를 담고있는 맵과 유사한 객체
          * Body-reading 메서드
         response.text() – 응답을 읽고 텍스트로 반환
         response.json() – 응답을 JSON으로 구문 분석
         response.formData() – 응답을 FormData객체로 반환
         response.blob() – 응답을 Blob (유형이 있는 이진 데이터) 으로 반환
         response.arrayBuffer() – 응답을 ArrayBuffer (바이너리 데이터의 저수준 표현) 로 반환
         response.body - ReadableStream 객체이므로 본문을 청크별로 읽을 수 있다.

=> catch: then 1단계에서 발생시킨 Error객체 의 매개변수값 을 인자의 message 속성으로 전달받아 처리함
		 
 => Test1) Post요청: rsLogin(), rsLoginJJ: JSON -> Text, JSON
 => Test2) Get요청: rsDetail() -> selectOneJno 
 => Test3) Delete요청: rsDelete

*/ 

"use strict"
// Test1) rsLogin
// 1.1) form 출력 하기
function rsLoginf(){
	let resultHtml = 
	`<table align center>
		<caption><h3> ** Ajax Login Form **</h3></caption>
                <tr height=40>
                        <td bgcolor="aqua"><label for="id">ID</label></td>
                        <td><input type="text" name="id" id="id"></td>
                </tr>
                <tr height=40>
                        <td bgcolor="aqua"><label for="password">ID</label></td>
                        <td><input type="password" name="password" id="password"></td>
                </tr>
                <tr height=40>
                        <td></td>
                        <td colspan="2">
                        <span class="textlink" onclick="rsLogin()">rsLogin</span>&nbsp;&nbsp;
                        <span class="textlink" onclick="rsLoginjj()">rsLoginJJ</span>&nbsp;&nbsp;
                        <span class="textlink" onclick="axiLoginjj()">axiLoginJJ</span>&nbsp;&nbsp;
                        <input type="reset" value="취소">
                        </td>
                </tr>
        </table>
	`;
	document.getElementById('resultArea1').innerHTML=resultHtml;
}

// 1.2) Login 기능 Service 요청처리
// => Ajax 요청, fetch 적용
// => @RestController, 계층적 uri 적용, Post 요청
// => request: JSON, response: Text 

function rsLogin() {
        console.log("rslogin")
        let url="/rest/rslogin";

        fetch(url, {method:'Post',
                        body:JSON.stringify({
                                id:document.getElementById('id').value,
                                password:document.getElementById('password').value
                        }),//body
                        headers:{'Content-Type':'application/json'}
                        // => POST 요청에서는 반드시 headers 형식 작성 
                        //(JSON 포맷을 사용함을 알려줘야함)

        }).then(response => {
                // fetch에서 response에 담아온 정보들을 1차적으로 확인한다
                // ** then 1 단계
                // => status 확인 -> Error catch 블럭으로 또는 Response Body-reading Data return
                // => Response Body의 Data-reading & return.
                if(!response.ok) throw new Error(response.status)
                // => Error 임을 인지시켜 catch 블럭으로 분기하기 위해서(에러가 발생했다면 catch블럭으로 전달)
                //   - fetch는 네트워크 장애등으로 HTTP요청을 할수없거나,
                //     url에 해당하는 페이지가 없는 경우에만 거부(reject)되어 catch로 분기하므로,
                //     .then절(1단계) 에서 수동으로 HTTP 에러를 처리함.
                //     그러나 axios는 상태코드가 2xx의 범위를 넘어가면 거부(reject)함.
                return response.text();
                // 서버에서 Text 형식으로 보냈으므로 text() 메서드 사용
                // Type 별로 Body-reading method를 적용함
        }).then(responseData => {
                // ** then 2 단계
                // fetch에서 response에 담아온 정보들을 2차적으로 확인
                alert(`** responseData => ${responseData}`);
                location.reload();

        }).catch(err => {
        // response에 담긴 정보가 올바르지 않을 때
        // 람다식으로 익명함수 사용
        console.error(`** Error => ${err.message}`); 
        if(err.message=='502') alert('아이디 혹은 패스워드 오류입니다');
        else alert(`Front : 시스템 오류입니다. 잠시후 다시 진행해주세요 status => ${err.message}`)
        });
}//rsLogin

// 1.3) reLoingjj
// request : json , response : json
function rsLoginjj() {
        let url="/rest/rsloginjj";

        fetch(url, {method:'Post',
                        body:JSON.stringify({
                                id:document.getElementById('id').value,
                                password:document.getElementById('password').value
                        }),//body
                        headers:{'Content-Type':'application/json'}
                        // => POST 요청에서는 반드시 headers 형식 작성 
                        //(JSON 포맷을 사용함을 알려줘야함)

        }).then(response => {
                if(!response.ok) throw new Error(response.status)
                return response.json();
        // => 서버에서 JSON 형식으로 보냈으므로 json() 메서드 사용
        //    서버에서 UserDTO를 사용했으므로 사용시에 맴버변수명 주의(id, username..) 
        }).then(responseData => {
                alert(`** responseData => id=${responseData.id}, name=${responseData.userName}`);
                location.reload();

        }).catch(err => {
                console.error(`** Error => ${err.message}`); 
                if(err.message=='502') alert('아이디 혹은 패스워드 오류입니다');
                else alert(`Front : 시스템 오류입니다. 잠시후 다시 진행해주세요 status => ${err.message}`)
        });
}//reLoingjj

// 2) Axios Login
// => 라이브러리 추가 (CDN 으로..   axTest01.jsp 에)
// => 서버요청은 위 "1.3) rsLoginJJ" 과 동일하게 rsloginjj 
// => JSON <-> JSON
// => Request
//   - data  : JSON Type 기본 (fetch 처럼 JSON.stringify 필요없음) 
//   - header: {'Content-Type': 'application/json'}  
// => Response
//    - then : 응답 Data는 매개변수.data 로 접근가능, JSON Type 기본 (1단계로 모두 받음: fetch 와 차이점))   
//   - catch
//       . axios는 상태코드가 2xx의 범위를 넘어가면 거부(reject) 되어 catch절로 분기함 
//         이때 catch 절의 매개변수는 response 속성으로 error 내용 전체를 객체형태로 전달받음   
//       . error.response : error 내용 전체를 객체형태로 전달받음
//       . error.response.status : status 확인가능   
//       . error.message : 브라우져의 Error_Message, "Request failed with status code 415

// 제이슨으로 주고받을것이기 때문에 서버에 메서드는 이미 존재한다 즉 따로
// rsloginjj 보내주는 대신 처리하는 문법만 작성해주면 된다
function axiLoginjj(){
        let url ="/rest/rsloginjj";
        
        axios({url:url,
                method:'Post',
                headers:{'Content-Type':'application/json'},
                data:{
                      id:document.getElementById('id').value,
                      password:document.getElementById('password').value}

        }).then(response => {
                alert(`** response.data => ${response.data}`)
                alert(`** response.id = ${response.data.id}, name=${response.data.userName}`);
                location.reload();
        }).catch(err => {
                console.error(`** err.response => ${err.response},
                                err.status => ${err.response.status},
                                err.message => ${err.message}`);
                
                if(err.response.status=='502') alert('아이디 혹은 패스워드 오류입니다')
                else alert(`Front : 시스템 오류입니다. 잠시후 다시 진행해주세요 status => ${err.message}`)
        });
}
/**
** 입력값의 무결성 확인
** member 무결성 확인사항
// ID : 길이(4~10), 영문자,숫자 로만 구성
// Password : 길이(4~10), 영문,숫자,특수문자로 구성, 특수문자는 반드시 1개 이상 포함할것
// Password2: 재입력후 Password 와 일치성 확인
// Name : 길이(2이상), 영문 또는 한글로 만 입력
// Age: 정수의 범위  ( 숫자이면서, '.'이 없어야함 )  
// BirthDay : 입력 여부 확인  ( length == 10 )
// Point : 실수 ( 구간설정 100 ~ 10000 까지만 가능 )
// Jno : select 를 이용 (X)
// Info : (X)

** 작성 규칙
   => JavaScript function 으로 정의 하고 
      결과를 true or false 로 return
   => 정규식을 활용한다.
   
** match Test
   => 아래 조건에 true -> not (!)  match 적용해보면
   => 정확하지 않으므로 (부적절, replace 를 사용)
        ...       
        } else if (!id.match(/[a-z.0-9]/gi)) {
            alert(' ID는 영문자와 숫자로만 입력하세요. !!!')
            return false;
        }    
 */

        "use strict"

        // 1) ID
        // 길이확인 & 영문과 한글 확인
        // => id.length 확인
        // => 영문과 숫자로만 입력했는지 : id 에서 영문과 숫자를 모두 '' 로 변경했을때 length 가 0 이면 OK 
        // => 정규식 사용하기 : / / 내에 사용  & i -> 대소문자 모두 확인 & g -> 모든 블럭의 글자에 적용해서 확인한다.
        
        let special = /[a-z.0-9]/gi;
        let special2 = /[a-z.0-9.!-*.@]/gi; // 영문, 숫자, 특수문자 확인
        let special3 = /^(?=.?[0-9])(?=.?[#?!@$ %^&*-]).{4,}$/gi;

        function idCheck(){
            let id=document.getElementById('id').value;
            if(id.length<4 || id.length>10){
                document.getElementById('iMessage').innerHTML="id는 4~10자 입니다";
                return false;
        
            // } else if(id.replace(/[a-z.0-9]/gi,'').length>0) { // 정규식 활용 시 /^[A-Za-z0-9][A-Za-z0-9]*$/.test(id)
                // test() 메서드 활용하기 : 검사대상문자
                // 정규식에 정의된 문자가 아닌 문자가 존재할 때 false 반환
             } else if(!special.test(id)) { 
                document.getElementById('iMessage').innerHTML="id는 영문과 숫자만 가능합니다.";
                return false;
            } else {
                document.getElementById('iMessage').innerHTML="";
                return true;
            }
        }
        
        // 2) Password
        function pwCheck(){
            let pw=document.getElementById('password').value;
            if(pw.length<6 || pw.length>15){
                document.getElementById('pMessage').innerHTML="비밀번호는 6~15자로 작성해주세요";
                return false;

            // 1. 영문, 숫자, 특수문자
            // } else if(pw.replace(/[a-z.0-9.!-*.@]/gi,'').length>0){ // => 영문, 숫자, 특수문자로만 구성할 때
            // } else if(!special3.test(pw)){ // test() 메서드 활용하기
        } else if(pw.replace(special2,'').length>0){ // => 영문, 숫자, 특수문자로만 구성할 때
            document.getElementById('pMessage').innerHTML="비밀번호는 영문,숫자,특수문자로 작성해주세요";
            return false;
            
            // 2. 특수문자
            //} else if(!special3.test(pw)){ // => 특수문자 반드시 포함
            } else if(pw.replace(/[!-*.@]/gi,'').length>=pw.length){ // => 특수문자 반드시 포함
                document.getElementById('pMessage').innerHTML="비밀번호는 특수문자를 반드시 포함해야합니다.";
                return false;
                
            } else{
                document.getElementById('pMessage').innerHTML="";
                return true;
            }
        }
        
        // 3) Password2
        function pw2Check(){
            let pw=document.getElementById('password').value;
            let pw2=document.getElementById('password2').value;
            
            if(pw!=pw2){
                document.getElementById('p2Message').innerHTML='입력하신 비밀번호가 다릅니다.';
                return false;
            } else{
                document.getElementById('p2Message').innerHTML='';
                return true;
            }
            
        }
        
        let special4 =  /^[가-힣]{2,17}$/;
        let special5 =  /[a-z.가-힣]/gi;    // 쌤코드
        // 4) name
        function nmCheck(){
            let name=document.getElementById('name').value;
            
            if(name.length<2){
                document.getElementById('nMessage').innerHTML='이름은 2글자 이상 입력 가능합니다.';
                return false;
            }
            else if(name.replace(special5,'').length>0){
                document.getElementById('nMessage').innerHTML='이름은 한국어로 2~17자내에 입력해주세요';
                return false;
            } else {
                document.getElementById('nMessage').innerHTML='';
                return true;
            }
        }
        

        // 5) age
        // => 정수의 조건 : 숫자이면서 소수점이 없어야한다.
        // => Number.isInteget(n) : n이 정수일때만 true 반환
        //     -> 단, n의 Type은 숫자여야한다.
        //     -> value 속성의 값은 문자 Type이므로 숫자화_parseInt가 필요하다.
        //         -> 실수의 경우에는 정수만 사용(123.56 -> 123)
        //         -> 숫자 뒤쪽에 문자가 포함되면 앞쪽의 숫자만 가져와 정수로 리턴
        //         -> 문자로 시작하게되면 문자로 취급한다, NaN(Not a Number)을 return함

        // => 숫자 아닌 값이 있는지를 확인 & Number.isInteget(n) 확인

        function agCheck(){
            let age=document.getElementById('age').value;
            console.log(`** parseInt(age) => ${parseInt(age)}`);                            // 형변환
            console.log(`** Number.isInteget(n) => ${Number.isInteger(age)}`);              // 문자열을 대입
            console.log(`** Number.isInteget(n) => ${Number.isInteger(parseInt(age))}`);    // integer로 변환 후 사용
            
            if(age.replace(/[^0-9]/,'').length < age.length ||  // 숫자 아닌 값이 있는지 확인하여 공백으로 만들고 기존 나이보다 작을경우 오류
                Number.isInteger(parseInt(age))==false){        // 숫자만 남은 상태에서 정수값만 있는지 확인
                    document.getElementById('aMessage').innerHTML='나이는 정수만 입력해주세요';
                    return false;
            } else {
                document.getElementById('aMessage').innerHTML='';
                return true;
            }
        }
        
        // 6) Point
        // => 정수 또는 실수 허용
        // => 범위: 100 ~ 10000
        // => parseFloat(point)
        //      -> 오류 또는 입력값이 없는 경우 NaN return
        //      -> 확인 : Number.isNaN(parseFloat(point)) 
        //    -> 단, 숫자로 시작하면 뒤쪽에 문자가 섞여있어도 숫자값만 사용함 ( NaN 을 return 하지않음 ) 
        function poCheck(){
            let point=document.getElementById('point').value;
            console.log(`** parseFloat(point) => ${parseFloat(point)}`);                            
            console.log(`** Number.isNaN(point) => ${Number.isNaN(point)}`);              
            console.log(`** Number.isNaN(parseFloat(point)) => ${Number.isNaN(parseFloat(point))}`); 

            // => 숫자가 아닌 값이 있는지 확인 : Number.isNaN(...) 작용
            // => 단, 소수점은 허용해야한다. : / 로 구분하여 .(소수점) 허용 (비교값으로 소수점을 사용하기 위해)
            if(point.replace(/[^0-9./.]/g,'').length < point.length ||
                Number.isNaN(parseFloat(point))==true){    // 입력받은 값이 없음
                document.getElementById('oMessage').innerHTML='포인트는 실수를 입력해주세요';
                return false;
            } else if(parseFloat(point)<100 || parseFloat(point)>10000){
                document.getElementById('oMessage').innerHTML='포인트는 100부터 10,000까지 입력해주세요';
                return false;
            } else {
                document.getElementById('oMessage').innerHTML='';
                return true;
            }
        }
        
        // 7) Birthday
        function bdCheck(){
            let birthday=document.getElementById('birthday').value;
            if(birthday.length!==10){
                document.getElementById('bMessage').innerHTML='생년월일을 정확히 입력해주세요';
                return false;
            } else {
                document.getElementById('bMessage').innerHTML='';
                return true;
            }
        }
        
        addEventListener
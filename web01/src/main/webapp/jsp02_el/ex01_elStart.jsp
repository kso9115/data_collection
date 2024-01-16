<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Expression Language(EL) 기본사항 **</title>
</head>
<body>
<h2>** EL 기본사항 **</h2>
<% String name = "홍길동"; %>
<pre>
=> EL : Expression Language, 표현언어
=> 편리한 값(Value)의 출력과 사용

1. 값(변수의 값) 의 출력 비교
=> Java 표현식 사용하기 : <%=name%>
=> Java out 객체 활용한 출력 : <% out.print("Java out 객체 출력 => "+name); %>
=> EL 활용한 출력 : ${"~~~ Hello EL, 표현언어 ~~~"}
	-> Java 변수 출력 : \${"EL로 Java변수 출력하기 =>"+name}
	-> EL 내부에서는 Java 변수를 사용할 수 없다.
	-> JSTL과 병행해서 사용해야한다.
<hr><b>

2. EL Test
** EL 자료형 **
정수형 : ${123}
실수형 : ${10.123}
문자형 : ${"안녕하세요 EL 자료형 공부중입니다!"}
논리형 : ${true}
null : ${null}

** EL 연산 **
=> 산술(4칙)연산
\${5+2}=${5+2}
\${5-2}=${5-2}
\${5*2}=${5*2}
자바코드에서 진행되는 부분이긴 하나 나누기의 연산결과를 실수로 취급한다
\${5/2}=${5/2}
\${5%2}=${5%2}

=> 관계(비교)연산 : 결과가 항상 true or false
<!-- >,<,>=,<=,==,!== -->
[형식]
gt : greater than / lt : less than
ge: greater equal / le: less equal
eq: equal, == / ne: not equal, !=

[실사용]
\${5>2} => ${5>2} 
\${5 gt 2} => ${5 gt 2} 
\${5<2} => ${5<2} 
\${5 lt 2} => ${5 lt 2}

\${5>=2} => ${5>=2} 
\${5 ge 2} => ${5 ge 2} 
\${5<=2} => ${5<=2} 
\${5 le 2} => ${5 le 2}
 
\${5==2} => ${5==2} 
\${5 eq 2} => ${5 eq 2} 
\${5!=2} => ${5!=2} 
<%-- \${5 ne 2} => ${5 ne 2} --%> // 오류 발생하는 상태로 뜨기때문에 우선 주석처리

=> 논리(집합) 연산 : &&, ||
\${5>2 && 10>20} => ${5>2 && 10>20} 
\${5>2 || 10>20} => ${5>2 || 10>20} 

=> 삼항식(조건식)
\${5>2 ? 5 : 2} => ${5>2 ? 5 : 2} 
EL태그 내부에서는 스트링 타입 표현 시 싱글쿼터, 더블쿼터 모두 사용 가능하다
\${5>2 ? '오' : '이'} => ${5>2 ? '오' : '이'} 
 
 <hr>
3. 기타
** Java 변수
=> Java 표현식의 경우 : <%=name%>
=> \${name} : ${name} 
   -> 자바변수는 출력하지 않음, JSTL로 정의한 변수는 출력 
   -> name 의 값이 없음을 확인하기 위해 활용 가능
   -> \${empty_name} : ${empty name}
   <!-- 
    => empty : 검사할 객체가 비어있는지 확인 
            비어있으면 true 
            list, map 타입의 객체가 값이 있는지 없는지 구분해줌  
    => EL 에 자바변수는 직접 값을 전달하지 못함
     (jsp에서 자바코드가 완전 분리됨을 목표로 하기때문에 자바변수를 사용할 필요는 없으므로)
     자바코드 내 name이라는 속성을 선언하여도 jsp에서는 인식하지 못하고, JSTL로 정의한 변수 혹은 속성값을 직접 입력하여 확인하는 방법밖에 없다. 
    => EL 에 변수명이 오면 JSTL로 정의한 변수 또는 속성(Attribute) 의 이름으로 인식함.              
    -->

** request 객체의 Parameter 처리
=> request 객체의 Parameter를 전달하는 el의 내부객체 제공 : param
=> 퀴리스트링으로 id 지정 전.후 Test : ~/web01/jsp02_el/ex01_elStart.jsp?id=banana

=> Java 의 표현식으로 처리할 때 : <%=request.getParameter("id")%>
=> EL로 처리할 때 : param구문 사용 ex.param.id
\${empty_param.id} : ${empty param.id}
\${param.id} : ${param.id}
\${param[id]} : ${param["id"]}

** getAttribute 처리
=> ex02 에서 진행
</b></pre>
</body>
</html>
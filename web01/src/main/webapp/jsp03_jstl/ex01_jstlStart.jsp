<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL Start **</title>
</head>
<body>
<h2>** JSTL Start **</h2>
<pre>
<b>
=> jstl 라이브러리를 인식할 수 있게 선언 -> 디렉티브 속
   디렉티브 taglib에 uri="..." prefix=".."
   
 1. 출력 : out Tag
 => Java의 out 객체와 표현식, EL을 대신하여 역할을 수행한다.
 	태그 내에 별도로 값이 없을경우 바로 닫아도 무방하다
 <c:out value="~~~Hello JSTL !!! 안녕 ~~~"/>
  
 2. 변수 정의
 => set : 변수명과 값의 위치는 바뀌어도 상관 없음
 <c:set value="홍길동" var="name"/>
 <c:set var="age" value="22"/>
 
 3. 변수 출력(out_Tag 혹은 EL 로 출력할 수 있다)
 => JSTL의 out_Tag : 결국은 EL사용(연산도 가능)
 * name = <c:out value="${name}"/>
 * age = <c:out value="${age}"/>
 => EL
 * name = ${name}
 * age = ${age}
 * age*100 = ${age}*100
 
 => Java는 JSTL 변수와 호환 되는가? : 안됨!!!!!!읽지못함!!
 => jstl - java코드 서로 호환되지 않음
 <%-- name = <%=name%> --%>
 
 4. 연산 적용
 <c:set value="${age+age}" var="add"/>
 \${add} = ${add}
 <c:set value="${name==age}" var="bool"/>
 \${bool} = ${bool}
 <c:set value="${age>add ? age : add}" var="max"/>
 \${max} = ${max}
 
 5. 변수 삭제
 => 정의된 변수 삭제 remove
 <c:remove var="add"/>
 \${empty_add} = ${empty add} 
 \${empty_age} = ${empty age} 
 
 => 정의하지 않은 변수 삭제
 => 별도로 오류가 발생하지는 않는다.
 <c:remove var="password"/> 
 
 6. 우선순위
 => Jstl 변수와 pageScope의 Attribute간의 우선순위 확인
 => 나중에 정의한 값이 우선 적용
 	(set변수, attribute 중 나중에 정의된 값 우선) 
 
 <% 
 // pageScope에 Attribute를 정의 후 Test
 	pageContext.setAttribute("name", "그린컴");
 %>

 
 * Test1) name을 정의한 순서 : set 홍길동 -> page_setAttribute에 그린컴 정의
 \${name} = ${name} 
 
 * Test2) set의 name을 재정의 : set -> set
 <c:set value="new_홍길동" var="name"/>
 \${name} = ${name}

</b>

</pre>
</body>
</html>
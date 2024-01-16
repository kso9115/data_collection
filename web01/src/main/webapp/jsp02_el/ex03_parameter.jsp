<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Parameter 활용하기 **</title>
</head>
<body>
<h2>** Parameter 활용하기 **</h2>
<pre>
<b>
=> 동질성 비교하기 , null(값의 존재) 확인
1. 동질성 비교
* I D : ${param.id} 
* Password : ${param.password} 

\${param.id =='admin'} => ${param.id =='admin'}
\${param.password =='12345'} => ${param.password =='12345'}

2. null(값의 존재)인지 아닌지 확인하기 : empty, ==
=> 파라미터의 존재 여부에 따라 쿼리스트링으로 비교 Test를 해야한다.
	~/ex03_parameter.jsp?id=admin&password=12345!
	~/ex03_parameter.jsp?id=admin&password=
	~/ex03_parameter.jsp?id=admin

2-1 ==null
\${param.id==null} => ${param.id==null}
\${param.password==null} => ${param.password==null}

2-2 empty
\${empty_param.id} => ${empty param.id}
\${empty_param.password} => ${empty param.password}

3. pageContext
=> Jsp 페이지에 대한 정보를 저장하고 있는 객체(pageScope)
=> 기본객체를 return 하는 메서드를 제공
EL 태그를 활용하여 URL URI 간편하게 출력하기
* 요청 URL : ${pageContext.request.requestURL}
* 요청 URI : ${pageContext.request.requestURI}


</b>
</pre>

</body>
</html>
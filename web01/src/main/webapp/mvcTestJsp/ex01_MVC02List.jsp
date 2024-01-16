<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mvcTest.StudentDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MVC02_List_Java **</title>
</head>
<body>
<%
	// java 형변환의 경우는 백엔드단에서 진행 후 프론트에 뿌려줘야한다
	// 프론트 작업때마다 데이터를 변경할 수 없으니까.
	
	List<StudentDTO> list = (List<StudentDTO>)request.getAttribute("myList");
%>
	<table border="1" style="width: 100%">
		<tr bgcolor="Lime">
			<th>Sno</th>
			<th>Name</th>
			<th>Age</th>
			<th>Jno</th>
			<th>Info</th>
			<th>Point</th>
		</tr>
		
<%	// List 출력구문
	if(list !=null){
		for (StudentDTO s : list) { %>
			<tr>
			<td><%=s.getSno()%></td>
			<td><%=s.getName()%></td>
			<td><%=s.getAge()%></td>
			<td><%=s.getJno()%></td>
			<td><%=s.getInfo()%></td>
			<td><%=s.getPoint()%></td>
			</tr>
			
<%		}
	} else { %>
		<h3> 출력할 차료가 없습니다. </h3>
<%	}
%>
	</table>
</body>
</html>
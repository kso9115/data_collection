<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="mvcTest.StudentService,mvcTest.StudentDTO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jsp StudentList_MVC01 **</title>
</head>
<body>

	<h2>** StudentList 출력하기 **</h2>
	<h2>=> Service -> 결과 -> 출력</h2>


	<%
	// 패키지명을 붙일경우 사용이 가능하나 가독성이 떨어진다. -> 위에서 임포트 해주기
	// mvcTest.StudentService service = new StudentService();
	StudentService service = new StudentService();
	List<StudentDTO> list = service.selectList();
	
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
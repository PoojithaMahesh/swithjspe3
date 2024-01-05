<%@page import="studentwithjspa2.dto.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%List<Student> students=(List)request.getAttribute("list"); %>

<table border="2px">
<tr>
<th>Id</th>
<th>Name</th>
<th>Address</th>
<th>Fees</th>
<th>Email</th>
<th>Password</th>
<th>Phone</th>
<th>Delete</th>
<th>Update</th>
</tr>

<%for(Student student:students){ %>
<tr>
<td><%=student.getId() %></td>
<td><%=student.getName()%></td>
<td><%=student.getAddress() %></td>
<td><%=student.getFees() %></td>
<td><%=student.getEmail() %></td>
<td><%=student.getPassword() %></td>
<td><%=student.getPhone() %></td>
<td><a href="delete?id=<%=student.getId() %>">Delete</a></td>
<td><a href="update">Update</a></td>
</tr>
<%} %>


</table>


</body>
</html>
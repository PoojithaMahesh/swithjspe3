<%@page import="studentwithjspa2.dto.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String message=(String)request.getAttribute("message"); %>
<%if(message!=null){ %>
<h1><%=message %></h1>

<%}%>
<%Student student=(Student)request.getAttribute("student"); %>


<form action="edit" method="post">
Id::<input type="number" name="id" value="<%=student.getId() %>" readonly="readonly">
<br>
<br>
Name::<input type="text" name="name" value="<%=student.getName() %>">
<br>
<br>
Address::<input type="text" name="address" value="<%=student.getAddress() %>">
<br>
<br>
Phone::<input type="tel" name="phone" value="<%=student.getPhone() %>">
<br>
<br>
Email::<input type="email" name="email" value="<%=student.getEmail() %>">
<br>
<br>
Password::<input type="password" name="password" value="<%=student.getPassword() %>">
<br>
<br>
<input type="submit" value="Edit">

</form>













</body>
</html>
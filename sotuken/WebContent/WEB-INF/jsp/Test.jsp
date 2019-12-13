<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	List<String[]> list = (List<String[]>)request.getAttribute("list");
%>

<table>
<%
	for(String[] s : list){
%>
<tr>
<td><%= s[0] %></td>
<td><%= s[1] %></td>
<td><%= s[2] %></td>
<td><%= s[3] %></td>
<td><%= s[4] %></td>
</tr>
<%
	}
%>
</table>

</body>
</html>
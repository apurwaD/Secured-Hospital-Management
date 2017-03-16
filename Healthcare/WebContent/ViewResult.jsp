<!--
 *  ViewResult.jsp
 * 
 * 
 * @author    :  Apurwa Dandekar  Id$ ard5145
 *              
 * 
 */-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page language="java" import="DatabaseConnection.*"
	isErrorPage="false"%>
<%@page language="java" import="java.sql.*" isErrorPage="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./style.css" />
<title>Healthcare Managemaent</title>
</head>
<body id="body2">
	<div id="one1" style="width: 60%; float: left">
		<h1>
			<u>Welcome</u>
		</h1>
		<%
			HttpSession sess = request.getSession();
			String userName = request.getParameter("username");
		%>
		<h2>
			<u><%=userName%></u>
		</h2>
	</div>
	<div id="two2"
		style="width: 40%; float: left; background: url(crimetypes.jpg)">
	</div>

</body class="center">
</html>
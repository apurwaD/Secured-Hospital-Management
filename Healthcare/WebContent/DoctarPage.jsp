<!--/*  
 *
 * 
 * @version   : DoctorPage.jsp,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */ 
 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->
<!--
/*
 *  index.html
 *
 * Project:Healthcare Management
 * 
 *
 * @version   : DoctorPage.jsp,v 1.1 2016/01/03 23:31:20
 *
 * @author    : Apurwa Dandekar  Id$ ard5145
 *             
 *
 *
 */
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page language="java" import="DatabaseConnection.*"
	isErrorPage="false"%>
<%@page language="java" import="java.sql.*" isErrorPage="false"%>
<html>
<head>
<script src="validation.js"></script>
<title>Healthcare Managemaent</title>
<style>
.right {
	position: absolute;
	right: 0px;
	width: 100px;
	padding: 10px;
}

.center {
    margin: auto;
    width: 60%;
    border: 3px solid #ff5c33;
    padding: 10px;
    background-color: #fff0e6;
}

.centerForm {
	margin: auto;
	width: 60%;
	padding: 10px;
}
.centerBody {
    margin: auto;
    width: 60%;
    padding: 10px;
    background-color: #fff0e6;
}
</style>
</head>
<body id="body1" class="centerBody" >
	<%
		HttpSession sess = request.getSession();
		String id = (String) sess.getAttribute("employeeID");
		String username = (String) sess.getAttribute("username");
		String role=(String) sess.getAttribute("role");
		if ((id != null) && (role.equals("doctor"))) {
	%>
	<div class="right" height="30%" id="header">
		<form action="Logout" method="post">
			<input type="submit" value="Logout" />
		</form>
	</div>
	<div class="center" height="30%" id="header">
		<b align="center"><h1>
				<u>Healthcare Management</u>
			</h1></b>
	</div>

	<div class="centerForm" height="70%">
		<h3>
			Welcome
			<%=username%>!</h3>
		
		<form name="myForm1" action="PatientDetails" method="get">
			<div align="center" height="70%">
				<input type="hidden" id="empId" name="empId" value="<%=id%>">
				<input type="hidden" id="role" name="role" value="doctor">
				<table class="center" height="50px" width="100px">
					<tr>
						<td><input type="submit" name="Showetails"
							value="Show details of all the patients"></td>
					</tr>
				</table>
			</div>
		</form>

		<form name="myForm2" action="PatientDetails" method="post">
			<div align="center" height="70%">
				<table class="center" height="50px" width="100px">
					<input type="hidden" id="empId" name="empId" value="<%=id%>">
					<tr>
						<td><b style="font-size: 15px;">Patient's First Name</b></td>
						<td><input type="text" name="patientName"></td>
						<td><input type="submit" name="lookup" value="Search"></td>
					</tr>
				</table>
			</div>
		</form>

		<form name="myForm3" action="UpdateDetails"
			onsubmit="return validate()" method="get">
			<div align="center" height="70%">
				<table class="center" height="50px" width="100px">
					<input type="hidden" id="empId" name="empId" value="<%=id%>">
					<tr>
						<td><b style="font-size: 15px;">Patient's First Name</b></td>
						<td><input type="text" name="patientName"></td>
						<td><input type="submit" name="Update" value="Update"></td>
					</tr>
				</table>
			</div>
		</form>

		
	</div>
	<div id="two" style="width: 50%; float: left"></div>
</body>
<%
	}
%>
</html>
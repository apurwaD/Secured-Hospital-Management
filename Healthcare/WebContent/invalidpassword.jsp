<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="validatepasswords.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
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
		String id = (String)sess.getAttribute("empId");
	%>
	<div class="center" height="30%" id="header">
		<b align="center"><h1>
				<u>Healthcare Management</u>
			</h1></b>
	</div>
	<form name="myForm1" action="UpdateOwnDetails" onsubmit="return validate()" method="post">
		<div align="center">
			<b style="font-size: 15px; color: red; align: center">Old
				password doesn't match</b>
		</div>
		<div align="center" height="70%">
			<input type="hidden" id="empId" name="empId" value="<%=id%>">
			<table class="center" height="50px" width="100px">
				<tr>
					<td><b style="font-size: 10px;">Enter Old Password</b></td>
					<td><input type="password" name="oldpassword"></td>
				</tr>
				<tr>
					<td><b style="font-size: 10px;">Enter New Password</b></td>
					<td><input type="password" name="newpassword"></td>
				</tr>
				<tr>
					<td><b style="font-size: 10px;">Confirm New Password</b></td>
					<td><input type="password" name="confirmpassword"></td>
				</tr>
				<tr>
					<td><input type="submit" name="update" value="Update Password"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
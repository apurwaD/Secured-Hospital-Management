<%@page import="DatabaseConnection.DbConnection"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.SQLException"%>
<%@page import="DatabaseConnection.DbConnection.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="validateAddDoctorPage.js"></script>
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
	DbConnection db = new DbConnection();
	db.connect();
	PreparedStatement preparedStatement = null;
	String selectDoctors = "select d_id,firstname, lastName from doctor";
	preparedStatement = db.con.prepareStatement(selectDoctors);
	ResultSet rs = preparedStatement.executeQuery();
	HashMap<String, String> docIds = new HashMap<String, String>();
	while (rs.next()) {
		String fullname = rs.getString("firstName") + " "
				+ rs.getString("lastname");
		docIds.put(rs.getString("d_id"), fullname);
	}
%>
<div class="center" height="30%" id="header">
		<b align="center"><h1>
				<u>Healthcare Management</u>
			</h1></b>
	</div>
	<form name="myForm1" action="EmployeeDetailsAdd" onsubmit="return validate()" method="post">
		<div align="center" height="70%">
			<table class="center" height="150px" width="300px">
			<input type="hidden" id="role" name="role" value="nurse">
				<tr>
					<td><b style="font-size: 10px;">Enter Username</b></td>
					<td><input type="text" name="username"></td>
				</tr>

				<tr>
					<td><b style="font-size: 10px;">Enter Password</b></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><b style="font-size: 10px;">Enter Confirmed Password</b></td>
					<td><input type="password" name="cpassword"></td>
				</tr>
				<tr>
					<td><b style="font-size: 10px;">Enter FirstName</b></td>
					<td><input type="text" name="firstName"></td>
				</tr>
				<tr>
					<td><b style="font-size: 10px;">Enter LastName</b></td>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>Doctor Supervised under</td>
					<td><select name="docId">
							<%
								if (!docIds.isEmpty()) {
									Iterator it = docIds.entrySet().iterator();
									while (it.hasNext()) {
										Map.Entry pair = (Map.Entry) it.next();
										String id = (String) pair.getKey();
										String name = (String) pair.getValue();
							%>
							<option value="<%=id%>"><%=name%></option>
							<%
								}
								}
							%>
					</select></td>
				</tr>
				<tr>
						<td><b style="font-size: 10px;">Select Department</b></td>
						<td><select name="dept">
								<option value="Orthopedics">Orthopedics</option>
								<option value="Gynac">Gynac</option>
								<option value="Cardio">Cardio</option>
								<option value="Peditrition">Peditrition</option>
						</select></td>
					</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Submit"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>
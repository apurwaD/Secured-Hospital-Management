<!--/*  
 *
 * 
 * @version   : receptionistAddbasic.jsp,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */  -->
<%@page import="DatabaseConnection.DbConnection"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="DatabaseConnection.DbConnection.*"%>
<%@page language="java" import="java.util.*" isErrorPage="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="check.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Healthcare Managemaent</title>
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
<%
	DbConnection db = new DbConnection();
	db.connect();
	PreparedStatement preparedStatement = null;
	String selectDoctors = "select d_id,firstname, lastName from doctor";
	preparedStatement = db.con.prepareStatement(selectDoctors);
	ResultSet rs = preparedStatement.executeQuery();
	Map<String, String> docIds = new HashMap<String, String>();
	while (rs.next()) {
		String fullname = rs.getString("firstName") + " "
				+ rs.getString("lastname");
		docIds.put(rs.getString("d_id"), fullname);
	}
%>
<body class="centerBody">
	<div class="center" height="30%" id="header">
		<b align="center"><h1>
				<u>Healthcare Management</u>
			</h1></b>
	</div>
	<form name="myForm4" action="PatientsBasicDetails" onsubmit="return validate()" method="post">
		<div align="center" height="70%">
			<input type="hidden" id="empId" name="empId" value="recep">
			<table class="center" height="50px" width="100px" border="1px">

				<tr>
					<td>Enter FirstName</td>
					<td><input type="text" name="firstName"></td>
				</tr>
				<tr>
					<td>Enter LaststName</td>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>Doctor consulted under</td>
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
					</select></td></td>

				</tr>
				<tr>
					<td>Enter Apartment</td>
					<td><input type="text" name="apartment"></td>
				</tr>
				<tr>
					<td>Enter Street</td>
					<td><input type="text" name="street"></td>
				</tr>
				<tr>
					<td>Enter City</td>
					<td><input type="text" name="city"></td>
				</tr>
				<tr>
					<td>Enter State</td>
					<td><input type="text" name="state"></td>
				</tr>
				<tr>
					<td>Enter zipcode</td>
					<td><input type="text" name="zipcode"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Add"></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>
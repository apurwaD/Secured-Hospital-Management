<!--/*  
 *
 * 
 * @version   : viewBasicDetails.jsp,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */  -->
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page language="java" import="DatabaseConnection.*"
	isErrorPage="false"%>
<%@page language="java" import="java.util.*" isErrorPage="false"%>
<%@page language="java" import="java.sql.*" isErrorPage="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="validateRecepUpdatingDetails.js"></script>
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
<body id="body1" class="centerBody" >
<div class="center" height="30%" id="header">
		<b align="center"><h1>
				<u>Healthcare Management</u>
			</h1></b>
	</div>

	<h1 align="center">Patient Details</h1>
	<%
		HttpSession sess = request.getSession();
		HashMap<String, Treatment> treatment = new HashMap<String, Treatment>();
		String lookup = request.getParameter("lookup");
		treatment = (HashMap) request.getAttribute("resultset");
		DbConnection db = new DbConnection();
		db.connect();
	%>
	<div align="center" height="70%">
		<table class="center" height="50px" width="100px" border="1px">
			<tr>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Apartment</th>
				<th>Street</th>
				<th>City</th>
				<th>State</th>
				<th>ZipCode</th>
				<th>Update</th>
			</tr>
			<%
			String selectSQL="";
			String patientName="";
			PreparedStatement ps;
				patientName=request.getParameter("patientName");
				selectSQL = "select * from RECEPTIONISTVIEW where firstName=?";
				ps = db.con.prepareStatement(selectSQL);
				ps.setString(1, patientName);
			
				ResultSet rs = ps.executeQuery();
				String patientId;
				String patientFirstName;
				String patientLastName;
				String patientApt;
				String patientStreet;
				String patientCity;
				String patientState;
				String patientZipcode;
				String patientAge;
				String patientBloodgroup;
				String patientAllergy;
				while (rs.next()) {
					patientId= rs.getString("patientid");
					 patientFirstName = rs.getString("firstName");
					 patientLastName = rs.getString("lastName");
					 patientApt = rs.getString("city");
					 patientStreet = rs.getString("street");
					 patientCity = rs.getString("city");
					 patientState = rs.getString("state");
					 patientZipcode = rs.getString("zipcode");
					 patientAge = rs.getString("age");
					 patientBloodgroup = rs.getString("bloodgroup");
					 patientAllergy = rs.getString("allergy");
			%>
			
			<tr>
			<form name="myForm" action="UpdateBasicDetails" onsubmit="return validate()" method="post">
				<input type="hidden" name="patientId" value="<%=patientId%>">
				<td><input type="text" name="firstName" value="<%=patientFirstName%>"></td>
				<td><input type="text" name="lastName" value="<%=patientLastName%>"></td>
				<td><input type="text" name="apt" value="<%=patientApt%>"></td>
				<td><input type="text" name="street" value="<%=patientStreet%>"></td>
				<td><input type="text" name="city" value="<%=patientCity%>"></td>
				<td><input type="text" name="state" value="<%=patientState%>"></td>
				<td><input type="text" name="zipcode" value="<%=patientZipcode%>"></td>
				<td><input type="submit" value="Update"><td>
			</form>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>
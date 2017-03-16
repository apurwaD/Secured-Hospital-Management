
<!--/*  
 *
 * 
 * @version   : viewPatientDetails.jsp,v 1.1 2016/10/03 22:34:10 
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
<script src="validateDoctorUpdatingDetails.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Healthcare Managemaent</title>
<style>
.center {
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
		//treatment = (HashMap) request.getAttribute("resultset");
		 treatment=(HashMap)sess.getAttribute("resultset");
	%>
	<div align="center" height="70%">
		<table class="center" height="50px" width="50px" border="1px">

			<tr>
				<th>FirstName</th>
				<th>LastName</th>
				<th>DateFrom</th>
				<th>DateTO</th>
				<th>TreatMent</th>
				<th>Age</th>
				<th>Bloodgroup</th>
				<th>Allergy</th>
				<th>Update</th>
			</tr>
			<%
				String patientId;
				String dateFrom;
				String dateTo;
				String desc;
				String firstName = "";
				String lastName = "";
				String age;
				String bloodgroup;
				String allergy;
				String tId;
				if (!treatment.isEmpty()) {
					Iterator it = treatment.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry pair = (Map.Entry) it.next();
						Treatment temp = (Treatment) pair.getValue();
						patientId = temp.getPatientID();
						dateFrom = temp.getDateFrom().toString();
						dateTo = temp.getDateTo().toString();
						desc = temp.getDescription();
						firstName = temp.getFirstName();
						lastName = temp.getLastName();
						age= temp.getAge();
						bloodgroup=temp.getBloodgroup();
						allergy= temp.getAllergy();
						tId=temp.getTreatmentId();
			%>

			<tr>
			<form name="myForm" action="UpdateDetails" onsubmit="return validate()" method="post">
				<input type="hidden" name="treatmentId" value="<%=tId%>">
				<input type="hidden" name="patientId" value="<%=patientId%>">
				<td><input type="text" name="firstName" value="<%=firstName%>"></td>
				<td><input type="text" name="lastName" value="<%=lastName%>"></td>
				<td><input type="text" name="dateFrom" value="<%=dateFrom%>"></td>
				<td><input type="text" name="dateTo" value="<%=dateTo%>"></td>
				<td><input type="text" name="treatmentDesc" value="<%=desc%>"></td>
				<td><input type="text" name="age" value="<%=age%>"></td>
				<td><input type="text" name="bloodGroup" value="<%=bloodgroup%>"></td>
				<td><input type="text" name="allergy" value="<%=allergy%>"></td>
				<td><input type="submit" value="Update"><td>
			</form>
			</tr>
			<%
				}
				}
			%>

		</table>
	</div>
</body>
</html>
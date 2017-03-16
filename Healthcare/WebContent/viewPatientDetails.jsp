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
<head>
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
	try{
		HttpSession sess = request.getSession();
		HashMap<String, Treatment> treatment = new HashMap<String, Treatment>();
		String lookup = request.getParameter("lookup");
		treatment = (HashMap) sess.getAttribute("resultset");
	%>
	<div align="center" height="70%">
		<table class="center" height="50px" width="100px" border="1px">

			<tr>
				<th>PatientID</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>DateFrom</th>
				<th>DateTO</th>
				<th>TreatMent</th>
				<th>Age</th>
				<th>Bloodgroup</th>
				<th>Allergy</th>
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
				if (!treatment.isEmpty()) {
					Iterator it = treatment.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry pair = (Map.Entry) it.next();
						Treatment temp = (Treatment) pair.getValue();
						patientId = temp.getPatientID();
						if(temp.getDateFrom()!=null){
						dateFrom = temp.getDateFrom().toString();
						}
						else{
							dateFrom="";
						}
						if(temp.getDateTo()!=null){
						dateTo = temp.getDateTo().toString();
						}
						else{
							dateTo="";
						}
						if(temp.getDescription()!=null){
						desc = temp.getDescription();
						}
						else{
							desc="";
						}
						firstName = temp.getFirstName();
						lastName = temp.getLastName();
						age= temp.getAge();
						bloodgroup=temp.getBloodgroup();
						allergy= temp.getAllergy();
			%>

			<tr>
				<td><%=patientId%></td>
				<td><%=firstName%></td>
				<td><%=lastName%></td>
				<td><%=dateFrom%></td>
				<td><%=dateTo%></td>
				<td><%=desc%></td>
				<td><%=age%></td>
				<td><%=bloodgroup%></td>
				<td><%=allergy%></td>
			</tr>
			<%
				}
				}
	}catch(Exception ex){
		
	}
			%>

		</table>
	</div>
</body>
</html>
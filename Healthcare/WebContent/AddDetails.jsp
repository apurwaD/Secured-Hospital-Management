<!--/*  
 *
 * 
 * @version   : AddDetails.java,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */  -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Healthcare Managemaent</title>
</head>
<body>
	<form name="myForm4" action="" onsubmit="return validate()"
		method="post">
		<div align="center" height="70%">
			<table class="center" height="50px" width="100px">

				<tr>
					<td>Enter FirstName</td>
					<td><input type="text" name="firstName"></td>
				</tr>
				<tr>
					<td>Enter LaststName</td>
					<td><input type="text" name="lastName"></td>
				</tr>
				<tr>
					<td>Enter Apartment</td>
					<td><input type="text" name="apartment"></td>
				</tr>
				<tr>
					<td> Enter Street</td>
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
					<td>Enter BloodGroup</td>
					<td><input type="text" name="bloodgroup"></td>
				</tr>
				<tr>
					<td>Enter Allergy</td>
					<td><input type="text" name="allergy"></td>
				</tr>

			</table>
		</div>
	</form>

</body>
</html>
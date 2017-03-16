
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<!--
<!--/*  
 *
 * 
 * @version   : index.jsp,v 1.1 2016/10/03 22:34:10 
 * 
 * @author    : Apurwa Dandekar  Id$ ard5145
 *              Anjali Pachpute  Id$ avp9145
 *              Sharvari Barve   Id$sub2104
 * 
 */  -->


<html>
<head>
<script src="validationLogin.js"></script>
<title>
Healthcare Managemaent
</title>
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
<div  class="center" height="30%"id="header"><b align="center"><h1><u>Healthcare Management</u></h1></b></div>
<div class="centerForm" height="70%">
<form name="myForm" action="UserLogin" onsubmit="return validate()" method="post">
<div align="center" height="70%">
<table class="center" height="150px" width="300px">
       
    <tr>
        <td><b style="font-size:10px;">Enter Username</b></td>
        <td><input type="text" name="username"></td>
    </tr>
	
	<tr>
        <td><b style="font-size:10px;">Enter Password</b></td>
        <td><input type="password" name="password"></td>
    </tr>
   <tr>
        <td colspan="2" align="center"><input type="submit" value="Sign In"></td>
    </tr>
</table>
</div>
</form>
</div >
<div id="two" style="width: 50%; float:left">
</div>
</body>
</html>
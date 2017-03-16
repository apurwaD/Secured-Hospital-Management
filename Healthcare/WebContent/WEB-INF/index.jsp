
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
 * @version   : index.html,v 1.1 2016/01/03 23:31:20
 *
 * @author    : Apurwa Dandekar  Id$ ard5145
 *             
 *
 *
 */
-->


<html>
<head>
<script src="validation.js"></script>
<title>
Crime Detection For Chicago
</title>
<style>
.center {
    margin: auto;
    width: 60%;
    border: 3px solid #73AD21;
    padding: 10px;
}
.centerForm {
    margin: auto;
    width: 60%;
    padding: 10px;
}
</style>
</head>
<body id="body1">
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
        <td colspan="2" align="center"><input type="submit" value="Submit"></td>
    </tr>
</table>
</div>
</form>
</div >
<div id="two" style="width: 50%; float:left">
</div>
</body>
</html>
/*
 *  validation.js
 *
 * Project: HealthCare Management
 * 
 *
 * @version   :validation.js,v 1.1 2015/10/03 23:31:20
 *
 * @author    : Apurwa Dandekar  Id$ ard5145
 *             

 *
 */

function validate() {
	var username = document.myForm1.username.value;
	var newPassword = document.myForm1.password.value;
	var confirmPassword = document.myForm1.cpassword.value;
	var firstName = document.myForm1.firstName.value;
	var lastName = document.myForm1.lastName.value;

	var specialChar = '=';
	
	// if username is empty
	if (username == "") {
		alert("Please provide the new username");
		document.myForm1.username.focus;
		return false;
	}
	// if newPassword is empty
	if (newPassword == "") {
		alert("Please provide the new Password");
		document.myForm1.password.focus;
		return false;
	}

	// if password is empty
	if (confirmPassword == "") {
		alert("Please provide the confirmed password");
		document.myForm1.cpassword.focus;
		return false;
	}

	if (confirmPassword != newPassword) {
		alert("new password and confirmed password don't match");
		document.myForm1.cpassword.focus;
		return false;
	}

	// if firstname is empty
	if (firstName == "") {
		alert("Please provide the firstname");
		document.myForm1.firstName.focus;
		return false;
	}

	// if lastName is empty
	if (lastName == "") {
		alert("Please provide the lastname");
		document.myForm1.lastName.focus;
		return false;
	}

	if ((newPassword.indexOf(specialChar) > -1)
			|| (confirmPassword.indexOf(specialChar) > -1)) {
		alert("Invalid input for passwords");
		return false;
	}

}
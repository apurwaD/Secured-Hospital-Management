
function validate()
{
var oldpassword= document.myForm1.oldpassword.value;
 var newPassword= document.myForm1.newpassword.value;
 var confirmPassword=document.myForm1.confirmpassword.value;
 var specialChar= '=';
 
 if( oldpassword == "" )
 {
   alert( "Please provide the oldpassword" );
   document.myForm1.oldpassword.focus;
   return false;
 }
 
 if (/[^0-9a-zA-Z\s]/gi.test(oldpassword))
 {  
	 alert( "Please enter alphanumeric value for oldpassword" );
    document.myForm1.oldpassword.focus;
    return false; 
 }
 
 // if username is empty
   if( newPassword == "" )
   {
     alert( "Please provide the new Password" );
     document.myForm1.newpassword.focus;
     return false;
   }
   
// if newPassword is empty
	if (newPassword.length<5) {
		alert("Password should contain atleast 5 characters");
		document.myForm1.newpassword.focus;
		return false;
	}
   
   if (/[^0-9a-zA-Z\s]/gi.test(newPassword))
   {  
  	 alert( "Please enter alphanumeric value for newpassword" );
      document.myForm1.newpassword.focus;
      return false; 
   }
  
   
   // if password is empty
   if(confirmPassword == "" )
   {
     alert( "Please provide the confirmed password" );
	 document.myForm1.confirmpassword.focus;
     return false;
   }
   
   if(confirmPassword != newPassword ){
	   alert( "new password and confirmed password don't match" );
		 document.myForm1.confirmpassword.focus;
	     return false;
   }
   if((newPassword.indexOf(specialChar) >-1)||(confirmPassword.indexOf(specialChar) >-1)){
	   alert( "Invalid input for passwords");
	   return false;
   }
  
}
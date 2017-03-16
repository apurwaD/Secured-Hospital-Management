

function validate()
{
 var username= document.myForm.username.value;
 var password=document.myForm.password.value;
 var specialChar= '=';
 var letterNumber = /^[0-9a-zA-Z]+$/; 
 
  
 //if username is empty
   if( username == "" )
   {
     alert( "Please provide the username" );
     document.myForm.username.focus;
     return false;
   }
   
   if (/[^0-9a-zA-Z\s]/gi.test(username))
   {  
 	 alert( "Please enter alphanumeric value for username" );
      document.myForm.username.focus;
      return false; 
   } 
   
   //if password is empty
   if(password == "" )
   {
     alert( "Please provide the password" );
	 document.myForm.password.focus;
     return false;
   }
   
   if (/[^0-9a-zA-Z\s]/gi.test(password))
   {  
 	 alert( "Please enter alphanumeric value for password" );
      document.myForm.password.focus;
      return false; 
   } 
   if((username.indexOf(specialChar) >-1)||(password.indexOf(specialChar) >-1)){
	   alert( "Invalid input for username or password");
	   return false;
   }
  
}


function validate()
{
 var username= document.myForm.username.value;
 var password=document.myForm.password.value;
 var specialChar= '=';
 var letterNumber = /^[0-9a-zA-Z]+$/; 
 alert("hi");
 if((username.match(letterNumber)))
  {  
	 alert( "Please alphanumeric value" );
     document.myForm.username.focus;
     return false; 
  }  
 //if username is empty
   if( username == "" )
   {
     alert( "Please provide the username" );
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
   if((username.indexOf(specialChar) >-1)||(password.indexOf(specialChar) >-1)){
	   alert( "Invalid input for username or password");
	   return false;
   }
  
}
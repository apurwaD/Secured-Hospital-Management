
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


function validate()
{
 var firstName= document.myForm4.firstName.value;
 var lastName=document.myForm4.lastName.value;
 var apartment= document.myForm4.apartment.value;
 var street=document.myForm4.street.value;
 var city= document.myForm4.city.value;
 var state=document.myForm4.state.value;
 var zipcode= document.myForm4.zipcode.value;
 
 
 var specialChar= '=';
 //if firstname is empty
   if( firstName == "" )
   {
     alert( "Please provide the firstname" );
     document.myForm4.firstName.focus;
     return false;
   }
   
   if (/[^a-zA-Z\s]/gi.test(firstName))
   {  
 	 alert( "Please enter alphabet value for lastName" );
      document.myForm4.firstName.focus;
      return false; 
   }
   
   
   
   //if lastname is empty
   if(lastName == "" )
   {
     alert( "Please provide the lastName" );
	 document.myForm4.lastName.focus;
     return false;
   }
   
   if (/[^a-zA-Z\s]/gi.test(lastName))
   {  
 	 alert( "Please enter alphabet value for lastName" );
      document.myForm4.lastName.focus;
      return false; 
   }
   
   
 //if apartment is empty
   if(apartment == "" )
   {
     alert( "Please provide the apartment" );
	 document.myForm4.apartment.focus;
     return false;
   }
   
 //if street is empty
   if(street == "" )
   {
     alert( "Please provide the street" );
	 document.myForm4.street.focus;
     return false;
   }
   
 //if city is empty
   if(city == "" )
   {
     alert( "Please provide the city" );
	 document.myForm4.city.focus;
     return false;
   }
   
   if (/[^a-zA-Z\s]/gi.test(city))
   {  
 	 alert( "Please enter alphabet value for city" );
      document.myForm4.city.focus;
      return false; 
   }
   
   
 //if state is empty
   if(state == "" )
   {
     alert( "Please provide the state" );
	 document.myForm4.state.focus;
     return false;
   }
   
   if (/[^a-zA-Z\s]/gi.test(state))
   {  
 	 alert( "Please enter alphabet value for state" );
      document.myForm4.state.focus;
      return false; 
   }
   
 //if zipcode is empty
   if(zipcode == "" )
   {
     alert( "Please provide the zipcode" );
	 document.myForm4.zipcode.focus;
     return false;
   }
   if(isNaN(zipcode)){
	   alert( "Please enter numeric value for zipcode" );
		 document.myForm4.zipcode.focus;
	     return false;
   }
   
   if(zipcode.length!=5){
	   alert( "The zipcode should have 5 numbers" );
		 document.myForm4.zipcode.focus;
	     return false;
   }
 
  
}
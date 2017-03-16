
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
 var firstName= document.myForm.firstName.value;
 var lastName=document.myForm.lastName.value;
 var dateFrom=document.myForm.dateFrom.value;
 var dateTo=document.myForm.dateTo.value;
 var treatmentDesc=document.myForm.treatmentDesc.value;
 var age=document.myForm.age.value;
 var bloodgroup=document.myForm.bloodGroup.value;
 var allergy=document.myForm.allergy.value;
 
 
 
 var specialChar= '=';
 //if firstname is empty
   if( firstName == "" )
   {
     alert( "Please provide the firstname" );
     document.myForm.firstName.focus;
     return false;
   }
   
   if (/[^a-zA-Z\s]/gi.test(firstName))
   {  
 	 alert( "Please enter alphabet value for firstName" );
      document.myForm.firstName.focus;
      return false; 
   }
   
   //if lastname is empty
   if(lastName == "" )
   {
     alert( "Please provide the lastName" );
	 document.myForm.lastName.focus;
     return false;
   }
   
   if (/[^a-zA-Z\s]/gi.test(lastName))
   {  
 	 alert( "Please enter alphabet value for lastName" );
      document.myForm.lastName.focus;
      return false; 
   }
   
 //if apartment is empty
   if(dateFrom == "" )
   {
     alert( "Please provide the dateFrom" );
	 document.myForm.dateFrom.focus;
     return false;
   }
   
 //if street is empty
   if(dateTo == "" )
   {
     alert( "Please provide value for dateTo" );
	 document.myForm.dateTo.focus;
     return false;
   }
   
 //if street is empty
   if(treatmentDesc == "" )
   {
     alert( "Please provide value for treatmentDesc" );
	 document.myForm.treatmentDesc.focus;
     return false;
   }
   
 //if city is empty
   if(age == "" )
   {
     alert( "Please provide the age" );
	 document.myForm.age.focus;
     return false;
   }
   
   if (isNaN(age)) {
		alert("Please enter numeric value for age");
		document.myForm.age.focus;
		return false;
	}
   
	if (age.length > 3) {
		alert("The age should atmost 2 numbers");
		document.myForm.age.focus;
		return false;
	}
   
 //if state is empty
   if(bloodgroup == "" )
   {
     alert( "Please provide the bloodgroup" );
	 document.myForm.bloodGroup.focus;
     return false;
   }
   
 //if zipcode is empty
   if(allergy == "" )
   {
     alert( "Please provide the allergy" );
	 document.myForm.allergy.focus;
     return false;
   }
  
  
}
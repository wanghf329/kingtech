$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	
	
      menuChecked("#personBorrowerList"); 
      
      $("#personnelFrom").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});



$("#addPersonnelBtn").click(function(){ 
	window.location.href = "borrower/personnel/edit";
});


$(".saveRecordBtn").click(function(){ 
	$("#personnelFrom").submit();
});

$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	
	
      menuChecked("#personBorrowerList"); 
      
      $("#form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});

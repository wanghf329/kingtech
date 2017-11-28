$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
      $('.datepicker').datepicker({autoclose: true });
      menuChecked("#capitalList"); 
      $("#form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});

$(".saveRecordBtn").click(function(){ 
	$("#form-horizontal").submit();
});
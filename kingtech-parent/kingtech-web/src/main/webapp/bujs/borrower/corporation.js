$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	
	
      menuChecked("#BuBorrowerList"); 
      
      $("#enterpriseFrom").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
      
      if(canEdit=="false"){ 
    	  $('#enterpriseFrom').find('input,textarea,select,button').attr('disabled',true); 
      }
});


$("#addEnterpriseBtn").click(function(){ 
	window.location.href = "borrower/enterprise/edit?id=";
});

function getEnterprise(id) {
	window.location.href = "borrower/enterprise/edit?id="+id;
}

$(".saveRecordBtn").click(function(){ 
	$("#enterpriseFrom").submit();
});




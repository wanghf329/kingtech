$(document).ready(function () {          
      menuChecked("#provisionInfo");
      
      $(".form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});

$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});


$("#editProvisiontBtn").click(function(){
	$(".form-horizontal")[0].reset();
	$("#editModel").modal();
});

$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});

$(".provision-edit").click(function(){ 
	var id = $(this).data("id"); 
	$.ajax({
		url:'postLoan/provision/detail/'+id, 
		type:'get',
		async:false,
		success:function(res){
			$("input[name='id']").val(res.id);
			$("#loanClassification option[value='"+res.loanClassification+"']").prop("selected",true);  
			$("#loanContractId").attr("disabled",true);   
			$("input[name='provisionMoney']").val(res.provisionMoney); 
			$("input[name='provisionDate']").val(res.provisionDate);
			$("input[name='provisionScale']").val(res.provisionScale);
			$("input[name='balance']").val(res.balance);
			$("#editModel").modal();
		}
	});
});
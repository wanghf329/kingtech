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
		url:'postLoan/extendrepay/detail/'+id, 
		type:'get',
		async:false,
		success:function(res){
			$("input[name='id']").val(res.id);
			$("#loanContractId option[value='"+res.loanContractId+"']").prop("selected",true);  
			$("#loanContractId").attr("disabled",true);   
			$("input[name='extendNum']").val(res.extendNum); 
			$("input[name='repayDate']").val(res.repayDate);
			$("input[name='repayAmount']").val(res.repayAmount);
			$("input[name='repayPrincipalAmount']").val(res.repayPrincipalAmount);
			$("input[name='repayInterestAmount']").val(res.repayInterestAmount);
			$("#editModel").modal();
		}
	});
});
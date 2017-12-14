$(document).ready(function () {          
    menuChecked("#extensionrepayplaninfo"); 
    
    $("#form-horizontal").validationEngine({ 
  	  validationEventTriggers:"keyup blur",
  	  inlineValidation: true,
  	  showOneMessage:true,
  	  success :  false,
  	  autoHidePrompt:true,
  	  failure : function() { callFailFunction()  } 
    })
    
    if(canEdit=="false"){ 
  	  $('#form-horizontal').find('input,textarea,select,button').attr('disabled',true); 
    }
});

$(".saveRecordBtn").click(function(){ 
	$("#form-horizontal").submit(); 
});

$("#editRepayExtendPlanBtn").click(function(){ 
	$("#form-horizontal")[0].reset();
	$("input[name='returnPrincipal']").val(0);
	$("input[name='returnInterest']").val(0);
	$("input[name='overdueDays']").val(0);
	$("#editModel").modal();
});

function getRepayExtendPlan(id){ 
	$.get('postLoan/get/extensionrepayplaninfo/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$(".loanContractId option[value='"+res.loanContractId+"']").attr("selected",true);
		$("input[name='extendCount']").val(res.extendCount); 
		$("input[name='extendTerm']").val(res.extendTerm);
		$("input[name='repayDate']").val(res.repayDate);
		$("input[name='principal']").val(res.principal);
		$("input[name='returnPrincipal']").val(res.returnPrincipal);
		$("input[name='interest']").val(res.interest);
		$("input[name='returnInterest']").val(res.returnInterest);
		$(".repayStatus option[value='"+res.status+"']").attr("selected",true);
		$(".overdueFlag option[value='"+res.overdueFlag+"']").attr("selected",true);
		$("input[name='overdueDays']").val(res.overdueDays);  
		$("#editModel").modal();
	});
}

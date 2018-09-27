$(document).ready(function () {          
      menuChecked("#assettransferinfo");
      
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


$("#editAssetTransferBtn").click(function(){
	$(".form-horizontal")[0].reset();
	$("#editModel").modal();
});

$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});

$(".assetTransfer-edit").click(function(){ 
	var id = $(this).data("id"); 
	$.ajax({
		url:'postLoan/assetTransfer/detail/'+id, 
		type:'get',
		async:false,
		success:function(res){
			$("input[name='id']").val(res.id);
			$("input[name='transferNumber']").val(res.transferNumber);
			$("input[name='transferMoney']").val(res.transferMoney); 
			$("input[name='originalMoney']").val(res.originalMoney);
			$("input[name='discountMoney']").val(res.discountMoney);
			$("input[name='acceptUnit']").val(res.acceptUnit); 
			$("input[name='protocol']").val(res.protocol);
			$("input[name='transferDate']").val(res.transferDate);
			$("#loanContractId option[value='"+res.loanContractId+"']").attr("selected",true);
			$("#editModel").modal();
		}
	});
});
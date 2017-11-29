$(document).ready(function () {          
      menuChecked("#shareholderList"); 
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

$("#editModelBtn").click(function(){ 
	$("#form-horizontal")[0].reset();
	$("#editModel").modal();
});


$("input[name='genderText']").click(function(){  
	alert();
	$("#gender").val($("input[name='genderText':checked]").val());  
});




function getShareHolder(id){ 
	$.get('branch/getShareholder/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='partnerType']").val(res.partnerType);
		$("input[name='holder']").val(res.holder); 
		$("input[name='holdingScale']").val(res.holdingScale);
		$("input[name='contributionAmount']").val(res.contributionAmount);
		$("input[name='joinTime']").val(res.joinTime);
		radioChecked('gender',res.gender);
		$("input[name='quitTime']").val(res.quitTime);  
		$("#editModel").modal();
	});
}

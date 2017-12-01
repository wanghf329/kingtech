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

$(".partnerType").on('change', function(){
	  if($(this).val()=="2"){ 
		  $("input[name='gender']").iCheck('uncheck');
		  $("#sexDiv").hide();
	  }else{
		  $("#sexDiv").show();
	  }
}); 



function getShareHolder(id){ 
	$.get('branch/getShareholder/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$(".partnerType option[value='"+res.partnerType+"']").attr("selected",true); 
		$("input[name='holder']").val(res.holder); 
		$("input[name='holdingScale']").val(res.holdingScale);
		$("input[name='contributionAmount']").val(res.contributionAmount);
		$("input[name='joinTime']").val(res.joinTime);
		$("input[name='gender'][value='"+res.gender+"']").iCheck('check');
		$("input[name='quitTime']").val(res.quitTime);  
		
		if($(".partnerType").val()=="2"){
			$("input[name='gender']").iCheck('uncheck');
			$("#sexDiv").hide();
		}else{
			$("#sexDiv").show();
		}
		$("#editModel").modal();
	});
}

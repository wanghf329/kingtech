$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	  $('.datepicker').datepicker({autoclose: true });
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
	reset();
	$("#editModel").modal();
});



function getShareHolder(id){ 
	$.get('branch/getShareholder/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='partnerType']").val(res.partnerType);
		$("input[name='holder']").val(res.holder);
		$("input[name='holdingScale']").val(res.holdingScale);
		$("input[name='contributionAmount']").val(res.contributionAmount);
		$("input[name='joinTime']").val(res.joinTime);
		$("input[name='gender']").val(res.gender);
		$("input[name='quitTime']").val(res.quitTime);  
		$("#editModel").modal();
	});
}

function reset(){
	$("input[name='id']").val("");
	$("input[name='partnerType']").val("1");
	$("input[name='holder']").val("");
	$("input[name='holdingScale']").val("");
	$("input[name='contributionAmount']").val("");
	$("input[name='joinTime']").val("");
	$("input[name='gender']").val("1");
	$("input[name='quitTime']").val("");  
}
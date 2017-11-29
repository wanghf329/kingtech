$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	
	
      menuChecked("#loanList"); 
      
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

$("#addContractBtn").click(function(){ 
	window.location.href = "loan/edit";
});

$("#addEnterPriseBtn").click(function(){ 
	window.location.href = "loan/enterprise/edit";
});


function getCapital(id){
	$.get('branch/getCapital/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='financingChannel']").val(res.financingChannel);
		$("input[name='financingMoney']").val(res.financingMoney);
		$("input[name='financingTime']").val(res.financingTime);
		$("input[name='expirationTime']").val(res.expirationTime);
		$("input[name='replyTime']").val(res.replyTime);
		$("#addCapitalModel").modal();
	});
}

function delConfirm(id){
	$("#confirmModel").modal();
	$("#del-id").val(id); 
}

function delCapital(){
	window.location.href = "branch/delCapital/"+$("#del-id").val();
};

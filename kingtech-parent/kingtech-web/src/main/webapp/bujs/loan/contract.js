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

$("#editContractBtn").click(function(){ 
	window.location.href = "loan/edit?id=";
});

$("#loanSupplementBtn").click(function(e){ 
	window.location.href = "loan/supplement/" + e.loanContractId;
});



function addCustomer(type,constractId) {
	if(type == 'S_1') {
		window.location.href = "loan/enterprise/edit";
	}else if(type == 'S_0'){
		window.location.href = "loan/personnel/edit";
	}else {
		alert("请选择添加用户类型");
	}
		
}


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

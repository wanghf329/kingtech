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

$("#borrowerType").change(function(){
	var html = "";
	var type = $(this).val();
	if(type=="S_1"){
		$.get("borrower/list/"+type,null,function(res){
			for(var o in res){
				html += "<option value='"+o.id+"'>'"+o.corporateName+"'</option>"
			}
		});
	}else{
		$.get("borrower/list/"+type,null,function(res){
			for(var o in res){
				html += "<option value='"+o.id+"'>'"+o.name+"'</option>"
			}
		});
	} 
	$("#borrowerId").html(html);
});


$("#loanSupplementBtn").click(function(e){ 
	window.location.href = "loan/supplement?loanContractId=2121";
});

$("input[name='pledgeType'][value='S_1']").on("ifChecked", function(event){
	$("#collateralType1").css("display","");
	$("#collateralType2").css("display","none");
	$("#collateralType2").val("");
});
$("input[name='pledgeType'][value='S_2']").on("ifChecked", function(event){
	$("#collateralType2").css("display","");
	$("#collateralType1").css("display","none");
	$("#collateralType1").val("");
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

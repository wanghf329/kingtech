$(document).ready(function () {          
      menuChecked("#repayinfo");
      
      $('.datepicker').datetimepicker({
    		minView: "2", //选择日期后，不会再跳转去选择时分秒 
    	    language:  'zh-CN',
    	    format: 'yyyy-mm-dd',
    	    todayBtn:  1,
    	    autoclose: 1,});
      
      $("#addRepayInfoForm").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() {   } 
      })
      
});

function validataPrincipal(field, rules, i, options) {
	var value = $("#repayAmount").val();  
	var principal = $("#repayPrincipalAmount").val();
    if(value ==""){  
        return "还款金额不能为空";  
     }  
    if(principal == "" || principal == null) {
    	return "";
    }
    if(isNaN(principal)) {
   	 return "还款金额必须为数字";
   }
    if(principal > value) {
    	 return "还款本金不能大于还款金额";  
    }
}

function validataInterest(field, rules, i, options) {
	var value = $("#repayAmount").val();  
	var principal = $("#repayPrincipalAmount").val();
	var interest = $("#repayInterestAmount").val();
	console.log(interest);
    if(value ==""){  
        return "还款金额不能为空";  
     }  
    if(interest == "" || interest == null) {
    	return "";
    }
    if(isNaN(interest)) {
    	 return "还款利息必须为数字";
    }
    
    if(interest != ( value - principal) ) {
    	 return "还款利息必须等于还款金额-还款本金";  
    }
}


function getRepayInfo(id){
	$.get('postLoan/getRepayInfo/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='repayDate']").val(res.repayDate);
		$("input[name='repayAmount']").val(res.repayAmount);
		$("input[name='repayPrincipalAmount']").val(res.repayPrincipalAmount);
		$("input[name='repayInterestAmount']").val(res.repayInterestAmount);
		$("#loanContractId option[value='"+res.loanContractId+"']").attr("selected",true); 
		$("#newRepayInfo").modal();
	});
}


$("#addRepayInfoBtn").click(function(){ 
	$("#addRepayInfoForm")[0].reset();
	$("#newRepayInfo").modal();
});



$(".saveRecordBtn").click(function(){ 
	$("#addRepayInfoForm").submit();
});



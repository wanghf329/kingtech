$(document).ready(function () {          
      menuChecked("#baddebtsinfo");
      
      $('.datepicker').datetimepicker({
  		minView: "2", //选择日期后，不会再跳转去选择时分秒 
  	    language:  'zh-CN',
  	    format: 'yyyy-mm-dd',
  	    todayBtn:  1,
  	    autoclose: 1,});
    
    $("#addbadDebtsInfoForm").validationEngine({ 
  	  validationEventTriggers:"keyup blur",
  	  inlineValidation: true,
  	  showOneMessage:true,
  	  success :  false,
  	  autoHidePrompt:true,
  	  failure : function() {   } 
    })
});

function getBaddebtInfo(id){
	$.get('postLoan/getBaddebtsInfo/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='badMoney']").val(res.badMoney);
		$("input[name='lossDate']").val(res.lossDate);
		$("#badType option[value='"+res.badType+"']").prop("selected",true); 
		$("textarea[name='followUp']").val(res.followUp);
		$("#loanContractId option[value='"+res.loanContractId+"']").attr("selected",true); 
		$("#badDebtsInfo").modal();
	});
}


$("#addbaddebtInfoBtn").click(function(){ 
	$("#addbadDebtsInfoForm")[0].reset();
	$("#badDebtsInfo").modal();
});



$(".saveRecordBtn").click(function(){ 
	$("#addbadDebtsInfoForm").submit();
});

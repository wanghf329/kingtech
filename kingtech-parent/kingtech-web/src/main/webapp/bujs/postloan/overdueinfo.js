$(document).ready(function () {          
      menuChecked("#overdueinfo");
      $('[data-toggle="popover"]').popover();
      $('.datepicker').datetimepicker({
  		minView: "2", //选择日期后，不会再跳转去选择时分秒 
  	    language:  'zh-CN',
  	    format: 'yyyy-mm-dd',
  	    todayBtn:  1,
  	    autoclose: 1,});
    
    $("#addOverdueinfoForm").validationEngine({ 
  	  validationEventTriggers:"keyup blur",
  	  inlineValidation: true,
  	  showOneMessage:true,
  	  success :  false,
  	  autoHidePrompt:true,
  	  failure : function() {  } 
    })
});

function getOverdueInfo(id){
	$.get('postLoan/getOverdueInfo/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='overdueMoney']").val(res.overdueMoney);
		$("input[name='overdueDate']").val(res.overdueDate);
		$("input[name='overdueInterest']").val(res.overdueInterest);
		$("input[name='balance']").val(res.balance);
		$("textarea[name='remarks']").val(res.remarks);
		$("#loanContractId option[value='"+res.loanContractId+"']").attr("selected",true); 
		$("#newOverdueinfo").modal();
	});
}


$("#addNewOverdueinfo").click(function(){ 
	$("#addOverdueinfoForm")[0].reset();
	$("#newOverdueinfo").modal();
});



$(".saveRecordBtn").click(function(){ 
	$("#addOverdueinfoForm").submit();
});
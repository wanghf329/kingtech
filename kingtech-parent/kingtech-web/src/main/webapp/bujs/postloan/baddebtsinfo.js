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
		
		if(res != null && res.pushStatus != 'INITATION'){
			$('.form-horizontal').find('input,textarea,select').attr('disabled',true); 
			$('.saveRecordBtn').hide();
		}
		$("#badDebtsInfo").modal();
	});
}

function deleteBaddebtInfo(id){
	swal({
		title : "确定删除吗？",
		text : "删除需要等待金融办确认,确认后数据将删除！",   
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "确认删除",
		cancelButtonText : "取消删除", 
		closeOnConfirm : false,
		closeOnCancel : true 
	}, function() {  
		$.ajax({
			url:"postLoan/delete/baddebtsInfo/"+id,
			type:'get',
			async: false,
			success:function(res){
				if(res==null){
					swal("删除！", "删除失败。", "error"); 
				}else{
					if(res.resultCode=='0'){ 
						swal("删除！", "删除成功。", "success"); 
						window.location.href = "postLoan/baddebtsinfo";  
					}else{
						swal("删除失败！", res.resultMsg, "error"); 
					}
				}
			}
		});
	});
}

$("#addbaddebtInfoBtn").click(function(){ 
	$("#addbadDebtsInfoForm")[0].reset();
	$("#badDebtsInfo").modal();
});

$(".saveRecordBtn").click(function(){ 
	$("#addbadDebtsInfoForm").submit();
});

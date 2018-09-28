$(document).ready(function () {          
      menuChecked("#assettransferinfo");
      
      $(".form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});

$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});


$("#editAssetTransferBtn").click(function(){
	$(".form-horizontal")[0].reset();
	$("#editModel").modal();
});

$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});

$(".assetTransfer-edit").click(function(){ 
	var id = $(this).data("id"); 
	$.ajax({
		url:'postLoan/assetTransfer/detail/'+id, 
		type:'get',
		async:false,
		success:function(res){
			$("input[name='id']").val(res.id);
			$("input[name='transferNumber']").val(res.transferNumber);
			$("input[name='transferMoney']").val(res.transferMoney); 
			$("input[name='originalMoney']").val(res.originalMoney);
			$("input[name='discountMoney']").val(res.discountMoney);
			$("input[name='acceptUnit']").val(res.acceptUnit); 
			$("input[name='protocol']").val(res.protocol);
			$("input[name='transferDate']").val(res.transferDate);
			$("#loanContractId option[value='"+res.loanContractId+"']").attr("selected",true);
			
			if(res != null && res.pushStatus != 'INITATION'){
				$('.form-horizontal').find('input,textarea,select').attr('disabled',true); 
				$('.saveRecordBtn').hide();
			}
			$("#editModel").modal();
		}
	});
});

$(".assetTransfer-delete").click(function(){ 
	var id = $(this).data("id");
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
			url:"postLoan/assetTransfer/delete/"+id,
			type:'get',
			async: false,
			success:function(res){
				if(res==null){
					swal("删除！", "删除失败。", "error"); 
				}else{
					if(res.resultCode=='0'){ 
						swal("删除！", "删除成功。", "success"); 
						window.location.href = "postLoan/assetTransferInfo";  
					}else{
						swal("删除失败！", res.resultMsg, "error"); 
					}
				}
			}
		});
	});
});
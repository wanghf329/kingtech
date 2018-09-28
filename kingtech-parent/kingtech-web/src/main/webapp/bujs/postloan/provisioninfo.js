$(document).ready(function () {          
      menuChecked("#provisionInfo");
      
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


$("#editProvisiontBtn").click(function(){
	$(".form-horizontal")[0].reset();
	$("#editModel").modal();
});

$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});

$(".provision-edit").click(function(){ 
	var id = $(this).data("id"); 
	$.ajax({
		url:'postLoan/provision/detail/'+id, 
		type:'get',
		async:false,
		success:function(res){
			$("input[name='id']").val(res.id);
			$("input[name='dateMonth']").val(res.dateMonth);
			$("input[name='normalBalance']").val(res.normalBalance); 
			$("input[name='normalRate']").val(res.normalRate);
			$("input[name='normalReal']").val(res.normalReal);
			$("input[name='followBalance']").val(res.followBalance); 
			$("input[name='followRate']").val(res.followRate);
			$("input[name='followReal']").val(res.followReal);
			$("input[name='minorBalance']").val(res.minorBalance); 
			$("input[name='minorRate']").val(res.minorRate);
			$("input[name='minorReal']").val(res.minorReal);
			$("input[name='suspiciousBalance']").val(res.suspiciousBalance); 
			$("input[name='suspiciousRate']").val(res.suspiciousRate);
			$("input[name='suspiciousReal']").val(res.suspiciousReal);
			$("input[name='lossBalance']").val(res.lossBalance); 
			$("input[name='lossRate']").val(res.lossRate);
			$("input[name='lossReal']").val(res.lossReal);
			
			if(res != null && res.pushStatus != 'INITATION'){
				$('.form-horizontal').find('input,textarea,select').attr('disabled',true); 
				$('.saveRecordBtn').hide();
			}
			$("#editModel").modal();
		}
	});
});

$(".provision-delete").click(function(){ 
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
			url:"postLoan/provision/delete/"+id,
			type:'get',
			async: false,
			success:function(res){
				if(res==null){
					swal("删除！", "删除失败。", "error"); 
				}else{
					if(res.resultCode=='0'){ 
						swal("删除！", "删除成功。", "success"); 
						window.location.href = "postLoan/provisioninfo";  
					}else{
						swal("删除失败！", res.resultMsg, "error"); 
					}
				}
			}
		});
	});
});

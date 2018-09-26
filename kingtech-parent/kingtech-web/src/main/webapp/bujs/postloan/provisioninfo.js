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
			$("#editModel").modal();
		}
	});
});
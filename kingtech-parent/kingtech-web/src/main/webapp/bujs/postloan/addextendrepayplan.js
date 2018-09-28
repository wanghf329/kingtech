$(document).ready(function() {
	menuChecked("#addextensionrepayplaninfo");

	$("#form-horizontal").validationEngine({
		validationEventTriggers : "keyup blur",
		inlineValidation : true,
		showOneMessage : true,
		success : false,
		autoHidePrompt : true,
		failure : function() {
			callFailFunction()
		}
	})
	
	 if(typeof(canEdit) != "undefined" && canEdit=="false"){  
   	  $('.form-horizontal').find('input,textarea,select').attr('disabled',true); 
   	  $('.form-horizontal').find("button[type='submit']").hide();
   	  $('body').find(".edit-href").hide(); 
     } 
	
	function initDatepicker(){
		$('.datepicker').datetimepicker({
			minView: "2", //选择日期后，不会再跳转去选择时分秒 
		    language:  'zh-CN',
		    format: 'yyyy-mm-dd',
		    todayBtn:  1,  
		    autoclose: 1, 
		    clearBtn: true});   
	}

});

$(".addRepayPlanBtn").on("click",function(){ 
	var clone = $(".repayPlanTemplate").clone().removeClass("repayPlanTemplate hide").addClass("repayPlan");
	$(".repayPlanTemplate").parent().append(clone);  
	bindDel("delRepayPlanBtn"); 
}); 

$(".addSettledInfoBtn").on("click",function(){
	var clone = $(".settledInfoTemplate").clone().removeClass("settledInfoTemplate hide").addClass("repayPlan");
	$(".settledInfoTemplate").parent().append(clone);  
	bindDel("delSettledInfoBtn");
});


$(".delSettledInfoBtn,.delRepayPlanBtn,.delCollateralBtn,.delGuaranteeBtn").on("click",function(){
	$(this).closest(".repayPlan").remove();
});

function bindDel(btn){
	$("."+btn).on("click",function(){
		$(this).closest(".repayPlan").remove();
	});
	initDatepicker();
}

function initDatepicker(){
	$('.datepicker').datetimepicker({
		minView: "2", //选择日期后，不会再跳转去选择时分秒 
	    language:  'zh-CN',
	    format: 'yyyy-mm-dd',
	    todayBtn:  1,  
	    autoclose: 1, 
	    clearBtn: true});   
}
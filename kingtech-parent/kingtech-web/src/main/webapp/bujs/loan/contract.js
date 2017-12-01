$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	
      menuChecked("#loanList");
      getBorrower(); 
      
      $(".form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
      
      if(canEdit === undefined && canEdit=="false"){
    	  $('#form-horizontal').find('input,textarea,select,button').attr('disabled',true);
      } 
});

$(".saveRecordBtn").click(function(){ 
	$("#form-horizontal").submit();
});

$("#editContractBtn").click(function(){ 
	window.location.href = "loan/edit?id=";
});

$("#borrowerType").change(function(){
	getBorrower();
});

function getBorrower(){
	var html = "";
	$("#borrowerId").empty(); 
	var type = $("#borrowerType").val();  
	$.ajax({
		url:"borrower/list/"+type,
		async:false,
		method:'get',
		success:function(res){
			if(type=="S_1"){
				for(var i in res){
					html += "<option value='"+res[i].id+"'>"+res[i].corporateName+"</option>"
				}
			}else{
				for(var i in res){
					html += "<option value='"+res[i].id+"'>"+res[i].name+"</option>" 
				}
			}
			$("#borrowerId").html(html); 
			$("#borrowerId option[value='"+borrowerId+"']").attr("selected",true);
		}
	});
}

$("select[name='pledgeType']").on("change", function(event){
	if($(this).val()=='S_1'){
		
	}     
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



$(".addGuaranteeBtn").on("click",function(){
	var clone = $(".guaranteeTemplate").clone().removeClass("guaranteeTemplate hide").addClass("repayPlan");
	$(".guaranteeTemplate").parent().append(clone);   
	bindDel("delGuaranteeBtn");
});

$(".addCollateralBtn").on("click",function(){
	var clone = $(".collateralTemplate").clone().removeClass("collateralTemplate hide").addClass("repayPlan");
	$(".collateralTemplate").parent().append(clone);   
	bindDel("delCollateralBtn");
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

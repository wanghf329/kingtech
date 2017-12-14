$(document).ready(function () {          
      menuChecked("#extensionrepayinfo");
      
      $(".form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
      initDataTables();
      var td;
});

$("#editExtendRepayPlanBtn").click(function(){
	$("#loanContractId").removeAttr("disabled");
	$(".form-horizontal")[0].reset();
	$("#editModel").modal();
});

$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});


$(".extend-repay-edit").click(function(){ 
	var id = $(this).data("id"); 
	$.ajax({
		url:'postLoan/extendrepay/detail/'+id, 
		type:'get',
		async:false,
		success:function(res){
			$("input[name='id']").val(res.id);
			$("#loanContractId option[value='"+res.loanContractId+"']").prop("selected",true);  
			$("#loanContractId").attr("disabled",true);   
			$("input[name='extendNum']").val(res.extendNum); 
			$("input[name='repayDate']").val(res.repayDate);
			$("input[name='repayAmount']").val(res.repayAmount);
			$("input[name='repayPrincipalAmount']").val(res.repayPrincipalAmount);
			$("input[name='repayInterestAmount']").val(res.repayInterestAmount);
			$("#editModel").modal();
		}
	});
});
function initDataTables() {
	this.dt = $("#extRepayInfo").dataTable({
		searching : false, // 禁用dataTables自带的查询框
		ordering : false, // 禁用排序
		processing: true,//加载特效
		ajax : {
			type : "POST",
			"url" : "postLoan/extensionrepayinfo/data",
			data: function(d) {
			}
		},
		columns :  [
		            {data : null},
		            {data : "loanContractNo"},
		            {data : "loanContractName"},
		            {data : "extendNum"},
		            {data : "repayDate"},
		            {data : "repayAmount", render:function(data, type, row){
		            	return return "<span class=\"red bolder\">￥"+ data +"</span>";  
		            	
		            }},
		            {data : "repayPrincipalAmount", render:function(data, type, row){
		            	return return "<span class=\"red bolder\">￥"+ data +"</span>";  
		            	
		            }},
		            {data : "repayInterestAmount", render:function(data, type, row){
		            	return return "<span class=\"red bolder\">￥"+ data +"</span>";  
		            }},
		            {data : "pushStatus", render:function(data, type, row){
		            	switch(data) {
		            	case 'INITATION': 
		            		return '<span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span>';
		            	case 'SUCCESS': 
		            		return '<span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span>';
		            	case 'INPROSESS': 
		            		return '<span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span>';
		            	case 'FAILED': 
		            		return '<span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span>';
		            	
		            	}
		            }},
		            {data : null, render:function(data, type, row){
		            	return '<a href="javascript:void(0)" data-id="'+row.id+'" class="extend-repay-edit"><i class="text-blue fa fa-edit"></i><strong>修改</strong>'
		            }}
		            ],
        "fnDrawCallback" : function(oSettings) {
        	for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
        		$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(
        				oSettings['_iDisplayStart'] + i + 1);
        	}

        	$(".modalBg").hide();
        },
		});
		            
		            
		            
};

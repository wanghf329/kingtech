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
      initDataTables();
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

function initDataTables() {
	this.dt = $("#repayInfo")
			.DataTable(
					{
						language : dataTableLang, // 提示信息
						autoWidth : false, // 禁用自动调整列宽
						processing : true, // 隐藏加载提示,自行处理
						ordering : false, // 禁用排序
						serverSide : true, // 启用服务器端分页
						searching : false, // 禁用原生搜索
						pagingType : "simple_numbers", // 分页样式：simple,simple_numbers,full,full_numbers
						ajax : function(data, callback, settings) {
							// 封装请求参数
							var param = {};
							param.length = data.length;// 页面显示记录条数，在页面显示每页显示多少项的时候
							param.start = data.start;// 开始的记录序号
							console.log(data);
							// ajax请求数据
							$.ajax({
									type : "POST",
									url : "postLoan/extensionrepayinfo/data",
									cache : false, // 禁用缓存
									data : param, // 传入组装的参数
									dataType : "json",
									success : function(result) {
										setTimeout(
												function() {
													// 封装返回数据
													var returnData = {};
													returnData.recordsTotal = result.totalSize;// 返回数据全部记录
													returnData.recordsFiltered = result.totalSize;// 后台不实现过滤功能，每次查询均视作全部结果
													returnData.data = result.results;// 返回的数据列表
													console.log(returnData);
													// 调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
													// 此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
													callback(returnData);
												}, 200);
									}
								});
						},
						columns : [
								{
									data : null
								},
								{
									data : "loanContractNo"
								},
								{
									data : "loanContractName"
								},
								{
									data : "extendNum"
								},
								{
									data : "repayDate"
								},
								{
									data : "repayAmount",
									render : function(data, type, row) {
										return "<span class=\"text-red bolder\">￥"
												+ data + "</span>";

									}
								},
								{
									data : "repayPrincipalAmount",
									render : function(data, type, row) {
										return "<span class=\"text-red bolder\">￥"
												+ data + "</span>";

									}
								},
								{
									data : "repayInterestAmount",
									render : function(data, type, row) {
										return "<span class=\"text-red bolder\">￥"
												+ data + "</span>";
									}
								},
								{
									data : "pushStatus",
									render : function(data, type, row) {
										switch (data) {
										case 'INITATION':
											return '<span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span>';
										case 'SUCCESS':
											return '<span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span>';
										case 'INPROSESS':
											return '<span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span>';
										case 'FAILED':
											return '<span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span>';

										}
									}
								},
								{
									data : null,
									render : function(data, type, row) {
										return '<a href="javascript:void(0)" onclick=getRepayInfo('+row.id+') class="extend-repay-edit"><i class="text-blue fa fa-edit"></i><strong>修改</strong>'
									}
								} ],
						"fnDrawCallback" : function(oSettings) {
							for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
								$(
										'td:eq(0)',
										oSettings.aoData[oSettings.aiDisplay[i]].nTr)
										.html(
												oSettings['_iDisplayStart'] + i
														+ 1);
							}
						}
					});
};

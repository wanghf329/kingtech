$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
      menuChecked("#financeCapitalList");
      initDataTables() ;
      $(".form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
      
      if(typeof(canEdit) != "undefined" && canEdit=="false"){  
    	  $('.form-horizontal').find('input,textarea,select').attr('disabled',true); 
    	  $('.form-horizontal').find("button[type='submit']").hide();
    	  $('body').find(".edit-href").hide(); 
      } 
});

function formateDate(date, fmt) {
    // Examples: 
    // formatDate(new Date(), "yyyy-MM-dd hh:mm:ss.S") ==> 2013-08-06 08:09:04.423 
    // formatDate(new Date(), "yyyy-M-d h:m:s.S")      ==> 2013-8-6 8:9:4.18 
    // formatDate(new Date(), "yyyy年M月d日")           ==> 2013年8月6日 
    var o = {
        "M+": date.getMonth() + 1, //月份 
        "d+": date.getDate(), //日 
        "h+": date.getHours(), //小时 
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒 
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度 
        "S": date.getMilliseconds()                     //毫秒 
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
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

$("#addCapitalBtn").click(function () {
	window.location.href = "finance/capital/edit?id=";
})

$(".saveRecordBtn").click(function () {
	$("#form-horizontal").submit();
})

function loanContractNumberValidate (field, rules, i, options) {
	console.log("aaaa")
	var _channel = $("#channel").val()
	var _loanContractNumber = $("#loanContractNumber").val().trim();
	console.log(_channel  + _loanContractNumber) ;
	if("S_3" == _channel) {
		if("" == _loanContractNumber || null == _loanContractNumber) {
			console.log(_channel  + _loanContractNumber ) ;
			rules.push('required');
			return;
		}
	}
}
function guaranteeMoneyValidate (field, rules, i, options) {
	var _channel = $("#channel").val()
	var _guaranteeMoney = $("#guaranteeMoney").val().trim();
	if("S_3" == _channel) {
		if("" == _guaranteeMoney || null == _guaranteeMoney) {
			rules.push('required');
			return;
		}
	}
}

$(".addRepayPlanBtn").on("click",function(){ 
	var clone = $(".repayPlanTemplate").clone().removeClass("repayPlanTemplate hide").addClass("repayPlan");
	$(".repayPlanTemplate").parent().append(clone);  
	bindDel("delSettledInfoBtn");
}); 

function bindDel(btn){
	$("."+btn).on("click",function(){
		$(this).closest(".repayPlan").remove();
	});
	initDatepicker();
}

$(".delRepayPlanBtn").on("click",function(){
	$(this).closest(".repayPlan").remove();
});

function initDataTables() {
	this.dt = $("#capitalListTab").DataTable({
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
							$.ajax({type : "GET",
									url : "finance/capitalList/data",
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
								{data : null},
								{data : "financeNumber"},
								{data : "financeName"},
								{data : "lender"},
								{data : "channel",render : function(data, type, row) {
									switch (data) {
										case 'S_1':
											return '<span class="text-gray">金融机构融资</span>';
										case 'S_2':
											return '<span class="text-gray">股东借款</span>';
										case 'S_3':
											return '<span class="text-gray">资产转让融资</span>';
										case 'S_4':
											return '<span class="text-gray">其他</span>';
									}
								}},
								{data : "money",render : function(data, type, row) {
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},
								{data : "interest",render : function(data, type, row) {
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},
								{data : "charge",render : function(data, type, row) {
									
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},
								{data : "guaranteeMoney",render : function(data, type, row) {
									if(data == null ) {
										return "";
									}
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},
								{data : "financeDate",render : function(data, type, row) {
									return formateDate(new Date(data),"yyyy-MM-dd")
									
								 }},
								 {data : "endDate",render : function(data, type, row) {
										return formateDate(new Date(data),"yyyy-MM-dd")
								 }},
								{data : "rate",render : function(data, type, row) {
									return '<Strong>'+data+'%</Strong></td>' ;   
								}},
								
								{data : "rateType",render : function(data, type, row) {
									switch (data) {
										case 'S_1':
											return '<Strong>日</Strong></td>';
										case 'S_2':
											return '<Strong>周</Strong></td>';
										case 'S_3':
											return '<Strong>月</Strong></td>';
										case 'S_4':
											return '<Strong>季度</Strong></td>';
										case 'S_5':
											return '<Strong>年</Strong></td>';
									}
								}},
								{data : "loanContractNumber"},
								{data : "pushStatus",render : function(data, type, row) {
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
								}},
								{data : null,render : function(data, type, row) {
									if (row.pushStatus=='INITATION' ){
										return '<a href="finance/capital/edit?id='+row.id+'"><strong><i class="text-blue fa fa-edit"></i>修改详情</strong></a>'
										+ '<a href="finance/capital/supplement?financeId='+row.id+'"><strong><i class="text-blue fa fa-plus-square-o"></i>补充信息</strong></a>'
										+ '<a href="javascirpt:void(0)" class="capital-push" data-id="'+row.id+'"><i class="text-blue fa fa-exchange"></i><strong>推送</strong></a>';
									} else {
										return '<a href="finance/capital/edit?id='+row.id+'"><strong><i class="text-gray fa fa-eye"></i>查看详情</strong></a> <a href="finance/capital/supplement?financeId='+row.id+'"><strong>查看补充信息</strong></a>'
									}
									
								}} ],
						"fnDrawCallback" : function(oSettings) {
							for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
								$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(oSettings['_iDisplayStart'] + i+ 1);
							}
							$('.capital-push').on("click",function(){
								var id = $(this).data("id");
								swal({
									title : "确定推送吗？",
									text : "推送前确认数据无误，推送后将无法更改！",
									type : "warning",
									showCancelButton : true,
									confirmButtonColor : "#DD6B55",
									confirmButtonText : "确认推送",
									cancelButtonText : "取消推送",
									closeOnConfirm : false,
									closeOnCancel : true 
								}, function() {  
									$.ajax({
										url:"finance/capital/push/"+id,
										type:'get',
										async: false,
										success:function(res){
											if(res==null){
												swal("推送！", "推送失败。", "error"); 
											}else{
												if(res.resultCode=='0'){
													swal("推送！", "推送成功。", "success"); 
													window.location.href = "finance/capitalList"; 
												}else{
													swal("推送失败！", res.resultMsg, "error"); 
												}
											}
										}
									});
								});
							})
						}
					});
};





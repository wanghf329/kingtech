$(document).ready(function () {          
      menuChecked("#dayendList");
      
      $('.datepicker').datetimepicker({
    		minView: "2", //选择日期后，不会再跳转去选择时分秒 
    	    language:  'zh-CN',
    	    format: 'yyyy-mm-dd',
    	    todayBtn:  1,
    	    autoclose: 1,});
      
      $(".form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() {   } 
      })
      initDataTables();
});


function getRepayInfo(id){
	$.get('postLoan/getRepayInfo/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='repayTime']").val(formateDate(new Date(res.repayTime),'yyyy-MM-dd'));
		$("input[name='money']").val(res.money);
		$("input[name='penaltyInterest']").val(res.penaltyInterest);
		$("input[name='interest']").val(res.interest);
		$("input[name='penalty']").val(res.penalty);
		$("input[name='serviceCharge']").val(res.serviceCharge);
		$("input[name='otherCharge']").val(res.otherCharge);
		$("#loanContractId option[value='"+res.loanContractId+"']").attr("selected",true); 
		$("#newRepayInfo").modal();
		
		if(res.pushStatus!='INITATION'){ 
	    	$('.saveRecordBtn').hide(); 
		}else{ 
			$('.saveRecordBtn').show();
		}
	});
}


$("#addDayendInfoBtn").click(function(){ 
	$("#addRepayInfoForm")[0].reset();
	$("#newDayendInfo").modal();
});



$(".saveRecordBtn").click(function(){ 
	$(".form-horizontal").submit();
});

function initDataTables() {
	this.dt = $("#dayendInfo").DataTable({
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
							$.ajax({type : "POST",
									url : "dayend/list/data",
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
								{data : "checkDate",render:function(data, type, row){
									return formateDate(new Date(data),'yyyy-MM-dd');
								}},
								{data : "dayCount",render : function(data, type, row) {
									return data;
								}},	
								{data : "dayMoney",render : function(data, type, row) {
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},	
								{data : "dayLoan",render : function(data, type, row) {
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},	
								{data : "dayRepay",render : function(data, type, row) {
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},			
								{data : "loanBalance",render : function(data, type, row) {
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},								
								{data : "loanMoney",render : function(data, type, row) {
									return "<span class=\"text-red bolder\">￥"+ data + "</span>";
								}},	
								{data : "loanCount"},
								{data : "pushStatus",render : function(data, type, row) {
										switch (data) {
											case 'INITATION':
												return '<span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span>';
											case 'SUCCESS':
												return '<span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span>';
											case 'DELETEING': 
												return '<span class="text-blue"><i class="text-green fa fa-asterisk"></i>删除处理中</span>';
											case 'INPROSESS':
												return '<span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span>';
											case 'FAILED':
												return '<span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span>';
										}
									}
								}],
						"fnDrawCallback" : function(oSettings) {
							for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
								$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(oSettings['_iDisplayStart'] + i+ 1);
							}
							
							$('.item-delete').on("click",function(){
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
										url:"postLoan/repay/delete/"+id,
										type:'get',
										async: false,
										success:function(res){
											if(res==null){
												swal("删除！", "删除失败。", "error"); 
											}else{
												if(res.resultCode=='0'){ 
													swal("删除！", "删除成功。", "success"); 
													window.location.href = "postLoan/repayinfo";  
												}else{
													swal("删除失败！", res.resultMsg, "error"); 
												}
											}
										}
									});
								});
							});							
						}
					});
};


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

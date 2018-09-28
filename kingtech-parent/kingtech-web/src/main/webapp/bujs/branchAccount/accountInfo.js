$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
      menuChecked("#branchAccountInfoList");
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

$("#addAccountInfoBtn").click(function () {
	window.location.href = "branchAccount/accountInfo/edit?id=";
})

$(".saveRecordBtn").click(function () {
	$("#form-horizontal").submit();
})



function initDataTables() {
	this.dt = $("#accountInfoListTab").DataTable({
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
							// ajax请求数据
							$.ajax({type : "GET",
									url : "branchAccount/accountInfoList/data",
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
								{data : "bank"},
								{data : "account"},
								{data : "type",render : function(data, type, row) {
									switch (data) {
										case 'S_1':
											return '<span class="text-gray">账户类型</span>';
										case 'S_2':
											return '<span class="text-gray">一般户</span>';
										case 'S_3':
											return '<span class="text-gray">监管专用户</span>';
									}
								}},
								{data : "accountStatus",render : function(data, type, row) {
									switch (data) {
										case 'normal':
											return '<span class="text-gray">正常</span>';
										case 'loss':
											return '<span class="text-gray">挂失</span>';
										case 'lock':
											return '<span class="text-gray">锁定</span>';
										case 'pledge':
											return '<span class="text-gray">质押</span>';
										case 'cancel':
											return '<span class="text-gray">销户</span>';
										case 'other':
											return '<span class="text-gray">其它</span>';
									}
								}},
								{data : "openTime",render : function(data, type, row) {
									return formateDate(new Date(data),"yyyy-MM-dd")
									
								 }},
								{data : "pushStatus",render : function(data, type, row) {
									switch (data) {
									case 'INITATION':
										return '<span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span>';
									case 'SUCCESS':
										return '<span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span>';
									case 'INPROSESS':
										return '<span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span>';
									case 'DELETEING':
										return '<span class="text-blue"><i class="text-blue fa fa-asterisk"></i>删除处理中</span>';
									case 'FAILED':
										return '<span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span>';
									}
									return data;
								}},
								{data : null,render : function(data, type, row) {
									if(row.pushStatus=='SUCCESS' || row.pushStatus=='INPROSESS') {
										return '<a href="branchAccount/accountInfo/edit?id='+row.id+'"><strong>查看详情</strong></a> '
									}
									if(row.pushStatus=='INITATION' || row.pushStatus=='FAILED') {
										return '<a href="branchAccount/accountInfo/edit?id='+row.id+'"><i class="text-blue fa fa-edit"></i><strong>修改</strong></a>'
										 +'<a href="javascirpt:void(0)" class="accountInfo-delete" data-id="'+row.id+'"><i class="text-red fa fa-edit"></i><strong>删除</strong>';
									}
								}} ],
						"fnDrawCallback" : function(oSettings) {
							for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
								$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(oSettings['_iDisplayStart'] + i+ 1);
								$('.accountInfo-delete').on("click",function(){
									var id = $(this).data("id");
									swal({
										title : "确定删除吗？",
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
											url:"branchAccount/accountInfo/delete/"+id,
											type:'get',
											async: false,
											success:function(res){
												if(res==null){
													swal("推送！", "推送失败。", "error"); 
												}else{
													if(res.resultCode=='0000'){
														swal("推送！", "推送成功。", "success"); 
														window.location.href = "branchAccount/accountInfoList"; 
													}else{
														swal("推送失败！", res.resultMsg, "error"); 
													}
												}
											}
										});
									});
								})
							}
						}
					});
};





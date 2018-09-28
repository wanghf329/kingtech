$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
      menuChecked("#settledList");
      $(".form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
      initDataTables();  
      
      if(typeof(canEdit) != "undefined" && canEdit==false){  
    	  $('.form-horizontal').find('input,textarea,select').attr('disabled',true); 
    	  $('.form-horizontal').find("button").hide();
    	  $('body').find(".edit-href").hide(); 
      } 
});

$("#addSettledInfoBtn").click(function(){
	window.location.href = "settled/single?loanContractId="; 
});

$(".saveRecordBtn").click(function(){ 
	$("#form-horizontal").submit();
});


function initDataTables() {
	this.dt = $("#settledDataList").DataTable({
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
					url : "settled/data",
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
				{data : "contractNumber"},
				{data : "contractName"}, 
				{data : "money"}, 
				{data : "loanTime"},
				{data : "startDate"}, 
				{data : "endDate"},
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
				}}, 
				{data : null,render : function(data, type, row) {  
									if(row.pushStatus=='SUCCESS') {
											return '<a href="settled/single?loanContractId='+row.loanContractId+'"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>  '+
												'<a href="javascirpt:void(0)" class="settled-delete" data-id="'+row.id+'"><i class="text-red fa fa-times"></i><strong>删除</strong>';
									} 
									if(row.pushStatus=='INPROSESS' || row.pushStatus=='DELETEING'){
										return '<a href="settled/single?loanContractId='+row.loanContractId+'"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>';
									}
									
									}}],
							"fnDrawCallback" : function(oSettings) {
								for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
									$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(oSettings['_iDisplayStart'] + i+ 1);
									$('.settled-delete').on("click",function(){
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
												url:"settled/delete/"+id,
												type:'get',
												async: false,
												success:function(res){
													if(res==null){
														swal("删除！", "删除失败。", "error"); 
													}else{
														if(res.resultCode=='0'){ 
															swal("删除！", "删除成功。", "success"); 
															window.location.href = "settled/list";  
														}else{
															swal("删除失败！", res.resultMsg, "error"); 
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



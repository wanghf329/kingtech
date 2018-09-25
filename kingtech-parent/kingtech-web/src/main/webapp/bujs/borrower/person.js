$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	
	 initDataTables(); 
	
	
      menuChecked("#personBorrowerList"); 
     
      $("#personnelFrom").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
      if(canEdit=="false"){
    	  $('#personnelFrom').find('input,textarea,select,button').attr('disabled',true); 
      } 
});


function initDataTables() {
	this.dt = $("#personBorrowerInfo").DataTable({
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
									url : "borrower/person/data",
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
								{data : "name"},
								{data : "sex",render : function(data, type, row) {
									switch (data) {
									case 'S_1':
										return '<span class="text-gray">男</span>';
									case 'S_2':
										return '<span class="text-gray">女</span>';
									}
								}},
								{data : "cardType",render : function(data, type, row) {
									switch (data) {
										case 'S_1':
											return '<span class="text-gray">二代身份证</span>'; 
										case 'S_2':
											return '<span class="text-gray">港澳通行证</span>';
										case 'S_3':
											return '<span class="text-gray">台湾通行证</span>';
										case 'S_4':
											return '<span class="text-gray">护照</span>';
									}
								}},
								{data : "cardNumber"},
								{data : "phone"},
								{data : "isFarmer",render : function(data, type, row) {
									switch (data) {
										case 'S_0':
											return '<span class="text-gray">否</span>';
										case 'S_1':
											return '<span class="text-gray">是</span>';
									}
								}},
								{data : "addressProvince",render : function(data, type, row) {
										return '<td><Strong>'+row.address+'</Strong></td>';
									}
								},
								{data : null,render : function(data, type, row) {
										return '<a href="javascript:void(0)" onclick=getPerson(\''+row.id+'\') class="extend-repay-edit"><i class="text-blue fa fa-edit"></i><strong>修改</strong>'
									}
								} ],
						"fnDrawCallback" : function(oSettings) {
							for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
								$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(oSettings['_iDisplayStart'] + i+ 1);
							}
						}
					});
};



$("#addPersonnelBtn").click(function(){ 
	window.location.href = "borrower/personnel/edit?id=";
});

function getPerson(id) {
	window.location.href = "borrower/personnel/edit?id="+id;
}


$(".saveRecordBtn").click(function(){ 
	$("#personnelFrom").submit();
});

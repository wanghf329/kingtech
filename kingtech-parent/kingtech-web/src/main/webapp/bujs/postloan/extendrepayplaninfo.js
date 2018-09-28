$(document).ready(function () {          
    menuChecked("#extensionrepayplaninfo"); 
    
    $("#form-horizontal").validationEngine({ 
  	  validationEventTriggers:"keyup blur",
  	  inlineValidation: true,
  	  showOneMessage:true,
  	  success :  false,
  	  autoHidePrompt:true,
  	  failure : function() { callFailFunction()  } 
    })
    
    initDataTables(); 
    /*if(canEdit=="false"){ 
  	  $('#form-horizontal').find('input,textarea,select,button').attr('disabled',true); 
    }*/
});

$(".saveRecordBtn").click(function(){ 
	$("#form-horizontal").submit(); 
});

$("#editRepayExtendPlanBtn").click(function(){ 
	$("#form-horizontal")[0].reset();
	$("input[name='returnPrincipal']").val(0);
	$("input[name='returnInterest']").val(0);
	$("input[name='overdueDays']").val(0);
	$("#editModel").modal();
});

function getRepayExtendPlan(id){ 
	$.get('postLoan/get/extensionrepayplaninfo/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$(".loanContractId option[value='"+res.loanContractId+"']").attr("selected",true);
		$("input[name='count']").val(res.count); 
		$("input[name='endDate']").val(res.endDate);
		$("input[name='principal']").val(res.principal);
		$("input[name='interest']").val(res.interest);
		$("#editModel").modal();
	});
}

function getRepayExtendPlan(id){
	 $.post("postLoan/update/extensionrepayplaninfo/", params, function() { 
         alert('已删除！');
     });
}


$("select[name='overdueFlag']").change(function(){  
	initOverdueDays($(this));
});

function initOverdueDays(el){
	if(el.val()=='S_0'){ 
		$("input[name='overdueDays']").val("0");
		$("input[name='overdueDays']").closest(".form-group").addClass("hide");
	}else{
		$("input[name='overdueDays']").val(""); 
		$("input[name='overdueDays']").closest(".form-group").removeClass("hide"); 
	}
}
function initDataTables() {
	this.dt = $("#repayExtendPlanTable").DataTable({
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
									url : "postLoan/extendRepayPlan/data",
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
								{data : "count"},
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
									}
								},
								{data : null,render : function(data, type, row) {
									if(row.pushStatus=='SUCCESS' || row.pushStatus=='INPROSESS') {
										return '<a href="postLoan/planInfo/edit?id='+row.id+'"><strong>查看详情</strong></a> <a href="postLoan/plan/add?infoId='+row.id+'"><strong>查看补充信息</strong></a>'
									}
									if(row.pushStatus=='INITATION' || row.pushStatus=='FAILED') {
										return '<a href="postLoan/planInfo/edit?id='+row.id+'"><i class="text-blue fa fa-edit"></i><strong>修改</strong></a>'
                                		        +'<a href="postLoan/plan/add?infoId='+row.id+'" ><i class="text-blue fa fa-plus-square-o"></i><strong>补充</strong></a>'
                                		        +'<a href="postLoan/plan/push?infoId='+row.id+'" ><i class="fa fa-fw fa-mail-forward"></i><strong>推送</strong></a>';
									}
									
									
									}
								} ],
						"fnDrawCallback" : function(oSettings) {
							for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
								$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(oSettings['_iDisplayStart'] + i+ 1);
							}
						}
					});
};
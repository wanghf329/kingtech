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
      
      var lang = {
    		  "sProcessing": "处理中...",
    		  "sLengthMenu": "每页 _MENU_ 项",
    		  "sZeroRecords": "没有匹配结果",
    		  "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
    		  "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
    		  "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    		  "sInfoPostFix": "",
    		  "sSearch": "搜索:",
    		  "sUrl": "",
    		  "sEmptyTable": "表中数据为空",
    		  "sLoadingRecords": "载入中...",
    		  "sInfoThousands": ",",
    		  "oPaginate": {
    		  "sFirst": "首页",
    		  "sPrevious": "上页",
    		  "sNext": "下页",
    		  "sLast": "末页",
    		  "sJump": "跳转"
    		  },
    		  "oAria": {
    		  "sSortAscending": ": 以升序排列此列",
    		  "sSortDescending": ": 以降序排列此列"
    		  }
    		  };

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
	this.dt = $("#extRepayInfo").DataTable({
		 language: {
	    		  "sProcessing": "处理中...",
	    		  "sLengthMenu": "显示 _MENU_ 条记录",
	    		  "sZeroRecords": "没有匹配结果",
	    		  "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
	    		  "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
	    		  "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
	    		  "sInfoPostFix": "",
	    		  "sSearch": "搜索:",
	    		  "sUrl": "",
	    		  "sEmptyTable": "表中数据为空",
	    		  "sLoadingRecords": "载入中...",
	    		  "sInfoThousands": ",",
	    		  "oPaginate": {
	    		  "sFirst": "首页",
	    		  "sPrevious": "上页",
	    		  "sNext": "下页",
	    		  "sLast": "末页",
	    		  "sJump": "跳转"
	    		  },
	    		  }, //提示信息
		 autoWidth: false, //禁用自动调整列宽
		 processing: true, //隐藏加载提示,自行处理
		 ordering : false, // 禁用排序
		 serverSide: true, //启用服务器端分页
		 searching: false, //禁用原生搜索
		 pagingType: "simple_numbers", //分页样式：simple,simple_numbers,full,full_numbers
		ajax: function (data, callback, settings) {
			 //封装请求参数
			 var param = {};
			 param.length = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
			 param.start = data.start;//开始的记录序号
			 console.log(data);
			 //ajax请求数据
			 $.ajax({
			  type: "POST",
			  url: "postLoan/extensionrepayinfo/data",
			  cache: false, //禁用缓存
			  data: param, //传入组装的参数
			  dataType: "json",
			  success: function (result) {
			 // console.log(result);
			  //setTimeout仅为测试延迟效果
			  setTimeout(function () {
			  //封装返回数据
			  var returnData = {};
			  returnData.recordsTotal = result.totalSize;//返回数据全部记录
			  returnData.recordsFiltered = result.totalSize;//后台不实现过滤功能，每次查询均视作全部结果
			  returnData.data = result.results;//返回的数据列表
			  console.log(returnData);
			  //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
			  //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
			  callback(returnData);
			  }, 200);
			  }
			 });
		},
		columns :  [
		            {data : null},
		            {data : "loanContractNo"},
		            {data : "loanContractName"},
		            {data : "extendNum"},
		            {data : "repayDate"},
		            {data : "repayAmount", render:function(data, type, row){
		            	return  "<span class=\"red bolder\">￥"+ data +"</span>";  
		            	
		            }},
		            {data : "repayPrincipalAmount", render:function(data, type, row){
		            	return  "<span class=\"red bolder\">￥"+ data +"</span>";  
		            	
		            }},
		            {data : "repayInterestAmount", render:function(data, type, row){
		            	return  "<span class=\"red bolder\">￥"+ data +"</span>";  
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
            }
		});
		            
		            
		            
};

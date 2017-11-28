function initTable() {  
	 this.dt = $("#branchBaseList").dataTable({
			searching : false, // 禁用dataTables自带的查询框
			ordering : false, // 禁用排序
			processing: true,//加载特效
		    serverSide: true,//页面在加载时就请求后台，以及每次对 datatable 进行操作时也是请求后台
		    autoWidth:true,
			ajax : {
				type : "POST",
				"url" : "branch/list",
				data: function(d) {
				}
			},
			columns :  [
		            {data : "corporateName"},
		            {data : "legalRepresentative"},
		            {data : "regDapital",render: function(data, type, row) {
				    	return '<span class="text-red" ><i class="fa fa-jpy"/><strong>'+data+'万元</strong></span>';
			        }},
		            {data : "buildDate",render: function(data, type, row) {
				    	return formateDate(new Date(data),"yyyy-MM-dd ");;
		            }},
		            {data : "openingDate",render: function(data, type, row) {
				    	return formateDate(new Date(data),"yyyy-MM-dds");;
		            }},
		            {data : "businessAddr"},
		            {data : "siteArea"},
		            {data : "organizationCode"},
		            {data : "licence"},
		            {data : "nationalRegNum"},
		            {data : "landRegNum"},
		            {data : "businessScope"},
		            {data : "pushStatus",render: function(data, type, row) {
		            	if(row.pushStatus =='SUCCESS' ) {
		            		return '<span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span>';
		            	}
		            	
		            	if(row.pushStatus =='INPROSESS' ) {
		            		return '<span class="text-green"><i class="text-blue fa fa-asterisk"></i>推送处理中</span>';
		            	}
		            	
		            	if(row.pushStatus =='FAILED' ) {
		            		return '<span class="text-green"><i class="text-red fa fa-minus-circle"></i>推送失败</span>';
		            	}
		            }},
		            {data : "id",render: function(data, type, row) {
				    	return '<a href="javascript:void(0)" onclick="getCapital('+row.id+')"><i class="text-blue fa  fa-edit"></i><strong>修改</strong></a></td>'
		            }},
		            
        
        ],
			"fnDrawCallback" : function(oSettings) {
				for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
					$('td:eq(0)',oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(
									oSettings['_iDisplayStart'] + i + 1);
				}
			},
		});
 }
$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
      //$('.datepicker').datepicker({autoclose: true });
      $("#addBranchForm").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
      
      $('.datepicker1').datetimepicker({
    		minView: "2", //选择日期后，不会再跳转去选择时分秒 
    	    language:  'zh-CN',
    	    format: 'yyyy-mm-dd hh:ii:ss',
    	    todayBtn:  1,
    	    autoclose: 1,});
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


function getBranchInfo(id){
	$.get('branch/getCapital/'+id,null,function(res){
		$("input[name='financingChannel']").val(res.financingChannel);
		$("input[name='financingMoney']").val(res.financingMoney);
		$("input[name='financingTime']").val(res.financingTime);
		$("input[name='expirationTime']").val(res.expirationTime);
		$("input[name='replyTime']").val(res.replyTime);
		$("#newBranchBaseInfo").modal();
	});
}




$(".saveRecordBtn").click(function(){ 
	$("#addBranchForm").submit();
});

  
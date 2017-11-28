 function initTable() {  
	 this.dt = $("#branchBaseList").dataTable({
			searching : false, // 禁用dataTables自带的查询框
			ordering : false, // 禁用排序
			processing: true,//加载特效
		    serverSide: true,//页面在加载时就请求后台，以及每次对 datatable 进行操作时也是请求后台
		    autoWidth:true,
			ajax : {
				type : "POST",
				"url" : $$ctx+"/loan/finalAudit/getAllDatas",
				data: function(d) {
				}
			},
			columns :  [
		            {data : "id"},
		            {data : "title",render: function(data, type, row) {
				    	return '<a href="#'+$$ctx+'/loan/finalAudit/audit/' + row.id + '">' + data + '</a>';
			        }},
		            {data : "rate",render:function(data,type,row){
		            	return data+"%";
		            }},
		            {data : "userName"},
		            {data : "purposeText"},
		            {data : "amount",render:function(data,type,row){
		            	return "<font style='font-weight:bold;color:#dd5a43'>￥"+formatAmount(row.amount,2)+"</font>";
		            }},
		            {data : "timeLimit"},
//		            {data : "durationMethod"},
		            {data : "methodText"},
		            {data : "branchName"},
		            {date : "series" ,render:function(data,type,row)
		            	{
			            	if(row.series==="SMALL_LOAN_RECOMMEND"){//小贷推荐
				        		return "<span class='badge badge-primary'>"+row.seriesString+"</span>";
				        	}else if(row.series==="INCOMRS_TRANSFER"){//收益权转让
				        		return "<span class='badge badge-success'>"+row.seriesString+"</span>";
				        	}else if(row.series==="SEVEN_INCOMRS_TRANSFER"){//七天收益权转让
				        		return "<span class='badge badge-grey'>"+row.seriesString+"</span>";
				        	}else if(row.series==="ASSET_PACKAGE_TRANSFER"){//资产打包
				        		return "<span class='badge badge-purple'>"+row.seriesString+"</span>";
				        	}else if(row.series==="DONGXING_DAXIN"){//东兴打新
				        		return "<span class='badge badge-inverse'>"+row.seriesString+"</span>";
				        	}else if(row.series==="TIANJINSUO_LOAN" || row.series==="WUJINSUO_LOAN"){//天金所挂牌
				        		return "<span class='badge badge-warning'>"+row.seriesString+"</span>";
				        	}
		            	}
		            },
		            {date : "loanActivityType" ,render:function(data,type,row)
		            	{
			            	if(row.loanActivityType==="NOIVE_8"){//8.88新手标
				        		return "<span class='badge badge-purple'>"+row.acctTypeString+"</span>";
				        	}else if(row.loanActivityType==="NOIVE_6"){//6.66新手标
				        		return "<span class='badge badge-grey'>"+row.acctTypeString+"</span>";
				        	}else {
				        		return "<span class='badge badge-success'>"+row.acctTypeString+"</span>";
				        	}
		            	}
		            },
		            {data : "borrowerName"},
		            {data : "timesubmit",render:function(data,type,row){
		            	return dateTimeTillNow(data,row.currDate);
		            }}
		            
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
      initTable();  
      $('.datepicker').datepicker({autoclose: true });
      
});

menuChecked("#branch1"); 
$(".form-horizontal").validator()



$('#datepicker').datepicker({ autoclose: true});
$(".saveRecordBtn").click(function(){
	var self = this;
	if (!self.validate()) {
        return;
    }
    var data=new FormData($('#addBranchForm')[0]);
    $.ajax({
        url: $$myCtx +'/settings/changePwd',
        data: data,
        processData: false,
        contentType: false,
        type: 'POST',
        success: function(data) {
         	bootbox.alert(data.msg);
        }
    }).fail(function() {
    	bootbox.alert(data.msg);
    });
    
    
    return false; 
	var data = {
			id:'',	
			contractName:$("#contractName").val(),
			activityName:$("#activityName").val(),
			orderId:$("#orderId").val(),
			orderName:$("#orderName").val(),
			amount:$("#amount").val(),
			discription:$("#discription").val()
	};
	
	$.post("recharge/create",data,function(res){
		alert(res);
	});
});
  
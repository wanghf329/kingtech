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
		            {data : "id"},
		            {data : "corporateName"},
		            {data : "legalRepresentative"},
		            {data : "regDapital"},
		            {data : "purposeText"},
		            {data : "buildDate"},
		            {data : "openingDate"},
		            {data : "businessAddr"},
		            {data : "timeLimit"},
		            {data : "organizationCode"},
		            {data : "licence"},
		            {data : "nationalRegNum"},
		            {data : "landRegNum"}
        
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
      $("#addBranchForm").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});




$('#datepicker').datepicker({ autoclose: true});

$(".saveRecordBtn").click(function(){ 
	$("#addBranchForm").submit();
});

  
$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	  $('.datepicker').datetimepicker({
  		minView: "2", //选择日期后，不会再跳转去选择时分秒 
  	    language:  'zh-CN',
  	    format: 'yyyy-mm-dd',
  	    todayBtn:  1,
  	    autoclose: 1,});
	  
      menuChecked("#personalList"); 
      
      $("#form-horizontal").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});

$("#resetBtn").click(function(){ 
	$("#form-horizontal")[0].reset();
});

$(".saveRecordBtn").click(function(){ 
	$("#form-horizontal").submit();
});

function getEmployee(id){
	$.get('branch/getEmployee/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='name']").val(res.name);
		$("input[name='phone']").val(res.phone);
		$("input[name='email']").val(res.email);
		$("input[name='postalAddress']").val(res.postalAddress);
		$("input[name='department']").val(res.department);
		
//		$("input[name='sex']").parent().removeClass("checked");
		$("input[name='sex'][value='"+res.sex+"']").parent().addClass("checked");
		$("input[name='sex'][value='"+res.sex+"']").attr("checked",true);
		$("input[name='idNumber']").val(res.idNumber);
		$("input[name='education'][text="+res.education+"]").attr("selected",true);
		$("input[name='executiveFlag'][value="+res.executiveFlag+"]").attr("checked",true);
		$("input[name='post'][text="+res.post+"]").attr("selected",true);
		$("input[name='replyTime']").val(res.replyTime);
		$("input[name='entryTime']").val(res.entryTime);
		$("input[name='status'][value="+res.status+"]").attr("checked",true);
		$("input[name='quitTime']").val(res.quitTime);
		$("#rechargeModel").modal();
	});
}

function delConfirm(id){
	$("#confirmModel").modal();
	$("#del-id").val(id); 
}

function delCapital(){
	window.location.href = "branch/delEmployee/"+$("#del-id").val();
};
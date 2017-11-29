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

$("#rechargeModelBtn").click(function(){ 
	$("#form-horizontal")[0].reset();
	$("#rechargeModel").modal();
});

$(".saveRecordBtn").click(function(){ 
	$("#form-horizontal").submit();
});

$("input[name='executiveFlag'][value='0']").on("ifChecked", function(event){
	$("select[name='post']").css("display","none");
	$("select[name='post']").val("");
});
$("input[name='executiveFlag'][value='1']").on("ifChecked", function(event){
	$("select[name='post']").css("display","");
	$("select[name='post']").attr("disabled",false);
});

function getEmployee(id){
	$.get('branch/getEmployee/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='name']").val(res.name);
		$("input[name='phone']").val(res.phone);
		$("input[name='email']").val(res.email);
		$("input[name='postalAddress']").val(res.postalAddress);
		$("input[name='department']").val(res.department);
		radioChecked('sex',res.sex);
		$("input[name='idNumber']").val(res.idNumber);
		optionSelected('education', res.education);
		radioChecked('executiveFlag',res.executiveFlag);
		if (res.executiveFlag == 0) {
			$("select[name='post']").css("display","none");
		} else {
			optionSelected('post', res.post);
		}
		$("input[name='replyTime']").val(res.replyTime);
		$("input[name='entryTime']").val(res.entryTime);
		radioChecked('status',res.status);
		$("input[name='quitTime']").val(res.quitTime);
		$("#rechargeModel").modal();
	});
}

function optionSelected(name, value){
	var all_options = document.getElementsByName(name)[0].options;
	for (i=0; i<all_options.length; i++) {
		if (all_options[i].text == value) {
			all_options[i].selected = true;
		}
	}
}


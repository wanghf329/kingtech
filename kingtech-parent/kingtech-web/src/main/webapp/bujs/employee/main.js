$(document).ready(function () {          
      //调用函数，初始化表格  
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
		
		console.log("res.sex2: " +res.sex);
//		$("input[name='sex'][value='1']").attr("checked",true);
		setRadio("sex",res.sex);
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

function setRadio(rName,rValue){
    var rObj = document.getElementsByName(rName);

    for(var i = 0;i < rObj.length;i++){
        if(rObj[i].value == rValue){
        	console.log("set 1: ");
            rObj[i].checked = true;
        }
    }
}

function delConfirm(id){
	$("#confirmModel").modal();
	$("#del-id").val(id); 
}

function delCapital(){
	window.location.href = "branch/delEmployee/"+$("#del-id").val();
};
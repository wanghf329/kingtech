$(document).ready(function () {          
      //调用函数，初始化表格  
	  menuChecked("#branch1"); 
      $("#addBranchForm").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});

function getBranchInfo(id){
	$.get('branch/getBranchInfo/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='corporateName']").val(res.corporateName);
		$("input[name='legalRepresentative']").val(res.legalRepresentative);
		$("input[name='regCapital']").val(res.regCapital);
		$("input[name='buildDate']").val(res.buildDate);
		$("input[name='openingDate']").val(res.openingDate);
		$("input[name='siteArea']").val(res.siteArea);
		$("input[name='businessAddr']").val(res.businessAddr);
		$("input[name='organizationCode']").val(res.organizationCode);
		$("input[name='licence']").val(res.licence);
		$("input[name='nationalRegNum']").val(res.nationalRegNum);
		$("input[name='landRegNum']").val(res.landRegNum);
		$("input[name='businessScope']").val(res.businessScope);
		$("#newBranchBaseInfo").modal();
	});
}


$("#newBranchBaseBtn").click(function(){ 
	$("#addBranchForm")[0].reset();
	$("#newBranchBaseInfo").modal();
});



$(".saveRecordBtn").click(function(){ 
	$("#addBranchForm").submit();
});

  
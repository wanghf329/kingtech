$(document).ready(function () {          
      //调用函数，初始化表格  
      //initTable();  
	
	
      menuChecked("#BuBorrowerList"); 
      
      $("#enterpriseFrom").validationEngine({ 
    	  validationEventTriggers:"keyup blur",
    	  inlineValidation: true,
    	  showOneMessage:true,
    	  success :  false,
    	  autoHidePrompt:true,
    	  failure : function() { callFailFunction()  } 
      })
});


$("#addEnterpriseBtn").click(function(){ 
	window.location.href = "borrower/enterprise/edit";
});


$(".saveRecordBtn").click(function(){ 
	$("#enterpriseFrom").submit();
});


function getEnterprise(id){
	if(id == null || id == "") {
		alert("请选择企业");
		return;
	}
	$.get('borrower/getEnterprise/'+id,null,function(res){
		$("input[name='id']").val(res.id);
		$("input[name='corporateName']").val(res.corporateName);
		$("select[name='scale'] option [value='"+res.scale+"']").attr("selected","selected");
		$("select[name='industryType'] option [value='"+res.industryType+"']").attr("selected","selected");
		$("select[name='industryinvolved'] option [value='"+res.industryinvolved+"']").attr("selected","selected");
		$("input[name='organizationcode']").val(res.organizationcode);
		$("input[name='regCode']").val(res.regCode);
		$("input[name='regOffice']").val(res.regOffice);
		$("input[name='regDate']").val(res.regDate);
		$("input[name='nationalregNum']").val(res.nationalregNum);
		$("input[name='landRegNum']").val(res.landRegNum);
		$("input[name='licence']").val(res.licence);
		$("input[name='licenceEndDate']").val(res.licenceEndDate);
		$("input[name='nature']").val(res.nature);
		$("input[name='employNum']").val(res.employNum);
		
		$("input[name='legalRepresentative']").val(res.legalRepresentative);
		$("input[name='bulidDate']").val(res.bulidDate);
		$("input[name='actualController']").val(res.actualController);
		$("input[name='regCapital']").val(res.regCapital);
		$("input[name='reallyCapital']").val(res.reallyCapital);
		
		$("input[name='businessScope']").val(res.businessScope);
		$("input[name='regAddress']").val(res.regAddress);
		$("input[name='contactAddressProvince']").val(res.contactAddressProvince);
		$("input[name='contactAddresscity']").val(res.contactAddresscity);
		$("input[name='contactAddressDistrict']").val(res.contactAddressDistrict);
		
		$("input[name='contactAddress']").val(res.contactAddress);
		$("input[name='postcode']").val(res.postcode);
		$("input[name='phone']").val(res.phone);
		$("input[name='linkman']").val(res.linkman);
		$("input[name='fax']").val(res.fax);
		
		$("input[name='email']").val(res.email);
		$("input[name='webSite']").val(res.webSite);
	});
	
} 

$(document).ready(function() {
	menuChecked("#addextensionrepayplaninfo");

	$("#form-horizontal").validationEngine({
		validationEventTriggers : "keyup blur",
		inlineValidation : true,
		showOneMessage : true,
		success : false,
		autoHidePrompt : true,
		failure : function() {
			callFailFunction()
		}
	})

});

// 添加
$("#buttonAdd").click(
		function(e) {
			var self = this;
			var model = $(e.currentTarget).parent().parent().parent().parent()
					.parent();
			var $originHtml = $('#originPlanInfo');
			var tmp = model.clone(true);
			tmp.find('.deleteButton').removeClass("hidden");
			tmp.find('.addButton').addClass("hidden");
			$originHtml.after(tmp);
		});

// 删除
$(".buttonDelete").click(function(e) {
			var self = this;
			var $result = $(e.currentTarget).parent().parent().parent()
					.parent().parent();
			$result.remove();
		});

// 保存
$(".buttonSave").click(function() {
	var self = this;
	
	$("form[name='addExtendPlanInfo']").submit(function(e) {
		var data = {};
		data.plans = new Array();
		var list = [];
		var plans = [];
		
		$(".addExtendPlanInfo").each(function(i, o) {
			var plan = {};
			plan.repayDate = $(o).find("input[name='endDate']").val();
			plan.principal = $(o).find("input[name='principal']").val();
			plan.interest =$(o).find("input[name='interest']").val();

			plans.push(plan);
			data.plans.push(plans);
		});
		
		data.count = $("input[name='count']").val();
		data.loanContractId =  $(".loanContractId option:selected").val();
//		var json = JSON.stringify(data);
		$.post('postLoan/add/extensionrepayplaninfo', data, function(o) {
			callback(o);
		});
		
//		bootbox.confirm({
//			message: "确定要保存该展期计划信息吗？",
//			buttons: {
//				confirm: {
//					label: "<i class='ace-icon fa fa-trash-o'></i> 确定",
//					className: "btn-danger btn-sm",
//				},
//				cancel: {
//					label: "<i class='ace-icon fa fa-times'></i> 取消",
//					className: "btn-sm",
//				}
//			},
//			callback: function(result) {
//				if (result) {
//					$.post('postLoan/add/extensionrepayplaninfo', data, function(o) {
//						callback(o);
//					});
//				}
//			}
//		});
		
		
		
		
		
	});

});

$(".saveRecordBtn").click(function() {
	$("#form-horizontal").submit();
});

$("#editRepayExtendPlanBtn").click(function() {
	$("#form-horizontal")[0].reset();
	$("input[name='returnPrincipal']").val(0);
	$("input[name='returnInterest']").val(0);
	$("input[name='overdueDays']").val(0);
	$("#editModel").modal();
});

function getRepayExtendPlan(id) {
	$.get('postLoan/get/extensionrepayplaninfo/' + id, null, function(res) {
		$("input[name='id']").val(res.id);
		$(".loanContractId option[value='" + res.loanContractId + "']").attr(
				"selected", true);
		$("input[name='count']").val(res.count);
		$("input[name='endDate']").val(res.endDate);
		$("input[name='principal']").val(res.principal);
		$("input[name='interest']").val(res.interest);
		$("#editModel").modal();
	});
}

//function getRepayExtendPlan(id) {
//	$.post("postLoan/update/extensionrepayplaninfo/", params, function() {
//		alert('已删除！');
//	});
//}

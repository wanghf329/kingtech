<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>数据对接平台</title>
<%@include file="../common/head.jspf"%>
</head>
<body class="skin-blue">
	<%@include file="../common/logout.jspf"%>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<%@include file="../common/sidebar.jspf"%>
		<aside class="right-side">
			<section class="content-header">
				<h1>
					展期还款信息列表 <small></small>
				</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="box">
						<div class="box-header"></div>
						<!-- /.box-header -->
						<div class="box-body">
							<form class="form-horizontal" method="POST"
								name="addExtendPlanInfo">
								<input type="hidden" value="${capital.id}">

								<div class="form-group">
									<label for="#loanContractId" class="col-sm-3 control-label"><i
										class="text-red">*</i> 合同编号</label>
									<div class="col-sm-6 input-group">
										<select class="form-control loanContractId validate[required]"
											name="loanContractId" data-errormessage="合同编号不能为空">
											<c:forEach var="it" items="${contracts}">
												<%-- 											<option value ="${it.name()}" <c:if test="${it eq capital.channel}">selected</c:if>>${it.getKey()}</option> --%>
												<option value="${it.contractNumber}">
													<c:if test="${it eq extendPlan.contractNumber}">selected</c:if>${it.contractNumber}
												</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="#extendCount" class="col-sm-3 control-label"><i
										class="text-red">*</i> 展期次数</label>
									<div class="col-sm-6 input-group">
										<input type="text" value="${extendPlan.count}"
											class="form-control validate[required,custom[number]]"
											maxlength="2" data-errormessage="展期次数只能为数字" name="count">
									</div>
								</div>
								<div class="row" id="originPlanInfo">
									<div class="col-md-12">
										<div class="box box-success addExtendPlanInfo">
											<div class="box-header with-border">
												<h3 class="box-title">展期还款计划</h3>
											</div>
											<div class="box-body">
												<div class="form-group">
													<label for="#repayDate" class="col-sm-3 control-label"><i
														class="text-red">*</i> 展期后还款日期</label>
													<div class="col-sm-6 input-group">
														<span class="input-group-addon"><i
															class="fa fa-calendar"></i></span> <input type="text"
															class="form-control pull-right datepicker validate[required]"
															name="endDate" readonly data-errormessage="展期后还款日期不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#principal" class="col-sm-3 control-label"><i
														class="text-red">*</i> 展期后还款本金</label>
													<div class="col-sm-6 input-group">
														<span class="input-group-addon"><i
															class="fa fa-rmb"></i></span> <input type="text"
															class="form-control validate[required,custom[number]]"
															data-errormessage="展期后还款本金只能为数字" name="principal">
														<span class="input-group-addon"><i class="fa">元</i></span>
													</div>
												</div>
												<div class="form-group">
													<label for="#interest" class="col-sm-3 control-label"><i
														class="text-red">*</i> 展期后还款利息</label>
													<div class="col-sm-6 input-group">
														<span class="input-group-addon"><i
															class="fa fa-rmb"></i></span> <input type="text"
															class="form-control validate[required,custom[number]]"
															data-errormessage="展期后还款利息只能为数字" name="interest">
														<span class="input-group-addon"><i class="fa">元</i></span>
													</div>
												</div>
											</div>
											<div class="box-footer">
												<div class=" input-group addButton">
													<button type="button" id="buttonAdd"
														class="btn bg-maroon btn-flat margin center">添加</button>
												</div>
												<div class="input-group deleteButton hidden">
													<button type="button"
														class="btn btn-block btn-danger buttonDelete">删除</button>
												</div>
											</div>

											<!-- /.box-body -->
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group deleteButton ">
										<button type="submit"
											class="btn btn-block btn-danger buttonSave">保存</button>
									</div>
								</div>

							</form>
						</div>
					</div>
					<!-- /.box -->
				</div>
				<!-- /.col -->
	</div>
	<!-- /.row -->
	</section>
	<!-- /.content -->
	</aside>
	<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
	<%@include file="../common/footer.jspf"%>
	<script src="bujs/postloan/addextendrepayplaninfo.js"
		type="text/javascript"></script>
	<!-- 	<script src="bujs/finance/capital.js" type="text/javascript"></script> -->
	<!-- 	<script type="text/javascript"> -->
	// var borrowerId = '${capital.id}'; // var canEdit = "${capital==null or capital.pushStatus=='INITATION' or capital.pushStatus=='FAILED'}";
	<!-- 	</script> -->
</body>
</html>
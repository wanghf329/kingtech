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
							<form class="form-horizontal" id="form-horizontal"
								action="postLoan/add/extensionrepayplaninfo" method="POST">
								<input type="hidden" name="id" value="${extendPlanInfo.id}">

								<div class="form-group">
									<label for="#loanContractId" class="col-sm-3 control-label"><i
										class="text-red">*</i> 合同编号</label>
									<div class="col-sm-6 input-group">
										<select class="form-control loanContractId validate[required]"
											name="loanContractId" data-errormessage="合同编号不能为空">
											<c:forEach var="it" items="${contracts}">
												<option value="${it.id}"
													<c:if test="${it.id eq extendPlanInfo.loanContractId}">selected</c:if>>${it.contractNumber}
												<option value="${it.id}">${it.contractNumber}</option></option>


											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="#extendCount" class="col-sm-3 control-label"><i
										class="text-red">*</i> 展期次数</label>
									<div class="col-sm-6 input-group">
										<input type="text" value="${extendPlanInfo.count}"
											class="form-control validate[required,custom[number]]"
											maxlength="2" data-errormessage="展期次数只能为数字" name="count">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-6 input-group">
										<label class="col-sm-5 control-label"></label>
										<button type="button"
											class="btn btn-primary saveRecordBtn edit-href">保&nbsp;存</button>
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
	<script src="bujs/postloan/extendrepayinfo.js" type="text/javascript"></script> 
	<script type="text/javascript">
		var canEdit = "${extendPlanInfo==null or extendPlanInfo.pushStatus=='INITATION' or extendPlanInfo.pushStatus=='FAILED'}";
	</script>
</body>
</html>
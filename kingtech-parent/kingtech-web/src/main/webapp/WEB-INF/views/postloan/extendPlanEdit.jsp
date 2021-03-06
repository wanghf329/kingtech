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
					展期还款计划列表 <small></small>
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
								action="finance/capital/save" method="POST">
								<input type="hidden" name="id" value="${capital.id}">

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
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text"
											class="form-control validate[required,custom[number]]"
											data-errormessage="展期后还款本金只能为数字" name="principal"> <span
											class="input-group-addon"><i class="fa">元</i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="#interest" class="col-sm-3 control-label"><i
										class="text-red">*</i> 展期后还款利息</label>
									<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text"
											class="form-control validate[required,custom[number]]"
											data-errormessage="展期后还款利息只能为数字" name="interest"> <span
											class="input-group-addon"><i class="fa">元</i></span>
									</div>
								</div>

								<div class="form-group  text-center">
									<button type="submit" class="btn btn-primary ">保&nbsp;存</button>
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
	<!-- 	<script src="bujs/finance/capital.js" type="text/javascript"></script> -->
	<!-- 	<script type="text/javascript"> -->
	// var borrowerId = '${capital.id}'; // var canEdit = "${capital==null or capital.pushStatus=='INITATION' or capital.pushStatus=='FAILED'}";
	<!-- 	</script> -->
</body>
</html>
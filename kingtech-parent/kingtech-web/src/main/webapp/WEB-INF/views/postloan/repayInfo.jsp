<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>数据对接平台</title>
        <%@include file="../common/head.jspf" %> 
    </head>
    <body class="skin-blue">
		<%@include file="../common/logout.jspf" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../common/sidebar.jspf" %>
            <aside class="right-side">                
                <section class="content-header">
                    <h1>
                                                                        还款信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="postLoan/repayinfo">贷后管理</a></li>
                        <li class="active">还款信息</li> 
                    </ol>
                </section>
                
                <!-- Modal -->
				<div class="modal fade" id="newRepayInfo" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" >还款信息录入</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id = "addRepayInfoForm" action="postLoan/add/repayInfo" method="POST">
									<input type="hidden" name="id" value="">
									<div class="form-group">
										<label for="#loanContractId" class="col-sm-3 control-label"><i class="text-red">*</i>合同编号</label> 
										<div class="col-sm-6 input-group">
											<select class="form-control validate[required]" id="loanContractId" name="loanContractId">
												<c:forEach var="it" items="${contracts}">
			  										<option value ="${it.id}">${it.loanContractNo}</option> 
												</c:forEach>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#repayAmount" class="col-sm-3 control-label"><i class="text-red">*</i> 还款金额</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number]]" name = "repayAmount" id="repayAmount" 
											data-errormessage-value-missing="还款金额不能为空"  data-errormessage-custom-error="还款金额必须是数字" >
										<span class="input-group-addon"><i class="fa ">元</i></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#repayPrincipalAmount" class="col-sm-3 control-label"><i class="text-red">*</i> 还款本金</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,funcCall[validataPrincipal]]" name = "repayPrincipalAmount" 
													id="repayPrincipalAmount"  data-errormessage-value-missing="还款本金不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#repayInterestAmount" class="col-sm-3 control-label"><i class="text-red">*</i> 还款利息</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,funcCall[validataInterest]]" name = "repayInterestAmount" id="repayInterestAmount" 
														 data-errormessage-value-missing="还款利息不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#repayDate" class="col-sm-3 control-label"><i class="text-red">*</i> 还款日期</label>
										<div class="col-sm-6 input-group date">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" name="repayDate"
												readonly id="repayDate" data-errormessage="还款日期不能为空">
										</div>
									</div>
								</form>						
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
								<button type="submit" class="btn btn-primary saveRecordBtn">保存</button>
							</div>
						</div>
					</div>
				</div>

			<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12 no-padding">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">还款信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addRepayInfoBtn">新增还款信息</button>  
                                    <table class="table table-bordered" role="grid" id="repayInfo">
                                    	<thead>
	                                        <tr>
	                                            <th>编号</th> 
	                                            <th>主合同编号</th>
	                                            <th>主合同名称</th>
	                                            <th>还款日期</th> 
	                                            <th>还款金额(元)</th> 
	                                            <th>还款本金(元)</th>
	                                            <th>还款利息(元)</th>
	                                            <th>状态</th>
	                                            <th>操作</th>
	                                        </tr>
                                        </thead>
                                        <tbody></tbody>
                                    </table>
                                </div>
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

		<%@include file="../common/footer.jspf" %>   
        <script src="bujs/postloan/repayinfo.js" type="text/javascript"></script>       
    </body>
</html>
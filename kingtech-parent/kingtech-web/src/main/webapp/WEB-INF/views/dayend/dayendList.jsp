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
				<div class="modal fade" id="newDayendInfo" tabindex="-1" role="dialog"
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
								<form class="form-horizontal" id = "addRepayInfoForm" action="dayend/save" method="POST">
									<input type="hidden" name="id" value="">

									<div class="form-group">
										<label for="#checkDate" class="col-sm-4 control-label"><i class="text-red">*</i> 日期</label>
										<div class="col-sm-6 input-group date">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" name="checkDate"
												readonly id="checkDate" data-errormessage="还款日期不能为空">
										</div>
									</div>									
									
									<div class="form-group">
										<label for="#dayCount" class="col-sm-4 control-label"><i class="text-red">*</i> 上传合同笔数</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number],min[0],max[999999]]" name = "dayCount" 
													id="money"  data-errormessage-value-missing="上传合同笔数不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span> 
										</div> 
									</div>
									
									<div class="form-group">
										<label for="#dayMoney" class="col-sm-4 control-label"><i class="text-red">*</i> 上传合同总金额（元）</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number],min[0],max[999999999999]]" name = "dayMoney" 
													id="dayMoney"  data-errormessage-value-missing="上传合同总金额不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span> 
										</div> 
									</div>		
									
									<div class="form-group">
										<label for="#dayLoan" class="col-sm-4 control-label"><i class="text-red">*</i> 当日放款总金额（元）</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number],min[0],max[999999999999]" name = "dayLoan" 
													id="dayLoan"  data-errormessage-value-missing="当日放款总金额不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span> 
										</div> 
									</div>	
									
									<div class="form-group">
										<label for="#dayRepay" class="col-sm-4 control-label"><i class="text-red">*</i> 当日还款金额（元）</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number],min[0],max[999999999999]]" name = "dayRepay" 
													id="dayLoan"  data-errormessage-value-missing="当日还款金额不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span> 
										</div> 
									</div>
									
									<div class="form-group">
										<label for="#loanBalance" class="col-sm-4 control-label"><i class="text-red">*</i> 贷款余额（元）</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number],min[0],max[999999999999]]" name = "loanBalance" 
													id="loanBalance"  data-errormessage-value-missing="贷款余额不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span> 
										</div> 
									</div>
									
									<div class="form-group">
										<label for="#loanMoney" class="col-sm-4 control-label"><i class="text-red">*</i> 贷款总额（元）</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number],min[0],max[999999999999]]" name = "loanMoney" 
													id="loanMoney"  data-errormessage-value-missing="贷款总额不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span> 
										</div> 
									</div>
									
									<div class="form-group">
										<label for="#loanCount" class="col-sm-4 control-label"><i class="text-red">*</i> 贷款总笔数</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number],min[0],max[999999]]" name = "loanCount" 
													id="loanCount"  data-errormessage-value-missing="贷款余额不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span> 
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
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addDayendInfoBtn">新增日终信息</button>  
                                    <table class="table table-bordered" role="grid" id="dayendInfo">
                                    	<thead>
	                                        <tr>
	                                            <th>编号</th> 
	                                            <th>日期</th>
	                                            <th>上传合同笔数</th>
	                                            <th>上传合同总金额(元)</th> 
	                                            <th>当日放款总金额(元)</th> 
	                                            <th>当日还款金额(元)</th>
	                                            <th>贷款余额(元)</th>
	                                            <th>贷款总额(元)</th>
	                                            <th>贷款总笔数(元)</th>
	                                            <th>状态</th>
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
        <script src="bujs/dayend/dayend.js" type="text/javascript"></script>       
    </body>
</html>
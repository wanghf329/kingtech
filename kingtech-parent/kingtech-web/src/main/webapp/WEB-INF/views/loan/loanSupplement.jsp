<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>AdminLTE | Simple Tables</title>
        <%@include file="../common/head.jspf" %> 
    </head>
    <body class="skin-blue">
		<%@include file="../common/logout.jspf" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../common/sidebar.jspf" %>
            <aside class="right-side">                
                <section class="content-header">
                    <h1>
                                                                        合同信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构管理</a></li>
                        <li class="active">合同信息</li>
                    </ol>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">补充合同信息</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
			                    	<form class="form-horizontal" id="form-horizontal" action="loan/add/supplement" method="POST">
										
										<input type="hidden" id="loanContractId" value="${loanContractId}">
										<div class="form-group">
											<label for="#pledgeType" class="col-sm-2 control-label">担保类型</label>
											<div class="col-sm-4 input-group">
								                <div class="radio">
								                    <input type="radio" name="pledgeType" value="1" checked="">抵押
								                   	<input type="radio" name="pledgeType" value="2" >质押
								                </div>
											</div>
										</div>
										<div class="form-group">
											<label for="#collateralType" class="col-sm-2 control-label">抵质押物类型</label>
											<div class="col-sm-4 input-group ">
												<select class="form-control validate[required]" name="collateralType">
									            	<option value="1">存货抵押</option>
									            	<option value="2">客账抵押</option>
									            	<option value="3">证券抵押</option>
									            	<option value="4">设备抵押</option>
									            	<option value="5">不动产抵押</option>
									            	<option value="6">人寿险抵押</option>
									            	<option value="7">其他</option>
									            </select>
									            <select class="form-control validate[required]" name="collateralType" style="display:none">
									            	<option value="1">股权质押</option>
									            	<option value="2">定期存单质押</option>
									            	<option value="3">专利权质押</option>
									            	<option value="4">应收账款质押</option>
									            	<option value="5">其他</option>
									            </select>
											</div>
										</div>
										<div class="form-group">
											<label for="#collateralName" class="col-sm-2 control-label">抵质押物名称</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control validate[required]" name="collateralName" data-errormessage="抵质押物名称不能为空">
											</div>
										</div>
										<div class="form-group">
											<label for="#warrantNum" class="col-sm-2 control-label">权证号</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control" name="warrantNum">
											</div>
										</div>
										<div class="form-group">
											<label for="#evaluationValue" class="col-sm-2 control-label">评估值</label>
											<div class="col-sm-4 input-group">
												<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
												<input type="text" class="form-control" name="evaluationValue" >
												<span class="input-group-addon"><i class="fa">元</i></span>
											</div>
										</div>
										<div class="form-group">
											<label for="#warrantHolder" class="col-sm-2 control-label">权证所有人</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control" name="warrantHolder" >
											</div>
										</div>
										<div class="form-group">
											<label for="#collateralAddr" class="col-sm-2 control-label">抵质押物放置位置</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control" name="collateralAddr" >
											</div>
										</div>
										<div class="form-group">
											<label for="#handleDate" class="col-sm-2 control-label">办理抵质押日期</label>
											<div class="col-sm-4 input-group">
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text"
													class="form-control pull-right datepicker-time" name="handleDate">
											</div>
										</div>
										
										<hr />
										<div class="form-group">
											<label for="#name" class="col-sm-2 control-label">保证人名字</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control validate[required]" name="name" data-errormessage="保证人名字不能为空">
											</div>
										</div>
										<div class="form-group">
											<label for="#cardNum" class="col-sm-2 control-label">保证人证件号</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control validate[required]" name="cardNum" data-errormessage="保证人证件号不能为空">
											</div>
										</div>
										<div class="form-group">
											<label for="#phone" class="col-sm-2 control-label">保证人联系方式</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control validate[required]" name="phone" data-errormessage="保证人联系方式不能为空">
											</div>
										</div>
										<div class="form-group">
											<label for="#address" class="col-sm-2 control-label">联系地址</label>
											<div class="col-sm-4 input-group">
												<input type="text" class="form-control validate[required]" name="address" data-errormessage="联系地址不能为空">
											</div>
										</div>
										
										<hr />
										<div class="form-group">
											<label for="#repayDate" class="col-sm-2 control-label">还款日期</label>
											<div class="col-sm-4 input-group date">
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" class="form-control pull-right datepicker validate[required]" name="repayDate"
														 data-errormessage="还款日期不能为空">
											</div>
										</div>
										<div class="form-group">
											<label for="#principal" class="col-sm-2 control-label">还款本金</label>
											<div class="col-sm-4 input-group">
												<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
												<input type="text" class="form-control validate[required,custom[number]]" name="principal" data-errormessage="还款本金只能为数字">
												<span class="input-group-addon"><i class="fa">元</i></span>
											</div>
										</div>
										<div class="form-group">
											<label for="#interest" class="col-sm-2 control-label">还款利息</label>
											<div class="col-sm-4 input-group">
												<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
												<input type="text" class="form-control validate[required,custom[number]]" name="interest" data-errormessage="还款利息只能为数字">
												<span class="input-group-addon"><i class="fa">元</i></span>
											</div>
										</div>
										
										<hr />
										<div class="form-group">
											<label for="#money" class="col-sm-2 control-label">放款金额</label>
											<div class="col-sm-4 input-group">
												<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
												<input type="text" class="form-control validate[required,custom[number]]" name="money" data-errormessage="放款金额只能为数字">
												<span class="input-group-addon"><i class="fa">元</i></span>
											</div>
										</div>
										<div class="form-group">
											<label for="#loanDate" class="col-sm-2 control-label">放款日期</label>
											<div class="col-sm-4 input-group date">
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" class="form-control pull-right datepicker validate[required]" name="loanDate"
														 data-errormessage="放款日期不能为空">
											</div>
										</div>
										<div class="form-group">
											<label for="#debtStartDate" class="col-sm-2 control-label">本笔放款债项开始日</label>
											<div class="col-sm-4 input-group date">
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" class="form-control pull-right datepicker validate[required]" name="debtStartDate"
														 data-errormessage="本笔放款债项开始日不能为空">
											</div>
										</div>
										<div class="form-group">
											<label for="#debtEndDate" class="col-sm-2 control-label">本笔放款债项结束日</label>
											<div class="col-sm-4 input-group date">
												<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" class="form-control pull-right datepicker validate[required]" name="debtEndDate"
														 data-errormessage="本笔放款债项结束日不能为空">
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-sm-5 control-label"></label> 
											<button type="button" class="btn btn-primary saveRecordBtn">保&nbsp;存</button>
										</div>	
									</form>
                                </div>
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        
		<%@include file="../common/footer.jspf" %>   
        <script src="bujs/loan/contract.js" type="text/javascript"></script>       
    </body>
</html>
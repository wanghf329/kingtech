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
                                                                        逾期信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="postLoan/repayinfo">贷后管理</a></li>
                        <li class="active">逾期信息</li> 
                    </ol>
                </section>
                
                <!-- Modal -->
				<div class="modal fade" id="newOverdueinfo" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" >逾期信息录入</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id ="addOverdueinfoForm" action="postLoan/add/overdueInfo" method="POST">
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
										<label for="#overdueMoney" class="col-sm-3 control-label"><i class="text-red">*</i> 逾期金额</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number]]" name = "overdueMoney" id="overdueMoney" 
											data-errormessage-value-missing="逾期金额不能为空"  data-errormessage-custom-error="逾期金额必须是数字" >
										<span class="input-group-addon"><i class="fa ">元</i></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#overdueDate" class="col-sm-3 control-label"><i class="text-red">*</i> 逾期日期</label>
										<div class="col-sm-6 input-group date">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" name="overdueDate"
												readonly id="overdueDate" data-errormessage="还款日期不能为空">
										</div>
									</div>
									
									<div class="form-group">
										<label for="#overdueInterest" class="col-sm-3 control-label"><i class="text-red">*</i> 逾期利息</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number]]" name = "overdueInterest" 
													id="overdueInterest"  data-errormessage-custom-error="逾期利息必须是数字" data-errormessage-value-missing="逾期利息不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#balance" class="col-sm-3 control-label"><i class="text-red">*</i> 贷款余额</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number]]" name = "balance" id="balance" 
														 data-errormessage-custom-error="贷款余额必须是数字" data-errormessage-value-missing="贷款余额不能为空" >
										<span class="input-group-addon"><i class="fa ">元</i></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#remarks" class="col-sm-3 control-label">备注</label>
										<div class="col-sm-6 input-group">
										<textarea  class="form-control validate[maxSize[255]]" name = "remarks" id="remarks" ></textarea>
										
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
                                    <h3 class="box-title">逾期信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addNewOverdueinfo">新增逾期信息</button>  
                                    <table class="table"> 
                                        <tr>
                                            <th>编号</th> 
                                            <th>主合同编号</th>
                                            <th>主合同名称</th>
                                            <th>逾期金额(元)</th> 
                                            <th>逾期日期</th> 
                                            <th>逾期利息(元)</th>
                                            <th>贷款余额(元)</th>
                                            <th>备注</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" varStatus="status" items="${list}">
	                                        <tr>
	                                            <td>${status.index+1}</td>
	                                            <td>${it.loanContractNo}</td>  
	                                            <td>${it.loanContractName}</td>
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><Strong>${it.model.overdueMoney}</Strong></td>  
	                                            <td>${it.model.overdueDate}</td>
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><Strong>${it.model.overdueInterest}</Strong></td>  
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><Strong>${it.model.balance}</Strong></td>  
	                                            <td><a  data-toggle="tooltip" data-placement="right" title="${it.model.remarks}" ><c:if test="${!empty it.model.remarks }">${fn:substring(it.model.remarks,0,3)}...</c:if></a></td>    
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td> 
	                                            	<a href="javascript:void(0)" onclick="getOverdueInfo('${it.model.id}')"><i class="text-blue fa  fa-edit"></i><strong>修改</strong></a>
	                                            </td>
	                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

		<%@include file="../common/footer.jspf" %>   
        <script src="bujs/postloan/overdueinfo.js" type="text/javascript"></script>       
    </body>
</html>
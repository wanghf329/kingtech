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
                                                                        展期还款计划信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="postLoan/repayinfo">贷后管理</a></li>
                        <li class="active">展期还款计划信息</li> 
                    </ol>
                </section>
                
                <!-- Modal -->
				<div class="modal fade" id="editModel" tabindex="-1" role="dialog" 
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">展期还款计划信息录入</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id="form-horizontal" action="postLoan/add/extensionrepayplaninfo" method="POST">
									<input type="hidden" name="id" value="">
									<div class="form-group">
										<label for="#loanContractId" class="col-sm-3 control-label"><i class="text-red">*</i> 合同编号</label>
										<div class="col-sm-6 input-group">
											<select class="form-control loanContractId validate[required]" name="loanContractId" data-errormessage="合同编号不能为空">
												<c:forEach var="it" items="${contracts}">
			  										<option value ="${it.id}">${it.loanContractNo}</option>
												</c:forEach>
											</select>
										</div>
									</div>								
									<div class="form-group"> 
										<label for="#extendCount" class="col-sm-3 control-label"><i class="text-red">*</i> 展期次数</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number]]" maxlength="2" data-errormessage="展期次数只能为数字"
												name="extendCount">
										</div>									
									</div>
									<div class="form-group">
										<label for="#extendTerm" class="col-sm-3 control-label"><i class="text-red">*</i> 展期期限</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required]" name="extendTerm" maxlength="25" data-errormessage="展期期限不能为空"> 
										</div>
									</div>
									<div class="form-group">
										<label for="#repayDate" class="col-sm-3 control-label"><i class="text-red">*</i> 展期后还款日期</label> 
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" name="repayDate"
												readonly data-errormessage="展期后还款日期不能为空">
										</div>
									</div>
									<div class="form-group">
										<label for="#principal" class="col-sm-3 control-label"><i class="text-red">*</i> 展期后还款本金</label>
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-rmb"></i></span> 
											<input type="text" class="form-control validate[required,custom[number]]" data-errormessage="展期后还款本金只能为数字"
												name="principal">
											<span class="input-group-addon"><i class="fa">元</i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="#returnPrincipal" class="col-sm-3 control-label"><i class="text-red">*</i> 已还本金</label>
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-rmb"></i></span> 
											<input type="text" class="form-control validate[required,custom[number]]" data-errormessage="已还本金只能为数字"
												name="returnPrincipal">
											<span class="input-group-addon"><i class="fa">元</i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="#interest" class="col-sm-3 control-label"><i class="text-red">*</i> 展期后还款利息</label>
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-rmb"></i></span> 
											<input type="text" class="form-control validate[required,custom[number]]" data-errormessage="展期后还款利息只能为数字"
												name="interest">
											<span class="input-group-addon"><i class="fa">元</i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="#returnInterest" class="col-sm-3 control-label"><i class="text-red">*</i> 已还利息</label>
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-rmb"></i></span> 
											<input type="text" class="form-control validate[required,custom[number]]" data-errormessage="已还利息只能为数字"
												name="returnInterest">
											<span class="input-group-addon"><i class="fa">元</i></span>
										</div>
									</div>
									<div class="form-group">
										<label for="#status" class="col-sm-3 control-label"><i class="text-red">*</i> 状态</label>
										<div class="col-sm-6 input-group">
											<select class="form-control repayStatus validate[required]" name="status" data-errormessage="状态不能为空">
												<c:forEach var="it" items="${repayStatus}">
			  										<option value ="${it.name()}">${it.getKey()}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="#overdueFlag" class="col-sm-3 control-label"><i class="text-red">*</i> 是否逾期</label>
										<div class="col-sm-6 input-group">
											<select class="form-control overdueFlag validate[required]" name="overdueFlag" data-errormessage="是否逾期不能为空">
												<c:forEach var="it" items="${overdueFlags}">
			  										<option value ="${it.name()}">${it.getKey()}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group hide">  
										<label for="#overdueDays" class="col-sm-3 control-label"><i class="text-red">*</i> 逾期天数</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number]]" data-errormessage="逾期天数只能为数字"
												name="overdueDays">
										</div>
									</div>
								</form>						
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> 
								<button type="button" class="btn btn-primary saveRecordBtn">保存</button>
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
                                    <h3 class="box-title">展期还款计划信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="editRepayExtendPlanBtn">新增展期还款计划信息</button>  
                                    <table class="table"> 
                                        <tr>
                                            <th>编号</th> 
                                            <th>主合同编号</th>
                                            <th>主合同名称</th>
                                            <th>展期次数</th> 
                                            <th>展期期限</th> 
                                            <th>新还款日期</th>
                                            <th>新还款本金</th>
                                            <th>已还本金</th>
                                            <th>新还款利息</th>
                                            <th>已还利息</th>
                                            <th>还款状态</th>
                                            <th>是否逾期</th>
                                            <th>逾期天数</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" varStatus="status" items="${list}">
	                                        <tr>
	                                            <td>${status.index+1}</td>  
	                                            <td>${it.loanContractNo}</td>  
	                                            <td>${it.loanContractName}</td>
	                                            <td>${it.extendCount}</td>
	                                            <td>${it.extendTerm}</td>
	                                            <td>${it.repayDate}</td>
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><Strong>${it.principal}</Strong></td>
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><Strong>${it.returnPrincipal}</Strong></td>
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><Strong>${it.interest}</Strong></td>
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><Strong>${it.returnInterest}</Strong></td>
	                                            <td>${it.status}</td>
	                                            <td>${it.overdueFlag}</td>
	                                            <td>${it.overdueDays}</td>

	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td> 
	                                            	<a href="javascript:void(0)" onclick="getRepayExtendPlan('${it.id}')"><i class="text-blue fa fa-edit"></i><strong>修改</strong></a>
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
        <script src="bujs/postloan/extendrepayplaninfo.js" type="text/javascript"></script>       
    </body>
</html>
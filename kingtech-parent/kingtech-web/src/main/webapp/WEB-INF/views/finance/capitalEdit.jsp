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
                                                                       单笔融资信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">融资信息</a></li>
                        <li class="active">单笔融资信息</li>
                    </ol>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                            <div class="box">
                                <div class="box-header">
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="form-horizontal" action="finance/capital/save" method="POST">
												<input type="hidden" name="id" value="${capital.id}">
												<div class="form-group">
													<label for="#financeNumber" class="col-sm-2 control-label"><i class="text-red">*</i> 融资编号</label>
													<div class="col-sm-4 input-group">
														<input class="form-control validate[required] maxSize[20]" id="financeNumber" value="${capital.financeNumber}" name="financeNumber" data-errormessage="融资编号不能为空"/>
													</div>
												</div>
												<div class="form-group">
													<label for="#financeNumber" class="col-sm-2 control-label"><i class="text-red">*</i> 融资名称</label>
													<div class="col-sm-4 input-group">
														<input class="form-control validate[required] maxSize[20]" id="financeName" value="${capital.financeName}" name="financeName" data-errormessage="融资名称不能为空"/>
													</div>
												</div>
												<div class="form-group">
													<label for="#financeNumber" class="col-sm-2 control-label"><i class="text-red">*</i> 出借方名称</label>
													<div class="col-sm-4 input-group">
														<input class="form-control validate[required] maxSize[20]" id="lender" value="${capital.lender}" name="lender" data-errormessage="出借方名称不能为空"/>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#branch" class="col-sm-2 control-label"><i class="text-red">*</i> 融资渠道</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="channel" name="channel">
															<c:forEach var="it" items="${channels}">
						  										<option value ="${it.name()}" <c:if test="${it eq capital.channel}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#loanAmount" class="col-sm-2 control-label"><i class="text-red">*</i> 融资金额（元）</label> 
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required,custom[number],min[0],max[99999999999]]" name="money" id ="money" value="${capital.money}" data-errormessage="融资金额错误,范围0-99,999,999,999">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#loanAmount" class="col-sm-2 control-label"><i class="text-red">*</i> 应付利息（元）</label> 
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required,custom[number],min[0],max[99999999999]]" name="interest" id ="interest" value="${capital.interest}" data-errormessage="应付利息错误,范围0-99,999,999,999">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#loanAmount" class="col-sm-2 control-label"><i class="text-red">*</i> 手续费（元）</label> 
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required,custom[number],min[0],max[99999999999]]" name="charge" id ="charge" value="${capital.charge}" data-errormessage="手续费错误,范围0-99,999,999,999">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#loanAmount" class="col-sm-2 control-label">合同担保金额（元）</label> 
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[funcCall[guaranteeMoneyValidate], custom[number],min[0],max[99999999999]]" name="guaranteeMoney" id ="guaranteeMoney" value="${capital.guaranteeMoney}" data-errormessage="合同担保金额错误,范围0-99,999,999,999">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#financeNumber" class="col-sm-2 control-label"> 备注</label>
													<div class="col-sm-4 input-group">
														<input class="form-control  maxSize[100]" id="remark" value="${capital.remark}" name="remark" data-errormessage="融资编号不能为空"/>
													</div>
												</div>
												<div class="form-group">
													<label for="#startDate" class="col-sm-2 control-label"><i class="text-red">*</i> 融资日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="financeDate" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${capital.financeDate}"></fmt:formatDate>" data-errormessage="融资日期不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#startDate" class="col-sm-2 control-label"><i class="text-red">*</i> 到期日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="endDate" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${capital.endDate}"></fmt:formatDate>" data-errormessage="到期日期不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#rate" class="col-sm-2 control-label"><i class="text-red">*</i> 融资利率(%)</label> 
													<div class="col-sm-4 input-group"> 
														<input type="text" class="form-control validate[required,custom[number],min[1],max[99]]" name="rate" value="${capital.rate}" data-errormessage="利率范围1-100">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#rateType" class="col-sm-2 control-label"><i class="text-red">*</i> 利率类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="rateType" name="rateType">
															<c:forEach var="it" items="${rateTypes}">
						  										<option value ="${it.name()}" <c:if test="${it eq capital.rateType}">selected</c:if>>${it.getKey()}</option> 
															</c:forEach>
														</select>
													</div>													
												</div>
												
												<div class="form-group">
													<label for="#position" class="col-sm-2 control-label">合同编号</label>
													<div class="col-sm-4 input-group">
														<input type="text"  class="form-control validate[funcCall[loanContractNumberValidate] ]" id="loanContractNumber" name="loanContractNumber"  value="${capital.loanContractNumber}" data-errormessage="合同编号不能为空"/>
													</div>
												</div>
																											
												<div class="form-group">
													<label class="col-sm-5 control-label"></label> 
													<button type="button" class="btn btn-primary saveRecordBtn edit-href">保&nbsp;存</button>
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
        <script src="bujs/finance/capital.js" type="text/javascript"></script>  
        <script type="text/javascript">
        	var borrowerId = '${capital.id}';
        	var canEdit = "${capital==null or capital.pushStatus=='INITATION' or capital.pushStatus=='FAILED'}";
        </script>     
    </body>
</html>
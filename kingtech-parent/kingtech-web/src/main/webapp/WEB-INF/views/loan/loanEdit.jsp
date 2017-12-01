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
                                    <h3 class="box-title">合同信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="form-horizontal" action="loan/save" method="POST">
												<input type="hidden" name="id" value="${contract.id}">
												<div class="form-group">
													<label for="#branch" class="col-sm-2 control-label"><i class="text-red">*</i> 所属机构</label> 
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="branch" name="branch">
															<c:forEach var="it" items="${branchs}">
						  										<option value ="${it.id}">${it.corporateName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="#branch" class="col-sm-2 control-label"><i class="text-red">*</i> 借款人类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="borrowerType" name="borrowerType">
															<c:forEach var="it" items="${borrowerType}">
						  										<option value ="${it.name()}" <c:if test="${it eq contract.borrowerType}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="#branch" class="col-sm-2 control-label"><i class="text-red">*</i> 借款人</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="borrowerId" name="borrowerId" data-errormessage="借款人不能为空">
						  									
														</select>
													</div>
												</div>																									
												<div class="form-group">
													<label for="#loanContractId" class="col-sm-2 control-label"><i class="text-red">*</i> 合同编号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="loanContractId" value="${contract.loanContractId}" data-errormessage="合同编号不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#floanContractName" class="col-sm-2 control-label"><i class="text-red">*</i> 合同名称</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="loanContractName" value="${contract.loanContractName}" data-errormessage="合同名称不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#customerId" class="col-sm-2 control-label"><i class="text-red">*</i> 营业执照号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="customerId" value="${contract.customerId}" data-errormessage="营业执照号不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#loanAmount" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款金额（元）</label> 
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required,custom[number],min[1000],max[99999999999]]" name="loanAmount" value="${contract.loanAmount}" data-errormessage="贷款金额错误,范围1,000-99,999,999,999">
													</div>
												</div>
												<div class="form-group">
													<label for="#periodType" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款期限类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="periodType" name="periodType" data-errormessage="贷款期限类型不能为空">
															<c:forEach var="it" items="${periodType}">
						  										<option value ="${it.name()}" <c:if test="${it.name() eq contract.periodType}"></c:if> >${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>										
												<div class="form-group">
													<label for="#periodTerm" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款期限</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="periodTerm"  value="${contract.periodTerm}"  data-errormessage="贷款期限不能为空">
													</div>
												</div>			
												<div class="form-group">
													<label for="#loanStartDate" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款开始日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="loanStartDate" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${contract.loanStartDate}"></fmt:formatDate>" data-errormessage="贷款开始日期不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#loanEndDate" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款截止日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="loanEndDate" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${contract.loanEndDate}"></fmt:formatDate>" data-errormessage="贷款截止日期不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#rateType" class="col-sm-2 control-label"><i class="text-red">*</i> 利率类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="rateType" name="rateType">
															<c:forEach var="it" items="${rateType}">
						  										<option value ="${it.name()}">${it.getKey()}</option> 
															</c:forEach>
														</select>
													</div>													
												</div>	 
												<div class="form-group">
													<label for="#rate" class="col-sm-2 control-label"><i class="text-red">*</i> 利率(%)</label> 
													<div class="col-sm-4 input-group"> 
														<input type="text" class="form-control validate[required,custom[number],min[1],max[99]]" name="rate" value="${contract.rate}" data-errormessage="利率范围1-100">
													</div>
												</div>
												<div class="form-group">
													<label for="#purpose" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款用途</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="purpose" name="purpose">
															<c:forEach var="it" items="${purpose}">
						  										<option value ="${it.name()}">${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>	
												</div>										
												<div class="form-group">
													<label for="#industry" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款投向</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="industry" name="industry">
															<c:forEach var="it" items="${industry}">
						  										<option value ="${it.name()}">${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>														
												</div>								
												<div class="form-group">
													<label for="#loanType" class="col-sm-2 control-label"><i class="text-red">*</i> 贷款方式</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="loanType" name="loanType">
															<c:forEach var="it" items="${loanType}"> 
						  										<option value ="${it.name()}" <c:if test="${it eq contract.loanType}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>													
												</div>								
												<div class="form-group">
													<label for="#unionFlag" class="col-sm-2 control-label"><i class="text-red">*</i> 是否多户联合贷款</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="unionFlag" name="unionFlag">
															<c:forEach var="it" items="${unionFlag}">
						  										<option value ="${it.name()}">${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>														
												</div>
												<div class="form-group">
													<label for="#payType" class="col-sm-2 control-label"><i class="text-red">*</i> 还款方式</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="payType" name="payType">
															<c:forEach var="it" items="${payType}">
						  										<option value ="${it.name()}">${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>														
												</div>
												<div class="form-group">
													<label for="#signDate" class="col-sm-2 control-label"><i class="text-red">*</i> 合同签订日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="signDate" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${contract.signDate}"></fmt:formatDate>" data-errormessage="合同签订日期不能为空">
													</div>													
												</div>																								
												<div class="form-group">
													<label for="#repaySource" class="col-sm-2 control-label"><i class="text-red">*</i> 还款来源</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="repaySource" value="${contract.repaySource}" data-errormessage="还款来源不能为空">
													</div>
												</div>
												<div class="form-group"> 
													<label for="#status" class="col-sm-2 control-label"><i class="text-red">*</i> 合同状态</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="status" name="status">
															<c:forEach var="it" items="${status}">
						  										<option value ="${it.name()}">${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>													
												</div>
												<div class="form-group">
													<label for="#isExtend" class="col-sm-2 control-label"><i class="text-red">*</i> 是否展期</label>
													<div class="col-sm-4 input-group">
														<select class="form-control" id="isExtend" name="isExtend">
															<c:forEach var="it" items="${isExtend}">
						  										<option value ="${it.name()}">${it.getKey()}</option>
															</c:forEach>
														</select>
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
        <script type="text/javascript">
        	var borrowerId = '${contract.borrowerId}';
        	var canEdit = "${contract.pushStatus=='INITATION' or contract.pushStatus=='FAILED'}";
        </script>     
    </body>
</html>
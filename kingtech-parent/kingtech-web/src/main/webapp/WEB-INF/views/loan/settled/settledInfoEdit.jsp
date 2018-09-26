<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>数据对接平台</title>
        <%@include file="../../common/head.jspf" %> 
    </head>
    <body class="skin-blue">
		<%@include file="../../common/logout.jspf" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../../common/sidebar.jspf" %>
            <aside class="right-side">                
                <section class="content-header">
                    <h1>
                                                                        放款信息录入
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构管理</a></li>
                        <li class="active"> 放款信息</li>
                    </ol>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title"> 放款信息</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body col-sm-10">
											<form class="form-horizontal" id="form-horizontal" action="loan/supplement/addSettledInfo" method="POST">
													<div class="form-group"> 
														<label for="#loanContractId" class="col-sm-4 control-label"><i class="text-red">*</i> 合同编号</label>
														<div class="col-sm-7 input-group">
															<select class="form-control validate[required]" name="loanContractId" data-errormessage="合同不能为空">
																<option value ="">-请选择-</option>  
																<c:forEach var="it" items="${loanContractIds}">
							  										<option value ="${it.id}" <c:if test="${it.id eq settledInfo.loanContractId}">selected</c:if>>${it.contractName}</option> 
																</c:forEach>
															</select>
														</div>
													</div>														
													<div class="form-group">
														<label for="#money" class="col-sm-4 control-label"><i class="text-red">*</i> 放款金额</label>
														<div class="col-sm-7 input-group">
															<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
															<input type="text" class="form-control validate[required,custom[number]]" name="money" data-errormessage="放款金额只能为数字"
																	value="${settledInfo.money}">
															<span class="input-group-addon"><i class="fa">元</i></span>
														</div>
													</div>
													<div class="form-group">
														<label for="#loanTime" class="col-sm-4 control-label"><i class="text-red">*</i> 放款日期</label>
														<div class="col-sm-7 input-group date">
															<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
															<input type="text" class="form-control pull-right datepicker validate[required]" readonly name="loanTime" 
																	data-errormessage="放款日期不能为空"
																	value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${settledInfo.loanTime}"></fmt:formatDate>">
														</div>
													</div>
													<div class="form-group">
														<label for="#startDate" class="col-sm-4 control-label"><i class="text-red">*</i> 本笔放款债项开始日</label>
														<div class="col-sm-7 input-group date">
															<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
															<input type="text" class="form-control pull-right datepicker validate[required]" readonly name="startDate"
																	 data-errormessage="本笔放款债项开始日不能为空"
																	 value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${settledInfo.startDate}"></fmt:formatDate>">
														</div>
													</div>
													<div class="form-group">
														<label for="#endDate" class="col-sm-4 control-label"><i class="text-red">*</i> 本笔放款债项结束日</label>
														<div class="col-sm-7 input-group date">
															<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
															<input type="text" class="form-control pull-right datepicker validate[required]" readonly name="endDate" 
																	 data-errormessage="本笔放款债项结束日不能为空"
																	 value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${settledInfo.endDate}"></fmt:formatDate>">
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
		<%@include file="../../common/footer.jspf" %>   
        <script src="bujs/loan/settled.js" type="text/javascript"></script> 
        <script type="text/javascript"> 
        	var canEdit = '${canEdit}';
        </script>          
    </body>
</html>
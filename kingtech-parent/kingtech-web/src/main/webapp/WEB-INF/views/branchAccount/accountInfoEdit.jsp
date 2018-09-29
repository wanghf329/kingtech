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
                                                                     机构银行账户信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构银行账户信息</a></li>
                        <li class="active">机构银行账户信息</li>
                    </ol>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                            <div class="box">
                                <div class="box-header">
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="form-horizontal" action="branchAccount/accountInfo/save" method="POST">
												<input type="hidden" name="id" value="${accountInfo.id}">
												<div class="form-group">
													<label for="#financeNumber" class="col-sm-2 control-label"><i class="text-red">*</i> 银行名称</label>
													<div class="col-sm-4 input-group">
														<input class="form-control validate[required] maxSize[30]" id="bank" value="${accountInfo.bank}" name="bank" data-errormessage="银行名称不能为空"/>
													</div>
												</div>
												<div class="form-group">
													<label for="#financeNumber" class="col-sm-2 control-label"><i class="text-red">*</i> 银行账号</label>
													<div class="col-sm-4 input-group">
														<input class="form-control validate[required] maxSize[30]" id="account" value="${accountInfo.account}" name="account" data-errormessage="银行账号不能为空"/>
													</div>
												</div>
												<div class="form-group">
													<label for="#branch" class="col-sm-2 control-label"><i class="text-red">*</i> 账户类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="type" name="type">
															<c:forEach var="it" items="${accountTypes}">
						  										<option value ="${it.name()}" <c:if test="${it eq accountInfo.type}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#branch" class="col-sm-2 control-label"><i class="text-red">*</i> 账户类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="accountStatus" name="accountStatus">
															<c:forEach var="it" items="${accountStatuses}">
						  										<option value ="${it.name()}" <c:if test="${it eq accountInfo.accountStatus}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#startDate" class="col-sm-2 control-label"><i class="text-red">*</i> 开户日期 </label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="openTime" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${accountInfo.openTime}"></fmt:formatDate>" data-errormessage="开户日期 不能为空">
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
        <script src="bujs/branchAccount/accountInfo.js" type="text/javascript"></script>  
        <script type="text/javascript">
        	var canEdit = "${accountInfo==null or accountInfo.pushStatus=='SUCCESS' or accountInfo.pushStatus=='INITATION'}";
        </script> 
    </body>
</html>
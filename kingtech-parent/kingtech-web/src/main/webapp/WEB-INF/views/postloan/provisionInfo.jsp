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
                                                                        计提信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="postLoan/repayinfo">贷后管理</a></li>
                        <li class="active">计提信息</li> 
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
								<h4 class="modal-title" id="myModalLabel">计提信息录入</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id="form-horizontal" action="postLoan/provision/edit" method="POST"> 
									<input type="hidden" name="id" value="">
									<div class="form-group">
										<label for="#provisionDate" class="col-sm-3 control-label"><i class="text-red">*</i> 计提日期</label>
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" 
											readonly name="provisionDate" data-errormessage="计提日期不能为空">
										</div>
									</div>
									<div class="form-group">
										<label for="#provisionScale" class="col-sm-3 control-label"><i class="text-red">*</i> 计提比例</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[100]]" data-errormessage="计提比例，最大100" 
												name="provisionScale"> 
											<span class="input-group-addon"><i class="fa">%</i></span>
										</div> 
									</div>								
									<div class="form-group"> 
										<label for="#provisionMoney" class="col-sm-3 control-label"><i class="text-red">*</i> 计提金额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="计提金额只能为数字"
												name="provisionMoney"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>								
									</div>
									<div class="form-group">
										<label for="#loanClassification" class="col-sm-3 control-label"><i class="text-red">*</i> 贷款分类</label>
										<div class="col-sm-6 input-group"> 
											<select class="form-control validate[required]" id="loanClassification" name="loanClassification">
												<c:forEach var="it" items="${loanClassificationEnum}">
			  										<option value ="${it.name()}">${it.getKey()}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group"> 
										<label for="#balance" class="col-sm-3 control-label"><i class="text-red">*</i> 贷款余额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="贷款余额只能为数字"
												name="balance"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
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
                                    <h3 class="box-title">计提信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="editProvisiontBtn">新增计提信息</button>  
                                    <table class="table"> 
                                        <tr>
                                            <th>编号</th> 
                                            <th>计提日期</th> 
                                            <th>计提比例</th> 
                                            <th>计提金额(万元)</th>
                                            <th>贷款分类</th>
                                            <th>贷款余额(万元)</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" varStatus="status" items="${list}">
	                                        <tr>
	                                        	<td>${status.index+1}</td>
	                                            <td><fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${it.provisionDate}"></fmt:formatDate></td>
	                                            <td><strong class="text-green">${it.provisionScale}%</strong></td>  
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.provisionMoney}</strong></td>
	                                            <td>${it.loanClassification.getKey()}</td> 
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.balance}</strong></td>
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td> 
	                                            	<a href="javascript:void(0)" class="provision-edit" data-id="${it.id}"><i class="text-blue fa  fa-edit"></i><strong>修改</strong></a>
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
        <script src="bujs/postloan/provisioninfo.js" type="text/javascript"></script>       
    </body>
</html>
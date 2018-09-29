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
										<label for="#dateMonth" class="col-sm-3 control-label"><i class="text-red">*</i> 计提日期</label>
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" 
											readonly name="dateMonth" data-errormessage="计提日期不能为空">
										</div>
									</div>
									
									<div class="form-group"> 
										<label for="#normalBalance" class="col-sm-3 control-label"><i class="text-red">*</i> 正常类贷款余额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="正常类贷款余额只能为数字"
												name="normalBalance"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>	
									</div>
									<div class="form-group">
										<label for="#normalRate" class="col-sm-3 control-label"><i class="text-red">*</i> 正常类计提比例</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[100]]" data-errormessage="正常类计提比例，最大100" 
												name="normalRate"> 
											<span class="input-group-addon"><i class="fa">%</i></span>
										</div> 
									</div>								
									<div class="form-group"> 
										<label for="#normalReal" class="col-sm-3 control-label"><i class="text-red">*</i> 正常类计提金额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="正常类计提金额只能为数字"
												name="normalReal"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>								
									</div>
									
									<div class="form-group"> 
										<label for="#followBalance" class="col-sm-3 control-label"><i class="text-red">*</i> 关注类贷款余额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="关注类贷款余额只能为数字"
												name="followBalance"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>	
									</div>
									<div class="form-group">
										<label for="#followRate" class="col-sm-3 control-label"><i class="text-red">*</i> 关注类计提比例</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[100]]" data-errormessage="关注类计提比例，最大100" 
												name="followRate"> 
											<span class="input-group-addon"><i class="fa">%</i></span>
										</div> 
									</div>								
									<div class="form-group"> 
										<label for="#followReal" class="col-sm-3 control-label"><i class="text-red">*</i> 关注类计提金额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="关注类计提金额只能为数字"
												name="followReal"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>								
									</div>
									
									<div class="form-group"> 
										<label for="#minorBalance" class="col-sm-3 control-label"><i class="text-red">*</i> 次级类贷款余额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="次级类贷款余额只能为数字"
												name="minorBalance"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>	
									</div>
									<div class="form-group">
										<label for="#minorRate" class="col-sm-3 control-label"><i class="text-red">*</i> 次级类计提比例</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[100]]" data-errormessage="次级类计提比例，最大100" 
												name="minorRate"> 
											<span class="input-group-addon"><i class="fa">%</i></span>
										</div> 
									</div>								
									<div class="form-group"> 
										<label for="#minorReal" class="col-sm-3 control-label"><i class="text-red">*</i> 次级类计提金额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="次级类计提金额只能为数字"
												name="minorReal"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>								
									</div>
									
									<div class="form-group"> 
										<label for="#suspiciousBalance" class="col-sm-3 control-label"><i class="text-red">*</i> 可疑类贷款余额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="可疑类贷款余额只能为数字"
												name="suspiciousBalance"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>	
									</div>
									<div class="form-group">
										<label for="#suspiciousRate" class="col-sm-3 control-label"><i class="text-red">*</i> 可疑类计提比例</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[100]]" data-errormessage="可疑类计提比例，最大100" 
												name="suspiciousRate"> 
											<span class="input-group-addon"><i class="fa">%</i></span>
										</div> 
									</div>								
									<div class="form-group"> 
										<label for="#suspiciousReal" class="col-sm-3 control-label"><i class="text-red">*</i> 可疑类计提金额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="可疑类计提金额只能为数字"
												name="suspiciousReal"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>								
									</div>
									
									<div class="form-group"> 
										<label for="#lossBalance" class="col-sm-3 control-label"><i class="text-red">*</i> 损失类贷款余额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="损失类贷款余额只能为数字"
												name="lossBalance"> 
											<span class="input-group-addon"><i class="fa">万元</i></span>
										</div>	
									</div>
									<div class="form-group">
										<label for="#lossRate" class="col-sm-3 control-label"><i class="text-red">*</i> 损失类计提比例</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[100]]" data-errormessage="损失类计提比例，最大100" 
												name="lossRate"> 
											<span class="input-group-addon"><i class="fa">%</i></span>
										</div> 
									</div>								
									<div class="form-group"> 
										<label for="#lossReal" class="col-sm-3 control-label"><i class="text-red">*</i> 损失类计提金额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="损失类计提金额只能为数字"
												name="lossReal"> 
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
                                    <table class="table table-bordered" role="grid" > 
                                        <tr>
                                            <th>编号</th> 
                                            <th>计提月份</th> 
                                            <th>正常类贷款余额(万元)</th>
                                            <th>正常类计提比例</th> 
                                            <th>正常类计提金额(万元)</th>
                                            <th>关注类贷款余额(万元)</th>
                                            <th>关注类计提比例</th> 
                                            <th>关注类计提金额(万元)</th>
                                            <th>次级类贷款余额(万元)</th>
                                            <th>次级类计提比例</th> 
                                            <!-- <th>次级类计提金额(万元)</th>
                                            <th>可疑类贷款余额(万元)</th>
                                            <th>可疑类计提比例</th> 
                                            <th>可疑类计提金额(万元)</th>
                                            <th>损失类贷款余额(万元)</th>
                                            <th>损失类计提比例</th> 
                                            <th>损失类计提金额(万元)</th> -->
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" varStatus="status" items="${list}">
	                                        <tr>
	                                        	<td>${status.index+1}</td>
	                                            <td><fmt:formatDate type="date" pattern = "yyyy-MM" value="${it.dateMonth}"></fmt:formatDate></td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.normalBalance}</strong></td>
	                                            <td><strong class="text-green">${it.normalRate}%</strong></td>  
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.normalReal}</strong></td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.followBalance}</strong></td>
	                                            <td><strong class="text-green">${it.followRate}%</strong></td>  
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.followReal}</strong></td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.minorBalance}</strong></td>
	                                            <td><strong class="text-green">${it.minorRate}%</strong></td>  
	                                            <%--<td><strong class="text-red"><i class="fa fa-rmb"></i>${it.minorReal}</strong></td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.suspiciousBalance}</strong></td>
	                                            <td><strong class="text-green">${it.suspiciousRate}%</strong></td>  
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.suspiciousReal}</strong></td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.lossBalance}</strong></td>
	                                            <td><strong class="text-green">${it.lossRate}%</strong></td>  
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.lossReal}</strong></td>
	                                             --%>
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='DELETEING'}"><span class="text-blue"><i class="text-green fa fa-asterisk"></i>删除处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}">
	                                            		<a href="javascript:void(0)" class="provision-edit" data-id="${it.id}"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>
	                                            		<a href="javascript:void(0)" class="provision-delete" data-id="${it.id}"><i class="text-red fa fa-times"></i><strong>删除</strong></a>
	                                            	</c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS' || it.pushStatus=='DELETEING'}">
	                                            		<a href="javascript:void(0)" class="provision-edit" data-id="${it.id}"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>
	                                            	</c:if>
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
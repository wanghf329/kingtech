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
                                                                        坏账信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">合同管理</a></li>
                        <li class="active">坏账信息</li> 
                    </ol>
                </section>
                
                
                <!-- Modal -->
				<div class="modal fade" id="badDebtsInfo" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" >坏账信息录入</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id = "addbadDebtsInfoForm" action="postLoan/add/baddebtsInfo" method="POST">
									<input type="hidden" name="id" value="">
									<div class="form-group">
										<label for="#loanContractId" class="col-sm-3 control-label"><i class="text-red">*</i>合同编号</label> 
										<div class="col-sm-6 input-group">
											<select class="form-control validate[required]" id="loanContractId" name="loanContractId">
												<c:forEach var="it" items="${contracts}">
			  										<option value ="${it.id}">${it.contractNumber}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#badMoney" class="col-sm-3 control-label"><i class="text-red">*</i>坏账金额</label>
										<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
										<input type="text" class="form-control validate[required,custom[number]]" name = "badMoney" id="badMoney" 
											data-errormessage-value-missing="坏账金额不能为空"  data-errormessage-custom-error="坏账金额必须是数字" >
										<span class="input-group-addon"><i class="fa ">元</i></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#lossDate" class="col-sm-3 control-label"><i class="text-red">*</i> 定损日期</label>
										<div class="col-sm-6 input-group date">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" name="lossDate"
												readonly id="lossDate" data-errormessage="定损日期不能为空">
										</div>
									</div>
									
									<div class="form-group">
										<label for="#badType" class="col-sm-3 control-label"><i class="text-red">*</i> 坏账类型</label>
										<div class="col-sm-6 input-group"> 
											<select class="form-control validate[required]" id="badType" name="badType">
												<c:forEach var="it" items="${badTypes}">
			  										<option value ="${it.name()}">${it.getKey()}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="#followUp" class="col-sm-3 control-label"><i class="text-red">*</i>后续工作</label>
										<div class="col-sm-6 input-group">
										<textarea  class="form-control validate[required,maxSize[255]]" name = "followUp" id="followUp" 
											data-errormessage-value-missing="后续工作不能为空"  data-errormessage-range-overflow="后续工作不能超过255个字符" >
										</textarea>
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
                                    <h3 class="box-title">坏账信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addbaddebtInfoBtn">新增坏账信息</button>  
                                    <table class="table table-bordered" role="grid" > 
                                        <tr>
                                        	<th>编号</th> 
                                            <th>主合同编号</th>
                                            <th>主合同名称</th>  
                                            <th>坏账金额(元)</th>
                                            <th>定损日期</th>
                                            <th>后续工作</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" varStatus="status"  items="${list}">
	                                        <tr>
	                                            <td>${status.index+1}</td>
	                                            <td>${it.contractNumber}</td>  
	                                            <td>${it.contractName}</td>  
	                                            <td class="text-red"><i class="text-red fa  fa-rmb"></i><strong>${it.model.badMoney}</strong></td>
	                                            <td>${it.model.lossDate}</td>
	                                            
	                                            <td><a  data-toggle="tooltip" data-placement="right" title="${it.model.followUp}" ><c:if test="${!empty it.model.followUp }">${fn:substring(it.model.followUp,0,10)}...</c:if></a></td>
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='DELETEING'}"><span class="text-blue"><i class="text-green fa fa-asterisk"></i>删除处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}">
	                                            		<a href="javascript:void(0)" onclick="getBaddebtInfo('${it.model.id}')"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>
	                                            		<a href="javascript:void(0)" onclick="deleteBaddebtInfo('${it.model.id}')"><i class="text-red fa fa-times"></i><strong>删除</strong></a>
	                                            	</c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS' || it.pushStatus=='DELETEING'}">
	                                            		<a href="javascript:void(0)" onclick="getBaddebtInfo('${it.model.id}')"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>
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
        <script src="bujs/postloan/baddebtsinfo.js" type="text/javascript"></script>       
    </body>
</html>
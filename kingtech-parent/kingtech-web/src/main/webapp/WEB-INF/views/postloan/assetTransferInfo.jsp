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
                                                                        资产转让信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">合同管理</a></li>
                        <li class="active">资产转让信息</li> 
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
								<h4 class="modal-title" id="myModalLabel">资产转让信息录入</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id="form-horizontal" action="postLoan/assetTransfer/edit" method="POST"> 
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
									<div class="form-group" >
										<label for="#transferNumber" class="col-sm-3 control-label"><i class="text-red">*</i> 转让编号</label> 
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required]" name="transferNumber" data-errormessage="转让编号不能为空">
										</div>   
									</div>
									<div class="form-group"> 
										<label for="#transferMoney" class="col-sm-3 control-label"><i class="text-red">*</i> 转让金</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="转让金只能为数字"
												name="transferMoney"> 
											<span class="input-group-addon"><i class="fa">元</i></span>
										</div>	
									</div>
									<div class="form-group"> 
										<label for="#originalMoney" class="col-sm-3 control-label"><i class="text-red">*</i> 原资产金额</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="原资产金额只能为数字"
												name="originalMoney"> 
											<span class="input-group-addon"><i class="fa">元</i></span>
										</div>	
									</div>
									<div class="form-group"> 
										<label for="#discountMoney" class="col-sm-3 control-label"><i class="text-red">*</i> 溢价\折价金</label>
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required,custom[number],max[999999999]]" data-errormessage="溢价\折价金只能为数字"
												name="discountMoney"> 
											<span class="input-group-addon"><i class="fa">元</i></span>
										</div>	
									</div>
									<div class="form-group" >
										<label for="#acceptUnit" class="col-sm-3 control-label"><i class="text-red">*</i> 受让单位</label> 
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required]" name="acceptUnit" data-errormessage="受让单位不能为空">
										</div>   
									</div>
									<div class="form-group" >
										<label for="#protocol" class="col-sm-3 control-label"><i class="text-red">*</i> 转让协议编号</label> 
										<div class="col-sm-6 input-group">
											<input type="text" class="form-control validate[required]" name="protocol" data-errormessage="转让协议编号不能为空">
										</div>   
									</div>
									<div class="form-group">
										<label for="#transferDate" class="col-sm-3 control-label"><i class="text-red">*</i> 转让日期</label>
										<div class="col-sm-6 input-group">
											<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" class="form-control pull-right datepicker validate[required]" 
											readonly name="transferDate" data-errormessage="转让日期不能为空">
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
                                    <h3 class="box-title">资产转让列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="editAssetTransferBtn">新增资产转让</button>  
                                    <table class="table table-bordered" role="grid" > 
                                        <tr>
                                            <th>编号</th> 
                                            <th>主合同编号</th>
                                            <th>主合同名称</th>
                                            <th>转让编号</th> 
                                            <th>转让金(元)</th>
                                            <th>原资产金额(元)</th>
                                            <th>溢价\折价金(元)</th>
                                            <th>受让单位</th> 
                                            <th>转让协议编号</th>
                                            <th>转让日期</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" varStatus="status" items="${list}">
	                                        <tr>
	                                        	<td>${status.index+1}</td>
	                                        	<td>${it.contractNumber}</td>  
	                                            <td>${it.contractName}</td> 
	                                            <td>${it.model.transferNumber}</td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.model.transferMoney}</strong></td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.model.originalMoney}</strong></td>
	                                            <td><strong class="text-red"><i class="fa fa-rmb"></i>${it.model.discountMoney}</strong></td>
	                                            <td>${it.model.acceptUnit}</td>
	                                            <td>${it.model.protocol}</td>
	                                            <td>${it.model.transferDate}</td>
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='DELETEING'}"><span class="text-blue"><i class="text-green fa fa-asterisk"></i>删除处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td> 
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}">
	                                            		<a href="javascript:void(0)" class="assetTransfer-edit" data-id="${it.model.id}"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>
	                                            		<a href="javascript:void(0)" class="assetTransfer-delete" data-id="${it.model.id}"><i class="text-red fa fa-times"></i><strong>删除</strong></a>
	                                            	</c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS' || it.pushStatus=='DELETEING'}">
	                                            		<a href="javascript:void(0)" class="assetTransfer-edit" data-id="${it.model.id}"><i class="text-gray fa fa-eye"></i><strong>查看</strong></a>
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
        <script src="bujs/postloan/assettransferinfo.js" type="text/javascript"></script>       
    </body>
</html>
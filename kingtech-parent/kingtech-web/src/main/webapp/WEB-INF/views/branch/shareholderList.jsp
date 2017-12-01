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
                                                                        股东列表
                        <small>股东列表预览</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构管理</a></li>
                        <li class="active">股东列表</li>
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
							<h4 class="modal-title" id="myModalLabel">机构股东信息录入</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form-horizontal" action="branch/add/shareholder" method="POST">
								<input type="hidden" name="id" value="">
								<div class="form-group">
									<label for="#branch" class="col-sm-3 control-label"><i class="text-red">*</i> 所属机构</label>
									<div class="col-sm-6 input-group">
										<select class="form-control validate[required]" id="branch" name="branch" data-errormessage="机构不能为空">
											<c:forEach var="it" items="${branchs}">
		  										<option value ="${it.id}">${it.corporateName}</option>
											</c:forEach>
										</select>
									</div>
								</div>								
								<div class="form-group">
									<label for="#partnerType" class="col-sm-3 control-label"><i class="text-red">*</i> 股东类型</label>
									<div class="col-sm-6 input-group">
					                  <div class="radio">
					                      <input type="radio" name="partnerType" value="1" checked>自然人股东
					                   	  <input type="radio" name="partnerType" value="2">企业股东 
					                  </div>
									</div>
								</div>
								<div class="form-group">
									<label for="#holder" class="col-sm-3 control-label"><i class="text-red">*</i> 持股人</label>
									<div class="col-sm-6 input-group">
										<input type="text" class="form-control validate[required]" name="holder" data-errormessage="持股人不能为空"> 
									</div>
								</div>
								<div class="form-group">
									<label for="#holdingScale" class="col-sm-3 control-label"><i class="text-red">*</i> 持股比例</label>
									<div class="col-sm-6 input-group">
										<input type="text" class="form-control validate[required,custom[number],max[100]]" data-errormessage="持股比例只能为数字"
											name="holdingScale">
										<span class="input-group-addon"><i class="fa">%</i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="#contributionAmount" class="col-sm-3 control-label"><i class="text-red">*</i> 出资金额</label>
									<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-rmb"></i></span> 
										<input type="text" class="form-control validate[required,custom[number]]" data-errormessage="出资金额只能为数字"
											name="contributionAmount">
										<span class="input-group-addon"><i class="fa">万元</i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="#joinTime" class="col-sm-3 control-label"><i class="text-red">*</i> 入股时间</label> 
									<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker validate[required]" name="joinTime"
											readonly data-errormessage="入股时间不能为空">
									</div>
								</div>
								
								<div class="form-group" id="sexDiv">
									<label for="#gender" class="col-sm-3 control-label"><i class="text-red">*</i> 性别</label>
									<div class="col-sm-6">
										<div class="radio">  
										  <input type="radio" name="gender" value="1">男	
					                      <input type="radio" name="gender" value="2">女     
					                  	</div>
									</div>
								</div>
								
								<div class="form-group">
									<label for="#quitTime" class="col-sm-3 control-label">退出时间</label>
									<div class="col-sm-6 input-group">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker" name="quitTime" readonly> 
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
                                    <h3 class="box-title">股东列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" id="editModelBtn" data-toggle="modal">新增股东</button>   
                                    <table class="table">
                                        <tr>
                                            <th>持股人</th>
                                            <th>股东类型</th> 
                                            <th>持股比例</th>
                                            <th>出资金额（万元）</th>
                                            <th>入股时间</th>
                                            <th>性别</th>
                                            <th>离职时间</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" items="${list}">
	                                        <tr>
	                                            <td>${it.holder}</td>  
	                                            <td>${it.partnerType.getTips()}</td>
	                                            <td class="text-green">${it.holdingScale}%</td>
	                                            <td class="text-red"><i class="fa fa-jpy"></i><strong>${it.contributionAmount}</strong></td>
	                                            <td><fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${it.joinTime}"></fmt:formatDate></td>
	                                            <td>${it.gender.getTips()}</td>
	                                            <td><fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${it.quitTime}"></fmt:formatDate></td>
	                                            <td>
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>	                                            
	                                            <td>
	                                            	<a href="javascript:void(0)" onclick="getShareHolder('${it.id}')"><i class="text-blue fa  fa-edit"></i><strong>修改</strong></a>
	                                            </td>
	                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <%@include file="../common/footer.jspf" %>
        <script src="bujs/shareholder/main.js" type="text/javascript"></script>
    </body>
</html>
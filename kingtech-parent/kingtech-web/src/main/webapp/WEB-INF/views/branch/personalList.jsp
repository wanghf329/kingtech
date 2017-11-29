<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>AdminLTE | Simple Tables</title>
        <%@include file="../common/head.jspf" %> 
    </head>
    <body class="skin-blue">
		<%@include file="../common/logout.jspf" %> 
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <%@include file="../common/sidebar.jspf" %>
            <aside class="right-side">                
                <section class="content-header">
                    <h1>
                                                                        机构人员列表
                        <small>机构人员信息预览</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构管理</a></li>
                        <li class="active">机构人员列表</li>
                    </ol>
                </section>

			<!-- Modal -->
			<div class="modal fade" id="rechargeModel" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">机构人员录入</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="form-horizontal" action="branch/add/employee" method="POST">
								<input type="hidden" name="id" value="">
								<div class="form-group" >
									<label for="#name" class="col-sm-2 control-label">姓名</label>
									<div class="col-sm-8 input-group">
										<input type="text" class="form-control validate[required]" name="name" data-errormessage="姓名不能为空">
									</div>
								</div>
								<div class="form-group">
									<label for="#phone" class="col-sm-2 control-label">电话</label>
									<div class="col-sm-8 input-group">
										<input type="text" class="form-control" name="phone">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#email" class="col-sm-2 control-label">电子邮件</label>
									<div class="col-sm-8 input-group">
									<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
									<input type="text" class="form-control " name="email">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#postalAddress" class="col-sm-2 control-label">通讯地址</label>
									<div class="col-sm-8 input-group">
									<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
									<input type="text" class="form-control validate[required]" name="postalAddress" data-errormessage="通讯地址不能为空">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#department" class="col-sm-2 control-label">部门</label>
									<div class="col-sm-8 input-group">
									<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
									<input type="text" class="form-control validate[required]" name="department" data-errormessage="部门不能为空">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#sex" class="col-sm-2 control-label">性别</label>
									<div class="col-sm-8 input-group">
					                  <div class="radio">
					                      <input type="radio" name="sex" value="1" checked="">男
					                   	  <input type="radio" name="sex" value="2" >女
					                  </div>
									</div>
								</div>
								
								<div class="form-group">
									<label for="#idNumber" class="col-sm-2 control-label">身份证号</label>
									<div class="col-sm-8 input-group">
									<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
									<input type="text" class="form-control validate[required]" name="idNumber" data-errormessage="身份证号不能为空">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#education" class="col-sm-2 control-label">学历</label>
									<div class="col-sm-8 input-group ">
										<select class="form-control" name="education">
						                   <option>小学</option>
						                   <option>高中</option>
						                   <option>大学</option>
						                   <option>研究生</option>
						                   <option>博士</option>
						                 </select>
									</div>
								</div>
								
								<div class="form-group">
									<label for="#executiveFlag" class="col-sm-2 control-label">是否董监高</label>
									<div class="col-sm-8 input-group">
										<div class="radio">
					                      <input type="radio" name="executiveFlag" value="1" checked="">是
					                   	  <input type="radio" name="executiveFlag" value="0" checked="">否
					                  </div>
									</div>
								</div>
								
								<div class="form-group">
									<label for="#post" class="col-sm-2 control-label">职务</label>
									<div class="col-sm-8 input-group">
										<select class="form-control" name="post">
						                   <option>董事长</option>
						                   <option>执行董事</option>
						                   <option>董事</option>
						                   <option>监事长</option>
						                   <option>监事</option>
						                   <option>总经理</option>
						                   <option>副总经理</option>
						                   <option>风控总监</option>
						                   <option>财务总监</option>
						                   <option>业务总监</option>
						                   <option>其他高管</option>
						                 </select>
									</div>
								</div>
								
								<div class="form-group">
									<label for="#replyTime" class="col-sm-2 control-label">批复时间</label>
									<div class="col-sm-8 input-group date">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker" name="replyTime"
											readonly data-errormessage="批复时间不能为空">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#entryTime" class="col-sm-2 control-label">入职时间</label>
									<div class="col-sm-8 input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker validate[required]" name="entryTime"
											readonly data-errormessage="批复时间不能为空">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#status" class="col-sm-2 control-label">是否在职</label>
									<div class="col-sm-8 input-group date">
									<div class="radio">
					                      <input type="radio" name="status" value="1" checked="">是
					                   	  <input type="radio" name="status" value="0" checked="">否
					                  </div>
									</div>
								</div>
								
								<div class="form-group">
									<label for="#quitTime" class="col-sm-2 control-label">离职时间</label>
									<div class="col-sm-8 input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker" name="quitTime"
											readonly data-errormessage="批复时间不能为空">
									</div>
								</div>
								
							</form>								
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button> 
							<button type="reset" id="resetBtn" class="btn btn-danger">重置</button>								
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
                                    <h3 class="box-title">人员信息</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#rechargeModel">新增人员信息</button>  
                                    <table class="table">
                                        <tr>
                                            <th>姓名</th> 
                                            <th>联系电话</th>
                                            <th>电子邮件</th>
                                            <th>通讯地址</th>
                                            <th>部门</th>
                                            <th>性别</th>
                                            <th>身份证号</th>
                                            <th>学历</th>
                                            <th>是否董监高</th>
                                            <th>是否在职</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" items="${list}">
	                                        <tr>
	                                            <td class="text-blue">${it.name}</td>  
	                                            <td>${it.phone}</td> 
	                                            <td>${it.email}</td>
	                                            <td>${it.postalAddress}</td>
	                                            <td>${it.department}</td>
	                                            <td>
	                                            	<c:if test="${it.sex=='1'}">男</c:if>
	                                            	<c:if test="${it.sex=='2'}">女</c:if>
	                                            </td>
	                                            <td>${it.idNumber}</td>
	                                            <td>${it.education}</td>
	                                            <td>
	                                            	<c:if test="${it.executiveFlag=='1'}">是</c:if>
	                                            	<c:if test="${it.executiveFlag=='0'}">否</c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${it.status=='NUM1'}">在职</c:if>
	                                            	<c:if test="${it.status=='NUM0'}">离职</c:if>
	                                            </td>
	                                            <td>
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td>
	                                            	<a href="javascript:void(0)" onclick="getEmployee('${it.id}')"><i class="text-blue fa  fa-edit"></i><strong>修改</strong></a>
	                                            	<!-- <a href="javascript:void(0)" onclick="delConfirm('${it.id}')"><i class="text-red fa  fa-minus-circle"></i><strong>删除</strong></a> -->
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
        <script src="bujs/employee/main.js" type="text/javascript"></script>
        
    </body>
</html>
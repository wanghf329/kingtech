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
                                                                        机构人员信息
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构信息</a></li>
                        <li class="active">人员信息</li> 
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                            <div class="box">
                                <div class="box-header">
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="form-horizontal" action="branch/save" method="POST">
												<input type="hidden" name="id" value="${employee.id}">
												<div class="form-group">
													<label for="#name" class="col-sm-2 control-label"><i class="text-red">*</i>姓名</label>
													<div class="col-sm-4 input-group">
														<input type="text"  class="form-control validate[required] maxSize[12]" id="name" name="name" value="${employee.name}" data-errormessage="姓名不能为空"/>
													</div>
												</div>	
												<div class="form-group">
													<label for="#phone" class="col-sm-2 control-label"><i class="text-red">*</i>联系电话</label>
													<div class="col-sm-4 input-group">
														<input type="text"  class="form-control validate[required, custom[phone]]" id="phone" name="phone" value="${employee.phone}" />
													</div>
												</div>	
												
												<div class="form-group">
													<label for="#email" class="col-sm-2 control-label">电子邮件</label>
													<div class="col-sm-4 input-group">
														<input type="text"  class="form-control validate[custom[email]]" id="email" name="email"  value="${employee.email}" />
													</div>
												</div>	
												
												<div class="form-group">
													<label for="#phone" class="col-sm-2 control-label"><i class="text-red">*</i>通讯地址</label>
													<div class="col-sm-4 input-group">
														<input type="text"  class="form-control validate[required, maxSize[120]]" id="address" name="address"  value="${employee.address}" data-errormessage="通讯地址不能为空"/>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#department" class="col-sm-2 control-label"><i class="text-red">*</i>部门</label>
													<div class="col-sm-4 input-group">
														<input type="text"  class="form-control validate[required, maxSize[10]]]" id="department" name="department" value="${employee.department}"  data-errormessage="部门不能为空"/>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#sex" class="col-sm-2 control-label"><i class="text-red">*</i>性别</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="sex" name="sex" data-errormessage="性别不能为空">
															<c:forEach var="it" items="${sexTypes}">
						  										<option value ="${it.name()}" <c:if test="${it eq employee.sex}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#cardType" class="col-sm-2 control-label"><i class="text-red">*</i>证件类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="cardType" name="cardType" data-errormessage="证件类型不能为空">
															<c:forEach var="it" items="${cardTypes}">
						  										<option value ="${it.name()}" <c:if test="${it eq employee.cardType}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#cardNumber" class="col-sm-2 control-label"><i class="text-red">*</i>证件号</label>
													<div class="col-sm-4 input-group">
														<input type="text"  class="form-control validate[required]" id="cardNumber" name="cardNumber"  value="${employee.cardNumber}" data-errormessage="证件号不能为空"/>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#education" class="col-sm-2 control-label"><i class="text-red">*</i>学历</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="education" name="education" data-errormessage="学历不能为空">
															<c:forEach var="it" items="${educations}">
						  										<option value ="${it.name()}" <c:if test="${it eq employee.education}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#executiveFlag" class="col-sm-2 control-label"><i class="text-red">*</i>是否董监高</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="executiveFlag" name="executiveFlag" data-errormessage="是否董监高不能为空">
															<c:forEach var="it" items="${isLeaders}">
						  										<option value ="${it.name()}" <c:if test="${it eq employee.executiveFlag}">selected</c:if>>${it.getKey()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#position" class="col-sm-2 control-label"><i class="text-red">*</i>职务</label>
													<div class="col-sm-4 input-group" id="positionDiv">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#entryTime" class="col-sm-2 control-label"><i class="text-red">*</i> 入职日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="entryTime" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${employee.entryTime}"></fmt:formatDate>" data-errormessage="入职日期不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#quitTime" class="col-sm-2 control-label"> 离职日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker "
														readonly name="quitTime" value="<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${employee.quitTime}"></fmt:formatDate>" >
													</div>
												</div>
																																		
												<div class="form-group">
													<label class="col-sm-5 control-label"></label> 
													<button type="button" class="btn btn-primary saveRecordBtn edit-href">保&nbsp;存</button>
												</div>												
											</form>						
                                </div>
                                <input type="text"  class="form-control validate[required] hide" id="positionInput" name="position"  value="${employee.position}" />
                                <select class="form-control validate[required] hide" id="positionSelect" name="position" >
                                	<option value ="董事长" <c:if test="${employee.position == '董事长' }">selected </c:if>>董事长</option>
                                	<option value ="执行董事" <c:if test="${employee.position == '执行董事' }">selected </c:if> >执行董事</option>
                                	<option value ="董事"  <c:if test="${employee.position == '董事' }">selected </c:if> >董事</option>
                                	<option value ="监事长" <c:if test="${employee.position == '监事长' }">selected </c:if> >监事长</option>
                                	<option value ="监事" <c:if test="${employee.position == '监事' }">selected  </c:if>>监事</option>
                                	<option value ="总经理" <c:if test="${employee.position == '总经理' }">selected </c:if> >总经理</option>
                                	<option value ="副总经理" <c:if test="${employee.position == '副总经理' }">selected </c:if> >副总经理</option>
                                	<option value ="风控总监" <c:if test="${employee.position == '风控总监' }">selected </c:if> >风控总监</option>
                                	<option value ="财务总监" <c:if test="${employee.position == '财务总监' }">selected </c:if> >财务总监</option>
                                	<option value ="业务总监" <c:if test="${employee.position == '业务总监' }">selected  </c:if>>业务总监</option>
                                	<option value ="其他高管" <c:if test="${employee.position == '其他高管' }">selected  </c:if>>其他高管</option>
                                </select>
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
		<%@include file="../common/footer.jspf" %>   
        <script src="bujs/branch/employee.js" type="text/javascript"></script>  
        <script type="text/javascript">
       		var _executiveFlag ="${employee.executiveFlag}";
    		var _employeeId ="${employee.id}";
        	var canEdit = "${employee==null or employee.pushStatus=='SUCCESS' }";
        </script>     
    </body>
</html>
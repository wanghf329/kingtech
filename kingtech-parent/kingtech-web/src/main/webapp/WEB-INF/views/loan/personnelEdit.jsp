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
                                    <h3 class="box-title">个人客户信息</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="personnelFrom" action="branch/add/personnel" method="POST">
												<input type="hidden" name="id" value="">
												<div class="form-group">
													<label for="#name" class="col-sm-2 control-label">姓名</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="name" data-errormessage="姓名不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#sex" class="col-sm-2 control-label">性别</label>
													<div class="col-sm-6 input-group">
									                  <div class="radio">
									                      <input type="radio" name="sex" value="1" checked="">男
									                   	  <input type="radio" name="sex" value="2" >女
									                  </div>
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#category" class="col-sm-2 control-label">证件类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="contract" name="category" data-errormessage="证件类型不能为空">
															<c:forEach var="it" items="${certTypes}">
						  										<option value ="${it}">${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												
												
												<div class="form-group">
													<label for="#cardNum" class="col-sm-2 control-label">证件号码</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="name" data-errormessage="证件号码不能为空" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#phone" class="col-sm-2 control-label">联系电话</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="phone" data-errormessage="联系电话不能为空" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#farmersFlag" class="col-sm-2 control-label">是否农牧民</label>
													<div class="col-sm-6 input-group">
									                  <div class="radio">
									                      <input type="radio" name="farmersFlag" value="S_0" checked="">否
									                   	  <input type="radio" name="farmersFlag" value="S_1" >是
									                  </div>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#education" class="col-sm-2 control-label">联系电话</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  name="education" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#fax" class="col-sm-2 control-label">传真</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  name="fax" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#email" class="col-sm-2 control-label">电子邮件</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  name="email" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#marriage" class="col-sm-2 control-label">婚姻状况</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  name="marriage" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#nationality" class="col-sm-2 control-label">国籍</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  name="nationality" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#nationality" class="col-sm-2 control-label">国籍</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  name="nationality" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#birthDate" class="col-sm-2 control-label">工商注册登记日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker "
														readonly name="birthDate" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#nation" class="col-sm-2 control-label">民族</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  name="nation" >
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#contactAddresscity" class="col-sm-2 control-label">固定住址（省份）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="contactAddresscity" data-errormessage="固定住址（省份）不能为空" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#addressCity" class="col-sm-2 control-label">固定住址（市）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="addressCity" data-errormessage="固定住址（市）不能为空" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#addressDistrict" class="col-sm-2 control-label">固定住址（区/县）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]"  name="addressDistrict" data-errormessage="固定住址（区/县）不能为空" >
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#address" class="col-sm-2 control-label">固定住址（详细）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="address"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#postCode" class="col-sm-2 control-label">邮政编码</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="postCode"  >
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#residence" class="col-sm-2 control-label">户口所在地</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="residence"  >
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#nativePlace" class="col-sm-2 control-label">籍贯</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="nativePlace"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#workUnit" class="col-sm-2 control-label">现工作单位</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="workUnit"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#post" class="col-sm-2 control-label">职务</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="post"  >
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
    </body>
</html>
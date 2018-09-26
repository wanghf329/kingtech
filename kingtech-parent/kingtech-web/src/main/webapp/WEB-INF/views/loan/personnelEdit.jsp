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
											<form class="form-horizontal" id="personnelFrom" action="borrower/personenl/save" method="POST">
												<input name="id" value="${model.id}" type="hidden"/>
												<div class="form-group">
													<label for="#name" class="col-sm-2 control-label"><i class="text-red">*</i> 姓名</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" value ="${model.name}" name="name" data-errormessage="姓名不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#sex" class="col-sm-2 control-label"><i class="text-red">*</i> 性别</label>
													<div class="col-sm-6 input-group">
									                  <div class="radio">
									                      <input type="radio" name="sex" value="S_1" <c:if test="${empty model.sex || model.sex=='S_1' }">checked</c:if>>男
									                   	  <input type="radio" name="sex" value="S_2" <c:if test="${ model.sex=='S_2' }">checked</c:if>>女
									                  </div>
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#cardType" class="col-sm-2 control-label"><i class="text-red">*</i> 证件类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" name="cardType" data-errormessage="证件类型不能为空">
															<c:forEach var="it" items="${cardTypes}">
						  										<option value ="${it}" <c:if test="${it.name() eq model.cardType}"></c:if>>${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div> 
												
												
												
												<div class="form-group">
													<label for="#cardNumber" class="col-sm-2 control-label"><i class="text-red">*</i> 证件号码</label>
													<div class="col-sm-4 input-group">  
														<input type="text" class="form-control validate[required,maxSize[18],custom[onlyLetterNumber]]" value ="${model.cardNumber}" name="cardNumber" data-errormessage="证件号码错误" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#phone" class="col-sm-2 control-label"><i class="text-red">*</i> 联系电话</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required,custom[phone]]" value ="${model.phone}"  name="phone" data-errormessage="联系电话不能为空" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#isFarmer" class="col-sm-2 control-label"><i class="text-red">*</i> 是否农牧民</label>
													<div class="col-sm-4 input-group">
									                  <div class="radio">
														<select class="form-control validate[required]" name="isFarmer">
															<c:forEach var="it" items="${yesNoEnum}">
						  										<option value ="${it}" <c:if test="${it.name() eq model.isFarmer}"></c:if>>${it.key}</option>
															</c:forEach>
														</select>										                  
									                  </div>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#education" class="col-sm-2 control-label">学历</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" name="education" data-errormessage="学历不能为空">
															<c:forEach var="it" items="${educations}">
						  										<option value ="${it}" <c:if test="${it.name() eq model.education}">selected</c:if>>${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#email" class="col-sm-2 control-label">电子邮件</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[custom[email]]" value ="${model.email}"   data-errormessage="邮件格式不正确" name="email" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#marriage" class="col-sm-2 control-label">是否已婚</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" name="isMarry">
															<c:forEach var="it" items="${yesNoEnum}"> 
						  										<option value ="${it}" <c:if test="${it.name() eq model.isMarry}"></c:if>>${it.key}</option>
															</c:forEach>
														</select>													
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#nationality" class="col-sm-2 control-label">国籍</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control"  value ="${model.nationality}" name="nationality" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#birthday" class="col-sm-2 control-label">生日</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker "
														readonly name="birthDate" value=<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${model.birthDate}"></fmt:formatDate> >
													</div> 
												</div>
												
												<div class="form-group">
													<label for="#nation" class="col-sm-2 control-label">民族</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control" value ="${model.nation}" name="nation" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#address" class="col-sm-2 control-label">固定住址（详细）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="address"  value ="${model.address}"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#postCode" class="col-sm-2 control-label">邮政编码</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control 	validate[custom[number]]" name="postCode"  value ="${model.postCode}" data-errormessage="邮政编码必须为数字">
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#registeredAddress" class="col-sm-2 control-label">户口所在地</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="registeredAddress" value ="${model.registeredAddress}" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#nativePlace" class="col-sm-2 control-label">籍贯</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="nativePlace" value ="${model.nativePlace}"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#workUnit" class="col-sm-2 control-label">现工作单位</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="workUnit" value ="${model.workUnit}" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#post" class="col-sm-2 control-label">职务</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="position"  value ="${model.position}">
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
        <script src="bujs/borrower/person.js" type="text/javascript"></script> 
        <script type="text/javascript"> 
        	var canEdit = '${canEdit}';
        </script>          
    </body>
</html>
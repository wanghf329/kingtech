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
                                    <h3 class="box-title">企业客户信息</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body"> 
											<form class="form-horizontal" id="enterpriseFrom" action="borrower/enterprise/save" method="POST"> 
												<input type="hidden" name="id" value="${model.id}"/>
												<div class="form-group">
													<label for="#name" class="col-sm-2 control-label"><i class="text-red">*</i> 企业名称</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="name" value="${model.name}" data-errormessage="企业名称不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#scale" class="col-sm-2 control-label"><i class="text-red">*</i> 企业规模</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="scale" name="scale" data-errormessage="企业规模不能为空">
															<c:forEach var="it" items="${scaleTypes}">
						  										<option value ="${it}" <c:if test="${it eq model.scale}">selected</c:if>>${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#industryType" class="col-sm-2 control-label"><i class="text-red">*</i> 产业类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="industryType" name="industryType" data-errormessage="产业类型不能为空">
															<c:forEach var="it" items="${industryTypes}">
						  										<option value ="${it}" <c:if test="${it eq model.industryType}">selected</c:if> >${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#industryinvolved" class="col-sm-2 control-label"><i class="text-red">*</i> 所属行业</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="contract" name="industryinvolved" data-errormessage="所属行业不能为空">
															<c:forEach var="it" items="${industryinvolveds}">
						  										<option value ="${it}" <c:if test="${it eq model.industryinvolved}">selected</c:if> >${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#organizationcode" class="col-sm-2 control-label">组织机构代码</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="organizationcode" value="${model.organizationcode}" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#regcode" class="col-sm-2 control-label">公司注册登记号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control" name="registCode" value="${model.registCode}">
													</div>
												</div>										
												<div class="form-group">
													<label for="#regOffice" class="col-sm-2 control-label">工商注册登记机关</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="registOffice" value="${model.registOffice}">
													</div>
												</div>	
														
												<div class="form-group">
													<label for="#regDate" class="col-sm-2 control-label">工商注册登记日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker "
														readonly name="registDate"  value=<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${model.registDate}"></fmt:formatDate>>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#nationalregNum" class="col-sm-2 control-label">国税税务登记号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="nationalTaxCode" value="${model.nationalTaxCode}" >
													</div>
												</div>	
												
												<div class="form-group">
													<label for="#landRegNum" class="col-sm-2 control-label">地税税务登记号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="landTaxCode" value="${model.landTaxCode}">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#licence" class="col-sm-2 control-label"><i class="text-red">*</i> 营业执照号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" value="${model.licenseCode}" name="licenseCode"  data-errormessage="营业执照号不能为空">
													</div>
												</div>
												
												
												
												<div class="form-group">
													<label for="#licenceEndDate" class="col-sm-2 control-label"><i class="text-red">*</i> 营业执照截止日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="licenceEndDate" data-errormessage="营业执照截止日期不能为空" value=<fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${model.licenceEndDate}"></fmt:formatDate>>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#legalRepresentative" class="col-sm-2 control-label">法定代表人</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="legalPerson" value="${model.legalPerson}" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#bulidDate" class="col-sm-2 control-label">成立日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker "
														readonly name="foundDate" value=<fmt:formatDate type="date" pattern = "yyyy-MM-dd HH:mm:ss" value="${model.foundDate}"></fmt:formatDate> >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#actualController" class="col-sm-2 control-label">实际控制人</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="controller"  value="${model.controller}">
													</div>
												</div>	
												
												<div class="form-group">
													<label for="#reallyCapital" class="col-sm-2 control-label">实收资本</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[custom[number]]" name="reallyCapital" value="${model.reallyCapital}"  data-errormessage="实收资本必须是数字" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#businessScope" class="col-sm-2 control-label">经营范围</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="businessScope" value="${model.businessScope}" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#regAddress" class="col-sm-2 control-label">注册地址</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="registerAddress" value="${model.registerAddress}" >
													</div>
												</div>
												
												<div class="form-group"> 
													<label for="#address" class="col-sm-2 control-label">通讯地址（详细）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="address"  value="${model.address}">
													</div> 
												</div>
												
												<div class="form-group">
													<label for="#phone" class="col-sm-2 control-label">联系电话</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[custom[phone]]" name="phone"  value="${model.phone}" data-errormessage="联系电话电话格式不对">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#linkman" class="col-sm-2 control-label">联系人</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="linkman"  value="${model.linkman}">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#email" class="col-sm-2 control-label">公司邮箱</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[custom[email]]" name="email"  value="${model.email}" data-errormessage="邮箱格式不对">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#webSite" class="col-sm-2 control-label">网址</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="webSite" value="${model.webSite}" >
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
        <script src="bujs/borrower/corporation.js" type="text/javascript"></script>  
        <script type="text/javascript">
        	var canEdit = '${canEdit}';
        </script>      
    </body>
</html>
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
                                    <h3 class="box-title">企业客户信息</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="enterpriseFrom" action="branch/add/enterprise" method="POST">
												<input type="hidden" name="id" value="">
												<div class="form-group">
													<label for="#corporateName" class="col-sm-2 control-label">企业名称</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="corporateName" data-errormessage="企业名称不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#scale" class="col-sm-2 control-label">企业规模</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="contract" name="contract" data-errormessage="企业规模不能为空">
															<c:forEach var="it" items="${scaleTypes}">
						  										<option value ="${it}">${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#industryType" class="col-sm-2 control-label">产业类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="contract" name="industryType" data-errormessage="产业类型不能为空">
															<c:forEach var="it" items="${industryTypes}">
						  										<option value ="${it}">${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#industryinvolved" class="col-sm-2 control-label">产业类型</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="contract" name="所属行业" data-errormessage="所属行业不能为空">
															<c:forEach var="it" items="${industryinvolveds}">
						  										<option value ="${it}">${it.key}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												
												<div class="form-group">
													<label for="#organizationcode" class="col-sm-2 control-label">组织机构代码</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="organizationcode" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">贷款金额（元）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#regcode" class="col-sm-2 control-label">公司注册登记号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control" name="regcode" >
													</div>
												</div>										
												<div class="form-group">
													<label for="#regOffice" class="col-sm-2 control-label">工商注册登记机关</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="regOffice" >
													</div>
												</div>	
														
												<div class="form-group">
													<label for="#regDate" class="col-sm-2 control-label">工商注册登记日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker "
														readonly name="regDate" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#nationalregNum" class="col-sm-2 control-label">国税税务登记号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="nationalregNum" >
													</div>
												</div>	
												
												<div class="form-group">
													<label for="#landRegNum" class="col-sm-2 control-label">地税税务登记号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="landRegNum" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#licence" class="col-sm-2 control-label">营业执照号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="licence"  data-errormessage="营业执照号不能为空">>
													</div>
												</div>
												
												
												
												<div class="form-group">
													<label for="#licenceEndDate" class="col-sm-2 control-label">营业执照截止日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker validate[required]"
														readonly name="licenceEndDate" data-errormessage="营业执照截止日期不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#nature" class="col-sm-2 control-label">企业性质</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="nature" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#employNum" class="col-sm-2 control-label">从业人数</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="employNum" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#legalRepresentative" class="col-sm-2 control-label">法定代表人</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="legalRepresentative" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#bulidDate" class="col-sm-2 control-label">成立日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker "
														readonly name="bulidDate">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#actualController" class="col-sm-2 control-label">实际控制人</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="actualController" >
													</div>
												</div>	
												
												<div class="form-group">
													<label for="#actualController" class="col-sm-2 control-label">实际控制人</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="actualController" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#regCapital" class="col-sm-2 control-label">注册资本</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="regCapital" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#reallyCapital" class="col-sm-2 control-label">实收资本</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="reallyCapital" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#businessScope" class="col-sm-2 control-label">经营范围</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="businessScope" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#regAddress" class="col-sm-2 control-label">注册地址</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="regAddress" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#contactAddressProvince" class="col-sm-2 control-label">通讯地址（省份）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="contactAddressProvince" data-errormessage="通讯地址（省份）不能为空" >
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#contactAddresscity" class="col-sm-2 control-label">通讯地址（市）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="contactAddresscity" data-errormessage="通讯地址（市）不能为空" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#contactAddressDistrict" class="col-sm-2 control-label">通讯地址（区/县）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="contactAddressDistrict" data-errormessage="通讯地址（区/县）不能为空" >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#contactAddress" class="col-sm-2 control-label">通讯地址（详细）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="contactAddress"  >
													</div>
												</div>
												
												
												<div class="form-group">
													<label for="#postcode" class="col-sm-2 control-label">邮政编码</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="postcode"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#phone" class="col-sm-2 control-label">联系电话</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="phone"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#linkman" class="col-sm-2 control-label">联系人</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="linkman"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#fax" class="col-sm-2 control-label">传真</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="fax"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#email" class="col-sm-2 control-label">公司邮箱</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="email"  >
													</div>
												</div>
												
												<div class="form-group">
													<label for="#webSite" class="col-sm-2 control-label">网址</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control " name="webSite"  >
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
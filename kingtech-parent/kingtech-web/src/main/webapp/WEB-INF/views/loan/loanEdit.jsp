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
                                    <h3 class="box-title">合同信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="form-horizontal" action="branch/add/contract" method="POST">
												<input type="hidden" name="id" value="">
												<div class="form-group">
													<label for="#branch" class="col-sm-2 control-label">所属机构</label>
													<div class="col-sm-4 input-group">
														<select class="form-control validate[required]" id="branch" name="branch" data-errormessage="机构不能为空">
															<c:forEach var="it" items="${branchs}">
						  										<option value ="${it.id}">${it.corporateName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">合同编号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">合同名称</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">营业执照号</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">贷款金额（元）</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">贷款期限类型</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>										
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">贷款期限</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>			
												<div class="form-group">
													<label for="financingTime" class="col-sm-2 control-label">贷款开始日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker-time validate[required]"
														readonly name="financingTime" data-errormessage="融资时间不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="financingTime" class="col-sm-2 control-label">贷款截止日期</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker-time validate[required]"
														readonly name="financingTime" data-errormessage="融资时间不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">利率类型</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>	
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">利率(%)</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">贷款用途</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>										
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">贷款投向</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>								
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">贷款方式</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>								
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">是否多户联合贷款</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">还款方式</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">合同签订日期</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>																								
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">还款来源</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">合同状态</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>
												<div class="form-group">
													<label for="#financingChannel" class="col-sm-2 control-label">是否展期	</label>
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required]" name="financingChannel" data-errormessage="融资渠道不能为空">
													</div>
												</div>																								
												
												<div class="form-group">
													<label for="#financingMoney" class="col-sm-2 control-label">融资金额</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-rmb"></i></span>
														<input type="text" class="form-control validate[required,custom[number]]" data-errormessage="融资金额只能为数字"
															name="financingMoney">
														<span class="input-group-addon"><i class="fa">万元</i></span>
													</div>
												</div>
												<div class="form-group">
													<label for="financingTime" class="col-sm-2 control-label">融资时间</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker-time validate[required]"
														readonly name="financingTime" data-errormessage="融资时间不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="expirationTime" class="col-sm-2 control-label">到期时间</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right datepicker-time validate[required]"
														readonly name="expirationTime" data-errormessage="到期时间不能为空">
													</div>
												</div>
												
												<div class="form-group">  
													<label for="replyTime" class="col-sm-2 control-label">实际还款时间</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span> 
														<input type="text" class="form-control pull-right datepicker-time" name="replyTime"
														readonly data-errormessage="实际还款时间不能为空"> 
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
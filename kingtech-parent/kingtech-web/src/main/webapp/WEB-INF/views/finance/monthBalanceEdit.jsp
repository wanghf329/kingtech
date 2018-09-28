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
                                                                       月度融资余额信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">融资信息</a></li>
                        <li class="active">月度融资余额信息信息</li>
                    </ol>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                            <div class="box">
                                <div class="box-header">
                                </div><!-- /.box-header --> 
                                <div class="box-body">
											<form class="form-horizontal" id="form-horizontal" action="finance/monthBalance/save" method="POST">
												<input type="hidden" name="id" value="${monthBalance.id}">
												
												<div class="form-group">
													<label for="#startDate" class="col-sm-2 control-label"><i class="text-red">*</i> 融资年月</label>
													<div class="col-sm-4 input-group">
														<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
														<input type="text" class="form-control pull-right  validate[required]" id="datepickerMonth"
														readonly name=financeMonth value="${monthBalance.financeMonth}" data-errormessage="融资年月不能为空">
													</div>
												</div>
												
												<div class="form-group">
													<label for="#loanAmount" class="col-sm-2 control-label"><i class="text-red">*</i> 融资余额（万元）</label> 
													<div class="col-sm-4 input-group">
														<input type="text" class="form-control validate[required,custom[number],min[0],max[99999999999]]" name="balance" id ="balance" value="${monthBalance.balance}" data-errormessage="融资金额错误,范围0-99,999,999,999">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-5 control-label"></label> 
													<button type="button" class="btn btn-primary saveRecordBtn edit-href">保&nbsp;存</button>
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
        <script src="bujs/finance/monthBalance.js" type="text/javascript"></script>  
        <script type="text/javascript">
        	var canEdit = "${monthBalance==null}";
        </script>     
    </body>
</html>
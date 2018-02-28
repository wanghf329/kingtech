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
                        <li><a href="branch">合同管理</a></li>
                        <li class="active">合同信息</li> 
                    </ol>
                </section>

			<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12 no-padding">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">合同信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="editContractBtn">新增合同信息</button>  
                                    <table  class="table table-bordered" role="grid" id="constractList"> 
                                    	<thead>
	                                        <tr>
	                                        	<th>序号</th> 
	                                            <th>合同编号</th> 
	                                            <th>合同名称</th>
	                                            <th><i class="fa  fa-user"></i>借款客户</th>
	                                            <th>利率类型</th>
	                                            <th>利率</th>
	                                            <th>贷款金额（元）</th>
	                                            <th>贷款期限</th>
	                                            <th>贷款开始日期</th>
	                                            <th>贷款截止日期</th>
	                                            <th>状态</th>
	                                            <th>操作</th>
	                                        </tr>
                                        </thead>
                                    </table>
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
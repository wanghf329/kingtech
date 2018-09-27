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
                                                                      单笔融资信息
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">融资信息</a></li>
                        <li class="active">单笔融资信息</li> 
                    </ol>
                </section>

			<!-- Main content -->
                <section class=content>
                    <div class="row">
                        <div class="col-md-12 no-padding">
                            <div class="box">
                                <div class="box-header">
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addCapitalBtn">新增单笔融资信息</button>  
                                    <table  class="table table-bordered" role="grid" id="capitalListTab"> 
                                    	<thead>
	                                        <tr>
	                                        	<th>序号</th> 
	                                            <th>融资编号</th> 
	                                            <th>融资名称</th>
	                                            <th>出借方名称</th>
	                                            <th>融资渠道</th>
	                                            <th>融资金额（元）</th>
	                                            <th>应付利息</th>
	                                            <th>手续费</th>
	                                            <th>合同担保金额</th>
	                                            <th>融资日期</th>
	                                            <th>到期日期</th>
	                                            <th>融资利率</th>
	                                            <th>利率类型</th>
	                                            <th>合同编号</th>
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
        <script src="bujs/finance/capital.js" type="text/javascript"></script>     
    </body>
</html>
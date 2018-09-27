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
                                                                      机构银行账户信息
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构银行账户信息</a></li>
                        <li class="active">机构银行账户信息</li> 
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
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addAccountInfoBtn">新增机构银行账户信息</button>  
                                    <table  class="table table-bordered" role="grid" id="accountInfoListTab"> 
                                    	<thead>
	                                        <tr>
	                                        	<th>序号</th> 
	                                            <th>银行名称</th> 
	                                            <th>银行账号</th>
	                                            <th>账户类型</th>
	                                            <th>账户状态</th>
	                                            <th>开户日期</th>
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
        <script src="bujs/branchAccount/accountInfo.js" type="text/javascript"></script>     
    </body>
</html>
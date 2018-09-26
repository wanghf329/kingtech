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
                    </ol>
                </section>

			<!-- Main content -->
                <section class=content>
                    <div class="row">
                        <div class="col-md-12 no-padding">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">机构信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addEmployeeBtn">新增人员信息</button>  
                                    <table  class="table table-bordered" role="grid" id="employeeListTab"> 
                                    	<thead>
	                                        <tr>
	                                        	<th>序号</th> 
	                                            <th><i class="fa  fa-user"></i>姓名</th> 
	                                            <th>联系电话</th>
	                                            <th>电子邮件</th>
	                                            <th>部门</th>
	                                            <th>性别</th>
	                                            <th>证件类型</th>
	                                            <th>证件号</th>
	                                            <th>学历</th>
	                                            <th>职务</th>
	                                            <th>入职日期</th>
	                                            <th>离职日期</th>
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
        <script src="bujs/branch/employee.js" type="text/javascript"></script>     
    </body>
</html>
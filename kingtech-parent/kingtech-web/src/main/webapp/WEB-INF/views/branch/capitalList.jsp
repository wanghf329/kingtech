<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<base href="${pageContext.request.scheme}://${header.host}${pageContext.request.contextPath}/"/>
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Simple Tables</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        
         <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
        
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../../css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/AdminLTE.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-blue">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="../../index.html" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                AdminLTE
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <%@include file="../common/logout.jspf" %>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">                
                <!-- sidebar: style can be found in sidebar.less -->
                <%@include file="../common/sidebar.jspf" %>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">                
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                                                                        机构资本信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="#">机构信息</a></li>
                        <li class="active">机构资本信息</li>
                    </ol>
                </section>

			<!-- Modal -->
			<div class="modal fade" id="rechargeModel" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">机构资本信息录入</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="#financingChannel" class="col-sm-2 control-label">融资渠道</label>
									<div class="col-sm-8 input-group">
										<input type="text" class="form-control" id="financingChannel">
									</div>
								</div>
								<div class="form-group">
									<label for="#financingMoney" class="col-sm-2 control-label">融资金额</label>
									<div class="col-sm-8 input-group">
										<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
										<input type="text" class="form-control"
											id="financingMoney">
										<span class="input-group-addon"><i class="fa">万元</i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="financingTime" class="col-sm-2 control-label">融资时间</label>
									<div class="col-sm-8 input-group">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right timepicker" id="financingTime">
									</div>
								</div>
								
								<div class="form-group">
									<label for="expirationTime" class="col-sm-2 control-label">到期时间</label>
									<div class="col-sm-8 input-group">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right timepicker" id="expirationTime">
									</div>
								</div>
								
								<div class="form-group">
									<label for="replyTime" class="col-sm-2 control-label">实际还款时间</label>
									<div class="col-sm-8 input-group">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right timepicker" id="replyTime">
									</div>
								</div>
							</form>						
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button> 
							<button type="button" class="btn btn-danger">重置</button>								
							<button type="button" class="btn btn-primary saveRecordBtn">保存</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12 no-padding">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">资本信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#rechargeModel">新增人员信息</button>  
                                    <table class="table">
                                        <tr>
                                            <th>融资渠道</th> 
                                            <th>融资金额（万元）</th>
                                            <th>融资时间</th>
                                            <th>到期时间</th>
                                            <th>实际还款时间</th>
                                            <th>操作</th>
                                        </tr>
                                        <tr>
                                            <td>网络</td> 
                                            <td class="text-red"><i class="fa fa-jpy"/>20.6</td>
                                            <td>2009-04-15 14：25：30</td>
                                            <td>2016-04-15 14：25：30</td>
                                            <td></td>
                                            <td><a><strong>修改<a><strong></td> 
                                        </tr>
                                    </table>
                                </div><!-- /.box-body -->
                                <div class="box-footer clearfix">
                                    <ul class="pagination pagination-sm no-margin pull-right">
                                        <li><a href="#">&laquo;</a></li>
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">&raquo;</a></li>
                                    </ul>
                                </div> 
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="js/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="js/AdminLTE/app.js" type="text/javascript"></script>
        <script src="bujs/main.js" type="text/javascript"></script>
        
        <script src="js/plugins/datepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        
        <script src="js/plugins/datepicker/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"/></script>
        
        <script type="text/javascript">
        	menuChecked("#capitalList");
        	
        	$(".saveRecordBtn").click(function(){
        		var data = {
        			id:'',	
       				contractName:$("#contractName").val(),
       				activityName:$("#activityName").val(),
       				orderId:$("#orderId").val(),
       				orderName:$("#orderName").val(),
       				amount:$("#amount").val(),
       				discription:$("#discription").val()
        		};
        		
        		$.post("recharge/create",data,function(res){
        			alert(res);
        		});
        	});
        	
        	$(function () {  
        		 $(".timepicker").datetimepicker({
        			 	minView: "0", //选择日期后，不会再跳转去选择时分秒 
        			    language:  'zh-CN',
        			    format: 'yyyy-mm-dd hh:ii:ss',
        			    todayBtn:  1,
        			    autoclose: 1,
        		    });
        		
        	});
        </script>

    </body>
</html>
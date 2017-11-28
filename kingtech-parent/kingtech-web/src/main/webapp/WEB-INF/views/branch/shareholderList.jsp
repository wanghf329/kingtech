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
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../../css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/AdminLTE.css" rel="stylesheet" type="text/css" />
        
         <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />

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
                                                                        股东列表
                        <small>股东列表预览</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                        <li><a href="#">机构信息</a></li>
                        <li class="active">股东列表</li>
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
							<h4 class="modal-title" id="myModalLabel">机构股东信息录入</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label for="#partnerType" class="col-sm-2 control-label">股东类型</label>
									<div class="col-sm-8 input-group">
					                  <div class="radio">
					                      <input type="radio" name="optionsRadios" id="partnerType" value="1" checked="">自然人股东
					                   	  <input type="radio" name="optionsRadios" id="partnerType" value="2" checked="">企业股东
					                  </div>
									</div>
								</div>
								<div class="form-group">
									<label for="#holder" class="col-sm-2 control-label">持股人</label>
									<div class="col-sm-8 input-group">
										<input type="text" class="form-control"
											id="holder">
									</div>
								</div>
								<div class="form-group">
									<label for="#holdingScale" class="col-sm-2 control-label">持股比例</label>
									<div class="col-sm-8 input-group">
										<input type="text" class="form-control"
											id="holdingScale">
										<span class="input-group-addon"><i class="fa">%</i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="#contributionAmount" class="col-sm-2 control-label">出资金额</label>
									<div class="col-sm-8 input-group">
										<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
										<input type="text" class="form-control"
											id="contributionAmount">
										<span class="input-group-addon"><i class="fa">万元</i></span>
									</div>
								</div>
								<div class="form-group">
									<label for="#joinTime" class="col-sm-2 control-label">入股时间</label>
									<div class="col-sm-8 input-group">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker" id="joinTime">
									</div>
								</div>
								
								<div class="form-group">
									<label for="#gender" class="col-sm-2 control-label">性别</label>
									<div class="col-sm-8">
										<div class="radio input-group">
					                      <input type="radio" name="optionsRadios" id="gender" value="1" checked="">男	
					                   	  <input type="radio" name="optionsRadios" id="gender" value="2" checked="">女
					                  </div>
									</div>
								</div>
								
								<div class="form-group">
									<label for="#quitTime" class="col-sm-2 control-label">退出时间</label>
									<div class="col-sm-8 input-group">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker" id="quitTime">
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
                                    <h3 class="box-title">股东列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#rechargeModel">新增股东</button>  
                                    <table class="table">
                                        <tr>
                                            <th>股东类型</th> 
                                            <th>持股人</th>
                                            <th>持股比例</th>
                                            <th>出资金额</th>
                                            <th>入股时间</th>
                                            <th>性别</th>
                                            <th>操作</th>
                                        </tr>
                                        <tr>
                                            <td >自然人股东</td> 
                                            <td class="text-blue" ><a><strong>大山哥<a><strong></td>
                                            <td class="text-red">25%</td>
                                            <td class="text-blue" ><i class="fa fa-jpy">100000</td>
                                            <td>2010-10-25</td>
                                            <td>男</td>
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
        
        <script src="js/plugins/datepicker/bootstrap-datepicker.min.js"></script>
        
        <script type="text/javascript">
        	menuChecked("#shareholderList");
        	
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
        		$('.datepicker').datetimepicker({
        			minView: "2", //选择日期后，不会再跳转去选择时分秒 
    			    language:  'zh-CN',
    			    format: 'yyyy-mm-dd hh:ii:ss',
    			    todayBtn:  1,
    			    autoclose: 1,});
        		
        	});
        </script>

    </body>
</html>
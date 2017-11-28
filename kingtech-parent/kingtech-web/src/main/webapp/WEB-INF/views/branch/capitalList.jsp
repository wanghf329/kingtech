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
                                                                        机构资本信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构管理</a></li>
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
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#rechargeModel">新增资本信息</button>  
                                    <table class="table"> 
                                        <tr>
                                            <th>融资渠道</th> 
                                            <th>融资金额（万元）</th>
                                            <th>融资时间</th>
                                            <th>到期时间</th>
                                            <th>实际还款时间</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <tr>
                                            <td>网络</td> 
                                            <td class="text-red"><i class="fa fa-jpy"/>20.6</td>
                                            <td>2009-04-15 14：25：30</td>
                                            <td>2016-04-15 14：25：30</td>
                                            <td></td> 
                                            <td><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span>
                                            	<span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span>
                                            	<span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span>
                                            </td>
                                            <td><a href="javascript:void(0)"><i class="text-blue fa  fa-edit"></i><strong>修改</strong></a></td>
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

        <%@include file="../common/footer.jspf" %>        
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
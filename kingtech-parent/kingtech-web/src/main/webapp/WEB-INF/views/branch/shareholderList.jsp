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
                                                                        股东列表
                        <small>股东列表预览</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构管理</a></li>
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


        <%@include file="../common/footer.jspf" %>
        
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
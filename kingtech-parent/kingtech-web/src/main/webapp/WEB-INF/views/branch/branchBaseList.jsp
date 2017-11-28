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
                                                                        机构基本列表
                        <small>机构基本列表预览</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="#">业务办理</a></li>
                        <li class="active">机构基本列表</li>
                    </ol>
                </section>

			<!-- Modal -->
			<div class="modal fade" id="newBranchBaseInfo" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" >机构基本信息录入</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal">
								<div class="form-group" >
									<label for="#corporateName" class="col-sm-2 control-label">公司名称</label>
									<div class="col-sm-8 input-group">
										<input type="text" class="form-control" id="corporateName">
									</div>
								</div>
								<div class="form-group">
									<label for="#legalRepresentative" class="col-sm-2 control-label">法人</label>
									<div class="col-sm-8 input-group">
										<input type="text" class="form-control"
											id="legalRepresentative">
									</div>
								</div>
								<div class="form-group">
									<label for="#regCapital" class="col-sm-2 control-label">注册资本</label>
									<div class="col-sm-8 input-group">
									<span class="input-group-addon"><i class="fa fa-dollar"></i></span>
									<input type="text" class="form-control " id="regCapital">
									</div>
									
								</div>
								<div class="form-group">
									<label for="buildDate" class="col-sm-2 control-label">成立日期</label>
									<div class="col-sm-8 input-group date">
										<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker" id="buildDate">
									</div>
								</div>
								<div class="form-group">
									<label for="openingDate" class="col-sm-2 control-label">开业日期</label>
									<div class="col-sm-8 input-group date">
									<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
										<input type="text" class="form-control pull-right datepicker"
											id="openingDate">
									</div>
								</div>
								<div class="form-group">
									<label for="siteArea" class="col-sm-2 control-label">场所面积</label>
									<div class="col-sm-8 input-group">
										<input type="text"  class="form-control"
											id="siteArea"></input>
										<span class="input-group-addon"><i class="fa ">平方米</i></span>
									</div>
								</div> 	
								<div class="form-group">
									<label for="businessaddr" class="col-sm-2 control-label">经营地址</label>
									<div class="col-sm-8 input-group">
										<input type="text"  class="form-control"
											id="businessaddr"></input>
									</div>
								</div> 
								<div class="form-group ">
									<label for="organizationCode" class="col-sm-2 control-label">组织机构代码</label>
									<div class="col-sm-8 input-group">
										<input type="text"  class="form-control"
											id="organizationCode"></input>
									</div>
								</div> 
								<div class="form-group ">
									<label for="licence" class="col-sm-2 control-label">营业执照</label>
									<div class="col-sm-8 input-group">
										<input type="text"  class="form-control"
											id="licence"></input>
									</div>
								</div>
								
								<div class="form-group ">
									<label for="nationalRegNum" class="col-sm-2 control-label">国税编码</label>
									<div class="col-sm-8 input-group">
										<input type="text"  class="form-control"
											id="nationalRegNum"></input>
									</div>
								</div>
								
								<div class="form-group ">
									<label for="landRegNum" class="col-sm-2 control-label">地税编码</label>
									<div class="col-sm-8 input-group">
										<input type="text"  class="form-control"
											id="landRegNum"></input>
									</div>
								</div>
								
								
								<div class="form-group ">
									<label for="businessScope" class="col-sm-2 control-label">经营范围</label>
									<div class="col-sm-8 input-group">
										<input type="text"  class="form-control"
											id="businessScope"></input>
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
                                    <h3 class="box-title">机构基本信息</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#newBranchBaseInfo">新增机构基本信息</button>  
                                    <table class="table">
                                        <tr>
                                            <th>公司名称</th> 
                                            <th>法人</th>
                                            <th>注册资本</th>
                                            <th>成立日期</th>
                                            <th>开业日期</th>
                                            <th>经营地址</th>
                                            <th>场所面积</th>
                                            <th>组织机构代码</th>
                                            <th>营业执照</th>
                                            <th>国税编码</th>
                                            <th>地税编码</th>
                                            <th>经营范围</th>
                                            <th>操作</th>
                                        </tr>
                                        <tr>
                                            <td >阿尔法</td> 
                                            <td class="text-blue" ><a><strong>桥本<a><strong></td>
                                            <td class="text-red"><i class="fa fa-jpy">200000</td>
                                            <td>2009-10-25</td>
                                            <td>2010-10-25</td>
                                            <td>陕西西安</td>
                                            <td>100<a><strong>(平)<a><strong></td>
                                            <td>610526*********9112</td>
                                            <td>5325******6302</td>
                                            <td>320102</td>
                                            <td>A124254BC</td> 
                                            <td>放贷</td> 
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
        	menuChecked("#branch1");  
        	
        	
        	$('#datepicker').datepicker({ autoclose: true});
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
        		$('.datepicker').datepicker({autoclose: true });
        		
        	});
        </script>

    </body>
</html>
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
                                                                        单笔融资信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">融资信息管理</a></li>
                        <li class="active">单笔融资信息</li>
                    </ol>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">补充单笔融资信息</h3>
                            </div><!-- /.box-header --> 
                            <div class="col-xs-12">
                                <div class="tabbable">
                                	<ul id="myTab" class="inbox-tabs nav nav-tabs tab-size-bigger tab-space-1">
										<li data-type="repayPlan">
											<a data-toggle="tab" href="#repayPlanTab">
												还款计划
											</a>
										</li>
                                	</ul>
                                </div>
                                <div class="tab-content no-border no-padding">
                                	<!-- 还款计划 -->
                                	<div id="repayPlanTab" class="tab-pane active"> 
	                                	<div class="content col-md-12"> 
	                                		<a href="javascript:void(0)" class="edit-href"><i class="text-blue fa  fa-plus-circle addRepayPlanBtn">增加</i></a> 
	                                		<form class="form-horizontal" id="form-repayPlan" action="finance/supplement/addRepayPlan" method="POST">
			                                	<input type="hidden" name="financeId" value="${financeId}">
			                                		<div>
					                                	<%@include file="sub/repayPlan.jspf" %>			                                		
			                                			<c:forEach var="it" items="${repayPlanList}" varStatus="status">
						                                	<%@include file="sub/repayPlan1.jspf" %>
						                            	</c:forEach>
				                                	</div>
				                            	<div class="col-md-8  text-center">   
					                                <button type="submit" class="btn btn-primary ">保&nbsp;存</button>									
				                                </div>	 
			                            	</form>
	                                	</div>    
                                	</div>
                                </div>
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        
		<%@include file="../common/footer.jspf" %>   
        <script src="bujs/loan/contract.js" type="text/javascript"></script>   
        <script type="text/javascript">
			var canEdit = "${capital.pushStatus=='INITATION' or capital.pushStatus=='FAILED'}";
        </script>    
    </body>
</html>
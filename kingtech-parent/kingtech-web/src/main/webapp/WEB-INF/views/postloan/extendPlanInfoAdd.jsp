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
                                                                        展期还款计划信息
                        <small></small>
                    </h1>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">补充展期还款计划</h3>
                            </div><!-- /.box-header --> 
                            <div class="col-xs-12">
                                <div class="tabbable">
                                	<ul id="myTab" class="inbox-tabs nav nav-tabs tab-size-bigger tab-space-1">
										<li data-type="repayPlan" class="active">
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
	                                		<form class="form-horizontal" id="form-repayPlan" action="postLoan/extendRepay/addRepayPlan" method="POST">
			                                	<input type="hidden" name="infoId" value="${infoId}">
			                                		<div>
					                                	<%@include file="sub/repayPlan.jspf" %>			                                		
			                                			<c:forEach var="it" items="${extendPlans}" varStatus="status">
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
        <script src="bujs/postloan/addextendrepayplan.js" type="text/javascript"></script>   
        <script type="text/javascript">
			var canEdit = "${planInfo.pushStatus=='INITATION' or planInfo.pushStatus=='FAILED'}";
        </script>    
    </body>
</html>
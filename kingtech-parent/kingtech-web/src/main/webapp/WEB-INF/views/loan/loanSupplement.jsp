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
                                                                        合同信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">机构管理</a></li>
                        <li class="active">合同信息</li>
                    </ol>
                </section>

				<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="box">
                            <div class="box-header">
                                <h3 class="box-title">补充合同信息</h3>
                            </div><!-- /.box-header --> 
                            <div class="col-xs-12">
                                <div class="tabbable">
                                	<ul id="myTab" class="inbox-tabs nav nav-tabs tab-size-bigger tab-space-1">
                                		<li data-type="collateral" class="active">
											<a data-toggle="tab" href="#collateralTab">
												抵质押物	
											</a>
										</li>
										<li data-type="guarantee">
											<a data-toggle="tab" href="#guaranteeTab">
												保证人信息
											</a>
										</li>
										<li data-type="repayPlan">
											<a data-toggle="tab" href="#repayPlanTab">
												还款计划
											</a>
										</li>
										<li data-type="settledInfo">
											<a data-toggle="tab" href="#settledInfoTab">
												放款信息
											</a>
										</li>
                                	</ul>
                                </div>
                                <div class="tab-content no-border no-padding">
                                	<!-- 抵质押物 -->
                                	<div id="collateralTab" class="tab-pane in active">
	                                	<div class="content col-md-10">
	                                		<a href="javascript:void(0)"><i class="text-blue fa  fa-plus-circle addCollateralBtn">增加</i></a> 
	                                		<form class="form-horizontal" id="form-collateral" action="loan/supplement/addCollateral" method="POST">
	                                			<input type="hidden" name="loanContractId" value="${loanContractId}">
	                                				<div>
					                                	<%@include file="sub/collateral.jspf" %>			                                		
			                                			<c:forEach var="it" items="${collateralList}">
						                                	<%@include file="sub/collateral1.jspf" %>
						                            	</c:forEach>
				                                	</div>
				                            	<div class="col-md-8  text-center">   
					                                <button type="submit" class="btn btn-primary ">保&nbsp;存</button>									
				                                </div>	 
			                            	</form>
	                                	</div>    
                                	</div>
                                	
                                	<!-- 保证人信息 -->
                                	<div id="guaranteeTab" class="tab-pane">
	                                	<div class="content col-md-10"> 
	                                			<a href="javascript:void(0)"><i class="text-blue fa  fa-plus-circle addGuaranteeBtn">增加</i></a> 
		                                		<form class="form-horizontal" id="form-guarantee" action="loan/supplement/addGuarantee" method="POST">
													<input type="hidden" name="loanContractId" value="${loanContractId}">
			                                		<div>
					                                	<%@include file="sub/guarantee.jspf" %>			                                		
			                                			<c:forEach var="it" items="${guaranteeList}">
						                                	<%@include file="sub/guarantee1.jspf" %>
						                            	</c:forEach>
				                                	</div>
					                            	<div class="col-md-8  text-center">   
						                                <button type="submit" class="btn btn-primary ">保&nbsp;存</button>									
					                                </div>	 
				                            	</form>
	                                	</div>    
                                	</div>
                                	
                                	<!-- 还款计划 -->
                                	<div id="repayPlanTab" class="tab-pane"> 
	                                	<div class="content col-md-12"> 
	                                		<a href="javascript:void(0)"><i class="text-blue fa  fa-plus-circle addRepayPlanBtn">增加</i></a> 
	                                		<form class="form-horizontal" id="form-repayPlan" action="loan/supplement/addRepayPlan" method="POST">
			                                	<input type="hidden" name="loanContractId" value="${loanContractId}">
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
                                	
                                	<!-- 放款信息 -->
                                	<div id="settledInfoTab" class="tab-pane">
	                                	<div class="content col-md-10"> 
	                                		<a href="javascript:void(0)"><i class="text-blue fa  fa-plus-circle addSettledInfoBtn">增加</i></a> 
	                                		<form class="form-horizontal" id="form-settledInfo" action="loan/supplement/addSettledInfo" method="POST">
			                                	<input type="hidden" name="loanContractId" value="${loanContractId}">
			                                		<div>
					                                	<%@include file="sub/settledInfo.jspf" %>			                                		
			                                			<c:forEach var="it" items="${settledInfoList}">
						                                	<%@include file="sub/settledInfo1.jspf" %>
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
        	var collateralTypeOption1 = "";
        	var collateralTypeOption2 = "";
			<c:forEach var="collateralType" items="${collateralType1}">
				collateralTypeOption1 += "<option value='${collateralType}'>${collateralType.getKey()}</option>";
			</c:forEach>
			<c:forEach var="collateralType" items="${collateralType2}">
				collateralTypeOption2 += "<option value='${collateralType}'>${collateralType.getKey()}</option>";
			</c:forEach>
			var canEdit = "true";
        </script>    
    </body>
</html>
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
                                                                        个人借款人信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">合同管理</a></li>
                        <li class="active">借款人信息</li>
                    </ol>
                </section>

			<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12 no-padding">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">个人借款人信息列表</h3>
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addContractBtn">新增个人借款人</button>
                                    <table class="table"> 
                                        <tr>
                                            <th>合同编号</th> 
                                            <th>合同名称</th>
                                            <th>借款客户类型</th>
                                            <th>借款客户</th>
                                            <th>利率类型</th>
                                            <th>利率</th>
                                            <th>贷款金额（元）</th>
                                            <th>贷款期限</th>
                                            <th>贷款开始日期</th>
                                            <th>贷款截止日期</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" items="${list}">
	                                        <tr>
	                                            <td>${it.loanContractId}</td>  
	                                            <td>${it.loanContractName}</td>  
	                                            <td>${it.borrowerType.getKey()}</td> 
	                                            <td>${it.borrowerId} </td>
	                                            <td>${it.rateType.getKey()}</td> 
	                                            <td class="text-green">${it.rate}%</td>    
	                                            <td class="text-red"><Strong>${it.loanAmount}</Strong></td> 
	                                            <td>${it.periodTerm}${it.periodType.getKey()}</td> 
	                                            <td><fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${it.loanStartDate}"></fmt:formatDate></td>
	                                            <td><fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${it.loanEndDate}"></fmt:formatDate></td> 
	                                            <td>
	                                            	<c:if test="${it.pushStatus=='INITATION'}"><span class="text-gray"><i class="text-gray fa fa-info-circle"></i>初始</span></c:if>
	                                            	<c:if test="${it.pushStatus=='SUCCESS'}"><span class="text-green"><i class="text-green fa fa-check-square"></i>推送成功</span></c:if>
	                                            	<c:if test="${it.pushStatus=='INPROSESS'}"><span class="text-blue"><i class="text-blue fa fa-asterisk"></i>推送处理中</span></c:if>
	                                            	<c:if test="${it.pushStatus=='FAILED'}"><span class="text-red"><i class="text-red fa fa-minus-circle"></i>推送失败</span></c:if>
	                                            </td>
	                                            <td> 
	                                            	<a href="javascript:void(0)" onclick="alert('${it.id}')"><i class="text-blue fa fa-exchange"></i><strong>推送</strong></a>
	                                            </td>
	                                        </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div><!-- /.box -->
                        </div><!-- /.col -->
                    </div><!-- /.row --> 
                </section><!-- /.content -->                
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
		<%@include file="../common/footer.jspf" %>   
        <script src="bujs/borrower/person.js" type="text/javascript"></script>       
    </body>
</html>
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
                                                                        企业借款人信息列表
                        <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="branch"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li><a href="branch">合同管理</a></li> 
                        <li class="active">企业借款人信息</li>
                    </ol>
                </section>

			<!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-md-12 no-padding">
                            <div class="box">
                                <div class="box-header">
                                    <h3 class="box-title">企业借款人信息列表</h3> 
                                </div><!-- /.box-header --> 
                                <div class="box-body">
                                	<button class="btn btn-primary btn-sm" data-toggle="modal" id="addEnterpriseBtn">新增企业借款人信息</button>
                                    <table class="table"> 
                                        <tr>
                                            <th>企业名称</th> 
                                            <th>企业规模</th>
                                            <th>产业类型</th>
                                            <th>所属行业</th>
                                            <th>营业执照号</th>
                                            <th>营业执照截止日期</th>
                                            <th>通讯地址</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach var="it" items="${list}">
	                                        <tr>
	                                            <td>${it.corporateName}</td>  
	                                            <td>${it.scale.getKey()}</td>  
	                                            <td>${it.industryType.getKey()}</td> 
	                                            <td>${it.industryinvolved.getKey()} </td>
	                                            <td>${it.licence}</td> 
	                                            <td class="text-green"><fmt:formatDate type="date" pattern = "yyyy-MM-dd" value="${it.licenceEndDate}"></fmt:formatDate></td>
	                                            <td ><Strong>${it.contactAddresscity}${it.contactAddressDistrict}${it.contactAddress}</Strong></td>    
	                                            <td> 
	                                            	<a href="javascript:void(0)"  onclick="getEnterprise('${it.id}')" > <i class="text-blue fa  fa-edit"></i><strong>修改</strong></a>
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
        <script src="bujs/borrower/corporation.js" type="text/javascript"></script>       
    </body>
</html>
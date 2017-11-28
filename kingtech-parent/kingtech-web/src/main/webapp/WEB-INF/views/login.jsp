<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-gray">
    <head>
        <meta charset="UTF-8">
        <title>AdminLTE | Log in</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="css/AdminLTE.css" rel="stylesheet" type="text/css" /> 

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="bg-gray"> 

        <div class="form-box" id="login-box">  
            <div class="header">数据对接平台</div> 
            <form action="../../index.html" method="post">
                <div class="body">
				  	<div class="form-group">
                         <label>用户名：</label>
                         <div class="input-group">
                             <div class="input-group-addon">
                                 <i class="fa fa-user"></i>
                             </div>
                             <input type="text" class="form-control">
                         </div><!-- /.input group -->
                    </div>
				    <div class="form-group">
                         <label>密码：</label>
                         <div class="input-group">
                             <div class="input-group-addon">
                                 <i class="fa fa-key"></i>
                             </div>
                             <input type="password" class="form-control">
                         </div><!-- /.input group -->
                    </div> 
                </div>
                <div class="footer">
                    <button type="submit" class="btn btn-info bg-olive btn-block">登录</button> 
                </div>
            </form>
        </div>


        <!-- jQuery 2.0.2 -->
        <script src="js/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

    </body>
</html>

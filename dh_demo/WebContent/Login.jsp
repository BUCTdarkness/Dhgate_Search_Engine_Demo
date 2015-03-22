<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <title>DHgate_Search_Engine</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="scripts/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/compiled/signin.css" type="text/css" media="screen" />
</head>
<body>

<%
	session.removeAttribute("uid");
%>

<form method="get" action="index.jsp">
    <div class="login-wrapper">
        <a href="#">
            <img class="logo" src="img/logo-white.png">
        </a>

        <div class="box">
            <div class="content-wrap">
                <h6>Dhgate个性化搜索与推荐系统</h6>
                <input class="form-control" name="uid" type="text" placeholder="用户ID">
                <input class="form-control" name="upwd" type ="password" placeholder="密码">
                <a href="#" class="forgot">忘记密码?</a>
                <div class="remember">
                    <input id="remember-me" type="checkbox">
                    <label for="remember-me">记住密码</label>
                </div>

				<button type="submit" class="btn btn-default" >
				登录
				</button> 
            </div>
        </div>
    </div>
</form>
		<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>

    <!-- pre load bg imgs -->
    <script type="text/javascript">
        $(function () {
            // bg switcher
            var $btns = $(".bg-switch .bg");
            $btns.click(function (e) {
                e.preventDefault();
                $btns.removeClass("active");
                $(this).addClass("active");
                var bg = $(this).data("img");

                $("html").css("background-image", "url('img/bgs/" + bg + "')");
            });

        });
    </script>
</body>
</html>
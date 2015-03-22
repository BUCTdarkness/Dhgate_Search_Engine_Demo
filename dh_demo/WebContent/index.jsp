<%@page import="com.dh.util.ValidateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <title>DHgate_Demo</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- 引入 Bootstrap -->
   <link href="css/bootstrap.min.css" rel="stylesheet">
   <script src="scripts/jquery.min.js"></script>
   <script src="js/bootstrap.min.js"></script>
   </head>
   <body>


	<%
		String userid = request.getParameter("uid");
		if(ValidateUtil.isValid(userid))
			session.setAttribute("uid", userid);
		String uid=null;
		if(ValidateUtil.isValid((String) session.getAttribute("uid") )){
			uid = (String) session.getAttribute("uid");
		}
		else{
			uid = "tourists";
		}
	%>


      <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->

      
   <nav id="navbar-example" class="navbar navbar navbar-inverse navbar-static-top" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp" >    
           <span class="glyphicon glyphicon-home" title="回主页" >  DHgate.com</span>
                   <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>　
      </a>
   </div>
   <div>
      <p class="navbar-text navbar-right">Signed in as 
         <a href="Login.jsp" class="navbar-link"><%=uid %></a>
      </p>
   </div>
</nav>

<div data-spy="scroll" data-target="#navbar-example" data-offset="0" class ="container">
	<div style="padding: 100px 100px 10px; ">
		<img src="img/Dh.png" />
	</div>
	<div style="padding: 100px 100px 100px; ">
	   <form class="bs-example bs-example-form" role="form"  action="/dh_demo/search" method="GET">
	      <div class="row">
	         <div class="col-lg-6">
	            <div class="input-group  input-group-lg ">
	               <input type="text" class="form-control" name="key" style="width:500px;" />
	               <span class="input-group-btn">
	                  <button class="btn btn-default" type="submit">
	                     Go!
	                  </button>
	               </span>
	            </div>
	         </div>
	      </div>
	
	   </form>
	</div>
</div>
</body>
</html>
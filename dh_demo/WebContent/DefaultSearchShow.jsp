<%@page import="com.dh.util.ValidateUtil"%>
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
   </head>
   <body>

      
<%
	
	String uid=null;
	if(ValidateUtil.isValid((String) session.getAttribute("uid") )){
		uid = (String) session.getAttribute("uid");
	}
	else{
		uid = "tourists";
	}


	String imgurlString = (String)request.getAttribute("imgurl");
	String itemcodeString = (String)request.getAttribute("itemcode");
	String supplieridString = (String)request.getAttribute("supplierid");
	String maxcostString = (String)request.getAttribute("maxcost");
	String mincostString = (String)request.getAttribute("mincost");
	String itemnameString = (String)request.getAttribute("itemname");
	String keyString = (String) request.getAttribute("searchkey");
	
	if(!ValidateUtil.isValid(imgurlString)||!ValidateUtil.isValid(itemcodeString)||!ValidateUtil.isValid(supplieridString)||!ValidateUtil.isValid(maxcostString)||!ValidateUtil.isValid(mincostString)||!ValidateUtil.isValid(itemnameString)||!ValidateUtil.isValid(keyString)){
		
		RequestDispatcher rd;
		if(ValidateUtil.isValid(uid)&&uid!="tourists"){
			rd=request.getRequestDispatcher("/rec"); 
			rd.forward(request, response);
		}
		else{
			rd=request.getRequestDispatcher("/rec?username="+uid); 
			rd.forward(request, response);
		}

	}
	
	String []imgurls = imgurlString.split(";");
	String []itemcodes=itemcodeString.split(";");
	String []supplierids=supplieridString.split(";");
	String []maxcosts = maxcostString.split(";");
	String []mincosts = mincostString.split(";"); 
	String []itemnames = itemnameString.split(";");
	
%>
      
      
      
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
      <form class="navbar-form navbar-left" action="/dh_demo/search" role="search">
	            <div class="input-group  input-group-lg ">
	               <input type="text" class="form-control" placehold="search" value=<%=keyString %> name="key"  />
               		<span class="input-group-btn">
	                  <button class="btn btn-default" type="submit">
	                     Go!
	                  </button>
	               </span>
	            </div>
      </form> 
   </div>
      <div>
      <p class="navbar-text navbar-right">Signed in as 
         <a href="Login.jsp" class="navbar-link"><%=uid %></a>
      </p>
   </div>
</nav>


<ul class="nav nav-tabs">
   <li class="active"><a href="#">默认排序</a></li>
   <li><a href="#">相关度排序</a></li>
   <li><a href="#">价格排序</a></li>
	<li><a href="/dh_demo/rec?username=<%=uid%>">猜你喜欢</a></li>
</ul>

<div data-spy="scroll" data-target="#navbar-example" data-offset="0" 
   style="height:500px;overflow:auto; position: relative;" class="row" >
	<%
		int len = imgurls.length;
		for (Integer i=0;i<len;i++){
			
			String imgurl = imgurls[i];
			String itemcode = itemcodes[i];
			String supplierid = supplierids[i];
			String maxcost = maxcosts[i];
			String mincost = mincosts[i];
			String itemname = itemnames[i];
			String titemcode ="/dh_demo/rec?itemcode="+itemcode;
			String titemname = itemname.substring(0,Math.min(60,itemname.length()))+"...";
	%>
	
       <div class="col-sm-6 col-md-3"  >
	      <a class="thumbnail" href ="<%=titemcode %>" style="width:260px;height:300px;">
			   <img src=<%=imgurl %>  style="width:250px;height:250px;"  />
	      	   <small title="<%=itemname %>" style="color: rgb(159, 199, 241);width:250px;height:20px;font-size: 10px;"><%=titemname %></small>
		  </a>
	      <div class="caption">
	      
		  	  <p  style="font-size: 20px;color: rgb(255, 0, 60); font-family:Georgia, serif;font-weight: bold;width:250px;height:20px; " >$<%=mincost %> ~ <%=maxcost %></p>
		      <%String info = "info"+i.toString();
		      String tinfo = "#info"+i.toString();
		      %>
		      <div id="<%=info %>" class="collapse">
		         <p style="color: rgb(255, 0, 60);font-variant: small-caps;">itemcode:<%=itemcode %></p>
		         <p style="color: rgb(255, 0, 60);font-variant: small-caps;">supplierid:</p>
		         <p style="color: rgb(255, 0, 60);font-variant: small-caps;"><%=supplierid %></p>
		      </div>
		      <p >
		         <a href="#" class="btn btn-primary" role="button">
		               Buy
		         </a> 
	             <button type="button" class="btn btn-info" data-toggle="collapse" data-target="<%=tinfo %>">Detail</button>
		      </p>
	      </div>
   </div>
	
	<%
		}
	%>
	
</div>

   </body>
</html>
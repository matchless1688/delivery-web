<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/SuperTag.tld"  prefix="ui" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
	<head>
		<title></title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>  
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/theme/css/context.css"/>
	</head>
	<body>

		<%@include file="top.jsp"%>
		<div class="wrap">
			<div style="overflow: hidden;">
			     <%@include file="menu.jsp"%>
	            <div class="rightcontent"> 
	            <div class="controlpanel_add">
		            <form action="<%=basePath%>/admin/user_add.php" method="post" id="searchForm">
		            <label>用户名称</label> <input type="text" name="user.userName" value="${user.userName}"/>
		            <br/>
		            <label>手机号码</label> <input type="text" name="user.telPhone"  value="${user.telPhone}"/>
		            <p/>
		            <a class="simple_button" style="margin-left: 50px; margin-bottom:80px;" onclick="document.getElementById('searchForm').submit()">添加</a>
		            </form>
	            </div>
	            </div>
	        </div>
        </div>
        <%@include file="foot.jsp" %>
		 <script>
		  $('#userMenu').addClass('active');
		</script>
	</body>
</html>

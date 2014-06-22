 <%@ page language="java" pageEncoding="UTF-8"%>
   <%
String mpath = request.getContextPath();
String mbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+mpath;
%>
 <div class="leftnav">
	 <ul >
		<li id="userMenu"><a href="<%=mbasePath%>/admin/user_manage.php">用户管理</a></li>
		<li id="stationMenu"><a href="<%=mbasePath%>/admin/station_manage.php">储物柜管理</a></li>
		<li id="boxMenu"><a href="<%=mbasePath%>/admin/box_manage.php">箱子管理</a></li>
		<li id="expressMenu"><a href="<%=mbasePath%>/admin/express_manage.php">快件管理</a></li>
	 </ul>
 </div> 
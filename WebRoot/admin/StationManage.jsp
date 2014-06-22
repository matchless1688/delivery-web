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
		<script type="text/javascript">
		 
		    
		   function gotoPage(number)
	       {
	         $('#currentPage').val(number);
	         $('#searchForm').attr('action','<%=basePath%>/admin/station_manage.php');
	          document.getElementById("searchForm").submit();
	       }
	       function doDelete(id)
		   {
		      $.getJSON("<%=basePath%>/admin/station_delete.php", {id:id},
			   function(data){
			     $('#'+id).fadeOut().fadeIn().fadeOut();
		       });
		   }
		</script>
	</head>
	<body>

		<%@include file="top.jsp"%>
		<div class="wrap">
			<div style="overflow: hidden;">
			     <%@include file="menu.jsp"%>
	            <div class="rightcontent"> 
	            <div class="controlpanel">
		            <form action="<%=basePath%>/admin/station_search.php" method="post" id="searchForm">
		            <input type="hidden" name="currentPage" id="currentPage"/>
		            <label>名称</label><input type="text" name="station.name" value="${ station.name}"/>
		            <label>地址</label> <input type="text" name="station.address" value="${ station.address}"/>
		            <a class="simple_button" style="margin-left: 50px;" onclick="document.getElementById('searchForm').submit()">查询</a>
		            </form>
	            </div>
		           <table style="margin-top: 40px;" class="utable">
	            	<thead>
		                <tr>
		                    <th width="10%">名称</th>
		                    <th width="50%">地址</th>
		                    <th width="5%">箱数</th>
		                    <th width="10%">发布时间</th>
		                </tr>
	                </thead>
	                <tbody id="firstpublist">
	                    <c:forEach var="station" items="${page.dataList}">          
		                <tr id="${station.id}">	
		                   <td>${station.name }</td>
		                   <td>${station.address }</td>
		                   <td>${station.boxes }</td>
		                   <td>${station.createDate }</td>
		                   <td>
			                   <a href="javascript:doDelete(${station.id})">删除</a>
		                   </td>
		                </tr>
		                </c:forEach>
		                <c:if test="${page.count==0}">
		                 <tr>	
		                   <td colspan="7">暂无记录</td>
		                </tr>
		                </c:if>
	                </tbody>
	                <tfoot>
	                 <tr>	
		                   <td colspan="7">
                            <ui:page page="${page}" style="width: 100%;"></ui:page>
                           </td>
		                </tr>
	                </tfoot>
	            </table>
	            </div>
	        </div>
        </div>
        <%@include file="foot.jsp" %>
		 <script>
		  $('#stationMenu').addClass('active');
		</script>
	</body>
</html>

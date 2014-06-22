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
	       function doDelete(id)
		   {
		      $.post("<%=basePath%>/admin/box_delete.php", {id:id},
			   function(data){
			     $('#'+id).fadeOut().fadeIn().fadeOut();
		       });
		   }
		   
		    function doSave()
		   {
		      $.post("<%=basePath%>/admin/box_save.php", {domain:$('#A_domain').val(),key:$('#A_key').val(),value:$('#A_value').val()},
			   function(data){
			      if(data=='0')
			      {
			         alert("添加失败");
			       }else
			       {
			         var t = "<tr id='"+data+"'><td>"+$('#A_domain').val()+"</td><td>"+$('#A_key').val()+"</td><td>"+$('#A_value').val()+"</td><td><a href='javascript:doDelete("+data+")'>删除</a></td></tr>";
			         $(t).appendTo('#boxTable').fadeOut().fadeIn();
			       }
			       
			      
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
		            <form action="<%=basePath%>/admin/box_search.php" method="post" id="searchForm">
		            <input type="hidden" name="currentPage" id="currentPage"/>
		            <label>类型</label><input type="text" name="box.type"  value="${box.type }"/>
		            <a class="simple_button" style="margin-left: 50px;" onclick="document.getElementById('searchForm').submit()">查询</a>
		            </form>
	            </div>
		           <table style="margin-top: 30px;" class="utable" id="boxTable">
	            	<thead>
		                <tr>
		                    <th width="15%">长度</th>
		                    <th width="15%">高度</th>
		                    <th width="60%">宽度</th>
		                    <th width="60%">类型</th>
		                    <th width="10%">操作</th>
		                </tr>
	                </thead>
	                <tbody id="firstpublist">
	                    <c:forEach var="box" items="${dataList}">          
		                <tr id="${box.length}">	
		                   <td>${box.height}</td>
		                   <td>${box.width }</td>
		                   <td>${box.type }</td>
		                   <td>
			                   <a href="javascript:doDelete(${box.id})">删除</a>
		                   </td>
		                </tr>
		                </c:forEach>
		                
	                </tbody>
	                <tfoot>
	                  <c:if test="${count==0}">
		                 <tr>	
		                   <td colspan="4">暂无记录</td>
		                </tr>
		              </c:if>
	                </tfoot>
	            </table>
	            </div>
	        </div>
        </div>
        <%@include file="foot.jsp" %>
		 <script>
		  $('#boxMenu').addClass('active');
		</script>
	</body>
</html>

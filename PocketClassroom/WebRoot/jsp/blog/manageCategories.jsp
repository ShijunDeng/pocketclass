<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>分类管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog/managecategories.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/application.js"></script>
<script type="text/javascript">

</script>
</head>

<body id="container">
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
		<div id="left">
			<jsp:include page="left3.jsp"></jsp:include>
		</div>
		<div id="right">
			<div>
				<form action="${pageContext.request.contextPath}/servlet/StoreModifyCategoriesServlet" method="post">
					<table class="tab" cellspacing="1" cellpadding="3" id="mytable">
						<tr>
							<td class="blog_con blog_title" colspan="3" height="25"><b>
									分类管理 </b></td>
						</tr>
						
						<tr>
							<th class="td_left" >
								类别
							</th>
							<th class="td_center" >
								文章
							</th>
							<th class="td_right" >
								操作
							</th>
						</tr>
						<c:forEach items="${customlist}" var="custom">
							<tr>
							<td class="td_left">
							<input type="hidden" name="idCustom" value="${custom.idCustom}">
							<input type="hidden" name="customName" value="${custom.customName}">${custom.customName}
							</td>
							<td class="td_center"><input type="hidden" name="noteAmount" value="${custom.noteAmount}">${custom.noteAmount}
							</td>
							<c:choose>
							<c:when test="${custom.customName=='默认分类'}">
							<td class="td_right">显示|隐藏</td>
							</c:when>
							<c:otherwise>
							<td class="td_right">
							<a href="javascript:void(0);" onclick="editRow(this);">编辑</a>|<a href="javascript:void(0);" onclick="delRow(this)">删除</a>|显示|隐藏
							</td>
							</c:otherwise>
							</c:choose>
						</tr>
						</c:forEach>
					</table>
					<div style="text-align:left">添加分类：<input type="text" id="newCategory"/><input type="button" value="确定" onclick="checkCategory()"/></div>
					<div><input type="submit" value="保存修改"/></div>
					<%--start:用于保存被删除的分类(原来已存在),以便将此分类下的笔记转移到默认分类中--%>
					<input type="hidden" value="" name="deletedCategory" id="deletedCategory"/>
					<%--end:用于保存被删除的分类(原来已存在),以便将此分类下的笔记转移到默认分类中--%>
					
					<%--start:用于保存被修改的分类(不管原来存不存在，或者修改后又删除了)--%>
					<input type="hidden" value="" name="modifiedId" id="modifiedId"/>
					<input type="hidden" value="" name="modifiedName" id="modifiedName"/>
					<%--end:用于保存被修改的分类(原来已存在)--%>
					
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

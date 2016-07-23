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

<title>发表新笔记</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog/displaynote.css">
<script type="text/javascript">
function confirmDelete()
{
	if(!confirm('确定删除此笔记吗？删除后，相关评论将一并删除！'))
	{
		return false;
	}
	return true;
}
</script>
</head>

<body id="container">
	<%--start:判断是不是所有者--%>
	<c:choose>
	<c:when test="${sessionScope.username eq spaceinfo.username}">
	<c:set var="isMaster" value="yes"></c:set>
	</c:when>
	<c:otherwise>
	<c:set var="isMaster" value="no"></c:set>
	</c:otherwise>
	</c:choose>
	<%--end:判断是不是所有者--%>
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
		<div id="left">
			<jsp:include page="left3.jsp"></jsp:include>
		</div>
		<div id="right">
			<div class="item">
				<div class="item_top">${note.title}</div>

				<div class="item_right">
					<div class="item_intro">
						${note.content}
					</div>
				</div>
				<div class="item_bottom">${note.addTime}
					阅读(${note.viewAmount}) 评论(${note.cmtAmount})
					<c:if test="${isMaster eq 'yes'}">
					<a href="${pageContext.request.contextPath}/servlet/DisplayNoteServlet?idNote=${note.idNote}&oper=edit">编辑</a> <a href="${pageContext.request.contextPath}/servlet/DeleteNoteServlet?idNote=${note.idNote}" onclick="return confirmDelete()">删除</a>
					</c:if>
				</div>
			</div>
			<fieldset>
				<legend>评论列表</legend>
				<div class="item_comment">
					<div>
						<span class="userspan" style="float:left;">pocketclass01&nbsp;&nbsp;&nbsp;2010-10-10 10:50:40&nbsp;发表</span><span
							class="timespan" style="float:right;">回复</span>
					</div>
					<div class="clear"></div>
					<div style="height:30px;text-align:justify;">不错不错</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>发表评论</legend>
				<c:choose>
					<c:when test="${!empty sessionScope.username}">
						<script>
							function getFocus(e, a) {
								if (e && e.preventDefault)
									e.preventDefault();
								else
									window.event.returnValue = false;
								a.focus();
							}
							function checkComment() {
								var txtstr = document.getElementById("comment").value;
								var regex = /^\s+$/g;

								if (txtstr.length == 0 || regex.test(txtstr)) {
									var spanNode = document
											.getElementById("txtspan");
									spanNode.innerHTML = "评论内容不能为空!!"
											.fontcolor("red");
									return false;
								}
							}
						</script>
						<form
							action="${pageContext.request.contextPath}/servlet/PublishCommentServlet?idNote=${note.idNote}"
							method="post">
							<textarea id="comment" rows="10" style="overflow:hidden" name="content" cols="77" onmousedown="getFocus(event,this);"></textarea>
								<input type="hidden" name="token" value="${token}"> 
						    	<input type="hidden" name="idNote" value="${note.idNote}"/>
						    	<input type="hidden" name="username" value="${sessionScope.username}" />
						    	<input type="hidden" name="parentId" value="0"/>
						    	<input type="submit" value="马上发表" onclick="return checkComment()">
						    <span id="txtspan"></span>
						</form>
					</c:when>
					<c:otherwise>
						<span style="color:red;text-align:center;">您需要登录后才能发表评论！ </span>
					</c:otherwise>
				</c:choose>
			</fieldset>
		</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

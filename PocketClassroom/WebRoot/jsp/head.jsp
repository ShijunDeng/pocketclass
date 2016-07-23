<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" media="all">
<script type="text/javascript">
	function bookmarkit() {
		window.external.AddFavorite('http://www.baidu.com', '掌上课堂PC版');
	}

	function shift(obj) {
		var childNodes = document.getElementById("items").getElementsByTagName(
				"a");

		for ( var i = 0; i < childNodes.length; i++) {
			childNodes[i].className = "";
		}
		var parent = obj.parentNode;
		parent.className = "current";
	}
</script>


<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<div id="container">
		<div id="top_title" style="background-color:#F4FCFC;text-align:right;">
			<p id="top_addFav">
				<a href="javascript:void(0)" onclick="bookmarkit()">+:收藏本网站</a>
			</p>

			<c:choose>
				<c:when test="${empty sessionScope.username}" >
					<p class="top_p">
						欢迎来到本网站
					</p>
					<p class="top_p">
						<a href="${pageContext.request.contextPath}/jsp/login/login.jsp">请登录</a>
					</p>
					<p class="top_p">
						<a
							href="${pageContext.request.contextPath}/jsp/register_jsp/register.jsp">注册</a>
					</p>
				</c:when>
				<c:otherwise>
					<p class="top_p">
						欢迎	<c:out value="${sessionScope.username}"></c:out>来到本网站
					</p>
					<p class="top_p">
						<a href="${pageContext.request.contextPath}/servlet/LogoutServlet?username=${sessionScope.username}">退出</a>
					</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/" id="logo"></a>
			<div id="items">
				<a href="${pageContext.request.contextPath}/"
					class="current"><span onclick="shift(this)">&nbsp;找课程&nbsp;</span>
				</a><a
					href="${pageContext.request.contextPath}/servlet/LearnServlet?isEnd=NO"><span
					onclick="shift(this)">&nbsp;去学习&nbsp;</span> </a><a href="${pageContext.request.contextPath}/servlet/ForumsIndexServlet">&nbsp;要交流&nbsp;</a><a
					href="${pageContext.request.contextPath}/servlet/NoteCenterServlet"><span onclick="shift(this)">&nbsp;记笔记&nbsp;</span> </a><a
					href="${pageContext.request.contextPath}/servlet/VideoDownLoadServlet"><span
					onclick="shift(this)">&nbsp;课程下载&nbsp;</span> </a>
			</div>
		</div>
		<div class="clear"></div>
		<hr
			style="width:960px;background-color:#F4AC34;color:#F4AC34;margin-top:0px;height:7px;" />
	</div>
</body>


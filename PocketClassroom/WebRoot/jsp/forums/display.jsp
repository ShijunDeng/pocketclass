<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>学习交流-掌上课堂</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/forums/forums.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/soft/xheditor-1.1.14/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/soft/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/js/common/application.js"></script>

<script type="text/javascript">
	function checkContent(formNode, tip) {
		return true;
		var spanNode = document.getElementById(tip);
		with (formNode) {
			if (content.value.length > 2001) {
				spanNode.style.display = "inline";
				spanNode.innerHTML = "<font color=red>字数不能超过2000字</font>";
				return false;
			}
			var regex = /^\s+$/g;
			if (content.value.length == 7 || content.value.length == 0
					|| (regex.test(content.value)) == true) {
				spanNode.style.display = "inline";
				spanNode.innerHTML = "<font color=red>回复内容不能为空</font>";
				return false;
			}
			spanNode.style.display = "none";
			return true;
		}
	}

	function checkTitle(titleNode) {
		return !isNullOrBlank(titleNode.value);
	}
	function checkForm() {
		var titleNode = document.getElementById("title");
		if (isNullOrBlank(titleNode.value)) {
			var titleTip = document.getElementById("titleTip");
			titleTip.innerHTML = "<font color=red>标题不能为空</font>";
			return false;
		}
		return true;
	}
</script>
</head>

<body>
	<div align="center">
		<div align="center">
			<jsp:include page="../head.jsp"></jsp:include>
		</div>
		<div class="main">
			<table width="100%" align="center">
				<tr>
					<td valign="middle" align="left" colspan="2"><span
						style="font-size:14px"> <a
							style="font-weight:bold;color:#077AC5;text-decoration:none"
							href="${pageContext.request.contextPath}/servlet/ForumsIndexServlet">交流首页</a>
							&raquo; <a
							style="font-weight:bold;color:#077AC5;text-decoration:none"
							href="#">${cateGory}</a> </span></td>
				</tr>
				<tr>
					<td width="100%" height="auto" valign="top">
						<table cellspacing="0" cellpadding="2" width="100%" align="center"
							border="0">
							<tr>
								<td class="gensmall" style="padding: 10px;width:350px;">
									<form action="http://www.baidu.com/s" method="get"
										id="formSearch" name="formSearch">
										<input type="text" id="a" name="a"
											style="width:0px;height:0px;"> <input type="text"
											id="wd" name="wd" value="" style="width: 180px;height: 23px;" />
										<input type="button" class="btn-img btn-s1" value="搜交流"
											onclick="window.open('http://www.baidu.com/s?wd=site:baidu.com '+document.getElementById('wd').value)" />
									</form>
								</td>


							</tr>
						</table></td>
				</tr>
			</table>

			<div class="contentform">
				<table cellspacing="1" cellpadding="2" width="100%" border="0">
					<tr>
						<th class="menu_div" nowrap="nowrap" colspan="2" height="25"
							align="center" valign="middle">&nbsp;主题&nbsp;</th>
						<th class="mu1" nowrap="nowrap" width="60">&nbsp;回复条数&nbsp;</th>
						<th class="mu1" nowrap="nowrap" width="60">&nbsp;浏览次数&nbsp;</th>
						<th class="mu1" nowrap="nowrap" width="120">&nbsp;发表人&nbsp;</th>
						<th class="mu2" nowrap="nowrap" width="200">&nbsp;发表时间&nbsp;</th>
					</tr>


					<c:forEach items="${posts}" var="post">
						<tr class="td">
							<td class="td_a" valign="middle" align="center" width="40">
								<img class="leftlogo"
								src="${pageContext.request.contextPath}/images/forums/forum_a.gif"
								alt="" />
							</td>
							<td class="td_a" width="500"
								style="word-wrap:break-word; word-break:break-all; overflow:hidden;">
								<span class="topictitle"> <a
									href="${pageContext.request.contextPath}/servlet/ForumsListServlet?idPost=${post.idPost}"
									style="text-decoration:none;"> ${post.title} </a> </span>
							</td>

							<td class="td_b" valign="middle" align="center"><span
								class="postdetails">${post.replyNum}</span></td>
							<td class="td_c" valign="middle" align="center"><span
								class="name"> ${post.browNum} </span>
							</td>

							<td class="td_b" valign="middle" align="center"><span
								class="postdetails">${post.username}</span></td>
							<td class="td_c" valign="middle" nowrap="nowrap" align="center"><span
								class="postdetails">${post.postTime}<br /> <a id=''
									style="text-decoration:none;font-size: 14px;"
									href='javascript:void(0)'>${post.username}</a> </span> <a href="#"
								style="text-decoration:none;"><img
									src="${pageContext.request.contextPath}/images/forums/pointer.jpg"
									border="0" alt="[Latest pointer]" /> </a>
							</td>

						</tr>
					</c:forEach>

				</table>



				<span class="op_form"> <c:choose>
						<c:when test="${requestScope.currentPage==1}">
							<c:out value="首页"></c:out>
						</c:when>
						<c:otherwise>
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsServlet?option=first">首页</a>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${requestScope.currentPage > 1}">
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsServlet?option=prior">上一页</a>
						</c:when>
						<c:otherwise>
							<c:out value="上一页"></c:out>
						</c:otherwise>
					</c:choose> <c:forEach begin="${requestScope.startPageTag}"
						end="${requestScope.endPageTag}" var="i">
						<c:choose>
							<c:when test="${requestScope.currentPage == i }">${i }</c:when>
							<c:otherwise>
								<a style="text-decoration:none;"
									href="${pageContext.request.contextPath}/servlet/ForumsServlet?option=jump&page=${i}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach> <c:choose>
						<c:when
							test="${requestScope.currentPage < requestScope.pageCount }">
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsServlet?option=next">下一页</a>
						</c:when>
						<c:otherwise>
							<c:out value="下一页"></c:out>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when
							test="${requestScope.currentPage == requestScope.pageCount }">
							<c:out value="尾页"></c:out>
						</c:when>
						<c:otherwise>
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsServlet?option=end">尾页</a>
						</c:otherwise>
					</c:choose> </span>
			</div>
		</div>
		<div class="clear"></div>
		<div class="main">
			<div>
				<form id="postcontent_p"
					action="${pageContext.request.contextPath}/servlet/ForumsServlet?option=newpost"
					method="post" onsubmit="return checkForm()">
					<div class="form_item">
						<a name="reply"></a> <span class="label">标题<sup
							class="must">*</sup>：</span>
						<div>
							<input type="text" id="title" name="title"><span
								id="titleTip" class="tip">请填写标题</span>
						</div>
						<div class="clear"></div>
						<span class="label">分类<sup class="must">*</sup>：</span>
						<div>
							<select name="selectedOption">
								<c:forEach items="${middlesOfSuper}" var="middleOfSuper">
										document.write("<option value='${middleOfSuper.key }'>${middleOfSuper.value
										}</option>"");
									</c:forEach>
							</select><span class="tip" id="reply_tip">注意：内容最长2000字符</span>
						</div>	<div class="clear"></div>
					<span class="label">内容<sup class="must">*</sup>：</span>
						<div style="padding: 0 0 0 10px;" class="text">
							<textarea id="content" name="content" class="xheditor"
								style="width:600px;height:200px">
							</textarea>
						</div>
					</div>
					<div class="form_item">
						<span class="label">&nbsp;</span>
						<div class="sendBtn">
							<input id="doSubmit" type="submit" class="btn-img btn-s1"
								value="发布" />
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="clear"></div>

		<div>
			<jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>

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


</head>

<body>
	<div align="center">
		<div align="center">
			<jsp:include page="../head.jsp"></jsp:include>
		</div>
		<div class="main">
			<table width="100%" align="center">
				<tr>
					<td width="100%" height="auto" valign="top">
						<table cellspacing="0" cellpadding="2" width="100%" align="center"
							border="0">
							<tr>
								<td valign="bottom" style="padding: 10px;width: 290px;"><span
									class="smalltext">您最近一次登入是在: 0000-00-00 00:00:00</span><br />
									<span class="smalltext">现在的时间是:${time}</span><br /></td>

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

								<td class="gensmall" valign="middle" align="right">&nbsp; <img
									src="${pageContext.request.contextPath}/images/forums/latest_mini_logo.gif"
									alt="" /> <a id="latest" class="minimenu" href="#">最新</a>
									&nbsp; <img
									src="${pageContext.request.contextPath}/images/forums/latest_mini_logo.gif"
									alt="" /> <a id="hottest" class="minimenu" href="#">最热</a>
									&nbsp;</td>
							</tr>
						</table></td>
				</tr>
			</table>

			<div class="contentform">
				<table cellspacing="1" cellpadding="2" width="100%" border="0">
					<tr>
						<th class="menu_div" nowrap="nowrap" colspan="2" height="25"
							align="center" valign="middle">&nbsp;版面&nbsp;</th>
						<th class="mu1" nowrap="nowrap" width="60">&nbsp;主题&nbsp;</th>
						<th class="mu1" nowrap="nowrap" width="60">&nbsp;文章&nbsp;</th>
						<th class="mu2" nowrap="nowrap">&nbsp;最后发表&nbsp;</th>
					</tr>


					<c:forEach items="${forumIndexBeans}" var="forumIndexBean">
						<tr>
							<td class="catleft" colspan="2" height="28"><span
								class="cattitle">${forumIndexBean.cateGory}</span>
							</td>
							<td class="catright" align="right" colspan="3">&nbsp;</td>
						</tr>

						<tr>
							<td class="titlerow" valign="middle" align="center" height="50"><img
								class="minilogo"
								src="${pageContext.request.contextPath}/images/forums/forum.jpg"
								alt="[Folder]" />
							</td>
							<td class="titlerow" width="100%" height="50"><span
								class="forumlink"><a class="forumlink"
									style="text-decoration:none;"
									href="${pageContext.request.contextPath}/servlet/ForumsServlet?idSuper=${forumIndexBean.idSuper}">${forumIndexBean.cateGory}</a>
							</span><br /> <span class="genmed"><c:forEach
										items="${forumIndexBean.middles}" var="middle">${middle}&nbsp;&nbsp;</c:forEach>
							</span> <br /></td>
							<td class="contentrow" valign="middle" align="center" height="50"><span
								class="gensmall">${forumIndexBean.topicNum}</span>
							</td>
							<td class="contentrow" valign="middle" align="center" height="50"><span
								class="gensmall">${forumIndexBean.postsNum}</span></td>
							<td class="contentrow" valign="middle" nowrap="nowrap"
								align="center" height="50"><span class="postdetails">
									${forumIndexBean.latestPostTime}<br /> <a id=''
									style="text-decoration:none;font-size: 14px;"
									href='javascript:void(0)'>${forumIndexBean.username}</a> <a
									href="#" style="text-decoration:none;"><img
										src="${pageContext.request.contextPath}/images/forums/pointer.jpg"
										border="0" alt="[Latest Reply]" /> </a> </span></td>
						</tr>

					</c:forEach>
					<tr>
						<td class="catleft" colspan="2" height="28"><span
							class="cattitle">学习问题讨论 </span>
						</td>
						<td class="catright" align="right" colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class="titlerow" valign="middle" align="center" height="50"><img
							class="minilogo"
							src="${pageContext.request.contextPath}/images/forums/forum.jpg"
							alt="[Folder]" />
						</td>
						<td class="titlerow" width="100%" height="50"><span
							class="forumlink"><a class="forumlink" href="#">学习问题讨论</a>
						</span><br /> <span class="genmed">coding world</span> <br /></td>
						<td class="contentrow" valign="middle" align="center" height="50"><span
							class="gensmall">0000</span>
						</td>
						<td class="contentrow" valign="middle" align="center" height="50"><span
							class="gensmall">0000</span></td>
						<td class="contentrow" valign="middle" nowrap="nowrap"
							align="center" height="50"><span class="postdetails">
								0000-00-00 00:00:00<br /> <a id='' style="font-size: 14px;"
								href='javascript:void(0)'>AAAAAAAAA</a> <a href="#"><img
									src="${pageContext.request.contextPath}/images/forums/pointer.jpg"
									border="0" alt="[Latest Reply]" /> </a> </span></td>
					</tr>




					<tr>
						<td class="catleft" colspan="2" height="28"><span
							class="cattitle">休闲空间 </span>
						</td>
						<td class="catright" align="right" colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class="titlerow" valign="middle" align="center" height="50"><img
							class="minilogo"
							src="${pageContext.request.contextPath}/images/forums/forum.jpg"
							alt="[Folder]" />
						</td>
						<td class="titlerow" width="100%" height="50"><span
							class="forumlink"><a class="forumlink" href="#">休闲空间</a> </span><br />
							<span class="genmed">共享快乐生活</span> <br /></td>
						<td class="contentrow" valign="middle" align="center" height="50"><span
							class="gensmall">0000</span>
						</td>
						<td class="contentrow" valign="middle" align="center" height="50"><span
							class="gensmall">0000</span></td>
						<td class="contentrow" valign="middle" nowrap="nowrap"
							align="center" height="50"><span class="postdetails">
								0000-00-00 00:00:00<br /> <a id='' style="font-size: 14px;"
								href='javascript:void(0)'>AAAAAAAAA</a> <a href="#"><img
									src="${pageContext.request.contextPath}/images/forums/pointer.jpg"
									border="0" alt="[Latest Reply]" /> </a> </span></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="clear"></div>

		<div>
			<jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>

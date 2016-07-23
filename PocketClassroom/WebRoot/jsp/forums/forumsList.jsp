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
	function checkContent(formNode,tip) {
		return true;
		var spanNode = document.getElementById(tip);
		with (formNode) {
			if (content.value.length > 2001) {
				spanNode.style.display = "inline";
				spanNode.innerHTML = "<font color=red>字数不能超过2000字</font>";
				return false;
			}
			var regex = /^\s+$/g;
			if (content.value.length==7||content.value.length==0||(regex.test(content.value)) == true) {
				spanNode.style.display = "inline";
				spanNode.innerHTML = "<font color=red>回复内容不能为空</font>";
				return false;
			}
			spanNode.style.display = "none";
			return true;
		}
	}
</script>
</head>
<body>
	<div align="center">
		<div align="center">
			<jsp:include page="../head.jsp"></jsp:include>
		</div>
		<div></div>
		<div class="main">
			<table width="100%" align="center">
				<tr>
					<td valign="middle" align="left" colspan="2"><span
						style="font-size:14px"> <a
							style="font-weight:bold;color:#077AC5;text-decoration:none"
							href="${pageContext.request.contextPath}/servlet/ForumsIndexServlet">交流首页</a>
							&raquo;<a style="font-weight:bold;color:#077AC5;text-decoration:none" href="${pageContext.request.contextPath}/servlet/ForumsServlet?idSuper=${idSuper}">${cateGory }</a>&raquo; <a
							style="font-weight:bold;color:#077AC5;text-decoration:none"
							href="#">${post.title}</a> </span>
					</td>
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
									</form></td>


							</tr>
						</table>
					</td>
				</tr>
			</table>

			<div class="contentform">
				<table cellspacing="1" cellpadding="2" width="100%" border="0">
					<tr>
						<th class="thleft" nowrap="nowrap" width="150" height="26">发表人</th>
						<th class="thright" nowrap="nowrap" width="100%">内容</th>
					</tr>
					<tr>
						<td class="td_a" valign="middle" align="center" width="100"
							style="border-bottom: 1px #D7D7D7 solid; ">
							<div>
								<a href="#"> <img class="parentleftlogo"
									src="${pageContext.request.contextPath}/images/heads/${post.headPorAdd}"
									alt="" /> </a>
							</div>
							<div>
								<a class="forumlink" href="#" style="text-decoration:none;">${post.username}</a>
							</div></td>
						<td style="border-bottom: 1px #D7D7D7 solid; ">
							<div class="parent_postcontent">
								<a href="#" style="text-decoration:none;font-size:12px">${post.username}</a>${post.content}
							</div>
							<div class="parent_tail">
								主题 ${post.postTime}<a href="#reply"
									style="text-decoration:none;">回复</a>
							</div></td>
					</tr>

					<c:forEach items="${forumListElements}" var="forumListElement">
						<tr>
							<td class="td_a" valign="middle" align="center" width="100"
								style="border-bottom: 1px #D7D7D7 solid; ">
								<div>
									<a href="#"><img class="parentleftlogo"
										src="${pageContext.request.contextPath}/images/heads/${forumListElement.mPostReply.headPorAdd}"
										alt="" /> </a>
								</div>
								<div>
									<a class="forumlink" href="#" style="text-decoration:none;">${forumListElement.mPostReply.username}</a>
								</div></td>
							<td style="border-bottom: 1px #D7D7D7 solid; ">
								<div class="parent_postcontent">
									<a href="#" style="text-decoration:none;font-size:12px">${forumListElement.mPostReply.username}</a>
									${forumListElement.mPostReply.content }
								</div>
								<div class="parent_tail">
									${forumListElement.order}楼
									${forumListElement.mPostReply.postTime} <a
										href='javascript:void(0)'  id="reply_am${forumListElement.order}"
										onclick="createReplyBox('postcontent_m${forumListElement.order}','reply_am${forumListElement.order}')"
										style="text-decoration:none;">回复</a>
									<div>
										<form id="postcontent_m${forumListElement.order}" class="reply_display"
											action="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=newlpost&parentPostId=${forumListElement.mPostReply.idPost}"
											method="post"
											onsubmit="return checkContent(this,'reply_mtip${forumListElement.order}')">
											<div class="form_item">
												 <span class="tip" id="reply_mtip${forumListElement.order}"
													style="padding: 0 0 0 100px;">注意：内容最长2000字符</span><br>
												<span class="label">内容<sup class="must">*</sup>：</span>
												<div style="padding: 0 0 0 10px;" class="text">
													<textarea id="content" name="content" class="xheditor"
														style="width:600px;height:100px">
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
								<div class="blank">&nbsp;</div> <c:forEach
									items="${forumListElement.lPostReplys}" var="lPostReply">
									<div class="postreply">
										<div class="postreply_row">
											<div class="reply_left">
												<a href="#" style="text-decoration:none;"> <img
													class="replyleftlogo"
													src="${pageContext.request.contextPath}/images/heads/${lPostReply.headPorAdd}"
													alt="" /> </a>
											</div>
											<div class="reply_right">
												<div class="reply_content">
													<a href="#" style="text-decoration:none;font-size:12px">${lPostReply.username}</a>
													${lPostReply.content}
												</div>
												<div class="reply_tail">
													${lPostReply.postTime} <a href='javascript:void(0)' id="reply_al${lPostReply.postTime}"
														onclick="createReplyBox('postcontent_l${lPostReply.postTime}','reply_al${lPostReply.postTime}')"
														style="text-decoration:none;">回复</a>
												</div>
												<div>
													<form  id="postcontent_l${lPostReply.postTime}" class="reply_display"
														action="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=newlpost&parentPostId=${forumListElement.mPostReply.idPost}&parentPostUsername=${lPostReply.username}"
														method="post"
														onsubmit="return checkContent(this,'reply_ltip${lPostReply.postTime}')">
														<div class="form_item">
															 <span class="tip" id="reply_ltip${lPostReply.postTime}"
																style="padding: 0 0 0 100px;">注意：内容最长2000字符</span><br>
															<span class="label">内容<sup class="must">*</sup>：</span>
															<div style="padding: 0 0 0 10px;" class="text">
																<textarea id="content" name="content" class="xheditor"
																	style="width:600px;height:100px">
																</textarea>
															</div>
														</div>
														<div class="form_item">
															<span class="label">&nbsp;</span>
															<div class="sendBtn">
																<input id="doSubmit" type="submit"
																	class="btn-img btn-s1" value="发布" />
															</div>
														</div>
													</form>
												</div>
												<span class="clear"></span>
											</div>
										</div>
									</div>
								</c:forEach></td>
						</tr>
					</c:forEach>
				</table>

				<span class="op_form"> <c:choose>
						<c:when test="${requestScope.currentPage==1}">
							<c:out value="首页"></c:out>
						</c:when>
						<c:otherwise>
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=first">首页</a>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${requestScope.currentPage > 1}">
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=prior">上一页</a>
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
									href="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=jump&page=${i}">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach> <c:choose>
						<c:when
							test="${requestScope.currentPage < requestScope.pageCount }">
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=next">下一页</a>
						</c:when>
						<c:otherwise>
							<c:out value="下一页"></c:out>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when
							test="${requestScope.currentPage >= requestScope.pageCount }">
							<c:out value="尾页"></c:out>
						</c:when>
						<c:otherwise>
							<a style="text-decoration:none;"
								href="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=end">尾页</a>
						</c:otherwise>
					</c:choose> </span>
			</div>
		</div>
		<div class="clear"></div>
		<div class="main">
			<table width="100%" align="center">
				<tr>
					<td valign="middle" align="left" colspan="2"><span
						style="font-size:14px"> <a
							style="font-weight:bold;color:#077AC5;text-decoration:none"
							href="${pageContext.request.contextPath}/servlet/ForumsServlet?idMiddle=${post.idMiddle}">&lt;&lt;返回上一级</a>
					</span>
					</td>
				</tr>
			</table>
			<div>
				<form id="postcontent_p"
					action="${pageContext.request.contextPath}/servlet/ForumsListServlet?option=newmpost&idPost=${post.idPost}"
					method="post" onsubmit="return checkContent(this,'reply_tip')">
					<div class="form_item">
						<a name="reply"></a> <span class="tip" id="reply_tip"
							style="padding: 0 0 0 100px;">注意：内容最长2000字符</span><br> <span
							class="label">内容<sup class="must">*</sup>：</span>
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

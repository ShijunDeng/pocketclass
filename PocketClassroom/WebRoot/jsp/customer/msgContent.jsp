<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的消息 - 我的课堂 - 掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/customer/favorites.css"
	rel="stylesheet" type="text/css" media="all">
<script language="javascript"
	src="${pageContext.request.contextPath}/js/common/application.js"></script>

</head>

<body>
	<div align="center">
		<div align="center">
			<jsp:include page="../head.jsp"></jsp:include>
		</div>
		<div class="main">
			<div class="left">
				<jsp:include page="left.jsp"></jsp:include>
			</div>
			<div class="right">
				<div class="blueLogo_a">
					<div class="title_a">我的消息</div>
				</div>
				<div class="r_form" style="margin:0px;padding:0px">
					<table width="100%" cellspacing="1" cellpadding="1" border="0"
						style="text-align: center;float:left;line-height: 25px;">
						<tbody>
							<tr style="border:1px #00AAFF dashed;">
								<td colspan="3" class="rightBottom"><a href="#"
									style="text-decoration:none">${msg.sendUserName}</a>发送的消息</td>
							</tr>
							<tr>
								<td width="30%" class="rightBottom">发件人</td>
								<td width="30%" class="rightBottom">收件人</td>
								<td width="40%" class="bottom1">发送时间</td>
							</tr>
							<tr>
								<td class="rightBottom">${msg.sendUserName}</td>
								<td class="rightBottom">${msg.addreUserName}</td>
								<td class="bottom1">${msg.sendTime}</td>
							</tr>
							<tr>
								<td class="rightBottom">标题</td>
								<td colspan="2" class="rightBottom">${msg.title}</td>
							</tr>
							<tr>
								<td colspan="3" class="rightBottom">内容</td>
							</tr>
							<tr>
								<td colspan="3" style="padding-left: 20px;" class="rightBottom">
									<div
										style="width: 700px;float: left;text-align: left;word-wrap: break-word;min--height:150px;height:auto;">
										<p>${msg.content}</p>
									</div></td>
							</tr>
							<tr>
								<td colspan="3" align="center" style="padding: 10px 0 5px 0;">
									<a
									href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=001"
									class="btn-img btn-s1" style="margin-right: 10px;">返回</a> <a
									href="${pageContext.request.contextPath}/servlet/MsgContentServlet?option=forward"
									class="btn-img btn-s1" style="margin-right: 10px;">转发</a> <a
									href="${pageContext.request.contextPath}/servlet/MsgContentServlet?option=reply"
									class="btn-img btn-s1" style="margin-right: 10px;">回复</a> <a
									href="javascript:void(0)"
									onclick="toConfirm('确定转入收藏箱吗?', '${pageContext.request.contextPath}/servlet/MsgContentServlet?option=toFavobox&info=${msg.sendUserName}^${msg.sendTime}')"
									class="btn-img btn-s1" style="margin-right: 10px;">转入收藏箱</a> <a
									href="javascript:void(0)"
									onclick="toConfirm('确定转入垃圾箱吗?', '${pageContext.request.contextPath}/servlet/MsgContentServlet?option=toSpambox&info=${msg.sendUserName}^${msg.sendTime}')"
									class="btn-img btn-s1" style="margin-right: 10px;">转入垃圾箱</a></td>
							</tr>
						</tbody>
					</table>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<div>
			<jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
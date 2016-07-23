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

<script type="text/javascript">
	function checkAll(tag, isChecked) {
		var allInput = document.getElementsByName(tag);
		var loopTime = allInput.length;
		for (i = 0; i < loopTime; i++) {
			if (allInput[i].type == "checkbox") {
				allInput[i].checked = isChecked;
			}
		}
	}
	function confirmOp(tag, msg, obj, optiontype) {
		var allInput = document.getElementsByName(tag);
		var loopTime = allInput.length;
		var checkedNum = 0;
		for (i = 0; i < loopTime; i++) {
			if (allInput[i].checked) {
				checkedNum++;
			}
		}
		if (checkedNum < 1) {
			alert("您当前没有选中任何操作项！");
			return false;
		} else if (confirm(msg)) {
			//根据不同的请求类型动态的改变action
			document.form1.action = "${pageContext.request.contextPath}/servlet/MsgServlet?option="
					+ optiontype + "&idLoca=002";
			obj.form.submit();
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
		<div class="main">
			<div class="left">
				<jsp:include page="left.jsp"></jsp:include>
			</div>
			<div class="right">
				<div class="blueLogo_a">
					<div class="title_a">我的消息</div>
				</div>								
				<div class="r_form">
					<div class="select">
						<div id=con>
							<span id=tab_tags class="unselected1"><a
								href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=001">收信箱</a>
							</span> <span class="selected"><a
								href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=002">发信箱</a>
							</span> <span class="unselected"><a
								href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=003">收藏箱</a>
							</span><span class="unselected"><a
								href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=004">垃圾箱</a>
							</span> <span class="selected_blue"><a
								href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=000">发送新信息</a>
							</span>
						</div>
					</div>		
					<div class="clear"></div>			
					<div class="blue" style="width: 100%;" style="font-family:'幼圆'">
						<div class="tb-hidden">
							<c:choose>
								<c:when test="${msgs!=null&&requestScope.pageCount>0}">
									<form method="post" name="form1">
										<table width="100%" cellspacing="0" cellpadding="0" border="1">
											<tbody>
												<tr>
													<th width="10%">选中项</th>
													<th width="20%">标题</th>
													<th width="40%">收件人</th>
													<th width="30%">发送时间</th>

												</tr>
											</tbody>

											<c:forEach items="${msgs}" var="msg">
												<tr class="align_center">
													<td><input type="checkbox" name="uuids"
														style="margin-right: 14px;"
														value="${msg.sendUserName}&${msg.sendTime}" /></td>
													<td><a
														href="${pageContext.request.contextPath}/servlet/MsgContentServlet?option=content&msg=${msg.sendUserName}^${msg.sendTime}&idLoca=001">${msg.title}
													</a>
													</td>
													<td><a href="#">${msg.sendUserName}</a>
													</td>
													<c:set var="sendTime" value="${msg.sendTime}"></c:set>
													<td>${fn:replace(sendTime," ","<br>")}</td>
												</tr>
											</c:forEach>
										</table>
										<br> <input type="button" value="转入垃圾箱"
											class="btn-img btn-s1" style="margin-right: 10px;"
											onclick="confirmOp('uuids','确认要将选中消息转入垃圾箱？',this,'toSpambox') ;" />
										<input type="button" value="转入收藏箱" class="btn-img btn-s1"
											style="margin-right: 10px;"
											onclick="confirmOp('uuids','确认要将选中消息转入收藏箱？',this,'toFavobox')" />
										<input type="button" value="删除" class="btn-img btn-s1"
											style="margin-right: 10px;"
											onclick="confirmOp('uuids','确认要删除选中项？',this,'delete')" />
									</form>
									<span class="op_form"> <input type="checkbox"
										id="checkAll" onclick="checkAll('uuids',this.checked);">全选
										<c:choose>
											<c:when test="${requestScope.currentPage==1}">
												<c:out value="首页"></c:out>
											</c:when>
											<c:otherwise>
												<a
													href="${pageContext.request.contextPath}/servlet/MsgServlet?option=first&idLoca=002">首页</a>
											</c:otherwise>
										</c:choose> <c:choose>
											<c:when test="${requestScope.currentPage > 1}">
												<a
													href="${pageContext.request.contextPath}/servlet/MsgServlet?option=prior&idLoca=002">上一页</a>
											</c:when>
											<c:otherwise>
												<c:out value="上一页"></c:out>
											</c:otherwise>
										</c:choose> <c:forEach begin="${requestScope.startPageTag}"
											end="${requestScope.endPageTag}" var="i">
											<c:choose>
												<c:when test="${requestScope.currentPage == i }">${i }</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/servlet/MsgServlet?option=jump&idLoca=002&page=${i}">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach> <c:choose>
											<c:when
												test="${requestScope.currentPage < requestScope.pageCount }">
												<a
													href="${pageContext.request.contextPath}/servlet/MsgServlet?option=next&idLoca=002">下一页</a>
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
												<a
													href="${pageContext.request.contextPath}/servlet/MsgServlet?option=end&idLoca=002">尾页</a>
											</c:otherwise>
										</c:choose> </span>
									<a href='javascript:void(0)'
										onclick="toConfirm('该操作将删除所有信息且无法恢复，确认清空?', '${pageContext.request.contextPath}/servlet/MsgServlet?option=flush&idLoca=002')">清空</a>
										&nbsp;&nbsp;共${requestScope.recordNums}条记录 
									<br>
									<br>
								</c:when>

								<c:otherwise>							
								<div class="clear"></div>
									<table width="100%" cellspacing="0" cellpadding="0" border="0">
										<tbody>
											<tr>
												<th width="25%"></th>
												<th width="50%"><br> <br>您的收信箱暂时没有信息!<br>
													<br> <br> <br> <br></th>
												<th width="25%"></th>
											</tr>
										</tbody>
									</table>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div></div>
		</div>
		<div class="clear"></div>
		<div>
			<jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
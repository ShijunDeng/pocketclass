<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的学习中心 - 我的课堂 - 掌上课堂</title>
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
					<div class="title_a">我的学习中心</div>
				</div>
				<div class="r_form">
					<div class="select">
						<div id=con>
							<span id=tab_tags class="selected"><a
								href="${pageContext.request.contextPath}/servlet/LearnServlet?isEnd=NO">正学习课程</a>
							</span> <span class="unselected"><a
								href="${pageContext.request.contextPath}/servlet/LearnServlet?isEnd=YES">已学完课程</a>
							</span>
						</div>
					</div>
					<div class="clear"></div>
					<div class="blue" style="width: 100%;font-family:'幼圆'">
						<div class="tb-hidden">
							<c:choose>
								<c:when
									test="${learnProgresses!=null&&requestScope.pageCount>0}">
									<table width="100%" cellspacing="0" cellpadding="0" border="1">
										<tbody>
											<tr>
												<th width="20%">课程系列名称</th>
												<th width="25%">所属分类</th>
												<th width="15%">总课时</th>
												<th width="10%">讲师</th>
												<th width="18%">开始学习时间</th>
												<th width="12%">操作</th>
											</tr>
										</tbody>

										<c:forEach items="${learnProgresses}" var="learnProgress">
											<tr class="align_center">
												<td style="padding:5px 0 5px 0;"><span> <a
														class="title"
														href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${learnProgress.idVideoSeries}"
														title="${learnProgress.topic}"> ${learnProgress.topic}</a>
												</span>
												</td>
												<td class="align_left">
													<div style="padding: 5px 0px 0px 5px;text-align: left;">
														<span style="float: left;"> <a
															href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${learnProgress.idMiddle}&type=primary">
																<img
																src='${pageContext.request.contextPath}/images/videos/${learnProgress.thumbnail}'
																title='${learnProgress.middleCateGory}'
																class='product_img_small' /><br>
																${learnProgress.middleCateGory}</a> </span>
													</div>
												<td>${learnProgress.size}</td>
												<td>${learnProgress.teacher}</td>
												<c:set var="startTime" value="${learnProgress.startTime}"></c:set>
												<td>${fn:replace(startTime," ","<br>") }</td>
												<td style="font-size:14px;font-family:幼圆"><a
													href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${learnProgress.idVideoSeries}">进入学习</a><br />
													<a href='javascript:void(0)'
													onclick="toConfirm('确认已经学完?', '${pageContext.request.contextPath}/servlet/LearnServlet?idVideoSeries=${learnProgress.idVideoSeries}&isEnd=NO&option=changeToLearned')">标记为学完</a><br>
													<a href="#">播放记录</a><br /></td>
											</tr>
										</c:forEach>
									</table>
									<br>
									<span class="op_form"> <c:choose>
											<c:when test="${requestScope.currentPage==1}">
												<c:out value="首页"></c:out>
											</c:when>
											<c:otherwise>
												<a
													href="${pageContext.request.contextPath}/servlet/LearnServlet?isEnd=NO?option=first&isEnd=NO">首页</a>
											</c:otherwise>
										</c:choose> <c:choose>
											<c:when test="${requestScope.currentPage > 1}">
												<a
													href="${pageContext.request.contextPath}/servlet/LearnServlet?option=prior&isEnd=NO">上一页</a>
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
														href="${pageContext.request.contextPath}/servlet/LearnServlet?option=jump&page=${i}&isEnd=NO">${i}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach> <c:choose>
											<c:when
												test="${requestScope.currentPage < requestScope.pageCount }">
												<a
													href="${pageContext.request.contextPath}/servlet/LearnServlet?option=next&isEnd=NO">下一页</a>
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
													href="${pageContext.request.contextPath}/servlet/LearnServlet?option=end&isEnd=NO">尾页</a>
											</c:otherwise>
										</c:choose> </span>
									<a href='javascript:void(0)'
										onclick="toConfirm('该操作无法恢复，确认清空?', '${pageContext.request.contextPath}/servlet/LearnServlet?option=flush&isEnd=NO')">清空</a>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共${requestScope.recordNums}条记录
								<br>
									<br>
								</c:when>
								<c:otherwise>
								<div class="clear"></div>
									<table width="100%" cellspacing="0" cellpadding="0" border="0">
										<tbody>
											<tr>
												<th width="15%"></th>
												<th width="70%"><br>
												<br>暂无正在学习课程，赶紧选择自己喜欢的课程开始学习吧!<br>
												<br>
												<br>
												<br>
												<br>
												</th>
												<th width="15%"></th>
											</tr>
										</tbody>
									</table>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
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
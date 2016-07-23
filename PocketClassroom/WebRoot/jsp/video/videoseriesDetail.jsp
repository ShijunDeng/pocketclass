<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>系列视频 - 掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/video/seriesdetail.css">
<script language="javascript"
	src="${pageContext.request.contextPath}/js/common/application.js"></script>
</head>
<body id="container" >
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
		<div id="detail_left">
			<div id="relative_categories">
				<div id="relative_title">相关分类</div>
				<div id="relative_super">
					<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${super.id}">${super.categoryName}</a>
				</div>
				<div id="relative_middle">
					<c:forEach items="${middlelist}" var="middle" varStatus="status">
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${middle.id}"><span style="width:100px;text-align:left;"><c:out
								value="${middle.middleCategoryName}"></c:out> </span></a>
					</c:forEach>
				</div>
			</div>
			<div id="retitive_videoseries">
				<div id="relative_title">相关课程</div>
				<c:forEach items="${serieslist}" var="series" varStatus="status">
					<div class="relative_item">
						<div>
							<a href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}"><img alt="${series.topic}" style="width:120px;height:120;"
								src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}"></a>
						</div>
						<div><a href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}">${series.topic}</a></div>
					</div>
				</c:forEach>

			</div>
		</div>
		<div id="series_right">
			<div id="thumbnail">
				<img style="width: 200px;height: 200px;"
					src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}">
			</div>
			<div class="detail_right">
				<div class="detail_title">${series.topic}</div>
				<div class="detail_intro">视频简介：${series.introduction}</div>
				<div class="detail_otherinfo">课时数:${series.size}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;主讲老师:${series.teacherId}</div>
				<c:if test="${!empty sessionScope.username}">
						<c:choose>
						<c:when test="${exist ==true}">
							<div class="addtoFav">
								<input type="image"
								src="${pageContext.request.contextPath}/images/videos/favourite1.jpg">
							</div>
						</c:when>
						<c:otherwise>
							<div class="addtoFav">
								<input type="image"
									src="${pageContext.request.contextPath}/images/videos/favourite.jpg"
									onclick="toConfirm('收藏之后，您可以更方便的找到自己喜欢的课程，确认收藏?', '${pageContext.request.contextPath}/servlet/AddFavoServlet?idVideoSeries=${series.id}&option=add')">
							</div>
						</c:otherwise>
					</c:choose>
				</c:if>
			</div>
			<fieldset>
				<legend>视频列表</legend>
				<c:choose>
					<c:when test="${!empty videolist}">
						<c:forEach items="${videolist}" var="video">
							<div class="item_video">
								<a href="${pageContext.request.contextPath}/servlet/VideoPlayServlet?seriesId=${series.id}&videoId=${video.id}">${video.position}&nbsp;&nbsp;${video.title}</a>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<span style="color:red;text-align:center;">该视频系列下暂时没有上传视频资源！</span>
					</c:otherwise>
				</c:choose>
			</fieldset>
			<fieldset>
				<legend>评论列表</legend>
				<c:choose>
					<c:when test="${!empty commentlist}">
						<c:forEach items="${commentlist}" var="comment">
							<div class="item_comment">
								<div>
									<span class="userspan" style="float:left;">${comment.username}</span><span
										class="timespan" style="float:right;">${comment.time}发表</span>
								</div>
								<div class="clear"></div>
								<div style="height:30px;text-align:justify;">${comment.content}</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<span style="color:red;text-align:center;">没有找到相关的评论！</span>
					</c:otherwise>
				</c:choose>
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
							action="${pageContext.request.contextPath}/servlet/AddCommentServlet?seriesId=${series.id}"
							method="post">
							<textarea id="comment" rows="10" style="overflow:hidden"
								name="content" cols="77" onmousedown="getFocus(event,this);"></textarea>
								<input type="hidden" name="token" value="${token}"> 
							<input type="hidden" name="seriesId" value="${series.id}" /> <input
								type="hidden" name="username" value="${sessionScope.username}" /> <input
								type="submit" value="马上发表" onclick="return checkComment()"><span
								id="txtspan"></span>
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程系列展示</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/video/seriesshow.css">
</head>

<body id="container">
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
		<div class="videolist">
			<jsp:include page="categoriesList.jsp"></jsp:include>
		</div>
		<div class="list_right" style="padding-bottom: 5px;">
			<div style="text-align:left; margin:10px;font-weight:bold;color: #fc5b03;">
				<c:choose>
					<c:when test="${!empty id}">
				首页&nbsp;&gt;&nbsp;<a style="text-decoration: none;color: #000000;"href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}">${categoryName }</a>
					</c:when>
					<c:otherwise>
					首页&nbsp;&gt;&nbsp;<a style="text-decoration: none;color: #000000;"href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}">【${keyword}】</a>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="yellow">
			<c:choose>
			<c:when test="${splitpage.currentPage==1}">
				首页 上一页
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty id}">
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=first">首页</a>
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=previous">上一页</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=first">首页</a>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=previous">上一页</a>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${!empty id}">
					<c:forEach begin="1" end="${splitpage.totalPages}" step="1" varStatus="status" >
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=${status.count}">${status.count}</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" end="${splitpage.totalPages}" step="1" varStatus="status" >
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${id}&option=${status.count}">${status.count}</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${splitpage.currentPage==splitpage.totalPages}">
				下一页 尾页
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty id}">
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=next">下一页</a>
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=last">尾页</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=next">下一页</a>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=last">尾页</a>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
			</div>
			<c:choose>
			<c:when test="${!empty serieslist}">
				<c:forEach items="${serieslist}" var="series">
					<div class="item">
						<div class="item_left">
							<a
								href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}">
								<img style="width:110px;height:110px;"
								src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}">
							</a>
						</div>
						<div class="item_right">
							<div class="item_title">
								<a 
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}">${series.topic}</a>
							</div>
							<div class="item_intro" >视频简介：${series.introduction}<div style="position:absolute; right:0; bottom:0; z-index:10; background-color:#FFFFFF;">...</div></div>
							<div class="item_otherinfo">课时数:${series.size}
								主讲老师:${series.teacherId}</div>
						</div>
					</div>
					<hr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				没有找到相关的视频系列！
			</c:otherwise>
			</c:choose>
			<div>
			<c:choose>
			<c:when test="${splitpage.currentPage==1}">
				首页 上一页
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty id}">
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=first">首页</a>
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=previous">上一页</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=first">首页</a>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=previous">上一页</a>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${!empty id}">
					<c:forEach begin="1" end="${splitpage.totalPages}" step="1" varStatus="status" >
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=${status.count}">${status.count}</a>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach begin="1" end="${splitpage.totalPages}" step="1" varStatus="status" >
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${id}&option=${status.count}">${status.count}</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
			<c:when test="${splitpage.currentPage==splitpage.totalPages}">
				下一页 尾页
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${!empty id}">
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=next">下一页</a>
						<a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=${id}&option=last">尾页</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=next">下一页</a>
						<a href="${pageContext.request.contextPath}/servlet/SearchVideoServlet?keyword=${keyword}&option=last">尾页</a>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>播放视频-掌上课堂</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
#d1 {
	border: border:#00CCFF 1px solid;
	width: 960px;
	height: 550px;
}
#webplayer
{
	margin-top:20px;
	float:left;
	width:620px;
	height:500px;
	border:#3399FF 1px solid;
}
#right
{
	margin-left:10px;
	float:left;
	border:#3399FF 1px solid;
	width:300px;
	height:500px;
}
#videolist
{
	border:#3399FF 1px solid;
	height:137px;
	text-align:left;
}
#videolist_top,#note_top
{
	height:25px;
	border:#3399FF 1px solid;
}
.list_item
{
	height:20px;
	border:#3399FF 1px solid;
	text-align:left;
	font-size:14px;
	color:red;
}
.list_item a{
	text-decoration: none;
	width:293px;
	color: red;
}
.list_item a:hover{
	color: blue;
}
.cur{
	background-color:#79B900;
	color: blue;
}
#pageindex
{
	height:20px;
	border:#3399FF 1px solid;
	text-align:right;
}
#note
{	
	text-align:left;
	margin-top:30px;
	border:#3399FF 1px solid;
	height:250px;
}
.clear{
	clear:both;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/soft/flowplayer/flowplayer-3.2.11.min.js"></script>
</head>
<body id="container">
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
	<div id="webplayer">
		<a  
			href="http://pocketclass.oicp.net/videos/${video.linkAddress}"
			 style="display:block;width:620px;height:500px;"  
			 id="player"> 
		</a> 
	
		<script>
			flowplayer("player", "${pageContext.request.contextPath}/soft/flowplayer/flowplayer-3.2.15.swf");
		</script>
	</div>
	<div id="right">
		<div id="videolist">
			<div id="videolist_top">可观看列表</div>
			<c:set var="pageSize" value="5"></c:set>
			
			<c:set var="rowCount" value="${fn:length(videolist)}"></c:set>
			<c:choose>
				<c:when test="${rowCount%pageSize==0}">
					<c:set var="pageCount" value="${rowCount/pageSize}"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="pageCount" value="${rowCount/pageSize+1}"></c:set>
				</c:otherwise>
			</c:choose>
			
			<c:forEach begin="1" step="1" end="${pageCount}" varStatus="Topstatus">
				<c:set var="pageNum" value="${Topstatus.count}" scope="page"></c:set>
				
			<div id="page_${Topstatus.count}" style="display: none;" >
			<c:forEach begin="1" step="1" end="${pageSize}" varStatus="status">
				<c:set var="curIndex" value="${(pageNum-1)*pageSize+status.count}"></c:set>
				<c:choose>
					<c:when test="${curIndex==video.position}">
					<%--start：根据视频的在视频系列中的位置决定显示视频列表的哪一页 --%>
					<c:set var="curPage" value="${Topstatus.count}"></c:set>
					<%--end：根据视频的在视频系列中的位置决定显示视频列表的哪一页 --%>
				<div class="list_item"><a class="cur" href="#">&nbsp;&nbsp;&nbsp;${videolist[curIndex-1].position}&nbsp;${videolist[curIndex-1].title}</a></div>
					</c:when>
					<c:otherwise>
				<div class="list_item">
					<a href="${pageContext.request.contextPath}/servlet/VideoPlayServlet?seriesId=${seriesId}&videoId=${videolist[curIndex-1].id}">&nbsp;&nbsp;&nbsp;${videolist[curIndex-1].position}&nbsp;${videolist[curIndex-1].title}</a>
				</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</div>
			
			</c:forEach>
			
			<script type="text/javascript">
				//根据视频的在视频系列中的位置决定显示视频列表的哪一页
				var curPage = document.getElementById("page_${curPage}");
				curPage.style.display="block";
				
				function displayPage(pageNum,pageCount)
				{
					for(var i=1;i<=pageCount;i++)
					{
						var obj = document.getElementById("page_"+i);
						obj.style.display="none";
					}
					
					var curPage = document.getElementById("page_"+pageNum);
					curPage.style.display="block";
				}
			</script>
			
			
			<div id="pageindex">
			  	<c:forEach begin="1" step="1" end="${pageCount}" varStatus="status">
				<a href="javascript:void(0);" onclick="displayPage(${status.count},${pageCount})"><c:out value="${status.count}"></c:out></a>
				</c:forEach>
			</div>
		</div>
		<div id="note">
			<div id="note_top">记录笔记</div>
			<div id="note_main">
				<form action="">
					<div id="note_title">标题:<input type="text" style="height:20px;" value="${video.title}"></div>
					<div id="note_content">内容:<br/><textarea style="width:290px;height:200px;"cols="100" rows="100"></textarea></div>
					<div style="text-align:center;"><input type="submit" value="发表笔记" ></div>
				</form>
			</div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

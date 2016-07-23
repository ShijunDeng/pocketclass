<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>课程下载页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
div table { /*	border: #00FFFF 1px solid;*/
	height: 150px;
	width: 960px;
}

div table td {
	height: 135px;
	width: 160px;
/*	border: #00FFFF 1px solid;*/
	text-align: center;
}

fieldset {
	width: 960px;
	text-align: left;
	margin-bottom: 10px;
}

legend {
	color: #0099FF;
	font-weight: bold;
	font-size: 16px;
}

img {
	width: 120px;
	height: 100px;
	border-width: 0px;
	border-style: none;
}

span {
	font-size: 16px;
	color: #F9BC28;
}
</style>
<script type="text/javascript">
	
</script>
</head>

<body id="container">
	<jsp:include page="../head.jsp"></jsp:include>
	<fieldset>
		<legend>客户端下载</legend>
	</fieldset>
	<c:forEach items="${all}" var="list">
		<fieldset>
			<legend>${list.key}视频下载</legend>
			<div>
				<table width="960" border="0">
					<c:forEach items="${list.value}" var="series" varStatus="status">
						<c:if test="${status.index%5==0}">
							<tr>
						</c:if>
						<td>
							<div><a href="${pageContext.request.contextPath}/servlet/VideoDownLoadServlet?option=download&id=${series.id}" style="text-decoration:none"><img
								src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}" /></a>
							</div>
								<span><a href="${pageContext.request.contextPath}/servlet/VideoDownLoadServlet?option=download&id=${series.id}" style="text-decoration:none">${series.topic}</a></span> 
						</td>
					</c:forEach>
				</table>
			</div>
		</fieldset>
	</c:forEach>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

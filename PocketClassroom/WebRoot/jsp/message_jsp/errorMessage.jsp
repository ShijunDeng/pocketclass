<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 操作有问题或者异常的提示jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>   
<title>温馨提示-掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/errorMessage.css"
	rel="stylesheet" type="text/css" media="all">
  </head>
  
 
<body>
	<div class="wrapper">
		<div class="head" style="text-align:center;"><jsp:include
				page="../head.jsp"></jsp:include></div>
		<div class="blank_a"></div>
		<div class="content">
			<div class="blueLogo_a">
				<div class="title_a">错误提示</div>
			</div>
			<div class="errorMessage" style="font-size: 14px; line-height: 20px;">
				 				<img
					src="${pageContext.request.contextPath}/images/register_logo/message_error.gif" /> ${message} <br>
			</div>
		</div>
		<div class="tail"><jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>

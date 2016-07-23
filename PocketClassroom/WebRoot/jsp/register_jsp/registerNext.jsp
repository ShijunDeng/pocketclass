<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 注册中的下一步 -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户注册(下一步)-掌上课堂</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/register_css/registerNext.css"
	rel="stylesheet" type="text/css" media="all">

</head>


<body>
	<div class="wrapper">
		<div class="head" style="text-align:center;"><jsp:include
				page="../head.jsp"></jsp:include></div>
		<div class="blank_a"></div>
		<div class="content">
			<div class="logo_css01"></div>
			<div class="blueLogo_a">
				<div class="title_a">下一步</div>
			</div>
			<div class="nextMessage" style="font-size: 14px; line-height: 20px;">
				您的信息已经成功提交，激活链接已发送到您的邮箱 <br> 登录注册邮箱，按照邮件内容提示，激活您的帐户即可完成注册。
			</div>
		</div>
		<div class="tail"><jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>

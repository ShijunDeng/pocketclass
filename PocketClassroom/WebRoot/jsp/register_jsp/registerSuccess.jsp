<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>注册成功-掌上课堂</title>
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/register_css/registerSuccess.css"
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
				<div class="title_a">激活并注册成功</div>
			</div>
			<div class="successMessage"
				style="font-size: 14px; line-height: 20px;">
				恭喜您，注册成功，祝您学习愉快！<br /> 完善个人资料，方便我们更好的为您提供服务！<a
					style="font-size: 14px;text-decoration:underline;"
					href="${pageContext.request.contextPath}/jsp/login/login.jsp">前往完善个人资料！</a> <br /> <br /> <a
					href="${pageContext.request.contextPath}/jsp/login/login.jsp"
					style="font-size: 14px;">立即登录</a> <br />
			</div>
		</div>
		<div class="tail"><jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>

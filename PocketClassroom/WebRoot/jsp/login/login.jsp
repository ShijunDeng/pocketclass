<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>登陆-掌上课堂</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login/login.css">

<script type="text/javascript">
function checkForm(formNode)
{
	with(formNode)
	{	
		var divNode = document.getElementById("errormessage");
		var userstr = username.value;
		var regex = /^\s+$/g;
		if(userstr.length==0 || regex.test(userstr))
		{
			divNode.innerHTML = "请输入用户名";
			return  false;
		}
		var pswstr = password.value;
		if(pswstr.length==0 || regex.test(pswstr))
		{
			divNode.innerHTML = "请输入密码";
			return false;
		}
	}	
}
function rememberPsw()
{
	var rememNode = document.getElementsByName("rememberme")[0];
	rememNode.value =(rememNode.value == 'yes'?'no':'yes');
}
</script>

</head>

<body id="container">
	 <jsp:include page="../head.jsp"></jsp:include>
	<div id="mainframe">	
		<div id="top_bar"><div style="padding:10px;">用户登录</div></div>
		<div id="bodydiv">
			<div id="formdiv">
				<form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post" onsubmit="return checkForm(this)">
				<div id="errormessage">${error}</div>				
				<div id="userdiv">用户名：<input type="text" id="username" name="username" value="${username}"/></div><br /> 
				<div id="pswdiv">密&nbsp;&nbsp;码：<input type="password" id="password" name="password" value=""/></div><br />
				&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="checkbox" id="remember_me"  onclick="rememberPsw()"/><input type="hidden" name="rememberme" value="no"> 记住密码一周<a href="${pageContext.request.contextPath}/jsp/password/forgetPassword.jsp">忘记密码</a><br />
				<input type="image" src="${pageContext.request.contextPath}/images/login/login.jpg"
					style="margin: 10px 0px 0 20px;" class="button" name="commit" />
				</form>
			</div>
			<div id="intro">网站介绍</div>
		</div>
	</div>
	<jsp:include page="../tail.jsp"></jsp:include> 
</body>
</html>

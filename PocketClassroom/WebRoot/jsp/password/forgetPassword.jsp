<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 操作正常的提示jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>忘记密码-掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/password/password.css"
	rel="stylesheet" type="text/css" media="all">
	<script type="text/javascript">
	function checkForm(formNode){
	with(formNode)
	{	
		var divNode = document.getElementById("errormessage");
		var userstr = username.value;
		var regexusername =/^[a-zA-Z]{1}([a-zA-Z0-9]){4,24}$/;
		var regexnull = /^\s+$/g;
		if(userstr.length==0||regexnull.test(userstr))
		{
			divNode.innerHTML = "请输入用户名";
			return  false;
		}
		if( regexusername.test(userstr)==false)
		{
			divNode.innerHTML = "请输入正确的用户名";
			return  false;
		}
		var emailstr = email.value;
		if(emailstr.length==0||regexnull.test(emailstr))
		{
			divNode.innerHTML = "请输入邮箱地址";
			return false;
		}
		var regexemail= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if( regexemail.test(emailstr)==false)
		{
			divNode.innerHTML = "请输入有效的邮箱地址";
			return false;
		}
	}	//with
	divNode.innerHTML = "";
	return true;
	}
	</script>
</head>


<body>
	<div class="wrapper">
		<div class="head" style="text-align:center;"><jsp:include
				page="../head.jsp"></jsp:include></div>
		<div class="blank_a"></div>
		<div class="content">
			<div class="blueLogo_a">
				<div class="title_a">忘记密码</div>
			</div>
			<div class="r_form">
				<form id="registerForm"
					action="${pageContext.request.contextPath}/servlet/PasswordEmailServlet"
					method="post" name="register" onsubmit="return checkForm(this)">
					<div class="line1">
						<div id="errormessage">${error}</div>
					</div>
					<div class="line2">
						<span class="username_label">用户名:</span> <span
							class="username_text"> <input type="text" name="username"
							style="width:180px; height:25px;" value='${form.username}'>
						</span>
					</div>
					<div class="line3">
						<span class="email_label">邮&nbsp;&nbsp;箱:</span><span
							class="email_text"> <input type="text" name="email"
							style="width:180px; height:25px;" value='${form.email}'>
						</span>
					</div>

					<div class="line4"><input
						type="submit" value="确认" class="doSubmit" /> </div>
				</form>
			</div>
		</div>
		<div class="tail"><jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>

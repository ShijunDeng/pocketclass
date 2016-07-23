<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 操作正常的提示jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>重置密码-掌上课堂</title>
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
		var _password = password.value;
		var _confPassword=confPassword.value;
		var regexPassword=/^[a-zA-Z0-9]{6,16}$/;
		//var regexnull = /^\s+$/;
		if(_password=="")
		{
			divNode.innerHTML = "请输入新密码";
			return  false;
		}
		if(_confPassword=="")
		{
			divNode.innerHTML = "请输入确认密码";
			return  false;
		}
		//document.write(password.length);
		if(regexPassword.test(_password)==false){
			divNode.innerHTML = "密码格式不正确";
			return  false;
		}
		if(regexPassword.test(_confPassword)==false){
			divNode.innerHTML = "确认密码格式不正确";
			return  false;
		}
		if(_password!=_confPassword)
		{
			divNode.innerHTML = "两次输入密码不一致";
			return  false;
		}	
	}
		divNode.innerHTML = "";
			return  true;	
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
				<div class="title_a">重置密码</div>
			</div>
			<div class="r_form">
				<form id="registerForm"
					action="${pageContext.request.contextPath}/servlet/ResetPasswordServlet?username=${pageContext.request.parameterMap['username'][0]}"
					method="post" name="register" onsubmit="return checkForm(this)">
					<div class="line1">
						<div id="errormessage">${error}</div>
					</div>
					<div class="line2">
						<span class="password_label">新密码:</span> <span
							class="password_text"> <input type="password"
							name="password" style="width:180px; height:25px;"
							value='${form.password}'> </span>
					</div>
					<div class="line3">
						<span class="confPassword_label">确认密码:</span><span
							class="confPassword_text"> <input type="password"
							name="confPassword" style="width:180px; height:25px;"
							value='${form.confPassword}'> </span>
					</div>

					<div class="line4">
						<input type="submit" value="确认修改" class="doSubmit" />
					</div>
				</form>
			</div>
		</div>
		<div class="tail"><jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>

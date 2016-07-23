<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>用户注册 - 掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/register_css/register.css"
	rel="stylesheet" type="text/css" media="all">
<script language="javascript"
	src="${pageContext.request.contextPath}/js/common/application.js"></script>
<script type="text/javascript">
	var xmlHttp;
	var existInfo=false;//只有邮箱和用户名都不重复的情况下该标识为true，否则为false，默认为false（若为true，首次检测时，服务器响应时间太长的时候提交会通过）
	function createXMLHttpRequest(){
		if(window.ActiveXObject){//是IE浏览器
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){//非IE浏览器
			xmlHttp=new XMLHttpRequest();
		}
	}
	//从服务器返回验证结果
	function usernameProcessor(){
		var spanNode= document.getElementById("userspan1");
		if(xmlHttp.readyState==4){//如果响应完成
				if(xmlHttp.status==200){//如果返回成功
				if(xmlHttp.responseText.toString()=="exist"){
					spanNode.style.display = "inline";
       				spanNode.innerHTML="<font color=red>该用户名已被注册</font>";
       				existInfo=false;
   				 }else{
   				 	spanNode.style.display = "inline";
        			spanNode.innerHTML="<font color=green>可以注册</font>";
        		 	existInfo=true;
    			}
			}
		}
	}
		//从服务器返回验证结果
	function emailProcessor(){
		var spanNode= document.getElementById("mailspan1");
		if(xmlHttp.readyState==4){//如果响应完成
			if(xmlHttp.status==200){//如果返回成功
				if(xmlHttp.responseText.toString()=="exist"){
					spanNode.style.display = "inline";
       				spanNode.innerHTML="<font color=red>该邮箱已被注册</font>";
       				existInfo=false;
   				 }else{
   				 	spanNode.style.display = "inline";
        			spanNode.innerHTML="<font color=green>可以注册</font>";
        			existInfo=true;
    			}
			}
		}
	}
	//通过一个AJAX异步验证
	function usernameCheckFromDB(data){
		 createXMLHttpRequest();
		 //将状态触发器绑定到一个函数
		 xmlHttp.onreadystatechange=usernameProcessor;		 
		 xmlHttp.open("GET", "${pageContext.request.contextPath}/servlet/ConflictDataCheckServlet?data="+data);
		 //向服务器发送请求
		 xmlHttp.send(null);
	}
	//通过一个AJAX异步验证
	function emailCheckFromDB(data){
		 createXMLHttpRequest();
		 //将状态触发器绑定到一个函数
		 xmlHttp.onreadystatechange=emailProcessor;
		 xmlHttp.open("GET", "${pageContext.request.contextPath}/servlet/ConflictDataCheckServlet?data="+data);
		 //向服务器发送请求
		 xmlHttp.send(null);
	}
	function inputColor(inputNode) {
		inputNode.className = "norm";
		inputNode.onfocus = function() {
			inputNode.className = "focus";
		};
	}
	window.onload = function() {
		with (document.forms[0]) {
			inputColor(username);
			inputColor(password);
			inputColor(confPassword);
			inputColor(email);
			inputColor(vercode);
		}
	};
	
	
	//校验方法
	function checkMethod(inputNode, regex, spanId) {
		var b = false;
		var value = inputNode.value;
		var spanNode = document.getElementById(spanId);
		if (regex.test(value)) {//验证正确
			inputNode.className = "norm";
			spanNode.style.display = "none";
			b = true;
		} else {//验证错误
			inputNode.className = "error";
			spanNode.style.display = "inline";
			spanNode.style.color="red";
		}
		return b;
	}

	//校验用户名
	function checkUser(userNode) {
		var b = false;
		var regex = new RegExp("^[a-z]\\w{4,24}$", "i");
		//1.首先要格式正确	
		b=checkMethod(userNode, regex, "userspan1");
		//2.判断用户名是否已经注册	
		if(b==true)
			usernameCheckFromDB(userNode.value);			
		return b;
	}

	//校验密码(格式是否正确)
	function checkPassword(passwordNode) {

		var regex = new RegExp("^[a-z0-9]{6,16}$", "i");
		return checkMethod(passwordNode, regex, "passwordspan");
	}
	//校验确认密码
	function checkConfPassword(confPswNode) {
		var b = false;
		var value1 = confPswNode.value;
		var spanNode = document.getElementById("confPasswordspan");

		var value2 = document.getElementsByName("password")[0].value;
		if (value1 == value2) {
			confPswNode.className = "norm";
			spanNode.style.display = "none";
			b = true;
		} else {
			confPswNode.className = "error";
			spanNode.style.display = "inline";
			spanNode.style.color="red";
		}
		return b;

	}
	//验证邮箱
	function checkMail(mailNode)
	{
		var  regex = new RegExp("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		var b=false;
 		//1.验证邮箱格式是否合法
 		b=checkMethod(mailNode,regex,"mailspan1");
 		//2.判断邮箱是有已经绑定
 		if(b==true)
 			emailCheckFromDB(mailNode.value);
 		return b;	
	}
	//判断验证码是否为空
	function checkVerCode(vercodeNode)
	{
		var spanNode = document.getElementById("vercodespan");
		var vercode = vercodeNode.value;
		var regex = /^\s+$/g;
		if(vercode.length==0 || regex.test(vercode))
		{
			spanNode.innerHTML = "请输入验证码".fontcolor("red");
			vercodeNode.className = "error";
			return  false;
		}
		else
		{
			spanNode.innerHTML ="";
			vercodeNode.className = "norm";
			return true;
		}
	}
	
	function checkForm(formNode)
	{
		with(formNode)
		{
			if(checkUser(username) && checkPassword(password) && checkConfPassword(confPassword) && checkMail(email) && checkVerCode(vercode)&&existInfo)
			{
				if(agreement.checked)//同意用户协议
					return true;
			}
		}
		return false;
	}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="head" style="text-align:center;"><jsp:include
				page="../head.jsp"></jsp:include></div>
		<div class="blank_a"></div>
		<div class="content">
			<div class="logo_css01"></div>
			<div class="blueLogo_a">
				<div class="title_a">用户注册</div>
			</div>
			<div class="r_form">
				<form id="registerForm"
					action="${pageContext.request.contextPath}/servlet/RegisterEmailServlet"
					method="post" name="register" onsubmit="return checkForm(this)">
					<div class="line1">
						<span class="username_label">用户名:</span> <span
							class="username_text"> <input type="text" name="username"
							style="width:180px; height:25px;" value='${form.username}'
							onblur="checkUser(this)"> </span> <span class="usernameTip"
							id="userspan1"> <c:choose>
								<c:when test="${form!=null}">
									<c:choose>
										<c:when test="${form.tips.username!=null}">
											<font color="red">${form.tips.username}</font>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>用户名应为5-25位字母、数字或下划线组合，首字符必须为字母</c:otherwise>
							</c:choose> </span>
					</div>
					<div class="line2">
						<span class="password_label">密&nbsp;&nbsp;码:</span> <span
							class="password_text"><input type="password"
							name="password" style="width:180px; height:25px;"
							value='${form.password}' onblur="checkPassword(this)"> </span> <span
							class="passwordTip" id="passwordspan"> <c:choose>
								<c:when test="${form!=null}">
									<c:choose>
										<c:when test="${form.tips.password!=null}">
											<font color="red">${form.tips.password}</font>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>密码应为6-16位字母或者数字</c:otherwise>
							</c:choose> </span>
					</div>
					<div class="line3">
						<span class="confPassword_label">确认密码:</span> <span
							class="confPassword_text"><input type="password"
							name="confPassword" style="width:180px; height:25px;"
							value='${form.confPassword}' onblur="checkConfPassword(this)">
						</span> <span class="confPasswordTip" id="confPasswordspan"> <c:choose>
								<c:when test="${form!=null}">
									<c:choose>
										<c:when test="${form.tips.confPassword!=null}">
											<font color="red">${form.tips.confPassword}</font>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>两次密码不一致</c:otherwise>
							</c:choose> </span>
					</div>
					<div class="line4">
						<span class="email_label">邮&nbsp;&nbsp;箱:</span><span
							class="email_text"> <input type="text" name="email"
							style="width:180px; height:25px;" value='${form.email}'
							onblur="checkMail(this)"> </span> <span class="emailTip"
							id="mailspan1"> <c:choose>
								<c:when test="${form!=null}">
									<c:choose>
										<c:when test="${form.tips.email!=null}">
											<font color="red">${form.tips.email}</font>
										</c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>请填写正确的邮箱格式，如abc@163.com，用于找回密码</c:otherwise>
							</c:choose> </span>
					</div>
					<div class="line5">
						<span class="vercode_label">验证码:</span> <span class="vercode_text"><input
							type="text" name="vercode" style="width:90px; height:25px;"
							onblur="checkVerCode(this)"> <img
							src="${pageContext.request.contextPath}/servlet/ImageServlet"
							alt="换一张" onclick="this.src=this.src+'?'+new Date().getTime()"
							style="cursor:hand" /> </span> <span class="vercodeTip"
							id="vercodespan"> <c:choose>
								<c:when test="${form.tips.vercode!=null}">
									<font color="red">${form.tips.vercode}</font>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose> </span>
					</div>
					<div class="line6">
						<div class="agreement">
							<span class="agreement"><input type="checkbox"
								name="agreement" checked="checked">我已经阅读并同意<a
								href="${pageContext.request.contextPath}/jsp/help/agreement.jsp"
								target="_blank">用户协议</a><br /> </span>
						</div>
					</div>
					<span class="line7"> <span class="blank_b">&nbsp;</span> <input
						type="submit" value="立即注册" align="right" class="doSubmit" /> </span>
				</form>
			</div>
		</div>
		<div class="tail"><jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>

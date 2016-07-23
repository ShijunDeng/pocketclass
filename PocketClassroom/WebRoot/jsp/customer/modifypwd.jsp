<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改密码-掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link href="${pageContext.request.contextPath}/css/customer/modify.css"
	rel="stylesheet" type="text/css" media="all">
<style type="text/css">
.line1 {
	align:center;
	padding-top: 0px;
}

.line2,.line3 {
	padding-top: 20px;
	align:center;
}

.line4 {
	margin-top: 20px;
	align:center;
}
</style>
</head>

<script type="text/javascript">
	var xmlHttp;
	var existInfo=false;
	function createXMLHttpRequest(){
		if(window.ActiveXObject){//是IE浏览器
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){//非IE浏览器
			xmlHttp=new XMLHttpRequest();
		}
	}
	//从服务器返回验证结果
	function passwordProcessor(){
	var spanNode= document.getElementById("oldpwdpan");
		if(xmlHttp.readyState==4){//如果响应完成
				if(xmlHttp.status==200){//如果返回成功
				if(xmlHttp.responseText.toString()=="false"){
					spanNode.style.display = "inline";
       				spanNode.innerHTML="<font color=red>密码不正确</font>";
       				existInfo=false;
   				 }else{
   				 	spanNode.style.display = "inline";
        			spanNode.innerHTML="<font color=green>密码正确</font>";
        		 	existInfo=true;
    			}
			}
		}
	}
	//通过一个AJAX异步验证
	function passwordCheckFromDB(data){
		 createXMLHttpRequest();
		 //将状态触发器绑定到一个函数
		 xmlHttp.onreadystatechange=passwordProcessor;
		 xmlHttp.open("GET", "${pageContext.request.contextPath}/servlet/ModifyPasswordServlet?password="+data+"&operation=query");
		 //向服务器发送请求
		 xmlHttp.send(null);
	}
	function trim(sText)//返回去掉两边空格的sText
	{ 
		return sText.replace(new RegExp("(^[\\s]*)|([\\s]*$)", "g"), ""); 
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
	//校验密码(格式是否正确)
	function checkPassword(passwordNode,type) {
		var regex = new RegExp("^[a-z0-9]{6,16}$", "i");
		var b= checkMethod(passwordNode, regex, type);
		//如果是原密码且格式正确，就连接数据库检测
		if(b&&type=="oldpwdpan"){
			passwordCheckFromDB(passwordNode.value);
		}
		return b;
	}
	//校验确认密码
	function checkConfPassword(confPswNode) {
		var b = checkPassword(confPswNode,"confpwdpan");
		if(b==true){
			var value1 = confPswNode.value;
			var spanNode = document.getElementById("confpwdpan");
			var value2 = document.getElementById("newpwd").value;
			if (value1 == value2) {
				confPswNode.className = "norm";
				spanNode.style.display = "none";
				b = true;
			} else {
				confPswNode.className = "error";
				spanNode.style.display = "inline";
				spanNode.innerHTML="两次密码输入不一致";
				spanNode.style.color="red";
				b=false;
		}
		}
		return b;

	}
	function checkForm(formNode)
	{
		with(formNode)
		{
			if(checkPassword(oldpwdname,"oldpwdpan") && checkPassword(password,"newpwdpan") && checkConfPassword(confpwdname)&&existInfo)
			{
				return true;
			}
		}
		return false;
	}
</script>
<body>
	<div align="center">
		<div align="center">
			<jsp:include page="../head.jsp"></jsp:include>
		</div>
		<div class="main">
			<div class="left">
				<jsp:include page="left.jsp"></jsp:include></div>
			<div class="right">
				<div class="blueLogo_a">
					<div class="title_a">修改密码</div>
				</div>
				<div class="r_form">
					<form id="registerForm"
						action="${pageContext.request.contextPath}/servlet/ModifyPasswordServlet?operation=modify"
						method="post" name="modifypwd" onsubmit="return checkForm(this)">
						<div class="line1">
							<span class="oldpwd_label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原密码:</span> <span class="oldpwd_text">
								<input type="password" id="oldpwd"
								style="width:180px; height:25px;" name="oldpwdname"
								value='${form.oldpwd}' onblur="checkPassword(this,'oldpwdpan')">
							</span> <span class="oldpwd_tip" id="oldpwdpan"> <c:choose>
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
						<div class="line2">
							<span class="newpwd_label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新密码:</span> <span class="newpwd_text">
								<input type="password" name="password" id="newpwd"
								style="width:180px; height:25px;" value='${form.newpwd}'
								onblur="checkPassword(this,'newpwdpan')"> </span> <span
								class="newpwd_tip" id="newpwdpan"> <c:choose>
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
							<span class="confpwd_label">确认密码:</span><span
								class="confpwd_text"> <input type="password" id="confpwd"
								style="width:180px; height:25px;" name="confpwdname"
								value='${form.confpwd}' onblur="checkConfPassword(this)">
							</span> <span class="confpwd_tip" id="confpwdpan"> <c:choose>
									<c:when test="${form!=null}">
										<c:choose>
											<c:when test="${form.tips.password!=null}">
												<font color="red">${form.tips.password}</font>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>请再次输入密码</c:otherwise>
								</c:choose> </span>
						</div>

						<div class="line4">
							<input type="submit" value="确认修改" class="doSubmit" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<div>
			<jsp:include page="../tail.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
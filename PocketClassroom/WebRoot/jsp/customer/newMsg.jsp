<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>我的消息 - 我的课堂 - 掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/customer/favorites.css"
	rel="stylesheet" type="text/css" media="all">
<script language="javascript"
	src="${pageContext.request.contextPath}/js/common/application.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/soft/xheditor-1.1.14/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/soft/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js"></script>

<script type="text/javascript">
	var xmlHttp;
	var existInfo = false;//检测收信人是否存在(存在为true不存在为false)：默认不存在
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {//是IE浏览器
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {//非IE浏览器
			xmlHttp = new XMLHttpRequest();
		}
	}
	//从服务器返回验证结果
	function usernameProcessor() {
		var spanNode = document.getElementById("addreUserNameTip");
		if (xmlHttp.readyState == 4) {//如果响应完成
			if (xmlHttp.status == 200) {//如果返回成功
				if (xmlHttp.responseText.toString() == "exist") {
					spanNode.style.display = "inline";
					spanNode.innerHTML = "<font color=green>可以发送</font>";
					existInfo = true;
				} else {
					spanNode.style.display = "inline";
					spanNode.innerHTML = "<font color=red>用户不存在</font>";
					existInfo = false;
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
			spanNode.style.color = "red";
		}
		return b;
	}

	//校验用户名
	function checkUser(userNode) {
		var b = false;
		var regex = new RegExp("^[a-z]\\w{4,24}$", "i");
		//1.首先要格式正确	
		b = checkMethod(userNode, regex, "addreUserNameTip");
		//2.判断用户名是否已经注册	
		if (b == true)
			usernameCheckFromDB(userNode.value);
		return b;
	}//校验消息标题
	function checkTitle(userNode) {
		var regex = new RegExp("^.{0,50}$", "i");
		return checkMethod(userNode, regex, "titleTip");
	}
	function checkForm(formNode) {
		with (formNode) {
			return checkUser(addreUserName)&&checkTitle(title)&& existInfo&&checkContent(content);
		}
	}
	
	function checkContent(content){
		return content.value.length<2001;
	}
</script>
</head>

<body>
	<div align="center">
		<div align="center">
			<jsp:include page="../head.jsp"></jsp:include>
		</div>
		<div class="main">
			<div class="left">
				<jsp:include page="left.jsp"></jsp:include>
			</div>
			<div class="right">
				<div class="blueLogo_a">
					<div class="title_a">
						<a style="text-decoration:none ;color:#fff"
							href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=001">我的消息</a>
						〉发送消息
					</div>
				</div>
				<div class="r_form" style="height:400px ">
					<form id="msgForm" action="${pageContext.request.contextPath}/servlet/MsgContentServlet?option=newMsg" method="post"
						onsubmit="return checkForm(this)">
						<div class="blue" style="width: 100%;" style="font-family:'幼圆'">
							<div class="tb-hidden">
								<div>
									<div>
										<div>
											<span class="label">用户名<sup class="must">*</sup>：</span><input
												type="text" name="addreUserName" onblur="checkUser(this)" value="${sendUserNameDef}"><span
												id="addreUserNameTip" class="tip">请输入有效的用户名（即登录名）</span>
										</div>
										<div>
											<span class="label">标题<sup class="must">*</sup>：</span><input
												type="text" name="title" onblur="checkTitle(this)" value="${titleDef}"><span
												id="titleTip" class="tip">0-50个字符</span>
										</div>
										<div class="form_item">
											<span class="label">内容<sup class="must">*</sup>：</span>
											<div style="padding: 0 0 0 10px;" class="text">
											<textarea id="content" name="content" class="xheditor" style="width:600px;height:200px" onblur="checkContent(this)">
                                			${contentDef}
											</textarea>
											</div>
											<span class="tip" style="padding: 0 0 0 100px;">注意：内容最长2000字符</span>
										</div>
									</div>
									<div class="form_item">
										<span class="label">&nbsp;</span>
										<div class="sendBtn">
											<input id="doSubmit" type="submit" class="btn-img btn-s1"
												value="发送" />
										</div>
									</div>
								</div>
							</div>
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
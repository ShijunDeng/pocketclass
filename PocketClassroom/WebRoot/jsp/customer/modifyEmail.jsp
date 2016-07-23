<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改邮箱-掌上课堂</title>
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
	padding-top: 0px;
	align:center;
}

.line2 {
	padding-top: 20px;
	align:center;
}

.line3 {
	margin-top: 20px;
	align:center;
}
</style>
<script type="text/javascript">
	var xmlHttp;
	var existInfo_new=false;
	var existInfo_old=false;
	function createXMLHttpRequest(){
		if(window.ActiveXObject){//是IE浏览器
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){//非IE浏览器
			xmlHttp=new XMLHttpRequest();
		}
	}
	
		//从服务器返回验证结果
	function newemailProcessor(){
		var spanNode= document.getElementById("newemailpan");
		if(xmlHttp.readyState==4){//如果响应完成
			if(xmlHttp.status==200){//如果返回成功
				if(xmlHttp.responseText.toString()=="true"){
					spanNode.style.display = "inline";
       				spanNode.innerHTML="<font color=red>该邮箱已被注册</font>";
       				existInfo_new=true;
   				 }else{
   				 	spanNode.style.display = "inline";
        			spanNode.innerHTML="<font color=green>邮箱可用</font>";
        			existInfo_new=false;
    			}
			}
		}
	}
	
	//通过一个AJAX异步验证
	function newemailCheckFromDB(data){
		 createXMLHttpRequest();
		 //将状态触发器绑定到一个函数
		 xmlHttp.onreadystatechange=newemailProcessor;
		 xmlHttp.open("GET", "${pageContext.request.contextPath}/servlet/ModifyEmailServlet?email="+data+"&operation=querynew");
		 //向服务器发送请求
		 xmlHttp.send(null);
	}
	
		//从服务器返回验证结果
	function oldemailProcessor(){
		var spanNode= document.getElementById("oldemailpan");
		if(xmlHttp.readyState==4){//如果响应完成
			if(xmlHttp.status==200){//如果返回成功
				if(xmlHttp.responseText.toString()=="false"){
					spanNode.style.display = "inline";
       				spanNode.innerHTML="<font color=red>您输入的旧邮箱错误</font>";
       				existInfo_old=false;
   				 }else{
   				 	spanNode.style.display = "inline";
        			spanNode.innerHTML="<font color=green>输入的邮箱正确</font>";
        			existInfo_old=true;
    			}
			}
		}
	}
	
	//通过一个AJAX异步验证
	function oldemailCheckFromDB(data){
		 createXMLHttpRequest();
		 //将状态触发器绑定到一个函数
		 xmlHttp.onreadystatechange=oldemailProcessor;
		 xmlHttp.open("GET", "${pageContext.request.contextPath}/servlet/ModifyEmailServlet?email="+data+"&operation=queryold");
		 //向服务器发送请求
		 xmlHttp.send(null);
	}
	//校验方法
	function checkMethod(inputNode, regex, spanId) {
		var b = false;
		var value = inputNode.value;
		var spanNode = document.getElementById(spanId);
		var nullregex=new RegExp("/^\s+$/g");

		if(value.length==0||nullregex.test(value)){
			inputNode.className = "error";
			spanNode.style.display = "inline";
			spanNode.style.color="red";
			spanNode.innerHTML="邮箱地址不能为空";
			return false;
		} 
		if (regex.test(value)) {//验证正确
			inputNode.className = "norm";
        	spanNode.innerHTML="<font color=green>邮箱正确</font>";
			b = true;
		} else {//验证错误
			inputNode.className = "error";
			spanNode.style.display = "inline";
			spanNode.style.color="red";
			spanNode.innerHTML="邮箱格式错误";
		}
		return b;
	}
	
	//验证邮箱
	function checkMail(mailNode,type)
	{
		var  regex = new RegExp("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		var b=false;
 		//1.验证邮箱格式是否合法
 		b=checkMethod(mailNode,regex,type);
 		//2.判断邮箱是有已经绑定
 		if(b==true){
 			if(type=="newemailpan"){
 			newemailCheckFromDB(mailNode.value);
 			}else{
 			oldemailCheckFromDB(mailNode.value);
 			}
 		}
 		return b;	
	}
	
	function checkForm(formNode)
	{
		with(formNode)
		{
			if(checkMail(oldemailname,'oldemailpan') &&checkMail(newemailname,'newemailpan') &&existInfo_new==false&&existInfo_old)
			{
				return true;
			}
		}
		return false;
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
				<jsp:include page="left.jsp"></jsp:include></div>

			<div class="right">
				<div class="blueLogo_a">
					<div class="title_a">修改邮箱</div>
				</div>
				<div class="r_form">
					<form id="registerForm"
						action="${pageContext.request.contextPath}/servlet/ModifyEmailServlet?operation=modify"
						method="post" name="modifyemail" onsubmit="return checkForm(this)">
						<div style="padding-top:30px"></div>
						<div class="line1">
							<span class="oldemail_label">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;旧邮箱地址:</span> <span
								class="oldemail_text"> <input type="text" id="oldemail"
								style="width:180px; height:25px;" name="oldemailname"
								id="oldemail" value='${form.email}'
								onblur="checkMail(this,'oldemailpan')"> </span> <span
								class="oldemail_tip" id="oldemailpan">请输入旧邮件地址，将用来找回密码 </span>
						</div>
						<div class="line2">
							<span class="newemail_label">新邮箱地址:</span> <span
								class="newemail_text"> <input type="text"
								name="newemailname" id="newemail"
								style="width:180px; height:25px;" value='${form.newemail}'
								onblur="checkMail(this,'newemailpan')"> </span> <span
								class="newemail_tip" id="newemailpan">修改邮件地址后需要重新激活 </span>
						</div>

						<div class="line3">
							<input type="submit" value="修改邮箱" class="doSubmit" />
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
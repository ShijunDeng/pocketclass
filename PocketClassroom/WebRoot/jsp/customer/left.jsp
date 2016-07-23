<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
body {
	margin: 0px;
	padding: 0px;
	font-size: 12px;
}

#container1 {
	font-size: 12px;
	width: 158px;
	height: auto !important;
	float: left;
}

#container2 {
	margin-top: -2px;
	border: 1px solid #0099FF;
	min-height: 350px;
	height: auto !important;
	width: 158px;
}

#title_h {
	background-color: #F1F1F1;
	width: 158px;
	height: 30px;
	cursor: pointer;
}

.te {
	padding-left:10px;
	padding-top:5px;
	text-align: left;
	font-size: 14px;
	font-weight: bold;
	color: #000000;
}

#ctext01,#ctext02 {
	background-color: #FFFFFF;
	height: 80px;
	width: 158px;
	text-align: left;
}

#ctext01 ul,#ctext02 ul {
	margin-left: 30px;
	padding-left: 10px;
	list-style-type: none;
	list-style-image: url(../images/ie.gif);
}

#ctext01 ul li,#ctext02 ul li {
	margin-bottom: 10px;
	margin-left: 5px;
}

#ctext01 ul li a,#ctext02 ul li a {
	text-decoration: none;
}

#ctext01 ul li a:hover,#ctext02 ul li a:hover {
	color: blue;
	text-decoration: underline;
	font-size: 13px;
}

.u_t {
	width: 160px;;
	height: 35px;
	background-image: url(../images/tit_bg1.gif) ;	
}

.text_s {
	padding-top:10px;
	font-weight: bold;
	text-decoration: none;
	color: #FFFFFF;
	font-size: 14px;
}
</style>
<script>
	function hiddenDiv(div) {
		div.style.display = (div.style.display == 'none' ? 'block' : 'none');
	}
	var xmlHttp;
	function createXMLHttpRequest() {
		if (window.ActiveXObject) {//是IE浏览器
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {//非IE浏览器
			xmlHttp = new XMLHttpRequest();
		}
	}

	//从服务器返回验证结果
	function unReadNumProcessor() {
		if (xmlHttp.readyState == 4) {//如果响应完成
			if (xmlHttp.status == 200) {//如果返回成功
				return xmlHttp.responseText.toString();
			}
		}
	}
	//通过一个AJAX异步验证
	function getUnReadNumFromDB() {
		createXMLHttpRequest();
		//将状态触发器绑定到一个函数
		xmlHttp.onreadystatechange = unReadNumProcessor;
		xmlHttp
				.open("GET",
						"${pageContext.request.contextPath}/servlet/LearnServlet?option=unReadNum");
		//向服务器发送请求
		xmlHttp.send(null);
	}
</script>

<body>
	<div id="container1">
		<div class="u_t">
			<div class="text_s">个人中心 </div>
		</div>
		<div id="container2">
			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext01'))" >
				<div class="te">我的设置</div>
			</div>
			<div id="ctext01" style="display:block">
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/servlet/ReadUserInfoServlet">
							个人信息</a>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/servlet/ModifyPasswordServlet">
							修改密码</a>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/servlet/ModifyEmailServlet">
							修改邮箱</a>
					</li>
				</ul>
			</div>

			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext01'))">
				<div class="te">我的应用</div>
			</div>
			<div id="ctext02" style="display:block">
				<ul>
					<li><a
						href="${pageContext.request.contextPath}/servlet/LearnServlet?isEnd=NO">
							我的学习中心</a>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/servlet/FavoCourseServlet">
							我的收藏</a>
					</li>
					<li><a
						href="${pageContext.request.contextPath}/servlet/MsgServlet?idLoca=001">
							我的消息<span style="color:red">${unReadNum}[未读]</span> </a>
					</li>
					<li><a href="#"> 我的评论</a>
					</li>
					<li><a href="#"> 我的笔记</a>
					</li>
					<li><a href="#"> 我的交流</a>
					</li>
				</ul>
			</div>
		</div>

	</div>

</body>


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

.ctext {
	background-color: #FFFFFF;
	height: 80px;
	width: 158px;
	text-align: left;
}

.ctext ul {
	margin-left: 30px;
	padding-left: 10px;
	list-style-type: none;
	list-style-image: url(../images/ie.gif);
}

.ctext ul li {
	margin-bottom: 10px;
	margin-left: 5px;
}

.ctext ul li a {
	text-decoration: none;
}

.ctext ul li a:hover {
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
</script>

<body>
	<div id="container1">
		<div class="u_t">
			<div class="text_s">个人中心 </div>
		</div>
		<div id="container2">
			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext01'))" >
				<div class="te">公告管理</div>
			</div>
			<div id="ctext01" class="ctext" style="display:none">
				<ul>
					<li><a
						href="#">
							网站简介</a>
					</li>
					<li><a
						href="#">
							公告列表</a>
					</li>
					<li><a
						href="#">
							新增公告</a>
					</li>
				</ul>
			</div>

			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext02'))">
				<div class="te">用户管理</div>
			</div>
			<div id="ctext02" class="ctext" style="display:none">
				<ul>
					<li><a href="#">用户账户</a>
					</li>
					<li><a href="#">登录日志</a>
					</li>
				</ul>
			</div>
			
			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext03'))" >
				<div class="te">分类管理</div>
			</div>
			<div id="ctext03" class="ctext" style="display:none">
				<ul>
					<li><a
						href="#">
							一级分类</a>
					</li>
					<li><a
						href="#">
							二级分类</a>
					</li>
				</ul>
			</div>
			
			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext04'))" >
				<div class="te">视频管理</div>
			</div>
			<div id="ctext04" class="ctext" style="display:none">
				<ul>
					<li><a
						href="#">
							全部</a>
					</li>
					<li><a
						href="#">
							修改密码</a>
					</li>
					<li><a
						href="#">
							修改邮箱</a>
					</li>
				</ul>
			</div>
			
			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext05'))" >
				<div class="te">交流管理</div>
			</div>
			<div id="ctext05" class="ctext" style="display:none">
				<ul>
					<li><a
						href="#">
							个人信息</a>
					</li>
					<li><a
						href="#">
							修改密码</a>
					</li>
					<li><a
						href="#">
							修改邮箱</a>
					</li>
				</ul>
			</div>
			
			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext06'))" >
				<div class="te">笔记管理</div>
			</div>
			<div id="ctext06" class="ctext" style="display:none">
				<ul>
					<li><a
						href="#">
							个人信息</a>
					</li>
					<li><a
						href="#">
							修改密码</a>
					</li>
					<li><a
						href="#">
							修改邮箱</a>
					</li>
				</ul>
			</div>
			
			<div id="title_h"
				onclick="hiddenDiv(document.getElementById('ctext07'))" >
				<div class="te">下载管理</div>
			</div>
			<div id="ctext07" class="ctext" style="display:none">
				<ul>
					<li><a
						href="#">
							个人信息</a>
					</li>
					<li><a
						href="#">
							修改密码</a>
					</li>
					<li><a
						href="#">
							修改邮箱</a>
					</li>
				</ul>
			</div>
		</div>

	</div>

</body>


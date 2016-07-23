<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog/left.css">

<body>
	<div class="forum_menu">
		<span class="blog_con"><strong class="blog_title white_a"><a
				style="color: #fff;" href="javascript:void(0)">全部笔记</a> </strong> </span>
		<ul>
			<li><a style="font-size: 14px;" href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=S01">Java</a>
			</li>
			<li><a style="font-size: 14px;" href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=S02">Web前端技术</a>
			</li>
			<li><a style="font-size: 14px;" href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=S03">移动编程</a>
			</li>
			<li><a style="font-size: 14px;" href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=S04">数据库</a>
			</li>
			<li><a style="font-size: 14px;" href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=S05">.NET</a>
			</li>
			<li><a style="font-size: 14px;" href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=S06">其他语言</a>
			</li>
			<li><a style="font-size: 14px;" href="#">学习问题讨论</a>
			</li>
		</ul>
	</div>

	<br />
	<%---
	<div class="forum_menu">
		<span class="blog_con"><strong class="blog_title">本月笔记排行</strong>
		</span>
		<ul>
			<li class="rank">
				<div class="rank_logo">
					<a title="看看ta的笔记"
						href="#"
						target="_blank"><img class="img_border"
						src="${pageContext.request.contextPath}/images/heads/no_img.jpg"
						width="48px" height="48px" border="0" alt="" /> </a>
				</div>
				<div class="rank_info">
					<div class="rank_top">第1名</div>
					<div class="rank_top">
						<a id='customer_btn_10013859' style="font-size: 14px;"
							href='javascript:void(0)'
							onmouseover="triggerCustomerBar('10013859', '${pageContext.request.contextPath}/images/heads/no_img.jpg', this)">xiaodeng</a>
					</div>
				</div>
			</li>
		</ul>
	</div>


	<div class="forum_menu">
		<span class="blog_con"><strong class="blog_title">年度笔记排行</strong>
		</span>
		<ul>
			<li class="rank">
				<div class="rank_logo">

					<a title="看看ta的笔记"
						href="#"
						target="_blank"> <img class="img_border"
						src="${pageContext.request.contextPath}/images/heads/no_img.jpg" width="48px"
						height="48px" border="0" alt="" /> </a>

				</div>
				<div class="rank_info">
					<div class="rank_top">第1名</div>
					<div class="rank_top">
						<a id='customer_btn_10013859' style="font-size: 14px;"
							href='javascript:void(0)'
							onmouseover="triggerCustomerBar('10013859', '${pageContext.request.contextPath}/images/heads/no_img.jpg', this)">xiaodeng</a>
					</div>
				</div></li>
		</ul>
	</div>
	 --%>
</body>


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog/left.css">
<body>
	<table class="blog_forumline" >
		<tr class="blog_con">
			<td class="thhead blog_title">
			<a target="_parent">xiaodeng的笔记</a>
			</td>
			
		</tr>
		<tr>
			<td class="row1" align="center">
				<div class="div_block">
					<a title="看看ta的笔记"
						href=" "><img
						class="img_border"
						src="${pageContext.request.contextPath}/images/heads/no_img.jpg"
						width="100px" height="100px" border="0" alt="[头像]" />
					</a><br /> <a id='customer_btn_10000001' style="font-size: 14px;"
						href='javascript:void(0)'
						onmouseover="triggerCustomerBar('10000001', '#', this)">xiaodeng</a>
				</div></td>
		</tr>
		<tr>
			<td class="row1"><span class="cattitle">属性：</span>
			</td>
		</tr>



		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">搜索笔记</span>
			</td>
		</tr>
		<tr>
			<td>
				<form action="" method="get">
					<input type="text" name="search_keywords"
						style="width: 190px; height: 25px;font-size: 14px;" value="" /> <input
						type="hidden" name="search_author" value="2" /> <input
						type="hidden" name="user_id" value="2" /><br> <input type="submit"
						class="btn-img btn-s1" value="搜笔记" />
				</form></td>
		</tr>
		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">ta的交流分类</span>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed"
				href="#"
				title="ta的交流主题贴">ta的交流主题贴(数量)</a>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed"
				href="#" title="ta的所有交流贴">ta的所有交流贴(数量)</a>
			</td>
		</tr>

		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">ta的全部笔记</span>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed"
				href="#" title="全部笔记">全部笔记(数量)</a>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed"
				href="#"
				title="未分类笔记">分类1</a>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed"
				href="#"
				title="Java Web"> 分类2</a>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed"
				href="#"
				title="并发实践">分类3</a>
			</td>
		</tr>
		<tr>
		</tr>
	</table>



</body>
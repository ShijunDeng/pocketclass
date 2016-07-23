<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog/left.css">
	<%--start:判断是不是所有者--%>
	<c:choose>
	<c:when test="${sessionScope.username eq spaceinfo.username}">
	<c:set var="isMaster" value="yes"></c:set>
	</c:when>
	<c:otherwise>
	<c:set var="isMaster" value="no"></c:set>
	</c:otherwise>
	</c:choose>
	<%--end:判断是不是所有者--%>
	<table class="blog_forumline" style="cellspacing:1;cellpadding:4;width:100%;border:0;text-align: left;">
		<tr class="blog_con">
			<td class="thhead blog_title">${spaceinfo.username}的笔记
			</td>

		</tr>
		<tr>
			<td class="row1" align="center">
				<div class="div_block">
					<a title="看看ta的笔记" href=" "><img class="img_border"
						src="${pageContext.request.contextPath}/images/heads/${spaceinfo.headPorAdd}"
						width="100px" height="100px" border="0" alt="${spaceinfo.username}" /> </a><br /> <a
						id='customer_btn_10000001' style="font-size: 14px;"
						href='javascript:void(0)'
						onmouseover="triggerCustomerBar('10000001', '#', this)">${spaceinfo.username}</a>
				</div>
			</td>
		</tr>
		<tr>
			<td class="row1"><span class="cattitle">访问量：${spaceinfo.viewAmount} 文章总数：${spaceinfo.noteAmount}评论总数：${spaceinfo.cmtAmount}</span></td>
		</tr>



		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">搜索笔记</span></td>
		</tr>
		<tr>
			<td>
			<script type="text/javascript">
			    function checkKeyword(form)
			    {
			    	with(form)
			    	{
			    		var keywordstr = keyword.value;
						var regex = /^\s+$/g;
						if (keywordstr.length == 0 || regex.test(keywordstr))
						{
    						window.alert('请输入关键字！');
    						return false;
						}
			    	}
			    }
			</script>
				<form style="height:25px;width:180px;" action="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet" method="post"  onsubmit="return checkKeyword(this);">
					<input type="text" name="keyword" style="width: 110px; height: 25px;font-size: 14px;"/>
					<input type="hidden" name="username" value="${spaceinfo.username}"/>
					<input type="submit" class="btn-img btn-s1" value="搜笔记" />
				</form>
			</td>
		</tr>
		<c:if test="${isMaster eq 'yes'}">
		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">我的笔记管理</span></td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed" href="${pageContext.request.contextPath}/servlet/MakeNotesServlet">发表新笔记</a>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed" href="#">草稿箱(1)</a>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed" href="${pageContext.request.contextPath}/servlet/ManageCategoriesServlet">分类管理</a>
			</td>
		</tr>
		</c:if>
		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">交流分类</span>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed" href="#" title="交流主题贴">交流主题贴(0)</a>
			</td>
		</tr>
		<tr>
			<td class="row1"><a class="genmed" href="#" title="所有交流贴">所有交流贴(0)</a>
			</td>
		</tr>

		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">全部笔记</span>
			</td>
		</tr>
		<c:forEach items="${customlist}" var="custom">
		<tr>
			<td class="row1"><a class="genmed" href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${custom.idCustom}">${custom.customName}(${custom.noteAmount})</a>
			</td>
		</tr>
		</c:forEach>
		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">存档</span>
			</td>
		</tr>
		<c:forEach items="${yyyyMMmap}" var="yyyyMM">
		<tr>
			<td class="row1"><a class="genmed" href="${pageContext.request.contextPath}/servlet/DisplayArchiveServlet?username=${spaceinfo.username}&yyyyMM=${yyyyMM.key}&amount=${yyyyMM.value}">${yyyyMM.key}(${yyyyMM.value})</a>
			</td>
		</tr>
		</c:forEach>
		<tr class="blog_con">
			<td height="28" style="float: left;padding-left: 8px;"><span
				class="blog_title">最近来访者</span>
			</td>
		</tr>
	</table>

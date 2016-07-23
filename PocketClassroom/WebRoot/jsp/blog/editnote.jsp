<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>发表新笔记</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
   
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog/makenotes.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/soft/xheditor-1.1.14/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/soft/xheditor-1.1.14/xheditor-1.1.14-zh-cn.min.js"></script>

<script language="javascript"
	src="${pageContext.request.contextPath}/js/common/application.js"></script>
<script type="text/javascript">
	function checkForm(form)
	{
		var spanNode = document.getElementById("errorspan");
		with(form)
		{
			if(isNullOrBlank(title.value))
			{
				spanNode.innerHTML = "标题不能为空";
				return false;
			}
			if(superCategory.selectedIndex==0)
			{
				spanNode.innerHTML = "请选择系统分类";
				return false;
			}
			if(isNullOrBlank(content.value))
			{
				spanNode.innerHTML = "内容不能为空";
				return false;
			}
			//还要进行内容长度的限定
		}
		spanNode.style.color="green";
		spanNode.innerHTML = "<meta http-equiv='refresh' content='3;URL=/servlet/DisplayNoteServlet'>发表成功，正在跳转.......";
	}
	
</script>

</head>

<body id="container" >
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
		<div id="left">
			<jsp:include page="left3.jsp"></jsp:include>
		</div>
		<div id="right">
			<div>
				<form id="noteForm" action="${pageContext.request.contextPath}/servlet/EditNoteServlet?idNote=${note.idNote}" method="post" onsubmit="return checkForm(this)">
					<table cellspacing="1" cellpadding="3">
						<tr>
							<td class="blog_con blog_title" colspan="2" height="25"><b>
									编辑笔记 </b>
							</td>
						</tr>
						<tr>
							<td class="td_left">标题:</td>
							<td><input type="text" name="title" size="50px;" value="${note.title}"/></td>
						</tr>
						<tr>
							<td class="td_left">系统分类:</td>
							<td ><select id="idSuper" name="idSuper">
									<option value="none">选择分类</option>
										<option value="S01">Java</option>
										<option value="S02">Web前端技术</option>
										<option value="S03">数据库</option>
										<option value="S04">移动编程</option>
										<option value="S05">.NET</option>
										<option value="S06">其他语言</option>
							</select> 
							</td>
						</tr>
						<tr>
							<td class="td_left">自定义分类:</td>
							<td ><select id="idCustom" name="idCustom" >
									<c:forEach items="${customlist}" var="custom">
									<option value="${custom.idCustom}">${custom.customName}</option>
									</c:forEach>
							</select> 
							</td>
						</tr>
						<tr>
							<td class="td_left">评论:</td>
							<td><input type="radio" name="cmtallow" checked="checked" value="1"/>允许<input type="radio" name="cmtallow" value="0"/>禁止</td>
						</tr>
						<tr>
							<td class="td_left">内容:</td>
							<td><textarea id="content" name="content" class="xheditor" rows="12" cols="80" style="width:593px;">
                                ${note.content}
                                </textarea>
							</td>
						</tr>
						<tr>
							<td class="td_left">&nbsp;</td>
							<td>提示：内容长度最多2000字</td>
						</tr>
						<tr>
							<td class="td_left">标签:</td>
							<td><input type="text" name="tags"/><span>标签之间用引号隔开</span></td>
						</tr>
						<tr>
							<td class="td_left">&nbsp;</td>
							<td><span id="errorspan" style="color:red;"></span></td>
						</tr>
						<tr>
							<td class="td_left">&nbsp;</td>
							<td><input type="submit" value="发表"/><input type="button" value="存为草稿"/></td>
						</tr>
					</table>
				</form>
				<c:set var="idSup" value="${note.idSuper}"></c:set>
				<c:set var="idCus" value="${note.idCustom}"></c:set>
				<c:set var="cmt" value="${note.cmtallow}"></c:set>
				<script type="text/javascript">
				//得到用户笔记的系统分类
	            var selSuper = document.getElementById("idSuper");
		 		var optionsSuper = selSuper.options;
		 		var idSup = '${idSup}';
		 		for(var i =0;i<optionsSuper.length;i++)
				{
				 	if(optionsSuper[i].value==idSup)
					{
					 optionsSuper[i].selected=true;
					}
				}   
				//得到用户笔记的自定义分类
				 var selCustom = document.getElementById("idCustom");
		 		var optionsCustom = selCustom.options;
		 		var idCus = '${idCus}';
		 		for(var i =0;i<optionsCustom.length;i++)
				{
				 	if(optionsCustom[i].value==idCus)
					{
					 optionsCustom[i].selected=true;
					}
				}       
				//得到是否允许评论
				var cmt = '${cmt}';
				var radioCmt = document.getElementsByName("cmtallow");
				for(var i =0;i<radioCmt.length;i++)
				{
					if(radioCmt[i].value==cmt)
					{
						radioCmt[i].checked=true;
					}
				}       				
	            </script>
			</div>
		</div>
	</div>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>笔记中心</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog/notecenter.css">
	
</head>

<body id="container">
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
		<div id="categoryIndex"><jsp:include page="left1.jsp"></jsp:include></div>
		<div id="notes">
			<div class="list_right" style="padding-bottom: 5px;">
				<c:choose>
					<c:when test="${!empty idSuper}">
					<div>
						<div
							style="float:left;text-align:left; margin:10px;font-weight:bold;color: #fc5b03;">
							记笔记&gt;${categoryName}
						</div>
						<div style="float:right;text-align:left; margin:10px;font-weight:bold;">
							<c:if test="${!empty sessionScope.username}">
							<a
								href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${sessionScope.username}">我的空间</a>
							</c:if>
						</div>
					</div>
					</c:when>
					<c:when test="${!empty keyword}">
						<div>
						<div
							style="float:left;text-align:left; margin:10px;font-weight:bold;color: #fc5b03;">
							记笔记&gt; 【${keyword}】
						</div>
						<div style="float:right;text-align:left; margin:10px;font-weight:bold;">
							<c:if test="${!empty sessionScope.username}">
							<a
								href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${sessionScope.username}">我的空间</a>
							</c:if>
						</div>
					</div>
					</c:when>
					<c:otherwise>
						<div>
						<div
							style="float:left;text-align:left; margin:10px;font-weight:bold;color: #fc5b03;">
							记笔记&gt;全部笔记
						</div>
						<div style="float:right;text-align:left; margin:10px;font-weight:bold;">
							<c:if test="${!empty sessionScope.username}">
							<a
								href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${sessionScope.username}">我的空间</a>
							</c:if>
						</div>
					</div>
					</c:otherwise>
				</c:choose>
				<div class="clear"></div>
				<div class="yellow">
					<c:choose>
						<c:when test="${splitpage.currentPage==1}">
				首页 上一页
			</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty idSuper}">
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=previous">上一页</a>
								</c:when>
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=previous">上一页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=previous">上一页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${!empty idSuper}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:when test="${!empty keyword}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${splitpage.currentPage==splitpage.totalPages}">
				下一页 尾页
			</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty idSuper}">
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=last">尾页</a>
								</c:when>
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=last">尾页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=last">尾页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
				<c:choose>
					<c:when test="${!empty notelist}">
						<c:forEach items="${notelist}" var="note">
							<div class="item">
								<div class="item_top">[${note.categoryName}]<a 
										href="${pageContext.request.contextPath}/servlet/DisplayNoteServlet?idNote=${note.idNote}">${note.title}</a></div>
								<div class="item_left">
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${note.username}">
										<img style="width:72px;height:88px;"
										src="${pageContext.request.contextPath}/images/heads/${note.headPorAdd}">
									</a>
								</div>
								<div class="item_right">
									<div class="item_intro">
										${note.content}
										<div
											style="position:absolute; right:0; bottom:0; z-index:10; background-color:#FFFFFF;">...</div>
									</div>
								</div>
								<div class="item_bottom">${note.username} ${note.addTime}
									阅读(${note.viewAmount}) 评论(${note.cmtAmount})</div>
							</div>
							<hr>
						</c:forEach>
					</c:when>
					<c:otherwise>
				该分类下暂时没有发表笔记！
			</c:otherwise>
				</c:choose>
				<div class="yellow">
					<c:choose>
						<c:when test="${splitpage.currentPage==1}">
				首页 上一页
			</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty idSuper}">
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=previous">上一页</a>
								</c:when>
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=previous">上一页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=previous">上一页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${!empty idSuper}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:when test="${!empty keyword}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${splitpage.currentPage==splitpage.totalPages}">
				下一页 尾页
			</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty idSuper}">
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NotesInASuperServlet?idSuper=${idSuper}&option=last">尾页</a>
								</c:when>
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchNoteServlet?keyword=${keyword}&option=last">尾页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/NoteCenterServlet?option=last">尾页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div id="hotandnew">
			<div>
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
				<form style="margin-top:10px;"
					action="${pageContext.request.contextPath}/servlet/SearchNoteServlet"
					method="post" onsubmit="return checkKeyword(this);">
					<input type="text" name="keyword" style="width: 110px; height: 25px;font-size: 14px;" /> <input
						type="submit" class="btn-img btn-s1" value="搜笔记">
				</form>
			</div>
			<div id="newest"></div>
			<div id="hotest">
				<div class="blog_con"><span class="blog_title">热门笔记分类</span></div>
				<c:choose>
					<c:when test="${!empty hotestlist}">
						<c:forEach items="${hotestlist}" var="hotnote">
							<div class="hotest_item">
								[${hotnote.viewAmount}]&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/servlet/DisplayNoteServlet?idNote=${hotnote.idNote}">${hotnote.title}</a>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

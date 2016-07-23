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

<title>用户笔记中心</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/blog/usernotecenter.css">
<script type="text/javascript">
function confirmDelete()
{
	if(!confirm('确定删除此笔记吗？删除后，相关评论将一并删除！'))
	{
		return false;
	}
	return true;
}
</script>
</head>

<body id="container">
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
	<jsp:include page="../head.jsp"></jsp:include>
	<div id="d1">
		<div id="left">
			<jsp:include page="left3.jsp"></jsp:include>
		</div>
		<div id="right">
			<div class="yellow">
					<c:choose>
						<c:when test="${splitpage.currentPage==1}">
				首页 上一页
			</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=previous">上一页</a>
								</c:when>
								<c:when test="${!empty idCustom}">
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=previous">上一页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=previous">上一页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${!empty keyword}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:when test="${!empty idCustom}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${splitpage.currentPage==splitpage.totalPages}">
				下一页 尾页
			</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=last">尾页</a>
								</c:when>
								<c:when test="${!empty idCustom}">
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=last">尾页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=last">尾页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
				<c:choose>
					<c:when test="${!empty notelist}">
						<c:forEach items="${notelist}" var="note">
							<div class="item">
								<div class="item_top"><a href="${pageContext.request.contextPath}/servlet/DisplayNoteServlet?idNote=${note.idNote}">${note.title}</a></div>
								
								<div class="item_right">
									<div class="item_intro">
										${note.content}
										<div
											style="position:absolute; right:0; bottom:0; z-index:10; background-color:#FFFFFF;">...</div>
									</div>
								</div>
								<div class="item_bottom"> ${note.addTime}
									阅读(${note.viewAmount}) 评论(${note.cmtAmount})
									<c:if test="${isMaster eq 'yes'}">
									<a href="${pageContext.request.contextPath}/servlet/DisplayNoteServlet?idNote=${note.idNote}&oper=edit">编辑</a>
									<a href="${pageContext.request.contextPath}/servlet/DeleteNoteServlet?idNote=${note.idNote}" onclick="return confirmDelete()">删除</a>
									</c:if>
								</div>
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
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=previous">上一页</a>
								</c:when>
								<c:when test="${!empty idCustom}">
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=previous">上一页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=first">首页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=previous">上一页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${!empty keyword}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:when test="${!empty idCustom}">
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach begin="1" end="${splitpage.totalPages}" step="1"
								varStatus="status">
								<a
									href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=${status.count}">${status.count}</a>
							</c:forEach>
						</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${splitpage.currentPage==splitpage.totalPages}">
				下一页 尾页
			</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty keyword}">
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/SearchUserNoteServlet?username=${spaceinfo.username}&keyword=${keyword}&option=last">尾页</a>
								</c:when>
								<c:when test="${!empty idCustom}">
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNotesInACustomServlet?username=${spaceinfo.username}&idCustom=${idCustom}&option=last">尾页</a>
								</c:when>
								<c:otherwise>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=next">下一页</a>
									<a
										href="${pageContext.request.contextPath}/servlet/UserNoteCenterServlet?username=${spaceinfo.username}&option=last">尾页</a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
		</div>
	</div>
	<jsp:include page="../tail.jsp"></jsp:include>
</body>
</html>

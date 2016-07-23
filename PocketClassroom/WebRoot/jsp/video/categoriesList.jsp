<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common/flyout.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/findCourses/flyout.js">
	
</script>
<div id="allcourses">
	<div id="top" style="width: 214px;height: 31px;"><span style="font-size:14px;font-weight:bold;">课程分类</span></div>
	<div id="leftcolumn">
		<dl class="dropdown">
			<dt id="one-ddheader" class="upperdd" onMouseOver="ddMenu('one',1)"
				onMouseOut="ddMenu('one',-1)">
				<a
					href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=S01">Java</a>
			</dt>
			<dd id="one-ddcontent" onMouseOver="cancelHide('one')"
				onMouseOut="ddMenu('one',-1)">
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0101" class="underline">JavaSE</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0102" class="underline">JavaWeb</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0103" class="underline">JavaEE</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0104">框架技术</a></li>
				</ul>
			</dd>
		</dl>
		<dl class="dropdown">
			<dt id="two-ddheader" class="upperdd" onMouseOver="ddMenu('two',1)"
				onMouseOut="ddMenu('two',-1)"><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=S02">Web前端</a></dt>
			<dd id="two-ddcontent" onMouseOver="cancelHide('two')"
				onMouseOut="ddMenu('two',-1)">
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0201" class="underline">xml</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0202" class="underline">html</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0203" class="underline">Javascript</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0204">jquery</a></li>
				</ul>
			</dd>
		</dl>
		<dl class="dropdown">
			<dt id="three-ddheader" class="upperdd" onMouseOver="ddMenu('three',1)"
				onMouseOut="ddMenu('three',-1)"><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=S03">移动编程</a></dt>
			<dd id="three-ddcontent" onMouseOver="cancelHide('three')"
				onMouseOut="ddMenu('three',-1)">
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0301" class="underline">Android</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0302" class="underline">iOS</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0303" class="underline">Window Phone</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0304">JavaME</a></li>
				</ul>
			</dd>
		</dl>
		<dl class="dropdown">
			<dt id="four-ddheader" onMouseOver="ddMenu('four',1)"
				onMouseOut="ddMenu('four',-1)"><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=S04">数据库</a></dt>
			<dd id="four-ddcontent" onMouseOver="cancelHide('four')"
				onMouseOut="ddMenu('four',-1)">
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0401" class="underline">Oracle</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0402" class="underline">MySQL</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0403" class="underline">SQLServer</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0404">NoSQL</a></li>
				</ul>
			</dd>
		</dl>
		<dl class="dropdown">
			<dt id="five-ddheader" onMouseOver="ddMenu('five',1)"
				onMouseOut="ddMenu('five',-1)"><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=S05">.NET</a></dt>
			<dd id="five-ddcontent" onMouseOver="cancelHide('five')"
				onMouseOut="ddMenu('five',-1)">
				<ul>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0501" class="underline">C#</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0502" class="underline">ASP.NET</a></li>
					<li><a href="${pageContext.request.contextPath}/servlet/FindVideoSeriesServlet?id=M0503" class="underline">WinForm</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</div>


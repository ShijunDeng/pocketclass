<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/blog/blog.css">

<td align="left" valign="top"><br />
	<table>
		<tr>
			<td>
				<form action="/forum/blog/index.html" method="get">
					<input type="text" name="search_keywords" value=""
						style="width: 130px;height: 23px;" /> <input type="submit"
						class="btn-img btn-s1" value="搜笔记" />
				</form></td>
		</tr>
		<tr>
			<td><script type="text/javascript">
				function nTabs(thisObj, num) {
					thisObj = $(thisObj);
					if (thisObj.attr("class") == "active")
						return;

					var tabList = thisObj.siblings().andSelf();
					tabList.removeClass();
					for ( var i = 0; i < tabList.length; i++) {
						if (i == num) {
							thisObj.addClass("active");
							$(
									"#" + thisObj.parent().attr("id")
											+ "_Content" + i)
									.css("display", "");
						} else {
							$(tabList.get(i)).addClass("normal");
							$(
									"#" + thisObj.parent().attr("id")
											+ "_Content" + i).css("display",
									"none");
						}
					}
				}
			</script>
				<div class="nTab">
					<div class="TabTitle">
						<ul id="myTab0">
							<li class="active" onclick="nTabs(this,0);">&nbsp;<strong>周排行</strong>
							</li>
							<li class="normal" onclick="nTabs(this,1);">&nbsp;<strong>月排行</strong>
							</li>
							<li class="normal" onclick="nTabs(this,2);">&nbsp;<strong>年排行</strong>
							</li>
						</ul>
					</div>
					<div class="TabContent">

						<div id="myTab0_Content2" class="forum_menu_big"
							style="display: none;border: none;">
							<ul>
								<li>[28297] <a href='/forum/blogPost/list/1290.html'
									target="_blank" title="java从零基础到精通的整个详细笔记（带批注）">
										java从零基础到精通的整... </a></li>
								<li>[19463] <a href='/forum/blogPost/list/990.html'
									target="_blank" title="java学习笔记（必看经典）"> java学习笔记（必看经典... </a></li>
							</ul>
						</div>
					</div>

				</div></td>
		</tr>
	</table>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>欢迎来到掌上课堂PC版首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/common/flyout.css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/findCourses/flyout.js"></script>
<script type="text/javascript">
	var $ = function(id) {
		return "string" == typeof id ? document.getElementById(id) : id;
	};
	var Extend = function(destination, source) {
		for ( var property in source) {
			destination[property] = source[property];
		}
		return destination;
	};
	var CurrentStyle = function(element) {
		return element.currentStyle
				|| document.defaultView.getComputedStyle(element, null);
	};
	var Bind = function(object, fun) {
		var args = Array.prototype.slice.call(arguments).slice(2);
		return function() {
			return fun.apply(object, args.concat(Array.prototype.slice
					.call(arguments)));
		};
	};
	var Tween = {
		Quart : {
			easeOut : function(t, b, c, d) {
				return -c * ((t = t / d - 1) * t * t * t - 1) + b;
			}
		},
		Back : {
			easeOut : function(t, b, c, d, s) {
				if (s == undefined)
					s = 1.70158;
				return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
			}
		},
		Bounce : {
			easeOut : function(t, b, c, d) {
				if ((t /= d) < (1 / 2.75)) {
					return c * (7.5625 * t * t) + b;
				} else if (t < (2 / 2.75)) {
					return c * (7.5625 * (t -= (1.5 / 2.75)) * t + .75) + b;
				} else if (t < (2.5 / 2.75)) {
					return c * (7.5625 * (t -= (2.25 / 2.75)) * t + .9375) + b;
				} else {
					return c * (7.5625 * (t -= (2.625 / 2.75)) * t + .984375)
							+ b;
				}
			}
		}
	};
	//容器对象,滑动对象,切换数量
	var SlideTrans = function(container, slider, count, options) {
		this._slider = $(slider);
		this._container = $(container);//容器对象
		this._timer = null;//定时器
		this._count = Math.abs(count);//切换数量
		this._target = 0;//目标值
		this._t = this._b = this._c = 0;//tween参数

		this.Index = 0;//当前索引

		this.SetOptions(options);

		this.Auto = !!this.options.Auto;
		this.Duration = Math.abs(this.options.Duration);
		this.Time = Math.abs(this.options.Time);
		this.Pause = Math.abs(this.options.Pause);
		this.Tween = this.options.Tween;
		this.onStart = this.options.onStart;
		this.onFinish = this.options.onFinish;

		var bVertical = !!this.options.Vertical;
		this._css = bVertical ? "top" : "left";//方向

		//样式设置
		var p = CurrentStyle(this._container).position;
		p == "relative" || p == "absolute"
				|| (this._container.style.position = "relative");
		this._container.style.overflow = "hidden";
		this._slider.style.position = "absolute";

		this.Change = this.options.Change ? this.options.Change
				: this._slider[bVertical ? "offsetHeight" : "offsetWidth"]
						/ this._count;
	};
	SlideTrans.prototype = {
		//设置默认属性
		SetOptions : function(options) {
			this.options = {//默认值
				Vertical : true,//是否垂直方向（方向不能改）
				Auto : true,//是否自动
				Change : 0,//改变量
				Duration : 50,//滑动持续时间
				Time : 10,//滑动延时
				Pause : 2000,//停顿时间(Auto为true时有效)
				onStart : function() {
				},//开始转换时执行
				onFinish : function() {
				},//完成转换时执行
				Tween : Tween.Quart.easeOut
			//tween算子
			};
			Extend(this.options, options || {});
		},
		//开始切换
		Run : function(index) {
			//修正index
			index == undefined && (index = this.Index);
			index < 0 && (index = this._count - 1) || index >= this._count
					&& (index = 0);
			//设置参数
			this._target = -Math.abs(this.Change) * (this.Index = index);
			this._t = 0;
			this._b = parseInt(CurrentStyle(this._slider)[this.options.Vertical ? "top"
					: "left"]);
			this._c = this._target - this._b;

			this.onStart();
			this.Move();
		},
		//移动
		Move : function() {
			clearTimeout(this._timer);
			//未到达目标继续移动否则进行下一次滑动
			if (this._c && this._t < this.Duration) {
				this.MoveTo(Math.round(this.Tween(this._t++, this._b, this._c,
						this.Duration)));
				this._timer = setTimeout(Bind(this, this.Move), this.Time);
			} else {
				this.MoveTo(this._target);
				this.Auto
						&& (this._timer = setTimeout(Bind(this, this.Next),
								this.Pause));
			}
		},
		//移动到
		MoveTo : function(i) {
			this._slider.style[this._css] = i + "px";
		},
		//下一个
		Next : function() {
			this.Run(++this.Index);
		},
		//上一个
		Previous : function() {
			this.Run(--this.Index);
		},
		//停止
		Stop : function() {
			clearTimeout(this._timer);
			this.MoveTo(this._target);
		}
	};
</script>
<style type="text/css">
#d1 {
	border: border:#00CCFF 1px solid;
	width: 960px;
	height: 230px;
}

#hotcourses,#newcourses {
	margin-top: 10px;
	height: 330px;
	width: 960px;
	border: #00CCFF 1px solid;
}

#findAndhot {
	margin-left: 10px;
	height: 228px;
	width: 450px;
	float: left;
	border: #00CCFF 1px solid;
}

#websiteNotice {
	margin-left: 10px;
	height: 228px;
	width: 270px;
	float: left;
	border: #00CCFF 1px solid;
}

#findAndhot,#websiteNotice {
	display: inline;
}

.top_enum {
	background-image: url(../images/videos/top_bar.jpg);
	height: 30px;
	color: #666666;
}

.tab {
	border: #00FFFF 1px solid;
	height: 300px;
	width: 960px;
}

.tab td {
	height: 135px;
	width: 160px;
	border: #00FFFF 1px solid;
	text-align: center;
}

.shortname {
	text-decoration: none;
	color: #FC8C04;
	font-size: 14px;
}

.videolist {
	float: left;
}

.search_div {
	width: 200px;
	height: 28px;
	background:
		url("${pageContext.request.contextPath}/images/main/search.jpg")
		no-repeat;
	float: left;
	margin-left: 5px;
	margin-top: 0px;
	border: 0px;
	position: relative;
}

img {
	border-width: 0;
	border-style: none;
}

.newest_item a {
	text-decoration: none;
	color: #555252;
}

.newest_item a:hover {
	text-decoration: underline;
	color: rgb(1, 171, 233);
}

.container1,.container1 img {
	width: 450px;
	height: 190px;
}

.container1 {
	border: 0px solid #333;
	border-top-width: 1px;
	border-top-color: #00AAFF;
}

.container1 img {
	border: 0;
}

.num {
	position: absolute;
	right: 5px;
	bottom: 5px;
}

.num li {
	float: left;
	list-style: none;
	color: #fff;
	text-align: center;
	line-height: 16px;
	width: 16px;
	height: 16px;
	font-family: Arial;
	font-size: 12px;
	cursor: pointer;
	margin: 1px;
	border: 1px solid #707070;
	background-color: #060a0b;
}

.num li.on {
	line-height: 18px;
	width: 18px;
	height: 18px;
	font-size: 14px;
	border: 0;
	background-color: #ce0609;
	font-weight: bold;
}

.text_a {
	font-size: 14px;
	font-weight: bold;
	color: #fff;
}
</style>
</head>
<body id="container">
	<jsp:include page="head.jsp"></jsp:include>
	<div id="d1">
		<div class="videolist">
			<jsp:include page="video/categoriesList.jsp"></jsp:include>
		</div>
		<div id="findAndhot">
			<div>
				<form
					style="border: 0px solid #333;margin-bottom:0px;margin-top:0px;"
					action="${pageContext.request.contextPath}/servlet/SearchVideoServlet"
					method="post">
					<div class="search_div">
						<input type="text" name="keyword"
							style="border:none;margin-left:20px;margin-top:3px;width:160px;">
					</div>
					<input style="margin-top:1px;float: left; margin-left: 5px;"
						type="image"
						src="${pageContext.request.contextPath}/images/main/find.jpg">
				</form>
			</div>
			<div class="container1" id="idContainer2">
				<table id="idSlider2" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><a href="#"><img
								src="${pageContext.request.contextPath}/images/items1.jpg" /> </a>
						</td>
						<td><a href="#"><img
								src="${pageContext.request.contextPath}/images/items2.jpg" /> </a>
						</td>
						<td><a href="#"><img
								src="${pageContext.request.contextPath}/images/items3.jpg" /> </a>
						</td>
					</tr>
				</table>
				<ul class="num" id="idNum">
				</ul>
			</div>
			<script>
				var forEach = function(array, callback, thisObject) {
					if (array.forEach) {
						array.forEach(callback, thisObject);
					} else {
						for ( var i = 0, len = array.length; i < len; i++) {
							callback.call(thisObject, array[i], i, array);
						}
					}
				};
				var st = new SlideTrans("idContainer2", "idSlider2", 3, {
					Vertical : false
				});
				var nums = [];
				//插入数字
				for ( var i = 0, n = st._count - 1; i <= n;) {
					(nums[i] = $("idNum").appendChild(
							document.createElement("li"))).innerHTML = ++i;
				}
				forEach(nums, function(o, i) {
					o.onmouseover = function() {
						o.className = "on";
						st.Auto = false;
						st.Run(i);
					};
					o.onmouseout = function() {
						o.className = "";
						st.Auto = true;
						st.Run();
					};
				});
				//设置按钮样式
				st.onStart = function() {
					forEach(nums, function(o, i) {
						o.className = st.Index == i ? "on" : "";
					});
				};
				st.Run();
			</script>
		</div>
		<div id="websiteNotice">
			<div id="top" class="text_a" style="padding-top:8px;">公告</div>
					<div ><span style="color:#00AAFF">&nbsp;<br>好消息：掌上课堂正式上线啦!<br>让你的课堂就在掌上，如影随形!<br>这里的课堂，更精彩!<br>让您随时随地遨游于知识的海洋，我们<br>将为您提供一个自由时尚的课堂!</span></div>
			
		</div>
	</div>
	<div id="hotcourses">
		<div class="top_enum">最热课程</div>
		<table class="tab">
			<tr>
				<c:forEach begin="0" step="1" end="4" varStatus="status">
					<c:set var="series" value="${newestlist[status.index]}"></c:set>
					<td>
						<div class="newest_item">
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}"><img
									alt="${series.topic}" style="width:120px;height:120;"
									src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}">
								</a>
							</div>
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}">${series.topic}</a>
							</div>
						</div></td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach begin="0" step="1" end="4" varStatus="status">
					<c:set var="series" value="${newestlist[status.index+5]}"></c:set>
					<td>
						<div class="newest_item">
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}"><img
									alt="${series.topic}" style="width:120px;height:120;"
									src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}">
								</a>
							</div>
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}">${series.topic}</a>
							</div>
						</div></td>
				</c:forEach>
			</tr>
		</table>
	</div>
	<div id="newcourses">
		<div class="top_enum">新课上线</div>
		<table class="tab">
			<tr>
				<c:forEach begin="0" step="1" end="4" varStatus="status">
					<c:set var="series" value="${newestlist[status.index]}"></c:set>
					<td>
						<div class="newest_item">
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}"><img
									alt="${series.topic}" style="width:120px;height:120;"
									src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}">
								</a>
							</div>
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}">${series.topic}</a>
							</div>
						</div></td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach begin="0" step="1" end="4" varStatus="status">
					<c:set var="series" value="${newestlist[status.index+5]}"></c:set>
					<td>
						<div class="newest_item">
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}"><img
									alt="${series.topic}" style="width:120px;height:120;"
									src="${pageContext.request.contextPath}/images/videos/${series.thumbnail}">
								</a>
							</div>
							<div>
								<a
									href="${pageContext.request.contextPath}/servlet/SeriesDetailServlet?seriesID=${series.id}">${series.topic}</a>
							</div>
						</div></td>
				</c:forEach>
			</tr>
		</table>
	</div>
	<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户基本信息-掌上课堂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords"
	content="掌上课堂,java初级,java中级视频,java高级视频,android4就业,maven视频,slf4j视频,hibernate4视频,s2s3h4," />
<meta name="description"
	content="java学习培训,框架技术,深入浅出struts、hibernate、spring,企业架构、工具技术、服务器技术,数据库技术,oracle、mysql,jquery学习,.net培训,移动编程,android,iOS,php教程," />
<link
	href="${pageContext.request.contextPath}/css/customer/userInfo.css"
	rel="stylesheet" type="text/css" media="all">
<script language="javascript"
	src="${pageContext.request.contextPath}/js/common/application.js"></script>
</head>
<script type="text/javascript">
function queryGender(qGender){
	if(qGender=="male"){
		document.write('<input type="radio" value="male" name="gender" checked="checked"/>男 &nbsp;&nbsp; <input type="radio" value="female" name="gender"/>女');
	}else if(qGender=="female"){		
		document.write('<input type="radio" value="male" name="gender"/>男 &nbsp;&nbsp;<input type="radio" value="female" name="gender" checked="checked"/>女');
	}else{
		document.write('<input type="radio" value="male" name="gender"/>男 &nbsp;&nbsp;<input type="radio" value="female" name="gender"/>女');
	}
}

function leapYear(year, month) {
    switch (parseInt(month)) {
        case 2:
            return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) ? 29 : 28;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        default:
            return 31;
    }
}
function changeDay(yearObj, monthObj, dayObj) {
    dayObj.options.length = 0;
    for (var i = 1; i <= leapYear(yearObj.value, monthObj.value); i++)
        dayObj.options.add(new Option(i, i));

    if (monthObj.value == iDate.getMonth() + 1 && yearObj.value == iDate.getFullYear())
        dayObj.selectedIndex = iDate.getDate() - 1;
}

function createDateSelect(y, m, d) {
    document.write('<select id="year" name="year" class="select" onchange="changeDay(this, document.getElementById(\'month\'), document.getElementById(\'day\'))">');
    for (var i = 0; i <= 100; i++)
        document.write('<option value="' + (iDate.getFullYear() - i) + '"' + (iDate.getFullYear() - i == y ? ' selected="selected"' : '') + '>' + (iDate.getFullYear() - i) + '</option>');
    document.write('</select>&nbsp;年&nbsp;&nbsp;');

    document.write('<select id="month" name="month" class="select" onchange="changeDay(this, document.getElementById(\'month\'), document.getElementById(\'day\'))">');

    for (var i = 1; i < 13; i++)
        document.write('<option value="' + i + '"' + (i == m ? ' selected="selected"' : '') + '>' + i + '</option>');
    document.write('</select>&nbsp;月&nbsp;&nbsp;');

    document.write('<select id="day" name="day" class="select">');
    for (var i = 1; i <= leapYear(iDate.getFullYear(), iDate.getMonth() + 1); i++)
        document.write('<option value="' + i + '"' + (i == d ? ' selected="selected"' : '') + '>' + i + '</option>');
    document.write('</select>&nbsp;日');
}


//校验方法
	function checkMethod(inputNode, regex, spanId) {
		var b = false;
		var value = inputNode.value;
		var spanNode = document.getElementById(spanId);
		if (regex.test(value)||trim(value)==""||value.length==0) {//验证正确
			inputNode.className = "norm";
			spanNode.style.display = "none";
			b = true;
		} else {//验证错误
			inputNode.className = "error";
			spanNode.style.display = "inline";
			spanNode.style.color="red";
			spanNode.innerHTML="格式不正确";
		}
		return b;
	}
	//检测姓名
	function checkName(nameNode,type) {
		var regex = new RegExp("^[\u4e00-\u9fa5]{1,10}$");
		var b= checkMethod(nameNode, regex, type);
		return b;
	}
	//检测学校已经学院名称
	function checkCollegeName(nameNode,type) {
		var regex = new RegExp("^[\u4e00-\u9fa5]{1,20}$");
		var b= checkMethod(nameNode, regex, type);
		return b;
	}
	//检测电话号码
	function checkPhoneNum(numNode,type) {
		var regex = new RegExp("^[1-9][0-9]{10}$");
		var b= checkMethod(numNode, regex, type);
		return b;
	}
	//检测QQ号码
	function checkQQ(qq,type) {
	//5-13位
		var regex = new RegExp("^[1-9][0-9]{4,12}$");
		var b= checkMethod(qq, regex, type);
		return b;
	}
	function checkForm(formNode)
	{
		with(formNode)
		{
			if(checkName(name,"namepan") && checkCollegeName(college,"collegepan")
			 && checkCollegeName(academy,"academypan") && checkPhoneNum(phoneNum,"phoneNumpan")
			 &&checkQQ(QQ,"QQpan"))
			{
				return true;
			}
		}
		return false;
	}
</script>
<body>
	<div align="center">
		<div align="center">
			<jsp:include page="../head.jsp"></jsp:include>
		</div>
		<div class="main">
			<div class="left">
				<jsp:include page="left.jsp"></jsp:include></div>
				<div class="right">
					<div class="blueLogo_a">
						<div class="title_a">个人信息</div>
					</div>
					<div class="p_form">
						<form enctype="multipart/form-data" id="userinfoForm"
							action="${pageContext.request.contextPath}/servlet/UserInfoServlet"
							method="post" name="userInfo" onsubmit="return checkForm(this)">

							<div style="color:red;text-align:center;padding-top:30px">
								完善更多个人信息，有助于我们为您提供更加个性化的服务；掌上课堂将尊重并保护您的隐私。</div>
							<div class="s_form1">
								<fieldset>
									<legend>基本信息（必填）</legend>
									<ul>
										<li><label>登录名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span>${username}</span>
										</li>
										<li><label>昵称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
											type="text" class="text" id="nickname" maxlength="32"
											name="nickname" value='${nickname}' size="25"> <span
											class="nickname_tip" id="nicknamepan"></span>
										</li>
									</ul>
								</fieldset>
							</div>
							<div class="s_form2">
								<fieldset>
									<legend>个人信息</legend>
									<ul>
										<li><label>姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
											type="text" class="text" id="name" name="name"
											value='${name}' size="25" onblur="checkName(this,'namepan')">
											<span class="name_tip" id="namepan"></span></li>

										<li><label>性别：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <script
												type="text/javascript">
										var qGender='${gender}'.replace(/-/g,"");
										queryGender(qGender);
										</script></li>
										<li><label>生日：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <script
												type="text/javascript">
                         				var str='${dateOfBirth}'.replace(/-/g,"");
										var year=str.substring(0,4);
										var month=str.substring(4,6);
										var day=str.substring(6,8);
                           				var iDate =new Date();                         
                            			createDateSelect(year ,month, day);
                       				 	</script></li>
										<li><label>Q&nbsp;&nbsp;Q：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<input type="text" class="text" id="QQ" name="QQ"
											value='${QQ}' size="25" onblur="checkQQ(this,'QQpan')">
											<span class="QQ_tip" id="QQpan"></span>
										</li>
										<li><label>手机：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
											type="text" class="text" id="phoneNum" name="phoneNum"
											value='${phoneNum}' size="25"
											onblur="checkPhoneNum(this,'phoneNumpan')"> <span
											class="phone_tip" id="phoneNumpan"></span>
										</li>
									</ul>
								</fieldset>
							</div>
							<div class="s_form3">
								<fieldset>
									<legend>学校信息</legend>
									<ul>
										<li><label>学校名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
											type="text" class="text" id="college" name="college"
											value='${college}' size="25"
											onblur="checkCollegeName(this,'collegepan')"> <span
											class="college_tip" id="collegepan"></span>
										</li>
										<li><label>学院名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input
											type="text" class="text" id="academy" name="academy"
											value='${academy}' size="25"
											onblur="checkCollegeName(this,'academypan')"> <span
											class="academy_tip" id="academypan"></span>
										</li>

									</ul>
								</fieldset>
							</div>
							<div class="s_form4">
								<fieldset>
									<legend>其他信息（选填）</legend>
									<ul>
										<li><a name="image"></a> <label>头像：&nbsp;&nbsp;&nbsp;&nbsp;</label>
											<div class="user_pic"
												style="width: 100px;height: 100px;padding-left:100px">
												<img id="image" width="100" height="100"
													src="${pageContext.request.contextPath}/images/heads/${headPorAdd}"
													alt="用户头像" />
											</div></li>
										<li style="list-style-type:none"><label>&nbsp;&nbsp;</label>
											<div style="float: left;width: 100%;padding-top:30px;">
												选择图片：<input type="file" name="headPorAdd" /><br />
												<div style="padding-top:10px">提示信息：
													请上传jpg、gif、bmp或png格式的图片,大小限制在512kb以内</div>
												<br />
											</div></li>

									</ul>
								</fieldset>
							</div>
							<div>
								<input type="submit" value="修改个人信息" class="doSubmit" />
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div>
				<jsp:include page="../tail.jsp"></jsp:include><br>
			</div>
		</div>
	
</body>
</html>
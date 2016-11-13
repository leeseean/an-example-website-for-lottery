<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>
<%
String loginName = request.getParameter("loginName");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="${resourceRoot}/agent/css/normalize.css" />
<link rel="stylesheet" href="${resourceRoot}/agent/css/base.css" />
<link rel="stylesheet" media="all" href="${resourceRoot}/agent/css/login.css" />

<script src="${resourceRoot}/agent/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">

/****/
String.prototype.trim = function() {
    return this.replace(/(^\s+)|(\s+$)/g, "");
};

var Verify=0;
var val = "0123456789abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
var p = "";
function sendForm(){
	var s,v;
	s=document.getElementById("loginName").value.trim();
	p=document.getElementById("password").value.trim();
	v=document.getElementById("verifycode").value.trim();
	
	if(Verify == 1 && v==""){
		alert("请输入验证码");
		document.getElementById("verifycode").focus();
		return false;
	}
	if(s==""||p==""){
		alert("请输入帐号密码");
		document.getElementById("loginName").focus();
		return false;
	}
	for(var i=0; i<s.length; i++){
		if(val.indexOf(s.charAt(i)) == -1){
			alert("帐号包含非法字符 ["+s.charAt(i)+"]");
			return false;
		}
	}
	
	
	if (v == "" || v.Leng == 0) {
		alert("验证码不能为空！");
		document.getElementById("verifycode").focus();
		return false;
	}
	var rpp = /^[0-9]{4}$/;//验证码
	if (!rpp.test(v)) {
		alert("请输入四位数字验证码");
		document.getElementById("verifycode").focus();
		return false;
	}

	return true;
}

function login() {
	if (!sendForm())
		return false;
	var dates = $("#agentLoginForm").serialize();
	$.ajax({
		url : "${ctx}/agent/login",
		 type: "post",
		data : dates,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		error : function() {
			alert("用户登入网络连接出错~");
			window.location.reload();
		},
		success : function(obj) {
			//重启服务器，令牌失效，重新获取令牌
			if (typeof (obj) == 'undefined' || obj == null) {
				window.location.reload();
			}
			if (obj.rs) {
				window.location.href = "${ctx}/agent/main";
			} else {
				$("#password").val(p);
				$("#verifycode").val("");
				createCode();
				alert(obj.msg);
				$("#_form_token_uniq_id").val(obj.token.token);
			}
		}
	});
}

function createCode(){
	$("#secimg").attr("src","${ctx}/test-resources-code.jpg?"+new Date().getTime());
}


$(document).ready(function(){
	//防止session过期时，index页面在子页面中打开
	if(self != top){
		window.top.location="${ctx}/agent/index";
	}
	createCode();
	$("#loginName").keydown(function(event){
		if(event.keyCode == 13){
			$("#password")[0].focus();
		}
	});
	$("#password").keydown(function(event){
		if(event.keyCode == 13){
			$("#verifycode")[0].focus();
		}
	});
	
	$("#verifycode").keydown(function(event){
		if(event.keyCode == 13){
			login();
		}
	});
	
	var loginName = $.trim("<%=loginName%>");
	if(loginName!=null && loginName != "null"){
		$("#loginName").val(loginName);
		$("#password")[0].focus();
	}else{
		$("#loginName")[0].focus();
	}
});

</script>

</head>
<body>
	<div class="wrapCss">
		<div class="bg001_ag"></div>
		<div class="bg002_ag">
			<div class="div_warp">
				<table class="tab_001">
					<tr>
						<td><a href="javascript:void(0);">繁体版</a></td>
						<td><a href="javascript:void(0);">简体版</a></td>
						<td><a href="javascript:void(0);">English</a></td>
					</tr>
				</table>
				<form name="agentLoginForm" id="agentLoginForm" action="${ctx}/agent/login" method="post">
					<mh:token></mh:token>
					<table class="tab_002">
						<tbody>
							<tr>
								<td class="td_001">帳號：</td>
								<td class="td_002" colspan="2">
									
									<input type="text" id="loginName"  name="loginName"  style="width:150px;height: 20px;" tabIndex=1 maxlength="16" title="只能输入[数字、字母、下划线]"  style="ime-mode:disabled" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" />
								</td>
							</tr>
							<tr>
								<td class="td_001">密碼：</td>
								<td class="td_002" colspan="2">
									<input type="password" id="password" name="password" tabIndex=2 autocomplete="off" style="width:150px;height: 20px;" maxlength="40"/>
								</td>
							</tr>
							<tr>
								<td class="td_001">验证码：</td>
								<td class="td_003">
									<input id="verifycode" name="verifycode" type="text" maxlength="4" tabIndex=3 size="4" class="i-text" style="width:40px;height: 20px;"  onkeyup="value=value.replace(/[^\d]/g,'')"/>
	            					
			            			<span id="testverifycodeLabelId" onclick="javascript:createCode();"><img
					                  src="${resourceRoot}/web/ybb/assets/images/yzm.gif"  title="看不清楚，换一张"
					                  id="secimg" style="vertical-align: middle" />
					                </span>
								</td>
							</tr>
							<tr>
								<td colspan="3" style="padding-left:60px">
									<input id="Forms Button1" type="button" onclick="login();return false;" value="登&nbsp;&nbsp;入" class="login" align="middle"  /> 
									<a href="" class="a_001">忘記密碼</a>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<div class="bg005_ag"></div>
	</div>
	<div class="copyright">&copy; Copyright by Diamond Bet System Corporation.</div>
</body>

</html>

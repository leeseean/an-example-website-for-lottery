



/****/
String.prototype.trim = function() {
    return this.replace(/(^\s+)|(\s+$)/g, "");
};

/**
 * 登录信息验证
 */
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
	
		var rp = /^[a-zA-Z0-9]{6,12}$/;//密码规范
	/*if (!rp.test(p)) {
		alert("密码不符合规范");
		document.getElementById("password").focus();
		return false;
	} */

	
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

	//$("#password").val(hex_sha1($("#password").val()).toUpperCase());
	return true;
}

function mobile_login() {
	if (!sendForm())
		return false;
	var dates = $("#loginForm").serialize();
	$.ajax({
		url : rootPath+"/login",
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
				window.location.href = rootPath + "/m/main";
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







function mobile_loginOut(){
	$.ajax({
		    url: rootPath+"/loginOut",
		    type: "post",
		    data: null,
		    timeout : 30000,
		    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		   	dataType:"json",
		    error: function(){
		        alert("用户退出系统出错~");
		    },
		    success: function(obj){
		    	if(obj.rs){
		    		window.location.href=rootPath+"/m/main";
		    	}else{
		    		 alert("用户退出系统出错~");
		    	}
			}
		});
}

function Go(url, code){
	$.ajax({
		url: rootPath+"/valid/checkflatsatus",
		type: "post",
		data : {"code":code},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		success : function(obj) {
			if (obj.datas.status) {
				alert(obj.datas.msg);
				return;
			}else{
				window.location.href=url;
			}
		}
	});
}

	$("#loginName").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#password")[0].focus();
		}
	});
	$("#password").keydown(function(event) {
		if (event.keyCode == 13) {
			$("#verifycode")[0].focus();
		}
	});

	$("#verifycode").keydown(function(event) {
		if (event.keyCode == 13) {
			login();
		}
	});

/**
 * 注册页面跳转
 */
function go_register()
{
	$.ajax({
		url: rootPath+"/m/register/checkParam",
		type: "post",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		success : function(obj) {
			
			if (obj.rs) {
				window.location.href = rootPath + "/m/register/goRegister";
			}else{
				window.location.href = rootPath + "/m/main";
			}
		}
	});
}


function registerForm() 
{
	if (!CheckRegForm())
		return false;
	var dates = $("#regForm").serialize();
	$("#submitinfo").hide();
	$("#submitmsg").show();
	$.ajax({
		url : rootPath+"/m/register/doRegister",
		type: "post",
		data : dates,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		error : function() {
			alert("用户注册网络连接出错~");
			window.location.reload();
		},
		success : function(obj) {
			if(obj.rs)
			{
				window.location.href=rootPath + "/m/main";
			}
			else
			{
				alert(obj.msg);
				$("#submitinfo").show();
				$("#submitmsg").hide();
			}
		}
	});
}

/**
 * 代理账号校验
 * @param {} obj
 */
function validUserAgent(obj){
	var userAgent = $(obj).val();
	if(userAgent == ""){
		$(obj).val("888");
		return true;
	}
	if(validNum(userAgent)){
		alert("请输入有效的代理账号");
		return false;
	}
	return true;
}

/**
 * 纯数字校验
 * @param {} num
 * @return {}
 */
function validNum(num){
	var re = /[^\d]/g;
	return re.test(num);
}

//验证注册用户输入
function CheckRegForm() 
{
	var userName = $("#userName").val();
	if(!CheckUserName(userName))
	{
		alert("请正确输入账号！");
		return false;
	}
	
	var userPassword = $("#userPassword").val();
	var ruserPassword = $("#ruserPassword").val();
	if(!CheckUserPassword(userPassword) || !CheckUserPassword(ruserPassword))
	{
		alert("请正确输入密码！");
		return false;
	}
	
	if(userPassword != ruserPassword)
	{
		alert("两次密码输入不一致！");
		return false;
	}
	
	if(!validUserAgent($("#userAgent"))){
		return false;
	}
	
	var userRealName = $("#userRealName").val();
	if(!CheckUserRealName(userRealName))
	{
		alert("请正确输入真实姓名！");
		return false;
	}
	
	var birthday = $("#birthday").val();
	var swBirthday_1 = $("#swBirthday_1").val();
	if(birthday && birthday == "")
	{
		alert("请输入生日时间");
		return;
	}
	
	if(swBirthday_1 && swBirthday_1 == '1')
	{
		if(birthday == "")
		{
			alert("请输入生日时间");
			return;
		}
	}
	
	var swmobile_1 = $("#swmobile_1").val();
	var userMobile = $("#userMobile").val();
	
	if(userMobile && userMobile != "")
	{
		if(!CheckUserMobile(userMobile))
		{
			alert("请正确输入手机号码！");
			return false;
		}
	}
	
	if(swmobile_1 && swmobile_1 == '1')
	{
		if(!CheckUserMobile(userMobile))
		{
			alert("请正确输入手机号码！");
			return false;
		}
	}
	
	var userQq = $("#userQq").val();
	var swQq_1 = $("#swQq_1").val();
	
	if(userQq && userQq != "")
	{
		if(!CheckUserQq(userQq))
		{
			alert("请正确输入用户QQ！");
			return false;
		}
	}
	
	if(swQq_1 && swQq_1 == '1')
	{
		if(!CheckUserQq(userQq))
		{
			alert("请正确输入用户QQ！");
			return false;
		}
	}
	
	var userMail = $("#userMail").val();
	var swMail_1 = $("#swMail_1").val();
	
	if(userMail && userMail == '1')
	{
		if(!CheckUserEmail(userMail))
		{
			alert("请正确输入邮箱！");
			return false;
		}
	}
	
	if(swMail_1 && swMail_1 == '1')
	{
		if(!CheckUserEmail(userMail))
		{
			alert("请正确输入邮箱！");
			return false;
		}
	}
	
	var userWithdrawPassword = $("#userWithdrawPassword").val();
	if(!CheckUserWithdrawPassword(userWithdrawPassword))
	{
		alert("请正确输入提款密码！");
		return false;
	}
	return true;
}

function CheckUserName(val) {
	var re = /^[a-z0-9]{4,10}$/g;
	var valid = re.test(val);
	return valid;
}

function CheckUserRealName(val){
	var re = /^[\u4E00-\u9FA5]+$/g;
	var valid = re.test(val);
	return valid;
}

function CheckUserPassword(val) {
	var re = /^[a-z0-9]{6,12}$/i;
	var valid = re.test(val);
	return valid;
}

function CheckUserWithdrawPassword(val){
	var re = /^[0-9]{4}$/i;
	var user_withdraw_password = re.test(val);
	if (!user_withdraw_password) {
		return false;
	}
	return true;
}

function CheckUserMobile(val) {
	var re = /^1[3456789]\d{9}$/i;
	var valid = re.test(val);
	return valid;
}

function CheckUserEmail(val) {
	var re = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	var valid = re.test(val);
	return valid;
}

function CheckUserQq(val) {
	var re = /^\d{4,15}$/i;
	var valid = re.test(val);
	return valid;
}

function validRegisterUserName(object){
	var userName = encodeURI($(object).val());
	if(userName == "")
		return;
	$.ajax( {
		url : rootPath+"/valid/registerUserName",
		type : "POST",
		data : {"userName":userName},
		dataType : "json",
		timeout:30000,
		success : function(data) {
			if (!data.rs) {
				$("#userName").val("");
				$("#userName").focus();
				alert("账号已被注册");
			}
		}
	});
 
}

function charSize(obj,size)
{
	if(obj.value.length > size)
		obj.value = obj.value.substr(0,size);
}

function checkFlatSatus(url,code){
	$.ajax({
		url : rootPath+"/valid/checkflatsatus",
		type: "post",
		data : {"code":code},
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		timeout : 30000,
		dataType :"json",
		cache : false,
		async:false,
		error:function(){
			alert("网络连接有问题!");
		},
		success : function(obj) {
			if (obj.datas.status) {
				alert(obj.datas.msg);
				reFlag = false;
				return;
			}
			window.location.href = url;
		}
	}); 
}

/**进入会员中心后 首次获取消息总条数**/

var initMsgCount = 0;

function GetRndUserID() {
	var x = "123456789poiuytrewqasdfghjklmnbvcxzQWERTYUIPLKJHGFDSAZXCVBNM";
	var tmp = "uu";
	for ( var i = 0; i < 6; i++) {
		tmp += x.charAt(Math.ceil(Math.random() * 100000000) % x.length);
	}
	return tmp;
}

function SetTxtVal(obj, v) {
	document.getElementById(obj).value += v;
}

function Backspace(obj) {
	s = document.getElementById(obj).value;
	document.getElementById(obj).value = s.substr(0, s.length - 1);
}

function Clear(obj) {
	document.getElementById(obj).value = "";
}

var CharKey = [ "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s",
		"d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m" ];
CharKey.sort(function() {
	return Math.random() > 0.5 ? -1 : 1;
});

function CheckTestUserName() {
	var re = /^[a-z0-9]{1,12}$/g;
	var tag = document.getElementById("testLoginUserName");
	var user_byname = re.test(tag.value);

	if (!user_byname) {
		tag.focus();
		return false;
	}
	return true;
}


function validTestPassword1(object){
	var valid = CheckUserPassword(object);

	var id = $(object).attr("id");
	if(valid){
		$("#"+id+"Info").html("<img src='"+root+"/web/ybb/assets/img/gou.gif'>");
		return true;
	}else{
		$("#"+id+"Info").html("<font color=#ff0000>请输入有效的密码</font>");
		return false;
	}
}

function validTestPassword2(object){
	var valid = CheckUserPassword(object);

	var id = $(object).attr("id");
	if(valid&($(object).val()==$("#password1").val())){
		$("#"+id+"Info").html("<img src='"+root+"/web/ybb/assets/img/gou.gif'>");
		return true;
	}else{
		$("#"+id+"Info").html("<font color=#ff0000>请输入有效确认密码</font>");
		return false;
	}
}
var testRegFlag=false;
function registerTestForm() {
	var password1 = document.getElementById("password1").value;
	var password2 = document.getElementById("password2").value;
	if(password1==''){
		$("#password1Info").html("<font color=#ff0000>请输入密码</font>");
		return;
	}
	if(password2==''){
		$("#password2Info").html("<font color=#ff0000>请输入密码</font>");
		return;
	}
	if(password1 !=password2){
		$("#password2Info").html("<font color=#ff0000>两次输入的密码不一致</font>");
		return;
	}
	var testUserName = document.getElementById("testUserName").value;
	if(!$("#agree").is(':checked')){
		alert("请同意开户协议");
		return;
	}
	if(testRegFlag){
		return;
	}
	testRegFlag = true;
	var reFlag = false;
	$.ajax({
		url : rootPath+"/test/register",
		type: "post",
		data : {"testUserName":testUserName,"password1":password1,"password2":password2},
		timeout : 30000,
		dataType :"json",
		cache : false,
		async:false,
		error : function() {
			testRegFlag = false;
			alert("测试用户注册网络连接出错~");
			window.location.reload();
		},
		success : function(obj) {
			if (obj.rs) {
				var userName = obj.datas.userName;
				$("#testUserName").val(userName);
				$("#testUserNameSpan").html(userName);
				reFlag = true;
			}else{
				alert(obj.msg);
			}
			testRegFlag = false;
			$("#password1").val("");
			$("#password2").val("");
			$("#testverifycode").val("");

		}
	});
	if(reFlag){
		comeToOpen(rootPath+"/test/forwardGame",window.screen.width,window.screen.height,0,0,'game','ag');
	}
}

var loginTestFlag=false;
function loginTestForm() {

	var testLoginPwd = document.getElementById("testLoginPwd").value;
	if(testLoginPwd==null || testLoginPwd==''){
		$("#testLoginPwd1Info").html("<font color=#ff0000>请输入密码</font>");
		return;
	}

	var testLoginUserName = document.getElementById("testLoginUserName").value;
	if(testLoginUserName==null || testLoginUserName==''){
		$("#testLoginUserNameInfo").html("<font color=#ff0000>请输入账号</font>");
		return;
	}
	var testverifycode = document.getElementById("testverifycode").value;
	if(testLoginUserName==null || testLoginUserName==''){
		$("#testverifycodeInfo").html("<font color=#ff0000>请输入验证码</font>");
		return;
	}

	var re = /[^\d]/g;
	var valid = re.test(testverifycode);
	if(testverifycode!='' && valid){
		$("#"+id+"Info").html("<font color=#ff0000>请输入正确的验证码</font>");
		return;
	}
	if(loginTestFlag){
		return;
	}
	loginTestFlag = true;
	var reFlag = false;
	$.ajax({
		url : rootPath+"/test/login",
		type: "post",
		data : {"testLoginUserName":testLoginUserName,"testLoginPwd":testLoginPwd,"testverifycode":testverifycode},
		timeout : 30000,
		dataType :"json",
		cache : false,
		async:false,
		error : function() {
			loginTestFlag = false;
			alert("测试用户登录网络连接出错~");
			window.location.reload();
		},
		success : function(obj) {
			if (obj.rs) {
				reFlag = true;
				$("#testLoginPwd").val("");
				$("#testLoginUserName").val("");
				$("#testverifycode").val("");
			}else{
				alert(obj.msg);
			}
			loginTestFlag = false;
		}
	});
	if(reFlag){
		comeToOpen(rootPath+"/test/forwardGame",window.screen.width,window.screen.height,0,0,'game','ag');
	}
}


// 验证注册用户输入
function CheckRegForm() {

	var tag1=validRegisterUserNameForSubmit(document.getElementById("userName1"));

	var tag2=validRegisterPassword(0,document.getElementById("userPassword1"));
	var tag3=validRegisterRepwd(0,document.getElementById("rePwd"));
	var tag4=validRegisterUserRealName(0,document.getElementById("userRealName"));
	var tag5=validRegisterUserMobile(0,document.getElementById("userMobile"));
	var tag6= validRegisterUserQq(0,document.getElementById("userQq"));
	var tag8= validRegisterBirthday(0,document.getElementById("birthday"));

	var tag7=false;
	if(document.getElementById("pwd1").value!='-'&&document.getElementById("pwd2").value!='-'&&document.getElementById("pwd3").value!='-'&&document.getElementById("pwd4").value!='-'){
		$("#withdrawInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		tag7=true;
		$("#userWithdrawPassword").val(document.getElementById("pwd1").value+document.getElementById("pwd2").value+document.getElementById("pwd3").value+document.getElementById("pwd4").value);
	}else{
		$("#withdrawInfo").html("<font color=#ff0000>请输入有效的取款密码</font>");
		tag7=false;
	}
	if(!$("#agree").attr("checked")){
		alert("请选择开户协议");
		return false;
	}
	if(tag1&tag2&tag3&tag4&tag5&tag6&tag7&tag8){
		 if($("#agree").attr("checked")){
			 return confirm("是否确定注册？");
		 }else{
			 return false;
		 }
	}else{
		return false;
	}
}

function registerForm() {
	if (!CheckRegForm())
		return false;
	var dates = $("#regForm").serialize();
	$.ajax({
		url : rootPath+"/doRegister",
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
			//重启服务器，令牌失效，重新获取令牌
			if (typeof (obj) == 'undefined' || obj == null) {
				window.location.reload();
			}
			if (obj.rs) {
				 window.location.href = rootPath+"/loginRule";
			} else {
				alert(obj.msg);
				$("#_form_token_uniq_id").val(obj.token.token);
			}
		}
	});
	return false;
}

function CheckUserWithdrawPassword(){
	var re = /^[0-9]{4}$/i;
	var user_withdraw_password = re.test(document.getElementById("userWithdrawPassword").value);
	if (!user_withdraw_password) {
		return false;
	}
	return true;
}

function CheckUserBankCard(){
	var re = /^[0-9]+$/i;
	var user_bank_card = re.test(document.getElementById("userBankCard").value);
	if (!user_bank_card) {
		return false;
	}
	return true;
}

function CheckUserBankType(){
	if(document.getElementById("userBankType").value==-1){
		return false;
	}
	return true;
}

function CheckUserRealName(object){
	var re = /^[\u4E00-\u9FA5]+$/g;
	var valid = re.test(object.value);
	return valid;
}

function CheckUserName(object) {
	var re = /^[a-z0-9]{4,10}$/g;
	var valid = re.test(object.value);
	return valid;
}

function CheckBirthday(object) {
	if(object.value!=''){
		return true;
	}
	 return false;
}

function CheckUserByname() {
	var re = /^[a-z0-9]{1,8}$/g;
	var tag = document.getElementById("userByname");
	var user_byname = re.test(tag.value);

	if (!user_byname) {
		tag.focus();
		return false;
	}
	return true;
}

function CheckUserPassword(object) {
	var re = /^[a-z0-9]{6,12}$/i;
	var valid = re.test(object.value);
	return valid;
}

function CheckRePwd(object) {
	return $(object).val() == $("#userPassword1").val();
}

function CheckUserMobile(object) {
	var re = /^1[3456789]\d{9}$/i;
	var valid = re.test(object.value);
	return valid;
}

function CheckUserEmail(object) {
	var re = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	var valid = re.test(object.value);
	return valid;
}

function CheckUserQq(object) {
	var re = /^\d{4,15}$/i;
	var valid = re.test(object.value);
	return valid;
}

function validBirthdayForSubmit(object){
	if(typeof object === 'undefined'){
		return true;
	}
	if(object == null){
		return true;
	}

	if(valid){
		$("#birthdayInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif' >");
		return true;
	}else{
		$("#birthdayInfo").html("<font color=#ff0000>请输入有效的生日</font>");
		return false;
	}

}


function validRegisterUserNameForSubmit(object){
	var valid = CheckUserName(object);
	if(valid){
		$("#userNameInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif' >");
		return true;
	}else{
		$("#userNameInfo").html("<font color=#ff0000>请输入有效的帐号</font>");
		return false;
	}

}

/**
 * 验证帐号
 *
 * @param object
 * @return
 */
function validRegisterUserName(isonblur,object){
	var userName = encodeURI($(object).val());
	if(isonblur==1){
		if(userName==""){
			$("#userNameInfo").html("<font color=#ff0000>请输入帐号</font>");
			return false;
		}
	}
	var valid = CheckUserName(object);
	if(!valid){
		$("#userNameInfo").html("<font color=#ff0000>请输入有效的帐号</font>");
		return false;
	}


	$.ajax( {
		url : rootPath+"/valid/registerUserName",
		type : "POST",
		data : {"userName":userName},
		dataType : "json",
		timeout:30000,
		success : function(data) {
			if (data.rs) {
				$("#userNameInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
			}else{
				//$("#userName1").val("");
				$("#userName1").focus();
				//$("#_form_token_uniq_id").val(obj.token.token);
				$("#userNameInfo").html("<font color=#ff0000>已经存在,请换帐号</font>");
			}
		}
	});

}

/**
 * 验证密码
 *
 * @param isonblur
 * @param object
 * @return
 */
function validRegisterPassword(isonblur,object){
	var value = encodeURI($(object).val());
	if(isonblur==1){
		if(value==""){
			return false;
		}
	}

	var valid = CheckUserPassword(object);
	if(valid){
		$("#passwordInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		return true;
	}else{
		$("#passwordInfo").html("<font color=#ff0000>请输入有效的密码</font>");
		return false;
	}
}

/**
 * 验证确认密码
 *
 * @param isonblur
 * @param object
 * @return
 */
function validRegisterRepwd(isonblur,object){
	var value = encodeURI($(object).val());


	if(isonblur==1){
		if(value==""){
			return false;
		}
	}



	var valid = CheckRePwd(object);
	if(valid&&value!=''){
		$("#rePwdInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		return true;
	}else{
		$("#rePwdInfo").html("<font color=#ff0000>请输入有效的密码</font>");
		return false;
	}

}

/**
 * 验证用户真名
 *
 * @param isonblur
 * @param object
 * @return
 */
function validRegisterUserRealName(isonblur,object){
	var value = encodeURI($(object).val());
	if(isonblur==1){
		if(value==""){
			return false;
		}
	}

	var valid = CheckUserRealName(object);
	if(valid){
		$("#userRealNameInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		return true;
	}else{
		$("#userRealNameInfo").html("<font color=#ff0000>请输入有效的名字</font>");
		return false;
	}
}

/**
 * 验证手机号码
 *
 * @param isonblur
 * @param object
 * @return
 */
function validRegisterUserMobile(isonblur,object){
	if(typeof object === 'undefined'){
		return true;
	}
	if(object == null){
		return true;
	}
	var value = encodeURI($(object).val());
	var needed_userMobile = document.getElementById('needed_userMobile').value;
	if(needed_userMobile=='1'){
		if(value==''){
			$("#userMobileInfo").html("<font color=#ff0000>请输入有效的手机号码</font>");
			return false;
		}
	}else{
		if(value==''){
			return true;
		}
	}


	var valid = CheckUserMobile(object);
	if(valid){
		$("#userMobileInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		return true;
	}else{
		$("#userMobileInfo").html("<font color=#ff0000>请输入有效的手机号码</font>");
		return false;
	}
}

/**
 * 验证郵箱
 *
 * @param isonblur
 * @param object
 * @return
 */
function validRegisterUserEmail(isonblur,object){
	if(typeof object === 'undefined'){
		return true;
	}
	if(object == null){
		return true;
	}

	var value = encodeURI($(object).val());
	var needed_userEmail = document.getElementById('needed_userEmail').value;
	if(needed_userEmail=='1'){
		if(value==''){
			$("#userEmailInfo").html("<font color=#ff0000>请输入有效的邮箱地址</font>");
			return false;
		}
	}else{
		if(value==''){
			return true;
		}
	}

	var valid = CheckUserEmail(object);
	if(valid){
		$("#userEmailInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		return true;
	}else{
		$("#userEmailInfo").html("<font color=#ff0000>请输入有效的邮箱地址</font>");
		return false;
	}
}


/**
 * 验证QQ
 *
 * @param isonblur
 * @param object
 * @return
 */
function validRegisterUserQq(isonblur,object){
	if(typeof object === 'undefined'){
		return true;
	}
	if(object == null){
		return true;
	}
	var value = encodeURI($(object).val());
	var needed_userEmail = document.getElementById('needed_userQq').value;
	if(needed_userEmail=='1'){
		if(value==''){
			$("#userQqInfo").html("<font color=#ff0000>请输入有效的QQ号码</font>");
			return false;
		}
	}else{
		if(value==''){
			return true;
		}
	}

	var valid = CheckUserQq(object);
	if(valid){
		$("#userQqInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		return true;
	}else{
		$("#userQqInfo").html("<font color=#ff0000>请输入有效的QQ号码</font>");
		return false;
	}
}

function validRegisterBirthday(isonblur,object){
	if(typeof object === 'undefined'){
		return true;
	}
	if(object == null){
		return true;
	}
	var value = encodeURI($(object).val());
	var needed_birthday = document.getElementById('needed_birthday').value;

	if(needed_birthday=='1'){
		if(value==''){
			$("#birthdayInfo").html("<font color=#ff0000>请输入有效的生日</font>");
			return false;
		}
	}else{
		if(value==''){
			return true;
		}
	}

	if(value!=''){
		$("#birthdayInfo").html("<img src='"+root+"/web/ybb/common/images/gou.gif'>");
		return true;
	}

}



function comeToOpen(url,width,height,left,top,name,code){
	var temp = "menubar=no,toolbar=no,directories=no,scrollbars=yes,resizable=no";
	if (width) {
		temp += ',width=' + width;
	} else {
		width = 1024;
	}
	if (height) {
		temp += ',height=' + height;
	} else {
		height = 600;
	}
	if (left) {
		temp += ',left=' + left;
	} else {
		temp += ',left='+ Math.round((window.screen.width - parseInt(width)) / 2);
	}
	if (top) {
		temp += ',top=' + top;
	} else {
		temp += ',top='+ Math.round((window.screen.height - parseInt(height)) / 2);
	}
	if(typeof(name)=="undefined"){
		name="";
	}
	if(name=="game")
	{
		//alert(temp);
		var obj=window.open (url,name,temp);
		obj.moveTo(0,0);
		obj.resizeTo(window.screen.availWidth,window.screen.availHeight);
		return;
	}else if(name=="message"){
		window.open (url,name,temp);
		return;
	}
	if(name=="game") temp+=',fullscreen=1';
	window.open(url,name,temp);
}

function validTestLoginName(object){
	var valid = CheckTestUserName(object);
	var id = $(object).attr("id");
	if(valid){
		$("#"+id+"Info").html("<img src='"+root+"/web/ybb/assets/img/gou.gif'>");
		return true;
	}else{
		$("#"+id+"Info").html("<font color=#ff0000>请输入有效的帐号</font>");
		return false;
	}
}


function winOpen(url,width,height,left,top,name,code){

	var reFlag = true;
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
		}
	});

	if(reFlag){
		comeToOpen(url,width,height,left,top,name,code);
	}


}

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

		var rp = /^[a-zA-Z0-9]{6,12}$/;//密码规范
/* 	if (!rp.test(p)) {
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

function login() {
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
				window.location.href = rootPath+"/loginRule";
			} else {
				$("#password").val("");
				$("#verifycode").val("");
				createCode();
				alert(obj.msg);
				$("#_form_token_uniq_id").val(obj.token.token);
			}
		}
	});
}



function loginOut(){
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
		    		window.location.href=rootPath+"/index";
		    	}else{
		    		 alert("用户退出系统出错~");
		    	}
			}
		});
}


/**
function refreshUserMoney(){
	$.ajax({
	    url: rootPath+"/valid/refreshUserMoney",
	    type: "post",
	    data: null,
	    timeout : 30000,
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   	dataType:"json",
	    error: function(){
	        //alert("刷新金额出错~");
	    },
	    success: function(obj){
	    	if(obj.rs){
	    		var data= obj.datas;
	    		$("#userMoneyId").html(data.userMoney+"RMB");
	    		$("#userMessageId").html(data.msgTotal);
	    		//公告
	    	}
		}
	});

}
**/

function refreshUserMoney(){
	$.ajax({
	    url: rootPath+"/valid/refreshUserMoney",
	    type: "post",
	    data: null,
	    timeout : 30000,
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   	dataType:"json",
	    error: function(){
	        //alert("刷新金额出错~");
	    },
	    success: function(obj){
	    	if(obj.rs){
	    		var data= obj.datas;
	    		$("#userMoneyId").html(data.userMoney+"RMB");
	    		$("#userMessageId").html(data.msgTotal);
	    		//公告
	    		if(data.msgTotal == 0)
    			{
	    			initMsgCount = 0;
    			}
	    		//如果成立,说明有新消息
	    		if(data.msgTotal > initMsgCount)
	    		{
	    			initMsgCount++;
	    			var player = document.getElementById("player");
					player.play();
	    		}
	    		else
	    		{
	    			initMsgCount = data.msgTotal;
	    		}

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
	 * 自定义弹窗
	 */
	function winOpenMessage(A,B,C) {
		window.open(A, B, C);
	}

	function formatMoney(number, places, symbol, thousand, decimal) {
		number = number || 0;
        places = !isNaN(places = Math.abs(places)) ? places : 2;
        symbol = symbol !== undefined ? symbol : "$";
        thousand = thousand || ",";
        decimal = decimal || ".";
        var negative = number < 0 ? "-" : "",
            i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
            j = (j = i.length) > 3 ? j % 3 : 0;
        return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
    }
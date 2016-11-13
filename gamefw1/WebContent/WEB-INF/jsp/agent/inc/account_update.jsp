<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="UTF-8">
<title></title>
 <%@include file="common.jsp"%>
<script type="text/javascript">

function CheckUserByname(object) {
	var re = /^[a-z0-9]{1,8}$/g;
	var valid = re.test(object.value);
	return valid;
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


function validUserByname(object){
	var value = encodeURI($(object).val());
	if(value==''){
		return true;
	}
	var valid = CheckUserByname(object);
	var id = $(object).attr("id");
	
	
	if(valid){
		$("#"+id+"Info").html("<img src='"+root+"/web/ybb/assets/images/gou.gif'>");
		return true;
	}else{
		$("#"+id+"Info").html("<font color=#ff0000>请输入有效的手机号码</font>");
		return false;
	}
}

function validUserMobile(object){
	var value = encodeURI($(object).val());
	if(value==''){
		return true;
	}
	var valid = CheckUserMobile(object);
	var id = $(object).attr("id");
	if(valid){
		$("#"+id+"Info").html("<img src='"+root+"/web/ybb/assets/images/gou.gif'>");
		return true;
	}else{
		$("#"+id+"Info").html("<font color=#ff0000>请输入有效的手机号码</font>");
		return false;
	}
}


function validUserEmail(object){
	var value = encodeURI($(object).val());
	if(value==''){
		return true;
	}
	var valid = CheckUserEmail(object);
	var id = $(object).attr("id");
	if(valid){
		$("#"+id+"Info").html("<img src='"+root+"/web/ybb/assets/images/gou.gif'>");
		return true;
	}else{
		$("#"+id+"Info").html("<font color=#ff0000>请输入有效的邮箱地址</font>");
		return false;
	}
}

function validPassword(object){
	var value = encodeURI($(object).val());
	if(value==""){
		return false;
	}
	var id = $(object).attr("id");
	var valid = CheckUserPassword(object);
	if(valid){
		$("#"+id+"Info").html("<img src='"+root+"/web/ybb/assets/images/gou.gif'>");
		return true;
	}else{
		$("#"+id+"Info").html("<font color=#ff0000>请输入有效的密码</font>");
		return false;
	}
}

function updateAccount(){

	//var userByName = $("#userByName").val();
	var oldPassworld = $("#oldPassworld").val();
	var newPassworld1 = $("#newPassworld1").val();
	var newPassworld2 = $("#newPassworld2").val();
	var userMobile = $("#userMobile").val();
	var userMail = $("#userMail").val();
	if(newPassworld1!='' && newPassworld2!='' && newPassworld1!=newPassworld2){
		$("#newPassworld2Info").html("<font color=#ff0000>二次输入密码不一致</font>");
		return;
	}
	
	//var flag1=validUserByname(document.getElementById("userByName"));
	var flag2=validPassword(document.getElementById("oldPassworld"));
	var flag3=validPassword(document.getElementById("newPassworld1"));
	var flag4=validPassword(document.getElementById("newPassworld2"));
	var flag5=validUserMobile(document.getElementById("userMobile"));
	var flag6=validUserEmail(document.getElementById("userMail"));
	
	if(flag2&&flag3&&flag4&&flag5&&flag6){
		$.ajax({
			url : rootPath+"/agent/doAccountUpdate",
			type: "post",
			data : {"oldPassworld":oldPassworld,"newPassworld1":newPassworld1,"newPassworld2":newPassworld2,"userMobile":userMobile,"userMail":userMail},
			timeout : 30000,
			dataType :"json",
			cache : false,
			error:function(){
				alert("更新信息出错!");
			},
			success : function(obj) {
				if(obj.rs){
					alert("更新用户信息成功,请重新登录!"); 
					window.location.reload();
				}else{
					alert(obj.msg); 
				}
			}
		}); 
	}
	
}


</script>

</head>

<body class="ag-frame-content font-hei">
	<div class="ag-wrap ag-edit">
		<table width="100%" class="ag-form">
			<thead>
				<tr>
					<th colspan="19">
						<div class="ag-form-head cf"><h2 class="col-one ele-title l">账户信息</h2></div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="ag-form-title pr10 b">旧密码</td>
					<td class="pl10 al">
						<input type="password" class="ui-write" id="oldPassworld" name="oldPassworld" maxlength="12" onblur="validPassword(this)" autocomplete="off" >
						<font color="#ff0000">*</font> <span id="oldPassworldInfo"></span>
					</td>
				</tr>
				<tr>
					<td class="ag-form-title pr10 b">新密码</td>
					<td class="pl10 al">
						<input type="password" class="ui-write" id="newPassworld1" name="newPassworld1" maxlength="12" onblur="validPassword(this)" autocomplete="off" >
						<font color="#ff0000">*</font><span id="newPassworld1Info"></span>
					</td>
				</tr>
				<tr>
					<td class="ag-form-title pr10 b">确认新密码</td>
					<td class="pl10 al">
						<input type="password" class="ui-write" id="newPassworld2" name="newPassworld2" maxlength="12" onblur="validPassword(this)" autocomplete="off" >
						<font color="#ff0000">*</font><span id="newPassworld2Info"></span>
					</td>
				</tr>
				<tr>
					<td class="ag-form-title pr10 b">电话号码</td>
					<td class="pl10 al">
						<input type="text" class="ui-write" id="userMobile" name="userMobile" value="${webUser.userMobile}" onblur="validUserMobile(this)" maxlength="11">
						<span id="userMobileInfo"></span>
					</td>
				</tr>
				<tr>
					<td class="ag-form-title pr10 b">邮箱地址</td>
					<td class="pl10 al">
						<input type="text" class="ui-write" id="userMail" name="userMail" value="${webUser.userMail}" onblur="validUserEmail(this)" maxlength="20">
						<span id="userMailInfo"></span>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="ag-form-foot cf">
							<div class="col-two col-affirm ac">
								<button class="ui-btn" onclick="updateAccount();return false;">确定</button>
								<button onclick="javascript :history.back(-1);" class="ui-btn ml10">返回</button>
							</div>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
<%@include file="comm_foot.jsp"%>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>修改密码</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page">
    <script type="text/javascript">
function CheckUserPassword(pwd) 
{
	var re = /^[a-z0-9]{6,12}$/i;
	var user_password = re.test(pwd);
	if (!user_password) 
		return false;
	return true;
}

function checkParam(oldPwd,newPwd,renewPwd)
{
	/** 1.数据校验* */
    if (!CheckUserPassword(oldPwd.val())) {
		alert("请输入规范的原登录密码");
		return false;
	}
	
	if (!CheckUserPassword(newPwd.val())) {
		alert("请输入规范的新登录密码");
		return false;
	}
	
	if (!CheckUserPassword(renewPwd.val())) {
		alert("请输入规范的确认密码");
		return false;
	}
	
	if(oldPwd.val() == newPwd.val())
	{
		alert("新密码不能与旧密码一致");
		return false;
	}
	
	if (newPwd.val()!=renewPwd.val()) {
		alert("两次输入的新密码不一样");
		return false;
	}
	return true;
}

function checkEditPwdForm()
{
	var oldPwd = $("#member-psw-11");
    var newPwd = $("#member-psw-12");
    var renewPwd = $("#member-psw-13");
	if(!checkParam(oldPwd,newPwd,renewPwd))
		return;
	$.ajax( {
		url : rootPath+"/member/doLoginPwd",
		type : "POST",
		data : {oldPwd:oldPwd.val(),newPwd:newPwd.val(),renewPwd:renewPwd.val()},
		dataType : "json",
		timeout:30000,
		success : function(data) 
		{
			alert(data.msg);
			//$("#msg").text(data.msg);
			//if(data.rs)
			//	$("#titleMsg").text("操作成功");
			//else
			//	$("#titleMsg").text("操作失败");
			//$.mobile.changePage("#memberPswInfo", {role: "dialog"});
			//alert(data.msg);
			oldPwd.val("");
			newPwd.val("");
			renewPwd.val("");
		}
	});
 }
</script>

<script type="text/javascript">
  function CheckUserWPassword(pwd) 
  {
  	var re = /^[0-9]{4}$/i;
  	var user_password = re.test(pwd);
  	if (!user_password) 
  		return false;
  	return true;
  }
  
  function checkWParam(oldPwd,newPwd,renewPwd)
  {
  	/** 1.数据校验* */
      if (!CheckUserWPassword(oldPwd.val())) {
  		alert("请输入规范的原提款密码");
  		return false;
  	}
  	
  	if (!CheckUserWPassword(newPwd.val())) {
  		alert("请输入规范的新提款密码");
  		return false;
  	}
  	
  	if (!CheckUserWPassword(renewPwd.val())) {
  		alert("请输入规范的确认密码");
  		return false;
  	}
  	
  	if(oldPwd.val() == newPwd.val())
  	{
  		alert("新密码不能与旧密码一致");
  		return false;
  	}
  	
  	if (newPwd.val()!=renewPwd.val()) {
  		alert("两次输入的新密码不一样");
  		return false;
  	}
  	return true;
  }
  
  function checkEditWPwdForm()
  {
  	var oldPwd = $("#member-wpsw-11");
    var newPwd = $("#member-wpsw-12");
    var renewPwd = $("#member-wpsw-13");
  	if(!checkWParam(oldPwd,newPwd,renewPwd))
  		return;
  	$.ajax( {
  		url : rootPath+"/member/doWithdrawPwd",
  		type : "POST",
  		data : {oldPwd:oldPwd.val(),newPwd:newPwd.val(),renewPwd:renewPwd.val()},
  		dataType : "json",
  		timeout:30000,
  		success : function(data) 
  		{
  			//$("#wmsg").text(data.msg);
  			alert(data.msg);
  			//$.mobile.changePage("#memberwPswInfo", {role: "dialog"});
  			//$.mobile.changePage("#memberwPswInfo", {role: "dialog"});
  			oldPwd.val("");
  			newPwd.val("");
  			renewPwd.val("");
  		}
  	});
   }
  </script>

    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>登录密码</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-form ybm-member-psw">
      <div data-role="fieldcontain">
        <label for="member-psw-11">旧密码：</label>
        <input type="password" name="password" id="member-psw-11">
      </div>
      <div data-role="fieldcontain">
        <label for="member-psw-12">新密码：</label>
        <input type="password" name="member-psw-12" id="member-psw-12">
      </div>
      <div data-role="fieldcontain">
        <label for="member-psw-13">确认密码：</label>
        <input type="password" name="member-psw-13" id="member-psw-13">
      </div>
      <div data-role="fieldcontain" class="tac">
        <button id="memberPswConfirm" data-corners="false" data-icon="check" data-theme="b" onclick="checkEditPwdForm()">确定</button>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="psw-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="${ctx }/m/member/updpassword?page=update_pwd"  data-transition="none" class="ui-btn-active ui-state-persist">登录密码</a></li>
          <li><a href="${ctx }/m/member/updpassword?page=update_wpwd" data-transition="none">提款密码</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
  <!-- /page -->
 <!-- 
<div data-role="dialog" id="memberPswInfo" data-close-btn="right">
    <div data-role="header" data-theme="b">
      <h2 id="titleMsg"></h2>
    </div>
    <div data-role="content" class="tac">
      <p id="msg"></p>
    </div>
</div>
 -->

<div data-role="dialog" data-close-btn="none" id="memberPswInfo">
  <div data-role="header">
    <h2 id="titleMsg"></h2>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e" id="msg">
      	${msg}
      </li>
      <li style=" text-align: center;">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="check" data-theme="b">确认</a>
          </fieldset>
      </li>
    </ul>
  </div>
</div>

</body>
</html>

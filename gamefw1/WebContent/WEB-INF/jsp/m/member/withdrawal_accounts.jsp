<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>在线提款</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page" id="withdrawal-accounts">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>收款账户</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
      <div class="withdrawal-accounts accounts-list">
        <ul data-role="listview" data-inset="true" data-corners="false" class="item">
          <li data-role="list-divider" data-theme="e">账户</li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">收款银行</div>
              <div class="ui-block-b">${webUser.userBankType}</div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">银行户名</div>
              <div class="ui-block-b">${webUser.userRealName}</div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">银行账号</div>
              <div class="ui-block-b">${webUser.userBankCard}</div>
            </div>
          </li>
          <li>
            <div class="ui-grid-a">
              <div class="ui-block-a">开户地址</div>
              <div class="ui-block-b">${webUser.userBankAddress}</div>
            </div>
          </li>
        </ul>
        <!-- /item -->
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed">
      <div data-role="navbar">
        <ul>
          <li><a href="#withdrawal-accounts" data-transition="none" class="ui-btn-active ui-state-persist">收款账户</a></li>
          <li><a href="#withdrawal-form" data-transition="none">提款表单</a></li>
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
  <div data-role="page" id="withdrawal-form">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu2" data-transition="none" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>提款表单</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
      <div class="ybm-form deposit-form ui-mini">
        <ul data-role="listview">
          <li data-role="divider" data-theme="e">会员帐号：${webUser.userName}</li>
          <li data-role="divider" data-theme="e">账户余额：${webUser.userMoney}</li>
          <li>
            <div data-role="fieldcontain">
              <label for="money">提款金额</label>
              <input type="number" name="money" id="money" pattern="[0-9]*" placeholder="最低提款额为：100元" maxlength="9" value="100">
            </div>
          </li>
          <!-- /item -->
          <li>
            <div data-role="fieldcontain">
              <label for="userWithdrawPassword">提款密码</label>
              <input type="password" name="userWithdrawPassword" id="userWithdrawPassword" autocomplete="off" maxlength="4">
            </div>
          </li>
          <!-- /item -->
          <li class="tac">
            <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
              <a href="javascript:void(0);" onclick="checkparam();" data-transition="pop" data-role="button" data-mini="false" data-icon="check" data-theme="b">确认</a>
            </fieldset>
          </li>
          <!-- /item -->
        </ul>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed">
      <div data-role="navbar">
        <ul>
          <li><a href="#withdrawal-accounts" data-transition="none">收款账户</a></li>
          <li><a href="#withdrawal-form" data-transition="none" class="ui-btn-active ui-state-persist">提款表单</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu2" class="ybm-panel">
      <%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
  <!-- /page -->
<script type="text/javascript">

function checkparam()
{
	var money = $("#money").val();
	var userWithdrawPassword = $("#userWithdrawPassword").val();
	if(!checkParam(money,userWithdrawPassword))
		return;
	
	$.mobile.changePage("#with_dialog", "pop", true, true);
}

function with_submit()
{
	var money = $("#money").val();
	var userWithdrawPassword = $("#userWithdrawPassword").val();
	if(!checkParam(money,userWithdrawPassword))
		return;
	
	$("#out_sub").hide();
	$("#out_msg").show();
	$.ajax( {
		url : rootPath+"/member/saveWithdraw",
		type : "POST",
		data :{'money':money,'userWithdrawPassword':userWithdrawPassword},
		dataType : "json",
		timeout:30000,
		success : function(data) {
			if(data.rs)
				$("#respMsg").text("操作成功");
			else
				$("#respMsg").text(data.msg);
			$.mobile.changePage("#resp_success", "pop", true, true);
		}
	});
	
}

function checkParam(money,pwd)
{
	if (money < 100)
	{
		alert("最小取款金额:100元");
		return false;
	}
	if(pwd.length != 4)
	{
		alert("请正确输入4位数字密码");
		return;
	}
	return true;
}
</script>
<%--确认弹框 --%>
<div data-role="dialog" data-close-btn="none" id="with_dialog">
  <div data-role="header">
    <h1>取款确认</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e">
       确定取款吗?
      </li>
      <li style=" text-align: center;" id="out_sub">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="javascript:void(0);" data-ajax="true" onclick="with_submit();" data-transition="pop" data-role="button" data-icon="check" data-theme="b">确认</a>
            <a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
          </fieldset>
      </li>
      <li style="display: none;" id="out_msg"><font style="color: red;">请求处理中...请勿关闭页面</font></li>
    </ul>
  </div>
</div>

<div data-role="dialog" data-close-btn="none" id="resp_success">
  <div data-role="header">
    <h1>操作结果</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li data-role="list-divider" data-theme="e" id="respMsg">
      </li>
      <li style=" text-align: center;">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="${ctx }/m/member/goWithdraw" data-role="button" data-ajax="false" data-icon="check" data-theme="b">确认</a>
          </fieldset>
      </li>
    </ul>
  </div>
</div>

</body>

</html>

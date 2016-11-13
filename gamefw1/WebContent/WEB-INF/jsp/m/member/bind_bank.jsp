<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>绑定银行卡</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>绑定银行卡</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-deposit">
    <form action="" id="bank_form" method="post">
      <div class="ybm-form deposit-form ui-mini">
        <ul data-role="listview">
          <li data-role="divider" data-theme="e">会员帐号：${webUser.userName}</li>
          <li data-role="divider" data-theme="e">账户余额：${webUser.userMoney}</li>
          <li>
            <div data-role="fieldcontain">
              <label for="userBankType">收款银行</label>
              <select name="userBankType" id="userBankType">
              	<option value="中国银行">中国银行</option>
				<option value="工商银行">工商银行</option>
				<option value="农业银行">农业银行</option>
				<option value="建设银行">建设银行</option>
				<option value="邮政银行">邮政银行</option>
				<option value="招商银行">招商银行</option>
				<option value="民生银行">民生银行</option>
				<option value="兴业银行">兴业银行</option>
				<option value="中信银行">中信银行</option>
				<option value="交通银行">交通银行</option>
				<option value="华厦银行">华厦银行</option>
				<option value="广发银行">广发银行</option>
				<option value="光大银行">光大银行</option>
				<option value="平安银行">平安银行</option>
				<option value="浦发银行">浦发银行</option>
				<option value="农村信用社">农村信用社</option>
              </select>
            </div>
          </li>
          <!-- /item -->
          <li>
            <div data-role="fieldcontain">
              <label for="userBankCard">银行账号</label>
              <input type="number" name="userBankCard" id="userBankCard" pattern="[0-9]*" placeholder="银行账号">
            </div>
          </li>
          <li>
            <div data-role="fieldcontain">
              <label for="userBankAddress">开户行地址</label>
              <input type="text" name="userBankAddress" id="userBankAddress" placeholder="开户行地址">
            </div>
          </li>
          <li>
            <div data-role="fieldcontain">
              <label for="userWithdrawPassword">资金密码</label>
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
      </form>
    </div>
    <!-- /content -->
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
<script type="text/javascript">

function checkparam()
{
	 var userBankType = $("#userBankType");
	 var userBankCard = $("#userBankCard");
	 var userBankAddress = $("#userBankAddress");
	 var userWithdrawPassword = $("#userWithdrawPassword");
	
	 if(userBankType.val()==""||userBankCard.val()==""||userBankAddress.val()=="" ||userWithdrawPassword.val()=="")
     {
        alert("请填写完整的表单信息！");
        return false;
     }
	 
	 $.mobile.changePage("#bank_dialog", "pop", true, true);
}

function bankSaveCheck()
{
	 var userBankType = $("#userBankType");
	 var userBankCard = $("#userBankCard");
	 var userBankAddress = $("#userBankAddress");
	 var userWithdrawPassword = $("#userWithdrawPassword");
	
	 if(userBankType.val()==""||userBankCard.val()==""||userBankAddress.val()=="" ||userWithdrawPassword.val()=="")
    {
        alert("请填写完整的表单信息！");
        return false;
    }
    $("#out_sub").hide();
	$("#out_msg").show();	
	/** 3.请求* */
	$.ajax({
	    url: rootPath+"/member/saveBackInfo",
	    type: "post",
	    data: $("#bank_form").serialize(),
	    timeout : 30000,
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   	dataType:"json",
	    error: function(){
	        alert("绑定银行卡出错~");
	    },
	    success: function(data){
	    	if(data.rs)
				$("#respMsg").text("操作成功");
			else
				$("#respMsg").text(data.msg);
			$.mobile.changePage("#resp_success", "pop", true, true);
		}
	});
}


</script>
<%--确认弹框 --%>
<div data-role="dialog" data-close-btn="none" id="bank_dialog">
  <div data-role="header">
    <h1>提交确认</h1>
  </div>
  <div data-role="content" class="ybm-order-confirm">
    <ul data-role="listview">
      <li style=" text-align: center;" id="out_sub">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <a href="javascript:void(0);" onclick="bankSaveCheck();" data-transition="pop" data-role="button" data-icon="check" data-theme="b">确认</a>
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

<!-- /page -->
</body>

</html>
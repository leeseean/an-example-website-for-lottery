<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title></title>
  <%@include file="inc/mobile_common.jsp"%>
</head>

<body>
<div data-role="page" class="ybm-account ybm-login">
   <div data-role="header" data-position="fixed">
     <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
     <h1>登录</h1>
   </div>
<div data-role="content" class="ybm-form ui-mini">
  <h1 class="tac">用户登录</h1>
  <form id="loginForm" name="loginForm" action="" method="post">
    <div class="login-form f12">
      <div data-role="fieldcontain">
        <label for="loginName">用戶名：</label>
        <input type="text" placeholder="請輸入您的用戶名" id="loginName" name="loginName">
      </div>
      <div data-role="fieldcontain">
        <label for="password">密碼：</label>
        <input type="password" placeholder="請輸入您的密碼" id="password" name="password">
      </div>
      <div data-role="fieldcontain" class="item-vcode">
        <label for="verifycode">验证码：</label>
        <input type="text" name="verifycode" id="verifycode" size="4" maxlength="4" placeholder="验证码">
        <img src="${resourceRoot}/web/ybb/assets/images/yzm.gif" id="img_validateCode" onclick="javascript:createCode();" style=" position: relative; z-index: 7; top: 1px; margin-left: 1px; height: 32px; vertical-align: middle;">
      </div>
      <style>
        @media screen and (max-width: 480px){
          .item-vcode div.ui-body-c {
            float: left;
          }
          .ybm-account .ybm-form .item-vcode.ui-field-contain {
            padding-bottom: 10px;
          }
        }
      </style>
      <div class="ui-grid-a row-action">
        <div class="ui-block-a"><a id="loginBtn" href="javascript:void(0);" onclick="return mobile_login();" data-role="button" data-theme="b">登錄</a></div>
        <div class="ui-block-b"><a href="javascript:void(0);" onclick="go_register();" data-position-to="window" data-role="button" data-theme="c" class="btn-reg">免費開戶</a></div>
      </div>
      <div data-role="fieldcontain" class="item-hohome" style="    text-align: right;
    margin-top: 10px;"><a href="${ctx }/m/main" style="    font-size: 11px;
    font-weight: normal;
    text-shadow: none;
    display: inline-block;
    padding: 5px 10px;
    border-radius: 2px;
    background-color: #2d89d4;
    color: #fff;
    border: none;
    margin-right: 10px;" data-ajax="false">返回首页</a></div>
      
    </div>
  </form>
</div>
<!-- /content -->

</div>
<!-- /登錄 -->
</body>
<script type="text/javascript">
function createCode() {
  $("#img_validateCode").attr("src", "${ctx}/resources-code.jpg?" + new Date().getTime());
}
createCode();
</script>
</html>

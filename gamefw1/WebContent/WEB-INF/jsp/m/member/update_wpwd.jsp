<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>短信息</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>提款密码</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-form ybm-member-psw">
      <div data-role="fieldcontain">
        <label for="member-wpsw-11">旧密码：</label>
        <input type="password" name="member-wpsw-11" id="member-wpsw-11">
      </div>
      <div data-role="fieldcontain">
        <label for="member-wpsw-12">新密码：</label>
        <input type="password" name="member-wpsw-12" id="member-wpsw-12">
      </div>
      <div data-role="fieldcontain">
        <label for="member-wpsw-13">确认密码：</label>
        <input type="password" name="member-wpsw-13" id="member-wpsw-13">
      </div>
      <div data-role="fieldcontain" class="tac">
        <button id="memberwPswInfo" data-corners="false" data-icon="check" data-theme="b" onclick="checkEditWPwdForm()">确定</button>
      </div>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="psw-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="${ctx }/m/member/updpassword?page=update_pwd" data-transition="none">登录密码</a></li>
          <li><a href="${ctx }/m/member/updpassword?page=update_wpwd" data-transition="none" class="ui-btn-active ui-state-persist">提款密码</a></li>
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
<div data-role="dialog" id="memberwPswInfo" data-close-btn="right">
    <div data-role="header" data-theme="b">
      <h2 id="wtitleMsg"></h2>
    </div>
    <div data-role="content" class="tac">
      <p id="wmsg"></p>
    </div>
</div>
</body>
</html>

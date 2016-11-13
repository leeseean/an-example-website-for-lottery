<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>个人资料</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>个人资料</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-member-info f12">
      <div class="ui-grid-a">
        <div class="ui-block-a">用户名：</div>
        <div class="ui-block-b">${ webUser.userName}</div>
        <div class="ui-block-a">姓名：</div>
        <div class="ui-block-b">${webUser.userRealName }</div>
        <div class="ui-block-a">会员等级：</div>
        <div class="ui-block-b">${userType.typeLevel }</div>
        <div class="ui-block-a">账户余额：</div>
        <div class="ui-block-b">${webUser.userMoney }</div>
        <div class="ui-block-a">电子邮箱：</div>
        <div class="ui-block-b">${ webUser.userMail}</div>
        <div class="ui-block-a">QQ号码：</div>
        <div class="ui-block-b">${ webUser.userQq}</div>
        <div class="ui-block-a">手机号码：</div>
        <div class="ui-block-b">${webUser.userMobile}</div>
      </div>
    </div>
    <!-- /content -->
    <%@include file="../inc/mobile_foot.jsp" %>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
  <!-- /page -->
</body>

</html>

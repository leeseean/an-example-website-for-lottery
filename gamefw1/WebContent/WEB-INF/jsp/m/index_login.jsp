<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>欢迎莅临</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="inc/mobile_common.jsp" %>
</head>

<body>

<div data-role="page">

<div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>欢迎莅临</h1>
    </div>

<div class="member-nav">
  <div class="nav-group cf">
    <a href="${ctx}/m/main" data-ajax="false" class="nav-index">返回 网站首页</a>
    <div class="nav-item item-a">
      <a href="${ctx}/m/wap/member#/user/info" data-ajax="false" class="nav-link">会员资料</a>
      <i class="flaticon-note20"></i>
    </div>
    <div class="nav-item item-b">
      <a href="${ctx}/m/wap/member#/messages" data-ajax="false" class="nav-link">会员消息</a>
      <i class="flaticon-speechbubble19"></i>
    </div>
    <div class="nav-item item-a">
      <a href="${ctx}/m/wap/member#/home" data-ajax="false" class="nav-link">财务中心</a>
      <i class="flaticon-coin3"></i>
    </div>
    <div class="nav-item item-b">
      <a href="${ctx }/m/main/order" data-rel="dialog" class="nav-link">注单查询</a>
      <i class="flaticon-magnifier53"></i>
    </div>
  </div>
</div>
<!-- /Nav -->

<%@ include file="inc/mobile_foot.jsp" %>
<!-- /footer -->

<div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
<%@ include file="inc/left_memu.jsp" %>
</div>
<!-- /sidemenu -->

</div>
<!-- /page -->

</body>

</html>
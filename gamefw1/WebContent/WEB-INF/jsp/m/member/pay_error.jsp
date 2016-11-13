<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>快捷支付</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>
   	    
<body>
	<div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-transition="none" data-role="button" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>快捷支付</h1>
    </div>
    <!-- /header -->
    <div data-role="content">
      	${paytype eq 1 ? '微信' : '支付宝' }支付已暂停使用！
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
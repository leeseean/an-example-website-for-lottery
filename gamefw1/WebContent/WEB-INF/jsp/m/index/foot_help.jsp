<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file="common.jsp" %>
</head>

<body>
<div data-role="page" data-theme="x" class="ybb-mobile-web">
  <%@ include file="head.jsp" %>
  <!-- /header -->
  <div role="main" class="ui-content">
    <div class="ybb-article mt20">
      ${pageContent }
    </div>
  </div>
  <!-- /main -->
  <%@ include file="foot.jsp" %>
  <!-- /footer -->
  <div data-role="panel" data-display="overlay" id="quickMenu" class="ybb-mobile-sidemenu">
	<%@ include file="../inc/web_sidemenu.jsp" %>
	</div>
</div>
<!-- /page -->
</body>
</html>
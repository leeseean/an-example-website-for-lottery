<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>错误提示</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="index/common.jsp" %>
</head>

<body>

<div data-role="page" data-theme="x" class="ybb-mobile ybb-mobile-web">

<%@ include file="index/head.jsp" %>
<!-- /header -->

<div data-role="content">
  <ul data-role="listview">
      <li>
        ${errorMsg}
      </li>
      <li>
        <div data-role="fieldcontain" style=" padding: 0; text-align: center;">
          <a href="#" data-role="button" data-rel="back" data-ajax="false" data-corners="false" data-inline="true">返回</a>
          <!-- /返回下注起始頁面 -->
        </div>
      </li>
    </ul>
</div>
<!-- /content -->

<%@ include file="index/foot.jsp" %>
<!-- /footer -->

<div data-role="panel" data-display="overlay" id="quickMenu" class="ybb-mobile-sidemenu">
<%@ include file="inc/web_sidemenu.jsp" %>
</div>
<!-- /sidemenu -->

</div>
<!-- /page -->

</body>

</html>
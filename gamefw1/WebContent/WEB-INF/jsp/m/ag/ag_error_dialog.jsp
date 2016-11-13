<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<title>ag视讯</title>
   	<%@include file="../inc/mobile_common.jsp"%>
</head>
<body>
<div data-role="page" data-close-btn="none">
  <div data-role="header">
    <h1>操作失败</h1>
  </div>
  <!-- /header -->
  <div data-role="content" class="ybm-form ybm-order ybm-order-success">
    <ul data-role="listview">
      <li>
        ${msg }
      </li>
      <li>
        <div data-role="fieldcontain" style=" padding: 0; text-align: center;">
          <a href="#" data-role="button" data-corners="false" data-inline="true" data-rel="back" data-icon="back">返回</a>
          <!-- /返回下注起始頁面 -->
        </div>
      </li>
    </ul>
  </div>
  <!-- /content -->
</div>
<!-- /page -->
</body>
</html>

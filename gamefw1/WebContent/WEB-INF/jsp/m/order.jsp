<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>订单查询</title>
  <%@include file="inc/mobile_common.jsp"%>
</head>
<body>
<div data-role="page" data-close-btn="right">
  <div data-role="header">
    <h1>注单类别</h1>
  </div>
  <div data-role="content">
	<a href="${ctx}/m/sport/goOrderFilter" data-rel="dialog" data-role="button">体育</a>
	<%-- <a href="${ctx}/m/mobileCpAccount/goCpOrderSelect" data-rel="dialog" data-role="button">彩票</a> --%>
	<a href="${ctx}/m/slot/goSlotOrder?code=ag" data-rel="dialog" data-role="button">AG</a>
	<a href="${ctx}/m/slot/goSlotOrder?code=mg" data-rel="dialog" data-role="button">MG</a>
	<a href="${ctx}/m/slot/goSlotOrder?code=os" data-rel="dialog" data-role="button">OS</a>
	<a href="${ctx}/m/slot/goSlotOrder?code=pt" data-rel="dialog" data-role="button">PT</a>
  </div>
</div>
</body>
</html>
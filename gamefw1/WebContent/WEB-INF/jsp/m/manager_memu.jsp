<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>财务中心</title>
	<%@include file="inc/mobile_common.jsp"%>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
				<a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a> 
				<a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
			</div>
			<h1>财务中心</h1>
		</div>
		<!-- /header -->
		<div data-role="content" class="ybm-category">
			<ul data-role="listview">
		      <li data-role="list-divider">财务中心</li>
		      <li><a href="${ctx }/m/member/goAccounts" data-ajax="false">公司入款</a></li>
		      <li><a href="${ctx }/m/member/gowxpay?paytype=1" data-ajax="false">微信支付</a></li>
		      <li><a href="${ctx }/m/member/gowxpay?paytype=2" data-ajax="false">支付宝支付</a></li>
		      <li><a href="${ctx }/m/member/goWithdraw" data-ajax="false">在线提款</a></li>
		      <li><a href="${ctx }/m/edu/goedu" data-ajax="false">额度转换</a></li>
		      <li><a href="${ctx }/m/member/goPayHistory?code=payHistory" data-ajax="false">入款记录</a></li>
		      <li><a href="${ctx }/m/member/goPayHistory?code=withdrawHistory" data-ajax="false">提款记录</a></li>
			  <li><a href="${ctx }/m/edu/eduHistory" data-rel="dialog">转换记录</a></li>
		    </ul>
		</div>
		<!-- /content -->
		<%@ include file="inc/mobile_foot.jsp"%>
		<!-- /footer -->
		<!-- 左側快捷菜單 -->
		<div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    		<%@ include file="inc/left_memu.jsp" %>
    	</div>
		<!-- /左側快捷菜單 -->
	</div>
	<!-- /page -->
</body>
</html>
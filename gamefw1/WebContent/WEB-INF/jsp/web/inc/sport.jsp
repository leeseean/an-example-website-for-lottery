<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="row">
	<div class="wrapper">
		<div class="content-top-banner"></div>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		<div class="header-msg">
			<%@include file="msg.jsp" %>
		</div>
	</div>
</div>
<div class="row">
	<div class="wrapper">
		<div class="w1000 align-h">
			<iframe src="${ctx}/sport/main" name="sportFrame" id="sportFrame" width="100%" height="750" frameborder="0" scrolling="no"></iframe>
		</div>
	</div>
</div>

<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>

</head>
<body>
<div class="wrapper">
<div class="panel-content font-hei">
<div class="panel-security">
	<div class="page-title clear">
		<div class="left">
			<h2 class="s24 blue">密码安全</h2>
			<h3 class="mt5 gray">密码安全与您的资金息息相关，切勿透露给他人</h3>
		</div>
		<div class="right mt10">
			<button
				class="button button-primary button-raised button-pill button-tiny btn-contact">
				<i></i>在线客服
			</button>
		</div>
	</div>
	<!-- /title -->
	<div class="page-body">
		<div class="switch-group">
			<div class="switch-item">
				<div class="page-body">
					<div class="item clear">
						<div class="icon left center gray-dark"><i class="flaticon-user157"></i><span class="pt10 block">登录密码</span></div>
						<div class="desc left gray">登录账户时需要输入的密码</div>
						<a href="javascript:void(0);" onclick="goParent('${ctx}/member/goLoginPwd')"  class="button button-3d button-primary button-pill right s12">修改</a>
					</div>
					<!-- /item -->
					<div class="item item-last clear">
						<div class="icon left center gray-dark"><i class="flaticon-money237"></i><span>提款密码</span></div>
						<div class="desc left gray">本密码设置是你提款时所需的提款密码，请妥善保管</div>
						<a href="javascript:void(0);" onclick="goParent('${ctx}/member/goWithdrawPwd')" class="button button-3d button-primary button-pill right s12">修改</a>
					</div>
					<!-- /item -->
				</div>
			</div>
			<!-- /item -->
		</div>
		<!-- /group -->
	</div>
	<!-- /body -->
</div>

</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
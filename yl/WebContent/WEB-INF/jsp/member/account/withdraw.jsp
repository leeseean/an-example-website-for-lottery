
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>
</head>
<body>
<div class="wrapper">
<div class="panel-content font-hei">
<div class="panel-amount">
<div class="page-title clear">
	<div class="left">
		<h2 class="s24 blue">提现到账户</h2>
		<p class="mt5 gray">请选择您的提款银行，并填写提款金额</p>
	</div>
	<div class="right mt10">
		<button
			class="button button-primary button-raised button-pill button-tiny btn-contact">
			<i></i>在线客服
		</button>
	</div>
</div>
<div class="panel-progress mt20">
	<ul class="progress-step-group clear">
		<li class="step step-one step-active">
			<div class="step-inner">
				<span class="step-icon">1</span>
				<h2>确认提款信息并填写金额</h2>
			</div>
		</li>
		<li class="step step-two">
			<div class="step-inner">
				<span class="step-icon">2</span>
				<h2 id="setp2Ts">正在操作，请稍候</h2>
			</div>
		</li>
		<li class="step step-three">
			<div class="step-inner">
				<span class="step-icon">3</span>
				<h2 id="setp3Ts">完成</h2>
			</div>
		</li>
	</ul>
	<div class="progress-line"></div>
</div>
<!-- /progress -->
<div class="page-body">
	<div class="progress-body-item progress-body-step1">
		<div class="verify-item verify-bank s12">
			<div class="verify-title clear">
				<div class="left b">收款账户信息确认</div>
			</div>
			<div class="verify-body">
				<table>
					<tr class="sheet-title">
						<td width="100"></td>
						<td>收款银行</td>
						<td>银行户名</td>
						<td>银行账号</td>
						<td>开户地址</td>
					</tr>
					<tr>
						<td class="pr20 ar gray-dark">账户一</td>
						<td>${webUser.userBankType}</td>
						<td>${webUser.userRealName}</td>
						<td>${webUser.userBankCard}</td>
						<td>${webUser.userBankAddress}</td>
					</tr>
				</table>
			</div>
		</div>
		<!-- /verify -->
		<div class="sheet-mod">
			<div class="sheet-content mt10">
				<form id="form1" name="form1" action="${ctx}/member/saveWithdraw">
					<table class="mt20 s14">
						<tr>
							<td width="160" class="pr20 ar gray-dark">会员账号</td>
							<td>${webUser.userName}</td>
						</tr>
						<tr>
							<td class="pr20 ar gray-dark">总账户余额</td>
							<td><span class="red">${webUser.userMoney}</span><span class="pl10 s12 gray">(各游戏平台的额度需先转回总账户，才可以进行提款)</span></td>
						</tr>
						<tr>
							<td class="pr20 ar gray-dark">提款金额</td>
							<td>
								<input type="text" id="money" name="money"  onkeyup="digitOnly(this)" maxlength="15" size="10" class="write b red">
								<span class="pl10 s12 gray">最低提款额为：100元</span>
							</td>
						</tr>
						<tr>
							<td class="pr20 ar gray-dark">提款密码</td>
							<td><input type="password" size="4" id="userWithdrawPassword" maxlength="4" name="userWithdrawPassword"
								class="write b center">
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<button class="button button-raised button-primary button-small"
									onclick="withdrawMoneyCheck();return false;">下一步</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!-- /sheet -->
	</div>
	<!-- /item -->
	<div class="progress-body-item progress-body-step2"></div>
	<!-- /item -->
</div>
<!-- /body -->
</div>
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
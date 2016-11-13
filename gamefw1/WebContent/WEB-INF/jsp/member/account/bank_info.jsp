
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>
</head>
<body>
<body>
<div class="wrapper">
<div class="panel-content font-hei">
	<div class="panel-welcome">
		<div class="page-title clear">
			<div class="left">
				<h2 class="s24 blue">绑定银行卡资料全览</h2>
				<h3 class="mt5 gray">欢迎您回来</h3>
			</div>
			<div class="right mt10">
				<button
					class="button button-primary button-raised button-pill button-tiny btn-contact">
					<i></i>在线客服
				</button>
			</div>
		</div>
		<div class="page-body">
			<div class="sheet-mod">
				<div class="sheet-content mt10">
					<form id="form1" action="${ctx}/member/saveBackInfo" method="post">
		 
						<table class="s14">
							<tr>
								<td width="160" class="pr20 ar gray-dark">会员账号</td>
								<td>${webUser.userName}</td>
							</tr>
							<tr>
								<td class="pr20 ar gray-dark">收款人姓名</td>
								<td><span class="red">${webUser.userRealName}</span></td>
							</tr>
							<tr>
								<td class="pr20 ar gray-dark">收款银行</td>
								<td>
									
									<select id="userBankType" name="userBankType" class="write" style="width: 120px;">
										<option value="中国银行" <c:if test="${webUser.userBankType=='中国银行'}">selected="selected"</c:if>>中国银行</option>
										<option value="工商银行" <c:if test="${webUser.userBankType=='工商银行'}">selected="selected"</c:if>>工商银行</option>
										<option value="农业银行" <c:if test="${webUser.userBankType=='农业银行'}">selected="selected"</c:if>>农业银行</option>
										<option value="建设银行" <c:if test="${webUser.userBankType=='建设银行'}">selected="selected"</c:if>>建设银行</option>
										<option value="邮政银行" <c:if test="${webUser.userBankType=='邮政银行'}">selected="selected"</c:if>>邮政银行</option>
										<option value="招商银行" <c:if test="${webUser.userBankType=='招商银行'}">selected="selected"</c:if>>招商银行</option>
										<option value="民生银行" <c:if test="${webUser.userBankType=='民生银行'}">selected="selected"</c:if>>民生银行</option>
										<option value="兴业银行" <c:if test="${webUser.userBankType=='兴业银行'}">selected="selected"</c:if>>兴业银行</option>
										<option value="中信银行" <c:if test="${webUser.userBankType=='中信银行'}">selected="selected"</c:if>>中信银行</option>
										<option value="交通银行" <c:if test="${webUser.userBankType=='交通银行'}">selected="selected"</c:if>>交通银行</option>
										<option value="华厦银行" <c:if test="${webUser.userBankType=='华厦银行'}">selected="selected"</c:if>>华厦银行</option>
										<option value="广发银行" <c:if test="${webUser.userBankType=='广发银行'}">selected="selected"</c:if>>广发银行</option>
										<option value="光大银行" <c:if test="${webUser.userBankType=='光大银行'}">selected="selected"</c:if>>光大银行</option>
										<option value="平安银行" <c:if test="${webUser.userBankType=='平安银行'}">selected="selected"</c:if>>平安银行</option>
										<option value="浦发银行" <c:if test="${webUser.userBankType=='浦发银行'}">selected="selected"</c:if>>浦发银行</option>
										<option value="农村信用社" <c:if test="${webUser.userBankType=='农村信用社'}">selected="selected"</c:if>>农村信用社</option>
									</select>
									<span class="pl10 s12 gray">认真填写，一旦录入无法修改。</span>
								</td>
							</tr>
							<tr>
								<td class="pr20 ar gray-dark">银行账号</td>
								<td>
									<input type="text" name="userBankCard" value="${webUser.userBankCard}" id="userBank" size="30" class="write b red" onKeyUp="value=value.replace(/[^0-9]+/,'')">
								</td>
							</tr>
							<tr>
								<td class="pr20 ar gray-dark">开户行地址</td>
								<td>
									<input type="text" name="userBankAddress" id="userBankAddress" value="${webUser.userBankAddress}" size="128" maxlength="128" class="write b red" style="width: 300px;">&nbsp;省/市/县
								</td>
							</tr>
							<tr>
								<td class="pr20 ar gray-dark">资金密码</td>
								<td>
									<input  type="password" maxlength="4" id="userWithdrawPassword" name="userWithdrawPassword" class="write b center">
									<span class="pl10 s12 gray">4位数字，方便记忆</span>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<button onclick="bankSaveCheck();return false;" class="button button-raised button-royal button-small">确认</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
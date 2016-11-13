
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
				<h2 class="s24 blue">提现到账户</h2>
				<p class="mt5 gray">请选择您的提款银行，并填写提款金额</p>
			</div>
			<div class="right mt10">
				<button class="button button-primary button-raised button-pill button-tiny btn-contact"> <i></i>在线客服
				</button>
			</div>
		</div>
		<div class="page-body">
			<div class="sheet-mod">
				<div class="sheet-content mt10">
					<table class="s14">
						<tr>
							<td width="160" class="pr20 ar gray-dark">&nbsp;</td>
							<td style="color: red;font-size: 15px;">尊敬的用户您好，您当前的打码量不够，还达不到提款条件，继续努力哦</td>
						</tr>
						<tr>
							<td width="160" class="pr20 ar gray-dark">会员账号</td>
							<td>${dama.userName }</td>
						</tr>
						<tr>
							<td width="160" class="pr20 ar gray-dark">申请时间(美东时间)</td>
							<td><fmt:formatDate value="${dama.dmBeginTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<td width="160" class="pr20 ar gray-dark">存款金额</td>
							<td>${dama.dmOriMoney }￥</td>
						</tr>
						<tr>
							<td width="160" class="pr20 ar gray-dark">赠送彩金</td>
							<td>${dama.dmSendMoney }￥</td>
						</tr>
						<tr>
							<td width="160" class="pr20 ar gray-dark">所需打码量</td>
							<td>${dama.dmReqDm }￥</td>
						</tr>
						<tr>
							<td width="160" class="pr20 ar gray-dark">已完成打码量</td>
							<td>${dama.curUserDama}￥</td>
						</tr>
						
					</table>
					 
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
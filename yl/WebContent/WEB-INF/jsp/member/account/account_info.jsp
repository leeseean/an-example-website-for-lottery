
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>
</head>
<body>
<div class="wrapper">
<div class="panel-content font-hei">
	<div class="panel-welcome">
		<div class="page-title clear">
			<div class="left">
				<h2 class="s24 blue">账户全览</h2>
				<h3 class="mt5 gray">欢迎您回来</h3>
			</div>
			<div class="right mt10">
				<button class="button button-primary button-raised button-pill button-tiny btn-contact">
					<i></i>在线客服					
				</button>
			</div>
		</div>
		<div class="page-body">
			<div class="sheet-mod">
				<div class="sheet-name s18 clear">
					<h6 class="left mt5">账户余额</h6>
					<div class="right">
						<button id="allBnt"  class="button button-raised button-pill button-tiny" onclick="loadAllMoney()">
							<i></i>全部显示
						</button>
					</div>
				</div>
				<div class="sheet-content mt10">
					<table class="s16 center">
						<tr class="sheet-title">
							<td>AG厅</td>
							<td>HG厅</td>
							<td>BBIN厅</td>
							<td>MG厅</td>
							<td>DS厅</td>
						</tr>
						<tr>
							<td><span id="ag_money" onclick="loadMoney('ag')"><img id="ag_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="hg_money" onclick="loadMoney('hg')"><img id="hg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="bbin_money" onclick="loadMoney('bbin')"><img id="bbin_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="mg_money" onclick="loadMoney('mg')"><img id="mg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="ds_money" onclick="loadMoney('ds')"><img id="ds_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
						</tr>
						<tr class="sheet-title">
							<td>PT厅</td>
							<td>NT厅</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><span id="pt_money" onclick="loadMoney('pt')"><img id="pt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="nt_money" onclick="loadMoney('nt')"><img id="nt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="sheet-mod mt40">
				<div class="sheet-name s18 clear">
					<h6 class="left mt5">银行资料</h6>
					<div class="right">
						<button class="button button-raised button-pill button-tiny" onclick="goParent('${ctx}/member/goBankInfo')">
							<i></i>修改
						</button>
					</div>
				</div>
				<div class="sheet-content mt10">
					<table class="s16 center">
						<tr class="sheet-title">
							<td>银行卡姓名</td>
							<td>银行卡开户行</td>
							<td>银行卡卡号</td>
						</tr>
						<tr>
							<td>${webUser.userRealName}</td>
							<td>${webUser.userBankAddress}</td>
							<td>${webUser.userBankCard}</td>
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
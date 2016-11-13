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
							<td>DS厅</td>
							<td>HG厅</td>
							<td>BBIN厅</td>
							<td>MG厅</td>
						</tr>
						<tr>
							<td><span id="ag_money" onclick="loadMoney('ag')"><img id="ag_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="ds_money" onclick="loadMoney('ds')"><img id="ds_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="hg_money" onclick="loadMoney('hg')"><img id="hg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="bbin_money" onclick="loadMoney('bbin')"><img id="bbin_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="mg_money" onclick="loadMoney('mg')"><img id="mg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
						</tr>
						<tr class="sheet-title">
							<td>PT厅</td>
							<td>NT厅</td>
							<%--<td>斗鸡厅</td>--%>
							<td>欧博厅</td>
							<td>OG厅</td>
							<td>OS厅</td>
						</tr>
						<tr>
							<td><span id="pt_money" onclick="loadMoney('pt')"><img id="pt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="newnt_money" onclick="loadMoney('newnt')"><img id="newnt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<%--<td><span id="douji_money" onclick="loadMoney('douji')"><img id="douji_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							--%>
							<td><span id="ab_money" onclick="loadMoney('ab')"><img id="ab_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="og_money" onclick="loadMoney('og')"><img id="og_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="os_money" onclick="loadMoney('os')"><img id="os_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
						</tr>
						<tr class="sheet-title">
							<td>沙巴厅</td>
							<td>GD厅</td>
							<td>TTG厅</td>
							<td>SBT厅</td>
							<td>SA厅</td>
						</tr>
						<tr>
							<td><span id="sb_money" onclick="loadMoney('sb')"><img id="sb_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="gd_money" onclick="loadMoney('gd')"><img id="gd_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="ttg_money" onclick="loadMoney('ttg')"><img id="ttg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="sbt_money" onclick="loadMoney('sbt')"><img id="sbt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="sa_money" onclick="loadMoney('sa')"><img id="sa_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
						</tr>
						<tr class="sheet-title">
							<td>VG厅</td>
						</tr>
						<tr>
							<td><span id="vg_money" onclick="loadMoney('vg')"><img id="vg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
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
</body>
<script src="${resourceRoot}/member/js/plugins.js"></script>
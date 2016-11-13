
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#fromFlatName').on('selectmenuchange', function (e,ui) {
	//alert(ui.item.value);
	selectFromFlat();
	});
});
</script>
</head>
<body>
<div class="wrapper">
<div class="panel-content font-hei">
<div class="panel-amount">
	<div class="page-title clear">
		<div class="left">
			<h2 class="s24 blue">额度转换与查询</h2>
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
	<div class="panel-progress mt20">
		<ul class="progress-step-group clear">
			<li class="step step-one step-active">
				<div class="step-inner">
					<span class="step-icon">1</span>
					<h2>输入转换金额</h2>
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
					<div class="left b">余额总览</div>
				</div>
				<div class="verify-body">
					<table class="center border-shin mb10 edu-table">
						<tr class="sheet-title">
							<td>账户余额</td>
							<td>AG余额</td>
							<td>DS余额</td>
							<td>HG余额</td>
							<td>BBIN余额</td>
							<td>MG余额</td>
							<td>PT余额</td>
							<td>NT余额</td>
						</tr>
						<tr>
							<td><span id="account_money" onclick="loadMoney('account')"><img id="account_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="ag_money" onclick="loadMoney('ag')"><img id="ag_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="ds_money" onclick="loadMoney('ds')"><img id="ds_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="hg_money" onclick="loadMoney('hg')"><img id="hg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="bbin_money" onclick="loadMoney('bbin')"><img id="bbin_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="mg_money" onclick="loadMoney('mg')"><img id="mg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="pt_money" onclick="loadMoney('pt')"><img id="pt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="newnt_money" onclick="loadMoney('newnt')"><img id="newnt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
						</tr>
					</table>
					<table class="center border-shin mb10 edu-table">
						<tr class="sheet-title">
							<td>欧博余额</td> 
							<td>OG余额</td>
							<td>OS余额</td>
							<td>沙巴余额</td>
							<td>GD余额</td>
							<td>TTG余额</td>
							<td>SBT余额</td>
							<td>SA余额</td>
						</tr>
						<tr>
							<td><span id="ab_money" onclick="loadMoney('ab')"><img id="ab_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="og_money" onclick="loadMoney('og')"><img id="og_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="os_money" onclick="loadMoney('os')"><img id="os_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="sb_money" onclick="loadMoney('sb')"><img id="sb_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="gd_money" onclick="loadMoney('gd')"><img id="gd_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="ttg_money" onclick="loadMoney('ttg')"><img id="ttg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="sbt_money" onclick="loadMoney('sbt')"><img id="sbt_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td><span id="sa_money" onclick="loadMoney('sa')"><img id="sa_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
						</tr>
					</table>
					<table class="center border-shin mb10 edu-table">
						<tr class="sheet-title">
							<td>VG余额</td>
							<td></td> 
							<td></td> 
							<td></td> 
							<td></td> 
							<td></td> 
							<td></td> 
							<td></td> 
						</tr>
						<tr>
							<td><span id="vg_money" onclick="loadMoney('vg')"><img id="vg_img" src="${resourceRoot}/member/images/freshen.png" /></span></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			</div>
			<!-- /verify -->
			
			<div class="sheet-mod">
				<div class="sheet-content mt10">
					<form id="form1" action="${ctx}/member/doEdu" method="post">
						<table class="mt20 s14">
							<tr>
								<td width="160" class="pr20 ar gray-dark">转出账户</td>
								<td>
									<select id="fromFlatName" name="fromFlatName"  class="dropmenu w150" >
										<option value="account">总帐号金额</option>
										<option value="ag">AG平台</option>
										<option value="ds">DS平台</option>
										<option value="hg">HG平台</option>
										<option value="bbin">BBIN平台</option>
										<option value="mg">MG平台</option>
										<option value="pt">PT平台</option>
										<option value="newnt">NT平台</option>
										<option value="ab">欧博平台</option>
										<option value="og">OG平台</option>
										<option value="os">OS平台</option>
										<option value="sb">沙巴平台</option>
										<option value="gd">GD平台</option>
										<option value="ttg">TTG平台</option>
										<option value="sbt">SBT平台</option>
										<option value="sa">SA平台</option>
										<option value="vg">VG平台</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="pr20 ar gray-dark">转出额度</td>
								<td><input type="text" id="wPoints" name="wPoints"  size="10" onkeyup="digitOnly(this);" class="write s14 b red"><span
									class="pl10 s12 gray">最低转账额度为：10元</span></td>
							</tr>
							<tr>
								<td class="pr20 ar gray-dark">转入账户</td>
								<td>
									<select id="toFlatName" name="toFlatName" class="dropmenu w150">
											<option value="ag">AG平台</option>
											<option value="ds">DS平台</option>
											<option value="hg">HG平台</option>
											<option value="bbin">BBIN平台</option>
											<option value="mg">MG平台</option>
											<option value="pt">PT平台</option>
											<option value="newnt">NT平台</option>
											<option value="ab">欧博平台</option>
											<option value="og">OG平台</option>
											<option value="os">OS平台</option>
											<option value="sb">沙巴平台</option>
											<option value="gd">GD平台</option>
											<option value="ttg">TTG平台</option>
											<option value="sbt">SBT平台</option>
											<option value="sa">SA平台</option>
											<option value="vg">VG平台</option>
									</select>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<button class="button button-raised button-primary button-small" onclick="checkUserEduForm();return false;">下一步</button>
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
	<div class="page-body"></div>
	<!-- /body -->
</div>
<!-- /amount -->
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
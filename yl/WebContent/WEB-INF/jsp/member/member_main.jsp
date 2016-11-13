<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html style="overflow-x:hidden" class="no-js" lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>会员中心</title>
<meta name="description" content="">
<%@include file="/commons/member/jsp/member_common.jsp"%>
</head>
<body>
	<div class="wrapper">
		<div class="panel-header">
			<div class="inner clear">
				<div class="member-info left clear">
					<div class="welcome left center">
						<div class="ih-item circle effect">
							<a href="javascript:void(0);">
								<div class="spinner"></div>
								<div class="icon">
									<script>
										var now = (new Date()).getHours();
										if (now > 0 && now <= 6) {
											document.write("<div class='icon-text center gray'>午夜好，</div><div class='icon-time flaticon-crescent33'></div>");
										} else if (now > 6 && now <= 11) {
											document.write("<div class='icon-text center gray'>早上好，</div><div class='icon-time flaticon-sunny27'></div>")
										} else if (now > 11 && now <= 14) {
											document.write("<div class='icon-text center gray'>中午好，</div><div class='icon-time flaticon-mountain5'></div>");
										} else if (now > 14 && now <= 18) {
											document.write("<div class='icon-text center gray'>下午好，</div><div class='icon-time flaticon-sunset9'></div>");
										} else {
											document.write("<div class='icon-text center gray'>晚上好，</div><div class='icon-time flaticon-crescent19'></div>");
										}
									</script>
								</div>
								<div class="info">
									<div class="info-back font-hei">
										<h3 class="s24">会员中心</h3>
										<p class="gray">点击前往</p>
									</div>
								</div></a>
						</div>
					</div>
					<!-- /welcome -->
					<div class="info left ml10">
						<h2 class="mt20 font-hei s20 gray-dark" >${webUser.userName}</h2>
						<h3 class="mt5 gray">
							您有<span class="ml5 mr5 font-hei red" id="userMessageId">${msgTotal}</span>封未读新消息
						</h3>
						<div class="security-tips gray-dark">
							<h6 class="mt20">
								<strong>上次登录信息：</strong> <a href="##" onclick="goback('${ctx}/member/goPwdMain')" class="link-more block">修改密码</a>
							</h6>
							<p class="mt10">
								时间：<span class="font-hei red">

									<fmt:formatDate value="${webUser.userLastLoginTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
								</span>
							</p>
							<div class="mt5">
								IP地址：<span class="font-hei red">${webUser.userLastLoginIp}</span>
							</div>
						</div>
					</div>
					<!-- /info -->
				</div>
				<div class="member-balance left">
					<ul class="clear">
						<li class="item item-1">
							<h6 class="ml5 gray">
								账户余额 <a href="##" onclick="goback('${ctx}/member/goPayBank')" class="link-more">我要充值</a>
							</h6>
							<div class="mt10 font-hei s30" id="userMoneyId">￥${webUser.userMoney}</div>
							<script type="text/javascript">
								setInterval('refreshUserMoney()', 10000);
							</script>
						</li>
						<!-- /item -->
						<li class="item item-2 slide-paging">
							<h6 class="gray">
								最新优惠 <a href="${ctx}/goPageCenter?code=promos" class="link-more block">更多优惠</a>
							</h6>
							<div class="body slide-paging-body">
								<ul>
									<c:forEach var="t" items="${lbPromoList}" varStatus="s">
										<li>${t.pmsTitle }</li>
									</c:forEach>
								</ul>
							</div>
							<ul class="slide-paging-group clear"></ul>
							<span class="em em-left font-hei s40 gray">“</span><span class="em em-right font-hei s40 gray">”</span>
						</li>
						<!-- /item -->
					</ul>
				</div>
				<!-- /balance -->
			</div>
		</div>
		<!-- /header -->
		<div class="panel-container mt20">
			<div class="switch-page switch-load-mod inner font-hei clear">
				<div class="panel-sidebar left">
					<ul class="switch-name switch-load-name">
						<li class="item" style=" display: none"></li>
						<li class="title white">
							<h2 class="pt15 s24">财务中心</h2>
							<h3 class="mt5">取款急速处理</h3>
						</li>

						<li class="item <c:if test="${type=='1'}">on</c:if>" onclick="goback('${ctx}/member/goPayBank')">充值到账户</li>
						<li class="item <c:if test="${type=='2'}">on</c:if>" onclick="goback('${ctx}/member/goWithdraw')">提现到账户</li>
						<li class="item <c:if test="${type=='3'}">on</c:if>" onclick="goback('${ctx}/member/goEdu')">额度转换</li>
						<li class="title white">
							<h2 class="pt15 s24">财务报表</h2>
							<h3 class="mt5">优惠时时结算</h3>
						</li>
						<li class="item" onclick="goback('${ctx}/member/goList?code=withdrawHistory')">提现记录</li>
						<li class="item" onclick="goback('${ctx}/member/goList?code=payHistory')">充值记录</li>
						<li class="item" onclick="goback('${ctx}/member/goList?code=eduHistory')">转换记录</li>
						<li class="item <c:if test="${type=='4'}">on</c:if>" onclick="goback('${ctx}/records/goList?code=sport')">投注记录</li>
						<li class="title white">
							<h2 class="pt15 s24">个人资料</h2>
							<h3 class="mt5">完成绑定方便快捷取款</h3>
						</li>
						<li class="item <c:if test="${type=='5'}">on</c:if>"  onclick="goback('${ctx}/message/listForUser')">站内信息</li>
						<li class="item" onclick="goback('${ctx}/member/goPwdMain')">密码安全</li>
						<li class="title white">
							<h2 class="pt15 s24">代理专区</h2>
							<h3 class="mt5">加入我们让财富来的更猛烈一些</h3>
						</li>
							<li class="item" onclick="goback('${ctx}/agent/agentDetail')">代理申请</li>
					</ul>
				</div>
				<!-- /sidebar -->
				<div class="panel-content left">
					<div class="goback">
						<div class="slide-notice left clear">
							<div class="bd left">
								<h2 class="gray">公告：</h2>
								<div class="mt5">
									<ul>
										<c:forEach var="item" items="${gongGaoList}" varStatus="item_index">
											<li>${item.ggName}</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<ul class="hd right">
							</ul>
							<div class="slide-ctrl">
								<a class="next1 block"></a><a class="prev1 block"></a>
							</div>
						</div>
						<a href="${ctx}/member/main" class="right mt20 mr20 block white">返回我的个人中心</a>
					</div>
					<iframe id="memberFrame" name="memberFrame" src="${ctx}/${backUrl}" scrolling="no" frameborder="0" width="100%" height="850" allowtransparency="true">
					</iframe>
					<!-- /group -->
				</div>
				<!-- /content -->
			</div>
		</div>
		<!-- /container -->
	</div>
	<!-- /wrapper -->
	  <script src="${resourceRoot}/member/js/switch-load.js"></script>
	  <script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
</html>
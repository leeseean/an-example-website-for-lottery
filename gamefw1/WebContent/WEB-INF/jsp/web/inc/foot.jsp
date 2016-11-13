<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%-- <div class="footer-content">
	<div class="links links-footer">
		<div class="wrapper w1040 align-h">
			<ul class="clearfix text-center">
				<li><a href="${ctx}/goPageCenter?code=help&pgSn=deposit">存取款帮助&nbsp;/&nbsp;</a></li>
				<li><a href="${ctx}/goPageCenter?code=help&pgSn=question">常见问题&nbsp;/&nbsp;</a></li>
				<li><a href="${ctx}/goPageCenter?code=help&pgSn=agent">合作伙伴&nbsp;/&nbsp;</a></li>
				<li><a href="${ctx}/goPageCenter?code=help&pgSn=about">关于我们&nbsp;/&nbsp;</a></li>
				<li><a href="${ctx}/goPageCenter?code=help&pgSn=contact">联系我们</a></li>
			</ul>
			<p>${ctxMap['siteFooter'] }</p>
		</div>
	</div>
	
	<div class="footer-services bghei w1040 align-h"><img src="${resourceRoot }/web/ybb/assets/img/fimg1.png" alt="" /></div>
	<div class="footer-copyright-warning" style="position: relative;">
	    <style>
		    .row-ab {
			    position: absolute;
			    right: -92px;
			    width: 150px;
			    top: 12px;
		    }
	    	.row-ab a{
	    		display: block;
	    	}
	    	.row-ab img {
		        width: 100%;
		        margin: 10px 0px 0px 0px;
	    	}
	    </style>
	    <div class="row row-ab mt20">
	    	<a href="http://www.ab8686.com/" target="_blank"><img src="${resourceRoot }/web/ybb/assets/img/ab_logo.png" alt="" /></a>
	    </div>
	</div>
</div>

<c:choose>
	<c:when test="${page_name == 'main' }">
		<div class="row">
			<div class="wrapper">
				<div class="home-foot-ads">
					<div class="w1040 align-h">
						<div class="h37"></div>
						<div class="left three-column column-index-1">
							<div class="sh"><img src="${resourceRoot }/web/ybb/assets/img/sf1.png" alt="" /></div>
							<div class="h15"></div>
							<div class="miaoshu">
								<div class="left">
									<p class="f18">存款到账</p>
									<p class="f12">平均时间</p>
								</div>
								<div class="right">
									<p class="f46 l40"><span id="ys_ckmsg">25</span><span class="f18">秒</span></p>
								</div>
								<div class="clearfix"></div>
								<div class="pao">
									<div id="ys_ckcent" class="paoin" style="width: 25%;"></div>
								</div>
							</div>
							<div class="h10"></div>
							<div class="miaoshu">
								<div class="left">
									<p class="f18">取款到账</p>
									<p class="f12">平均时间</p>
								</div>
								<div class="right">
									<p class="f46 l40"><span id="ys_qkmsg">2'15</span><span class="f18">分</span></p>
								</div>
								<div class="clear"></div>
								<div class="pao">
									<div id="ys_qkcent" class="paoin" style="width: 67%;"></div>
								</div>
							</div>
							<div class="h20"></div>
							<div class="why">
								<b class="f23">为什么？</b>
								<b class="f18">大家都选!永利高</b>
							</div>
							<div class="h20"></div>
							<ul class="liew">
								<li>澳门赌王何鸿燊100亿打造</li>
								<li>澳门永利高官方直营</li>
								<li>取款1000万3分钟火速到账</li>
								<li>资金安全,客户十年零投诉</li>
								<li>保障隐私,资料信息多重加密</li>
							</ul>
						</div>
						<div class="left three-column">
							<div class="sh"><img src="${resourceRoot }/web/ybb/assets/img/sf1.png" alt="" /></div>
							<div class="h25"></div>
							<div class="yuzhi ov">
								<img src="${resourceRoot}/web/ybb/assets/img/yz1.png" alt="" class="left mr8">
								<div class="yuzizi">
									<b class="f18 lh24">视讯直播</b>
									<p class="sot f13 l18">提供真人百家乐、轮盘、骰宝、龙虎斗、二八杠等,美女荷官在线发牌， 画面真实高清,在网上体验如同亲临赌场的刺激!</p>
								</div>
							</div>
							<div class="cl h38"></div>
							<div class="yuzhi ov">
								<img src="${resourceRoot }/web/ybb/assets/img/yz2.png" alt="" class="left mr8">
								<div class="yuzizi">
									<b class="f18 lh24">体育赛事</b>
									<p class="sot f13 l18">每月提供上万场的体育赛事，有足球、篮球、网球、排球等。五彩缤纷的体育赛事，精心设计的投注界面，带给您轻松、怡静的体育投注乐趣！</p>
								</div>
							</div>
							<div class="cl h38"></div>
							<div class="yuzhi ov">
								<img src="${resourceRoot }/web/ybb/assets/img/yz4.png" alt="" class="left mr8">
								<div class="yuzizi">
									<b class="f18 lh24">电子游艺</b>
									<p class="sot f13 l18">100多种电子游戏,包含老虎机、电动扑克、大型电玩、3D游戏等，顶级的视觉、声光效果，业界最高的赔率和返水,带给您最优越的体验！</p>
									</div>
							</div>
							<div class="cl h38"></div>
							<div class="yuzhi ov">
								<img src="${resourceRoot }/web/ybb/assets/img/yz3.png" alt="" class="left mr8">
								<div class="yuzizi">
									<b class="f18 lh24">彩票游戏</b>
									<p class="sot f13 l18">时时彩、快乐彩、六合彩、北京PK10等，彩票游戏丰富，所有赛果依据官方开奖结果，带给您一夜致富的机会及空前的游戏体验！</p>
								</div>
							</div>
						</div>
						<div class="left three-column column-index-3">
							<div><img src="${resourceRoot }/web/ybb/assets/img/sf3.png" alt=""></div>
							<div class="h8"></div>
							<div>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt1.png" alt=""></a>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt2.png" alt=""></a>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt3.png" alt=""></a>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt4.png" alt=""></a>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt5.png" alt=""></a>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt6.png" alt=""></a>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt7.png" alt=""></a>
								<a href="#" class="db fl"><img src="${resourceRoot }/web/ybb/assets/img/ftt8.png" alt=""></a>
							</div>
							<div class="clear h35"></div>
							<div class="lianbg">
								<div class="h85"></div>
								<p class="eco1">客服QQ：${ctxMap['siteQq'] } </p>
								<p class="eco2">E-mail：${ctxMap['siteMail'] }  </p>
								<p class="eco3">联系电话：${ctxMap['siteTel'] } </p>
								<p class="eco4">国际电话：${ctxMap['siteMobile'] }</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:when>
</c:choose> --%>
<div class="page-fooer">
	<div class="footer-img">
		
	</div>
	<div class="footer-text-wrap">
		<div class="article-menu">
			<a href="${ctx}/goPageCenter?code=help&pgSn=about">关于我们 ｜</a>
			<a href="${ctx}/goPageCenter?code=help&pgSn=contact">联络我们 ｜</a>
			<a href="${ctx}/goPageCenter?code=help&pgSn=agent" data-color="#8D8F93|#f54a19">代理合作 ｜</a>
			<a href="${ctx}/goPageCenter?code=help&pgSn=deposit">存款帮助 ｜</a>
			<a href="${ctx}/goPageCenter?code=help&pgSn=deposit">取款帮助 ｜</a>
			<a href="${ctx}/goPageCenter?code=help&pgSn=question">常见问题 </a>
		</div>
		<div class="bot-infomation">
			<span>${ctxMap['siteFooter'] }</span>
		</div>
		<div class="footer-img-wrap clearfix"></div>
		
		<a href="http://www.ab8686.com/" target="_blank" class="browser-logo">
		<img src="${resourceRoot }/web/ybb/assets/img/index/ublogo.png" style="wisth:146px;height:48px;">
		</a>
	
	</div>
</div>




<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<<<<<<< .mine
<style>
  #mesWrap{position: fixed;left: 0;bottom: 110px;z-index: 997;cursor: pointer;}
  #downloadPng{position: relative;width:150px;height:335px}
  #downloadZxd{position: absolute;display:block;left:0;bottom:50px;width:150px;height:125px;z-index:998}
  #closeZxd{position: absolute;display: block;left:0;bottom:0;width:150px;height:50px;z-index:998}
</style>
<div id="mesWrap">
  <div id="downloadPng" onclick="">
    <a title="扫描下载AG手机客户端">
      <img src="${resourceRoot}/web/ybb/assets/img/foot/ylMes.png" >
    </a>
    <a id="downloadZxd" title="点击下载资讯端" href="http://y1818.bybet888.com/澳门永利资讯端.exe" download></a>
    <a id="closeZxd" onclick=" this.parentNode.style.display='none' " title="点击关闭"></a>
  </div>
</div>
<!-- <div class="footer-content">
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
</c:choose> -->

=======
>>>>>>> .r6673
<style>
	.footer {
	background: #0e0e0e;
	height: 153px;
}

.footer .footerimg {
	height: 70px;
}

.footer .flink {
	color: #a9aaad;
	text-align: center;
	line-height: 30px;
	margin-top: 8px;
}

.footer .flink a {
	margin: 0 22px;
	font-size: 14px;
	color: #a9aaad;
	transition: all 0.4s
}

.footer .flink a:hover {
	color: #fff;
}

.footer .copyright {
	text-align: center;
	color: #a9aaad;
	font-family: Arial;
	line-height: 30px;
}
</style>
<div class="footer">
	<div class="w1000">
		<div class="footerimg" style="margin-left: -276px">
			<img src="${resourceRoot }/web/ybb/assets/img/foot/footerimg.png">
		</div>
		<div class="flink" style="margin-top: 20px;">
				<a href="${ctx}/goPageCenter?code=help&pgSn=about">
								关于我们</a>
				<a href="${ctx}/goPageCenter?code=help&pgSn=agent">
								合作伙伴</a>
				<a href="${ctx}/goPageCenter?code=help&pgSn=contact">
								联络我们</a>
				<a href="${ctx}/goPageCenter?code=help&pgSn=deposit">
								存款帮助</a>
				<a href="${ctx}/goPageCenter?code=help&pgSn=withdrawing">
								取款帮助</a>
	      <a href="${ctx}/goPageCenter?code=help&pgSn=contact">投诉建议</a>
		    <a href="${ctx}/goPageCenter?code=help&pgSn=faq">常见问题</a>
		</div>
		<div class="copyright">Copyright (c) 永利娱乐城 Reserved </div>
	</div>
</div>
<style>
	.dialog-official-cert{
	  overflow: hidden;
	  width: 490px;
	  height: 700px;
	}
	.ui-widget-overlay {
  z-index: 10000;
	}
	.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ui-draggable.ui-resizable {
	  background: #1c1c1c none;
	  border: solid 3px #333333;
	  border-radius: 8px;
	  overflow: hidden;
	}
	.ui-draggable .ui-dialog-titlebar {
	  font-size: 16px;
	  background: transparent none;
	  border-radius: 0;
	  border: medium none;
	  border-bottom: solid 1px #494949;
	  margin: 0 -3px;
	  color: #FC3;
	}
	.ui-draggable.newsAnomount .ui-dialog-titlebar .ui-dialog-title {
	  display: inline-block;
	  padding-right: 78px;
	  width: auto;
	}
	.ui-dialog .ui-dialog-titlebar-close {
	  border: medium none;
	  background: transparent none;
	  right: 10px;
	  width: 24px;
	  height: 24px;
	  outline: none;
	}
	.ui-state-default .ui-icon {
	  background: transparent none;
	}
	.ui-state-default .ui-icon.ui-icon-closethick {
	  background: transparent url("${resourceRoot }/web/ybb/common/css/images/icon_close.png") no-repeat center center;
	  width: 24px;
	  height: 24px;
	  margin: 0;
	  left: 0;
	  top: -3px;
	  outline: none;
	}
	.ui-dialog .ui-dialog-content {
	  padding: 5px;
	}
	.ui-dialog .ui-dialog-content * {
	  line-height: 28px;
	  font-size: 14px;
	  font-family: 'Microsoft YaHei', '\5FAE\8F6F\96C5\9ED1';
	  /*color: #896966;*/
	}
	.ui-dialog .ui-dialog-content p {
	  margin-bottom: 10px;
	}

</style>
<div class="dialog-official-cert" title="澳门永利官方政府牌照">
  <img src="${resourceRoot }/web/ybb/assets/img/main/about2.png" title="澳门永利官方政府牌照">
</div>

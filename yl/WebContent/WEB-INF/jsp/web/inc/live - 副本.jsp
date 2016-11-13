<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="row">
	<div class="wrapper">
		<div class="content-top-banner"></div>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		<div class="header-msg">
			<%@include file="msg.jsp" %>
		</div>
	</div>
</div>

<!-- <div class="row">
	<div class="wrapper">
		<div class="content-body">
			<div class="live-items live-items-main-page">
				<ul class="clearfix">
					<c:forEach var="item" items="${liveSite}">
					<li class="${item.flat }">
						<span class="bg-border"></span>
						<div class="htop">
							<h3>${item.flatName }</h3>
							<c:choose>
								<c:when test="${!empty webUser}">
									<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')" title="点击进入" class="enter">立即游戏</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">立即游戏</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="img"></div>
						<div class="intro">
							<p>${item.flatDes }</p>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div> -->
<style>
		.game-list {
		  margin: 20px -10px 0 -10px;
		  overflow: hidden;
		}
		.game-list > .item {
		  position: relative;
		  width: 300px;
		  height: 250px;
		  float: left;
		  background-color: #00a8e9;
		  margin: 0 10px;
		  cursor: default;
		}
		.game-list > .item.top {
		  margin-bottom: 20px;
		}
		.game-list > .item.big {
		  width: 370px;
		  height: 520px;
		  background-color: #00a8e9;
		}
		.game-list > .item > .img {
		  font-size: 0;
		}
		.game-list > .item > .logo {
		  position: absolute;
		  width: 50%;
		  height: 100px;
		  background-color: rgba(0, 168, 233, 0.8);
		  bottom: 0;
		  left: 0;
		  z-index: 1;
		  background-image: url("${resourceRoot}/web/ybb/assets/img/live/lottery_logo_icon.jpg");
		  background-repeat: no-repeat;
		}

		/* live ab */

		.game-list > .item > .logo.ab {
		  background-position: center -500px;
		}
		.game-list > .item > .hover > .logo.ab {
		  background-position: center -500px;
		}

		/* live og */

		.game-list > .item > .logo.ogt {
		  background-position: center -600px;
		}
		.game-list > .item > .hover > .logo.ogt {
		  background-position: center -600px;
		}

		/* live pt */

		.game-list > .item > .logo.pt {
		  background-position: center -700px;
		}
		.game-list > .item > .hover > .logo.pt {
		  background-position: center -700px;
		}

		/* live ag */

		.game-list > .item > .logo.ag {
		  background-position: center 0;
		}
		.game-list > .item > .hover > .logo.ag {
		  background-position: center 0;
		}
		.game-list > .item > .logo.bbin {
		  background-position: center -100px;
		}
		.game-list > .item > .logo.ea {
		  background-position: center -200px;
		}
		.game-list > .item > .logo.hg {
		  background-position: center -300px;
		}
		.game-list > .item > .logo.og {
		  background-position: center -400px;
		}
		.game-list > .item > .money {
		  position: absolute;
		  width: 50%;
		  height: 50px;
		  line-height: 48px;
		  background-color: rgba(0, 168, 233, 0.8);
		  bottom: 0;
		  right: 0;
		  z-index: 1;
		  color: #fff;
		  font-size: 16px;
		  text-align: center;
		}
		.game-list > .item > .hover {
		  position: absolute;
		  top: 0;
		  left: 0;
		  right: 0;
		  bottom: 0;
		  z-index: 2;
		  background-color: rgba(0, 0, 0, 0.6);
		  display: none;
		}
		.game-list > .item > .hover > .logo {
		  width: 150px;
		  height: 100px;
		  margin: 0 auto;
		  background-image: url("${resourceRoot}/web/ybb/assets/img/live/lottery_logo_icon.jpg");
		  background-repeat: no-repeat;
		}
		.game-list > .item > .hover > .logo.bbin {
		  background-position: center -100px;
		}
		.game-list > .item > .hover > .logo.ea {
		  background-position: center -200px;
		}
		.game-list > .item > .hover > .logo.hg {
		  background-position: center -300px;
		}
		.game-list > .item > .hover > .logo.og {
		  background-position: center -400px;
		}
		.game-list > .item > .hover > .text {
		  color: #fff;
		  line-height: 20px;
		  font-size: 13px;
		  padding: 0 36px;
		  text-align: center;
		}
		.game-list > .item > .hover > .money {
		  position: absolute;
		  height: 36px;
		  bottom: 60px;
		  left: 36px;
		  right: 36px;
		  line-height: 34px;
		  text-align: center;
		  font-size: 14px;
		  color: #fff;
		  background-color: #00a8e9;
		  border-radius: 12px;
		}
		.game-list > .item > .hover > .actions {
		  position: absolute;
		  height: 36px;
		  bottom: 15px;
		  left: 36px;
		  right: 36px;
		}
		.game-list > .item > .hover > .actions > .transfers {
		  width: 46%;
		  height: 36px;
		  line-height: 34px;
		  text-align: center;
		  text-decoration: none;
		  font-size: 14px;
		  color: #fff;
		  background-color: #26b69d;
		  border-radius: 12px;
		  float: left;
		  cursor: pointer;
		}
		.game-list > .item > .hover > .actions > .transfers > i {
		  width: 22px;
		  height: 22px;
		  display: inline-block;
		  vertical-align: -5px;
		  margin-left: 10px;
		  background-image: url("${resourceRoot}/web/ybb/assets/img/live/game_live_icon.png");
		  background-repeat: no-repeat;
		  background-position: -80px -80px;
		}
		.game-list > .item > .hover > .actions > .transfers:hover {
		  background-color: #2cc6ab;
		}
		.game-list > .item > .hover > .actions > .enter {
		  width: 46%;
		  height: 36px;
		  line-height: 34px;
		  text-align: center;
		  font-size: 14px;
		  color: #fff;
		  background-color: #90d554;
		  border-radius: 12px;
		  float: right;
		  cursor: pointer;
		}
		.game-list > .item > .hover > .actions > .enter > i {
		  width: 22px;
		  height: 22px;
		  display: inline-block;
		  vertical-align: -5px;
		  margin-left: 10px;
		  background-image: url("${resourceRoot}/web/ybb/assets/img/live/game_live_icon.png");
		  background-repeat: no-repeat;
		  background-position: -102px -80px;
		}
		.game-list > .item > .hover > .actions > .enter:hover {
		  background-color: #90ea39;
		}
		.game-list > .item.big > .hover > .logo {
		  margin-top: 80px;
		}
		.game-list > .item.big > .hover > .text {
		  line-height: 24px;
		  font-size: 14px;
		}
		.game-list > .item.big > .hover > .money {
		  height: 50px;
		  bottom: 100px;
		  left: 36px;
		  right: 36px;
		  line-height: 48px;
		  font-size: 18px;
		  border-radius: 20px;
		}
		.game-list > .item.big > .hover > .actions {
		  height: 50px;
		  bottom: 30px;
		  left: 36px;
		  right: 36px;
		}
		.game-list > .item.big > .hover > .actions > .transfers {
		  height: 50px;
		  line-height: 48px;
		  font-size: 18px;
		  border-radius: 20px;
		}
		.game-list > .item.big > .hover > .actions > .enter {
		  height: 50px;
		  line-height: 48px;
		  font-size: 18px;
		  border-radius: 20px;
		}

		.game-list > .item > .logo.gd {
		    background-position: center -785px;
		}

		.game-list > .item > .hover > .logo.gd {
		    background-position: center -785px;
		}
</style>
    <div class="wrapper" >
		<div class="content-body" style="padding-top: 20px">
		<div class="row" style=" margin: auto; width: 1000px;">

			<div class="game-list noselect" style=" padding-left: 10px">
        <c:forEach var="item" items="${liveSite}">
				<div class="item top">
					<div class="img">
						<img src="${resourceRoot}/web/ybb/assets/img/live/live_bg_${item.flat }.jpg"></div>
					<div class="logo ${item.flat }" style="display: block;"></div>
					<div class="money" style="display: block;">${item.flatName }</div>
					<div class="hover" style="display: none; opacity: 1;">
						<div class="logo ${item.flat }"></div>
						<div class="text">${item.flatDes}</div>
						<div class="actions">
						<c:choose>
							<c:when test="${!empty webUser}">
								<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')" title="点击进入" class="enter">立即游戏</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏" class="enter">立即游戏</a>
							</c:otherwise>
						</c:choose>
						</div>
					</div>
				</div>
			  </c:forEach>
			</div>
		</div>
	  </div>
		</div>
		<script>
			var show = function() {
			  $(this).children('.logo').hide();
			  $(this).children('.money').hide();
			  $(this).find('.hover').stop().fadeIn(200);
			}
			var hide = function() {
			  $(this).children('.logo').show();
			  $(this).children('.money').show();
			  $(this).find('.hover').stop().fadeOut(200);
			}
			$('.game-list .item').hover(show, hide);
		</script>

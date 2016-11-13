<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style>
	.content-top-banner {
    margin-top: 56px;
    height: 441px;
  }
</style>
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
<style>
	  .live-hall-group {
	    padding-right: 23px;
	    padding-bottom: 23px;
	    overflow: hidden;
	    width: 975px;
	  }
	  .ybb-opacity-0 {
		  -webkit-opacity: 0;
		  -moz-opacity: 0;
		  -ms-opacity: 0;
		  -o-opacity: 0;
		  opacity: 0;
		}
		.live-spr{
		  background-image: url("${resourceRoot}/web/ybb/assets/img/live/live_spr.png");
		  background-repeat: no-repeat;
		}
    .live-hall-group {
		  padding-right: 23px;
		  padding-bottom: 23px;
		  overflow: hidden;
		  width: 975px;
		}
		.live-hall-group .logo {
		  margin-top: 0;
		}
		.live-hall-group .hall-item {
		  position: relative;
		  float: left;
		  margin: 23px 0 0 23px;
		  overflow: hidden;
		  width: 302px;
		  height: 250px;
		  background-color: #ccc;
		}
		.live-hall-group .hall-ag {
		  background-position: -302px 0;
		}
		.live-hall-group .hall-ag.active {
		  background-position: -302px -250px;
		}
		.live-hall-group .hall-ab.active {
		  background-position: 0 -250px;
		}
		.live-hall-group .hall-og {
		  background-position: -2114px 0;
		}
		.live-hall-group .hall-og.active {
		  background-position: -2114px -250px;
		}
		.live-hall-group .hall-ds {
		  background-position: -604px 0;
		}
		.live-hall-group .hall-ds.active {
		  background-position: -604px -250px;
		}
		.live-hall-group .hall-hg {
		  background-position: -906px 0;
		}
		.live-hall-group .hall-hg.active {
		  background-position: -906px -250px;
		}
		.live-hall-group .hall-bbin {
		  background-position: -1208px 0;
		}
		.live-hall-group .hall-bbin.active {
		  background-position: -1208px -250px;
		}
		.live-hall-group .hall-pt {
		  background-position: -1812px 0;
		}
		.live-hall-group .hall-pt.active {
		  background-position: -1812px -250px;
		}
		.live-hall-group .hall-mg {
		  background-position: -1510px 0;
		}
		.live-hall-group .hall-mg.active {
		  background-position: -1510px -250px;
		}
		.live-hall-group .hall-sa {
		  background-position: -2718px 0;
		}
		.live-hall-group .hall-sa.active {
		  background-position: -2718px -250px;
		}
		.live-hall-group .hall-thumb {
		  height: 100%;
		}
		.live-hall-group .hall-logo {
		  position: absolute;
		  left: 0;
		  z-index: 6;
		  text-align: center;
		  color: #fff;
		  background-color: #4A3602;
		}
		.live-hall-group .hall-logo {
		  bottom: 10px;
		  width: 160px;
		  height: 60px;
		  background-position: -100px bottom;
		}
		.live-hall-group .hall-logo .logo {
		  position: relative;
		  height: 60px;
		}
		.live-hall-group .hall-logo .logo span {
		  position: absolute;
		  top: 50%;
		  left: 50%;
		  margin-top: -20px;
		  display: block;
		  height: 40px;
		}

		/* hall logo */

		.live-hall-group .hall-ag .hall-logo .logo span {
		  margin-left: -38px;
		  width: 76px;
		  background-position: -528px -500px;
		}
		.live-hall-group .hall-ab .hall-logo .logo span {
		  margin-top: -25px;
		  margin-left: -40px;
		  width: 80px;
		  height: 50px;
		  background-position: -222px -500px;
		}
		.live-hall-group .hall-og .hall-logo .logo span {
		  margin-left: -24px;
		  width: 48px;
		  background-position: -2066px -500px;
		}
		.live-hall-group .hall-ds .hall-logo .logo span {
		  margin-left: -47px;
		  width: 94px;
		  background-position: -812px -500px;
		}
		.live-hall-group .hall-hg .hall-logo .logo span {
		  margin-left: -59px;
		  width: 118px;
		  background-position: -1090px -500px;
		}
		.live-hall-group .hall-bbin .hall-logo .logo span {
		  margin-left: -39px;
		  width: 78px;
		  background-position: -1432px -500px;
		}
		.live-hall-group .hall-pt .hall-logo .logo span {
		  margin-left: -72px;
		  width: 143px;
		  background-position: -2114px -590px;
		}
		.live-hall-group .hall-mg .hall-logo .logo span {
		  margin-left: -61px;
		  width: 122px;
		  background-position: -1510px -590px;
		}
		.live-hall-group .hall-sa .hall-logo .logo span {
		  margin-left: -61px;
		  width: 122px;
		  background-position: -2718px -590px;
		}
		.live-hall-group .hall-logo-big {
		  position: absolute;
		  top: 80px;
		  left: 50%;
		  z-index: 7;
		  height: 90px;
		}

		/* hall overlay logo */

		.live-hall-group .hall-ag .hall-logo-big {
		  margin-left: -66px;
		  width: 132px;
		  background-position: -302px -500px;
		}
		.live-hall-group .hall-ab .hall-logo-big {
		  margin-left: -72px;
		  width: 144px;
		  background-position: 0 -500px;
		}
		.live-hall-group .hall-og .hall-logo-big {
		  margin-left: -55px;
		  width: 110px;
		  background-position: -1812px -500px;
		}
		.live-hall-group .hall-ds .hall-logo-big {
		  margin-left: -82px;
		  width: 164px;
		  background-position: -604px -500px;
		}
		.live-hall-group .hall-hg .hall-logo-big {
		  margin-left: -87px;
		  width: 174px;
		  background-position: -906px -500px;
		}
		.live-hall-group .hall-bbin .hall-logo-big {
		  margin-left: -91px;
		  width: 182px;
		  background-position: -1208px -500px;
		}
		.live-hall-group .hall-pt .hall-logo-big {
		  margin-left: -94px;
		  width: 187px;
		  background-position: -2114px -500px;
		}
		.live-hall-group .hall-mg .hall-logo-big {
		  margin-left: -122px;
		  width: 244px;
		  background-position: -1510px -500px;
		}
		.live-hall-group .hall-sa .hall-logo-big {
		  margin-left: -122px;
		  width: 244px;
		  background-position: -2718px -500px;
		}
		.live-hall-group .hall-intro {
		  position: absolute;
		  top: 120px;
		  left: 0;
		  z-index: 7;
		  margin: 0 5%;
		  width: 90%;
		  text-align: center;
		  color: #fff;
		}
		.live-hall-group .hall-enter {
		  position: absolute;
		  left: 0;
		  bottom: -60px;
		  z-index: 7;
		  width: 160px;
		  height: 60px;
		  background-position: -260px bottom;
		}
		.live-hall-group .hall-enter a {
		  display: block;
		  height: 100%;
		}
		.live-hall-group .hall-overlay {
		  position: absolute;
		  top: 0;
		  left: 0;
		  width: 100%;
		  height: 100%;
		  background-color: #000;
		}

		.live-hall-group .hall-item.hall-gd {
		    background-image: url("${resourceRoot}/web/ybb/assets/img/live/mgm-gd.png");
		}

		.live-hall-group .hall-item.hall-gd.active {
		    background-image: url("${resourceRoot}/web/ybb/assets/img/live/mgm-gd.png");
		}

		.live-hall-group .hall-gd .hall-logo .logo span {
		    width: 123px;
		    background-position: -2352px -598px;
		    margin-left: -74px;
		}

		.live-hall-group  .hall-gd .hall-logo-big {
		    background-position: -2509px -501px;
		    margin-left: -102px;
		    width: 200px;
		}
</style>
<div class="content-body" style="padding-top: 20px">
	<div class="live-hall-group cf" style="margin:0 auto">
		<c:forEach var="item" items="${liveSite}">
			<div class="hall-item hall-${item.flat} live-spr">
				<div class="hall-thumb"></div>
				<div class="hall-logo live-spr" style="left: 0px; opacity: 1;">
					<div class="logo">
						<span class="live-spr"></span>
					</div>
				</div>
				<div class="hall-logo-big live-spr ybb-opacity-0" style="top: 80px; opacity: 0;"></div>
				<div class="hall-intro ybb-opacity-0" style="top: 120px; opacity: 0;">
					<p>${item.flatDes}</p>
				</div>
				<c:choose>
					<c:when test="${!empty webUser}">
						<div class="hall-enter live-spr" style="bottom: -60px; opacity: 0;">
							<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')" title="点击进入" class="enter">立即游戏</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="hall-enter live-spr" style="bottom: -60px; opacity: 0;">
							<a onclick="alert('您尚未登录，请先登录再进行游戏')" href="javascript:void(0)" title="开始游戏"></a>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="hall-overlay ybb-opacity-0" style="opacity: 0;"></div>
			</div>
		</c:forEach>
	</div>
</div>
		<script>
				var liveHIRHide = function(){
				  $(this).addClass('active');
				  $(this).children('.hall-logo').stop().animate({left: '-160px', opacity: 0}, 300);
				  $(this).children('.hall-logo-big').stop().animate({top: '20px', opacity: 1}, 600);
				  $(this).children('.hall-intro').stop().animate({top: '130px', opacity: 1}, 600);
				  $(this).children('.hall-enter').stop().animate({bottom: '10px', opacity: 1}, 200);
				  $(this).children('.hall-overlay').stop().animate({opacity: .6}, 500);
				};
				var liveHIRShow = function(){
				  $(this).removeClass('active');
				  $(this).children('.hall-logo').stop().animate({left: '0', opacity: 1}, 400);
				  $(this).children('.hall-logo-big').stop().animate({top: '80px', opacity: 0}, 250);
				  $(this).children('.hall-intro').stop().animate({top: '120px', opacity: 0}, 250);
				  $(this).children('.hall-enter').stop().animate({bottom: '-60px', opacity: 0}, 200);
				  $(this).children('.hall-overlay').stop().animate({opacity: 0}, 200);
				};
				$('.live-hall-group .hall-item').hover(liveHIRHide, liveHIRShow);
		</script>

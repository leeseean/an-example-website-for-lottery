<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
		.logined {
		background: url("${resourceRoot }/web/ybb/assets/img/main/loginedbg.png") no-repeat;
		width: 250px;
		height: 202px;
		float: right;
		margin-top: 200px;
		overflow: hidden;
		line-height: 30px;
		padding-left: 30px;
		padding-top: 20px;
		position: absolute;
    top: -3%;
    right: 18.5%;
   }
		/*
		position: absolute;
		right: 0px;
		z-index: 100;
		*/
		}

		.logined p {
			color: #ffefa5!important;
			font-size: 13px;
		}

		.logined p span {
			color: #fff100!important;
		}

		.logined p a {
			color: #ffefa5!important;
			font-size: 13px;
		}

		.logined .exit {
			background: #ffe469!important;
			border-radius: 5px;
			width: 110px;
			height: 38px;
			display: block;
			float: left;
			margin-top: 20px;
			margin-left: 50px;
			color: #352c00;
			font-size: 15px;
			font-weight: bold;
			text-align: center;
			line-height: 38px;
		}

    .login {
			background: url("${resourceRoot }/web/ybb/assets/img/login/loginbg.png") no-repeat;
			width: 280px;
			height: 298px;
			/* float: right; */
			margin-top: 160px;
			overflow: hidden;
			position: absolute;
			z-index: 100;
			right: 18.5%;
      top: 0;
		}
		.login .button {
			background: url("${resourceRoot }/web/ybb/assets/img/login/buttonbtn.png") top center no-repeat;
			width: 259px;
			height: 46px;
			display: block;
			line-height: 46px;
			color: #fff;
			font-size: 18px;
			margin: 10px auto;
		}

		.login .button a {
			font-size: 18px;
			color: #fff;
			display: block;
			width: 90px;
			float: left;
			height: 46px;
			line-height: 44px;
			padding-left: 40px;
			font-weight: bold
		}

		.login .button a.freeplay {
			padding-left: 25px;
			width: 100px;
		}

		.login .button a:hover {
			color: #fefba6
		}

		.login p {
			height: 48px;
			line-height: 48px;
			margin-bottom: 1px
		}

		.login p input {
			width: 160px;
			margin-left: 50px;
			height: 48px;
			line-height: 48px;
			background: none;
			border: none;
			color: #707070;
			font-size: 15px;
			display: block;
			float: left;
		}

		.login p a.forget {
			width: 44px;
			height: 20px;
			display: block;
			text-align: center;
			line-height: 20px;
			color: #a0a0a0;
			border: 1px solid #4d4b4a;
			float: left;
			margin-top: 15px
		}

		.login .subbtn {
			background: url("${resourceRoot }/web/ybb/assets/img/login/subbtn.png") top center no-repeat;
			width: 259px;
			height: 46px;
			line-height: 40px;
			text-align: center;
			border: none;
			color: #282203;
			font-size: 18px;
			font-family: "微软雅黑";
			cursor: pointer;
			margin: 10px auto;
			display: block;
		}

		.login .subbtn:hover {
			background-position: bottom center
		}

</style>
<div class="row">
	<div class="wrapper" style="position: relative;">
		<div class="slider slider-home"><!-- plugin="superslide" plugin-data-pager=".links-pager ul" -->
			<ul class="bd clearfix">
			  <li><img src="${resourceRoot }/web/ybb/assets/img/main/indexbanner.jpg" alt="" /></li>
				<!-- <li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban1.jpg" alt="" /></li>
				<li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban2.jpg" alt="" /></li>
				<li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban3.jpg" alt="" /></li>
				<li><img src="${resourceRoot }/web/ybb/assets/img/slider/ban4.jpg" alt="" /></li> -->
			</ul>
			<div class="links-pager">
				<ul></ul>
			</div>
		</div>
		<c:choose>
		<c:when test="${!empty webUser}">
    <div class="logined">
				<p style="color: #ffefa5">
					账  号：<span>${webUser.userName}</span><br>
					账户余额：<span id="userMoneyId" style="cursor: pointer;">${webUser.userMoney}RMB</span>
				</p>
        <p style="color: #ffefa5">
        	<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main')">会员中心</a> |
        	<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=1')">线上存款</a> |
        	<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=2')">线上取款</a><br>
        	<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=3')">额度转换</a> |
        	<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=4')">投注记录</a> |
        	
        	<a href="${ctx}/member/main?type=5" target="_blank">未读消息(<label id="userMessageId">0</label>)</a>
        </p>
        <a href="javascript:void(0);" class="exit" onclick="loginOut();return false">退出账户</a>
		</div>
		<script type="text/javascript">
      setInterval('refreshUserMoney()', 10000);

      function GoToMember(url) {
        window.open(url, "memberFrame");
      }
    </script>
		</c:when>
		<c:otherwise>
			<form id="loginForm" class="login-form from">
				<mh:token></mh:token>
				<div class="login">
					<div class="button">
						<a href="javascript:void(0);" onclick="Go('${ctx}/register', 'register')">立即注册</a>
						<a href="javascript:void(0);" onclick="winOpenMessage('${ctx}/goMobile?code=tyc','mobile','top=0,left=0,width=800,height=502,resizable=no')" class="freeplay">手机投注</a>
					</div>
					<p style="margin-top: 30px;">
						<input type="text" tabindex="1" maxlength="12" id="loginName" name="loginName"  value="" placeholder="帐号" plugin-placeholder="账号" class="user" style="color: white"></p>
					<p>
						<input type="password" tabindex="2" maxlength="12" value="" plugin-placeholder="密码"  id="password" name="password" style="color: white">
						<a href="javascript:void(0);" onclick="alert('请联系24小时在线客服找回密码')" class="forget">忘记?</a>
					</p>
					<p>
						<input type="text" tabindex="3" name="verifycode"  id="verifycode" plugin-placeholder="验证码" maxlength="4"  class="yzm" style="color: white">
						<label id="validateCodeLabelId" class="clearfix" style="height:25px;position: absolute;top: 64%;right: 6%" plugin-refreshLoginCode=">
							img, ${ctx}/resources-code.jpg" >
							<img src="" class="randPic" id="img_validateCode"></label>
						<!-- <img alt="验证码" onclick="hd_reloadCode2();" id="hd_vPic" src="/validCode" style="height:25px;margin-left:-15px;margin-top:13px;position: absolute;">-->
					</p>
					<input type="button" value="登录游戏" class="subbtn" onclick="login();">
			  </div>
			</form>
		</c:otherwise>
	  </c:choose>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		<%@include file="msg.jsp" %>
	</div>
</div>

<!-- <div class="row">
	<div class="wrapper">
		<div class="heibg">
			<div class="links-index-navs links w888 align-h">
				<ul class="clearfix">
					<li class="item item-1"><a onclick="Go('${ctx}/goPageCenter?code=live')"><img src="${resourceRoot }/web/ybb/assets/img/jinru.png" alt="" /></a></li>
					<li class="item item-2"><a onclick="Go('${ctx}/goPageCenter?code=sport')"><img src="${resourceRoot }/web/ybb/assets/img/jinru.png" alt="" /></a></li>
					<li class="item item-3"><a onclick="Go('${ctx}/electronic?code=mg')"><img src="${resourceRoot }/web/ybb/assets/img/jinru.png" alt="" /></a></li>
					<li class="item item-4"><a href="${ctx}/goPageCenter?code=lottery_index"><img src="${resourceRoot }/web/ybb/assets/img/jinru.png" alt="" /></a></li>
				</ul>
			</div>

			<div class="links-index-services links w1040 align-h">
				<ul class="clearfix">
					<li class="item item-1"><a onclick="Go('${ctx}/goPageCenter?code=help&pgSn=deposit')">如何存款</a></li>
					<li class="item item-2"><a onclick="Go('${ctx}/goPageCenter?code=help&pgSn=withdrawals')">如何提款</a></li>
					<li class="item item-3"><a href="${ctx}/lineCheck" target="_blank">线路检测</a></li>
					<li class="item item-4"><a href="javascript:void(0)" onclick="winOpenMessage('${ctx}/goMobile?code=tyc','mobile','top=0,left=0,width=800,height=502,resizable=no')" >手机投注</a></li>
					<li class="item item-5"><a href="#" target="_blank">澳门体现</a></li>
				</ul>
			</div>
		</div>
	</div>
</div> -->
<style type="text/css">
  .main01 {
    height: 400px;
    overflow: hidden;
  }
  .w1000 {
    width: 1000px;
    margin: 0 auto;
  }
  .main01 .left {
    float: left;
    width: 600px;
	}
	.main01 .title {
    height: 50px;
    line-height: 50px;
    background: url("${resourceRoot }/web/ybb/assets/img/main/title.png") left center no-repeat;
    padding-left: 25px;
    color: #333333;
    font-size: 16px;
	}
	.main01 .title span {
    color: #898989;
    font-family: Arial;
    float: right;
    font-size: 12px;
	}
	.tab {
    width: 600px;
	}
	.tab .bd ul {
	zoom: 1;
	}
  .tab .bd ul img {
  display: block;
	}
	.tab .hd {
    height: 33px;
    line-height: 33px;
    background: #2c2c2c;
    position: relative;
	}
	.tab .hd ul {
    float: left;
    height: 33px;
	}

  .tab .hd ul li {
    float: left;
    cursor: pointer;
    width: 120px;
    text-align: center;
    color: #fff;
    font-size: 14px;
	}
	.tab .hd ul li.on {
	background: url("${resourceRoot }/web/ybb/assets/img/main/tablion.png") no-repeat;
	color: #594701
	}
	.tab .hd ul li.even {
	background: #3f3e3e
	}
	.main01 .right {
    float: right;
    width: 370px;
	}
	.tab img {
  cursor: pointer;
	}
  .main01 .right ul li {
    height: 60px;
    line-height: 60px;
    margin-bottom: 5px;
    background: url("${resourceRoot }/web/ybb/assets/img/main/mainrightli.png") top center no-repeat;
	}
	.main01 .right ul li a {
    color: #fff;
    font-size: 16px;
    height: 60px;
    display: block;
	}
	.main01 .right ul li a span {
    font-family: Arial;
    font-size: 35px;
    color: #ecdf87;
    width: 30px;
    padding-left: 20px;
    display: block;
    float: left;
	}
	.main01 .right ul li:hover {
  background-position: bottom center
	}

	.main01 .right ul li:hover a {
	  color: #594701
	}

	.main01 .right ul li:hover span {
	  color: #594701
	}
</style>
<script type="text/javascript" src="${resourceRoot}/web/ybb/assets/js/jquery.SuperSlide.2.1.1.js">

</script>

<!-- <div class="main01">
	<div class="w1000">
		<div class="left">
			<div class="title" style="color: #ecdf87">
				热门游戏推荐
				<span>HOT RECOMMENDED</span>
			</div>
			<div class="tab">
				<div class="tempWrap" style="overflow:hidden; position:relative; width:600px">
					<div class="bd" style="width: 4200px; position: relative; overflow: hidden; padding: 0px; margin: 0px; left: -2400px;">
						<c:forEach var="item" items="${slotSite}" begin="0" end="4" varStatus="i">
							<ul style="float: left; width: 600px;">
								<img src="${resourceRoot }/web/ybb/assets/img/main/tab0${i.index+1}.png" onclick="Go('${ctx}/${item.flatUrl}')"></ul>
						</c:forEach>

					</div>
				</div>
				<div class="hd">
					<ul>
						<c:forEach var="item" items="${slotSite}" begin="0" end="4" varStatus="i">

							<li class="ui-item-son slot-${item.flat }">
								<a href="${ctx}/${item.flatUrl}" class="ui-link-son">${item.flatName }</a>
							</li>
						</c:forEach>

					</ul>
				</div>
			</div>
		</div>
		<div class="right">
			<div class="title" style="color: #b99b3a">
				热门优惠
				<span>PROMOTIONS</span>
			</div>
			<ul>
				<li class="li1">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')">
						<span>1</span>
						公司入款&amp;在线支付赠送2%，业界最高
					</a>
				</li>
				<li class="li2">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')">
						<span>2</span>
						鸿运礼金，未赌先赢，首存10元送20
					</a>
				</li>
				<li class="li3">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')">
						<span>3</span>
						MG电子游艺，投注1元起独家返水1.5%
					</a>
				</li>
				<li class="li4">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')">
						<span>4</span>
						永利全新VIP系统闪耀开启，人人参与
					</a>
				</li>
				<li class="li5">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')">
						<span>5</span>
						情义相挺，月月大返利58888元月月送
					</a>
				</li>
			</ul>
		</div>
	</div>
</div> -->
<!-- 	<script type="text/javascript">
		jQuery(".tab").slide({effect:"leftLoop",autoPlay:true,trigger:"click"});
		$(document).ready(function(){
			$(".headbottom").fadeOut(0);
		})
		$(document).scroll(function(){
	 		if($(document).scrollTop()>=350)
	 		{
	 			$(".headbottom").fadeIn();
	 		}
	 		else
	 		{
	 			$(".headbottom").fadeOut();
	 		}
		});
		var options = {
			$FillMode: 2,
			$AutoPlay: true,
			$AutoPlayInterval:5000,
			$PauseOnHover: 1,
			$ArrowKeyNavigation: true,
			$SlideEasing: $JssorEasing$.$EaseOutQuint,
			$SlideDuration: 800,
			$MinDragOffsetToSlide: 20,
			//$SlideWidth: 600,
			$SlideHeight: 588,
			$SlideSpacing: 0,
			$DisplayPieces: 1,
			$ParkingPosition: 0,
			$UISearchMode: 1,
			$PlayOrientation: 1,
			$DragOrientation: 1,
			$BulletNavigatorOptions: {
			$Class: $JssorBulletNavigator$,
			$ChanceToShow: 2,
			$AutoCenter: 1,
			$Steps: 1,
			$Lanes: 1,
			$SpacingX: 2,
			$SpacingY: 2,
			$Orientation: 1,
			$Scale: false
		  },

			$ArrowNavigatorOptions: {
				$Class: $JssorArrowNavigator$,
				$ChanceToShow: 1,
				$AutoCenter: 2,
				$Steps: 1
			}
		};

  </script> -->
  <style>
		.process {
		  height: 105px;
		  border-bottom: 1px solid #252525;
		  border-top: 1px solid #252525;
		  background: #181818 url("${resourceRoot }/web/ybb/assets/img/main/process.png") center center
		    no-repeat;
		}

		.process a {
		  width: 60px;
		  display: block;
		  float: left;
		  line-height: 30px;
		  font-family: Arial;
		  margin-right: 175px;
		  margin-top: 20px;
		  text-align: center;
		}

		.process a span {
		  width: 44px;
		  height: 44px;
		  display: block;
		  text-align: center;
		  line-height: 44px;
		  font-size: 13px;
		  color: #fff;
		  background: url("${resourceRoot }/web/ybb/assets/img/main/processico.png") top center no-repeat;
		  font-family: "微软雅黑";
		  margin: 0 auto;
		}

		.process a:hover span {
		  background-position: bottom center;
		  color: #2c1f01;
		}

  </style>
	<!-- <div class="process">
		<div class="w1000">
			<a href="javascript:void(0);" style="margin-left:0"><span>开户</span>ACCOUNT</a>
			<a href="javascript:void(0);"><span>绑定</span>BINDING</a>
			<a href="javascript:void(0);"><span>存款</span>DEPOSIT</a>
			<a href="javascript:void(0);"><span>游戏</span>BETTING</a>
			<a href="javascript:void(0);" style="margin-right:0"><span>提款</span>DRAWING</a>
		</div>
	</div> -->

  <style>
		.indexabout {
		  background: url("${resourceRoot }/web/ybb/assets/img/main/about.png") no-repeat;
		  height: 323px;
		  cursor: pointer;
		}
    /* .indexabout:hover{
    	background: url("${resourceRoot }/web/ybb/assets/img/main/about2.png") no-repeat center;
    	height: 700px
    } */
		.indexabout .left {
		  float: left;
		  width: 280px;
		  text-align: left;
		}

		.indexabout h2 {
		  font-size: 18px;
		  color: #fff;
		  font-weight: normal;
		  margin-top: 55px;
		}

		.indexabout span {
		  margin-top: 20px;
		  height: 3px;
		  width: 20px;
		  display: block;
		  background: #f2af41
		}

		.indexabout p {
		  margin-top: 15px;
		  line-height: 24px;
		  color: #969696
		}

		.indexabout a {
		  display: block;
		  margin-top: 25px;
		  line-height: 25px;
		  color: #b99b3a;
		  font-size: 18px;
		}

		.indexabout a img {
		  display: block;
		  float: left;
		  position: relative;
		  top: 7px;
		  margin-right: 8px;
		}

		.indexabout a b {
		  font-weight: normal;
		  font-family: Arial
		}

		.indexabout .right {
		  float: right;
		  width: 280px;
		  text-align: right;
		}

		.indexabout .right span {
		  float: right;
		}

		.indexabout .right a img {
		  float: right;
		  margin-left: 10px;
		  margin-right: 0
		}
    .absolute{position: absolute}
    .bginfo1{
    	 cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info1.png") no-repeat;
    }
    .bginfo1:hover,.bginfo1:active{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info2.png") no-repeat;
    }
    .bginfo2{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info3.png") no-repeat;
    }
    .bginfo2:hover,.bginfo2:active{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info4.png") no-repeat;
    }
    .bginfo3{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info9.png") no-repeat;
    }
    .bginfo4{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info5.png") no-repeat;
    }
    .bginfo4:hover,.bginfo4:active{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info6.png") no-repeat;
    }
    .bginfo5{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info7.png") no-repeat;
    }
    .bginfo5{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info7.png") no-repeat;
    }
    .bginfo5:hover,.bginfo5:active{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info8.png") no-repeat;
    }
    .bginfo6{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info10.png") no-repeat;
    }
    .bginfo6:hover,.bginfo6:active{
    	cursor: pointer;
    	 background: url("${resourceRoot }/web/ybb/assets/img/main/info11.png") no-repeat;
    }
    .word-gold{
    	font-size: 18px;
    	color: #e9db82;
    	margin-bottom: 4%;
    }
    .word-up{
    	font-size: 15px;
    	margin-top: 17%;
      line-height: 28px;
    }
    .word-down{
    	font-size: 14px;
    	transform: scale(0.8);
    }
  </style>
  <div style="border-bottom: 1px solid #282828;">
  <div class="w1000" style="position: relative;height: 380px;margin-top: 1.5%">
  	<div class="absolute bginfo1" style="width: 239px;height: 209px;left: 0%" onclick="Go('${ctx}/goPageCenter?code=live', 'live')"></div>
  	<div class="absolute bginfo2" style="width: 239px;height: 209px;left: 19%;bottom: 14%" onclick="Go('${ctx}/electronic?code=mg', 'electronic')"></div>
  	<div class="absolute bginfo3" style="width: 239px;height: 209px;left: 38%"></div>
  	<div class="absolute bginfo4" style="width: 239px;height: 209px;right: 19%;bottom: 14%" onclick="Go('${ctx}/goPageCenter?code=lottery_index')"></div>
  	<div class="absolute bginfo5" style="width: 239px;height: 209px;right: 0" onclick="Go('${ctx}/goPageCenter?code=sport', 'sport')"></div>
  	<div class="absolute bginfo6" style="width: 226px;height: 103px;left: 38.6%;bottom: 14%" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')"></div>
  	<div style="color: white;text-align: center;width: 226px;height: 103px;left: 20%" class="absolute">
  		<p class="word-up">官方直营 值得信赖</p>
  		<p class="word-down">MACAO WYNN</p>
  	</div>
  	<div style="color: white;text-align: center;width: 226px;height: 103px;bottom: 0; margin-bottom: 3%;margin-left: 1%;" class="absolute">
  		<p class="word-gold">澳门很远</p>
  		<p class="word-down">MACAO WYNN<br>ENTERTAINMENT CITY</p>
  	</div>
  	<div style="color: white;text-align: center;width: 226px;height: 103px;right: 0;bottom: 0;margin-bottom: 3%;" class="absolute">
  		<p class="word-gold">永利很近</p>
  		<p class="word-down">MACAO WYNN<br>ENTERTAINMENT CITY</p>
  	</div>
  	<div style="color: white;text-align: center;width: 226px;height: 103px;right: 20.1%" class="absolute">
  		<p class="word-up">更多平台 更多选择</p>
  		<p class="word-down">MACAO WYNN</p>
  	</div>
  </div>
	</div>
  <div class="indexabout w1000">
		<div class="left">
			<h2>关于永利</h2>
			<span></span>
			<p>永利娱乐场与OG BBIN AG MG PT DS AB GD HG SA OS TTG IBC SBTech等进行技术合作，共同打造高质量游戏平台，目前拥有哥斯特黎加合法注册之博彩公司。选择永利娱乐场，绝对是玩家的首选。</p>
			<a href="http://www.7331.cc" target="_blank"><img src="${resourceRoot }/web/ybb/assets/img/main/bowserico.png">官网域名<br><b>www.7331.cc</b></a>
		</div>
		<div class="right">
			<h2>品牌实力</h2>
			<span></span>
			<div class="clear"></div>
			<p>永利娱乐场是全世界最大的赌场之一，大额无忧，快速存取款，玩家数据安全有保障，7X24小时在线客服为您提供帮助。澳门很远，永利很近，让你体验亲临澳门般的感觉。</p>
			<a href="http://www.936.cc" target="_blank"><img src="${resourceRoot }/web/ybb/assets/img/main/bowserico.png">认证域名<br><b>www.936.cc</b></a>
		</div>
	</div>
	<div>
		<div class="w1000">
			<img src="${resourceRoot }/web/ybb/assets/img/main/about1.png" alt="">
		</div>
	</div>
  <script>
    $('.indexabout').click(function(event) {
    	$('.dialog-official-cert').dialog('open');
      $('.ui-widget-overlay').css('background','none');
		  return false;
    });
  </script>

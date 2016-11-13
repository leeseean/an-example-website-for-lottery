<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>
<%
String reFlag = (String) request.getSession().getAttribute("PARAM_S");
if ("0".equals(reFlag)) {
	request.getSession().removeAttribute("PARAM_S");
	out.flush();
	out.println("<script>");
	out.println("alert('您尚未登录，请先登录再进行游戏!');");
	out.println("</script>");
}
%>
<script type="text/javascript">
var web_online_contact = "${ctxMap['msg007'] }";
var web_qq_contact = "${ctxMap['siteQq'] }";

</script>

<style>
		.header {
		  height: 105px;
		  position: fixed;
		  top: 0;
		  left: 0;
		  width: 100%;
		  z-index: 999;
		}

		.header .head {
		  height: 103px;
		  background: #0e0e0e;
		  border-bottom: 1px solid #2c2c2c;
		  position: relative
		}

		.header .w1000 .logo1 {
		  background: url("${resourceRoot }/web/ybb/assets/img/logo/logo.png") no-repeat;
		  width: 196px;
		  height: 103px;
		  display: block;
		  float: left;
		}

		.header .headright {
		  float: right;
		  width: 790px;
		  height: 103px;
		}

		.righttop {
		  margin-top: 23px;
		  height: 24px;
		  line-height: 24px;
		  /* width: 350px; */
		  float: right;
		}

		.righttop .link {
		  color: #707070;
		  float: left;
		  margin: -15px 0;
		}

		.righttop .link a {
		  color: #FCF5A1;
		  margin: 0 5px;
		  transition: all 0.4s
		}

		.righttop .link a:hover {
		  color: #eee
		}

		.righttop .lang {
		  float: left;
		  margin-left: 7px;
		}

		.righttop .lang a {
		  width: 24px;
		  height: 24px;
		  display: block;
		  float: left;
		  text-align: center;
		  line-height: 24px;
		  color: #707070;
		  margin-left: 3px;
		}

		.righttop .lang a.on {
		  background: #ecdf87;
		  border-radius: 24px;
		  color: #2d2701
		}

		.menu {
		  height: 55px;
		  line-height: 55px;
		  width: 790px;
		}

		.menu ul {
		  width: 800px;
		  margin-left: 4px;
		}

		.menu ul li {
		  float: left;
		  margin-left: 22.7px;
		  position: relative;
		}

		.menu ul li a {
		  color: #eee;
		  font-size: 14px;
		  transition: all 0.4s
		}

		.menu ul li a.color1 {
		  color: #5fe976
		}

		.menu ul li a.color2 {
		  color: #fff100
		}

		.menu ul li a.color3 {
		  color: #83eff4
		}

		.menu ul li a.color4 {
		  color: #fb293a
		}

		.menu ul li a.color5 {
		  color: #55a5d8
		}

		.menu ul li:hover a {
		  color: #fff100
		}

		.hot {
		  position: absolute;
		  right: -20px;
		  top: 10px;
		}

		.menu .box {
		  position: absolute;
		  left: 0px;
		  top: 103px;
		  width: 100%;
		  overflow: hidden;
		  height: 0;
		  filter: alpha(opacity = 0);
		  opacity: 0;
		  background: url(../images/menubox.png) bottom center repeat-x;
		  z-index: 999;
		  border-bottom: 1px solid #2c2c2c;
		  height: 150px;
		}

		.menu .cont {
		  position: relative;
		  height: 150px;
		  width: 1000px;
		  margin: 0 auto;
		}

		.menu .cont a {
		  display: block;
		  position: absolute;
		  bottom: 0px;
		  height: 43px;
		  line-height: 43px;
		  color: #dcdcdc;
		  font-size: 12px;
		}

		.menu .cont a:hover {
		  color: #fff100
		}

		.menu .cont a.img {
		  display: block;
		  position: absolute;
		  top: 15px;
		  height: 80px;
		}

		.menulive {
		  background: url(../images/menulive.png) no-repeat;
		}

		.menuslots {
		  background: url(../images/menuslots.png) no-repeat;
		}

		.menusports {
		  background: url(../images/menusports.png) no-repeat;
		}

		.menulottery {
		  background: url(../images/menulottery.png) no-repeat;
		}
		.headlogin {
			height: 42px;
			border-bottom: 1px solid #2c2c2c;
			background: #040404;
			border-top: 1px solid #2c2c2c;
			overflow: hidden;
		}

		.headlogin input {
			border: 1px solid #626262;
			width: 134px;
			height: 24px;
			background: none;
			color: #707070;
			padding-left: 10px;
			float: left;
			margin-top: 8px;
			margin-right: 5px;
		}

		.headlogin .yzmimg {
			position: relative;
			margin-left: -66px;
			margin-top: 10px;
			float: left;
			width: 45px;
		}

		.headlogin .yzm {
			width: 115px
		}

		.headlogin .subbtn {
			width: 83px;
			height: 26px;
			background: url("${resourceRoot }/web/ybb/assets/img/head/subbtn.png") no-repeat;
			border: none;
			padding: 0;
			color: #54300d;
			cursor: pointer;
		}

		.headlogin .regbtn {
			width: 83px;
			height: 26px;
			background: url("${resourceRoot }/web/ybb/assets/img/head/regbtn.png") no-repeat;
			border: none;
			padding: 0;
			color: #e9d87b;
			display: block;
			float: left;
			line-height: 26px;
			text-align: center;
			margin-top: 8px;
		}

		.headlogin .wz {
			background: url("${resourceRoot }/web/ybb/assets/img/head/wz.png") left center no-repeat;
			width: 242px;
			height: 42px;
			float: left;
		}

		.headlogin .forget {
			color: #aaa;
			display: block;
			float: left;
			line-height: 42px;
			height: 42px;
			width: 70px;
			text-align: center;
		}
    .headlogin .hlogined {
			float: right;
			height: 40px;
			line-height: 40px;
			color: #dac085;
			width: 700px;
			text-align: right
		}

		.headlogin .hlogined span {
			color: #fff100
		}

		.headlogin .hlogined a {
			color: #dac085
		}

</style>
<!-- header -->
<div class="head" id="headCert" >
	<!-- <div style="positon:absolute;left:34%;height: 700px;width: 490px;margin: 0 auto;z-index: 2222;display: none" id="cert">
		<img src="${resourceRoot }/web/ybb/assets/img/main/about2.png"></div> -->
	<div class="w1000 ">
		<a href="javascript:void(0);" onclick="Go('${ctx}/index', 'index')" class="logo1"></a>
		<div class="headright">
			<div class="righttop">
				<div class="link" >
					<a href="javascript:void(0);" class="official-cert">博彩执照展示</a>
					|
					<a href="${ctx }/test/main" style="color: red" ><!-- style="width:110px;" -->
						免费试玩
						<!-- <img src="${resourceRoot }/web/ybb/assets/img/head/hot.gif" style="margin: 10px 0;margin-right: -17px;"> --></a>
					|
					<a href="http://www.7331.cc" style="color: #FDF027" target="_blank">简易网址</a>
					|
					<a href="${ctx }/lineCheck" style="color: #FDF027" target="_blank">线路检测</a>
					|
					<a href="javascript:void(0);" onclick="Go('${ctx}/goPageCenter?code=help&pgSn=agent', 'agent')">代理加盟</a>
					
					|
					
					<a href="${ctxMap['msg007'] }" target="_blank" style="color: red">在线客服</a>
					|
					<a href="javascript:void(0);" id="setHome" onclick="setFirst('http://'+ window.location.hostname)">设为首页</a>
					|
					<a href="javascript:void(0);" onclick="bookMarksite('http://${ctxMap['lckMainDomain'] }',document.title);">加入收藏</a>
					
				</div>
				<!-- <div class="lang">
				<a href="javascript:void(0);" class="on">EN</a>
				<a href="javascript:void(0);">简</a>
				<a href="javascript:void(0);">繁</a>
			</div>
			-->
		</div>
		<div class="clear"></div>
		<div class="menu" style="margin-left: 7px" plugin-navSubmenu=".submenu-wrapper, .header >
			.head">
			<div class="list" id="menulist">
				<ul>
					<li>
						<a href="${ctx}/index">首页</a>
					</li>
					
					<li>
						<a href="${ctx}/electronic?code=pt" class="tg4">PT老虎机</a>
					</li>
					<li>
						<a href="${ctx}/electronic?code=mg" class="tg4">MG老虎机</a>
					</li>
					<li>
						<a href="${ctx}/goPageCenter?code=fishinggame" class="ui-link">对战游戏
            <img src="${resourceRoot }/web/ybb/assets/img/head/hot.gif" class="hot">
						</a>
					</li>
					<li class="has-submenu-3">
						<a href="javascript:void(0);" class="tg5" onclick="Go('${ctx}/electronic?code=mg', 'electronic')">电子游艺</a>
					</li>
					<li class="has-submenu-2">
						<a href="javascript:void(0);" class="tg3" onclick="Go('${ctx}/goPageCenter?code=live', 'live')">真人视讯</a>
					</li>
					<li class="has-submenu-1">
						<a href="javascript:void(0);" class="tg1" onclick="Go('${ctx}/goPageCenter?code=sport', 'sport')">
							体育赛事
						</a>
					</li>
					
					
					<li class="has-submenu-4">
						<a href="${ctx}/goPageCenter?code=lottery_index" class="ui-link tg2">彩票娱乐</a>
					</li>
					
					<li>
						<a href="javascript:void(0);" onclick="winOpenMessage('${ctx}/goMobile?code=tyc','mobile','top=0,left=0,width=800,height=502,resizable=no')" class="animation-service-link  animation-normal">
							<img src="${resourceRoot }/web/ybb/assets/img/head/hot.gif" class="hot">手机投注</a>
					</li>
					
					<li>
						<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')">优惠活动</a>
					</li>
					

				</ul>
			</div>
		</div>
	</div>
</div>
<div class="wrapper submenu-wrapper">
	<ul class="submenu-1 submenu clearfix hideme">
		<li class="ui-item-son">
			<a tabindex="0" role="button" href="javascript:void(0);" class="ui-link-son" onclick="Go('${ctx}/goPageCenter?code=sport')">进入游戏</a>
		</li>
	</ul>
	<ul class="submenu-2 submenu clearfix hideme">
		<div class="position-it clearfix">
			<c:forEach var="item" items="${liveSite}">
				<li class="ui-item-son item-${item.flat }">
					<c:choose>
						<c:when test="${!empty webUser}">
							<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')" title="点击进入" class="ui-link-son"></c:when>
							<c:otherwise>
								<a href="javascript:void(0)" onclick="javascript:alert('请先登录');" class="ui-link-son"></c:otherwise>
							</c:choose>
							${item.flatName }
						</a>
					</li>
				</c:forEach>
			</div>
		</ul>
		<ul class="submenu-3 submenu clearfix hideme">
			<div class="position-it clearfix">
				<li class="ui-item-son slot-fanshui">
					<a href="javacript:void(0)">返回</a>
				</li>
				<c:forEach var="item" items="${slotSite}">
					<li class="ui-item-son slot-${item.flat }">
						<a href="${ctx}/${item.flatUrl}" class="ui-link-son">${item.flatName }</a>
					</li>
				</c:forEach>
			</div>
		</ul>

		<ul class="submenu-4 submenu clearfix hideme">
			<div class="position-it clearfix">
				<c:forEach var="item" items="${cpSite}">
					<li class="ui-item-son">
						<c:choose>
							<c:when test="${item.flat eq 'bbin' }">
								<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')"  class="ui-link-son">${item.flatName }</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx}/${item.flatUrl}" class="ui-link-son">${item.flatName }</a>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</div>
		</ul>
	</div>
</div>
<c:choose>
<c:when test="${empty webUser}">
<div class="headbottom">
	<div class="headlogin">
		<div class="w1000">
			<div class="wz"></div>
			  <form id="loginForm">
			  <mh:token></mh:token>
				<div style="float:right">
					<input type="text" id="loginName" name="loginName" plugin-placeholder="账号" style="color: white">
					<input type="password" id="password" name="password" plugin-placeholder="密码" style="color: white">
					<input type="text" name="verifycode"  id="verifycode" plugin-placeholder="验证码" class="yzm" style="color: white">
					<label id="validateCodeLabelId" class="yzmimg" plugin-refreshLoginCode=">img, ${ctx}/resources-code.jpg" ><img src="" class="randPic" id="img_validateCode"></label>
					<!-- <img onclick="hd_reloadCode2();" id="hd_vPic" src="/validCode" class="yzmimg"> -->
					<a href="javascript:void(0);" onclick="alert('请联系24小时在线客服找回密码')" class="forget">忘记密码？</a>
					<input type="button" value="登 陆" class="subbtn" onclick="login();">
					<a href="javascript:void(0);" class="regbtn" onclick="Go('${ctx}/register', 'register')">注 册</a>
				</div>
				</form>
		</div>
	</div>
</div>
</c:when>
<c:when test="${!empty webUser}">
	<div class="headbottom">
		<div class="headlogin">
			<div class="w1000">
				<div class="wz"></div>
				<div class="hlogined" style="margin-right: 4px">
					帐号：
					<span onclick="GoToMember('${ctx}/member/main')">${webUser.userName}</span>
					账户余额：
					<span id="totalMoneyHead" style="cursor: pointer;">${webUser.userMoney}RMB</span>
					
					<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main')">会员中心</a>
					|
					<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=1')">线上存款</a>
					|
					<a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=2')">线上取款</a>
					|
					
					<a href="javascript:void(0);" onclick="GoToMember('${ctx}/member/main?type=3')">额度转换</a>
					|
					<a href="javascript:void(0);" onclick="GoToMember('${ctx}/member/main?type=4')">投注记录</a>
					|
					<a href="${ctx}/member/main?type=5" target="_blank">未读讯息(<font id="userMessageId" color="F0E800">0</font>)</a>
					|
					
					<a href="javascript:void(0);" onclick="loginOut();return false">退出</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
    setInterval('refreshUserMoney()', 10000);
    function GoToMember(url) {
      window.open(url, "memberFrame");
    }
  </script>
</c:when>
</c:choose>
<!-- header -->

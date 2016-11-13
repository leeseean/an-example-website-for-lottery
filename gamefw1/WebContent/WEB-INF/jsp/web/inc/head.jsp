<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags"%>
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

<!-- header -->
<div class="nav-header clearfix">
	<div class="header-one clearfix">
		<div class="left">
			<a href="${ctxMap['msg007'] }">
			<img src="${resourceRoot}/web/ybb/assets/img/index/h_01.png" /> </a>
		</div>
		<div class="right">
			<img src="${resourceRoot}/web/ybb/assets/img/index/h_02.png" /> 
			<a href="${resourceRoot }/web/ybb/assets/img/lucense.png" class="official-cert" style=""> 
				<img src="${resourceRoot }/web/ybb/assets/img/index/h_07.png"> 
			<img src="${resourceRoot}/web/ybb/assets/img/index/h_02.png" />
			<a href="${resourceRoot }/web/ybb/assets/img/lucense.png" class="official-cert" style="">
				<img src="${resourceRoot }/web/ybb/assets/img/index/h_07.png">
			</a>
		</div>
	</div>

	<div class="header-two clearfix">
		<div class="left">
			<div class="msg-header">
				<div class="msg-alert-con">
					<div class="wrapper">
						<div class="msg-alert-marquee left" plugin="marquee">
							<ul class="clearfix">
						        <c:forEach var="item" items="${dataList}" varStatus="status">
						          <li><a onclick="winOpenMessage('${ctx}/message/goGongGaoList','message','top=0,left=0,width=800,height=450,resizable=yes')" href="javascript:void(0)" style=" overflow: hidden; width: 810px; height: 30px; line-height: 30px;">${item.ggContent}</a></li>
						        </c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="right infor">
			<div class="lang-icons">
				<a href="javascript:void(0)" class="hk-lang"></a> <a
					href="javascript:void(0)" class="cn-lang"></a> <a
					href="javascript:void(0)" class="en-lang"></a>
			</div>
			<ul class="clearfix qick-links">
				<li><a href="${ctx}/test/main">免费试玩</a></li>
				<li><a href="${ctx}/lineCheck">线路检测</a></li>
				

			    <li><a href=""></a></li>
			</ul>
		</div>
	</div>
	<!-- nav -->

	<div class="full-wrapper header-wrapper">
		<div class="wrapper">
			<div class="links links-nav clearfix">
				<div class="nav-line"></div>
				<div class="header-nav-icon"></div>
				<div class="header-nav-icon-second"></div>
				<div class="header-nav-icon-third"></div>
				<ul class="clearfix left">
					<li class="nav-first"><a href="${ctx}/index" class="nav-first"><p
								class="word-cn">首页</p> </a>
					</li>
					<li class="has-submenu has-submenu-3">
						<a href="javascript:void(0)" onclick="Go('${ctx}/electronic?code=mg', 'electronic')" >
						 <p class="word-cn">电子游艺</p> </a>
							<!-- 电子游艺 下拉菜单 -->
							<div class="list">
								<c:forEach items="${slotSite }" var="slot">
									<a href="${ctx }/${slot.flatUrl }" class="child-menu">${slot.flatName }</a>
								</c:forEach>
							</div>
					</li>
					<li class="has-submenu has-submenu-2">
						<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=live', 'live')">
							<p class="word-cn">视讯直播</p> 
						</a>
						<!-- 电子游艺 下拉菜单 -->
						<div class="list">
							<c:forEach items="${liveSite }" var="live">
								<a href="javascript:void(0);" onclick="winOpen('/${live.flatUrl}',window.screen.width,window.screen.height,0,0,'game','${live.flat }');" class="child-menu">${live.flatName }</a>
							</c:forEach>
						</div>
					</li>
					<li class="has-submenu has-submenu-1"><a href="javascript:void(0)"
						onclick="Go('${ctx}/goPageCenter?code=sport', 'sport')"><p
								class="word-cn">体育赛事</p> </a>
						<div class="list">
							<c:forEach items="${sportSite }" var="sport">
								<a href="${ctx }/${sport.flatUrl}" class="child-menu">${sport.flatName }</a>
							</c:forEach>
						</div>
					</li>
					<li class="has-submenu has-submenu-4 has-submenu-1">
						<a href="${ctx}/goPageCenter?code=lottery_index" class="ui-link">
							<p class="word-cn">彩票游戏</p> 
						</a>
						<div class="list">
							<c:forEach items="${cpSite }" var="cp">
								<a href="${ctx }/${cp.flatUrl}" class="child-menu">${cp.flatName }</a>
							</c:forEach>
						</div>
					</li>
					<li>
						<a href="${ctx}/goPageCenter?code=fishinggame"><p class="word-cn">对战游戏</p> </a>
					</li>
					<li><a href="javascript:void(0)"
						onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')" class="">
						<p class="word-cn">最新优惠</p></a>
					</li>
					<li><a href="javascript:void(0);"
						onclick="winOpenMessage('${ctx}/goMobile?code=tyc','mobile','top=0,left=0,width=800,height=502,resizable=no')"
						class="animation-service-link  animation-normal">
							<p class="word-cn">手机下注</p> </a>
					</li>
					<li>
					<li><a href="${ctx}/goPageCenter?code=help&pgSn=agent" target="_blank" class=""><p
								class="word-cn">代理合作</p> </a>
					</li>
					<li><a href="${ctxMap['msg007'] }" target="_blank" class=""><p
								class="word-cn">在线客服</p> </a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!--nav 結束 -->
	<div class="logo left">
		<img src="${resourceRoot}/web/ybb/assets/img/index/logo.png" />
	</div>

	<div class="form">
		<c:choose>
			<c:when test="${!empty webUser }">
				<div class="row-signin signin-success clearfix">
					<ul class="row account-quick-link clearfix left mem-info">
						<li><a href="javascript:void(0)"
							onclick="GoToMember('${ctx}/member/main')">账户中心</a>    ｜</li>
						<li><a href="javascript:void(0)"
							onclick="GoToMember('${ctx}/member/main?type=1')">线上存款</a>    ｜</li>
						<li><a href="javascript:void(0)"
							onclick="GoToMember('${ctx}/member/main?type=2')">线上取款</a>    ｜</li>
						<li><a href="javascript:void(0)"
							onclick="GoToMember('${ctx}/member/main?type=3')">额度转换</a>    ｜</li>
						<li class="last-quick-link"><a href="javascript:void(0)"
							onclick="GoToMember('${ctx}/member/main?type=4')">往来记录</a></li>
					</ul>
					<ul class="row account-info cf clearfix left SU-Menual">
						<li><a href="javascript:void(0);">账号：<span
								class="b yellow">${webUser.userName}</span> </a></li>
						<li><a href="javascript:void(0);">余额：<span
								class="b yellow" id="userMoneyId">${webUser.userMoney}RMB</span>
						</a></li>
						<li><a href="${ctx}/member/main?type=5" target="_blank">未读讯息(<font
								id="userMessageId" color="F0E800">0</font>)</a></li>
						<li><a href="javascript:void(0);" class="link-logout b "
							onclick="loginOut();return false">退出</a></li>
					</ul>
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
					<div class="row members-login">

						<div class="inline-row inline-row-login-name">
							<label for="loginName">账号:</label> <input type="text"
								class="input input-login" id="loginName" name="loginName"
								plugin-placeholder="账号" />
						</div>

						<div class="inline-row inline-row-forget-password">
							<label for="password">密码:</label> <input type="password"
								class="input input-password" id="password" name="password"
								plugin-placeholder="******" />
						</div>

						<div class="inline-row inline-row-verifycode">
							<label for="">验证码:</label> <input type="text" name="verifycode"
								id="verifycode" class="input input-verifycode"
								plugin-placeholder="验证码" />
						</div>

						<div id="validateCodeLabelId"
							class="inline-row inline-valide-code"
							plugin-refreshLoginCode=">img, ${ctx}/resources-code.jpg">
							<img src="" class="randPic" id="img_validateCode">
						</div>

					  <input type="button" class="custom-btn custom-btn-login" onclick="login();"/>
					  <input type="button" class="custom-btn link-register" onclick="Go('${ctx}/register', 'register');"/> 
					  <input type="button" class="custom-btn link-register" onclick="Go('${ctx}/register', 'register');"/>
						<a href="javascript:void(0)"
							onclick="alert('请联系在线客服')" class="forget-password">忘记密码</a>
						<!-- <input type="button" value="代理加盟" onclick="Go('${ctx}/goPageCenter?code=help&pgSn=agent', 'agent')" class="custom-btn link-agent animation-normal"/>
							<a href="${ctx }/lineCheck" class="custom-btn link-linechk" target="_blank">线路检测</a> -->
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<!-- header -->
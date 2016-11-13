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

<!-- header -->
<div class="nav-top">
	<div class="nav-header clearfix">
    <c:choose>
    	<c:when test="${(!empty webUser)&&(page_name ne 'main')}">
    		<div class="nav-timer" plugin-timer="-4.0" style="margin-left: 4.1%"></div>
    	</c:when>
    	<c:otherwise>
    		<div class="nav-timer" plugin-timer="-4.0" style="position: absolute;left: 3%"></div>
    	</c:otherwise>
    </c:choose>
		<div class="form">
			<c:choose>
				<c:when test="${!empty webUser}">
            <c:choose>
	              <c:when test="${page_name == 'main'}">
	                <div class="row">
	                  <a style="margin-left: 580px;display: inline-block;"></a>
										<input type="button" value="代理加盟" onclick="Go('${ctx}/goPageCenter?code=help&pgSn=agent', 'agent')" class="custom-btn link-agent animation-normal"/>
										<a href="${ctx }/lineCheck" class="custom-btn link-linechk" target="_blank">线路检测</a>
										<a href="javascript:void(0);" onclick="bookMarksite('http://${ctxMap['lckMainDomain'] }',document.title);"class="custom-btn link-linechk" style="background-color: green">加入收藏</a>
										<a href="${ctx }/test/main" class="custom-btn link-linechk" style="background-color: orange" target="_blank">免费试玩</a>
									</div>
	              </c:when>
	              <c:otherwise>
	                <div class="row-signin signin-success clearfix" style="margin-right: 60px;">
	                  <ul class="row account-quick-link clearfix left">
	                  	<li><a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main')">账户中心</a></li>
	                  	<li><a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=1')">线上存款</a></li>
	                  	<li><a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=2')">线上取款</a></li>
	                  	<li><a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=3')">额度转换</a></li>
	                  	<li class="last-quick-link"><a href="javascript:void(0)" onclick="GoToMember('${ctx}/member/main?type=4')" >往来记录</a></li>
	                  </ul>
	                  <ul class="row account-info cf clearfix left">
	                    <li><a href="javascript:void(0);">账号：<span class="b yellow">${webUser.userName}</span></a></li>
	                    <li><a href="javascript:void(0);">余额：<span class="b yellow" id="userMoneyId">${webUser.userMoney}RMB</span></a></li>
	                    <li><a href="${ctx}/member/main?type=5" target="_blank">未读讯息(<font id="userMessageId" color="F0E800">0</font>)</a></li>
	                    <li><a href="javascript:void(0);" class="link-logout b " onclick="loginOut();return false">退出</a></li>
	                  </ul>
	                </div>
	                  <script type="text/javascript">
		                  setInterval('refreshUserMoney()', 10000);

		                  function GoToMember(url) {
		                    window.open(url, "memberFrame");
		                  }
	                  </script>
	              </c:otherwise>
            </c:choose>
				</c:when>



				<c:otherwise>
				  //首页另一个判断,要加jsp代码

	        <c:choose>
	        <c:when test="${page_name == 'main'}">
	       <!--  <form id="loginForm" class="login-form from" style="margin-top: -12px">
						<mh:token></mh:token> -->
						<div class="row">
						  <a style="width: 630px;display: inline-block;"></a>
							<input type="button" value="代理加盟" onclick="Go('${ctx}/goPageCenter?code=help&pgSn=agent', 'agent')" class="custom-btn link-agent animation-normal"/>
							<a href="${ctx }/lineCheck" class="custom-btn link-linechk" target="_blank">线路检测</a>
							<a href="javascript:void(0);" onclick="bookMarksite('http://${ctxMap['lckMainDomain'] }',document.title);"class="custom-btn link-linechk" style="background-color: green">
							加入收藏
							</a>
							<a href="${ctx }/test/main" class="custom-btn link-linechk" style="background-color: orange" target="_blank">免费试玩</a>
						</div>

					<!-- </form> -->

          </c:when>
          <c:otherwise>
					<form id="loginForm" class="login-form from" style="margin-top: -12px">
						<mh:token></mh:token>
						<div class="row">
						  <a style="width: 225px;display: inline-block;"></a>
							<input type="text" class="input input-login" id="loginName" name="loginName" plugin-placeholder="账号"  />
							<div class="inline-row inline-row-forget-password">
								<input type="password" class="input input-password" id="password" name="password" plugin-placeholder="******"/>
							</div>
							<div class="inline-row inline-row-verifycode">
								<input type="text" name="verifycode"  id="verifycode" class="input input-verifycode" plugin-placeholder="验证码" />
								<label id="validateCodeLabelId" class="clearfix" plugin-refreshLoginCode=">img, ${ctx}/resources-code.jpg" ><img src="" class="randPic" id="img_validateCode"></label>
							</div>
							<input type="button" value="登入" class="custom-btn custom-btn-login" onclick="login();"/>
							<input type="button" value="立即注册" onclick="Go('${ctx}/register', 'register')" class="custom-btn link-register"/>
							<input type="button" value="代理加盟" onclick="Go('${ctx}/goPageCenter?code=help&pgSn=agent', 'agent')" class="custom-btn link-agent animation-normal"/>
							<a href="${ctx }/lineCheck" class="custom-btn link-linechk" target="_blank">线路检测</a>
						</div>
					</form>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<div class="full-wrapper">
	<div class="wrapper">
		<div class="links links-nav clearfix"  plugin-navSubmenu=".submenu-wrapper, .header > .full-wrapper">
			<div class="logo left">
				<div onmousedown="Go('${ctx}/index', 'index')" class="block">
				  <img src="${resourceRoot }/web/ybb/assets/img/logo/logo.png" style="margin-top: -20px;margin-left: 80px">
					<!-- <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="280" height="75">
		              	<param name="movie" value="${resourceRoot }/web/ybb/assets/img/logo.swf">
		               	<param name="quality" value="high">
		               	<param name="wmode" value="transparent">
		                <embed src="${resourceRoot }/web/ybb/assets/img/logo/logo.png" width="280" height="75" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" wmode="transparent">
		           </object> -->
	           </div>
			</div>
			<ul class="clearfix left">
				<li><a href="${ctx}/index"><p class="word-cn">首页</p><p class="word-eng">HOME</p></a></li>
				<li class="has-submenu-1">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=sport', 'sport')"><p class="word-cn">体育赛事</p>
					<p class="word-eng">SPORT</p></a>
				</li>
				<li class="has-submenu-2">
					<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=live', 'live')"><p class="word-cn">真人视讯</p>
					<p class="word-eng">LIVE DEALER </p></a>
				</li>
				<li class="has-submenu-3">
					<a href="javascript:void(0)" onclick="Go('${ctx}/electronic?code=mg', 'electronic')"><p class="word-cn">电子游艺</p>
					<p class="word-eng">CASINO</p></a>
				</li>
				<li class="has-submenu-4"><a href="${ctx}/goPageCenter?code=lottery_index" class="ui-link"><p class="word-cn">彩票</p>
					<p class="word-eng">LOTTERY</p></a></li>
				<li><a href="${ctx}/goPageCenter?code=fishinggame" class="ui-link"><p class="word-cn">AG捕鱼王</p>
					<p class="word-eng">PCEGG</p></a></li>
				<li><a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=promos', 'promos')" class=""><p class="word-cn">优惠活动</p>
					<p class="word-eng">PROMOTION</p></a></li>
				<li><a href="javascript:void(0);" onclick="winOpenMessage('${ctx}/goMobile?code=tyc','mobile','top=0,left=0,width=800,height=502,resizable=no')" class="animation-service-link  animation-normal">
					<p class="word-cn">手机投注</p>
					<p class="word-eng">MOBILE</p>
				</a></li>
				<li><a href="${ctxMap['msg007'] }" target="_blank"  class=""><p class="word-cn">在线客服</p><p class="word-eng">SERVICE</p></a></li>
			</ul>
		</div>
	</div>

<div class="wrapper submenu-wrapper">
	<ul class="submenu-1 submenu clearfix hideme">
		<li class="ui-item-son"><a tabindex="0" role="button" href="javascript:void(0);" class="ui-link-son" onclick="Go('${ctx}/goPageCenter?code=sport')">进入游戏</a></li>
	</ul>
	<ul class="submenu-2 submenu clearfix hideme">
		<div class="position-it clearfix">
			<c:forEach var="item" items="${liveSite}">
			<li class="ui-item-son item-${item.flat }">
			<c:choose><c:when test="${!empty webUser}">
				<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')" title="点击进入" class="ui-link-son">
			</c:when><c:otherwise>
			    <a href="javascript:void(0)" onclick="javascript:alert('请先登录');" class="ui-link-son">
			</c:otherwise></c:choose>${item.flatName }</a>
			</li>
			</c:forEach>
		</div>
	</ul>
	<ul class="submenu-3 submenu clearfix hideme">
		<div class="position-it clearfix">
			<li class="ui-item-son slot-fanshui"><a href="javacript:void(0)">返回</a></li>
			<c:forEach var="item" items="${slotSite}">
			<li class="ui-item-son slot-${item.flat }"><a href="${ctx}/${item.flatUrl}" class="ui-link-son">${item.flatName }</a></li>
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


<!-- header -->
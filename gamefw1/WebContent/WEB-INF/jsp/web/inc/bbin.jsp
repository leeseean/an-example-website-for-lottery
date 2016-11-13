<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="body2">
	<ul>
		<li class="sport wow flipInX fade" data-wow-delay="0.3s"><c:choose>
				<c:when test="${!empty webUser}">
					<a href="javascript:void(0)"
						onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=ball',window.screen.width,window.screen.height,0,0,'game','bbin')"
						title="点击进入 BBIN体育"></a>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"
						title="点击进入 BBIN体育"></a>
				</c:otherwise>
			</c:choose></li>
		<li class="live wow flipInX fade" data-wow-delay="0.5s"><c:choose>
				<c:when test="${!empty webUser}">
					<a href="javascript:void(0)"
						onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=live',window.screen.width,window.screen.height,0,0,'game','bbin')"
						title="点击进入 BBIN真人"></a>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"
						title="点击进入 BBIN真人"></a>
				</c:otherwise>
			</c:choose></li>
		<li class="slots wow flipInX fade" data-wow-delay="0.7s"><c:choose>
				<c:when test="${!empty webUser}">
					<a href="javascript:void(0)"
						onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=game',window.screen.width,window.screen.height,0,0,'game','bbin')"
						title="点击进入 BBIN电游"></a>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"
						title="点击进入 BBIN电游"></a>
				</c:otherwise>
			</c:choose></li>
		<li class="lott wow flipInX fade" data-wow-delay="0.9s"><c:choose>
				<c:when test="${!empty webUser}">
					<a href="javascript:void(0)"
						onclick="winOpen('${ctx}/forwardGame?gameType=bbin&pageSite=ltlottery',window.screen.width,window.screen.height,0,0,'game','bbin')"
						title="点击进入 BBIN彩票"></a>
				</c:when>
				<c:otherwise>
					<a href="javascript:void(0)" onclick="alert('您尚未登录，请先登录再进行游戏')"
						title="点击进入 BBIN彩票"></a>
				</c:otherwise>
			</c:choose></li>
	</ul>
</div>
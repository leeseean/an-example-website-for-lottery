<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="ybb-main ybb-live-main">
	<div class="ui-mod-item">
		<c:choose>
			<c:when test="${empty webUser }">
			<a href="javascript:alert('请先登录');void(0);" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_ag.png" alt="" />
			</a>
			</c:when>
			<c:otherwise>
			<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/game/forwardGame?agGameType=2&gameType=ag','ag');" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_ag.png" alt="" />
			</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="ui-mod-item">
		<c:choose>
			<c:when test="${empty webUser }">
			<a href="javascript:alert('请先登录');void(0);" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_ds.png" alt="" />
			</a>
			</c:when>
			<c:otherwise>
			<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/game/forwardGame?gameType=ds','ds');" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_ds.png" alt="" />
			</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="ui-mod-item">
		<c:choose>
			<c:when test="${empty webUser }">
			<a href="javascript:alert('请先登录');void(0);" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_sa.png" alt="" />
			</a>
			</c:when>
			<c:otherwise>
			<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/game/forwardGame?gameType=sa','sa');" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_sa.png" alt="" />
			</a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="ui-mod-item">
		<c:choose>
			<c:when test="${empty webUser }">
			<a href="javascript:alert('请先登录');void(0);" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_hg.png" alt="" />
			</a>
			</c:when>
			<c:otherwise>
			<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/game/forwardGame?gameType=hg','hg');" class="ui-btn ui-btn-f" data-ajax="false">
				<img src="${resourceRoot }/m/img/live_hg.png" alt="" />
			</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
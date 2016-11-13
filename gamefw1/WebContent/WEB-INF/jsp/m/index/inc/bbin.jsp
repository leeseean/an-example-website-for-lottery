<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- bbin -->
<div class="mob bbin">
	<div class="ui-mod mod-bbin-product">
		<div class="ui-mod-body">
			<ul class="ui-group cf">
				<li class="ui-item">
					<c:choose>
						<c:when test="${empty webUser}">
							<a href="javascript:alert('请先登录');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB视讯</p> </a>
						</c:when>
						<c:otherwise>
							<%--<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/bbin/gobbin?typeCode=live','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB视讯</p> </a>
							--%><a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/game/forwardGame?typeCode=live&gameType=bbin','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB视讯</p> </a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="ui-item">
					<c:choose>
						<c:when test="${empty webUser}">
							<a href="javascript:alert('请先登录');" class="ui-link"> <i class="spr-icon icon_sport"></i><p>BB体育</p> </a>
						</c:when>
						<c:otherwise>
							<%--<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/bbin/gobbin?typeCode=ball','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB体育</p> </a>
							--%><a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/game/forwardGame?typeCode=ball&gameType=bbin','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB体育</p> </a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="ui-item">
					<c:choose>
						<c:when test="${empty webUser}">
							<a href="javascript:alert('请先登录');" class="ui-link"> <i class="spr-icon icon_slot"></i><p>BB电子</p> </a>
						</c:when>
						<c:otherwise>
							<%--<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/bbin/gobbin?typeCode=game','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB电子</p> </a>
							--%><a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/game/forwardGame?typeCode=game&gameType=bbin','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB电子</p> </a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="ui-item">
					<c:choose>
						<c:when test="${empty webUser}">
							<a href="javascript:alert('请先登录');" class="ui-link"> <i class="spr-icon icon_lotto"></i><p>BB彩票</p> </a>
						</c:when>
						<c:otherwise>
							<%--<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/bbin/gobbin?typeCode=ltlottery','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB彩票</p> </a>
							--%><a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/game/forwardGame?typeCode=ltlottery&gameType=bbin','bbin');" class="ui-link"> <i class="spr-icon icon_live"></i><p>BB彩票</p> </a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
	</div>
</div>
<!-- / bbin -->
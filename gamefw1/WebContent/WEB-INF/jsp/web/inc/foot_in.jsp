<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script src="${resourceRoot}/web/ybb/common/plugins/swfobject/swfobject.js"></script>
<script src="${resourceRoot}/web/ybb/common/plugins/jui/jquery-ui.min.js"></script>
<script src="${resourceRoot}/web/ybb/common/js/superslide.min.js"></script>
<script src="${resourceRoot}/web/ybb/common/js/lazyload.min.js"></script>
<script src="${resourceRoot}/web/ybb/common/js/core.js"></script>
<script src="${resourceRoot}/web/ybb/common/js/web_common.js"></script>
<script src="${resourceRoot}/web/ybb/common/js/custom-plugin.js"></script> 
<script src="${resourceRoot}/web/ybb/assets/js/float.js"></script>
<script src="${resourceRoot}/web/ybb/assets/js/basic.js"></script>
<script src="${resourceRoot}/web/ybb/assets/js/menu.js"></script>
<script src="${resourceRoot}/web/ybb/assets/js/custom.js"></script>

${ctxMap['msg008'] }

<!-- /浮動 -->
<%-- <div class="api-float api-float-1 api-float-service  api-float-left hideme">
	<a href="javascript:Go('${ctx}/electronic?code=mg')" class="f-mg"></a>
	<a href="" class="f-app"></a>
	<a href="" class="f-luck"></a>
	<a href="" class="f-payment"></a>
	<a href="" class="close" plugin="closeFloatPopup"></a>
</div> --%>
<!-- /浮動 -->
<!-- <div class="api-float api-float-1 api-float-contact api-float-right hideme">
	<a href="" class="f-400"></a>
	<a href="" class="f-ao"></a>
	<a href="" class="f-world"></a>
	<a href="" class="f-arcode"></a>
	<a href="" class="close" plugin="closeFloatPopup"></a>
</div> -->

<div class="ybb-md-cs">
	<div class="btn-open">
		<a href="javascript:void(0);"></a>
	</div>
	<div class="ybb-md-ct" style="right: -148px;">
		<ul>
			<li class="item-live center"><a
				href="${ctxMap['msg007'] }"
				target="_blank">在线客服</a>
			</li>
			<li class="item-signup center"><a href="${ctx}/register">免费开户</a>
			</li>
			<li class="item-promos center"><a
				href="${ctx}/goPageCenter?code=promos">优惠活动</a>
			</li>
			<li class="item-qq center"><a
				href="tencent://message/?uin=${ctxMap['siteQq'] }&amp;Site=web&amp;Menu=yes"
				target="_blank">QQ客服</a>
			</li>
		</ul>
		<a href="javascript:void(0);" class="btn-close"></a>
	</div>
</div> 




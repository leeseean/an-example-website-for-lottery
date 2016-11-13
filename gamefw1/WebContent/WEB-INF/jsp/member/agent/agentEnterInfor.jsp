<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<html>
<%@include file="/commons/member/jsp/member_common.jsp"%>
<script type="text/javascript">
	var status = "${webAgent.status}";
		if(status!=1){
			alert(status)
			var url = "${ctx}" +'/member/agentDetail';
			goParent(url);
		}
	function goParent(url) {
		parent.document.getElementById('memberFrame').src = url;
	}
</script>
<body>
	<div class="wrapper">
		<div class="panel-content">
			<div class="panel-record">
				<div class="page-title clear">
					<div class="left">
						<h2 class="s24 blue">代理申请</h2>
					</div>
					<div class="right mt10">
						<button
							class="button button-primary button-raised button-pill button-tiny btn-contact">
							<i></i>在线客服
						</button>
					</div>
				</div>
				<div class="page-body switch-mod">
					<!-- 是代理資格 -->
					<div class="switch-menu s16">
						<ul class="center clear">
							<li class="item" onclick="goParent('${ctx}/member/agentDetail')">代理信息</li>
							<c:if test="${webAgent.status eq 1}">
							<li class="item on" onclick="goParent('${ctx}/member/agentEnterInfor')">代理入口</li>
							</c:if>
 						</ul>
					</div>
					<div class="switch-group">
						<div class="switch-item">
							<div class="switch-body-wrap">
								<div class="sheet-mod">
									<div class="sheet-content pt20">
											<table class="s14">
												<tr class="sheet-title s14 b" align="center">
													<td colspan="2" >
														可以使用会员的用户名及密码登录代理线。
													</td>
												</tr>

												<tr>
													<td  class="pr20 ar gray" style="width: 160px;">代理线地址：</td>
													<td><a style="color: #0000FF" href="${enterUrl }" target="_blank">${enterUrl }</a></td>
												</tr>

												<tr>
													<td class="pr20 ar gray">点击收藏：</td>
													<td><a href="javascript:void(0);" id="addToF" onclick="bookMarksite('${enterUrl }',&quot;代理入口&quot;);">加入收藏</a></td>
												</tr>
												
											</table>
									</div>
								</div>
							</div>
							<!-- /sheet -->

							<!-- /item -->
						</div>
						<!-- /group -->
					</div>
					<!-- /body -->
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function bookMarksite(sURL, sTitle) {
		try {
			window.external.addFavorite(sURL, sTitle);
		} catch (e) {
			try {
				window.sidebar.addPanel(sTitle, sURL, "");
			} catch (e) {
				alert("抱歉，您所使用的浏览器无法完成此操作。\n\r加入收藏失败，请使用Ctrl+D进行添加");
			}
		}
	}
	</script>
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
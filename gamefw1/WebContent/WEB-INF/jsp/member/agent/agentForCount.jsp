<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<html>
<%@include file="/commons/member/jsp/member_common.jsp"%>
<body>
<script type="text/javascript">
		var status = "${webAgent.status}";
		if(status!=1){
			var url = "${ctx}" +'/agent/agentDetail';
			goParent(url);
		}
		
		function goParent(url) {
			parent.document.getElementById('memberFrame').src = url;
		}
</script>
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
				<li class="item" onclick="goParent('${ctx}/agent/agentDetail')">代理信息</li>
				<c:if test="${webAgent.status eq 1}">
					<li class="item" onclick="goParent('${ctx}/agent/agentForUsers')">旗下会员</li>
					<li class="item on" onclick="goParent('${ctx}/records/betAgentReport')">代理报表</li>
				</c:if>
			</ul>
		</div>
		<div class="switch-group">
				<div class="switch-item panel-record">
					<div class="switch-body-wrap">
						<div class="sheet-mod">
							<div class="title pt20">
								<form id="page_form" target="memberFrame" action="${ctx}/records/betAgentReport" method="Post" >
									<ul class="clear">
										<li class="gray">从：</li>
										<li>
											<input type="text" id="startTime" name="startTime" value="${records.startTime}" readonly="readonly" class="kui-input-date s14 datepicker" />
										</li>
										<li class="pl10 gray">到：</li>
										<li>
											<input type="text" id="endTime" name="endTime" value="${records.endTime}" readonly="readonly" class="kui-input-date s14 datepicker"/>
										</li>
									 
										<li class="pl10">
											<button  type="submit"  class="button button-primary button-small">查询</button>
										</li>
									</ul>
								</form>
							</div>
							<!-- /title -->
							<div class="sheet-content mt10">
								<table class="center">
									<tr class="sheet-title s14 b">
										<td>平台</td>
										<td>注单量</td>
										<td>投注额</td>
										<td>有效投注</td>
										<td>赢利</td>
									</tr>
									<c:forEach var="item" items="${dataList}" varStatus="s">
										<tr>
											<td>
												<c:choose>
												<c:when test="${item.betFlat eq 'caipiao' }">彩票平台</c:when>
												<c:when test="${item.betFlat eq 'sport' }">体育平台</c:when>
												<c:otherwise>${item.betFlat }平台</c:otherwise>
												</c:choose>
											</td>
											<td align="center">${item.betTotal==null?0:item.betTotal}</td>
											<td align="center">${item.betIn==null?0:item.betIn}</td>
											<td align="center">${item.betIncome==null? 0:item.betIncome }</td>
											<td style='color: ${item.betNetWin>=0?"green":"red" } '>${item.betNetWin==null?0:item.betNetWin}</td>
										</tr>
									</c:forEach>
							</table>
						</div>
					</div>
					<!-- /sheet -->
				</div>
				<div class="progress-body-item progress-body-step2"></div>
				<!-- /item -->
			</div>
			<!-- /group -->
		</div>
		<!-- /body -->
	</div>
	</div>
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
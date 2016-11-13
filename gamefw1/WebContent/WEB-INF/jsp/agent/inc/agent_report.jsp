<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@include file="common.jsp"%>
 <script type="text/javascript">
	function goto_query() {
	 	$.form_submit($("#page_form").get(0), {
			"action" : "${ctx}/agent/agentReport",
			"pageNo" : "1"
		});
		
	}
	
	function goto_detail(name, qs, flat){
		window.location = "${ctx}/agent/gotoDetail?agentName="+name+"&qs="+qs+"&betFlat="+flat;
	}
</script>
</head>

<body class="ag-frame-content font-hei">
<form action="${ctx}/agent/goAgentReport" method="post" id="page_form">
	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
	<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
	<input type="hidden" name="code" value="${code }"/>
	<div class="ag-wrap">
		<table width="100%" class="ag-form">
			<tbody>
				<tr class="ag-form-title b">
					<td>代理<br/>会员数</td>
					<td>AG馆</td>
					<td>MG馆</td>
					<td>BBIN馆</td>
					<td>DS馆</td>
					<td>HG馆</td>
					<td>NT馆</td>
					<td>PT馆</td>
					<td>皇冠体育</td>
					<td>彩票</td>
					<td>欧博</td>
					<td>OG</td>
					<td>OS</td>
					<td>SB</td>
					<td>GD</td>
					<td>TTG</td>
					<td>SBT</td>
					<td>SA</td>
					<td>VG</td>
					<td>存款<br/>手续费</td>
					<td>取款<br/>手续费</td>
					<td>行政费用</td>
					<td>佣金</td>
					<td>返水总量<br/>仅供参考</td>
					<td>入款总量<br/>仅供参考</td>
					<td>出款总量<br/>仅供参考</td>
				</tr>
				<c:forEach var="item" items="${page.result }">
				<tr>
					<td>${item.bet_user_agent }<br/><a href="${ctx }/agent/memberList?agentName=${item.bet_user_agent }" >${item.member_total}</a>   </td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','ag')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','mg')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','bbin')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','ds')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','hg')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','nt')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','pt')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','sport')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','caipiao')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','ab')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','og')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','os')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','sb')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','gd')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','ttg')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','sbt')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','sa')" type="button" value="详情" /></td>
					<td><input class="ui-btn" onclick="goto_detail('${item.bet_user_agent}','${item.bet_qs}','vg')" type="button" value="详情" /></td>
					<td>
						<c:if test="${item.deposit_fee>0}">-</c:if><fmt:formatNumber value="${item.deposit_fee}" pattern="#0.00#"/>
					</td>
					
					<td><fmt:formatNumber value="${item.withdraw_fee}" pattern="#0.00#"/></td>
					<td><fmt:formatNumber value="${item.adminis_free}" pattern="#0.00#"/></td>
					<td><fmt:formatNumber value="${item.comms_total}" pattern="#0.00#"/></td>
					<td><fmt:formatNumber value="${item.backwater_total}" pattern="#0.00#"/></td>
					<td><fmt:formatNumber value="${item.deposit_total}" pattern="#0.00#"/></td>
					<td><fmt:formatNumber value="${item.withdraw_total}" pattern="#0.00#"/></td>
				</tr>
				</c:forEach>
			</tbody>
			<jsp:include page="/commons/agent/jsp/member_page_footer.jsp">
				<jsp:param name="action" value="${ctx}/agent/agentReport" />
				<jsp:param name="form_id" value="page_form" />
			</jsp:include>
		</table>
	</div>
	</form>
</body>
<%@include file="comm_foot.jsp"%>
</html>

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
			"action" : "${ctx}/agent/goUserReport",
			"pageNo" : "1"
		});
		
	}
</script>
</head>

<body class="ag-frame-content font-hei">
<form action="${ctx}/agent/goUserReport" method="post" id="page_form">
	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
	<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
	<div class="ag-wrap">
		<table width="100%" class="ag-form">
			<thead>
				<tr>
					<th colspan="19">
						<div class="ag-form-head cf">
							<h2 class="col-one ele-title l">会员报表</h2>
							<div class="ele-filter l">
								<ul class="cf">
									<li>
										从 <input type="text" id="beginTimeStr" name="beginTimeStr" value="${webAgent.beginTimeStr}" 
											onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'%y-%M-%d'})"
										readonly="readonly" size="6" class="ui-write ui-date ac" />
									</li>
									<li>
										到 <input type="text" id="endTimeStr" name="endTimeStr" value="${webAgent.endTimeStr}" 
											onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'%y-%M-%d'})"
										readonly="readonly" size="6" class="ui-write ui-date ac" />
									</li>
									
									<li>娱乐场 <select name="platName" id="platName" class="ui-select">
												<c:forEach var="item" items="${platMap}">
													<option value="${item.key}" <c:if test="${webAgent.platName==item.key}">selected="selected"</c:if>>${item.value}</option>
												</c:forEach>
											</select>
									</li>
									<li>
										<input type="button" onclick="goto_query();" value="查询" class="ui-btn">
									</li>
									<li class="item-history">
										<input onclick="changeTime('yes');"  type="button"  value="昨天"/>
										<input onclick="changeTime('week');"  type="button"  value="本周"/>
										<input onclick="changeTime('month');"  type="button"  value="本月"/>
										<input onclick="changeTime('premonth');"  type="button"  value="上月"/>
									</li>
								</ul>
							</div>
						</div></th>
				</tr>
			</thead>
			<tbody>
				<tr class="ag-form-title b">
					<td width="60">序号</td>
					<td>账户</td>
					<td>下注笔数</td>
					<td>投注金额</td>
					<td>有效投注额</td>
					<td>输赢</td>
				</tr>
				<c:forEach var="item" items="${page.result}" varStatus="item_index">
		        	<tr class="ag-form-content">
			       		<td>${item_index.index+1 }</td>
			        	<td>${item.userName }</td>
			        	<td><strong>${item.bet_total}</strong>
			        	<td><strong class="pr10 ar block">${item.bet_in}</strong></td>
			        	<td><strong class="pr10 ar block">${item.bet_income}</strong></td>
			        	<td>
			        		<c:choose>
			        			<c:when test="${item.bet_usr_win>0}">
			        				<strong class="pr10 ar block red">${item.bet_usr_win}</strong>
			        			</c:when>
			        			<c:otherwise>
			        				<strong class="pr10 ar block blue">${item.bet_usr_win}</strong>
			        			</c:otherwise>
			        		</c:choose>
			        	</td>
		        	</tr>
		        </c:forEach>
			</tbody>
			<jsp:include page="/commons/agent/jsp/member_page_footer.jsp">
				<jsp:param name="action" value="${ctx}/agent/goUserReport" />
				<jsp:param name="form_id" value="page_form" />
			</jsp:include>
		</table>
	</div>
	</form>
</body>
<%@include file="comm_foot.jsp"%>
</html>

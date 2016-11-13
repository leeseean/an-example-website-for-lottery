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
			"action" : "${ctx}/agent/gotoUserRecords",
			"pageNo" : "1"
		});
		
	}
</script>
</head>

<body class="ag-frame-content font-hei">
<form action="${ctx}/agent/gotoUserRecords" method="post" id="page_form">
	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
	<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
	<div class="ag-wrap">
		<table width="100%" class="ag-form">
			<thead>
	        <tr>
	          <th colspan="19">
	            <div class="ag-form-head cf" align="left">
	            	<button onclick="goBack();" class="ui-btn ml10">返回</button>
	            </div>
	          </th>
	        </tr>
	    	</thead>
			<thead>
				<tr>
					<th colspan="19">
						<div class="ag-form-head cf">
							<h2 class="col-one ele-title l"><b>代理商:${agentName } 报表</b></h2>
							<div class="ele-filter l">
								<ul class="cf">
									<li>
										从 <input type="text" id="beginTimeStr" name="beginTimeStr" value="${beginTimeStr}" 
											onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'%y-%M-%d'})"
										readonly="readonly" size="15" class="ui-write ac" />
									</li>
									<li>
										到 <input type="text" id="endTimeStr" name="endTimeStr" value="${endTimeStr}" 
											onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'%y-%M-%d'})"
										readonly="readonly" size="15" class="ui-write ac" />
									</li>
									<li>
										类型：<select name="betFlat" id="betFlat" class="ui-select">
												<c:forEach var="item" items="${platMap}">
													<option value="${item.key}" <c:if test="${betFlat==item.key}">selected="selected"</c:if>>${item.value}</option>
												</c:forEach>
											</select>
									</li>
									<li>
										<input type="hidden" name="agentName" value="${agentName }" />
										<input type="button" onclick="goto_query();" value="查询" class="ui-btn">
									</li>
								</ul>
							</div>
						</div>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr class="ag-form-title b">
				<td width="60">序号</td>
				<td >会员</td>
				<td >注单量</td>
				<td >投注额</td>
				<td >有效投注</td>
				<td >赢利</td>
			</tr>
			<c:if test="${!empty page.result }">
			<c:forEach var="item" items="${page.result}" varStatus="status">
				<tr class="ag-form-content">
					<td>${status.index+1 }</td>
					
					<td >${item.userName}</td>
					
					<td title="${item.betTotal}">${item.betTotal}</td>
					
					<td title="${item.betIn}">${item.betIn}</td>
					
					<td title="${item.betIncome}">${item.betIncome}</td>
						
					<td title="${item.betNetWin}">
						${item.betNetWin>=0?"<font color=green>":"<font color=red>"}
					${item.betNetWin}
					</td>
				</tr>
			</c:forEach>
			
			<jsp:include page="/commons/agent/jsp/member_page_footer.jsp">
				<jsp:param name="action" value="${ctx}/agent/gotoUserRecords" />
				<jsp:param name="form_id" value="page_form" />
			</jsp:include>
			
			</tbody>
			<tbody>
			
			<tr class="ag-form-content">
				<td colspan="2"><font color=blue>页计</td>
				<td>${pageReport.betTotal }</td>
				<td>${pageReport.betIn }</td>
				<td>${pageReport.betIncome }</td>
				<td>${pageReport.betNetWin>=0?"<font color=green>":"<font color=red>"}${pageReport.betNetWin }
				</td>
			</tr>
			<tr class="ag-form-content">
				<td colspan="2"><font color=blue>总计</td>
				<td>${totalReport.betTotal }</td>
				<td>${totalReport.betIn }</td>
				<td>${totalReport.betIncome }</td>
				<td>${totalReport.betNetWin>=0?"<font color=green>":"<font color=red>"}${totalReport.betNetWin }
				</td>
			</tr>
			</c:if>
			<c:if test="${empty page.result }">
			<tr class="ag-form-content">
				<td colspan="6">暂未数据</td>
			</tr>
			
			</c:if>
			</tbody>
			
		</table>
	</div>
	</form>
</body>
<%@include file="comm_foot.jsp"%>
</html>

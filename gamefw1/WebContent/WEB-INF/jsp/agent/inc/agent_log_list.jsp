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
			"action" : "${ctx}/agent/goAgentLogList",
			"pageNo" : "1"
		});
		
	}
</script>
</head>

<body class="ag-frame-content font-hei">
<form action="${ctx}/agent/goAgentLogList" method="post" id="page_form">
	<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
	<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
	<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
	<div class="ag-wrap">
		<table width="100%" class="ag-form">
			<thead>
				<tr>
					<th colspan="19">
						<div class="ag-form-head cf">
							<h2 class="col-one ele-title l">操作日志</h2>
							<div class="ele-filter l">
								<ul class="cf">
									<li>
										从 <input type="text" id="beginTimeStr" name="beginTimeStr" value="${webAgentLog.beginTimeStr}" 
											onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'%y-%M-%d'})"
										readonly="readonly" size="15" class="ui-write ac" />
									</li>
									<li>
										到 <input type="text" id="endTimeStr" name="endTimeStr" value="${webAgentLog.endTimeStr}" 
											onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'%y-%M-%d'})"
										readonly="readonly" size="15" class="ui-write ac" />
									</li>
									<li>
										操作类型： <select name="logName" style="width: 50px;">
											<option value="">全部</option>
											<option value="登录" <c:if test="${webAgentLog.logName=='登录'}">selected="selected"</c:if>>登录</option>
											<option value="修改" <c:if test="${webAgentLog.logName=='修改'}">selected="selected"</c:if>>修改</option>
										</select>
									</li>
									<li>
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
					<td>操作时间</td>
					<td>操作类别</td>
					<td>操作对象</td>
					<td>操作者</td>
					<td>备注</td>
					<td>IP</td>
				</tr>
				<c:forEach var="item" items="${page.result}" varStatus="item_index">
		        	<tr class="ag-form-content">
			       		<td>${item_index.index+1 }</td>
			          	<td ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.opt_time}" /></td>
			        	<td>${item.log_name }</td>
			        	<td>${item.opt_target }</td>
			        	<td>${item.opt_user}</td>
			        	<td style="text-align: left;">&nbsp;${item.log_content}</td>
			        	<td>${item.opt_ip}</td>
		        	</tr>
		        </c:forEach>
			</tbody>
			<jsp:include page="/commons/agent/jsp/member_page_footer.jsp">
				<jsp:param name="action" value="${ctx}/agent/goAgentLogList" />
				<jsp:param name="form_id" value="page_form" />
			</jsp:include>
		</table>
	</div>
	</form>
</body>
<%@include file="comm_foot.jsp"%>
</html>

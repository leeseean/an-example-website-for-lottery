<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">

<html>
<%@include file="/commons/member/jsp/member_common.jsp"%>
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
				<form id="page_form" target="memberFrame" action="${ctx}/member/goList" method="Post" >
					<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
					<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
					<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
					<input type="hidden" name="code" value="${records.code}"/>
				<div class="page-body switch-mod">
					<!-- 是代理資格 -->
					<div class="switch-menu s16">
						<ul class="center clear">
							<li class="item" onclick="goParent('${ctx}/agent/agentDetail')">代理信息</li>
						<c:if test="${webAgent.status eq 1}">
							<li class="item on" onclick="goParent('${ctx}/agent/agentForUsers')">旗下会员</li>
							<li class="item" onclick="goParent('${ctx}/records/betAgentReport')">代理报表</li>
						</c:if>
						</ul>
					</div>
					<div class="switch-group">
						<div class="switch-item">
							<div class="switch-body-wrap">
								<div class="sheet-mod">
									<div class="sheet-content pt20">
										<table class="center">
											<tr class="sheet-title s14 b">
												<td>姓名</td>
												<td>账户</td>
												<td>金额</td>
												<td>上次登录时间IP</td>
											</tr>
											<c:if test="${empty page.result}">
												<tr height="125px;">
													<td colspan="4"><span class="gray-dark">查询结果 - 暂无纪录</span></td>
												</tr>
											</c:if>
											<c:forEach var="item" items="${page.result}" varStatus="s">
												<tr>
													<td align="center">${item.user_real_name}</td>

													<td align="center">${item.user_name}</td>


													<td style="color:#01B27E;font-family: Trebuchet MS;"
														align="center"
														title="余额:${item.user_money} ${webUserLCMap[item.user_name]}">
														${item.user_money} <font class="timeFontDefault">
															${webUserLCMap[item.user_name]}</font>
													</td>


													<td align="center">
														<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${item.user_last_login_ime}" /> | ${item.user_last_login_ip }
													</td>

												</tr>
											</c:forEach>
											<c:if test="${!empty page.result}">
												<tr class="sheet-title">
													<td colspan="4" class="pr20 ar"><span class="right">总计${page.totalCount}笔记录&nbsp;&nbsp;共${page.totalPages}页&nbsp;&nbsp;当前第${page.pageNo }页 &nbsp;&nbsp;</span></td>
												</tr>
											</c:if>
										</table>
									</div>
								</div>
								
									<jsp:include page="/commons/member/jsp/member_page_footer.jsp">
										<jsp:param name="action" value="${ctx}/agent/agentForUsers" />
										<jsp:param name="form_id" value="page_form" />
									</jsp:include>
								
							</div>
							<!-- /sheet -->
							<!-- /item -->
						</div>
						<!-- /group -->
					</div>
					<!-- /body -->
				</div>
				</form>
			</div>
		</div>
	</div>
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>

</head>
<body>
<div class="wrapper">
<div class="panel-content font-hei">
<div class="panel-record">
	<div class="page-title clear">
		<div class="left">
			<h2 class="s24 blue">提现纪录</h2>
			<p class="mt5 gray">在提现后达成资金有关历史记录记载</p>
		</div>
		<div class="right mt10">
			<button class="button button-primary button-raised button-pill button-tiny btn-contact"><i></i>在线客服</button>
		</div>
	</div>
	<div class="page-body">
		<div class="sheet-mod">
			<div class="title">
				<form id="page_form" target="memberFrame" action="${ctx}/member/goList" method="Post" >
					<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
					<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
					<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
					<input type="hidden" name="code" value="${records.code}"/>
					<ul class="clear">
						<li class="gray">从：</li>
						<li>
							<input type="text" id="startTime" name="startTime" value="${records.startTime}" readonly="readonly" class="kui-input-date s14 datepicker" />
						</li>
						<li class="pl10 gray">到：</li>
						<li>
							<input type="text" id="endTime" name="endTime" value="${records.endTime}" readonly="readonly" class="kui-input-date s14 datepicker"/>
						</li>
						<li class="pl10 gray">状态：</li>
						<li>
							<select name="withdrawType" id="withdrawType" style="height: 25px;" class="dropmenu w100">
								<option value="" >全部类型</option>
								<option value=11 ${records.withdrawType==11?"selected='selected'":"" } >
									会员提款
								</option>
								<option value=12 ${records.withdrawType==12?"selected='selected'":"" } >
									系统扣款
								</option>
							</select>
						</li>
						<li class="pl10">
							<button type="submit" class="button button-primary button-small">查询</button>
						</li>
					</ul>
				</form>
			</div>
			<!-- /title -->
			<div class="sheet-content mt20">
				<table class="center">
					<tr class="sheet-title s14 b">
						<td width="100">操作类型</td>
						<td width="160">订单号</td>
						<td width="150">提款时间</td>
						<td width="100">提款金额</td>
						<td width="100">提款状态</td>
						<td>备注</td>
					</tr>
					<c:if test="${empty page.result}">
						<tr height="125px;">
							<td colspan="6"><span class="gray-dark">查询结果 - 暂无提现纪录</span></td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${page.result}" varStatus="item_index">
						<tr class="sheet-body s12">
							<td>${item.withdraw_type==11?" 会员提款":"系统扣款" }</td>
							<td>${item.user_order}</td>
							<td><fmt:formatDate value="${item.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${item.user_withdraw_money}￥</td>
							<td>
								<c:choose>
									<c:when test="${item.status ==0}">
										待审核
									</c:when>
									<c:when test="${item.status ==1}">
										<c:if test="${item.check_status ==1}">已出款</c:if>
										<c:if test="${item.check_status ==2}">拒绝提款</c:if>
									</c:when>
								</c:choose>
							</td>
						 
							<td>${item.remark}</td>
						</tr>
					</c:forEach>
					
					<c:if test="${!empty page.result}">
						<tr class="sheet-title">
							<td colspan="6" class="pr20 ar"><span class="right">总计${page.totalCount}笔记录&nbsp;&nbsp;共${page.totalPages}页&nbsp;&nbsp;当前第${page.pageNo }页 &nbsp;&nbsp;</span></td>
						</tr>
					</c:if>
				</table>
			</div>
			<!-- form_id: 查询表单ID;action: 表单查义URL   -->
			<jsp:include page="/commons/member/jsp/member_page_footer.jsp">
				<jsp:param name="action" value="${ctx}/member/goList" />
				<jsp:param name="form_id" value="page_form" />
			</jsp:include>
		</div>
	</div>
	<!-- /body -->
</div>
<!-- /record -->
</div>
</div>
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
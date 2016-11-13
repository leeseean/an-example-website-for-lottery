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
				<h2 class="s24 blue">充值记录</h2>
				<p class="mt5 gray">在充值后达成资金有关历史记录记载</p>
			</div>
			<div class="right mt10">
				<button
					class="button button-primary button-raised button-pill button-tiny btn-contact">
					<i></i>在线客服
				</button>
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
							<li class="pl10 gray">查询类型：</li>
							<li> 
					 			<select name="hkModel" id="hkModel" class="dropmenu w100" >
									<option value="">全部类型</option>
									<option value=1 ${records.hkModel==1?"selected='selected'":"" }>
										公司入款
									</option>
									<option value=2 ${records.hkModel==2?"selected='selected'":"" }>
										线上支付
									</option>
									<option value=3 ${records.hkModel==3?"selected='selected'":"" }>
										红利赠送
									</option>
									<option value=5 ${records.hkModel==5?"selected='selected'":"" }>
										投注返水
									</option>
									<option value=4 ${records.hkModel==4?"selected='selected'":"" }>
										后台入款
									</option>
									<option value=6 ${records.hkModel==6?"selected='selected'":"" }>
										免费彩金
									</option>
									<option value=7 ${records.hkModel==7?"selected='selected'":"" }>
										存款赠送
									</option>
									<option value=9 ${records.hkModel==9?"selected='selected'":"" }>
										存款优惠
									</option>
								</select>
							</li>
							<li class="pl10"><button type="submit"  class="button button-primary button-small">查询</button></li>
						</ul>
					</form>
				</div>
				<!-- /title -->
				<div class="sheet-content mt20">
					<table class="center">
						<tr class="sheet-title s14 b">
							<td width="80">操作类型</td>
							<td width="100">订单号</td>
							<td width="100">入款时间</td>
							<td width="100">入款金额</td>
							<td width="100">入款状态</td>
							<td width="100">备注</td>
							<td>汇款银行</td>
						</tr>
						<c:if test="${empty page.result}">
							<tr height="125px;">
								<td colspan="7"><span class="gray-dark">查询结果 - 暂无充值纪录</span></td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${page.result}" varStatus="s">
							<tr class="sheet-body s12">
								<td align="center">
									<c:choose>
										<c:when test="${item.hk_model==1}">公司入款</c:when>
										<c:when test="${item.hk_model==2}">线上支付</c:when>
										<c:when test="${item.hk_model==3}">红利赠送</c:when>
										<c:when test="${item.hk_model==4}">后台入款</c:when>
										<c:when test="${item.hk_model==5}">投注返水</c:when>
										<c:when test="${item.hk_model==6}">免费彩金</c:when>
										<c:when test="${item.hk_model==7}">存款赠送</c:when>
										<c:when test="${item.hk_model==9}">存款优惠</c:when>
									</c:choose>
								</td>
								<td align="center">
									${item.hk_order}
								</td>
								<td align="center">
									<fmt:formatDate value="${item.hk_time}" pattern="yyyy-MM-dd HH:mm" />
								</td>
								<td align="center">
									${item.hk_money}￥
								</td>
								<td align="center">
									<c:choose>
										<c:when test="${item.hk_check_status ==0}">
											未入帐
										</c:when>
										<c:when test="${item.hk_check_status ==1}">
											已入帐
										</c:when>
										<c:when test="${item.hk_check_status ==2}">
											未入帐
										</c:when>
									</c:choose>
									
									<c:choose>
										<c:when test="${item.hk_status ==0}">
											(未审核)
										</c:when>
										<c:when test="${item.hk_status ==1}">
											(已审核)
										</c:when>
									</c:choose>
								</td>
								<td align="center">
									${item.remark}
								</td>
								<td align="center">
									${item.hk_company_bank}
								</td>

							</tr>
						</c:forEach>
						<c:if test="${!empty page.result}">
							<tr class="sheet-title">
								<td colspan="7" class="pr20 ar"><span class="right">总计${page.totalCount}笔记录&nbsp;&nbsp;共${page.totalPages}页&nbsp;&nbsp;当前第${page.pageNo }页 &nbsp;&nbsp;</span></td>
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
	<script src="${resourceRoot}/member/js/plugins.js"></script>
</div>
</div>
</body>
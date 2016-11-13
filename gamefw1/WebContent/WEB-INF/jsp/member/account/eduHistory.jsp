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
			<h2 class="s24 blue">转换纪录</h2>
			<p class="mt5 gray">在转帐后达成资金有关历史纪录记载</p>
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
						 <li class="pl10 gray">所属厅：</li>
						<li>
							<select name="flatName" id="flatName" class="dropmenu" style="width: 80px;">
								<option value="" >全部</option>
								<c:forEach var="item" items="${flatParms }">
									<option value="${item.key}" <c:if test="${flatName==item.key}">selected="selected"</c:if>>${item.value}</option>
								</c:forEach>
							</select>
						</li>
						<li class="pl10 gray">转换类型：</li>
						<li> 
							<select name="eduType" id="eduType" class="dropmenu w100">
								<option value=""  >全部类型</option>
								<option value=1 ${records.eduType==1?"selected='selected'":"" }>
									转出
								</option>
								<option value=2 ${records.eduType==2?"selected='selected'":"" }>
									转入
								</option>
							</select>
						</li>
						<li class="pl10"><button type="submit" class="button button-primary button-tiny">查询</button></li>
					</ul>
				</form>
			</div>
			<!-- /title -->
			<div class="sheet-content mt20">
				<table class="center">
					<tr class="sheet-title s14 b">
						<td width="80">操作类型</td>
						<td width="100">订单号</td>
						<td width="150">转换时间</td>
						<td width="80">转换金额</td>
						<td width="200">转换详情</td>
						<td>转换状态</td>
					</tr>
					<c:if test="${empty page.result}">
						<tr height="125px;">
							<td colspan="6"><span class="gray-dark">查询结果 - 暂无转换纪录</span></td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${page.result}" varStatus="item_index">
						<tr class="sheet-body s12">
							<td align="center">
								<c:choose>
									<c:when test="${item.edu_type ==2}">
										转入
									</c:when>
									<c:when test="${item.edu_type ==1}">
										转出
									</c:when>
								</c:choose>
							</td>
							<td align="center">${item.edu_order}</td>
							<td align="center">
								<fmt:formatDate value="${item.create_time}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td align="center">
								${item.edu_points}￥
							</td>
							<td align="left">
								${item.edu_forward_remark}
							</td>
							<td align="center">
								<c:choose>
									<c:when test="${item.edu_status ==-1}">
										业务繁忙，请联系在线客服处理
									</c:when>
									<c:when test="${item.edu_status ==0}">
										失败
									</c:when>
									<c:when test="${item.edu_status ==1}">
										成功
									</c:when>
									<c:when test="${item.edu_status ==2}">
										作废
									</c:when>
								</c:choose>
							</td>
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
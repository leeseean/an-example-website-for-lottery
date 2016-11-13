<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>
<script type="text/javascript">
$(function(){
	$('#code').on('selectmenuchange', function (e,ui) {
	$("#pageNo").val("1");
	});
});
</script>
</head>
<body>
<div class="wrapper">
<div class="panel-content font-hei">
	<div class="panel-record">		
		<div class="page-title clear">
			<div class="left">
				<h2 class="s24 blue">投注记录</h2>
				<p class="mt5 gray">NT平台记录查询</p>
			</div>
			<div class="right mt10">
				<button
					class="button button-primary button-raised button-pill button-tiny btn-contact">
					<i></i>在线客服
				</button>
			</div>
		</div>
		<div class="page-body switch-mod">
			<div class="switch-menu s16">
				<ul class="center clear">
					<li class="item on">记录查询</li>
					<li class="item" onclick="goParent('${ctx}/records/betReport')">报表全览</li>
				</ul>
			</div>
			<div class="switch-group">
				<div class="switch-item panel-record">
					<div class="page-body">
						<div class="sheet-mod">
							<div class="title">
								<form id="page_form" target="memberFrame" action="${ctx}/records/goList" method="Post" >
									<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
									<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
									<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
									<ul class="clear">
										<li class="gray">从：</li>
										<li>
											<input type="text" id="startTime" name="startTime" value="${records.startTime}" readonly="readonly" class="kui-input-date s14 datepicker" />
										</li>
										<li class="pl10 gray">到：</li>
										<li>
											<input type="text" id="endTime" name="endTime" value="${records.endTime}" readonly="readonly" class="kui-input-date s14 datepicker"/>
										</li>
										<li class="pl10 gray">游戏类别：</li>
										<li> 
											<select id="code" name="code" class="dropmenu w100">
												<c:forEach var="item" items="${flatParms }">
												<option value="${item.key}" <c:if test="${records.code==item.key}">selected="selected"</c:if>>${item.value}</option>
												</c:forEach>
											</select>
										</li>
										<li class="pl10">
											<button  type="submit"  class="button button-primary button-small">查询</button>
										</li>
									</ul>
								</form>
							</div>
							<!-- /title -->
							<div class="sheet-content mt20">
								<table class="center">
									<tr class="sheet-title s14 b">
										<td width="150">下注时间</td>
										<td width="170">注单号</td>
										<td width="120">游戏类型</td>
										<td width="120">投注内容</td>
										<td width="120">投注金额</td>
										<td width="120">输赢</td>
									</tr>
									<c:if test="${empty page.result}">
										<tr height="125px;">
											<td colspan="6"><span class="gray-dark">查询结果 - 暂无纪录</span></td>
										</tr>
									</c:if>
									<c:forEach var="item" items="${page.result}" varStatus="item_index">
										<tr class="sheet-body s12">
											<td align="center">
												<fmt:formatDate value="${item.bet_time}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td align="center">${item.bet_Wagers_id}</td>
											<td align="center">${item.bet_game_code}</td>
											<td align="center">${item.bet_content}</td>
											<td align="center">${item.bet_in}</td>
											<td align="center">${item.bet_usr_win*(-1)<0?"<font color=red>":"<font color=green>"} ${item.bet_usr_win}</td>
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
								<jsp:param name="action" value="${ctx}/records/goList" />
								<jsp:param name="form_id" value="page_form" />
							</jsp:include>
						</div>
					</div>
					<!-- /body -->
				</div>
			</div>
			<!-- /group -->
		</div>
		<!-- /body -->
	</div>
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
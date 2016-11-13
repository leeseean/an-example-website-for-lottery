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
<div class="panel-content font-hei" style="height:850px;overflow-y:scroll">
	<div class="panel-record">		
		<div class="page-title clear">
			<div class="left">
				<h2 class="s24 blue">投注记录</h2>
				<p class="mt5 gray">体育平台记录查询</p>
			</div>
			<div class="right mt10">
				<button
					class="button button-primary button-raised button-pill button-tiny btn-contact">
					<i></i>在线客服
				</button>
			</div>
		</div>
		<div class="page-body switch-mod" >
			<div class="switch-menu s16">
				<ul class="center clear">
					<li class="item on">记录查询</li>
					<li class="item" onclick="goParent('${ctx}/records/betReport')">报表全览</li>
				</ul>
			</div>
			<div class="switch-group" >
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
											<select id="code" name="code" class="dropmenu w100" onchange="resetPageNo();">
												<option value="sport" <c:if test="${records.code=='sport'}">selected="selected"</c:if>>体育</option>
												<option value="ag" <c:if test="${records.code=='ag'}">selected="selected"</c:if>>AG</option>
												<option value="hg" <c:if test="${records.code=='hg'}">selected="selected"</c:if>>HG</option>
												<option value="bbin" <c:if test="${records.code=='bbin'}">selected="selected"</c:if>>BBIN</option>
												<option value="mg" <c:if test="${records.code=='mg'}">selected="selected"</c:if>>MG</option>
												<option value="ds" <c:if test="${records.code=='ds'}">selected="selected"</c:if>>DS</option>
												<option value="nt" <c:if test="${records.code=='nt'}">selected="selected"</c:if>>NT</option>
												<option value="pt" <c:if test="${records.code=='pt'}">selected="selected"</c:if>>PT</option>
											</select>
										</li>
										<li class="pl10">
											<button  type="submit"  class="button button-primary button-small">查询</button>
										</li>
									</ul>
								</form>
							</div>
							<!-- /title -->
							<div class="sheet-content mt20" >
								<table class="center">
									<tr class="sheet-title s14 b">
										<td width="100">下注时间</td>
										<td width="100">注单号</td>
										<td width="300">内容</td>
										<td width="100">投注金额</td>
										<td width="100">输赢</td>
										<td width="80">结果</td>
									</tr>
									<c:if test="${empty page.result}">
										<tr height="125px;">
											<td colspan="6"><span class="gray-dark">查询结果 - 暂无纪录</span></td>
										</tr>
									</c:if>
									
									<c:forEach var="bet" items="${page.result}">
										<c:if test="${bet.matchRtype ne 'p3'}">
											<c:forEach var="detail" items="${bet.details}">
												<tr style="border-bottom:1px solid #ccc;">
													<td align="center"><fmt:formatDate value="${bet.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td align="center">${bet.betWagersId}</td>
													<td align="left" style="padding:10px 10px 10px 20px;line-height:1.5;">
														<div>
															<!-- 联盟名称 -->
															<p>${detail.league }</p>
															<!-- 比赛名称 -->
															<p><font color="blue"> ${detail.betVs } </font></p>
															<p><font color="red">${detail.betOddsDes }</font></p>
															<!-- 盘口 -->
															<p>
																<font color="red">${detail.betOddsName }</font> @ <strong class="light" id="xiazhu_odds" style="color: green">${detail.betOdds }</strong>
															</p>
														</div>
													</td>
													<td align="center">${bet.betIn}</td>
													<td align="center">${bet.betUsrWin<=0?"<font color=red>":"<font color=green>"} ${bet.betUsrWin}</td>
													<td><c:set var="betStatus">${bet.betStatus}</c:set>
														<font color='${bet.betNetWin!=null?(bet.betNetWin >= 0?"green":"red"):"#8B8C8C"}'> ${betSportMap[betStatus]} </font>
													</td>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${bet.matchRtype eq 'p3'}">
											<tr>
												<td align="center"><fmt:formatDate value="${bet.orderTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td align="center">${bet.betWagersId}</td>
												<td align="left" style="padding:10px 10px 10px 20px;line-height:1.5">
													<c:forEach var="detail" items="${bet.details}">
														<div>
															<!-- 联盟名称 -->
															<p>${detail.league }</p>
															<!-- 比赛名称 -->
															<p>
																<font color="blue">${detail.betVs }</font>
															</p>
															<p>
																<font color="red">${detail.betOddsDes }</font>
															</p>
															<!-- 盘口 -->
															<p>
																<font color="red">${detail.betOddsName }</font> @ <strong class="light" id="xiazhu_odds" style="color: green">${detail.betOdds}</strong>
															</p>
														</div>
														<hr style="margin:5px 0;height:1px;border:none;border-top:1px solid #ddd;width: 95%">
													</c:forEach>
												</td>
												<td align="center">${bet.betIn}</td>
												<td align="center">${bet.betUsrWin<=0?"<font color=red>":"<font color=green>"} ${bet.betUsrWin}</td>
												<td>
													<c:set var="betStatus">${bet.betStatus}</c:set> 
													<font color='${bet.betNetWin!=null?(bet.betNetWin >= 0?"green":"red"):""}'> ${betSportMap[betStatus]} </font>
												</td>

											</tr>
										</c:if>
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
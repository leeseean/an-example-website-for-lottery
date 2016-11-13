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
				<h2 class="s24 blue">投注记录</h2>
				<p class="mt5 gray">报表全览</p>
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
					<li class="item" onclick="goParent('${ctx}/records/goList?code=sport')">记录查询</li>
					<li class="item on">报表全览</li>
				</ul>
			</div>
			<div class="switch-group">
				<div class="switch-item panel-record">
					<div class="page-body">
						<div class="sheet-mod">
							<div class="title">
								<form id="page_form" target="memberFrame" action="${ctx}/records/betReport" method="Post" >
									 
									<ul class="clear">
										<li class="gray">从：</li>
										<li>
											<input type="text" id="startTime" name="startTime" value="${records.startTime}" readonly="readonly" class="kui-input-date s14 datepicker" />
										</li>
										<li class="pl10 gray">到：</li>
										<li>
											<input type="text" id="endTime" name="endTime" value="${records.endTime}" readonly="readonly" class="kui-input-date s14 datepicker"/>
										</li>
											
									 
										<li class="pl10">
											<button  type="submit"  class="button button-primary button-small">查询</button>
										</li>
										<li class="pl10">
											<font color="red">可以查询昨天及昨天之前的的数据</font>
										</li>
									</ul>
								</form>
							</div>
							<!-- /title -->
							<div class="sheet-content mt20">
								<table class="center">
									<tr class="sheet-title s14 b">
										<td width="100">平台</td>
										<td width="100">注单量</td>
										<td width="100">投注额</td>
										<td width="100">有效投注</td>
										<td width="100">赢利</td>
									</tr>
									<c:forEach var="item" items="${reports}" varStatus="item_index">
										<tr class="sheet-body s12">
											<td >${item.betFlat }</td>
											<td align="center">${item.betTotal }</td>
											<td align="center">${empty item.betIn?0:item.betIn }</td>
											<td align="center">${empty item.betIncome?0:item.betIncome }</td>
										    <td style='color: ${item.betUserWin>0?"red":"green" } '>${empty item.betUserWin?0:item.betUserWin }</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
					<!-- /body -->
				</div>
				<!-- /item -->
			</div>
			<!-- /group -->
		</div>
		<!-- /body -->
	</div>
</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
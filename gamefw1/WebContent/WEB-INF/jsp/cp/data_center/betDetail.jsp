<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>下注明细</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp">
<link rel="stylesheet" href="${resourceRoot}/cp/css/pure-min.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto-mod.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto.css">
<script src="${resourceRoot}/cp/js/jquery-1.11.0.min.js"></script>
<script src="${resourceRoot}/cp/js/plugins.js"></script>
<style>
	.ui-form .ui-body-son .ui-item-son {
		font-size: 12px;
	    font-weight: normal;
	}
	
	.ui-form .ui-body-son .ui-item-son  a {
	    font-weight: normal;
	}
</style>
</head>
<body>
	<div class="ybb-lotto">
		<!-- 交易状况 -->
		<div class="lotto-body">
			<div class="pure-g inner">
				<div class="pure-u-1">
					<div class="lotto-assist">
						<div class="ui-mod ui-form mod-assist assist-history">
							<div class="ui-mod-head cf">
								<div class="l b">下注状况&nbsp;&nbsp;<span style="color: red;">(此处是未结算注单，已结算请参考帐户历史)</span></div>
								<div class="r">
									<form  id="page_form" method="post" name="page_form" action="${ctx}/cpHistory/betDetail">
										<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
										<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
										<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
										
										<select name="gameTypeCode" id="gameTypeCode" onchange="javascript:document.forms[0].submit();" class="ui-input ui-custom1" >
											<option value="">全部</option>
											<c:forEach var="item" items="${gameMap}" varStatus="item_index" >
												<option <c:if test="${item.value.gameTypeCode eq record.gameTypeCode}">selected="selected"</c:if> value="${item.value.gameTypeCode}">${item.value.gameTypeName}</option>
											</c:forEach>
										</select>
									</form>
								</div>
							</div>
							<div class="ui-mod-body">
								<div class="ui-table">
									<div class="ui-table-loop">
										<div class="ui-head-son">
											<div class="pure-g">
												<div class="pure-u-1-24">
													<div class="ui-item-son">序号</div>
												</div>
												<div class="pure-u-3-24">
													<div class="ui-item-son">下注时间</div>
												</div>
												<div class="pure-u-3-24">
													<div class="ui-item-son">彩种</div>
												</div>
												<div class="pure-u-2-24">
													<div class="ui-item-son">玩法</div>
												</div>
												<div class="pure-u-3-24">
													<div class="ui-item-son">期数</div>
												</div>
												<div class="pure-u-6-24">
													<div class="ui-item-son">内容</div>
												</div>
												<div class="pure-u-2-24">
													<div class="ui-item-son">赔率</div>
												</div>
												<div class="pure-u-2-24">
													<div class="ui-item-son">下注金额</div>
												</div>
												<div class="pure-u-2-24">
													<div class="ui-item-son">可赢金额</div>
												</div>
											</div>
										</div>
										<div class="ui-body-son">
											<c:choose>
												<c:when test="${empty page.result}">
													<div class="pure-u-1">
														<div class="ui-item-son">暂无下注记录</div>
													</div>
												</c:when>
												<c:otherwise>
													<c:forEach var="item" items="${page.result}" varStatus="item_index">
														<div class="pure-g">
															<div class="pure-u-1-24">
																<div class="ui-item-son">${item_index.index+1}</div>
															</div>
															<div class="pure-u-3-24">
																<div class="ui-item-son"><fmt:formatDate value="${item.XZSJ}" pattern="yy/MM/dd HH:mm" /></div>
															</div>
															<div class="pure-u-3-24" >
																<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.GAME_TYPE_NAME}</div>
															</div>
															<div class="pure-u-2-24">
																<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.CP_TYPE_NAME}</div>
															</div>
															<div class="pure-u-3-24">
																<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.QS}</div>
															</div>
															<div class="pure-u-6-24">
																<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.CONTENT}</div>
															</div>
															<div class="pure-u-2-24">
																<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.PL}</div>
															</div>
															<div class="pure-u-2-24">
																<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.XZJE}</div>
															</div>
															<div class="pure-u-2-24">
																<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.KYJE}</div>
															</div>
														</div>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</div>
										<c:if test="${!empty page.result &&  page.totalPages>1}">
											<jsp:include page="../footPage.jsp">
												<jsp:param name="action" value="${ctx}/cpHistory/betDetail" />
												<jsp:param name="form_id" value="page_form" />
											</jsp:include>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- / 交易状况 -->
			</div>
		</div>
		<!-- /lotto-body -->
	</div>
</body>
</html>


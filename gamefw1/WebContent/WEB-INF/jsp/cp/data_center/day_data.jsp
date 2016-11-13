<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<style>
	.ui-form .ui-body-son .ui-item-son {
		font-size: 12px;
	    font-weight: normal;
	}
	
	.ui-form .ui-body-son .ui-item-son  a {
	    font-weight: normal;
	}
</style>

<div class="ui-mod ui-form mod-assist assist-history history-info">
	<div class="ui-mod-head cf">
		<div class="l b">
			<strong class="red">${dateStr}</strong> 下注状况
		</div>
		<div class="r">
			<form id="page_form" method="post" name="page_form"  action="${ctx}/cpHistory/day" >
				<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" />
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" />
				<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" />
				<input type="hidden" name="gameTypeCode" value="${gameTypeCode}"/>
			</form>
			<form id="page_form2" method="post" name="page_form2"  action="${ctx}/cpHistory/week" >
				<input type="submit" value="返回" class="button button-small button-raised">
			</form>
		</div>
	</div>
	<div class="ui-mod-body">
		<div class="ui-table">
			<div class="ui-table-loop">
				<div class="ui-head-son">
					<div class="pure-g">
						<!-- 
						<div class="pure-u-1-24">
							<div class="ui-item-son">序号</div>
						</div>
						 -->
						<div class="pure-u-4-24">
							<div class="ui-item-son">下注单号</div>
						</div>
						<div class="pure-u-3-24">
							<div class="ui-item-son">下注时间</div>
						</div>
						<div class="pure-u-3-24">
							<div class="ui-item-son">彩种/玩法</div>
						</div>
						<div class="pure-u-2-24">
							<div class="ui-item-son">期数</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son">内容</div>
						</div>
						<div class="pure-u-2-24">
							<div class="ui-item-son">下注金额</div>
						</div>
						<div class="pure-u-2-24">
							<div class="ui-item-son">返水金额</div>
						</div>
						<div class="pure-u-2-24">
							<div class="ui-item-son">中奖金额</div>
						</div>
						<div class="pure-u-2-24">
							<div class="ui-item-son">实际盈亏</div>
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
									<!-- 
									<div class="pure-u-1-24"><div class="ui-item-son">${item_index.index+1}</div></div>
									 -->
									<div class="pure-u-4-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.XZDH}</div>
									</div>
									<div class="pure-u-3-24">
										<div class="ui-item-son">
											<fmt:formatDate value="${item.XZSJ}" pattern="yy/MM/dd HH:mm" />
										</div>
									</div>
									<div class="pure-u-3-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.GAME_TYPE_NAME}.${item.CP_TYPE_NAME}</div>
									</div>
									<div class="pure-u-2-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.QS}</div>
									</div>
									<div class="pure-u-4-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.CONTENT}</div>
									</div>
									<div class="pure-u-2-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.XZJE}</div>
									</div>
									<div class="pure-u-2-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">${item.BACK_WATER_MONEY}</div>
									</div>
									<div class="pure-u-2-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">
											${item.WIN_MONEY}
										</div>
									</div>
									<div class="pure-u-2-24">
										<div class="ui-item-son" style="text-align:left; text-indent: 5px;">
											<span style="color:${item.BET_USR_WIN>0?'red':'blue'}">${item.BET_USR_WIN}</span>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
					<c:if test="${!empty page.result &&  page.totalPages>1}">
						<jsp:include page="../footPage.jsp">
							<jsp:param name="action" value="${ctx}/cpHistory/week" />
							<jsp:param name="form_id" value="page_form" />
						</jsp:include>
					</c:if>
			</div>
		</div>
	</div>
</div>
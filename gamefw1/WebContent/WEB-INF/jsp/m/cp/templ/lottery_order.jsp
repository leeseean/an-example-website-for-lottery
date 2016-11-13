<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div data-role="page" data-close-btn="right">
	<div data-role="header">
		<script src="${resourceRoot}/m/js/comm.js"></script>
		<h1>彩票投注</h1>
	</div>

	<!-- 投注單 -->
	<div data-role="content" class="ybm-form ybm-order ybm-lott-order">

		<form action="${ctx}/m/mobileCpOrder/goOrderList" id="order_form" method="post">
			<input type="hidden" id="gameTypeCode" name="gameTypeCode" value="${param.gameTypeCode }" /> 
			<input type="hidden" id="gameTypeName" name="gameTypeName" value="${gameTypeName }" /> 
			<input type="hidden" id="cpTypeCode" name="cpTypeCode" value="${param.cpTypeCode }" /> 
			<input type="hidden" id="cpCateCode" name="cpCateCode" value="${param.cpCateCode }" /> 
			<input type="hidden" id="xzlxCode" name="xzlxCode" value="${param.xzlxCode }" /> 
			<input type="hidden" id="minMoney" name="minMoney" value="${cpType.gminSingle }" /> 
			<input type="hidden" id="maxMoney" name="maxMoney" value="${cpType.gmaxSingle }" /> 
			<input type="hidden" id="maxSumMoney" name="maxSumMoney" value="${cpType.singleCredit }" />
			<input type="hidden" id="userMoney" name="userMoney" value="${userMoney }" />
			<ul data-role="listview" class="r1">
				<li data-role="list-divider" data-theme="e">期数：${nextResult.formatQs} 开奖日期：${nextResult.kjsj}</li>
				<c:if test="${sessionScope.USER_CONTEXT_KEY!=null }">
					<li>额度： <strong class="red"><fmt:formatNumber value="${userMoney }" type="currency" pattern="#,#00.0#" /></strong></li>
				</c:if>
				<li class="tac">
					<fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
						<c:if test="${sessionScope.USER_CONTEXT_KEY==null }">
							<a href="${ctx}/m/main/noLogin" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b">投注</a>
						</c:if>
						<c:if test="${sessionScope.USER_CONTEXT_KEY!=null }">
							<a href="javascript:" onclick="doOrderSubmit('order_form',this);" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b" data-ajax="false">投注</a>
						</c:if>
						<a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
					</fieldset>
				</li>

				<c:forEach var="conf" items="${confList }" varStatus="st">
					<c:choose>
						<c:when test="${conf.enumCode eq 'TMSB' }">
							<li class="ui-btn-up-d">
								<div data-role="fieldcontain" style=" padding: 0;">
									<label for="zxx${st.count }"> ${conf.cpCateName}<c:if test="${null!=conf.xzlxName && conf.xzlxName ne '' }">&${conf.xzlxName }</c:if> -${conf.number }@ <strong class="red">${conf.pl }</strong> </label>
									<table style="width: 100%">
										<tbody>
											<tr style="width: 100%">
												<td style=" width: 25%">
													<div class="ui-select">
														<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="">
															<select id="num1" name="num1">
																<c:forEach begin="0" end="27" var="i">
																	<option value="${i}">${i}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</td>
												<td style=" width: 25%">
													<div class="ui-select">
														<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="">
															<select id="num2" name="num2">
																<c:forEach begin="0" end="27" var="i">
																	<option value="${i}" <c:if test="${i eq 1 }">selected</c:if> >${i}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</td>
												<td style=" width: 25%">
													<div class="ui-select">
														<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="">
															<select id="num3" name="num3">
																<c:forEach begin="0" end="27" var="i">
																	<option value="${i}" <c:if test="${i eq 2 }">selected</c:if>>${i}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</td>
												<td style=" width: 25%">
													<div class="ui-select">
														<div data-corners="true" data-shadow="true" data-iconshadow="true" data-wrapperels="span" data-icon="arrow-d" data-iconpos="right" data-theme="c" class="">
															<a href="#" data-role="button" onclick="addTmsb();">添加</a>
														</div>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
									<input type="text" id="TMSB_NUM" data-mini="true" readonly="readonly"/>									
									<input type="number" enumcode="${conf.enumCode }" placeholder="请输入金额" pattern="[0-9]*" name="number[]" id="${conf.id }" data-mini="true" num="${conf.number}" minSingle="${conf.gminSingle }" maxSingle="${conf.gmaxSingle }" rate="${conf.pl }" cateName="${conf.cpCateName }" xzlxName="${conf.xzlxName }" />
								</div>
							</li>
						</c:when>
						<c:otherwise>
							<li class="ui-btn-up-d">
								<div data-role="fieldcontain" style=" padding: 0;">
									<label for="zxx${st.count }"> ${conf.cpCateName}
										<c:if test="${null!=conf.xzlxName && conf.xzlxName ne '' }">&${conf.xzlxName }</c:if>-${conf.number }@ <strong class="red">${conf.pl }</strong> </label> 
									<input type="number" pattern="[0-9]*" name="number[]" id="${conf.id }" data-mini="true" num="${conf.number}" rate="${conf.pl }" cateName="${conf.cpCateName }" xzlxName="${conf.xzlxName }" minSingle="${conf.gminSingle }" maxSingle="${conf.gmaxSingle }"/>
								</div></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<li class="tac">
					<fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
						<c:if test="${sessionScope.USER_CONTEXT_KEY==null }">
							<a href="${ctx}/m/main/noLogin" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b">投注</a>
						</c:if>
						<c:if test="${sessionScope.USER_CONTEXT_KEY!=null }">
							<a href="javascript:" onclick="doOrderSubmit('order_form',this);" data-rel="dialog" data-transition="pop" data-role="button" data-icon="check" data-theme="b" data-ajax="false">投注</a>
						</c:if>
						<a href="" data-role="button" data-rel="back" data-icon="back">取消</a>
					</fieldset>
				</li>
			</ul>
		</form>
	</div>
	<jsp:include page="../../no_login.jsp"></jsp:include>
	<!-- /投注單 -->
</div>
<!-- /page -->
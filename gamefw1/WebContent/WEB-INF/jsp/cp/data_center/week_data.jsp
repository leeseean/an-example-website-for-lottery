<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<script type="text/javascript">
	function CheckForm() {
		if ($("#startTimeStr").val() == "") {
			alert("请选择开始日期！");
			return false;
		}
		if ($("#endTimeStr").val() == "") {
			alert("请选择结束日期！");
			return false;
		}
		if (getDays($("#startTimeStr").val(), $("#endTimeStr").val()) > 7) {
			alert("范围不能超过7天！");
			return false;
		}
		$("#selForm").submit();
	}
	function getDays(strDateStart, strDateEnd) {
		var strSeparator = "-";
		var oDate1;
		var oDate2;
		var iDays;
		oDate1 = strDateStart.split(strSeparator);
		oDate2 = strDateEnd.split(strSeparator);
		var strDateS = new Date(oDate1[0], oDate1[1] - 1, oDate1[2]);
		var strDateE = new Date(oDate2[0], oDate2[1] - 1, oDate2[2]);
		iDays = parseInt(Math.abs(strDateS - strDateE) / 1000 / 60 / 60 / 24);
		return iDays;
	}
</script>
<style>
	.ui-form .ui-body-son .ui-item-son {
		font-size: 12px;
	    font-weight: normal;
	}
	
	.ui-form .ui-body-son .ui-item-son  a {
	    font-weight: normal;
	}
</style>
<div class="ui-mod ui-form mod-assist assist-history">
	<div class="ui-mod-head cf">
		<div class="l b">账户历史摘要<span style="color: red;">&nbsp;&nbsp;(点击日期可查看明细)</span></div>
		<form id="selForm" action="${ctx}/cpHistory/week">
			<div class="r">
				<input name="startTimeStr" class="ui-input ui-hand ui-custom1"
					id="startTimeStr" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
					type="text" value="${record.startTimeStr }"> ~ <input
					name="endTimeStr" class="ui-input ui-hand ui-custom1" id="endTimeStr" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
					type="text" value="${record.endTimeStr }"> 
				<input type="button" onclick="CheckForm();" value="搜索" class="button button-small button-raised button-primary" /> 
				<select name="gameTypeCode" id="gameTypeCode" class="ui-input ui-custom1" onchange="selType(this.value)">
					<option value="">全部</option>
					<c:forEach var="item" items="${gameMap}" varStatus="item_index">
						<option <c:if test="${item.value.gameTypeCode eq record.gameTypeCode}">selected="selected"</c:if> value="${item.value.gameTypeCode}">${item.value.gameTypeName}</option>
					</c:forEach>
				</select>
			</div>
		</form>
	</div>
	<div class="ui-mod-body">
		<div class="ui-table">
			<div class="ui-table-loop">
				<div class="ui-head-son">
					<div class="pure-g">
						<div class="pure-u-1-24">
							<div class="ui-item-son">序号</div>
						</div>
						<div class="pure-u-7-24">
							<div class="ui-item-son">日期</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son">有效投注额</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son">返水金额</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son">中奖金额</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son">实际盈亏</div>
						</div>
					</div>
				</div>
				<div class="ui-body-son">
					<div class="pure-g">
						<!-- 循环 -->
						<c:set var="xzjeTotal" value="0"/>
						<c:set var="backWaterMoneyTotal" value="0"/>
						<c:set var="winMoneyTotal" value="0"/>
						<c:set var="betUsrWinTotal" value="0"/>
						<c:forEach var="item" items="${dataList}" varStatus="item_index">
							<div class="pure-u-1-24">
								<div class="ui-item-son">${item_index.index+1}</div>
							</div>
							<div class="pure-u-7-24">
								<div class="ui-item-son">
									<a style="color:blue;" href="${ctx}/cpHistory/day?dateStr=${item.betTime}&gameTypeCode=${record.gameTypeCode}">${item.betTime}&nbsp;${item.week }</a>
								</div>
							</div>
							<div class="pure-u-4-24">
								<div class="ui-item-son" style="text-align: left; text-indent: 5px;">
									<fmt:formatNumber type="number"  pattern="###0.000" value="${item.xzje}"/>
								</div>
							</div>
							<div class="pure-u-4-24">
								<div class="ui-item-son" style="text-align: left;text-indent: 5px;">
									<fmt:formatNumber type="number"  pattern="###0.000" value="${item.backWaterMoney}"/>
								</div>
							</div>
							<div class="pure-u-4-24">
								<div class="ui-item-son" style="text-align: left;text-indent: 5px; ">
									<span style="color:${item.winMoney>0?'red':'blue'}">
										<fmt:formatNumber type="number"  pattern="###0.000" value="${item.winMoney}"/>
									</span>
								</div>
							</div>
							<div class="pure-u-4-24">
								<div class="ui-item-son" style="text-align: left;text-indent: 5px;">
									<span style="color:${item.betUsrWin>0?'red':'blue'}">
										<fmt:formatNumber type="number"  pattern="###0.000" value="${item.betUsrWin}"/>
									</span>
								</div>
								<c:set var="xzjeTotal" value="${item.xzje+xzjeTotal}"/>
								<c:set var="backWaterMoneyTotal" value="${item.backWaterMoney+backWaterMoneyTotal}"/>
								<c:set var="winMoneyTotal" value="${item.winMoney+winMoneyTotal}"/>
								<c:set var="betUsrWinTotal" value="${item.betUsrWin+betUsrWinTotal}"/>
							</div>
						</c:forEach>
						<!-- / 循环 -->
						
						<div class="pure-u-8-24" style="width: 33.3%;">
							<div class="ui-item-son">小计</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son" style="text-align: left;text-indent: 5px;"><fmt:formatNumber type="number"  pattern="###0.000" value="${xzjeTotal}"/></div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son" style="text-align: left;text-indent: 5px;">
								<fmt:formatNumber type="number"  pattern="###0.000" value="${backWaterMoneyTotal}"/>
							</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son" style="text-align: left;text-indent: 5px;">
								<span style="color:${winMoneyTotal>0?'red':''}">
									<fmt:formatNumber type="number"  pattern="###0.000" value="${winMoneyTotal}"/>
								</span>
							</div>
						</div>
						<div class="pure-u-4-24">
							<div class="ui-item-son" style="text-align: left;text-indent: 5px;">
								<span style="color:${betUsrWinTotal>0?'red':'blue'}">
									<fmt:formatNumber type="number"  pattern="###0.000" value="${betUsrWinTotal}"/>
								</span>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
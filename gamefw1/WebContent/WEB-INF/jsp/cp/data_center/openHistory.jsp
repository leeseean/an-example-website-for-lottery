<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>历史开奖</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp">
<link rel="stylesheet" href="${resourceRoot}/cp/css/pure-min.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/ui-button.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/ui-icon.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto-mod.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto.css">
<script src="${resourceRoot}/cp/js/jquery-1.11.0.min.js"></script>
<script src="${resourceRoot}/cp/js/datePicker/WdatePicker.js" type="text/javascript"></script>
<link href="${resourceRoot}/cp/js/datePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
</head>
<script type="text/javascript">
	function goto_query() {
		valide($("#startTimeStr").val(), $("#endTimeStr").val());
		var checkNum =/^[0-9]*[1-9][0-9]*$/
		if($('#qs').val()!=''){
			if(!checkNum.test($('#qs').val())){
				alert("只能输入数字!");
				return;
			}
		}
		
		$('#page_form').submit();
	}
	function valide(startTime, endTime) {
		if (endTime < startTime) {
			alert("开始时间不能大于结束时间!");
			return false;
		}
	}
	function selType(code) {
		$("#pageNo").val("1");
		$("#code").val(code);
		console.log($("#code").val(code));
		goto_query();
	}
	
	function validNum(obj){
		var $obj = $(obj);
		if(!isInteger($obj.val())){
			$obj.val("");
		}else if($obj.val().length > 15){
			$obj.val($obj.val().substring(0,15));
		}
	}
	
	function isInteger(num){
		return /^\+?[1-9][0-9]*$/.test(num);
	};

	var numstr = new Array();
	numstr[0] = "01.02.07.08.12.13.18.19.23.24.29.30.34.35.40.45.46.";//红波
	numstr[1] = "03.04.09.10.14.15.20.25.26.31.36.37.41.42.47.48.";//蓝波
	numstr[2] = "05.06.11.16.17.21.22.27.28.32.33.38.39.43.44.49.";//绿波
</script>
<body>

	<div class="ybb-lotto">
		<!-- 开奖结果 -->
		<div class="lotto-body">
			<div class="pure-g inner">

				<div class="pure-u-1">
					<div class="lotto-assist">
						<div class="ui-mod ui-form mod-assist assist-result">
							<form id="page_form" method="post" name="page_form" action="${ctx}/cpResult/goList">
								<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo }" /> 
								<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }" /> 
								<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages }" /> 
								<input type="hidden" id="code" name="code" value="${results.code }" />
								<div class="ui-mod-head cf">
									<div class="l b">${results.name}开奖管理</div>
									<div class="r">
										时间段：<input type="text" value="${results.bTime}"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"
											id="startTimeStr" name="bTime"
											class="ui-input ui-hand ui-custom1">~ <input name="eTime" value="${results.eTime}" class="ui-input ui-hand ui-custom1"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" type="text" readonly="readonly"/> 
										期数：<input type="text" class="ui-input ui-hand ui-custom1" id="qs" name="qs" value="${results.qs}" onkeyup="validNum(this);"/> 
										<input type="button" name="B1" value="搜索" class="button button-small button-raised button-primary" onclick="goto_query()" /> 
										<select name="tem" class="ui-input ui-custom1" onchange="selType(this.value)">
											<c:forEach var="item" items="${gameMap}" varStatus="item_index">
												<option <c:if test="${item.value.gameTypeCode==results.code}">selected="selected"</c:if> value="${item.value.gameTypeCode}">${item.value.gameTypeName}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</form>
							<style>
							<!--
							.ui-form .ui-item-son{
								padding:8px 0;
							}
							-->
							</style>
							<!-- 六合彩 -->
							<jsp:include page="../kjResult/${pageName}_results.jsp"></jsp:include>
							<jsp:include page="../footPage.jsp">
								<jsp:param name="action" value="${ctx}/cpResult/goList" />
								<jsp:param name="form_id" value="page_form" />
							</jsp:include>

						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
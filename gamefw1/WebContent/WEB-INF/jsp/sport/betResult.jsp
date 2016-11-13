<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
	<head>
		<title>皇冠体育</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="${resourceRoot}/sport/css/mem_order_ft.css" rel="stylesheet">
	 	<script>
		var root= "${resourceRoot}";
		var rootPath = "${ctx}";
		</script>
		<script src="${resourceRoot}/sport/js/jquery.min.js" type="text/javascript"></script>
		<script src="${resourceRoot}/sport/js/sport.js" type="text/javascript" ></script>
 
	</head>
<body id="HFT" class="bodyset" >
 
	<div class="ord" id="mainBox">
		<div class="title">
			<h1>${sportBetBean.matchType eq 'FT' ? '足球' : '篮球' } </h1>
		</div>
		<div class="main">
			<c:choose>
				<c:when test="${empty sportBetBean}">
					<div class="fin_title"><p class="error">${msg}</p></div>
				</c:when>
				<c:otherwise>
					<div class="fin_title">
						<p class="fin_acc">${msg}</p>
						<p class="fin_uid" style="height: 28px;">注单号：${sportBetBean.betWagersId}</p>
						<p class="error" style="display: none;"></p>
					</div>
					<c:forEach var="bet" items="${sportBetBean.sportBetList}">
						<div class="leag">${bet.league}</div>
						<div class="gametype">${bet.oddsDes}</div>
						<div class="teamName">
							<span class="tName">${bet.team1 } <font class="radio">${bet.vs }</font> ${bet.team2 }</span>
						</div>
						<p class="fin_team">
							<em>${bet.oddsName}</em> @ <strong>${bet.odds}</strong>
						</p>
					</c:forEach>
					<p class="fin_amount">
						交易金额：<span class="fin_gold">${sportBetBean.gold}</span>
					</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="betBox">
			<input type="button" name="PRINT" value="列印" onclick="window.print()" class="print">
			<input type="button" name="FINISH" value="关闭" onclick="cancelBetOrder();" class="close">
		</div>
	</div>
</body>

</html>
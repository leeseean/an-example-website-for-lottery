<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>
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
		<script type="text/javascript" src="${resourceRoot}/sport/js/jquery.min.js"></script>
		<script type="text/javascript"  src="${resourceRoot}/sport/js/sport.js" ></script>
	</head>
<body id="HFT" class="bodyset" >
<form name="xiazhuForm" id="xiazhuForm" action="${ctx}/sport/orderBet" method="post">
	<mh:token></mh:token>
    <div class="ord">
        <div class="title">
        	<h1 style="line-height: 22px">${bet.matchType eq 'FT' ? '足球' : '篮球' } </h1>
        	<!-- 
            <div class="tiTimer" onclick="orderReload();">
            	<span id="ODtimer">10</span>
            	<input type="checkbox" id="checkOrder" onclick="onclickReloadTime()" checked="" value="10">
            </div>
             -->
        </div>
        <div class="main">
            <div class="leag">${bet.league}</div>
            <div class="gametype">${bet.oddsDes}</div>
            <!-- 
            <c:choose>
            	<c:when test="${bet.rtype eq 'f'}">
            		<div class="gametype">${bet.oddsDes }</div>
            	</c:when>
            	<c:otherwise>
            		<div class="gametype">${bet.oddsDes } 
            		<c:if test="${matchType eq 'FT'}">
            		- <font color="red">${bet.period eq 'f' ? '全场' : '上半场' }</font>
            		</c:if>
            		</div>
            	</c:otherwise>
            </c:choose>
            -->
            
            <div class="teamName"><span class="tName">${bet.team1} <font class="radio">${bet.vs}</font> ${bet.team2}</span></div>
            <p class="team"><em>${bet.oddsName}</em> @ <strong class="light" id="xiazhu_odds">${bet.odds}</strong></p>

            <p class="auto">
            	<input type="checkbox" checked="checked" id="autoOdd" disabled="disabled" readonly="readonly" name="autoOdd" onclick="onclickReloadAutoOdd()" value="Y">
            	<span class="auto_info" title="在方格里打勾表示，如果投注单里的任何选项在确认投注时赔率变佳，系统会无提示的继续进行该下注。">自动接受最新赔率</span>
            </p>

            <div class="betdata">
                <p class="amount">交易金额：
                	<input name="gold" type="text" class="txt" id="gold" onKeyPress="return CheckKey(event)" onkeyup="changeWin(this,${bet.oddsMinus});" size="8" maxlength="9">
                </p>
                <p class="mayWin"><span class="bet_txt">可赢金额：</span><font id="xiazhu_win">0</font></p>
                <p class="minBet"><span class="bet_txt">单注最低：</span>${bet.limitBetMin}</p>
                <p class="maxBet"><span class="bet_txt">单注最高：</span>${bet.limitBet}</p>
            </div>
        </div>
        <div class="betBox">
            <input type="button" id="btnCancel" value="取消"  class="no"  onclick="cancelBetOrder();" />
            <span id="submit">
            	<input type="button" id="btnSubmit"   value="确定交易" class="yes" onclick="orderSubmit();return false;">
            </span>
        </div>
    </div>
    <input type="hidden" name="gid" value="${bet.gid}">
    <input type="hidden" name="timeType" value="${bet.timeType}">
    <input type="hidden" name="matchType" value="${bet.matchType}">
    <input type="hidden" name="rtype" value="${bet.rtype}">
    <input type="hidden" name="btype" value="${bet.btype}">
    <input type="hidden" name="dtype" value="${bet.dtype}">
    <input type="hidden" name="selection" value="${bet.selection}">
    <input type="hidden" name="period" value="${bet.period}">
    <input type="hidden" name="oddsName" value="${bet.oddsName}">
    <input type="hidden" name="limitBetMin" value="${bet.limitBetMin}">
    <input type="hidden" name="limitBet" value="${bet.limitBet}">
</form>
</body>
</html>
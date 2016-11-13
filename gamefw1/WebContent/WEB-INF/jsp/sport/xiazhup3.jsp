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
		<script src="${resourceRoot}/sport/js/sport.js" type="text/javascript" ></script>
	</head>
	<body id="HFT" class="bodyset" >
<form name="xiazhuForm" id="xiazhuForm"   action="${ctx}/sport/orderBet" method="post">
 	<mh:token></mh:token>
	<input type="hidden" name="timeType" value="${bet.timeType }">
    <input type="hidden" name="matchType" value="${bet.matchType }">
    <input type="hidden" name="rtype" value="${bet.rtype }">
	
	<input type="hidden" name="limitBetMin" value="${bet.limitBetMin }">
	<input type="hidden" name="limitBet" value="${bet.limitBet }">
    <div class="ord">
        <div class="title">
        	<h1 style="line-height: 22px">${sportBetBean.matchType eq 'FT' ? '足球' : '篮球' } </h1>
        	<!-- 
            <div class="tiTimer" onclick="orderReload();">
            	<span id="ODtimer">10</span>
            	<input type="checkbox" id="checkOrder" onclick="onclickReloadTime()" checked="" value="10">
            </div>
             -->
        </div>
        <div class="main">
        <c:set var="tmpOdds" value="1" ></c:set>
		<c:forEach var="bet" items="${sportBetBean.sportBetList}">
		<c:set var="tmpOdds" value="${tmpOdds*bet.odds}" ></c:set>
        	<!-- p3 begin -->
            <div class="leag">
            <span class="leag_txt">${bet.league }</span>
            <span class="deletebtn" ><input style="margin-bottom: 3px; " type="button" name="delteam1" value="" onclick="delteam('${bet.gid}')" class="par"></span>
            </div>
            <div class="gametype">${bet.oddsDes }</div>
            <!-- 
            <c:choose>
            	<c:when test="${bet.rtype eq 'f'}">
            		<div class="gametype">${bet.oddsDes }</div>
            	</c:when>
            	<c:otherwise>
            		<div class="gametype" style="margin-bottom: 2px;">${bet.oddsDes }
            		
            		<c:if test="${matchType eq 'FT'}">
            		 - <font color="red">${bet.period eq 'f' ? '全场' : '上半场' }</font>
            		</c:if> 
            		 
            		 </div>
            	</c:otherwise>
            </c:choose>
            -->
            
            <div class="teamName"><span class="tName">${bet.team1 } <font class="radio">${bet.vs }</font> ${bet.team2 }</span>
            <p class="team" style="background-color: white; line-height: 12px;padding-left: 5px;"><em>${bet.oddsName }</em> @ <strong class="light" id="xiazhu_odds">${bet.odds }</strong></p>
            </div>
            
         </c:forEach>
            <!-- p3 end -->
            
            <c:if test="${(fn:length(sportBetBean.sportBetList)) >= 3}">
            <p class="auto">
            	<input type="checkbox" checked="checked" id="autoOdd" disabled="disabled" readonly="readonly" name="autoOdd" onclick="onclickReloadAutoOdd()" value="Y">
            	<span class="auto_info" title="在方格里打勾表示，如果投注单里的任何选项在确认投注时赔率变佳，系统会无提示的继续进行该下注。">自动接受最新赔率</span>
            </p>

            <div class="betdata">
                <p class="amount">交易金额：
                	<input name="gold" type="text" value="" class="txt" id="gold" onKeyPress="return CheckKey3(event)" onkeyup="changeWinP3(this,1);" size="8" maxlength="9" style="height: 19px">
                	<fmt:formatNumber var="tmpOdds2" value="${tmpOdds}" pattern="#0.00"/>
                	<div style="margin-top: 8px">串关信息：@ <font color="red"><strong class="light" id="xiazhu3_odds">${tmpOdds2}</strong></font></div>
                </p>
                <p class="mayWin" style="margin-top: 3px"><span class="bet_txt">可赢金额：</span><font id="xiazhu_win">0</font></p>
                <p class="minBet" style="margin-top: 3px"><span class="bet_txt">单注最低：</span>${bet.limitBetMin }</p>
                <p class="maxBet" style="margin-top: 3px"><span class="bet_txt">单注最高：</span>${bet.limitBet }</p>
            </div>
            </c:if>
            
            
        </div>
        <c:if test="${(fn:length(sportBetBean.sportBetList)) >= 3}">
        <div class="betBox">
            <input type="button" name="btnCancel" value="取消" onclick="delteams();" class="no">
            <span id="submit"><input type="button"  id="btnSubmit"  onclick="orderSubmit();return false;" value="确定交易" class="yes"></span>
        </div>
        </c:if>
    </div>
</form>
<script type="text/javascript">
	parent.reinitIframeP3("${fn:length(sportBetBean.sportBetList)}");
</script>
</body>
</html>

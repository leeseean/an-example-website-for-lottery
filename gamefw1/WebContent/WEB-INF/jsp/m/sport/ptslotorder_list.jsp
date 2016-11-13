<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>PT注单</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
<c:choose>
<c:when test="${!empty map}">
<c:forEach var="map" items="${map }" varStatus="mapStatus">
  <div data-role="page" id="${map.key }">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu${mapStatus.index + 1}" data-transition="none" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a data-rel="back" data-transition="none" data-role="button" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>PT注单列表</h1>
      <div class="ui-btn-right">
        <a href="${ctx}/m/main/order" data-rel="dialog" data-role="button" data-icon="bars" data-theme="b">投注单</a>
      </div>
    </div>
    <!-- /header -->
    <div data-role="content">
      <c:choose>
      	<c:when test="${!empty map.value }">
      	<c:forEach var="item" items="${map.value }">
	        <div data-role="collapsible" data-collapsed="false" data-theme="b" data-content-theme="d" data-mini="true" class="ui-mini f12">
	          <h3>${item.betGameContent}</h3>
	          <ul data-role="listview">
	          	<li>订单号：${item.betWagersId}</li>
	          	<li>桌号：${item.betGameTable}</li>
	          	<li>局号：${item.betGameCode}</li>
	            <li>下注金额：${item.betIn}￥</li>
	            <li>有效投注：${item.betIncome}￥</li>
	            <li>输赢：${item.betUsrWin}￥</li>
	            <li>下注时间：<fmt:formatDate value="${item.betTime}" pattern="MM-dd HH:mm:ss" /></li>
	          </ul>
	        </div>
        </c:forEach>
        </c:when>
        <c:otherwise>
        	暂时没有记录
        </c:otherwise>
        </c:choose>
        <!-- /item -->
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed">
       <div data-role="navbar">
         <ul>
           <c:forEach var="page" items="${pageList }" varStatus="status">
             <li><a href="#${page }" data-transition="none" class='<c:if test="${status.index == mapStatus.index }">ui-btn-active ui-state-persist</c:if>' data-role="button">${status.index + 1}</a></li>
           </c:forEach>
         </ul>
       </div>
     </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu${mapStatus.index + 1}" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
</div>
</c:forEach>
 </c:when>
 <c:otherwise>
 	<div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-transition="none" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a data-rel="back" data-transition="none" data-role="button" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>PT注单列表</h1>
       <div class="ui-btn-right">
        <a href="${ctx}/m/main/order" data-rel="dialog" data-role="button" data-icon="bars" data-theme="b">投注单</a>
      </div>
    </div>
    <!-- /header -->
    <div data-role="content">
      	暂无记录!
    </div>
    <!-- /content -->
    <%@include file="../inc/mobile_foot.jsp" %>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu${mapStatus.index + 1}" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
</div>
 </c:otherwise>
</c:choose>
<!-- /page -->
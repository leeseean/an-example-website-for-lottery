<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>提款记录列表</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
<c:choose>
<c:when test="${!empty map}">
<c:forEach var="map" items="${map }" varStatus="mapStatus">
  <div data-role="page" id="${map.key }">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu${mapStatus.index + 1}" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a data-rel="back" data-role="button" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>提款记录列表</h1>
    </div>
    <!-- /header -->
    <div data-role="content">
      <div data-role="collapsible-set" data-corners="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="b" data-content-theme="d" class="ui-mini f12">
      <c:choose>
      	<c:when test="${!empty map.value }">
      	<c:forEach var="item" items="${map.value }">
	        <div data-role="collapsible" data-collapsed="false">
	          <h3>[${item.withdrawType==11?" 会员提款":"系统扣款" }] ${item.createTime}</h3>
	          <ul data-role="listview">
	            <li>订单号：${item.userOrder}</li>
	            <li>提款金额：${item.userWithdrawMoney}￥</li>
	            <li>提款状态：
		            <c:choose>
						<c:when test="${item.status ==0}">
							待审核
						</c:when>
						<c:when test="${item.status ==1}">
							<c:if test="${item.checkStatus ==1}">已出款</c:if>
							<c:if test="${item.checkStatus ==2}">拒绝提款</c:if>
						</c:when>
					</c:choose>
				</li>
	            <li>备注：${item.remark}</li>
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
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a data-rel="back" data-role="button" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>提款记录列表</h1>
    </div>
    <!-- /header -->
    <div data-role="content">
      	暂无记录!
    </div>
    <!-- /content -->
    <%@include file="../inc/mobile_foot.jsp" %>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
</div>
 </c:otherwise>
</c:choose>
<!-- /page -->
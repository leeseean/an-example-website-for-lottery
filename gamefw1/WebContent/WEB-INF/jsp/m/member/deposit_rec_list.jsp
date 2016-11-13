<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>入款记录列表</title>
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
      <h1>入款记录列表</h1>
    </div>
    <!-- /header -->
    <div data-role="content">
      <div data-role="collapsible-set" data-corners="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="b" data-content-theme="d" class="ui-mini f12">
      <c:choose>
      	<c:when test="${!empty map.value }">
      <c:forEach var="item" items="${map.value }">
        <div data-role="collapsible" data-collapsed="false">
          <h3><c:choose>
				<c:when test="${item.hkModel==1}">公司入款</c:when>
				<c:when test="${item.hkModel==2}">线上支付</c:when>
				<c:when test="${item.hkModel==3}">红利赠送</c:when>
				<c:when test="${item.hkModel==4}">后台入款</c:when>
				<c:when test="${item.hkModel==5}">投注返水</c:when>
				<c:when test="${item.hkModel==6}">免费彩金</c:when>
				<c:when test="${item.hkModel==7}">存款赠送</c:when>
				<c:when test="${item.hkModel==9}">存款优惠</c:when>
			</c:choose> ${item.hkTime }</h3>
          <ul data-role="listview">
            <li>订单号：${item.hkOrder }</li>
            <li>入款金额：${item.hkMoney }￥</li>
            <li>入款状态：
				<c:choose>
					<c:when test="${item.hkCheckStatus ==0}">
						未入帐
					</c:when>
					<c:when test="${item.hkCheckStatus ==1}">
						已入帐
					</c:when>
					<c:when test="${item.hkCheckStatus ==2}">
						未入帐
					</c:when>
					<c:when test="${item.hkStatus ==0}">
					(未审核)
					</c:when>
					<c:when test="${item.hkStatus ==1}">
						(已审核)
					</c:when>
				</c:choose>
			</li>
            <li>　　备注：${item.remark }</li>
            <li>汇款银行： ${item.hkCompanyBank }</li>
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
    <div data-role="panel" data-display="overlay" id="quickMenu${mapStatus.index + 1}" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
  </div>
  <!-- /page -->
  </c:forEach>
 </c:when>
 <c:otherwise>
 	<div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a data-rel="back" data-role="button" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>入款记录列表</h1>
    </div>
    <!-- /header -->
    <div data-role="content">
      	暂无记录!
    </div>
    <!-- /content -->
    <%@include file="../inc/mobile_foot.jsp" %>
    <!-- /footer -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
  </div>
 </c:otherwise>
 </c:choose>
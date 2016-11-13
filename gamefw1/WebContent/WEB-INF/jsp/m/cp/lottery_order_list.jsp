<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>彩票注单查看</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>
<body>
<c:if test="${pageNum==0 }"><jsp:include page="orderListInc.jsp"></jsp:include> </c:if>
<c:forEach var="obj" items="${groupList }" varStatus="st">
<div data-role="page" id="pageNum_${st.count }" data-close-btn="right">
  <div data-role="header">
  	 <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu${st.count }" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-icon="back" data-iconpos="notext"></a>
      </div>
    <h1>彩票投注历史</h1>
  </div>
  <!-- /header -->
 
  <div data-role="content">
    <div data-role="collapsible-set" data-corners="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="b" data-content-theme="d" class="ui-mini f12">
    <c:forEach var="order" items="${obj }" varStatus="stt">  
      <div data-role="collapsible" data-collapsed="false">
        <h3>[${order.gameTypeName}] ${order.xzsj }</h3>
        <ul data-role="listview">
          <li>注单号:${order.xzdh }</li>
          <li>期数:${order.qs }</li>
          <li>内容：${order.content }</li>
          <li>赔率：${order.pl }</li>
          <li>金额：${order.xzje }</li>
          <li>是否结算：${order.orderStatus eq '2'?'已结算':'未结算' }</li>
          <c:if test="${order.orderStatus eq '2'}">
          	<li>开奖结果：${order.kjjg }</li>
          	<li>会员收付：
          		<c:if test="${order.hysf>=0 }"><font color="red">${order.hysf }</font></c:if>
          		<c:if test="${order.hysf<0 }"><font color="blue">${order.hysf }</font></c:if>
          	</li>
          </c:if>
          
        </ul>
      </div>
     </c:forEach>
      <!-- /item -->
      
    </div>
  </div>
  <!-- /content -->


 <div data-role="footer" data-position="fixed">
          <div data-role="navbar" style="max-height: 15em; overflow:auto;">
            <ul>
              <c:forEach begin="1" end="${pageNum }" var="i">
              	<c:if test="${ i < 6}">                
              		<li><a href="#pageNum_${i }" <c:if test="${st.count == i }">class="ui-btn-active ui-state-persist"</c:if> data-role="button">${i}</a></li>
              	</c:if>
              </c:forEach>
            </ul>
          </div>
 </div>
 
 <div data-role="panel" data-display="overlay" id="quickMenu${st.count}" class="ybm-panel">
   <%@ include file="../inc/left_memu.jsp" %>
 </div>
 
 </div>
 </c:forEach>
 
 </body>
<!-- /page -->
</html>
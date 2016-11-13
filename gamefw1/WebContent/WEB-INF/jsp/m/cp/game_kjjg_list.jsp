<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/mobile_common.jsp"%>

<div data-role="page" id="pageNum_" data-close-btn="right">
  <div data-role="header">
  	 <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <!-- <a href="${ctx }/m/wap/member" data-role="button" data-icon="back" data-iconpos="notext"></a> -->
      </div>
    <h1>彩票投注历史</h1>
  </div>
  <!-- /header -->
 
  <div data-role="content">
    <div data-role="collapsible-set" data-corners="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="b" data-content-theme="d" class="ui-mini f12">
    <c:forEach var="order" items="${obj }" varStatus="st">  
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
          <div data-role="navbar">
            <ul>
                <li><a href="#pageNum_${i }" data-role="button">${i}</a></li>
            </ul>
          </div>
 </div>
   <%@ include file="../inc/left_memu.jsp" %>
 </div>
 </c:forEach>
<!-- /page -->
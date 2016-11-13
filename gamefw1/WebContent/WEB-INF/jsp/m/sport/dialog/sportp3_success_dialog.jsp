<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div data-role="page" data-close-btn="none">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div data-role="header">
    <h1>投注成功</h1>
  </div>
  <!-- 投注單 -->
  <div data-role="content" class="ybm-form ybm-order ybm-order-success">
    <ul data-role="listview" class="order-item">
      <li data-role="list-divider" data-mini="true" data-theme="d">注单号：<strong>${bet.orderNO }</strong></li>
    </ul>
    <c:forEach items="${betlist }" var="item">
	    <ul data-role="listview" data-inset="true" class="order-item">
	      <li data-role="list-divider" data-mini="true">${item.league }</li>
	      <li class="ui-mini">${item.oddsDes }</li>
	      <li>${item.oddsName } @ ${item.odds }</li>
	      <li>${item.teamH } vs ${item.teamC }</li>
	    </ul>
    </c:forEach>
    <ul data-role="listview" style=" margin-top: 0;">
      <li>
        交易金额：<strong style=" color: red;">${bet.money }</strong>
      </li>
      <li>
        <div data-role="fieldcontain" style=" padding: 0; text-align: center;">
          <a href="${bet.url }" data-ajax="false" data-role="button" data-corners="false" data-inline="true" data-icon="back">返回</a>
          <!-- /返回下注起始頁面 -->
        </div>
      </li>
    </ul>
  </div>
  <!-- /投注單 -->
</div>
<!-- /page -->
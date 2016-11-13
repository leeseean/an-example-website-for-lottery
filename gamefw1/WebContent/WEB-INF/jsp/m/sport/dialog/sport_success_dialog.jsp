<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div data-role="page" data-close-btn="none">
  <div data-role="header">
    <h1>投注成功     ${bet.roll eq true ? '滚球确认中' : '' }</h1>
  </div>
  <!-- 投注單 -->
  <div data-role="content" class="ybm-form ybm-order ybm-order-success">
    <ul data-role="listview" class="order-item">
      <li data-role="list-divider" data-mini="true" data-theme="d">注单号：<strong>${bet.orderNO }</strong></li>
    </ul>
    <ul data-role="listview" data-inset="true" class="order-item">
      <li data-role="list-divider" data-mini="true">${bet.league }</li>
      <li class="ui-mini">${bet.oddsDes }</li>
      <li>${bet.teamH } vs ${bet.teamC }</li>
    </ul>
    <ul data-role="listview" style=" margin-top: 0;">
      <li data-role="list-divider" data-theme="e">
        <div class="ui-grid-a">
          <div class="ui-block-a">${bet.oddsName } @ ${bet.odds }</div>
          <div class="ui-block-b" style=" text-align: right; display: none;">
            <label for="">自动接受最新赔率</label>
          </div>
        </div>
      </li>
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
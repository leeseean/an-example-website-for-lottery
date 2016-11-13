<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <%@include file="common.jsp"%>
  <base target="_self">
</head>

<body class="ag-frame-content font-hei">
<table width="100%" class="ag-form">
      <tbody>
        <tr class="ag-form-content">
          <td>会员输赢:红色(正数)-代表玩家赢的钱,绿色(负数)-代表玩家输的钱.</td>
        </tr>
        <tr class="ag-form-content">
          <td>可获佣金:红色(正数)-代表要支付代理费给代理商,绿色(亏损)-代表无需支付</td>
        </tr>
        <tr class="ag-form-content">
          <td>代理佣金计算公式:</td>
        </tr>
        <tr class="ag-form-content">
          <td>体育输赢总和 -（体育输赢总和 x 体育行政费比例） - 会员返水 x 行政费比例  = 实际盈利,实际盈利x退佣比例+体育有效投注x退水比例=代理佣金1;</td>
        </tr>
        <tr class="ag-form-content">
          <td>沙巴输赢总和 -（沙巴输赢总和 x 沙巴行政费比例） - 会员返水 x 行政费比例 = 实际盈利,实际盈利x退佣比例+沙巴有效投注x退水比例=代理佣金2;</td>
        </tr>
        <tr class="ag-form-content">
          <td>MG输赢总和 -（MG输赢总和 x MG行政费比例） - 会员返水 x 行政费比例 = 实际盈利,实际盈利x退佣比例+MG有效投注*退水比例=代理佣金3;</td>
        </tr>
        <tr class="ag-form-content">
          <td>BBIN输赢总和 -（BBIN输赢总和 x BBIN行政费比例） - 会员返水 x 行政费比例 = 实际盈利,实际盈利x退佣比例+BBIN有效投注x退水比例=代理佣金4;</td>
        </tr>
        <tr class="ag-form-content">
          <td>AG输赢总和 -（AG输赢总和 x AG行政费比例） - 会员返水 x 行政费比例 = 实际盈利,实际盈利x退佣比例+AG有效投注x退水比例=代理佣金5;</td>
        </tr>
        <tr class="ag-form-content">
          <td>可获佣金:代理佣金1+代理佣金2+代理佣金3+....+代理佣金11-存款手续费-取款手续费=代理可拿佣金;</td>
        </tr>
        <tr class="ag-form-content">
          <td>入款总量和出款总量:只用于计算本月代理下属会员的资金流水统计和计算手续费之用。由于会员可能上个月还有余额存在或者目前还有余额，会存在和本月游戏报表不符，所以仅供参考。</td>
        </tr>
      </tbody>
</table>
</body>
<%@include file="comm_foot.jsp"%>
</html>

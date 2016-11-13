<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>入款记录</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>入款记录</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-form-content">
      <form action="${ctx }/m/deposit/payHistory" method="post" data-ajax="false">
        <div data-role="fieldcontain">
          <label for="bet-order-filter-11">起始日期：</label>
          <input type="date" name="bet-order-filter-11" id="bet-order-filter-11" value="${currDateStr }">
        </div>
        <!-- /item -->
        <div data-role="fieldcontain">
          <label for="bet-order-filter-12">截至日期：</label>
          <input type="date" name="bet-order-filter-12" id="bet-order-filter-12" value="${currDateStr }">
        </div>
        <!-- /item -->
        <div data-role="fieldcontain">
          <fieldset data-role="controlgroup" data-type="horizontal" data-corners="false">
            <legend>查询类型：</legend>
            <select name="bet-order-filter-21" id="bet-order-filter-21">
              	<option value="">全部类型</option>
				<option value="1">
					公司入款
				</option>
				<option value="2">
					线上支付
				</option>
				<option value="3">
					红利赠送
				</option>
				<option value="5">
					投注返水
				</option>
				<option value="4">
					后台入款
				</option>
				<option value="6">
					免费彩金
				</option>
				<option value="7">
					存款赠送
				</option>
				<option value="9">
					存款优惠
				</option>
            </select>
          </fieldset>
        </div>
        <!-- /item -->
        <div data-role="fieldcontain" style=" text-align: center;">
          <!-- <a href="${ctx }/m/deposit/payHistory" data-role="button" data-inline="true" data-corners="false" data-icon="check" data-theme="b">开始查询</a> -->
          <input type="submit" value="提交">
        </div>
        <!-- /item -->
      </form>
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
  <!-- /page -->
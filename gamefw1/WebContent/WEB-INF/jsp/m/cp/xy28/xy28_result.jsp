<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>开奖结果</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../../inc/mobile_common.jsp" %>
</head>

<body>

<div data-role="page" data-theme="x" class="ybb-mobile ybb-mobile-web">

<%@ include file="../../index/head.jsp" %>
<!-- /header -->

<div data-role="content" class="lott">
  <div class="type">
    <c:forEach var="obj" items="${resultList }" varStatus="st">
      <div data-role="collapsible" data-collapsed="false" data-mini="true" data-theme="x" data-content-theme="c" class="ui-mini f12">
        <h3>[${obj.formatQs}]</h3>
        <ul data-role="listview">
          <li>开奖时间:${obj.kjsj }</li>
          <li>开奖号码:${obj.kl8Kjjg }</li>
        </ul>
      </div>
      <!-- /item -->
    </c:forEach>
  </div>
</div>
<!-- /content -->

<%@ include file="../../inc/lott-results-foot.jsp" %>
<%@ include file="../../index/foot.jsp" %>
<!-- /footer -->

<div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
<%@ include file="../../inc/left_memu.jsp" %>
</div>
<!-- /sidemenu -->

</div>
<!-- /page -->

</body>

</html>
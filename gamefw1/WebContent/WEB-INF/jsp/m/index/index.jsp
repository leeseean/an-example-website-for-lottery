<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file="common.jsp" %>
  <script src="${resourceRoot}/m/js/banner.js"></script>
</head>

<body>
<div id="loader">
  <div class="loading">
    <img src="${resourceRoot}/m/img/loading.png">
    <p class="loading-text">稍等片刻...</p>
    <p class="version">版本 2.0</p>
  </div>
</div>
<div data-role="page" data-theme="x" class="ybb-mobile-web">
  <!-- header -->
  <%@ include file="head.jsp" %>
  <!-- /header -->
  <div role="main" class="ui-content">
    <!-- banner -->
    <%@ include file="banner.jsp" %>
    <!-- /banner -->
    <!-- news -->
    <%@ include file="msg.jsp" %>
    <!-- /news -->

<div class="jackpot-mg">
  <div class="title">超级彩池 Jackpots</div>
  <script src="http://www.tickerassist.co.uk/ProgressiveTickers/include/js/ProgressiveTickersControl.js?currency=USD&fontfamily=verdana&font-size=18"></script>
  <style>
    .JackpotTotalLink { display: none; }
  </style>
</div>
<!-- /JackpotMG -->
<nav class="game-nav">
  <ul class="cf">
    <li class="item-a i1">
      <a href="${ctx}/m/main?code=live" data-ajax="false"><i>真人视讯</i></a>
      <h6>无需下载</h6>
    </li>
    <li class="item-b i2">
      <a href="${ctx}/m/main?code=slot_pt" data-ajax="false"><i>电子游艺</i></a>
      <h6>无需下载</h6>
    </li>
    <li class="item-a i3">
      <a href="${ctx}/m/main?code=sport" data-ajax="false"><i>体育赛事</i></a>
      <h6>无需下载</h6>
    </li>
    <li class="item-b i4">
          <a href="${ctx}/m/wap/" data-ajax="false"><i>彩票游戏</i></a>
      <h6>无需下载</h6> 
    </li>
  </ul>
</nav>
<!-- /GameNav -->
  </div>
  <!-- /main -->
  <!-- footer -->
  <%@ include file="foot.jsp" %>
  <!-- /footer -->

<div data-role="panel" data-display="overlay" id="quickMenu" class="ybb-mobile-sidemenu">
<%@ include file="../inc/web_sidemenu.jsp" %>
</div>
<!-- /sidemenu -->

</div>

</body>
</html>
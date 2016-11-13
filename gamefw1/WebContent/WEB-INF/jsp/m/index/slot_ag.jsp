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

<div data-role="page" data-theme="x" class="ybb-mobile-web">
  <!-- header -->
  <%@ include file="head.jsp" %>
  <!-- /header -->
  <div role="main" class="ui-content">
    <!-- banner -->
    <%@ include file="banner.jsp" %>
    <!-- /banner -->
    <!-- news -->
    <%--<%@ include file="msg.jsp" %> --%>
    <!-- /news -->
    <div class="ybb-slot-main">
      <div data-role="navbar" class="slot-hall">
        <ul>
          <li><a href="${ctx }/m/main?code=slot_ag" data-ajax="false" class="ui-btn-active">AG电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_mg" data-ajax="false">MG电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_pt" data-ajax="false">PT电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_os" data-ajax="false">OS电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_ttg" data-ajax="false" >TTG电子</a></li>
        </ul>
      </div>
<div class="ybb-main ybb-live-main">
  <div class="r1 mt20 spr-common"></div>
  <div class="r2">
    <p>AG平台是目前最具創意及創新的合法博彩娛樂平台供應商。從2012年開始，AG的團隊憑著大膽革新的思維，使AG平台能迅速在市場上佔一席位。Asia Gaming全球首創VIP包桌及咪牌百家樂，讓網上娛樂平台衝破傳統的束縛，帶來一個革命性的新體驗。AG平台同時符合IOM及First Cagayan認證,並獲得TST公平認證。AG的宗旨是秉持一個『公平、公正、公開』的真人荷官博彩平台，讓玩家感覺更逼真更震撼，仿如置身現場。</p>
    <p>產品特色种类：百家乐（旗舰厅、实地厅、VIP包桌）VIP包桌：玩家擁有獨立私人空間，且權限更多，例如：要求更換荷官咪牌百家樂：桌主自行咪牌，感覺更實在，耍樂更興奮。</p>
    <p>真人游戏种类：</p>
    <p>百家乐 / 免水百家乐 / 龙虎斗 / 轮盘 / 骰宝</p>
  </div>
  <div class="r3">
    <c:choose>
      <c:when test="${empty webUser}">
        <a href="javascript:alert('请先登录');void(0);" class="ui-btn ui-btn-f" data-ajax="false">进入AG电子游艺</a>
        <a href="javascript:alert('请先登录');void(0);" class="ui-btn ui-btn-f" data-ajax="false">进入AG真人</a>
      </c:when>
      <c:otherwise>
        <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/game/forwardGame?agGameType=2&gameType=ag','ag');" class="ui-btn ui-btn-f" data-ajax="false">进入AG电子游艺</a>
        <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/game/forwardGame?gameType=sa','sa');" class="ui-btn ui-btn-f" data-ajax="false">进入SA真人</a>
      </c:otherwise>
    </c:choose>
  </div>
</div>
    </div>
  </div>
  <!-- /main -->
  <!-- footer -->
  <%@ include file="foot.jsp" %>
  <!-- /footer -->
  <div data-role="panel" data-display="overlay" id="quickMenu" class="ybb-mobile-sidemenu">
	<%@ include file="../inc/web_sidemenu.jsp" %>
	</div>
</div>

</body>
</html>
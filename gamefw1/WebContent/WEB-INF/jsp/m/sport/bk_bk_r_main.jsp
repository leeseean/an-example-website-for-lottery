<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>今日赛事单式 - 篮球</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/mobile_common.jsp" %>
<style>
.ui-collapsible-set .ui-collapsible-set {
  margin: -10px 0;
}
</style>
</head>

<body>

<div data-role="page" data-theme="x" class="ybb-mobile ybb-mobile-web">

<%@ include file="../index/head.jsp" %>
<!-- /header -->

<div data-role="content" class="lott ybm-sport sport-match">
  <div class="type">
  <div data-role="collapsible-set" data-corners="false" data-inset="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="x" data-iconpos="right">
    <!-- 聯賽 -->
    <c:choose>
        <c:when test="${!empty leagueList}">
        <c:forEach var="league" items="${leagueList }">
	        <div data-role="collapsible">
	          <h3>${league }</h3>
              <div data-role="collapsible-set" data-inset="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="c" data-iconpos="right">
	          <c:forEach var="event" items="${eventList }">
	          	<c:if test="${league eq event.league }">
		            <!-- 單式 -->
		            <div data-role="collapsible">
		              <h4>${event.teamH } VS ${event.teamC }</h4>
		              <ul data-role="listview" class="match-info sport-bb-danshi">
		                <li data-role="list-divider" data-theme="e">${event.matchTime }</li>
		                <li data-role="list-divider" data-theme="c" class="title">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">全场</div>
		                    <div class="ui-block-b">独赢</div>
		                    <div class="ui-block-c">让分</div>
		                    <div class="ui-block-d">大小</div>
		                    <div class="ui-block-e">球队得分：大/小</div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">主</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=dy&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorMh}</a></div>
		                    <div class="ui-block-c">
		                    	<c:if test="${!empty event.iorRh}">
		                    		<c:if test="${event.strong eq 'H' }"><span>${event.ratio }</span> @</c:if>
		                    		<a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=rf&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorRh}</a>
		                    		</c:if>
		                    	</div>
		                    <div class="ui-block-d">大${event.ratioU } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=dx&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorOuc}</a></div>
		                    <div class="ui-block-e">
		                      <div class="ui-grid-a">
		                        <div class="ui-block-a"><c:if test="${!empty event.ratioOuho }">大${event.ratioOuho } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=jf&selection=H&period=f&dtype=dx_big" data-rel="dialog" data-transition="pop">${event.iorOuho }</a></c:if></div>
		                        <div class="ui-block-b"><c:if test="${!empty event.ratioOuhu }">小${event.ratioOuhu } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=jf&selection=H&period=f&dtype=dx_small" data-rel="dialog" data-transition="pop">${event.iorOuhu }</a></c:if></div>
		                      </div>
		                    </div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">客</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=dy&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorMc}</a></div>
		                    <div class="ui-block-c"><c:if test="${event.strong eq 'C' }"><span>${event.ratio }</span> @</c:if> <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=rf&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorRc}</a></div>
		                    <div class="ui-block-d">小${event.ratioO } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=dx&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorOuh}</a></div>
		                    <div class="ui-block-e">
		                      <div class="ui-grid-a">
		                        <div class="ui-block-a"><c:if test="${!empty event.ratioOuco }">大${event.ratioOuco } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=jf&selection=C&period=f&dtype=dx_big" data-rel="dialog" data-transition="pop">${event.iorOuco }</a></c:if></div>
		                        <div class="ui-block-b"><c:if test="${!empty event.ratioOucu }">小${event.ratioOucu } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=bk&rType=bk_r_main&btype=jf&selection=C&period=f&dtype=dx_small" data-rel="dialog" data-transition="pop">${event.iorOucu }</a></c:if></div>
		                      </div>
		                    </div>
		                  </div>
		                </li>
		              </ul>
		            </div>
		            <!-- /單式 -->
		        </c:if>
	          </c:forEach>
              </div>
	        </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
    	您选择的项目暂时没有赛事！
      </c:otherwise>
   </c:choose>
    <!-- /聯賽 -->
  </div>
  </div>
</div>
<!-- /content -->

<%@ include file="../inc/reload.jsp" %>
<%@ include file="../index/foot.jsp" %>
<!-- /footer -->

<div data-role="panel" data-display="overlay" id="quickMenu" class="ybb-mobile-sidemenu">
<%@ include file="../inc/web_sidemenu.jsp" %>
</div>
<!-- /sidemenu -->

</div>
<!-- /page -->

</body>

</html>
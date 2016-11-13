<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>半场/全场 - 体育赛事</title>
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
            <h3>${league}</h3>
            <div data-role="collapsible-set" data-inset="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-iconpos="right" data-theme="c">
              <c:forEach var="event" items="${eventList }">
                <c:if test="${league eq event.league }">
                  <!-- 半全場 -->
                  <div data-role="collapsible">
                    <h4>${event.teamH }VS ${event.teamC }</h4>
                    <ul data-role="listview" class="match-info sport-zongruqiu">
                      <li data-role="list-divider" data-theme="e">${event.matchTime }</li>
                      <li data-role="list-divider" data-theme="c" class="title">
                        <div class="ui-grid-b row">
                          <div class="ui-block-a">主 / 主</div>
                          <div class="ui-block-b">主 / 和</div>
                          <div class="ui-block-c">主 / 客</div>
                        </div>
                      </li>
                      <!-- /title -->
                      <li data-theme="c">
                        <div class="ui-grid-b row">
                          <div class="ui-block-a"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=H_H" data-rel="dialog" data-transition="pop">${event.iorFhh}</a></div>
                          <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=H_N" data-rel="dialog" data-transition="pop">${event.iorFhn}</a></div>
                          <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=H_C" data-rel="dialog" data-transition="pop">${event.iorFhc}</a></div>
                        </div>
                      </li>
                      <!-- /row -->
                      <li data-role="list-divider" data-theme="c" class="title">
                        <div class="ui-grid-b row">
                          <div class="ui-block-a">和 / 主</div>
                          <div class="ui-block-b">和 / 和</div>
                          <div class="ui-block-c">和 / 客</div>
                        </div>
                      </li>
                      <!-- /title -->
                      <li data-theme="c">
                        <div class="ui-grid-b row">
                          <div class="ui-block-a"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=N_H" data-rel="dialog" data-transition="pop">${event.iorFnh}</a></div>
                          <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=N_N" data-rel="dialog" data-transition="pop">${event.iorFnn}</a></div>
                          <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=N_C" data-rel="dialog" data-transition="pop">${event.iorFnc}</a></div>
                        </div>
                      </li>
                      <!-- /row -->
                      <li data-role="list-divider" data-theme="c" class="title">
                        <div class="ui-grid-b row">
                          <div class="ui-block-a">客 / 主</div>
                          <div class="ui-block-b">客 / 和</div>
                          <div class="ui-block-c">客 / 客</div>
                        </div>
                      </li>
                      <!-- /title -->
                      <li data-theme="c">
                        <div class="ui-grid-b row">
                          <div class="ui-block-a"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=C_H" data-rel="dialog" data-transition="pop">${event.iorFch}</a></div>
                          <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=C_N" data-rel="dialog" data-transition="pop">${event.iorFcn}</a></div>
                          <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=f&selection=H&period=f&btype=C_C" data-rel="dialog" data-transition="pop">${event.iorFcc}</a></div>
                        </div>
                      </li>
                      <!-- /row -->
                    </ul>
                  </div>
                  <!-- /半全場 -->
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
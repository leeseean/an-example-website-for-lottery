<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>总入球 - 体育赛事</title>
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
                <div data-role="collapsible-set" data-inset="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-iconpos="right" data-theme="c">
	          	<c:forEach var="event" items="${eventList }">
	          		<c:if test="${league eq event.league }">
			            <!-- 總入球 -->
			            <div data-role="collapsible">
			              <h4>${event.teamH } [主] VS ${event.teamC }</h4>
			              <ul data-role="listview" class="match-info sport-zongruqiu">
			                <li data-role="list-divider" data-theme="e">${event.matchTime }</li>
			                <li data-role="list-divider" data-theme="c" class="title">
			                  <div class="ui-grid-c row">
			                    <div class="ui-block-a">0 - 1</div>
			                    <div class="ui-block-b">2 - 3</div>
			                    <div class="ui-block-c">4 - 6</div>
			                    <div class="ui-block-d">7或以上</div>
			                  </div>
			                </li>
			                <li data-theme="c">
			                  <div class="ui-grid-c row">
			                    <div class="ui-block-a"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=t&selection=N&period=f&btype=0_1" data-rel="dialog" data-transition="pop">${event.iorT01}</a></div>
			                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=t&selection=N&period=f&btype=2_3" data-rel="dialog" data-transition="pop">${event.iorT23}</a></div>
			                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=t&selection=N&period=f&btype=4_6" data-rel="dialog" data-transition="pop">${event.iorT46}</a></div>
			                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=t&selection=N&period=f&btype=7up" data-rel="dialog" data-transition="pop">${event.iorOver}</a></div>
			                  </div>
			                </li>
			              </ul>
			            </div>
			            <!-- /總入球 -->
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
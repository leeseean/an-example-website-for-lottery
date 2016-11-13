<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>滚球单式 - 体育赛事</title>
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
  <div data-role="collapsible-set" data-corners="false" data-inset="false" data-iconpos="right" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="x">
    <!-- 聯賽 -->
    <c:choose>
        <c:when test="${!empty leagueList}">
        <c:forEach var="league" items="${leagueList }">
	        <div data-role="collapsible">
	          <h3>${league }</h3>
		          <div data-role="collapsible-set" data-inset="false" data-iconpos="right" data-collapsed-icon="arrow-r" data-theme="c" data-expanded-icon="arrow-d">
	          <c:forEach var="event" items="${eventList }">
	          	<c:if test="${league eq event.league }">
		            <!-- 單式 -->
		            <div data-role="collapsible">
		              <h4>${event.teamH }[主] VS ${event.teamC }</h4>
		              <ul data-role="listview" class="match-info sport-danshi">
		                <li data-role="list-divider" data-theme="e">${event.matchTime }</li>
		                <li data-role="list-divider" data-theme="c" class="title">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">全场</div>
		                    <div class="ui-block-b">独赢</div>
		                    <div class="ui-block-c">让球</div>
		                    <div class="ui-block-d">大小</div>
		                    <div class="ui-block-e">单双</div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">主</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dy&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorMh }</a></div>
		                    <div class="ui-block-c"><c:if test="${event.strong eq 'H'}">${event.ratio }</c:if>&nbsp;<a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=rq&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorRh }</a></div>
		                    <div class="ui-block-d"><c:if test="${!empty event.iorOuc }">大${event.ratioU } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dx&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorOuc }</a></c:if></div>
		                    <div class="ui-block-e">${event.strOdd } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=ds&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorEoo }</a></div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">客</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dy&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorMc }</a></div>
		                    <div class="ui-block-c"><c:if test="${event.strong eq 'C'}">${event.ratio }</c:if>&nbsp;<a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=rq&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorRc }</a></div>
		                    <div class="ui-block-d"><c:if test="${!empty event.iorOuh }">小${event.ratioO } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dx&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorOuh }</a></c:if></div>
		                    <div class="ui-block-e">${event.strEven } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=ds&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorEoe }</a></div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">和</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dy&selection=N&period=f" data-rel="dialog" data-transition="pop">${event.iorMn }</a></div>
		                    <div class="ui-block-c"></div>
		                    <div class="ui-block-d"></div>
		                    <div class="ui-block-e"></div>
		                  </div>
		                </li>
		                <li data-role="list-divider" data-theme="c" class="title">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a">半场</div>
		                    <div class="ui-block-b">独赢</div>
		                    <div class="ui-block-c">让球</div>
		                    <div class="ui-block-d">大小</div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a">主</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dy&selection=H&period=h" data-rel="dialog" data-transition="pop">${event.iorHmh }</a></div>
		                    <div class="ui-block-c"><c:if test="${event.hstrong eq 'H'}">${event.hratio }</c:if>&nbsp; <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=rq&selection=H&period=h" data-rel="dialog" data-transition="pop">${event.iorHrh }</a></div>
		                    <div class="ui-block-d"><c:if test="${!empty event.iorHouc}">大${event.hratioU } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dx&selection=H&period=h" data-rel="dialog" data-transition="pop">${event.iorHouc }</a></c:if></div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a">客</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dy&selection=C&period=h" data-rel="dialog" data-transition="pop">${event.iorHmc }</a></div>
		                    <div class="ui-block-c"><c:if test="${event.hstrong eq 'C'}">${event.hratio }</c:if>&nbsp; <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=rq&selection=C&period=h" data-rel="dialog" data-transition="pop">${event.iorHrc }</a></div>
		                    <div class="ui-block-d"><c:if test="${!empty event.iorHouh}">小${event.hratioO } <a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dx&selection=C&period=h" data-rel="dialog" data-transition="pop">${event.iorHouh }</a></c:if></div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a">和</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=re&btype=dy&selection=N&period=h" data-rel="dialog" data-transition="pop">${event.iorHmn }</a></div>
		                    <div class="ui-block-c"></div>
		                    <div class="ui-block-d"></div>
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

<%@ include file="../inc/roll_reload.jsp" %>
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
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>综合过关 - 体育赛事</title>
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
		                    <div class="ui-block-b">
		                    	<a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dy&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorMh }</a>
		                    	</div>
		                    <div class="ui-block-c">
		                    	<c:if test="${event.strong eq 'H'}">${event.ratio }</c:if>&nbsp;
		                    	<a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=rq&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorPrh }</a>
		                    </div>
		                    <div class="ui-block-d">
		                    	<c:if test="${!empty event.iorPouc }">
		                    		大${event.ratioU } <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dx&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorPouc }</a>
		                    	</c:if>
		                    </div>
		                    <div class="ui-block-e">
		                    	 <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=ds&selection=H&period=f" data-rel="dialog" data-transition="pop">${event.iorPeoo }</a>
		                    </div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">客</div>
		                    <div class="ui-block-b">
		                    	<a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dy&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorMc }</a>
		                    </div>
		                    <div class="ui-block-c">
		                    	<c:if test="${event.strong eq 'C'}">${event.ratio }</c:if>&nbsp;
		                    	<a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=rq&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorPrc }</a>
		                    </div>
		                    <div class="ui-block-d">
		                    	<c:if test="${!empty event.iorPouh }">
		                    		小${event.ratioO } <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dx&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorPouh }</a>
		                    	</c:if>
		                    </div>
		                    <div class="ui-block-e">
		                    	 <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=ds&selection=C&period=f" data-rel="dialog" data-transition="pop">${event.iorPeoe }</a>
		                    </div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-d row">
		                    <div class="ui-block-a">和</div>
		                    <div class="ui-block-b">
		                    	<a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dy&selection=N&period=f" data-rel="dialog" data-transition="pop">${event.iorMn }</a>
		                    </div>
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
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dy&selection=H&period=h" data-rel="dialog" data-transition="pop">${event.iorHmh }</a></div>
		                    <div class="ui-block-c"><c:if test="${event.hstrong eq 'H'}">${event.hratio }</c:if>&nbsp; <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=rq&selection=H&period=h" data-rel="dialog" data-transition="pop">${event.iorHprh }</a></div>
		                    <div class="ui-block-d">大${event.hratioU } <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dx&selection=H&period=h" data-rel="dialog" data-transition="pop">${event.iorHpouc }</a></div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a">客</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dy&selection=C&period=h" data-rel="dialog" data-transition="pop">${event.iorHmc }</a></div>
		                    <div class="ui-block-c"><c:if test="${event.hstrong eq 'C'}">${event.hratio }</c:if>&nbsp; <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=rq&selection=C&period=h" data-rel="dialog" data-transition="pop">${event.iorHprc }</a></div>
		                    <div class="ui-block-d">小${event.hratioO } <a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dx&selection=C&period=h" data-rel="dialog" data-transition="pop">${event.iorHpouh }</a></div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a">和</div>
		                    <div class="ui-block-b"><a href="${ctx}/m/sport/goP3Order?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=p3&btype=dy&selection=N&period=h" data-rel="dialog" data-transition="pop">${event.iorHmn }</a></div>
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
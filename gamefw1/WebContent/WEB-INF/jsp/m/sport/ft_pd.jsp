<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>波胆 - 体育赛事</title>
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
		            <!-- 波胆 -->
		            <div data-role="collapsible">
		              <h4>${event.teamH } [主] VS ${event.teamC }</h4>
		              <ul data-role="listview" class="match-info sport-bodan">
		                <li data-role="list-divider" data-theme="e">${event.matchTime }</li>
		                <li data-role="list-divider" data-theme="c" class="title">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a">全场</div>
		                    <div class="ui-block-b">比分</div>
		                    <div class="ui-block-c">${event.teamH }</div>
		                    <div class="ui-block-d">${event.teamC }</div>
		                  </div>
		                </li>
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">1:0</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=1_0" data-rel="dialog" data-transition="pop">${event.iorH1c0 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=0_1" data-rel="dialog" data-transition="pop">${event.iorH0c1 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">2:0</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=2_0" data-rel="dialog" data-transition="pop">${event.iorH2c0 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=0_2" data-rel="dialog" data-transition="pop">${event.iorH0c2 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">2:1</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=2_1" data-rel="dialog" data-transition="pop">${event.iorH2c1 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=1_2" data-rel="dialog" data-transition="pop">${event.iorH1c2 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">3:0</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=3_0" data-rel="dialog" data-transition="pop">${event.iorH3c0 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=0_3" data-rel="dialog" data-transition="pop">${event.iorH0c3 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">3:1</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=3_1" data-rel="dialog" data-transition="pop">${event.iorH3c1 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=1_3" data-rel="dialog" data-transition="pop">${event.iorH1c3 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">3:2</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=3_2" data-rel="dialog" data-transition="pop">${event.iorH3c2 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=2_3" data-rel="dialog" data-transition="pop">${event.iorH2c3 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">4:0</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=4_0" data-rel="dialog" data-transition="pop">${event.iorH4c0 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=0_4" data-rel="dialog" data-transition="pop">${event.iorH0c4 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">4:1</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=4_1" data-rel="dialog" data-transition="pop">${event.iorH4c1 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=1_4" data-rel="dialog" data-transition="pop">${event.iorH1c4 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">4:2</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=4_2" data-rel="dialog" data-transition="pop">${event.iorH4c2 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=2_4" data-rel="dialog" data-transition="pop">${event.iorH2c4 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">4:3</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=4_3" data-rel="dialog" data-transition="pop">${event.iorH4c3 }</a></div>
		                    <div class="ui-block-d"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=C&period=f&btype=3_4" data-rel="dialog" data-transition="pop">${event.iorH3c4 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c" class="merge">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">0:0</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=0_0" data-rel="dialog" data-transition="pop">${event.iorH0c0 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c" class="merge">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">1:1</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=1_1" data-rel="dialog" data-transition="pop">${event.iorH1c1 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c" class="merge">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">2:2</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=2_2" data-rel="dialog" data-transition="pop">${event.iorH2c2 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c" class="merge">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">3:3</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=3_3" data-rel="dialog" data-transition="pop">${event.iorH3c3 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c" class="merge">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">4:4</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=4_4" data-rel="dialog" data-transition="pop">${event.iorH4c4 }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		                <li data-theme="c" class="merge">
		                  <div class="ui-grid-c row">
		                    <div class="ui-block-a"></div>
		                    <div class="ui-block-b">其他</div>
		                    <div class="ui-block-c"><a href="${ctx}/m/sport/goOrder?gid=${event.gid }&timeType=${timeType }&matchType=ft&rType=pd&selection=H&period=f&btype=other" data-rel="dialog" data-transition="pop">${event.iorOvh }</a></div>
		                  </div>
		                </li>
		                <!-- /row -->
		              </ul>

		            </div>
		            <!-- /波胆 -->
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
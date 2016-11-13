<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>类别 - 体育赛事</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="../inc/mobile_common.jsp" %>
</head>

<body>

<div data-role="page" data-theme="x" class="ybb-mobile ybb-mobile-web">

<%@ include file="../index/head.jsp" %>
<!-- /header -->

<div data-role="content" class="lott">
  <div class="type">
  <div data-role="collapsible-set" data-inset="false" data-corners="false" data-iconpos="right" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
    <!-- 足球 -->
    <div data-role="collapsible" data-collapsed="false">
      <h3>足球</h3>
      <ul data-role="listview">
        <!-- 滾球 -->
        <li data-role="list-divider" data-theme="c">滚球</li>
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=roll&rType=re&tableName=t_r_match_re','huangguan');" data-role="button" data-ajax="false" data-theme="b">单式</a>
          </div>
        </li>
        <!-- /滾球 -->
        <!-- 今日賽事 -->
        <li data-role="list-divider" data-theme="c">今日赛事</li>
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=today&rType=r&tableName=t_ft_match_r','huangguan');"  data-role="button" data-ajax="false" data-theme="b">单式</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=today&rType=hpd&tableName=t_ft_match_pd','huangguan');"  data-role="button" data-ajax="false" data-theme="b">半场波胆</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=today&rType=pd&tableName=t_ft_match_pd','huangguan');"  data-role="button" data-ajax="false" data-theme="b">波胆</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=today&rType=t&tableName=t_ft_match_t','huangguan');"  data-role="button" data-ajax="false" data-theme="b">总入球</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=today&rType=f&tableName=t_ft_match_f','huangguan');"  data-role="button" data-ajax="false" data-theme="b">半场/全场</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=today&rType=p3&tableName=t_ft_match_p3','huangguan');"  data-role="button" data-ajax="false" data-theme="b">综合过关</a>
          </div>
        </li>
        <!-- /今日賽事 -->
        <!-- 早盘 -->
        <li data-role="list-divider" data-theme="c">早盘</li>
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=tom&rType=r&tableName=t_ft_match_r','huangguan');"  data-role="button" data-ajax="false" data-theme="b">单式</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=tom&rType=hpd&tableName=t_ft_match_pd','huangguan');"  data-role="button" data-ajax="false" data-theme="b">半场波胆</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=tom&rType=pd&tableName=t_ft_match_pd','huangguan');"  data-role="button" data-ajax="false" data-theme="b">波胆</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=tom&rType=t&tableName=t_ft_match_t','huangguan');"  data-role="button" data-ajax="false" data-theme="b">总入球</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=tom&rType=f&tableName=t_ft_match_f','huangguan');"  data-role="button" data-ajax="false" data-theme="b">半场/全场</a>
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=ft&timeType=tom&rType=p3&tableName=t_ft_match_p3','huangguan');"  data-role="button" data-ajax="false" data-theme="b">综合过关</a>
          </div>
        </li>
        <!-- /早盘 -->
      </ul>
    </div>
    <!-- /足球 -->
    <!-- 籃球 -->
    <div data-role="collapsible" data-theme="x">
      <h3>籃球</h3>
      <ul data-role="listview">
        <!-- 籃球滾球 -->
        <li data-role="list-divider" data-theme="c">滚球</li>
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=bk&timeType=roll&rType=re_main&tableName=t_r_match_remain','huangguan');" data-role="button" data-ajax="false" data-theme="b">单式</a>
          </div>
        </li>
        <!-- /籃球滾球 -->
        <!-- 籃球今日賽事 -->
        <li data-role="list-divider" data-theme="c">今日赛事</li>
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=bk&timeType=today&rType=bk_r_main&tableName=t_bk_match_rmain','huangguan');" data-role="button" data-ajax="false" data-theme="b">单式</a>
          </div>
        </li>
        <!-- /籃球今日賽事 -->
        <!-- 籃球早盘 -->
        <li data-role="list-divider" data-theme="c">早盘</li>
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="javascript:void(0);" onclick="checkFlatSatus('${ctx}/m/sport/goEventDetail?matchType=bk&timeType=tom&rType=bk_r_main&tableName=t_bk_match_rmain','huangguan');" data-role="button" data-ajax="false" data-theme="b">单式</a>
          </div>
        </li>
        <!-- /籃球早盘 -->
      </ul>
    </div>
    <!-- /籃球 -->
    <!-- 網球 -->
    <div data-role="collapsible" data-theme="x">
      <h3>網球</h3>
      <ul data-role="listview">
        <!-- 无赛事 -->
        <li data-role="list-divider" data-theme="c">暂无赛事</li>
        <!-- /无赛事 -->
      </ul>
    </div>
    <!-- /網球 -->
    <!-- 排球 -->
    <div data-role="collapsible" data-theme="x">
      <h3>排球</h3>
      <ul data-role="listview">
        <!-- 无赛事 -->
        <li data-role="list-divider" data-theme="c">暂无赛事</li>
        <!-- /无赛事 -->
      </ul>
    </div>
    <!-- /排球 -->
    <!-- 羽毛球 -->
    <div data-role="collapsible" data-theme="x">
      <h3>羽毛球</h3>
      <ul data-role="listview">
        <!-- 无赛事 -->
        <li data-role="list-divider" data-theme="c">暂无赛事</li>
        <!-- /无赛事 -->
      </ul>
    </div>
    <!-- /羽毛球 -->
    <!-- 乒乓球 -->
    <div data-role="collapsible" data-theme="x">
      <h3>乒乓球</h3>
      <ul data-role="listview">
        <!-- 无赛事 -->
        <li data-role="list-divider" data-theme="c">暂无赛事</li>
        <!-- /无赛事 -->
      </ul>
    </div>
    <!-- /乒乓球 -->
    <!-- 棒球-->
    <div data-role="collapsible" data-theme="x">
      <h3>棒球</h3>
      <ul data-role="listview">
        <!-- 无赛事 -->
        <li data-role="list-divider" data-theme="c">暂无赛事</li>
        <!-- /无赛事 -->
      </ul>
    </div>
    <!-- /棒球 -->
  </div>
  </div>
  <!-- /type -->
</div>
<!-- /content -->

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
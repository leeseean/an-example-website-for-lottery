<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>类别 - 彩票游戏</title>
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
    <div data-role="collapsible" data-collapsed="false">
      <h3>香港六合彩</h3>
      <ul data-role="listview">
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=TM" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">特码</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=ZMT" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">正码特</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=ZM" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">正码</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=ZM1T6" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">正码1-6</a>
        
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=BB" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">半波</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=YXYZTW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">一肖&正特尾</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=TXTW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">特肖头尾</a>
            <%-- <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=ZYBZ" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">中&不中</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=LX" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">连肖</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=HK6&cpTypeCode=HX" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">合肖</a> --%>
          </div>
        </li>
      </ul>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=hk6" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <!-- /category -->
    <div data-role="collapsible">
      <h3>福彩3D</h3>
      <jsp:include page="fc3dpl3/fc3d_list.jsp"></jsp:include>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=fc3d" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>排列三</h3>
      <jsp:include page="fc3dpl3/pl3_list.jsp"></jsp:include>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=pl3" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>重庆时时彩</h3>
      <jsp:include page="ssc/cqssc_list.jsp"></jsp:include>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=cqssc" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <%--
    <div data-role="collapsible">
      <h3>江西时时彩</h3>
      <jsp:include page="ssc/jxssc_list.jsp"></jsp:include>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=jxssc" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div> --%>
    <div data-role="collapsible">
      <h3>天津时时彩</h3>
      <jsp:include page="ssc/tjssc_list.jsp"></jsp:include>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=tjssc" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>新疆时时彩</h3>
      <jsp:include page="ssc/xjssc_list.jsp"></jsp:include>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=xjssc" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>广东快乐十分</h3>
      <ul data-role="listview">
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D1Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第一球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D2Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第二球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D3Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第三球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D4Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第四球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D5Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第五球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D6Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第六球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D7Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第七球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=D8Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第八球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=GDKLSF&cpTypeCode=ZHLH" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">总和龙虎</a>
          </div>
        </li>
      </ul>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=gdklsf" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>天津快乐十分</h3>
      <ul data-role="listview">
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D1Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第一球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D2Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第二球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D3Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第三球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D4Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第四球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D5Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第五球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D6Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第六球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D7Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第七球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=D8Q" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第八球</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJKLSF&cpTypeCode=ZHLH" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">总和龙虎</a>
          </div>
        </li>
      </ul>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=tjklsf" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>北京PK拾</h3>
      <ul data-role="listview">
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=SM" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">双面</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=GJ" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">冠军</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=YJ" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">亚军</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=JJ" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">季军</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=D4M" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第4名</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=D5M" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第5名</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=D6M" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第6名</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=D7M" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第7名</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=D8M" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第8名</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=D9M" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第9名</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=D10M" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">第十名</a>
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJPK10&cpTypeCode=GYJH" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">冠军亚和</a>
          </div>
        </li>
      </ul>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=bjpk10" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>幸运28</h3>
      <ul data-role="listview">
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=BJKL8&cpTypeCode=TM" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">特码</a>
          </div>
        </li>
      </ul>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=BJKL8" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <div data-role="collapsible">
      <h3>加拿大28</h3>
      <ul data-role="listview">
        <li>
          <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
            <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=CAKENO&cpTypeCode=TM" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">特码</a>
          </div>
        </li>
      </ul>
      <a href="${ctx}/m/mobileCpAccount/findKjjgList?gameCode=CAKENO" data-ajax="false" data-rel="dialog" data-transition="pop" data-role="button" data-corners="false" class="ui-mini" data-theme="b" style="margin-top: 20px;">开奖结果</a>
    </div>
    <!-- /category -->
  </div>
  </div>
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
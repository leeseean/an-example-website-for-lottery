<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<div data-role="collapsible-set" data-corners="false" data-inset="false" data-iconpos="right" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="c" class="ui-mini">
  <div data-role="collapsible">
    <h4>主盘式</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=ZPS" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">主盘式</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>一字</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=YZ" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">一字组合</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>二字</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
<%--      <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=EZZH&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">二字组合-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=EZZH&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">二字组合-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=EZZH&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">二字组合-后三位</a> --%>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BSDW&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾定位-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BSDW&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾定位-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BSDW&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾定位-后三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BGDW&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个定位-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BGDW&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个定位-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BGDW&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个定位-后三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=SGDW&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个定位-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=SGDW&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个定位-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=SGDW&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个定位-后三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BSHS&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾和数-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BSHS&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾和数-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BSHS&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾和数-后三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BGHS&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个和数-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BGHS&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个和数-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=BGHS&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个和数-后三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=SGHS&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个和数-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=SGHS&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个和数-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=EZ&cpCateCode=SGHS&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个和数-后三位</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>三字</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
<%--      <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=SZZH&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">三字组合-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=SZZH&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">三字组合-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=SZZH&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">三字组合-后三位</a> --%>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=BSGHS&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个和数-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=BSGHS&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个和数-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=BSGHS&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个和数-后三位</a>
          <a href="${ctx}/m/mobileCpOrder/szdwOrderPage?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=BSGDW&xzlxCode=QSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个定位-前三位</a>
          <a href="${ctx}/m/mobileCpOrder/szdwOrderPage?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=BSGDW&xzlxCode=ZSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个定位-中三位</a>
          <a href="${ctx}/m/mobileCpOrder/szdwOrderPage?gameTypeCode=TJSSC&cpTypeCode=SZ&cpCateCode=BSGDW&xzlxCode=HSW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个定位-后三位</a>
        </div>
      </li>
    </ul>
  </div>
  <%--
  <div data-role="collapsible">
    <h4>一字过关</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=YZGG" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">主盘式</a>
        </div>
      </li>
    </ul>
  </div>--%>
  <div data-role="collapsible">
    <h4>跨度</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=KD" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">主盘式</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>其它</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=TJSSC&cpTypeCode=QT" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">主盘式</a>
        </div>
      </li>
    </ul>
  </div>
</div>
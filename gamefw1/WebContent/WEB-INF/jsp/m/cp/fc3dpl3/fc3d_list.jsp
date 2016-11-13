<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<div data-role="collapsible-set" data-corners="false" data-inset="false" data-iconpos="right" data-collapsed-icon="arrow-r" data-theme="c" data-expanded-icon="arrow-d">
  <div data-role="collapsible">
    <h4>主盘式</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=ZPS" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">主盘式</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>一字</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=YZ" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">一字</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>二字</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
       <%--    <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=EZ&cpCateCode=EZZH" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">二字组合</a> --%>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=EZ&cpCateCode=BSDW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾定位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=EZ&cpCateCode=BGDW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个定位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=EZ&cpCateCode=SGDW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个定位</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=EZ&cpCateCode=BSHS" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾和数</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=EZ&cpCateCode=BGHS" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百个和数</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=EZ&cpCateCode=SGHS" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">拾个和数</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>三字</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=SZ&cpCateCode=SZZH" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">三字和数</a>
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=SZ&cpCateCode=BSGHS" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个和数</a>
          <a href="${ctx}/m/mobileCpOrder/szdwOrderPage?gameTypeCode=FC3D&cpTypeCode=SZ&cpCateCode=BSGDW" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">百拾个定位</a>
        </div>
      </li>
    </ul>
  </div>
  <%-- <div data-role="collapsible">
    <h4>一字过关</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=YZGG" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">一字过关</a>
        </div>
      </li>
    </ul>
  </div> --%>
  <div data-role="collapsible">
    <h4>跨度</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=KD" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">跨度</a>
        </div>
      </li>
    </ul>
  </div>
  <div data-role="collapsible">
    <h4>其它</h4>
    <ul data-role="listview">
      <li>
        <div data-role="controlgroup" data-type="horizontal" data-corners="false" data-mini="true">
          <a href="${ctx}/m/mobileCpOrder/orderIndex?gameTypeCode=FC3D&cpTypeCode=QT" data-role="button" data-rel="dialog" data-transition="pop" data-theme="b">其它</a>
        </div>
      </li>
    </ul>
  </div>
</div>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div class="ui-grid-a ybm-panel-header">
  <div class="ui-block-a"></div>
  <div class="ui-block-b" style=" text-align: right;">
    <div data-role="controlgroup" data-type="horizontal" data-mini="true">
      <a href="${ctx }/m/main" data-ajax="false" data-role="button" data-icon="home" data-iconpos="notext" data-theme="b" class="btn-home"></a>
      <a href="" data-rel="close" data-role="button" data-icon="delete" data-iconpos="notext" data-theme="b" class="btn-close"></a>
    </div>
  </div>
</div>
<ul data-role="listview" data-inset="false" data-corners="false" data-icon="false" class="ui-mini">
  <li data-role="list-divider">会员中心</li>
  <li>
    <a href="${ctx }/m/wap/member#/messages" data-ajax="false">短信息</a>
  </li>
  <li>
    <a href="${ctx }/m/wap/member#/user/info" data-ajax="false">个人资料</a>
  </li>
  <li>
    <a href="${ctx }/m/wap/member#/user/update/password" data-ajax="false">密码安全</a>
  </li>
  <c:if test="${!empty webUser}">
    <li>
      <a href="javascript:void(0);" onclick="mobile_loginOut();">注销</a>
    </li>
  </c:if>
  <!--
  <li data-role="list-divider">游戏</li>
<li>
  <a href="${ctx }/m/sport/list" data-ajax="false">体育赛事</a>
</li>
<li>
  <a href="${ctx}/m/mobileCpMain/menu" data-ajax="false">彩票游戏</a>
</li>
-->
<li data-role="list-divider">财务中心</li>
<li>
  <a href="${ctx}/m/wap/member#/home" data-ajax="false">充值</a>
</li>
<li>
  <a href="${ctx }/m/wap/member#/withdrawal/account" data-ajax="false">提款</a>
</li>
<li>
  <a href="${ctx }/m/wap/member#/conversion" data-ajax="false">额度转换</a>
</li>
</ul>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/inc.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title></title>
<%@include file="inc/common.jsp"%>
  <link rel="stylesheet" href="${resourceRoot}/web/ybb/assets/css/reset.css"/>
  <link rel="stylesheet" href="${resourceRoot}/web/ybb/assets/css/style.css"/>
  <script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
  <script>
    var root = "${resourceRoot}";
    var rootPath = "${ctx}";
  </script>
</head>
<body>
<div class="ybb-wrap yb-cock">
<%@ include file="inc/head.jsp" %>
<div class="ybb-wrap-bd">
<div class="ybb-pg-bd">
<div class="ybb-md-ban"></div>
<div class="ybb-pg-ma">
<div class="pagesmain">
  <!-- <div class="banner" style="background:url(${resourceRoot}/web/ybb/assets/img/pages/banner02.jpg) top center no-repeat; height:256px;"></div> -->
<%@ include file="inc/msg.jsp" %>
<style>
  .gonggaolist{ position: relative; top: -9px }
</style>
    <div style="height:10px;"></div>
    <!-- 主體 -->
    <div class="yb-ct-wp">
      <div class="yb-ct">
        <div class="cock-main">
          <div class="r1 spr-cock"></div>
          <div class="r2 ac cf">
          <c:choose>
            <c:when test="${!empty webUser}">
              <a href="javascript:void(0);" onclick="winOpen('${ctx}/forwardGame?gameType=douji',window.screen.width,window.screen.height,0,0,'game','douji');" class="btn-play block spr-cock"></a>
            </c:when>
            <c:otherwise>
              <a href="javascript:void(0);" onclick="alert('您尚未登录，请先登录再进行游戏');" class="btn-play block spr-cock"></a>
            </c:otherwise>
          </c:choose>
            <a href="${resourceRoot}/web/ybb/html/douji/cf-rule.html" target="_blank"
              class="btn-rule block spr-cock"></a>
          </div>
          <div class="r3">
            <h2 class="ac">游戏简介</h2>
            <p>斗鸡是菲律宾独特的遗产，一种体现了体育精神的传统文化。诚实遵守公平竞赛，尊敬彼此和大方的接受失败是这项运动的规范，菲律宾人都无一例外严格遵守。在追求这项运动中，爱好者被冠于崇高的敬意，而批评者将被排斥。这项运动符合了民主主义。社会的两个极端，从赤脚的农民，到崇高和威武的，以及两者之间的人们，都一视同仁。</p>
            <p>通过采用这种统一的赛事规则和服从其要求，我们希望最终能成功的启发公众，以及减少（如果不能完全消除）社会对Sabong的偏见和不屑。</p>
            <p>保护每个斗鸡爱好者的固有权利和热忱，让他们能享受这项运动，作为我国传统的一部分。</p>
          </div>
        </div>
      </div>
    </div>
    <!-- /主體 -->
  <div style="height:20px;" class="clear"></div>
  </div>
</div>
</div>
</div>
<jsp:include page="inc/foot_in.jsp" />
</html>
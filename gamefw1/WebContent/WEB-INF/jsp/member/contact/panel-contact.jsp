<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <%@include file="/inc.jsp"%>
    <!DOCTYPE html>
    <html lang="zh-CN">

    <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>panel contact</title>
      <link rel="stylesheet" href="${resourceRoot}/member/css/font.css">
      <link rel="stylesheet" href="${resourceRoot}/member/css/button.css">
      <link rel="stylesheet" href="${resourceRoot}/member/css/panel.css">
    </head>
    </head>

    <body class="panel-contact">
      <div class="content-group font-hei">
      <c:choose>
		<c:when test="${!empty ctxMap['msg007']}">
        <div class="content-item">
          <div class="page-title al clear">
            <div class="left">
              <h2 class="s24 gray-dark">在线客服</h2>
              <h3 class="mt5 gray">7*24小时在线为您服务</h3>
            </div>
            <div class="left" style=" margin-left: 150px;">
              <a href="${ctxMap['msg007']}" target="_blank" class="button button-glow button-rounded button-raised button-primary">立刻连线</a>
            </div>
          </div>
        </div>
         </c:when>
		</c:choose>
        <!-- /item -->
        <c:choose>
		<c:when test="${!empty ctxMap['siteQq']}">
        <div class="content-item">
          <div class="page-title al clear">
            <div class="left">
              <h2 class="s24 gray-dark">QQ客服</h2>
              <h6 class="mt5 gray">触手可及的悉心服务</h6>
            </div>
            <div class="left" style=" margin-left: 175px;">
              <a href="tencent://message/?uin=${ctxMap['siteQq'] }&Site=web&Menu=yes" title="点击联系QQ客服" target="_blank" class="s24 blue b">${ctxMap['siteQq'] }</a>
            </div>
          </div>
        </div>
        </c:when>
		</c:choose>
        <!-- /item -->
        <c:choose>
		<c:when test="${!empty ctxMap['siteTel']}">
        <div class="content-item">
          <div class="page-title al clear">
            <div class="left">
              <h2 class="s24 gray-dark">热线电话</h2>
              <h3 class="mt5 gray">7*24小时保持连线</h3>
            </div>
            <div class="left" style=" margin-left: 140px;">
              <h6 class="s24 blue b">${ctxMap['siteTel'] }</h6>
            </div>
          </div>
        </div>
        </c:when>
		</c:choose>
        <!-- /item -->
         <c:choose>
		<c:when test="${!empty ctxMap['siteMobile']}">
        <div class="content-item">
          <div class="page-title al clear">
            <div class="left">
              <h2 class="s24 gray-dark">国际电话</h2>
              <h3 class="mt5 gray">7*24小时保持连线</h3>
            </div>
            <div class="left" style=" margin-left: 140px;">
              <h6 class="s24 blue b">${ctxMap['siteMobile'] }</h6>
            </div>
          </div>
        </div>
        </c:when>
		</c:choose>
        <!-- /item -->
      </div>
      <!-- /group -->
    </body>

    </html>

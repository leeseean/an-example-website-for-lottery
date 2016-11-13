<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>短信息</title>
  <%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
  <div data-role="page">
    <div data-role="header" data-position="fixed">
      <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <a href="#" data-rel="back" data-role="button" data-ajax="false" data-icon="back" data-iconpos="notext"></a>
      </div>
      <h1>个人信息</h1>
    </div>
    <!-- /header -->
    <div data-role="content" class="ybm-member-msg">
      <ul data-role="listview" data-inset="true" data-corners="false" class="f12">
      <c:choose>
      <c:when test="${!empty msgList }">
        <li data-role="list-divider">
          <div class="ui-grid-b f12">
            <div class="ui-block-a">标题</div>
            <div class="ui-block-b tac">时间</div>
            <div class="ui-block-c tac">操作</div>
          </div>
        </li>
        <c:forEach items="${msgList }" var="item">
	        <li>
	          <div class="ui-grid-b">
	            <div class="ui-block-a"><a href="${ctx }/m/message/showMessage?id=${item.id }" data-rel="dialog">${item.msgTitle }</a></div>
	            <div class="ui-block-b tac">${item.createTime }</div>
	            <!-- <div class="ui-block-c tac"><a href="${ctx }/m/message/delMessage?id=${item.id }&code=2">删除</a></div> -->
	          </div>
	        </li>
        </c:forEach>
        </c:when>
        <c:otherwise>
        	暂无消息！
        </c:otherwise>
        </c:choose>
        <!-- /item -->
      </ul>
    </div>
    <!-- /content -->
    <div data-role="footer" data-position="fixed" data-id="deposit-footer">
      <div data-role="navbar">
        <ul>
          <li><a href="${ctx }/m/message/getMessageList?code=2" data-transition="none">个人信息</a></li>
          <li><a href="${ctx }/m/message/getMessageList?code=1" data-transition="none" class="ui-btn-active ui-state-persist">系统信息</a></li>
        </ul>
      </div>
    </div>
    <!-- /footer -->
    <!-- 左側快捷菜單 -->
    <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
    <!-- /左側快捷菜單 -->
  </div>
</body>
</html>
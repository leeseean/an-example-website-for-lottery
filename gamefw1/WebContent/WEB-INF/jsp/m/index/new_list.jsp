<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%@ include file="common.jsp"%>
	<script src="${resourceRoot}/m/js/banner.js"></script>
</head>

<body>
	<div data-role="page" data-theme="x" class="ybb-mobile-web">
  <%@ include file="head.jsp" %>
  <!-- /head -->
  <div role="main" class="ui-content">
    <%@ include file="banner.jsp" %>
    <!-- /banner -->
    <%@ include file="msg.jsp" %>
    <!-- /news -->
    <div class="ybb-main ybb-news-main">
      <ul>
      	<c:forEach var="gg" items="${ggList }">
        <li>
          <h6><fmt:formatDate value="${gg.createTime }" pattern="MM-dd hh:mm:ss"/></h6>
          <div>
            <p>${gg.ggContent }</p>
          </div>
        </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  <!-- /main -->
  <%@ include file="foot.jsp" %>
  <!-- /footer -->
  <div data-role="panel" data-display="overlay" id="quickMenu" class="ybb-mobile-sidemenu">
	<%@ include file="../inc/web_sidemenu.jsp" %>
	</div>
</div>
</body>
</html>

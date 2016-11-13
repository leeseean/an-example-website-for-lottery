<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file="common.jsp" %>
  <link rel="stylesheet" href="${resourceRoot}/m/css/ybb_m_sport.css">
  <script src="${resourceRoot}/m/js/banner.js"></script>
</head>

<body class="ybb-m sport">

<div data-role="page" data-theme="x" class="ybb-mobile-web">
  <%@ include file="head.jsp" %>
  <!-- /header -->
  <div role="main" class="ui-content">
    <%@ include file="banner.jsp" %>
    <!-- /banner -->
    <%@ include file="msg.jsp" %>
    <!-- /news -->
    
	<div class="ui-mod mod-hall hall-three">
		<div class="ui-mod-body cf">
			<div class="ui-mod-item">
	       		<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/sport/list','huangguan');" data-ajax="false" class="ui-button">
	       			<img src="${resourceRoot }/m/img/sport/sport_huangguan.png" alt="" />
	       		</a>
			</div>
		</div>
		<div class="ui-mod-body cf">
			<div class="ui-mod-item">
	        	<c:choose>
				<c:when test="${!empty webUser}">
		       		<a href="javascript:void(0);" onclick="checkFlatSatus('${ctx }/m/game/forwardGame?&gameType=sb','sb');" data-ajax="false" class="ui-button">
		       			<img src="${resourceRoot }/m/img/sport/sport_sb.png" alt="" />
		       		</a>
	       		</c:when>
	       		<c:otherwise>
	    			<a href="javascript:alert('请先登录!');void(0);" class="ui-button">
	    				<img src="${resourceRoot }/m/img/sport/sport_sb.png" alt="" />
	    			</a>
	       		</c:otherwise>
	       		</c:choose>
			</div>
		</div>
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
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file="common.jsp" %>
  <script src="${resourceRoot}/m/js/banner.js"></script>
</head>

<body>
<div data-role="page" data-theme="x" class="ybb-mobile-web" id="page1">
 <%@ include file="head.jsp" %>
  <!-- /header -->
  <div role="main" class="ui-content">
    <%@ include file="banner.jsp" %>
    <!-- /banner -->
    <%--<%@ include file="msg.jsp" %> --%>
    <!-- /news -->
    <div class="ybb-slot-main">
      <div data-role="navbar" class="slot-hall">
        <ul>
          <li><a href="${ctx }/m/main?code=slot_ag" data-ajax="false">AG电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_mg" data-ajax="false" >MG电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_pt" data-ajax="false">PT电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_os" data-ajax="false">OS电子</a></li>
          <li><a href="${ctx }/m/main?code=slot_ttg" data-ajax="false" class="ui-btn-active">TTG电子</a></li>
        </ul>
      </div>
      <div class="game-group">
        <div class="ui-grid-a">
           <c:forEach var="item" items="${ttgslot }" varStatus="itemStatus">
           	<c:if test="${itemStatus.index % 2 == 0 }">
           	<c:choose>
				<c:when test="${empty webUser}">
			          <div class="ui-block-a item">
				  <a href="javascript:void(0);" onclick="alert('请先登录');">
			            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/ttg/${item.eleGamePic});"></div>
			            <p class="r2 ac">${item.eleGameCnname}</p>
		          </a>
			          </div>
		        </c:when>
		        <c:otherwise>
		        	<div class="ui-block-a item">
		          		<a onclick="checkFlatSatus('${ctx }/m/game/forwardGame?gameType=ttg&gameName=${item.eleGameName}&gameId=${ item.eleGameId}&eleGameType=${item.eleGameType2 }','ttg');" href="javascript:void(0);" data-ajax="false">
		            		<div class="r1" style=" background-image: url(${resourceRoot}/m/img/ttg/${item.eleGamePic});"></div>
		            		<p class="r2 ac">${item.eleGameCnname}</p>
		          		</a>
		          	</div>
		        </c:otherwise>
		    </c:choose>
	        </c:if>
	        <c:if test="${itemStatus.index % 2 == 1 }">
	        <c:choose>
				<c:when test="${empty webUser}">
		          <div class="ui-block-b item">
		          	<a href="javascript:void(0);" onclick="alert('请先登录');">
		            <div class="r1" style=" background-image: url(${resourceRoot}/m/img/ttg/${item.eleGamePic});"></div>
		            <p class="r2 ac">${item.eleGameCnname}</p>
		            </a>
		          </div>
		        </c:when>
		    <c:otherwise>
		    	<div class="ui-block-b item">
		            <a onclick="checkFlatSatus('${ctx }/m/game/forwardGame?gameType=ttg&gameName=${item.eleGameName}&gameId=${ item.eleGameId}&eleGameType=${item.eleGameType2 }','ttg');" href="javascript:void(0);" data-ajax="false">
		            	<div class="r1" style=" background-image: url(${resourceRoot}/m/img/ttg/${item.eleGamePic});"></div>
		            	<p class="r2 ac">${item.eleGameCnname}</p>
		          	</a>
		        </div>
		    </c:otherwise>
		    </c:choose>
	        </c:if>
           </c:forEach>
          <!-- /item -->
        </div>
      </div>
      <!-- /group -->
      <div data-role="navbar">
        <ul>
          <%-- <c:forEach var="page" items="${pageList }" varStatus="status">
	          <li><a href="#${page }" data-transition="none" class=<c:if test="${status.index == mapStatus.index}">'ui-btn-active ui-state-persist'</c:if>>${status.index + 1}页</a></li>
          </c:forEach> --%>
          <li><a href="#1" data-transition="none" class='ui-btn-active ui-state-persist'>1页</a></li>
        </ul>
      </div>
    </div>
    <!-- /slot-main -->
  </div>
  <!-- /main -->
  <%@ include file="foot.jsp" %>
  <!-- /footer -->


<div data-role="panel" data-display="overlay" id="quickMenu1" class="ybb-mobile-sidemenu">
<%@ include file="../inc/web_sidemenu.jsp" %>
</div>
<!-- /sidemenu -->

</div>
</body>
</html>
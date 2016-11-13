<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <!DOCTYPE HTML>
  <html>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>mg电子</title>
    <%@include file="../inc/mobile_common.jsp"%>
  </head>

  <body>
    <c:forEach var="map" items="${map }" varStatus="mapStatus">
      <div data-role="page" id="${map.key }">
        <div data-role="header" data-position="fixed">
          <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
            <!-- <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a> -->
            <a href="${ctx }/m/wap/member" data-transition="none" data-role="button" data-icon="back" data-iconpos="notext"></a>
          </div>
          <h1>MG电子</h1>
        </div>
        <div data-role="content" class="ybb-m-slot-list">
          <ul>
            <c:forEach var="item" items="${map.value }">
            <li>
            	<c:choose>
            		<c:when test="${!empty webUser }">
		            	<a href="${ctx }/m/mg/goMg?gameType=mgElectronic&gameName=${item.eleGameId}&gameCode=${item.remark}" style='text-decoration:none;' data-ajax="false">
			              <img src="${resourceRoot}/web/ybb/common/images/mg/${item.eleGamePic}_h5.png" style="width: 140px;" />
			              <h6>${item.eleGameCnname}</h6>
		              	</a>
	              	</c:when>
	              	<c:otherwise>
	              		<a href="javascript:alert('请登录！');void(0);" style='text-decoration:none;'>
		              		<img src="${resourceRoot}/web/ybb/common/images/mg/${item.eleGamePic}_h5.png" style="width: 140px;" />
		              		<h6>${item.eleGameCnname}</h6>
	              		</a>
	              	</c:otherwise>
	            </c:choose>
            </li>
            </c:forEach>
          </ul>
        </div>
        <div data-role="footer" data-position="fixed">
          <div data-role="navbar">
            <ul>
              <c:forEach var="page" items="${pageList }" varStatus="status">
                <li><a href="#${page }" data-transition="none" class='<c:if test="${status.index == mapStatus.index }">ui-btn-active ui-state-persist</c:if>' data-role="button">${status.index + 1}</a></li>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
    </c:forEach>
  </body>

  </html>

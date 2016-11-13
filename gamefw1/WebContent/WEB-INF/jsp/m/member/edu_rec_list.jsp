<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<title>转换记录</title>
   	<%@include file="../inc/mobile_common.jsp"%>
</head>

<body>
<c:choose>
<c:when test="${!empty map}">
<c:forEach var="map" items="${map }" varStatus="mapStatus">
<div data-role="page" id="${map.key }">
  <div data-role="header">
    <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu${mapStatus.index + 1}" data-transition="none" data-role="button" data-icon="bars" data-iconpos="notext"></a>
      </div>
      <h1>转换记录</h1>
      <div class="ui-btn-right">
        <a href="${ctx }/m/edu/eduHistory" data-transition="none" data-rel="dialog" data-role="button" data-icon="bars" data-theme="b">转换记录</a>
      </div>
  </div>
  <!-- /header -->
  <div data-role="content">
    <div data-role="collapsible-set" data-corners="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" data-theme="b" data-content-theme="d" class="ui-mini f12">
    	<c:forEach var="item" items="${map.value }">
	      <div data-role="collapsible" data-collapsed="false">
	        <h3>
	        	${item.flatName}
	        	<c:choose>
					<c:when test="${item.eduType ==2}">
						转入
					</c:when>
					<c:when test="${item.eduType ==1}">
						转出
					</c:when>
				</c:choose>
				&nbsp;&nbsp;${item.eduPoints}￥ &nbsp;&nbsp;
				<c:choose>
					<c:when test="${item.eduStatus ==-1}">
						业务繁忙，请联系在线客服处理
					</c:when>
					<c:when test="${item.eduStatus ==0}">
						失败
					</c:when>
					<c:when test="${item.eduStatus ==1}">
						成功
					</c:when>
					<c:when test="${item.eduStatus ==2}">
						作废
					</c:when>
				</c:choose>
			</h3>
	        <ul data-role="listview">
	          <li>操作类型：
	          	<c:choose>
					<c:when test="${item.eduType ==2}">
						转入
					</c:when>
					<c:when test="${item.eduType ==1}">
						转出
					</c:when>
				</c:choose>
			  </li>
	          <!-- <li>订单号：${item.eduOrder}</li> -->
	          <li>转换时间：<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
	          <li>转换金额：${item.eduPoints}￥</li>
	          <li>转换详情：${item.eduForwardRemark}</li>
	          <li>转换状态：
	          	<c:choose>
					<c:when test="${item.eduStatus ==-1}">
						业务繁忙，请联系在线客服处理
					</c:when>
					<c:when test="${item.eduStatus ==0}">
						失败
					</c:when>
					<c:when test="${item.eduStatus ==1}">
						成功
					</c:when>
					<c:when test="${item.eduStatus ==2}">
						作废
					</c:when>
				</c:choose>
			  </li>
	        </ul>
	      </div>
      	</c:forEach>
      <!-- /item -->
    </div>
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
  <!-- /content -->
  <div data-role="panel" data-display="overlay" id="quickMenu${mapStatus.index + 1}" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
</div>
</c:forEach>
 </c:when>
 <c:otherwise>
 	<div data-role="page">
  <div data-role="header">
    <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
      </div>
      <h1>转换记录</h1>
      <div class="ui-btn-right">
        <a href="${ctx }/m/edu/eduHistory" data-rel="dialog" data-role="button" data-icon="bars" data-theme="b">转换记录</a>
      </div>
  </div>
  <!-- /header -->
  <div data-role="content">
    	暂无记录
  </div>
  <%@include file="../inc/mobile_foot.jsp" %>
  <!-- /content -->
  <div data-role="panel" data-display="overlay" id="quickMenu" class="ybm-panel">
    	<%@ include file="../inc/left_memu.jsp" %>
    </div>
</div>
 </c:otherwise>
 </c:choose>
<!-- /page -->
</body>

</html>
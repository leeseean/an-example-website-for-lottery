<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<title>注单</title>
   	<%@include file="../inc/mobile_common.jsp"%>
   	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>

<body>
<c:choose>
<c:when test="${!empty map}">
<c:forEach var="map" items="${map }" varStatus="mapStatus">
<div data-role="page" id="${map.key }">
  <div data-role="header">
    <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu${mapStatus.index + 1}" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <!-- <a href="${ctx }/m/wap/member" data-role="button" data-icon="back" data-iconpos="notext"></a> -->
      </div>
      <h1>体育投注历史</h1>
      <div class="ui-btn-right">
        <a href="${ctx}/m/main/order" data-rel="dialog" data-role="button" data-icon="bars" data-theme="b">投注单</a>
      </div>
  </div>
  <!-- /header -->
  <div data-role="content">
    <c:choose>
    	<c:when test="${!empty map.value }">
	      <c:forEach items="${map.value }" var="item">
	        <c:if test="${item.matchRtype ne 'p3' }">
		      	<c:forEach items="${item.details }" var="detail">
			      <div data-role="collapsible" data-collapsed="false" data-mini="true" data-theme="b" data-content-theme="d" class="ui-mini f12">
			        <h3>
			        	<c:if test="${detail.matchType eq 'FT'}">足</c:if>
			        	<c:if test="${detail.matchType eq 'BK'}">篮</c:if>
			        	<c:if test="${detail.dtype eq 'dy'}">[独赢]</c:if>
			        	<c:if test="${detail.dtype eq 'rq'}">[让球]</c:if>
			        	<c:if test="${detail.dtype eq 'dx'}">[大小]</c:if>
			        	<c:if test="${detail.dtype eq 'ds'}">[单双]</c:if>
			        	<c:if test="${detail.dtype eq 'dx_big'}">[积分]</c:if>
			        	<c:if test="${detail.dtype eq 'dx_small'}">[积分]</c:if>
			        	<c:if test="${detail.dtype eq 'rf'}">[让分]</c:if>
			        	<c:if test="${detail.dtype eq 'pd'}">[波胆]</c:if>
			        ${detail.teamH } VS. ${detail.teamC }</h3>
			        <ul data-role="listview">
			          <li data-theme="e">订单号：${detail.betWagersId }</li>
			          <li data-theme="e">订单日期：<fmt:formatDate value="${item.orderTime }" pattern="MM-dd HH:mm:ss" /></li>
			          <li data-theme="e">下注金额 ：<strong>${item.betIn }</strong></li>
			          <li data-theme="c">比赛时间：<fmt:formatDate value="${detail.matchTime }" pattern="MM-dd HH:mm:ss" /></li>
			          <li data-theme="c">${detail.league }</li>
			          <li data-theme="c">${detail.betVs }</li>
			          <li data-theme="c">${detail.betOddsDes }</li>
			          <li data-theme="c">
			          	<span style=" color: red;">
			          		${detail.betOddsName}
			          		</span> 
			          		@ <span style=" color: red;">${detail.betOdds }</span></li>
			          
			          <c:if test="${item.status != 2 }">
			          <c:if test="${detail.timeType eq 'roll'}">
			          	<li data-theme="c">当前比分：
      					<c:if test="${detail.betRqTeam eq 'H'}">
      						${detail.betScoreHCur }:${detail.betScoreCCur }
      					</c:if>
      					<c:if test="${detail.betRqTeam eq 'C'}">
      						${detail.betScoreCCur }:${detail.betScoreHCur }
      					</c:if>
      		
      					</c:if></li>
			          </c:if>
			          <c:if test="${item.status eq 2 }">
			          	<c:choose>
						<c:when test="${detail.matchType eq 'FT' }">
							<c:choose>
								<c:when test="${detail.betRqTeam eq 'C' and (detail.btype eq 'rq')}">
									<li data-theme="c">上半场 （${detail.score.hrScoreC }:${detail.score.hrScoreH }）</li>
									<li data-theme="c">全场（${detail.score.flScoreC }:${detail.score.flScoreH }）</li>
								</c:when>
								<c:otherwise>
									<li data-theme="c">上半场（${detail.score.hrScoreH }:${detail.score.hrScoreC }）</li>
									<li data-theme="c">全场（${detail.score.flScoreH }:${detail.score.flScoreC }）</li>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${detail.betRqTeam eq 'C' and (detail.btype eq 'rf')}">
									<li data-theme="c">全场（${detail.score.stageCF }:${detail.score.stageHF }）</li>
								</c:when>
								<c:otherwise>
									<li data-theme="c">全场（${detail.score.stageHF }:${detail.score.stageCF }）</li>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
			           </c:if>
			        </ul>
			      </div>
			     </c:forEach>
		     </c:if>
		     <c:if test="${item.matchRtype eq 'p3' }">
		      <div data-role="collapsible" data-collapsed="false" data-mini="true" data-theme="b" data-content-theme="d" class="ui-mini f12">
		        <h3>[串关]<c:out value="${fn:length(item.details)}"></c:out> </h3>
		        <ul data-role="listview">
		        <li data-theme="e">订单号：${item.betWagersId }</li>
		        <li data-theme="e">订单日期：<fmt:formatDate value="${item.createTime }" pattern="MM-dd HH:mm:ss" /></li>
		        <li data-theme="e">下注金额 ：<strong>${item.betIn }</strong></li>
		      	<c:forEach items="${item.details }" var="detail">
		          <li data-theme="a">
		          	<c:if test="${detail.matchType eq 'FT'}">足</c:if>
			        <c:if test="${detail.matchType eq 'BK'}">篮</c:if>
		          	<c:if test="${detail.dtype eq 'dy'}">[独赢]</c:if>
		        	<c:if test="${detail.dtype eq 'rq'}">[让球]</c:if>
		        	<c:if test="${detail.dtype eq 'dx'}">[大小]</c:if>
		        	<c:if test="${detail.dtype eq 'ds'}">[单双]</c:if>
		        	<c:if test="${detail.dtype eq 'jf'}">[积分]</c:if>
		        	<c:if test="${detail.dtype eq 'dx_big'}">[积分]</c:if>
			        <c:if test="${detail.dtype eq 'dx_small'}">[积分]</c:if>
		        	<c:if test="${detail.dtype eq 'pd'}">[波胆]</c:if>
		          ${detail.teamH } VS. ${detail.teamC }</li>
		          <li data-theme="c">比赛时间：<fmt:formatDate value="${detail.matchTime }" pattern="MM-dd HH:mm:ss" /></li>
		          <li data-theme="c">${detail.league }</li>
			      <li data-theme="c">${detail.betVs }</li>
		          <li data-theme="c">${detail.betOddsDes }</li>
		          <li data-theme="c">
		          	<span style=" color: red;">
		          		${detail.betOddsName}
		          		</span> 
		          		@ <span style=" color: red;">${detail.betOdds }</span></li>
	          		 <c:if test="${item.status != 2 }">
		          		<c:if test="${detail.timeType eq 'roll'}">
		   					<c:if test="${detail.betRqTeam eq 'H'}">
		   						${detail.betScoreHCur }:${detail.betScoreCCur }
		   					</c:if>
		   					<c:if test="${detail.betRqTeam eq 'C'}">
		   						${detail.betScoreCCur }:${detail.betScoreHCur }
		   					</c:if>
	   					</c:if>
   					</c:if>
		           <c:if test="${item.status eq 2 }">
		          	<c:choose>
						<c:when test="${detail.matchType eq 'FT' }">
							<c:if test="${detail.betRqTeam eq 'H'}">
								<li data-theme="c">上半场（${detail.score.hrScoreH }:${detail.score.hrScoreC }）</li>
								<li data-theme="c">全场（${detail.score.flScoreH }:${detail.score.flScoreC }）</li>
							</c:if>
							<c:if test="${detail.betRqTeam eq 'C'}">
      							<li data-theme="c">上半场（${detail.score.hrScoreC }:${detail.score.hrScoreH }）</li>
      							<li data-theme="c">全场（${detail.score.flScoreC }:${detail.score.flScoreH }）</li>
      						</c:if>
						</c:when>
						<c:otherwise>
							<c:if test="${detail.betRqTeam eq 'H'}">
								<li data-theme="c">全场（${detail.score.stageHF }:${detail.score.stageCF }）</li>
							</c:if>
							<c:if test="${detail.betRqTeam eq 'C'}">
      							<li data-theme="c">全场（${detail.score.stageCF }:${detail.score.stageHF }）</li>
      						</c:if>
						</c:otherwise>
					</c:choose>
		           </c:if>
		        </c:forEach>
		        </ul>
		      </div>
		     </c:if>
	      </c:forEach>
      	</c:when>
      	<c:otherwise>
      		暂无投注历史记录
      	</c:otherwise>
      </c:choose>
      <!-- /item -->
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
 	<div data-role="page" id="${map.key }">
  <div data-role="header">
    <div class="ui-btn-left" data-role="controlgroup" data-type="horizontal">
        <a href="#quickMenu" data-role="button" data-icon="bars" data-iconpos="notext"></a>
        <!-- <a href="${ctx }/m/wap/member" data-role="button" data-icon="back" data-iconpos="notext"></a> -->
      </div>
      <h1>体育投注历史</h1>
      <div class="ui-btn-right">
        <a href="${ctx}/m/main/order" data-rel="dialog" data-role="button" data-icon="bars" data-theme="b">投注单</a>
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
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<div class="row">
	<div class="wrapper">
		<div class="content-top-banner"></div>
	</div>
</div>

<div class="row">
	<div class="wrapper">
		<div class="header-msg">
			<%@include file="msg.jsp" %>
		</div>
	</div>
</div>

<div class="row content-body">
	<div class="wrapper">
		<!-- 体育赛事平台 -->
		<div class="ui-mod mod-hall hall-three" >
		
		  <div class="ui-mod-body cf">
			<!-- 体育赛事平台 -->
			<div class="row sports-index-row">
				<div class="wrapper">
					<ul class="clearfix">
						<c:forEach items="${sportSite }" var="item">
							<li class="item item1 clearfix">
								<div class="left">
									<img src="${resourceRoot }/web/ybb/assets/img/sport/sport-banner-${item.flat }.png" alt="" />
									<div class="bg"></div>
								</div>
								<div class="right">
									<img src="${resourceRoot }/web/ybb/assets/img/sport/sport_${item.flat }.png" alt="" />
									<c:choose>
										<c:when test="${item.flat eq 'bbin' }">
											<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')" >立即体验</a>
										</c:when>
				          				<c:when test="${item.flat eq 'sb' or item.flat eq 'sbt'}">
											<a href="${ctx }/${item.flatUrl}" target="_blank">立即体验</a>
				          				</c:when>
										<c:otherwise>
											<a href="${ctx }/${item.flatUrl}" >立即体验</a>
										</c:otherwise>
									</c:choose>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- 体育赛事平台结束 -->
		  </div>
		</div>
		<!-- / 体育赛事平台 -->
	</div>
</div>

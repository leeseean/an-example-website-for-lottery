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

<div class="row">
	<div class="wrapper">
		<div class="content-body">
			<div class="live-items live-items-main-page">
				<ul class="clearfix">
					<c:forEach var="item" items="${liveSite}">
					<li class="${item.flat }">
						<span class="bg-border"></span>
						<div class="htop">
							<h3>${item.flatName }</h3>
							<c:choose>
								<c:when test="${!empty webUser}">
									<a href="javascript:void(0)" onclick="winOpen('${ctx}/${item.flatUrl }',window.screen.width,window.screen.height,0,0,'game','${item.flat }')" title="点击进入" class="enter">立即游戏</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" plugin-alert="您尚未登录，请先登录再进行游戏">立即游戏</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="img"></div>
						<div class="intro">
							<p>${item.flatDes }</p>
						</div>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>
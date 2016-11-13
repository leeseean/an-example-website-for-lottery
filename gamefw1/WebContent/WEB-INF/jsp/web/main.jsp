<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>美高梅</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv=X-UA-Compatible content=IE=EmulateIE7>
<meta http-equiv=“X-UA-Compatible” content=“chrome=1″ />
<%@include file="/commons/web/jsp/web_common.jsp"%>

</head>
<body>
	<jsp:include page="inc/head.jsp" />
	<div class="banner">
		<div class="inner">
			<div class="banner-home">
				<ul>
					<li class="welcome"></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container home">
		<div class="notice-home">
			<div class="inner clear">
				<div class="title left"></div>
				<div class="body left">
					<ul>
						<li><a style="cursor:hand "
								onclick="winOpen('${ctx}/message/goGongGaoList','message','top=0,left=0,width=800,height=450,resizable=yes')">
									<marquee onmouseover="this.stop();" onmouseout="this.start();"
										direction="left" scrollamount="2" scrolldelay="8"
										behavior="scroll">
										 <c:forEach var="item" items="${dataList}" varStatus="status">
										 	 <span>${item.ggContent}</span>
										 </c:forEach>
									</marquee>
								</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="game">
			<ul class="clear">
				<li class="sport"><a href="javascript:void(0);" onclick="Go('${ctx}/goPageCenter?code=sport')" title="点击进入 体育赛事" class="block"></a></li>
				<li class="live"><a href="javascript:void(0);" onclick="Go('${ctx}/goPageCenter?code=live')" title="点击进入 真人视讯" class="block"></a></li>
				<li class="slots"><a href="javascript:void(0);" onclick="Go('${ctx}/electronic?code=mg')" title="点击进入 电子游艺" class="block"></a></li>
				<li class="lottery">
					<c:choose>
						<c:when test="${!empty webUser}">
							<a href="javascript:void(0)" onclick="Go('${ctx}/goPageCenter?code=lotto')" title="点击进入 彩票游戏" class="block"></a>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)" onclick="javascript:alert('您尚未登录，请先登录再进行游戏')"   title="点击进入 彩票游戏" class="block"></a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
		<div class="inner"></div>
	</div>
	<!-- /.container -->
	<jsp:include page="inc/foot.jsp" />
</body>
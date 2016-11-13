<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/inc.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title></title>
<%@include file="inc/common.jsp"%>
  <link rel="stylesheet" href="${resourceRoot}/web/ybb/assets/css/ybb_web_sport.css">
  <script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
  <script>
    var root = "${resourceRoot}";
    var rootPath = "${ctx}";
  </script>
</head>
<body class="ybb-web sport body-${page_name } body-lotto">
		<div class="body">
			<div class="header">
				<%@include file="inc/head.jsp" %>
			</div>
			<div class="main-content">
				<div class="wrapper">
					<div class="row">
						<div class="wrapper">
							<div class="content-top-banner"></div>
						</div>
					</div>
					
					<%-- <div class="row">
						<div class="wrapper">
							<div class="header-msg">
								<%@include file="inc/msg.jsp" %>
							</div>
						</div>
					</div> --%>
					
					<div class="row">
						<div class="wrapper">
							<div class="content-body">
							  <div id="lottery-index">
									<div class="wrapper">
										<ul class="clearfix clear list">
											<c:forEach items="${lottery_list}" var="obj" varStatus="sts">
											<c:if test="${obj.value.isEnable == 1 }">
												<li class="lottery-item lottery-item-${obj.value.gameTypeCode}"><a
													href="javascript:void(0)"
													onclick="Go('${ctx}/goPageCenter?code=lottery&cpparam=${obj.value.handicapCode}|${obj.value.gameTypeCode}', 'caipiao');">
														<img
														src="${resourceRoot}/web/ybb/assets/images/lottery/${obj.value.gameTypeCode}.png"
														alt="" />
														<h3>${obj.value.gameTypeName}</h3> </a></li>
											</c:if>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<%@include file="inc/foot.jsp" %>
			</div>
		</div>
		<%@include file="inc/foot_in.jsp" %>
</html>

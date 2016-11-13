<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <%@include file="/inc.jsp"%>
    <!DOCTYPE html>
    <html style="overflow-x:hidden" lang="zh-CN">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="x-ua-compatible" content="ie=edge">
      <title></title>
      <%@include file="inc/common.jsp"%>
      <link rel="stylesheet" href="${resourceRoot}/web/ybb/common/plugins/jui/smoothness/jquery-ui.min.css"/>
        <script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
        <script>
        var root = "${resourceRoot}";
        var rootPath = "${ctx}";
        </script>
        <style>
          .content-top-banner {
            margin-top: 56px;
            height: 441px;
          }
        </style>
    </head>
    <body>
		<div class="body body-${page_name } body-help">
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
					<div class="row">
						<div class="wrapper">
							<div class="header-msg">
								<%@include file="inc/msg.jsp"%>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="wrapper">
							<div class="content-body">
								<div class="pages-help clearfix" plugin="helpCenterNavgation">
									<div class="nav-left">
										<div class="h69"></div>
										<ul class="clearfix">
										<% int i = 1; %>
					                      <c:forEach var="wp" items="${wpList}" varStatus="s">
					                        <li<c:if test="${wp.pgSn eq param.pgSn}"> class="current"</c:if>>
					                          <a href="${resourceRoot}/web/ybb/html/help/${wp.pgSn }.html" class="block"><em class="icon-<%= i%>"></em><span>${wp.pgTitle}</span></a>
					                          </li>
					                          <% i+=1; %>
					                      </c:forEach>
										</ul>
									</div>
									<div class="pages-content">
										<div class="page-body">
											loading
										</div>
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
	</body>
</html>


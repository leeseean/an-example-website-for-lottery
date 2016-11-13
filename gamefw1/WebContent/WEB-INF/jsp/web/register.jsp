<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
  <%@include file="/inc.jsp"%>
    <!DOCTYPE html>
    <html lang="zh-CN">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="x-ua-compatible" content="ie=edge">
      <title></title>
 	
 	  <link rel="stylesheet" href="${resourceRoot}/web/ybb/common/css/jquery-ui.min.css"/>
      <%@include file="inc/common.jsp"%>

        <script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
        <script>
        var root = "${resourceRoot}";
        var rootPath = "${ctx}";
        </script>
    </head>

    <body>
		<div class="body body-${page_name }">
			<div class="header">
				<%@include file="inc/head.jsp" %>
			</div>
			<div class="main-content">
				<div class="wrapper">
					<%@include file="inc/register.jsp" %>
				</div>
			</div>
			<div class="footer">
				<%@include file="inc/foot.jsp" %>
			</div>
		</div>
		
		<%@include file="inc/foot_in.jsp" %>
	</body>
</html>


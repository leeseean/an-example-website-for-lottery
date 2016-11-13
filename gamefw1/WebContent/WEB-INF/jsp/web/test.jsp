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
		<div class="body">
			<div class="header">
				<%@include file="inc/head.jsp" %>
			</div>
			<div class="main-content">
				<div class="wrapper">
					<%@include file="inc/test.jsp" %>
				</div>
			</div>
			<div class="footer">
				<%@include file="inc/foot.jsp" %>
			</div>
			<div class="misc">
				<div class="ybb-dia-hp" title="新葡京娱乐城欢迎莅临" style="display:none">
				    <p class="center">${ctxMap['msg004'] }</p>
				</div>
			</div>
		</div>
		
		<%@include file="inc/foot_in.jsp" %>
	</body>
</html>


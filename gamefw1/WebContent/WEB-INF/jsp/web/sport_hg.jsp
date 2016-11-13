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
<body class="ybb-web sport body-${page_name } body-sport">
		<div class="body">
			<div class="header">
				<%@include file="inc/head.jsp" %>
			</div>
			<div class="main-content">
				<div class="wrapper">
					<jsp:include page="inc/sport.jsp" />
				</div>
			</div>
			<div class="footer">
				<%@include file="inc/foot.jsp" %>
			</div>
		</div>
		
		<%@include file="inc/foot_in.jsp" %>

</html>
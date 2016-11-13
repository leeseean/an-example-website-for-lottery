<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/inc.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:hidden">
<head>
<meta charset="UTF-8">
<title></title>
<%@include file="inc/common.jsp"%>
<link rel="stylesheet" href="${resourceRoot}/web/ybb/common/plugins/jui/smoothness/jquery-ui.min.css"/>
<script src="${resourceRoot}/web/ybb/common/js/jquery.min.js"></script>
<script>
var root = "${resourceRoot}";
var rootPath = "${ctx}";
</script>
</head>
<body>
	<div class="body body-${page_name } body-gdelectronic">
		<div class="header">
			<%@include file="inc/head.jsp" %>
		</div>
		<div class="main-content">
			<div class="wrapper">
				<%@include file="inc/gdElectronic.jsp" %>
			</div>
		</div>
		<div class="footer">
			<%@include file="inc/foot.jsp" %>
		</div>
	</div>

	<%@include file="inc/foot_in.jsp" %>
</body>
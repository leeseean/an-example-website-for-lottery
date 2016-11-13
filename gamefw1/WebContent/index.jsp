<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="inc.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>${ctxMap['siteName'] }</title>
<head>

  <meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="keywords" content="${ctxMap['siteKeywords'] }" />
<meta name="Description" content="${ctxMap['siteDescription'] }" />
<link rel="icon" href="${resourceRoot}/web/ybb/assets/img/favicon.ico" type="image/x-icon">
</head>
<frameset rows="*,0,0" frameborder="0" border="0" framespacing="0" style="border:0;"> 
<frame name="index" src="${ctx }/index" frameborder="0" style="border:0;">
<frame name="func" scrolling="0" noresize src="">
<frame src=""></frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>

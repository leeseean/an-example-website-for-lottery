<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 97%;">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>webcom</title>
</head>
<body id="HOP" style="height:100%;">
	<div id="kq_box_001" style="height: 100%;background-color: #fff;">
		<div id="kq_box_text" style="width: 100%;padding: 35px 0px;text-align: center;color: #974D02;font-size: 20px;border-radius: 5px;font-weight: bold;">${msg}</div>
	</div>
</body>
</html>
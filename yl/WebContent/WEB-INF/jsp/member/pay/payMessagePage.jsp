<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html style="overflow-x:hidden">
  <head>
    <base href="<%=basePath%>">

    <title>支付通知</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

  </head>

  <body>
   <div align="center" style="font-size: 14px;color: red;margin-top: 20px;"><p>尊敬的会员您好，您的${message}</p></div>

  </body>
</html>

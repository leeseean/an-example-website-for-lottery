<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <title></title>
<body onload="document.forms[0].submit();">
	<form action="${ctx}/agent/loginAuth" method="post">
		<mh:token></mh:token>
		<input type="hidden" name="authKey" value="${authKey}"/>
	</form>
  </body>
</html>

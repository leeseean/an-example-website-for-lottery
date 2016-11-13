<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="inc/common.jsp"%>
<title>SA GAME</title>
<body onload="login();">
	<form action="${url }" method="post" id="loginform">
		<input type="hidden" name="username" value="${userName }">
		<input type="hidden" name="token" value="${token }">
		<input type="hidden" name="lobby" value="${lobby}">
		<input type="hidden" name="lang" value="${lang }">
		<c:if test="${!empty mobile }">
			<input type="hidden" name="returnurl" value="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()%>">
			<input type="hidden" name="mobile" value="${mobile }">
		</c:if>
	</form>
</body>
<script type="text/javascript">
	function login(){
		document.getElementById("loginform").submit();
	}
</script>
</html>

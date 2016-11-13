<%@ page language="java" import="java.util.*,com.mh.commons.constants.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.mh.commons.utils.ServletUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>
<%
	Map<String,String> ctxMap = WebSiteManagerConstants.ctxMap;
	request.setAttribute("ctxMap", ctxMap);

	String p = request.getParameter("p");
	if(!"".equals(p) && !"null".equals(p) && p!=null){
		request.getSession().setAttribute("agentNo", p);
	}
%>
<!doctype html>
<html lang="en" ng-app="App">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
	<link rel="stylesheet" href="${resourceRoot }/wap/member/app/css/reset.css" />
	<link rel="stylesheet" href="${resourceRoot }/wap/member/app/css/pure-min.css" />
	<link rel="stylesheet" href="${resourceRoot }/wap/member/app/css/app.css" />

	<script>
		var templateBaseURI = "${resourceRoot}/wap/member/app/templates";
		var staticURI = "${resourceRoot}/wap/member/app";
		var baseURI = "${ctx}";
		var onlineServiceLink = "${ctxMap['msg007'] }";
	</script>

	<c:choose>
		<c:when test="${empty webUser}">
		<script>var isLogged = false;</script>
		</c:when>
		<c:otherwise>
		<script>var isLogged = true;</script>
		</c:otherwise>
	</c:choose>

	<c:if test="${dev eq true or true}">
		<script type="text/javascript" src="${resourceRoot }/wap/member/vender/angular.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/vender/angular-ui-router.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/vender/angular-dialog.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/vender/fastclick.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/vender/swiper.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/directives/commom.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/services/Common.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/filters/filters.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/HomeCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/DepositCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/MessagesCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/WithdrawalCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/WithdrawalAccountCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/ConversionCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/UserInfoCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/UserPwdCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/UpdateDepositCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/DispensingLogCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/OrderCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/UserBindBankCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/DepositAccountCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/OrderLogCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/UserEduLogCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/ThirdpayCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/ThirdpayDepositFormCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/controllers/goPayCenterCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/templates/template.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/app.module.js"></script>
	</c:if>

	<c:if test="${dev eq true }">
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/dist/main-min.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/member/app/dist/app.module-min.js"></script>
	</c:if>

	<title>会员中心</title>
</head>
<body>
	<div class="body">
		<ui-view></ui-view>
	</div>
</body>
</html>
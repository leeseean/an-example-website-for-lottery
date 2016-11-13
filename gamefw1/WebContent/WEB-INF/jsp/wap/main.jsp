<%@ page language="java" import="java.util.*,com.mh.commons.constants.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.mh.commons.utils.ServletUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.mh.com/framework/tags" %>
<!doctype html>
<html lang="en" ng-app="App">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
	<link rel="stylesheet" href="${resourceRoot }/wap/app/css/reset.css" />
	<link rel="stylesheet" href="${resourceRoot }/wap/app/css/pure-min.css" />
	<link rel="stylesheet" href="${resourceRoot }/wap/app/css/app.css" />
	<link rel="stylesheet" href="${resourceRoot }/wap/app/css/response.css" />
	
	<script>
		var templateBaseURI = "${resourceRoot}/wap/app/templates";
		var staticURI = "${resourceRoot}/wap/app";
		var baseURI = "${ctx}";
	</script>
	
	<c:if test="${dev eq true or true}">
		<script type="text/javascript" src="${resourceRoot }/wap/vender/angular.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/vender/angular-ui-router.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/vender/angular-dialog.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/vender/fastclick.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/vender/swiper.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/directives/commom.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/CaipiaoPeiyu.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/CaipiaoCreateOrder.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/CaipiaoInfo.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/CaipiaoOrderHistory.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/CaipiaoGameList.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/CaipiaoKaijingHistory.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/CaipiaoMultiGroupPreorder.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/services/Common.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/filters/filters.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/HomeCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpConfirmCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpChooseWayCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpHK6Ctrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpHistoryCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpKjCenterCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpBJKL8Ctrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpBJPK10Ctrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpKLSFCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpSSCCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpFTCCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpConfirmGGCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpConfirmHxCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/controllers/CpConfirmHxCtrl.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/templates/template.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/app.module.js"></script>
	</c:if>
	
	<c:if test="${dev eq true }">
		<script type="text/javascript" src="${resourceRoot }/wap/app/dist/main-min.js"></script>
		<script type="text/javascript" src="${resourceRoot }/wap/app/dist/app.module-min.js"></script>
	</c:if>
	
	<title>彩票</title>
</head>
<body>
	<ui-view></ui-view>
</body>
</html>
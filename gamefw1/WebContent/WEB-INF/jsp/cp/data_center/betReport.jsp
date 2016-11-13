<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>下注明细</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp">
<link rel="stylesheet" href="${resourceRoot}/cp/css/pure-min.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/ui-button.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/ui-icon.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto-mod.css">
<link rel="stylesheet" href="${resourceRoot}/cp/css/lotto.css">
<script src="${resourceRoot}/cp/js/jquery-1.11.0.min.js"></script>
<script src="${resourceRoot}/cp/js/datePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
	<div class="ybb-lotto">
		<!-- 账户历史 -->
		<div class="lotto-body">
			<div class="pure-g inner">
				<div class="pure-u-1">
					<div class="lotto-assist">
						<!-- / 账户历史 -->
						<c:choose>
							<c:when test="${!empty lx}">
								<jsp:include page="day_data.jsp"></jsp:include>
							</c:when>
							<c:otherwise>
								<jsp:include page="week_data.jsp"></jsp:include>
							</c:otherwise>
						</c:choose>
						
						<!-- 当前日期下注状况 -->
					</div>
				</div>
				<!-- / 当前日期下注状况 -->
			</div>
		</div>
	</div>
</body>
</html>


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<head>
<%@include file="/commons/member/jsp/member_common.jsp"%>
</head>
<body>
<body>
<div class="page-body">
	<div class="sheet-mod">
		<div class="sheet-content mt10">
			<c:if test="${success eq 1 }">
				<table class="s14">
				<tr>
					<td ></td>
					<td>${msg }</td>
				</tr>
				<tr>
					<td class="pr20 ar gray-dark">总账户余额:</td>
					<td><span class="red">${user.userMoney }</span>
					</td>
				</tr>
				</table>
			</c:if>
			<c:if test="${success != 1 }">
			<table class="s14">
				<tr>
					<td  ></td>
					<td>${msg }</td>
				</tr>
			</table>
			</c:if>
		</div>
	</div>
</div>
<script src="${resourceRoot}/member/js/plugins.js"></script>
</body>
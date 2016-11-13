<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>最新讯息</title>
<style type="text/css">
body {
	background-color: #E5E5E5;
}
</style>
</head>
<body style="background-color: #D4D4D4">
	<table width="720" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin-top: 10px">
		<tr>
			<td height="58" background="${resourceRoot}/web/ybb/assets/img/message_top.jpg">
				<div style="margin-left: 83px; font-size: 18px; margin-bottom: 12px; font-weight: bold">最新讯息</div>
			</td>
		</tr>


		<tr>
			<td valign="top" background="${resourceRoot}/web/ybb/assets/img/message_c.jpg">
				<table width="100%" cellpadding="0" cellspacing="0" border="0"
					style="margin-bottom: 5px;">
					<tr>
						<td width="150" valign="top">
							<div style="margin-left: 30px;font-size: 13px;font-weight: bold">日期</div></td>
						<td valign="top">
							<div style="margin-right: 20px; font-size: 13px;font-weight: bold">内容</div></td>
						<td width="10">
					</td>
					</tr>
				</table>
			</td>
		</tr>
 
		<c:forEach var="item" items="${dataList}" varStatus="item_index">
		<tr>
			<td valign="top" background="${resourceRoot}/web/ybb/assets/img/message_c.jpg">
				<table width="100%" cellpadding="0" cellspacing="0" border="0" style="margin-bottom: 7px;">
					<tr>
						<td width="150" valign="top">
							<div style="margin-left: 30px;font-size: 12px">
							<fmt:formatDate value="${item.createTime}" pattern="MM/dd HH:mm:ss"/>
							</div>
						</td>
						<td valign="top">
							<div style="margin-right: 20px; font-size: 12px;line-height:16px;">
								 ${item.ggContent}
							</div>
						</td>
						<td width="10"></td>	
					</tr>
				
				</table>
			</td>
		</tr>
 		</c:forEach>

		<tr>
			<td height="55" background="${resourceRoot}/web/ybb/assets/img/message_f.jpg">&nbsp;</td>
		</tr>

	</table>

</body>
</html>



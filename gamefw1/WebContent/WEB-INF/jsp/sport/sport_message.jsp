<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>History</title>

<link href="${resourceRoot}/sport/css/mem_body_ft.css" rel="stylesheet">
<link href="${resourceRoot}/sport/css/scroll.css" rel="stylesheet">

<script type="text/javascript">
	var select_date = '${select_date}';
	var t_page = '3';
	var page_no = '';
	var fField = '';
	var root= "${resourceRoot}";
	var rootPath = "${ctx}";
</script>
<script src="${resourceRoot}/sport/js/jquery.min.js" type="text/javascript"></script>
<script src="${resourceRoot}/sport/js/scroll_history.js" type="text/javascript" ></script>
</head>
<body id="MSG" class="bodyset" onload="init_scroll()" onkeydown="checkKey(event.keyCode)">

	<table border="0" cellpadding="0" cellspacing="0" id="box">
		<tbody>
			<tr>
				<td class="top"><h1>
						<em>公告栏</em>
					</h1>
				</td>
			</tr>
			<tr>
				<td class="mem his_top">
					<div>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="menu_set">
							<tbody>
								<tr class="table_main_settings_tr">
									<td id="page_no2"><span id="pg_txt"></span> <span
										style="display: none;" id="t_pge"></span>
										<span onclick="chg_date('all');"><a href="#"><font <c:choose><c:when test="${select_date=='all'}">class="scr_on"</c:when><c:otherwise>class="scr_out"</c:otherwise></c:choose>
												id="all" color="#ffffff" >全部</font>
										</a>
									</span> / <span onclick="chg_date(0);"><a href="#"><font <c:choose><c:when test="${select_date=='0'}">class="scr_on"</c:when><c:otherwise>class="scr_out"</c:otherwise></c:choose>
												id="today" color="#ffffff" >今日</font>
										</a>
									</span> / <span onclick="chg_date(-1);"><a href="#"><font <c:choose><c:when test="${select_date=='-1'}">class="scr_on"</c:when><c:otherwise>class="scr_out"</c:otherwise></c:choose>
												id="yesterday" color="#ffffff" >昨日</font>
										</a>
									</span> / <span onclick="chg_date(-2);"><a href="#"><font <c:choose><c:when test="${select_date=='-2'}">class="scr_on"</c:when><c:otherwise>class="scr_out"</c:otherwise></c:choose>
												id="before" color="#ffffff" >昨日之前</font>
										</a>
									</span></td>
									<td class="search">
										<input type="text" id="findField" name="" value="" class="ccroll_input"> 
										<input type="button" id="findbutton" name="" onclick="FindNext();" value="搜寻" class="ccroll_btn">
									</td>
								</tr>
							</tbody>
						</table>
					</div></td>
			</tr>
		</tbody>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" id="box">
		<tbody>
			<tr>
				<td class="mem his_body">
					<table border="0" cellspacing="0" cellpadding="0" class="game">
						<tbody>
							<tr>
								<th width="40">序号</th>
								<th width="70">日期</th>
								<th align="left" class="info">公告内容</th>
							</tr>
							<c:forEach var="item" items="${dataList}" varStatus="item_index">
								<tr class="b_rig color_bg2" style="display: ">
									<td width="40" class="m_cen">${item_index.index+1}</td>
									<td width="70" class="m_cen">
										<fmt:formatDate value="${item.date}" pattern="yy-MM-dd" />
									</td>
									<td class="m_lef">${item.content}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td id="foot"><b>&nbsp;</b></td>
			</tr>
		</tbody>
	</table>
</body>
</html>
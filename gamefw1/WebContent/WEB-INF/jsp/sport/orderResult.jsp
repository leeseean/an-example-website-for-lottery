<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.mh.service.WebSportResultsService,com.mh.commons.utils.SpringContextHolder"%>
<%@page import="java.util.*" %>
<%
WebSportResultsService webSportResultsService = SpringContextHolder.getBean("webSportResultsServiceImpl");
List<String> contentList = webSportResultsService.getSportMessageLimit();
 %>
<!doctype html>
<html>
<head>
<title>皇冠体育</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="${resourceRoot}/sport/css/mem_order_sel.css" rel="stylesheet" type="text/css">
    	<link href="${resourceRoot}/sport/css/mem_order_olympics.css" rel="stylesheet" type="text/css">
		<link href="${resourceRoot}/sport/css/mem_order_ft.css" rel="stylesheet" type="text/css">
<script>
	var root = "${resourceRoot}";
	var rootPath = "${ctx}";
</script>
<script type="text/javascript" src="${resourceRoot}/sport/js/jquery.min.js"></script>
<script type="text/javascript" src="${resourceRoot}/sport/js/hg_header.js"></script>
<script type="text/javascript">
	window.onload = function() {
		//parent.onloadSet(document.body.scrollWidth, document.body.scrollHeight,"rec_frame");
		var total = parseInt("${total}");
		parent.reinitRecIfFrame(total);
	};
	function re_load() {
		window.location.href = window.location;
	}
</script>
</head>
<body id="OHIS" onselectstart="self.event.returnValue=false" oncontextmenu="self.event.returnValue=false;window.event.returnValue=false;">
	<c:choose>
		<c:when test="${!empty dataList}">
			<div class="ord">
				<div class="title">
					<h1>最新十笔交易</h1>
					<div class="tiTimer" onclick="re_load();"></div>
				</div>
				<div class="show">
					<c:forEach var="item1" items="${dataList}">
						<div class="tname">
							<c:forEach var="item2" items="${item1.details}">
								<span>${item2.betVs}&nbsp;&nbsp;<font class="td_13_c" color="red"><b></b></font>
								<br>${item2.betOddsDes}<br> <font color="#CC0000">${item2.betOddsName}</font>@ <font color="#CC0000"><b>${item2.betOdds}</b></font>
								</span>
								<hr style="height:1px;border:none;border-top:1px solid #D1CBCB;width: 100%">
							</c:forEach>
							
							<b class="gold"><span  class="fin_gold">RMB&nbsp;<b>${item1.betIn}</b></span></b>
							<div class="error" style="display:none"></div>
						</div>
					</c:forEach>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div id="order_div" name="order_div" style=" overflow:hidden;">
		        <div id="pls_bet" name="pls_bet" style="left: 0px; top: 0px; background-color: rgb(227, 207, 170);">
		            <img src="${resourceRoot}/sport/images/order_none.jpg" width="216" height="22">
		            <div class="show_info">您没有未结算的交易。</div>
		        </div>
		    </div>
			
		</c:otherwise>
	</c:choose>
 	<!-- 已开赛 滚球 -->
    <div id="euro_open" style="z-index: -1;">
        <div id="euro_menu">
            <div class="rb_over" id="RB_btn" onmouseover="Eurover(this);" onmouseout="Eurout(this);"
                 onclick="goRB();"></div>
        </div>
        <div id="oly_main111">
            <!--滚球 -->
            <div id="RB_oly">
                <div id="FT_RB" class="oly_tr" style="color: black;text-decoration:none;" onclick="parent.parent.header.leftRollFt();">足球(${src.ftCount })</div>
                <div id="BK_RB" class="oly_tr" style="color: black;text-decoration:none;" onclick="parent.parent.header.leftRollBk();">篮球 / 美式足球(${src.bkCount })</div>
            </div>

        </div>

    </div>
    
    <!--公告 Start-->
    <div id="info_div" name="info_div" style="top: 480px;">
        <div class="msg_box">
            <h2>公告<span class="more"><a href="#" onclick="showMoreMsg('${ctx}/sport/goMessageList');">更多</a></span></h2>
            <div class="msg_main">
                <marquee height="100" scrollamount="1" direction="up" onmouseover="this.stop();"  onmouseout="this.start();">
                    <%
                    	for(String content:contentList){
                     %>
                      <span onclick="showMoreMsg('${ctx}/sport/goMessageList');"><%=content %></span><br/><br/>
                     <%}%>
                </marquee>
            </div>
        </div>
    </div>
    <!--公告 End-->
</body>
</html>
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

		<script type="text/javascript" src="${resourceRoot}/sport/js/jquery.min.js"></script>
    	<script type="text/javascript" src="${resourceRoot}/sport/js/hg_header.js"></script>
		
    	<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		-->
		</style>
	</head>
<body id="HFT" class="bodyset" >
	<div id="order_div" name="order_div" style=" overflow:hidden;">
        <div id="pls_bet" name="pls_bet" style="left: 0px; top: 0px; background-color: rgb(227, 207, 170);">
            <img src="${resourceRoot}/sport/images/order_none.jpg" width="216" height="22">
            <div style="width:216; height:93px; text-align:center; padding-top:16px;">
                <font style="font:0.75em Arial, Helvetica, sans-serif; font-weight:bold;">
                	<c:choose>
						<c:when test="${!empty webUser}">
							点击赔率便可将<br>选项加到交易单里。
						</c:when>
						<c:otherwise>
							请先登录!
						</c:otherwise>
					</c:choose>
                </font>
            </div>
        </div>
        <div id="bet_div" name="bet_div" style="display: none">
            <iframe src="" id="bet_order_frame" name="bet_order_frame" scrolling="NO" frameborder="NO" border="0"
                    height="100%"></iframe>
        </div>
        <div id="rec_div" name="rec_div" style="display: none">
            <iframe id="rec_frame" name="rec_frame" scrolling="NO" frameborder="NO" border="0" height="0"></iframe>
        </div>
    </div>

    <!-- 已开赛 滚球 -->
    <div id="euro_open" style="z-index: -1;">
        <div id="euro_menu">
            <div class="rb_over" ></div>
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
                      <span onclick="showMoreMsg('${ctx}/sport/goMessageList');"><%=content %></span>
                      <br/><br/>
                     <%}%>
                </marquee>
            </div>
        </div>
    </div>
    <!--公告 End-->
</body>
<script type="text/javascript">
	parent.reinitIframe();
</script>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="Robots" contect="none">
    <title>Sportsbook Rules</title>
   <link rel="stylesheet" href="${resourceRoot}/sport/css/mem_qa.css" type="text/css">
    <link rel="stylesheet" href="${resourceRoot}/sport/css/mem_qa_roul.css" type="text/css">
    <script language="javascript" src="${resourceRoot}/sport/js/QA_sport.js"></script>
</head>
<body id="SPORT" onselectstart="self.event.returnValue=false" oncontextmenu="self.event.returnValue=false;window.event.returnValue=false;">
<div class="qa_head"><em>帮助</em><span class="close_box" onclick="window.close();">关闭</span></div>
<div id="wrapper">

        <div id="qa_nav">
       <%@ include file="top1.jsp"%>
    </div>

    <div id="main">
        <a name="top"></a>
        <h1>选择体育项目 :
		<%@ include file="top2.jsp"%>
        </h1>
        <div id="info"><!--资料区-->
            <p class="b sub">冠军投注</p>
            <p class="b sub"><em>最后更新日期：06/06/2014</em></p>
            <p>冠军市场是提供预测一场赛事，联赛，锦标赛或赛车最终是否可以获得冠军。冠军市场包括以下类型（不仅限于此）：</p>
            <ul style="list-style:decimal">
                <li>一个比赛的最终结果，例如：世界杯冠军或F1车手冠军。</li>
                <li>小组赛回合的最终结果，例如：世界杯小组赛冠军。</li>
                <li>一场赛事的最终结果，例如：一个球队从半决赛到决赛，不管比分，是否有加时赛或点球。</li>
                <li>赛车的最终结果，例如：F1赛事的个人冠军。</li>
                <li>一场比赛的最高得分者。</li>
                <li>获得最有价值球员等。</li>
            </ul>
            <p class="b">1. 一般规则</p>
            <ul style="list-style:decimal">
                <li>所有冠军投注派彩都是已最终结果为准。</li>
                <li>无论挑选的人或球队是否有参与，所有冠军投注都视为有效。</li>
                <li>胜负不分规则适用于冠军投注市场。</li>
                <li>无论在什么情况下，如果使用"任何其他球员"或"任何其他球队"代替名称的参赛者将被视为无名称。</li>
                <li>如果体育项目有特殊规则，则将取代一般冠军规则。</li>
                <li>任何特定月份相关的冠军投注：市场将会在上一个月最后一天的23:59(英国时间)暂停。延迟或取消赛事将不影响赛果。如果是由于人为错误或技术故障而没有关闭市场，公司有权利取消利用此错误下注的所有注单。</li>
                <li>冠军投注仅适用于常规赛季（除非另作说明）。整个赛季赛程结束后球队最终的排名来决定冠军。季后赛或随后查询(潜在扣分)各自联赛将不计算在最终赛果。</li>
            </ul>

            <div class="to_top"><a href="#top"><span>回最上层</span></a></div>
        </div><!--资料区 End-->
    </div>

</div>
<div class="qa_foot"></div>


</body></html>
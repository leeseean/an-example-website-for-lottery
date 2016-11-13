<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta name="Robots" contect="none">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <title>Sportsbook Rules</title>
   <link rel="stylesheet" href="${resourceRoot}/sport/css/mem_qa.css" type="text/css">
    <link rel="stylesheet" href="${resourceRoot}/sport/css/mem_qa_roul.css" type="text/css">
    <script type="text/javascript" src="${resourceRoot}/sport/js/jquery.min.js"></script>
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
            <p class="b sub">篮球</p>
            <p class="b sub"><em>最后更新日期：19/05/2014</em></p>
            <p class="b sub">1. 一般规则</p>
            <ul style="list-style:decimal">
                <li>如果比赛场地有变更，所有注单将被取消。 </li>
                <li>NBA/NBL篮球赛事必须至少进行43分钟，投注的注单才被视为有效。任何其他的篮球联赛或比赛，至少要进行35分钟，投注的注单才被视为有效，除非另有注明。 </li>
                <li>美国大学篮球联赛至少必须进行35分钟，投注的注单才被视为有效，除非另有注明。 </li>
                <li>如果比赛或赛事取消，中断或延迟并且没有在官方指定开赛时间的12小时内重新开始，所有该场赛事的投注即被视为无效并取消，除非在个别体育规则里另有指定注明。公司取消该赛事所有注单的结果被视为最终决定，无需参考官方赛事裁判或相关部门的决定。</li>
                <li>如果赛事是在上半场中断，所有上半场的注单将被取消。如果赛事是在下半场中断所有上半场的投注保持有效，但所有下半场的注单将被取消，除非在个别玩法规则另有注明。 </li>
                <li>最终赛果比分是以全场四节为准，包括加时赛。</li>
                <li>单节/半场的投注，比赛必须完成赛节注单才被视为有效，除非在个别玩法规则另有注明。 </li>
                <li>第四节投注不包括加时赛。 </li>
                <li>美国大学篮球联赛场地规则：盘口指示的"主场"和"客场"信息仅供参考。无论原定场地是否更改为"主场"，"客场"或"中立场"，所有注单将保持有效。 </li>
                <li>除非个别玩法规则另有注明，赛事完场时间将包括加时赛。 </li>
                <li>如比赛在法定时间提前进行，在比赛开始前的投注依然有效，在比赛开始后的所有投注均视为无效(滚球投注另作别论)。 </li>
            </ul>
            <p class="b sub">2. 投注类型</p>
            <ul style="margin-left: 25px;">
                <p class="b sub">独赢盘</p>
                <ul style="list-style:decimal">
                    <li>预测哪一支球队将在比赛胜出。盘口提供两支球队为投注选项。 </li>
                </ul>
                <br>
                <p class="b sub">让分盘</p>
                <ul style="list-style:decimal">
                    <li>预测哪一支球队在盘口指定的让分球数在半场/全场/赛事单节赢得比赛。 </li>
                    <li>如果赛事在下半场取消或中断，所有上半场注单保持有效。 </li>
                    <li>如果赛事在下半场取消或中断，所有下半场注单将被取消。 </li>
                </ul>
                <br>
                <p class="b sub">滚球让分盘</p>
                <ul style="list-style:decimal">
                    <li>预测哪一支球队在盘口指定的让分数里赢得半场/全场/赛事单节的比赛。 </li>
                    <li>结算是以0-0的比分在比赛/时节结束后按盘口开出的让分球数做裁决。投注当时的比分对结算没有影响。 </li>
                </ul>
                <br>
                <p class="b sub">大/小盘（总比分）</p>
                <ul style="list-style:decimal">
                    <li>预测赛事总比分将大于或小于在盘口指定的大/小盘分数。 </li>
                    <li>如果赛事中断前已有明确结果并且之后没有任何显著会影响赛事结果的情况，大/小盘注单才会被结算。若遇到任何其他情况，注单将一律被取消。 </li>
                    <li>如果赛事在上半场中断，所有上半场注单将被取消，除非中断前已有明确结果并且之后没有任何显著会影响赛事结果的情况注单才会被结算。 </li>
                    <li>如果赛事在下半场取消或中断，所有上半场注单保持有效。 </li>
                    <li>如果赛事在下半场取消或中断，所有下半场注单将被取消，除非中断前已有明确结果并且之后没有任何显著会影响赛事结果的情况注单才会被结算。 </li>
                    <li>如果赛事中断, 所有时节的注单将被取消除非遇到以下其中一个情况：
                        <ul style="list-style:lower-alpha">
                            <li>投注的时节是在比赛中断前。 </li>
                            <li>比赛中断前已有明确结果并且之后没有任何显著会影响赛事结果的情况。 </li>
                        </ul>
                    </li>
                </ul>
                <br>
                <p class="b sub">滚球大/小盘（总比分）</p>
                <ul style="list-style:decimal">
                    <li>预测赛事总比分将大于或小于在盘口指定的大/小盘分数。 </li>
                    <li>结算是以0-0的比分在比赛/时节结束后按盘口开出的让分数做裁决。投注当时的比分对结算没有影响。 </li>
                    <li>如果赛事中断前已有明确结果并且之后没有任何显著会影响赛事结果的情况，大/小盘注单才会被结算。若遇到任何其他情况，注单将一律被取消。 </li>
                </ul>
                <br>
                <p class="b sub">大/小盘（主队或者客队的分数）</p>
                <ul style="list-style:decimal">
                    <li>预测赛事主队/客队的总分数将大于或小于在盘口指定的大/小盘分数。</li>
                    <li>如果赛事取消，所有的注单将会被认为无效，除非注单在中断前已经结算或者在中断前已有明确的结果并且之后没有任何显著会影响赛事结果的情况注单才会被结算。</li>
                    <li>所有注单的结算都是依据相关体育机构的官方统计数据为准。</li>
                </ul>
                <br>
                <p class="b sub">单/双</p>
                <ul style="list-style:decimal">
                    <li>预测赛事的总比分是单数或双数。加时赛将包括在内。 </li>
                </ul>
                <br>
                <p class="b sub">最先得分球队</p>
                <ul style="list-style:decimal">
                    <li>预测最先得分的球队。 </li>
                    <li>如果赛事在有得分后中断，所有最先得分球队的注单将保持有效。 </li>
                    <li>如果赛事在没有球队得分前中断，所有最先得分球队的注单将被取消。 </li>
                    <li>如果赛事在4节完场时间以及加时赛内没有球队得分，所有最先得分球队的注单将被取消。 </li>
                </ul>
                <br>
                <p class="b sub">最后得分球队</p>
                <ul style="list-style:decimal">
                    <li>预测最后得分的球队。 </li>
                    <li>如果赛事中断， 所有最后得分球队的注单将被取消。 </li>
                    <li>如果赛事在4节完场时间以及加时赛内没有球队得分，所有最后得分球队的注单将被取消。 </li>
                </ul>
                <br>
                <p class="b sub">单节最高得分球队</p>
                <ul style="list-style:decimal">
                    <li>预测单节最高得分的球队。 </li>
                    <li>加时赛不计算在内。 </li>
                    <li>如果赛事中断，所有单节最高得分球队的注单将被取消。 </li>
                    <li>如果赛事在4节完场时间以及加时赛内没有球队得分，所有最后单节最高得分球队的注单将被取消。 </li>
                </ul>
                <br>
                <p class="b sub">每节最先获得20分的球队</p>
                <ul style="list-style:decimal">
                    <li>预测每节最先得20分的球队。 </li>
                    <li>加时赛不计算在内。 </li>
                    <li>如果赛事中断前已有明确结果并且之后没有任何显著会影响赛事结果的情况，注单才会被结算。若遇到任何其他情况，注单将一律被取消。 </li>
                    <li>如果每节都没有球队获得20分，所有注单将被取消。 </li>
                    <li>取决于赛事，玩法指定球队需最先获得的分数可能有变化，并且会清楚的显示在盘口。 </li>
                </ul>
                <br>
                <p class="b sub">篮球特别投注</p>
                <ul style="list-style:decimal">
                    <li>预测比分，抢断，篮板，助攻，3分球等。 </li>
                    <li>注单的结算将根据赛事（包括加时赛）官方冠军赛果为准。 </li>
                    <li>双方球员/球队必须参与比赛，投注才被视为有效。 </li>
                    <li>如果一方或双方球员/球队没有参与比赛，所有注单将被取消。 </li>
                    <li>注单的结算将根据NBA或特别官方机构公布的结果为准，并且任何赛后更改的数据将被视为无效。 </li>
                </ul>
                <br>
                <p class="b sub">主队最终得分-最后位数/ 客队最终得分-最后位数</p>
                <ul style="list-style:decimal">
                    <li>预测主队或客队最终得分的最后一位数字。</li>
                    <li>赛果以官方公布的赛事结果为准，包括加时。</li>
                </ul>
                <br>
                <p class="b sub">梦幻篮球游戏规则</p>
                <ul style="list-style:decimal">
                    <li>梦幻篮球游戏将从同一天的赛事中任意选择两个球队（且原定不是在同一场赛事比赛的球队）进行投注。 </li>
                    <li>梦幻比赛赛果会根据球队真实的比分为准；梦幻比赛的让分数则以球队真实比分来计算。 </li>
                    <li>梦幻赛中的两支球队必须在同一天比赛，投注才被视为有效。 </li>
                    <li>如果球队的比赛时间和本公司网站显示的时间不同，所有涉及此球队的梦幻赛注单将被视为无效。</li>
                    <li>梦幻赛投注将不考虑赛事实际进行的场地。 </li>
                    <li>梦幻赛的举例如下:
                        <ul style="list-style:lower-alpha">
                            <li>波士顿凯尔特人101 – 98 芝加哥公牛，洛杉矶湖人118 – 101 奥兰多魔术</li>
                            <li>梦幻赛1： 波士顿凯尔特人vs 洛杉矶湖人</li>
                            <li>梦幻赛赛果：波士顿凯尔特人101 – 118洛杉矶湖人</li>
                            <li>梦幻赛的结算会根据各球队原定比赛的真实得分为准。 </li>
                        </ul>
                    </li>
                    <li>球队一定要实际完成原定比赛并且已在赛事的官方机构（例如：NBA）留下赛果记录，才能让涉及此球队的梦幻赛注单保持有效。如果球队没有完成原定比赛或最终赛果被官方否定，所有涉及此球队的梦幻赛注单将被取消。 </li>
                    <li>所有梦幻赛都将按照篮球个别玩法的规则和标准裁决。 </li>
                </ul>
                <br>
            </ul>
            <div class="to_top"><a href="#top"><span>回最上层</span></a></div>
        </div><!--资料区 End-->
    </div>

</div>
<div class="qa_foot"></div>


</body></html>
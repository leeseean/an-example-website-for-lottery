<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
	<head>
		<title>皇冠體育</title>
		<%@ include file="/commons/sport/jsp/sport_common.jsp"%>
		 <script type="text/javascript">
			var sportAction = "${ctx}/sport/goMatchCenter";
			var secs = 90; //倒计时的秒数 
			var URL = sportAction + "?matchPage=${curPage}&timeType=${timeType}&matchType=${matchType}&rtype=${rtype}";
			var pageURL = sportAction + "?timeType=${timeType}&matchType=${matchType}&rtype=${rtype}";		
			var League = "${searchLeague}";
			var interval;
			var datasArry = eval('${datas}');
			var myLeg = new Array();
			function selectPdType(){
				var pdTypeId =document.getElementById("pdTypeId").value;
				window.location.href = sportAction+ "?timeType=${timeType}&matchType=${matchType}&rtype="+pdTypeId;
			}
		</script>
		<script src="${resourceRoot}/sport/js/sport_frasher.js"></script>
	</head>
<body id="MFT" class="bodyset FTRE" >
<div id="refresh_right" style="position: absolute; top: 39px; left: 700px;"
	class="refresh_M_btn"
	onclick="this.className='refresh_M_on';javascript:refreshReload()">
	<span>刷新</span>
</div>
<table border="0" cellpadding="0" cellspacing="0" id="myTable"><tbody><tr><td>
    <table border="0" cellpadding="0" cellspacing="0" id="box">
        <tbody> <tr>
            <td class="top" align="left"><h1>
             <em>
            ${typeName }： 
            <select  id="pdTypeId" onChange="selectPdType()">
            	<option value="pd" ${rtype eq 'pd'?'selected="selected"':'' }>全场</option>
            	<option value="hpd" ${rtype eq 'hpd'?'selected="selected"':'' }>上半场</option>
            </select>
            
             </em>
            
            </h1></td>
        </tr>
        <tr>
            <td class="mem"><h2>
               <%@ include file="match_page.jsp"%>
            </h2>
                <!--     资料显示的layer     -->
                <div id="showtable">
                    <table id="game_table" cellspacing="0" cellpadding="0" class="game">
                        <tbody><tr>
                            <th class="time">时间</th>
                            <th class="team">赛事</th>
                            <th class="h_pd_ft">1:0</th>
                            <th class="h_pd_ft">2:0</th>
                            <th class="h_pd_ft">2:1</th>
                            <th class="h_pd_ft">3:0</th>
                            <th class="h_pd_ft">3:1</th>
                            <th class="h_pd_ft">3:2</th>
                            <th class="h_pd_ft">4:0</th>
                            <th class="h_pd_ft">4:1</th>
                            <th class="h_pd_ft">4:2</th>
                            <th class="h_pd_ft">4:3</th>
                            <th class="h_pd_ft">0:0</th>
                            <th class="h_pd_ft">1:1</th>
                            <th class="h_pd_ft">2:2</th>
                            <th class="h_pd_ft">3:3</th>
                            <th class="h_pd_ft">4:4</th>
                            <th class="h_pd_ft">其它</th>
                        </tr>

					<!-- 赛事数据开始 -->
					<c:set var="tmpLeague" value="" ></c:set>
					<c:forEach var="item" items="${dataList}">
   					<!--SHOW LEGUAGE BEGIN-->
					<tr style="display:${tmpLeague eq item.league ? 'none':'' } ;">
                        <td colspan="18" class="b_hline"><table border="0" cellpadding="0" cellspacing="0" style="float: left;">
                            <tbody>
                              <tr>
                                <td class="legicon" onclick="showLeg('${item.league}')" ><span class="showleg" id="${item.league}" name="${item.league}"><span id="LegOpen"></span> </span></td>
                                <td class="leg_bar" onclick="showLeg('${item.league}')">${item.league}</td>
                              </tr>
                            </tbody>
                          </table></td>
                    </tr>
                    <c:set var="tmpLeague" value="${item.league}"></c:set>
                    <!--SHOW LEGUAGE END-->


	 <tr id="TR_${item.id }" style="display: ;">
	    <td rowspan="2" class="b_cen">
	     <c:if test="${item.timeType eq 'tom'}">
	                          ${fn:substring(item.matchTime,5,10)}<br>
	                        </c:if>
	                         ${fn:substring(item.matchTime,11,16)}<br>
	     <font color="red"></font>
	    
	    </td>
	    <td rowspan="2" class="team_name">
	    	${item.teamH }<font color=gray> - (上半)</font> <br>
	      	${item.teamC }<font color=gray> - (上半)</font>
	    </td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','1_0','H','h')" title="1:0">            		
				${item.iorH1c0 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','2_0','H','h')" title="2:0">
			${item.iorH2c0 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','2_1','H','h')" title="2:1">
			${item.iorH2c1 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','3_0','H','h')" title="3:0">
			${item.iorH3c0 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','3_1','H','h')" title="3:1">
			${item.iorH3c1 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','3_2','H','h')" title="3:2">
			${item.iorH3c2 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','4_0','H','h')" title="4:0">
			${item.iorH4c0 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','4_1','H','h')" title="4:1">
			${item.iorH4c1 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','4_2','H','h')" title="4:2">
				${item.iorH4c2 }
			</a>
		</td>
	    <td class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','4_3','H','h')" title="4:3">
				${item.iorH4c3 }
			</a>
		</td>
	    <td rowspan="2" class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','0_0','H','h')" title="0:0">
				${item.iorH0c0 }
			</a>
		</td>
	    <td rowspan="2" class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','1_1','H','h')" title="1:1">
				${item.iorH1c1 }
			</a>
		</td>
	    <td rowspan="2" class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','2_2','H','h')" title="2:2">
			${item.iorH2c2 }
		</a>
		</td>
	    <td rowspan="2" class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','3_3','H','h')" title="3:3">
			${item.iorH3c3 }
			</a>
		</td>
	    <td rowspan="2" class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','4_4','H','h')" title="4:4">
			${item.iorH4c4 }
			</a>
		</td>
	    <td rowspan="2" class="b_cen">
			<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','other','H','h')" title="其他">
			${item.iorOvh }
			</a>
		</td>
	  </tr>

  <tr id="TR1_${item.id }">
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','0_1','C','h')" title="0:1">
			${item.iorH0c1 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','0_2','C','h')" title="0:2">
			${item.iorH0c2 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','1_2','C','h')" title="1:2">
			${item.iorH1c2 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','0_3','C','h')" title="0:3">
			${item.iorH0c3 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','1_3','C','h')" title="1:3">
			${item.iorH1c3 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','2_3','C','h')" title="2:3">
			${item.iorH2c3 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','0_4','C','h')" title="0:4">
			${item.iorH0c4 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','1_4','C','h')" title="1:4">
			${item.iorH1c4 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','2_4','C','h')" title="2:4">
			${item.iorH2c4 }
		</a>
	</td>
    <td class="b_cen">
		<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','pd','pd','3_4','C','h')" title="3:4">
			${item.iorH3c4 }
		</a>
	</td>
  </tr>
					<!--SHOW MATCH END-->	
					</c:forEach>
					<!-- 赛事数据结束 -->
					
				<!-- 没有数据 -->
					<c:if test="${empty dataList }">
                    	<tr><td colspan="20" class="no_game">您选择的项目暂时没有赛事。请修改您的选项或迟些再返回。</td></tr>
                    </c:if>
           </tbody></table>

                </div>
            </td>
        </tr>
        <tr>
            <td id="foot"><b>&nbsp;</b></td>
        </tr>
        </tbody></table>

    <!--下方刷新钮-->
	<div id="refresh_down" class="refresh_M_btn" onClick="this.className='refresh_M_on';refreshReload()"><span>刷新</span> </div>


</td></tr></tbody></table>

<!-- 选择联赛 -->
<%@ include file="league.jsp"%>

<script type="text/javascript">
	Load();
	parent.header.changeMatchCount("${sc.timeType}","${sc.ftRtype}","${sc.bkRtype}","${sc.ftCount}","${sc.bkCount}");
</script>
</body>
</html>
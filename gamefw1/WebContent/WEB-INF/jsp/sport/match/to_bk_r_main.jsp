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
        <tbody>
        
        <tr>
            <td class="top" align="left"><h1><em>${typeName } </em>
            </h1></td>
        </tr>
        <tr>
            <td class="mem">
      		<h2><%@ include file="match_page.jsp"%></h2>
                <!--     资料显示的layer     -->
                <div id="showtable">


                    <table id="game_table" cellspacing="0" cellpadding="0" class="game">
                        <tbody>
                        <tr>
              				<th class="time">时间</th>
              				<th class="team">赛事</th>
             				<th class="h_m">独赢</th>
              				<th class="h_r">让分</th>
              				<th class="h_ou">大小</th>
              				<!--th class="h_oe">单/双</th-->
              				<th class="h_ouhc" colspan="2"><span class="h_oe">球队得分：大/小</span></th>
            			</tr>
						
					<!-- 赛事数据开始 -->
					<c:set var="tmpLeague" value="" ></c:set>
					<c:forEach var="item" items="${dataList}">
					
					<!--SHOW LEGUAGE BEGIN-->
					<tr style="display:${tmpLeague eq item.league ? 'none':'' } ;">
                        <td colspan="9" class="b_hline"><table border="0" cellpadding="0" cellspacing="0" style="float: left;">
                            <tbody>
                              <tr>
                                <td class="legicon"  onclick="showLeg('${item.league}')" ><span class="showleg" id="${item.league}" name="${item.league}"><span id="LegOpen"></span> </span></td>
                                <td class="leg_bar" onclick="showLeg('${item.league}')">${item.league}</td>
                              </tr>
                            </tbody>
                          </table></td>
                    </tr>
                    <c:set var="tmpLeague" value="${item.league}"></c:set>
                    <!--SHOW LEGUAGE END-->
                    <!--SHOW MATCH BEGIN-->
                    
					 <tr id="TR_${item.id }">
					    <td rowspan="2" class="b_cen">
					    					<c:if test="${item.timeType eq 'tom'}">
					                          ${fn:substring(item.matchTime,5,10)}<br>
					                        </c:if>
					                          ${fn:substring(item.matchTime,11,16)}<br>
					                          <font color="red">${'1' eq item.roll ? '滚球' : ''}</font>    </td>
					    <td rowspan="2" class="team_name">
					    
					    	${item.teamH } <br>
					        ${item.teamC }    </td>
					    <td class="b_cen bc_h">
							<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','dy','dy','H','f')" title="${item.teamH }">
								${item.iorMh}
							</a>	
						</td>
					    <td class="b_rig">
							${item.strong eq 'H'?item.ratio:'' }
							<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','rf','rf','H','f')" title="${item.teamH }">
								${item.iorRh}		
							</a>	
						</td>
					    <td class="b_rig">
							${!empty item.ratioO ? '大':''}${item.ratioO }
							<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','dx','dx','H','f')" title="大">
								${item.iorOuc }		
							</a>	
							</td>
					    <!--td class="b_cen">*RATIO_EOO*</td-->
					    <td class="b_rig">
						${!empty item.ratioOuho ? '大':''}${item.ratioOuho } 
						<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','jf','dx_big','H','f')" title="${item.teamH }"> 
						${item.iorOuho }
						</a>
						</td>
						<td class="b_rig">
						${!empty item.ratioOuhu ? '小':''}${item.ratioOuhu } 
						<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','jf','dx_small','H','f')" title="${item.teamH }"> 
						${item.iorOuhu } 
						</a>
						</td>
					 </tr>
					  <tr id="TR1_${item.id }">
					  	<td class="b_cen bc_h">
							<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','dy','dy','C','f')" title="${item.teamC }">
								${item.iorMc}		
							</a>	
						</td>
					    <td class="b_rig">
							${item.strong eq 'C'?item.ratio:'' }
							<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','rf','rf','C','f')" title="${item.teamC }">
								${item.iorRc}		
							</a>	
						</td>
					    <td class="b_rig">
							${!empty item.ratioU ? '小':''}${item.ratioU }
							<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','dx','dx','C','f')" title="小">
								${item.iorOuh }		
							</a>	
						</td>
					    <td class="b_rig">
						${!empty item.ratioOuco ? '大':''}${item.ratioOuco }
						 <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','jf','dx_big','C','f')" title="${item.teamH }"> 
						 ${item.iorOuco } 
						 </a>
						 </span>
						</td>
					    <td class="b_rig">
						${!empty item.ratioOucu ? '小':''}${item.ratioOucu } 
						<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','BK','r_main','jf','dx_small','C','f')" title="${item.teamH }"> 
						${item.iorOucu } 
						</a>
						</span>
						</td>
					    <!--td class="b_cen">*RATIO_EOE*</td-->
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
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
                                    <th class="time" nowrap="">时间</th>
                                    <th class="team" nowrap="">赛事</th>
                                    <th class="h_1x2" nowrap="">独赢</th>
                                    <th class="h_r" nowrap="">让分</th>
                                    <th class="h_ou" nowrap="">大小</th>
                                    <th colspan="2" nowrap="" class="h_oe">球队得分：大/小</th>
                                </tr>
						
					<!-- 赛事数据开始 -->
					<c:set var="tmpLeague" value="" ></c:set>
					<c:forEach var="item" items="${dataList}">
					
					<!--SHOW LEGUAGE BEGIN-->
					<tr style="display:${tmpLeague eq item.league ? 'none':'' } ;">
                        <td colspan="10" class="b_hline"><table border="0" cellpadding="0" cellspacing="0" style="float: left;">
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
                          			${fn:substring(item.matchTime,11,16)}<br>                                    </td>
                                    <td rowspan="2" class="team_name">
                                    ${item.teamH } <br>
        							${item.teamC }                                    </td>
                                    <td class="b_cen">
									<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','dy','dy','H','f','${item.gidm}')" title="${item.teamH }">
									${item.iorMh}									</a>									</td>
                                    <td class="b_rig">
									${item.strong eq 'H'?item.ratio:'' }
									<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','rf','rf','H','f','${item.gidm}')" title="${item.teamH }">
										${item.iorPrh}									</a>									</td>
                                    <td class="b_rig">
									${!empty item.ratioO ? '大':''}${item.ratioO }
									<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','dx','dx','H','f','${item.gidm}')" title="${item.teamH }">		
										${item.iorPouc }									</a>									</td>
                                    <td class="bg_light_bk">
									${!empty item.ratioPouho ? '大':''}${item.ratioPouho }
									<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','jf','dx_big','H','f','${item.gidm}')" title="${item.teamH }">	
									${item.iorPouho }
									</a>
									</td>
                                    <td class="b_rig">
									${!empty item.ratioPouhu ? '小':''}${item.ratioPouhu }
									<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','jf','dx_small','H','f','${item.gidm}')" title="${item.teamH }">	
									${item.iorPouhu }
									</a>
							</td>
						</tr>
                        <tr id="TR1_${item.id }">
                           <td class="b_cen">
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','dy','dy','C','f','${item.gidm}')" title="${item.teamH }">		
							${item.iorMc}									</a>									</td>
                                  <td class="b_rig">
							${item.strong eq 'C'?item.ratio:'' }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','rf','rf','C','f','${item.gidm}')" title="${item.teamH }">		
							${item.iorPrc}									</a>									</td>
                                  <td class="b_rig">
							${!empty item.ratioU ? '小':''}${item.ratioU }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','dx','dx','C','f','${item.gidm}')" title="${item.teamH }">
							${item.iorPouh }									</a>									</td>
                                  <td class="b_rig">
							${!empty item.ratioPouco ? '大':''}${item.ratioPouco } 
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','jf','dx_big','C','f','${item.gidm}')" title="${item.teamH }">
							${item.iorPouco } 
							</a>									</td>
                                  <td class="b_rig">
							${!empty item.ratioPoucu ? '小':''}${item.ratioPoucu } 
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','BK','p3','jf','dx_small','C','f','${item.gidm}')" title="${item.teamH }"> 									${item.iorPoucu } 
							</a>
							</td>
                       </tr>
                      
                      
					<!--SHOW MATCH END-->	
					</c:forEach>
					<!-- 赛事数据结束 -->
					
					<!-- 没有数据 -->
					<c:if test="${empty dataList }">
                    	<tr><td colspan="21" class="no_game">您选择的项目暂时没有赛事。请修改您的选项或迟些再返回。</td></tr>
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
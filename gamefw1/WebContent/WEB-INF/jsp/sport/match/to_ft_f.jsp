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
        <tbody> <tr>
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
                                    <th class="h_f">主 / 主</th>
                                    <th class="h_f">主 / 和</th>
                                    <th class="h_f">主 / 客</th>
                                    <th class="h_f">和 / 主</th>
                                    <th class="h_f">和 / 和</th>
                                    <th class="h_f">和 / 客</th>
                                    <th class="h_f">客 / 主</th>
                                    <th class="h_f">客 / 和</th>
                                    <th class="h_f">客 / 客</th>
                                </tr>


					<!-- 赛事数据开始 -->
					<c:set var="tmpLeague" value="" ></c:set>
					<c:forEach var="item" items="${dataList}">
   					<!--SHOW LEGUAGE BEGIN-->
					<tr style="display:${tmpLeague eq item.league ? 'none':'' } ;">
                        <td colspan="18" class="b_hline"><table border="0" cellpadding="0" cellspacing="0" style="float: left;">
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


 					<tr id="TR_${item.id }" style="display: ;">
                                    <td class="b_cen">
                                    <c:if test="${item.timeType eq 'tom'}">
                          				${fn:substring(item.matchTime,5,10)}<br>
                        			</c:if>
                          			${fn:substring(item.matchTime,11,16)}<br>
                                    </td>
                                    <td class="team_name">
                                    	${item.teamH } <br>
                           				${item.teamC }
                                    </td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','H_H','H_H','f')" title="H/H">	
											${item.iorFhh}
										</a>
									</td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','H_N','H_N','f')" title="H/D">	
											${item.iorFhn}
										</a>
									</td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','H_C','H_C','f')" title="H/A">	
											${item.iorFhc}
										</a>
									</td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','N_H','N_H','f')" title="D/H">	
											${item.iorFnh}
										</a>
									</td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','N_N','N_N','f')" title="D/D">	
											${item.iorFnn}
										</a>
									</td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','N_C','N_C','f')" title="D/A">	
											${item.iorFnc}
										</a>
									</td>
                                   <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','C_H','C_H','f')" title="A/H">	
											${item.iorFch}
										</a>
									</td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','C_N','C_N','f')" title="A/D">	
											${item.iorFcn}
										</a>
									</td>
                                    <td class="b_cen">
										<a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','f','f','C_C','C_C','f')" title="A/A">	
											${item.iorFcc}
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
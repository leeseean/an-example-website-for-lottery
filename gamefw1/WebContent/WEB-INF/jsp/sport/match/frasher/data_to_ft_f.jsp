<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<body id="MFT" class="bodyset FTRE" >
 
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
                        <td colspan="18" class="b_hline"><table border="0" cellpadding="0" cellspacing="0">
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
 
</body>
</html>
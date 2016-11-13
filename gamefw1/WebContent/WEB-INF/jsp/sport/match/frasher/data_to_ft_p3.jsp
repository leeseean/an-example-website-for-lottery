<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
 
<body id="MFT" class="bodyset FTRE" >
 
                    <table id="game_table" cellspacing="0" cellpadding="0" class="game">
                        <tbody><tr>
                            <th class="time_re" nowrap="">时间</th>
                            <th class="team" nowrap="">赛事</th>
                            <th class="h_1x2" nowrap="">独赢</th>
                            <th class="h_r" nowrap="">全场 - 让球</th>
                            <th class="h_ou" nowrap="">全场 - 大小</th>
                            <th nowrap="" class="h_oe">单双</th>
                            <th class="h_1x2" nowrap="">独赢</th>
                            <th class="h_r" nowrap="">半场 - 让球</th>
                            <th class="h_ou" nowrap="">半场 - 大小</th>
                        </tr>
						
						
					<!-- 赛事数据开始 -->
					<c:set var="tmpLeague" value="" ></c:set>
					<c:forEach var="item" items="${dataList}">
					
					<!--SHOW LEGUAGE BEGIN-->
					<tr style="display:${tmpLeague eq item.league ? 'none':'' } ;">
                        <td colspan="9" class="b_hline"><table border="0" cellpadding="0" cellspacing="0">
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
                    <!--SHOW MATCH BEGIN-->
                    <tr id="TR_${item.id }" style="display: ;">
                        <td rowspan="3" class="b_cen time_main">
                        <c:if test="${item.timeType eq 'tom'}">
                          ${fn:substring(item.matchTime,5,10)}<br>
                        </c:if>
                          ${fn:substring(item.matchTime,11,16)}<br>
                          <font color="red">${'1' eq item.roll ? '滚球' : ''}</font>
                        </td>
                        <td rowspan="2" class="team_name none">
                           ${item.teamH } <br>
                           ${item.teamC }
                        </td>
                        <td class="b_cen">
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dy','dy','H','f','${item.gidm}')" title="${item.teamH }">
								${item.iorMh }
							</a>
                        </td>
                        <td class="b_rig">
                        	${item.strong eq 'H'?item.ratio:'' }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','rq','rq','H','f','${item.gidm}')" title="${item.teamH }">
                        	${item.iorPrh }
							</a>
						</td>
                        <td class="b_rig">
                        	大${item.ratioO }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dx','dx','H','f','${item.gidm}')" title="大">
                        	${item.iorPouc }
							</a>
							
                        </td>
                        
                        <td class="b_cen">
							单
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','ds','ds','H','f','${item.gidm}')" title="单">
							${item.iorPeoo }
							</a>
						</td>
                        <td class="b_1st">
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dy','dy','H','h','${item.gidm}')" title="${item.teamH }">
							${item.iorHmh }
							</a>
						</td>
                        <td class="b_1stR">
							${item.hstrong eq 'H'?item.hratio:'' }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','rq','rq','H','h','${item.gidm}')" title="${item.teamH }">
							${item.iorHprh }
							</a>
						</td>
                        <td class="b_1stR">
							${empty item.hratioO ?'':'大' }
							${item.hratioO }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dx','dx','H','h','${item.gidm}')" title="大">
							${item.iorHpouc }
							</a>
						</td>
                      </tr>
                      <tr id="TR1_${item.id }" style="display: ;">
                        <td class="b_cen">
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dy','dy','C','f','${item.gidm}')" title="${item.teamC }">
								${item.iorMc }
							</a>
						</td>
                        <td class="b_rig">
							${item.strong eq 'C'?item.ratio:'' }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','rq','rq','C','f','${item.gidm}')" title="${item.teamC }">
							${item.iorPrc }
							</a>
						</td>
                        <td class="b_rig">
							小${item.ratioU }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dx','dx','C','f','${item.gidm}')" title="小">
							${item.iorPouh }
							</a>
						</td>
                        <td class="b_cen">
							双
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','ds','ds','C','f','${item.gidm}')" title="双">
							${item.iorPeoe }
							</a>
						</td>
                        <td class="b_1st">
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dy','dy','C','h','${item.gidm}')" title="${item.teamC }">
							${item.iorHmc }
							</a>
						</td>
                        <td class="b_1stR">
							${item.hstrong eq 'C'?item.hratio:'' }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','rq','rq','C','h','${item.gidm}')" title="${item.teamC }">
							${item.iorHprc }
							</a>
						</td>
                        <td class="b_1stR">
							${empty item.hratioU ?'':'小' }
							${item.hratioU }
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dx','dx','C','h','${item.gidm}')" title="小">
							${item.iorHpouh }
							</a>
						</td>
                      </tr>
                      <tr id="TR2_${item.id }">
                        <td class="drawn_td"><table width="99%" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                              <tr>
                                <td align="left">和局</td>
                                <td class="hot_td"></td>
                                <td class="hot_tv"></td>
                              </tr>
                            </tbody>
                          </table>
                        </td>
                        <td class="b_cen">
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dy','dy','N','f','${item.gidm}')" title="和局">
							${item.iorMn }
							</a>
						</td>
                        <td colspan="3" valign="top" class="b_cen">
						</td>
                        <td class="b_1st">
							<a href="javascript://" onClick="parent.orderFrame.betOrderP3('${item.gid}','${timeType}','FT','p3','dy','dy','N','h','${item.gidm}')" title="和局">
							${item.iorHmn}
							</a>
						</td>
                        <td colspan="3" valign="top" class="b_1st">&nbsp;</td>
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
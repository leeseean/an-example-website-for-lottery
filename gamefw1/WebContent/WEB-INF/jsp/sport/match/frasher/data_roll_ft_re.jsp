<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
 
<body id="MFT" class="bodyset FTRE">
	
	<table id="game_table" cellspacing="0" cellpadding="0" class="game">
			<tbody>
				<tr>
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
				<c:set var="tmpLeague" value=""></c:set>
				<c:forEach var="item" items="${dataList}">
					<!--SHOW LEGUAGE BEGIN-->
					<tr style="display:${tmpLeague eq item.league ? 'none':'' } ;">
						<td colspan="9" class="b_hline">
							<table border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td class="legicon" onclick="showLeg('${item.league}')" ><span class="showleg" id="${item.league}" name="${item.league}"><span id="LegOpen"></span> </span></td>
										<td class="leg_bar" onclick="showLeg('${item.league}')">${item.league}</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<c:set var="tmpLeague" value="${item.league}"></c:set>
					<!--SHOW LEGUAGE END-->
					<!--SHOW MATCH BEGIN-->
					<tr id="TR_${item.id }" style="display: ;">
						<td rowspan="3" class="b_cen">
							<table border="1" cellpadding="0" cellspacing="0" class="rb_box_bk">
								<tbody>
									<tr><td class="rb_time">${item.retimeset } <font class="rb_color">'</font></td></tr>
									<tr><td class="rb_score">${item.scoreH}&nbsp;-&nbsp;${item.scoreC }</td></tr>
								</tbody>
							</table>
						</td>
						<td rowspan="2" class="team_name none" >
							<table border="0" cellspacing="0" cellpadding="0" class="re_team_box">
								<tbody>
									<tr>
										<td class="re_team">${item.teamH }</td>
										<td><c:if test="${item.redcardH >0}"><span class="red_card">${item.redcardH }</span></c:if></td>
									</tr>
								</tbody>
							</table>

							<table border="0" cellspacing="0" cellpadding="0"
								class="re_team_box">
								<tbody>
									<tr>
										<td class="re_team">${item.teamC }</td>
										<td><c:if test="${item.redcardC >0}"><span class="red_card">${item.redcardC }</span></c:if></td>
									</tr>
								</tbody>
							</table>
							
						</td>
						<td class="b_cen td_h"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dy','dy','H','f')" title="${item.teamH }" <c:if test="${fn:contains(item.frasherDate, 'iorMh')}">style="background-color : yellow"</c:if>>${item.iorMh} </a></td>
						<td class="b_rig">${item.strong eq 'H'?item.ratio:''} <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','rq','rq','H','f')" title="${item.teamH }" <c:if test="${fn:contains(item.frasherDate, 'iorRh')}">style="background-color : yellow"</c:if>> ${item.iorRh } </a></td>
						<td class="b_rig">${!empty item.ratioO ? '大':''}${item.ratioO } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dx','dx','H','f')" title="${item.teamH }" <c:if test="${fn:contains(item.frasherDate, 'iorOuc')}">style="background-color : yellow"</c:if>> ${item.iorOuc } </a></td>
						<td class="b_cen">${item.strOdd } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','ds','ds','H','f')" title="${item.teamH }"   <c:if test="${fn:contains(item.frasherDate, 'iorEoo')}">style="background-color : yellow"</c:if>> ${item.iorEoo } </a></td>

						<c:if test="${item.setType eq '1'}">
							<td class="b_1st"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dy','dy','H','h')" title="${item.teamH }"   <c:if test="${fn:contains(item.frasherDate, 'iorHmh')}">style="background-color : yellow"</c:if>> ${item.iorHmh } </a></td>
							<td class="b_1stR">${item.hstrong eq 'H'?item.hratio:'' } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','rq','rq','H','h')" title="${item.teamH }"   <c:if test="${fn:contains(item.frasherDate, 'iorHrh')}">style="background-color : yellow"</c:if>> ${item.iorHrh } </a></td>
							<td class="b_1stR">${empty item.hratioO ?'':'大'}${item.hratioO } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dx','dx','H','h')" title="${item.teamH }"   <c:if test="${fn:contains(item.frasherDate, 'iorHouc')}">style="background-color : yellow"</c:if>> ${item.iorHouc } </a></td>
						</c:if>
						<c:if test="${item.setType eq '2' or item.setType eq '3' }">
							<td class="b_1st"></td>
							<td class="b_1stR"></td>
							<td class="b_1stR"></td>
						</c:if>

					</tr>
					<tr id="TR1_${item.id }" style="display: ;">
						<td class="b_cen"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dy','dy','C','f')" title="${item.teamH }" <c:if test="${fn:contains(item.frasherDate, 'iorMc')}">style="background-color : yellow"</c:if>> ${item.iorMc} </a></td>
						<td class="b_rig">${item.strong eq 'C'?item.ratio:''} <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','rq','rq','C','f')" title="${item.teamH }"  <c:if test="${fn:contains(item.frasherDate, 'iorRc')}">style="background-color : yellow"</c:if> > ${item.iorRc } </a></td>
						<td class="b_rig">${!empty item.ratioU ? '小':'' }${item.ratioU } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dx','dx','C','f')" title="${item.teamH }"  <c:if test="${fn:contains(item.frasherDate, 'iorOuh')}">style="background-color : yellow"</c:if>> ${item.iorOuh } </a></td>
						<td class="b_cen">${item.strEven } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','ds','ds','C','f')" title="${item.teamH }"  <c:if test="${fn:contains(item.frasherDate, 'iorEoe')}">style="background-color : yellow"</c:if>> ${item.iorEoe } </a></td>
						<c:if test="${item.setType eq '1' }">
							<td class="b_1st"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dy','dy','C','h')" title="${item.teamH }"  <c:if test="${fn:contains(item.frasherDate, 'iorHmc')}">style="background-color : yellow"</c:if>> ${item.iorHmc } </a></td>
							<td class="b_1stR">${item.hstrong eq 'C'?item.hratio:'' } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','rq','rq','C','h')" title="${item.teamH }" <c:if test="${fn:contains(item.frasherDate, 'iorHrc')}">style="background-color : yellow"</c:if>> ${item.iorHrc } </a></td>
							<td class="b_1stR">${empty item.hratioU ?'':'小'}${item.hratioU } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dx','dx','C','h')" title="${item.teamH }"  <c:if test="${fn:contains(item.frasherDate, 'iorHouh')}">style="background-color : yellow"</c:if>> ${item.iorHouh } </a></td>
						</c:if>
						<c:if test="${item.setType eq '2' or item.setType eq '3' }">
							<td class="b_1st">&nbsp;</td>
							<td class="b_1stR">&nbsp;</td>
							<td class="b_1stR">&nbsp;</td>
						</c:if>

					</tr>
					<tr id="TR2_${item.id }" style="display: ;">
						<td class="drawn_td">
							<table width="99%" border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td align="left">和局</td>
										<td class="hot_td"></td>
										<td class="hot_tv"></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td class="b_cen"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dy','','N','f')" title="${item.teamH }" <c:if test="${fn:contains(item.frasherDate, 'iorMn')}">style="background-color : yellow"</c:if>> ${item.iorMn} </a></td>
						<td colspan="3" valign="top" class="b_cen"></td>
						<td class="b_1st"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','re','dy','','N','h')" title="${item.teamH }" <c:if test="${fn:contains(item.frasherDate, 'iorHmn')}">style="background-color : yellow"</c:if>> ${item.setType eq '1' ? item.iorHmn:''} </a></td>
						<td colspan="3" valign="top" class="b_1st">&nbsp;</td>
					</tr>
					<!--SHOW MATCH END-->
				</c:forEach>
				<!-- 赛事数据结束 -->
				<!-- 没有数据 -->
				<c:if test="${empty dataList }">
					<tr><td colspan="20" class="no_game">您选择的项目暂时没有赛事。请修改您的选项或迟些再返回。</td></tr>
				</c:if>
			</tbody>
		</table>
</body>
</html>
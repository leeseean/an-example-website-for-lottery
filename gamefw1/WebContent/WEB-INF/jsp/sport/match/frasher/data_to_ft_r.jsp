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
					<td rowspan="3" class="b_cen time_main">
						<!-- 2015-10-11 00:01:00 --> 
						<c:if test="${item.timeType eq 'tom'}">
							${fn:substring(item.matchTime,5,10)}<br>
						</c:if> ${fn:substring(item.matchTime,11,16)} <br> <font color="red">${'1' eq item.roll ? '滚球' : ''}</font></td>
					<td rowspan="2" class="team_name none">${item.teamH } <br> ${item.teamC }</td>
					<td class="b_cen"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dy','dy','H','f')" title="${item.teamH }"> ${item.iorMh } </a></td>
					<td class="b_rig">${item.strong eq 'H'?item.ratio:'' } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','rq','rq','H','f')" title="${item.teamH }"> ${item.iorRh } </a></td>
					<td class="b_rig">大${item.ratioO } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dx','dx','H','f')" title="大"> ${item.iorOuc } </a></td>

					<td class="b_cen">${item.strOdd } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','ds','ds','H','f')" title="${item.strOdd }"> ${item.iorEoo } </a></td>
					<td class="b_1st"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dy','dy','H','h')" title="${item.teamH }"> ${item.iorHmh } </a></td>

					<td class="b_1stR">${item.hstrong eq 'H'?item.hratio:'' } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','rq','rq','H','h')" title="${item.teamH }"> ${item.iorHrh } </a></td>

					<td class="b_1stR">${empty item.hratioO ?'':'大'}${item.hratioO } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dx','dx','H','h')" title="大"> ${item.iorHouc } </a></td>
				</tr>
				<tr id="TR1_${item.id }">
					<td class="b_cen"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dy','dy','C','f')" title="${item.teamC }"> ${item.iorMc } </a></td>
					<td class="b_rig">${item.strong eq 'C'?item.ratio:'' } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','rq','rq','C','f')" title="${item.teamC }"> ${item.iorRc } </a></td>
					<td class="b_rig">小${item.ratioU } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dx','dx','C','f')" title="小"> ${item.iorOuh } </a></td>
					<td class="b_cen">${item.strEven } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','ds','ds','C','f')" title="${item.strEven }"> ${item.iorEoe } </a></td>
					<td class="b_1st"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dy','dy','C','h')" title="${item.teamC }"> ${item.iorHmc } </a></td>

					<td class="b_1stR">${item.hstrong eq 'C'?item.hratio:'' } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','rq','','C','h')" title="${item.teamH }"> ${item.iorHrc } </a></td>

					<td class="b_1stR">${empty item.hratioU ?'':'小'}${item.hratioU } <a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dx','','C','h')" title="小"> ${item.iorHouh } </a></td>
				</tr>
				<tr id="TR2_${item.id }">
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
					<td class="b_cen"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dy','','N','f')" title="和局"> ${item.iorMn } </a></td>
					<td colspan="3" class="b_cen"></td>
					<td class="b_1st"><a href="javascript://" onClick="parent.orderFrame.betOrder('${item.gid}','${timeType}','FT','r','dy','','N','h')" title="和局"> ${item.iorHmn} </a></td>
					<td colspan="3" valign="top" class="b_1st">&nbsp;</td>
				</tr>
				<!--SHOW MATCH END-->
			</c:forEach>
			<!-- 赛事数据结束 -->

			<!-- 没有数据 -->
			<c:if test="${empty dataList }">
				<tr>
					<td colspan="20" class="no_game">您选择的项目暂时没有赛事。请修改您的选项或迟些再返回。</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>